package com.lizardtech.djview;

import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvu.PagesModel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.lizardtech.djvubean.DjVuBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ListSelectionModel;

// TODO: Documentation.
public class FullBookView
        extends JPanel {

    public JScrollPane ThumblainsScrollPane;
    public static int PagesCount;
    public static boolean Continous = false;
    public static boolean Is_Rotated = false;
    private PagesModel pages;
    private JList ThumblainsList;
//    private BufferedImage img[];
//    private ImageIcon BookImages[];
//    private JLabel PagesLabel[];
    // TODO: revisite the rotation.
//    private final JPanel Rotatedpane;
//    private final JScrollPane RotatedpaneScrollPane;
//    private BufferedImage RotatedpaneImage;
//    private final JLabel RotatedpaneLabel;
    private int Pagenum = 0;
    private final DjVuBean djvubean;
    private final Frame frame;
    private final JPanel TOP_PANEL;
    private final JPanel BEAN_PANEL;
    private final CardLayout CARD_LAYOUT;
    private final int PAGE_WIDHT;
    private final int PAGE_HEIGHT;

    public FullBookView(final DjVuBean djvubean, final Frame frame, JPanel beanPanel) {

        this.djvubean = djvubean;
        this.frame = frame;
        this.BEAN_PANEL = beanPanel;
        CARD_LAYOUT = (CardLayout) beanPanel.getLayout();
        PAGE_WIDHT = 720;
        PAGE_HEIGHT = 768;

//        Rotatedpane = new JPanel(new BorderLayout());
//        RotatedpaneScrollPane = new JScrollPane(Rotatedpane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        RotatedpaneLabel = new JLabel();
        TOP_PANEL = new JPanel(new BorderLayout());
        TOP_PANEL.setBackground(Color.gray);
//        Rotatedpane.setBackground(Color.gray);
        init();

//        img = new BufferedImage[PagesCount];
        beanPanel.add(TOP_PANEL, "FullBook");
//        beanPanel.add(RotatedpaneScrollPane, "Rotate");
        CARD_LAYOUT.first(beanPanel);
    }

    /**
     * initialize the variables and views.
     */
    public final void init() {

        ThumblainsList = new JList();
        ThumblainsScrollPane = new JScrollPane(ThumblainsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        BookImages = new ImageIcon[PagesCount];
        pages = new PagesModel(PagesCount, PAGE_WIDHT, PAGE_HEIGHT);
//        PagesLabel = new JLabel[PagesCount];

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
        ThumblainsList.setFixedCellWidth(PAGE_WIDHT);
        ThumblainsList.setFixedCellHeight(PAGE_HEIGHT + 10);
        ThumblainsList.setFocusable(false);

        // put our JList in a JScrollPane
        ThumblainsScrollPane.setPreferredSize(BEAN_PANEL.getPreferredSize());
        ThumblainsScrollPane.setWheelScrollingEnabled(true);
        TOP_PANEL.add(ThumblainsScrollPane, BorderLayout.CENTER);
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
            CARD_LAYOUT.show(BEAN_PANEL, "FullBook");
            Continous = true;
            Is_Rotated = false;
        } else {

            CARD_LAYOUT.show(BEAN_PANEL, "Bean");
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
        CARD_LAYOUT.show(BEAN_PANEL, "Rotate");
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
        return TOP_PANEL;
    }

}
