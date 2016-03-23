package com.lizardtech.djvubean.outline;

import com.lizardtech.djvu.DjVuOptions;
import com.lizardtech.djvu.DjVuPage;
import com.lizardtech.djvu.Document;
import com.lizardtech.djvubean.DjVuImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class CreateThumbnails implements ListCellRenderer {

    public static Document document;

    public static BufferedImage getScreenShot(Component component) {
        BufferedImage bi = new BufferedImage(component.getSize().width, component.getSize().height, BufferedImage.TYPE_INT_RGB);
        component.paint(bi.getGraphics());
        return bi;
    }

    public static BufferedImage Resizeimage(Image image, int thumbWidth,
            int thumbHeight) {

        // determine thumbnail size from WIDTH and HEIGHT
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }

        // draw original image to thumbnail image object and
        // scale it to the new size on-the-fly
        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

        return thumbImage;
    }

    /**
     * From <a
     * href="http://java.sun.com/javase/6/docs/api/javax/swing/ListCellRenderer.html:"
     * title="http://java.sun.com/javase/6/docs/api/javax/swing/ListCellRenderer.html:">http://java.sun.com/javase/6/docs/api/javax/swing/ListCellRenderer.html:</a>
     *
     * Return a component that has been configured to display the specified value. That component's
     * paint method is then called to "render" the cell. If it is necessary to compute the
     * dimensions of a list because the list cells do not have a fixed size, this method is called
     * to generate a component on which getPreferredSize can be invoked.
     *
     * jlist - the jlist we're painting value - the value returned by
     * list.getModel().getElementAt(index). cellIndex - the cell index isSelected - true if the
     * specified cell is currently selected cellHasFocus - true if the cell has focus
     */
    public Component getListCellRendererComponent(JList jlist,
            Object value,
            int cellIndex,
            boolean isSelected,
            boolean cellHasFocus) {
        if (value instanceof JPanel) {
            Component component = (Component) value;
            component.setForeground(Color.white);
            component.setBackground(isSelected ? UIManager.getColor("Table.focusCellForeground") : Color.white);
            return component;
        } else {
            // TODO - I get one String here when the JList is first rendered; proper way to deal with this?
            //System.out.println("Got something besides a JPanel: " + value.getClass().getCanonicalName());
            return new JLabel("???");
        }
    }

    public static BufferedImage getCroppedImage(BufferedImage source, double tolerance) {
        // Get our top-left pixel color as our "baseline" for cropping
        int baseColor = source.getRGB(0, 0);

        int width = source.getWidth();
        int height = source.getHeight();

        int topY = Integer.MAX_VALUE, topX = Integer.MAX_VALUE;
        int bottomY = -1, bottomX = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (colorWithinTolerance(baseColor, source.getRGB(x, y), tolerance)) {
                    if (x < topX) {
                        topX = x;
                    }
                    if (y < topY) {
                        topY = y;
                    }
                    if (x > bottomX) {
                        bottomX = x;
                    }
                    if (y > bottomY) {
                        bottomY = y;
                    }
                } else {
                }
            }
        }

        BufferedImage destination = new BufferedImage((bottomX - topX + 1),
                (bottomY - topY + 1), BufferedImage.TYPE_INT_ARGB);

        destination.getGraphics().drawImage(source, 0, 0,
                destination.getWidth(), destination.getHeight(),
                topX, topY, bottomX, bottomY, null);

        return destination;
    }

    private static boolean colorWithinTolerance(int a, int b, double tolerance) {
        int aAlpha = (int) ((a & 0xFF000000) >>> 24);   // Alpha level
        int aRed = (int) ((a & 0x00FF0000) >>> 16);   // Red level
        int aGreen = (int) ((a & 0x0000FF00) >>> 8);    // Green level
        int aBlue = (int) (a & 0x000000FF);            // Blue level

        int bAlpha = (int) ((b & 0xFF000000) >>> 24);   // Alpha level
        int bRed = (int) ((b & 0x00FF0000) >>> 16);   // Red level
        int bGreen = (int) ((b & 0x0000FF00) >>> 8);    // Green level
        int bBlue = (int) (b & 0x000000FF);            // Blue level

        double distance = Math.sqrt((aAlpha - bAlpha) * (aAlpha - bAlpha)
                + (aRed - bRed) * (aRed - bRed)
                + (aGreen - bGreen) * (aGreen - bGreen)
                + (aBlue - bBlue) * (aBlue - bBlue));

        // 510.0 is the maximum distance between two colors 
        // (0,0,0,0 -> 255,255,255,255)
        double percentAway = distance / 510.0d;

        return (percentAway > tolerance);
    }

    // TODO: check the resolution (size in memory) of the image.
    // TODO: Documentation.
    public static BufferedImage generateThumbnail(final int pageNumber, final int width, final int height)
            throws IOException {
        final DjVuPage[] page = {document.getPage(pageNumber, DjVuPage.MAX_PRIORITY, true)};
        final DjVuImage image = new DjVuImage(page, false);

        Rectangle bounds = new Rectangle(width, height);
        Image[] awtImages = image.getScaledInstance((int) bounds.getWidth(), (int) bounds.getHeight()).getImage(new Frame(), bounds);

        Image awtImage = awtImages[0];
        BufferedImage bimg = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bimg.getGraphics();
        graphics.drawImage(awtImage, 0, 0, null);

        return bimg;

    }
}
