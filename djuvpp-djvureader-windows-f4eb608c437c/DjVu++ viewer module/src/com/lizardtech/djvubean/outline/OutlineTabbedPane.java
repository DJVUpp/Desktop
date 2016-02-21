package com.lizardtech.djvubean.outline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvubean.DjVuBean;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;

public class OutlineTabbedPane
        extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel topPanel;
    private JPanel PagePane;

    public JScrollPane ThumblainsScrollPane;
//    NOTE: you can use dataModel instead of an array, but the data is static so array is perfable. 
//    private JPanel ThumblainsPane[];
    private DefaultListModel<JPanel> thumbnailData;
    public JList ThumblainsList;
    private ImageIcon BookImages[];
    private JLabel PagesLabel[];
    private static int PagesCount;
    private final int thumbnailWidht = 125;
    private final int thumbnailHeight = 170;
    private final int thumbnailCellHeight = 220;
    private final int thumBufferSize;

    private JPanel CommentPane;
    private DjVuBean djvubean;
    //Tabs Icons
    private ImageIcon PageIcon = new ImageIcon(
            this.getClass().getResource("/images/bookmark24.png"));
    private ImageIcon ThumblainsIcon = new ImageIcon(
            this.getClass().getResource("/images/layers24.png"));
    private ImageIcon CommentsIcon = new ImageIcon(
            this.getClass().getResource("/images/comment.png"));
    private ImageIcon Braker;

    private Boolean pageFlag = false;
    private Boolean thumblainsTabFlag = true;

    public OutlineTabbedPane(final DjVuBean djvubean, final Frame frame) {
        // thumbnail buffer size is the size of what the screen can fit + 1
        this.thumBufferSize = 4;
        this.djvubean = djvubean;
        final int pack = 200;
        setBackground(Color.gray);
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(pack, getToolkit().getScreenSize().height));
        topPanel.setLayout(new BorderLayout());
        try {
            Braker = new ImageIcon(
                    CreateThumbnails.Resizeimage((BufferedImage) (ImageIO.read(this.getClass().getResource("/images/Braker.jpg"))), 124, 45));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Create the tab pages
        createPagesTab();
        createCommentTab();
        createThumblainsTab();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                for (int i = 0; i < tabbedPane.getTabCount(); i++) {

                    if (tabbedPane.getBoundsAt(i).contains(e.getPoint())) {
                        int selectedIndex = tabbedPane.getSelectedIndex();

                        switch (selectedIndex) {
                            case 0:
                                if (pageFlag) {
                                    createTabs();
                                    topPanel.setPreferredSize(new Dimension(pack, getToolkit().getScreenSize().height));
                                    pageFlag = switchFlag(pageFlag);
                                    thumblainsTabFlag = true;
                                    tabbedPane.setSelectedIndex(0);

                                    frame.repaint();
                                    frame.revalidate();

                                } else {
                                    topPanel.setPreferredSize(new Dimension(55, getToolkit().getScreenSize().height));
                                    topPanel.revalidate();
                                    pageFlag = switchFlag(pageFlag);

                                    frame.repaint();
                                    frame.revalidate();
                                    tabbedPane.setSelectedIndex(0);
                                }

                                break;
                            case 1:

                                if (thumblainsTabFlag) {
                                    topPanel.setPreferredSize(new Dimension(pack, getToolkit().getScreenSize().height));
                                    createTabs();
                                    thumblainsTabFlag = switchFlag(thumblainsTabFlag);
                                    pageFlag = true;
                                    tabbedPane.setSelectedIndex(1);

                                    frame.repaint();
                                    frame.revalidate();

                                } else {
                                    thumblainsTabFlag = switchFlag(thumblainsTabFlag);
                                    removeTabs();
                                    topPanel.setPreferredSize(new Dimension(55, getToolkit().getScreenSize().height));
                                    topPanel.revalidate();

                                    frame.repaint();
                                    frame.revalidate();
                                    tabbedPane.setSelectedIndex(1);
                                }

                                break;
                            case 2:
                                break;
                            default:

                        }
                        return;
                    }
                }

            }
        });
        createTabs();
        frame.add("West", GettopPanel());
        frame.repaint();
        frame.revalidate();

    }

    public final void createPagesTab() {
        PagePane = new JPanel();
        PagePane.setLayout(new BorderLayout());
        //djvubean.getOutline().setBackground(Color.WHITE);
        if (djvubean.getOutline() != null) {
            PagePane.add(djvubean.getOutline());
        }

    }

    public final void createThumblainsTab() {
        // construct the ThumblainsList as a JList
        ThumblainsList = new JList();
        ThumblainsScrollPane = new JScrollPane(ThumblainsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        BookImages = new ImageIcon[PagesCount];
        PagesLabel = new JLabel[PagesCount];
//        ThumblainsPane = new JPanel[PagesCount];
        thumbnailData = new DefaultListModel<>();
        thumbnailData.setSize(PagesCount);

        // tell the ThumblainsList to use the panel array for its data
//        ThumblainsList.setListData(ThumblainsPane);
        ThumblainsList.setModel(thumbnailData);
        ThumblainsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList JLelement = (JList) e.getSource();
                djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
            }
        });
        ThumblainsList.setCellRenderer(new ImageListCellRenderer());
        ThumblainsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ThumblainsList.setLayoutOrientation(JList.VERTICAL);
        ThumblainsList.setFixedCellHeight(thumbnailCellHeight);
        ThumblainsList.setFocusable(true);

        // put our JList in a JScrollPane
        if (djvubean.getOutline() != null) {
            ThumblainsScrollPane.setPreferredSize(djvubean.getOutline().getSize());
        }
        ThumblainsScrollPane.setWheelScrollingEnabled(true);

        // loads visible thumbnails
        new Thread(new Runnable() {
            int drawnImagesCount = 0;
            int oldIndex = -1;
            int index;

            @Override
            public void run() {
                while (drawnImagesCount < PagesCount) {
                    // if the user didn't scroll continue
                    index = (int) (ThumblainsScrollPane.getVerticalScrollBar().getValue() / thumbnailCellHeight) + 1;
//                    System.out.println("index: " + index);
                    if (index == oldIndex) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                        }
                    } else {

                        System.out.println("index: " + index);
                        // render the following thumbnails
                        for (int i = index - 1; i <= index + thumBufferSize - 1; i++) {
                            try {
                                drawThumnail(i);
//                                ThumblainsList.setListData(ThumblainsPane);
                            } catch (IOException | ArrayIndexOutOfBoundsException ex) {
                                if (ex instanceof ArrayIndexOutOfBoundsException) {
                                    //trying to draw non-existing pages thumbnails
                                    break;
                                }
                            }
                        }
                        oldIndex = index;
                    }
                }
                System.out.println("DONE!");
            }

            public void drawThumnail(int i) throws IOException {
                if (BookImages[i] == null) {
                    PagesLabel[i] = new JLabel("" + (i + 1));
                    PagesLabel[i].setHorizontalTextPosition(JLabel.CENTER);
                    PagesLabel[i].setVerticalTextPosition(JLabel.BOTTOM);

                    BufferedImage img;
                    img = CreateThumbnails.generateThumbnail(i, thumbnailWidht, thumbnailHeight);
                    BookImages[i] = new ImageIcon(img);
                    drawnImagesCount++;
                    // add the images to jlabels with text
                    PagesLabel[i].setIcon(combosed_image(BookImages[i], Braker));
                    // create the corresponding panels 
                    JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    tempPanel.add(PagesLabel[i]);
                    tempPanel.add(PagesLabel[i]);
                    thumbnailData.set(i, tempPanel);

                }
            }
        }).start();
        // NOTE: not verty user-friendly!!
//        new Thread(new Runnable() {
//
//            public void run() {
//                for (int i = 0; i < PagesCount; i++) {
//                    try {
//                        drawThumnail(i);
//                    } catch (IOException | ArrayIndexOutOfBoundsException ex) {
//                        if (ex instanceof ArrayIndexOutOfBoundsException) {
//                            //trying to draw non-existing pages thumbnails
//                            break;
//                        }
//                    }
//                }
//                System.out.println("DONE!!");
//            }
//
//            public void drawThumnail(int i) throws IOException {
//                if (BookImages[i] == null) {
//                    PagesLabel[i] = new JLabel("" + (i + 1));
//                    PagesLabel[i].setHorizontalTextPosition(JLabel.CENTER);
//                    PagesLabel[i].setVerticalTextPosition(JLabel.BOTTOM);
//
//                    BufferedImage img;
//                    img = CreateThumbnails.generateThumbnail(i, thumbnailWidht, thumbnailHeight);
//                    BookImages[i] = new ImageIcon(img);
//                    // add the images to jlabels with text
//                    PagesLabel[i].setIcon(combosed_image(BookImages[i], Braker));
//                    // create the corresponding panels 
//                    JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//                    tempPanel.add(PagesLabel[i]);
//                    tempPanel.add(PagesLabel[i]);
//                    thumbnailData.set(i, tempPanel);
//                }
//            }
//        }).start();
    }

    public final void createCommentTab() {

        CommentPane = new JPanel();
        CommentPane.setLayout(new GridLayout(3, 2));
        /*
         CommentPane.add( new JLabel( "Field 1:" ) );
         CommentPane.add( new TextArea() );
         CommentPane.add( new JLabel( "Field 2:" ) );
         CommentPane.add( new TextArea() );
         CommentPane.add( new JLabel( "Field 3:" ) );
         CommentPane.add( new TextArea() );
         */
    }

    // Main method to get things started
    public static void main(String args[]) {
        // Create an instance of the test application
	/*	mainFrame= new OutlineTabbedPane();
         mainFrame.setVisible( true );
         */
    }

    private void removeTabs() {

        tabbedPane.removeAll();
        tabbedPane.addTab(null, PageIcon, null);
        tabbedPane.addTab(null, ThumblainsIcon, null);
        tabbedPane.addTab(null, CommentsIcon, null);

    }

    private void createTabs() {
        tabbedPane.removeAll();
        djvubean.properties.setProperty("navpane", "Outline");
        tabbedPane.addTab(null, PageIcon, PagePane);
        tabbedPane.addTab(null, ThumblainsIcon, ThumblainsScrollPane);
        tabbedPane.addTab(null, CommentsIcon, CommentPane);
    }

    public JPanel GettopPanel() {
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        return topPanel;
    }

    private Boolean switchFlag(Boolean X) {
        if (X == false) {
            return true;
        } else {
            return false;
        }
    }

    public static void setPagesCount(int pagesCount) {
        PagesCount = pagesCount;
    }

    private ImageIcon combosed_image(ImageIcon img1, ImageIcon img2) {
        int pad = 0;
        final BufferedImage compositeImage = new BufferedImage(img1.getIconWidth(), img1.getIconHeight() + img2.getIconHeight() + pad, BufferedImage.TYPE_INT_ARGB);
        final Graphics graphics = compositeImage.createGraphics();

        // Iterate over the icons, painting each icon and adding some padding space between them
        graphics.drawImage(img1.getImage(), 0, 0, null);
        graphics.drawImage(img2.getImage(), 0, img1.getIconHeight() + pad, null);

        return new ImageIcon(compositeImage);
    }
}
