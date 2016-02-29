package com.lizardtech.djview;

import com.lizardtech.djview.frame.Frame;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.outline.CreateThumbnails;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

// TODO: Documentation.
public class FullBookView
        extends JPanel {

    public JScrollPane ThumblainsScrollPane;
    public static int PagesCount;
    public static boolean Continous = false;
    public static boolean Is_Rotated = false;
    private DefaultListModel<JPanel> pages;
    private JList ThumblainsList;
    // NOTE: this could be the buffer, you can use DefaultListModel instead.
    private BufferedImage img[];
    private ImageIcon BookImages[];
    private JLabel PagesLabel[];
    // TODO: revisite the rotation.
//    private final JPanel Rotatedpane;
//    private final JScrollPane RotatedpaneScrollPane;
//    private BufferedImage RotatedpaneImage;
//    private final JLabel RotatedpaneLabel;
    private final DjVuBean djvubean;
    private final Frame frame;
    private final JPanel topPanel;
    private final JPanel beanPanel;
    private final JPanel defaultPage;
    private final CardLayout cardLayout;
    private final int Width;
    private final int Height;
    private int Pagenum = 0;

    public FullBookView(final DjVuBean djvubean, final Frame frame, JPanel beanPanel) {

        this.djvubean = djvubean;
        this.frame = frame;
        this.beanPanel = beanPanel;
        cardLayout = (CardLayout) beanPanel.getLayout();
        defaultPage = new JPanel();
        Width = 720;
        Height = 768;

//        Rotatedpane = new JPanel(new BorderLayout());
//        RotatedpaneScrollPane = new JScrollPane(Rotatedpane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        RotatedpaneLabel = new JLabel();
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.gray);
//        Rotatedpane.setBackground(Color.gray);
        init();

        img = new BufferedImage[PagesCount];

        beanPanel.add(topPanel, "FullBook");
//        beanPanel.add(RotatedpaneScrollPane, "Rotate");
        cardLayout.first(beanPanel);
    }

    /**
     * initialize the variables and views.
     */
    public void init() {

        ThumblainsList = new JList();
        ThumblainsScrollPane = new JScrollPane(ThumblainsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ThumblainsScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            private final int bufferSize = 1;       // number of pages above and below the current image.
            private final int timeGap = 110;        // time gap in milleseconds to draw a page.
            private int oldIndex;
            private long oldTime;                   // the time since the last change in the scroll value.
            private int index;
            private Thread gThread;

            {
                this.gThread = new Thread(() -> {
                    try {
                        resetAndDraw(index);
                    } catch (IOException ex) {
                    }
                });
            }

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                index = e.getValue() / Height;
                if ((System.currentTimeMillis() - oldTime) > timeGap && index != oldIndex) {
//                    try {
                    // draw pages and reset old pages
                    gThread.interrupt();
                    gThread = new Thread(() -> {
                        try {
                            resetAndDraw(index);
                        } catch (IOException ex) {
                        }
                    });
                    gThread.start();

//                        resetAndDraw(index);
//                    } catch (IOException ex) {
//                    }
                    // set old values
//                    oldIndex = index;
//                    oldTime = System.currentTimeMillis();
                }

            }

            /**
             * Draws indexed pages and reset the old indexed page
             *
             * @param index the index of the image to draw starting from 0
             */
            private void resetAndDraw(int index) throws IOException {
                System.out.println("index: " + index + " old index: " + oldIndex);
//                if (index - oldIndex < bufferSize) {        // pages above the indexed page can be reused.
//                    // reset pages
//                    for (int i = oldIndex - bufferSize; i < index - bufferSize; i++) {
//                        pages.set(i, defaultPage);
//                    }
//
//                    // draw pages
//                    for (int i = oldIndex + bufferSize + 1; i <= index + bufferSize; i++) {
//                        draw(i);
//                    }
//                } else if (oldIndex - index < bufferSize) { // pages below the indexed page can be reused.
//                    for (int i = oldIndex + bufferSize; i > index + bufferSize; i--) {      // reset.
//                        pages.set(i, defaultPage);
//                    }
//                    for (int i = oldIndex + bufferSize + 1; i >= index + bufferSize; i--) {  // draw.
//                        draw(i);
//
//                    }
//                } else {                                    // no pages can be reused.
//                for (int i = oldIndex - bufferSize; i <= oldIndex + bufferSize; i++) {  // reset.
//                    try {
//                        pages.set(i, null);
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        System.out.println("Exception");
//                        // excepected behaviour
//                    }
//                }
//
//                for (int i = index - bufferSize; i <= index + bufferSize; i++) {        // draw.
//                    try {
//                        draw(i);
//                    } catch (ArrayIndexOutOfBoundsException ex) {
//                        // excepected behaviour
//                    }
//                }
//                }

//                pages.remove(index);
//                draw(index);
//
//                oldIndex = index;
//                oldTime = System.currentTimeMillis();
            }

            /**
             * Draws a page given the index
             *
             * @param i the index of the page to draw starting from 0.
             */
            private void draw(int i) throws IOException {
                JLabel tempLabel;
                tempLabel = new JLabel("" + (i + 1));
                tempLabel.setHorizontalTextPosition(JLabel.CENTER);
                tempLabel.setVerticalTextPosition(JLabel.BOTTOM);
                tempLabel.setForeground(Color.GRAY);

                // create the corresponding panels
                JPanel tempPanel;
                tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                tempPanel.add(tempLabel);
                tempPanel.setForeground(Color.GRAY);

                // add the images to jlabels with text
                tempLabel.setSize(Width, Height);
                tempLabel.setIcon(new ImageIcon(CreateThumbnails.generateThumbnail(i, Width, Height)));

                pages.set(i, tempPanel);
            }
        });
        BookImages = new ImageIcon[PagesCount];
//        TODO: create a page template for the wait.
        pages = new DefaultListModel<>();
        pages.setSize(PagesCount);
        PagesLabel = new JLabel[PagesCount];
        // get  images of book
        // Set every page in 

        // tell the ThumblainsList to use the panel array for its data
        ThumblainsList.setModel(pages);
        ThumblainsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // TODO: revisite next statement.
                djvubean.setPage(djvubean.getPage());

                if (e.getClickCount() == 1) {
                    JList JLelement = (JList) e.getSource();
                    djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
                    frame.Thumbpanel.ThumblainsList.setSelectedIndex(JLelement.getSelectedIndex());
                    Pagenum = ((JLelement.getSelectedIndex() + 1));
                }
                if (e.getClickCount() == 2) {

                    JList JLelement = (JList) e.getSource();
                    djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
                    Pagenum = ((JLelement.getSelectedIndex() + 1));
                    Switch_FullBookView(false);
                }
            }
        });
        ThumblainsList.setCellRenderer(new com.lizardtech.djview.ImageListCellRenderer());

        ThumblainsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ThumblainsList.setLayoutOrientation(JList.VERTICAL);
        ThumblainsList.setFixedCellWidth(Width);
        ThumblainsList.setFixedCellHeight(Height + 10);
        ThumblainsList.setFocusable(false);

        // put our JList in a JScrollPane
        ThumblainsScrollPane.setPreferredSize(beanPanel.getPreferredSize());
        ThumblainsScrollPane.setWheelScrollingEnabled(true);
        topPanel.add(ThumblainsScrollPane, BorderLayout.CENTER);
//        Rotatedpane.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//
//                    Switch_FullBookView(false);
//
//                }
//            }
//        });

    }

    public void Switch_FullBookView(Boolean Is_Continous) {
        if (Is_Continous) {
            if (Continous) {
                return;
            }
            cardLayout.show(beanPanel, "FullBook");
            Continous = true;
            Is_Rotated = false;
        } else {

            cardLayout.show(beanPanel, "Bean");
            Is_Rotated = false;
            Continous = false;

        }

    }

    public void rotate(final int degree) {
//        RotatedpaneImage = rotateImage(img[djvubean.getPage() - 1], degree);
//        img[djvubean.getPage() - 1] = RotatedpaneImage;
//
//        RotatedpaneLabel.setIcon(new ImageIcon(RotatedpaneImage));

//        Rotatedpane.add(RotatedpaneLabel, BorderLayout.CENTER);
        cardLayout.show(beanPanel, "Rotate");
        Is_Rotated = true;
        Continous = false;

    }

    private Boolean switchFlag(Boolean X) {
        if (X == false) {
            return true;
        } else {
            return false;
        }
    }

    public static BufferedImage rotateImage(BufferedImage src, double degrees) {
        //     if (degrees==0) return src;
        double radians = Math.toRadians(degrees);

        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();

        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
        int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

        BufferedImage result = new BufferedImage(newWidth, newHeight,
                src.getType());
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
        g.rotate(radians, srcWidth / 2, srcHeight / 2);
        g.drawRenderedImage(src, null);

        return result;
    }

    public static void setPagesCount(int pagesCount) {
        PagesCount = pagesCount;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

}
