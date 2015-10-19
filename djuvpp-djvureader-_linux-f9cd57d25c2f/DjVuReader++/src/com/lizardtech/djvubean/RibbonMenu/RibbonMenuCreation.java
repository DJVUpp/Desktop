package com.lizardtech.djvubean.RibbonMenu;

import Printer.PrinterBook;
import com.lizardtech.djview.frame.DjvuStart;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.PageSelect.GoToArea;
import static com.lizardtech.djview.frame.PageSelect.pageSelectBox;
import com.lizardtech.djview.frame.StartPage;
import com.lizardtech.djview.frame.StatusBar;
import com.lizardtech.djvu.DjVuOptions;
import com.lizardtech.djvubean.DjVuBean;
import static com.lizardtech.djvubean.RibbonMenu.DjvuComponents.*;
import static com.lizardtech.djvubean.toolbar.Finder.*;
import helpMenu.View_Help;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButtonPanel;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.JFlowRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import org.pushingpixels.flamingo.api.ribbon.RibbonContextualTaskGroup;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;
import org.pushingpixels.flamingo.internal.ui.ribbon.appmenu.JRibbonApplicationMenuButton;
import org.pushingpixels.flamingo.internal.ui.ribbon.appmenu.JRibbonApplicationMenuPopupPanel;

/**
 * this the main class for Ribbon menu and it extends from Jribbonframe ,this
 * frame for ribbon menus only and in this ribbon can add the bands and tasks
 */
public class RibbonMenuCreation extends JRibbonFrame implements RibbonGetIcon {
    /*to call JCommandButtons from DjvuRibbonComponents class */

    private static final DjvuRibbonComponents Button = new DjvuRibbonComponents();
    /*to call Bands from RibbonBands Class*/
    private static final DjvuComponents Band = new DjvuComponents();
    /*to call Action Methods in Ribbon Actions*/
    private static final RibbonActions Action = new RibbonActions();
    private RibbonContextualTaskGroup high;
    private RibbonContextualTaskGroup under;
    private RibbonContextualTaskGroup strike;
    private RibbonContextualTaskGroup squiggly;
    private RibbonContextualTaskGroup insert;
    private RibbonContextualTaskGroup replace;
    public RibbonApplicationMenu Appmenu;
    public RibbonApplicationMenuEntryPrimary Create;
    public RibbonApplicationMenuEntryPrimary AboutDjvu;
    public RibbonApplicationMenuEntryPrimary OPen;
    public RibbonApplicationMenuEntryPrimary Save;
    public RibbonApplicationMenuEntryPrimary SaveAs;
    public static RibbonApplicationMenuEntryPrimary print;
    public RibbonApplicationMenuEntryPrimary Close;
    public RibbonApplicationMenuEntrySecondary Description;
    public RibbonApplicationMenuEntrySecondary AppBlank;
    public RibbonApplicationMenuEntrySecondary F_clip;
    public RibbonApplicationMenuEntrySecondary new_Open;
    public RibbonApplicationMenuEntrySecondary Re_file;
    public RibbonApplicationMenuEntrySecondary AppPdf;
    public RibbonApplicationMenuEntrySecondary djvu;
    public RibbonApplicationMenuEntrySecondary Word;
    public JRibbonApplicationMenuPopupPanel Panel;
    public RibbonApplicationMenuEntryFooter Exit;
    public RibbonApplicationMenuEntrySecondary djvuu;
    public RibbonApplicationMenuEntryFooter options;
    public JCommandButton FClip;
    public JCommandButton FBlank;
    public JCommandButton help;
    public JCommandButton AboutUS;
    public JCommandButton Contact;
    /**
     * Components for TaskBar
     */
    public JCommandButton Switch;
    public JCommandButton open;
    public JCommandButton save;
    public JCommandButton Email;
    public JCommandButton undo;
    public JCommandButton Redo;
    public JCommandButton search;
    public static JCommandButton TBprint;
    public JCommandButton TBBlank;
    public JCommandButton search2;
    public static Component AreaSearch;
    public static JCommandButton Strikeout;
    public static JCommandButton UnderLine;
    public static JCommandButton HighLight;
    public static JCommandButton Strikeout2;
    public static JCommandButton Strikeout3;
    public static JCommandButton UnderLine2;
    public static JCommandButton Findnext;
    public static JCommandButton FindPrev;
    boolean flage = true;
    /* The Main method that Load the Ribbon Menu */

    public void SetRibbonMenu(final DjvuStart djvu) {

    	//http://djvuplus.weebly.com/contact.html
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    RibbonTask Thome = new RibbonTask("Home", Band.getHomeToolsBand(djvu), Band.getHomeViewBand(), Band.getHomeCommentBand(), Band.getHomeConvertBand());
                    djvu.getRibbon().addTask(Thome);
                    RibbonTask taskConvert = new RibbonTask("CONVERT", Band.getToolConvertBand(), Band.getCreateBand(), Band.getOCRBand(), Band.getConvetrBand());
                    djvu.getRibbon().addTask(taskConvert);
                    RibbonTask taskEdit = new RibbonTask("EDIT", Band.getToolForEditBand()/*, Band.getEditContent()*/, Band.getFontBand(), Band.getParagraphBand());
                    djvu.getRibbon().addTask(taskEdit);
                    RibbonTask taskcomment = new RibbonTask("COMMENT", Band.getToolForCommentBand(), getTextmarkupBand(djvu), Band.getTypewriterBand(), Band.getDrawingBand());
                    djvu.getRibbon().addTask(taskcomment);
                    RibbonTask taskView = new RibbonTask("VIEW", Band.getToolForViewBand(), Band.getGotoBand(), Band.getPageDisplay(), getViewSetting(djvu));
                    djvu.getRibbon().addTask(taskView);
                 //   RibbonTask taskHelp = new RibbonTask("Help", Band.getUiOptionsBand(djvu));
                    // djvu.getRibbon().addTask(taskHelp);
                    RibbonTask highlight = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    RibbonTask underline = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    RibbonTask strikeout = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    RibbonTask Squiggly = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    RibbonTask Replace = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    RibbonTask Insert = new RibbonTask("Comment "
                            + "Formating", Band.getFontBand(), Band.getParagraphBand());
                    high = new RibbonContextualTaskGroup("Highlight Tools", Color.yellow, highlight);
                    under = new RibbonContextualTaskGroup("Underline Tools", Color.yellow, underline);
                    strike = new RibbonContextualTaskGroup("StrikeOut Tools", Color.yellow, strikeout);
                    squiggly = new RibbonContextualTaskGroup("SQIGGLY Tools", Color.yellow, Squiggly);
                    replace = new RibbonContextualTaskGroup("Replace Tools", Color.yellow, Replace);
                    insert = new RibbonContextualTaskGroup("Insert Tools", Color.yellow, Insert);
                    djvu.getRibbon().addContextualTaskGroup(high);
                    djvu.getRibbon().addContextualTaskGroup(under);
                    djvu.getRibbon().addContextualTaskGroup(strike);
                    djvu.getRibbon().addContextualTaskGroup(insert);
                    djvu.getRibbon().addContextualTaskGroup(squiggly);
                    djvu.getRibbon().addContextualTaskGroup(replace);

                    // add TaskBarcommponents into Ribbon Frame;   
                    ConfigureTaskBarComponents(djvu);
                    createAppMenuButton(djvu);
                    /* add help button**/

                    djvu.getRibbon().configureHelp(getResizableIconFromResource("/images/arrowUp&Down.png"), new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (flage) {
                                djvu.getRibbon().setMinimized(true);
                                djvu.getRibbon().setToolTipText(",xkxkxk");
                                flage = false;
                            } else {
                                djvu.getRibbon().setMinimized(false);
                                flage = true;
                            }

                        }
                    });
                } catch (Exception exp) {
                    exp.printStackTrace(DjVuOptions.err);
                    System.gc();
                }
            }
        });
    }

//this method to get icon from resource and set it into commponents
    public ResizableIcon getResizableIconFromResource(String resource) {
        ResizableIcon icon = null;
        try {
            icon = ImageWrapperResizableIcon
                    .getIcon(this.getClass().getClassLoader().getResourceAsStream(resource.substring(1)), new Dimension(16, 16));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return icon;
    }
    /*ConfigureTaskbar Components*/

    private void ConfigureTaskBarComponents(final DjvuStart djvu) {

        open = new JCommandButton(null, getResizableIconFromResource("/images/Open48.png"));
        open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        try {
                            djvu.opendialog();
                        } catch (IOException ex) {
                            Logger.getLogger(RibbonMenuCreation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        save = new JCommandButton(null, getResizableIconFromResource(new ImageIcon("/images/save48.png").toString()));
        save.setEnabled(false);
        undo = new JCommandButton(null, getResizableIconFromResource("/images/undo48.png"));
        undo.setEnabled(false);
        Redo = new JCommandButton(null, getResizableIconFromResource("/images/redo48.png"));
        Redo.setEnabled(false);
        TBprint = new JCommandButton(null, getResizableIconFromResource("/images/print48.png"));
        TBprint.setEnabled(false);
        TBprint.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    PrinterBook.PrintBook();
                } catch (PrinterException e1) {

                    e1.printStackTrace();
                }
            }
        });
        TBBlank = new JCommandButton(null, getResizableIconFromResource("/images/Blank48.png"));
        // TBBlank.setEnabled(false);
        TBBlank.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new CreatDjVu.NewDjvu().createFrame();
            }
        });
        search = new JCommandButton(null, getResizableIconFromResource("/images/Find48.png"));
        search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (djvubean != null) {
                    Findnext.setEnabled(true);
                    FindPrev.setEnabled(true);
                    AreaSearch.setEnabled(true);
                } else {
                    Findnext.setEnabled(false);
                    FindPrev.setEnabled(false);
                    AreaSearch.setEnabled(false);
                }
            }
        });
        search2 = new JCommandButton(null, getResizableIconFromResource("/images/search.png"));
        search2.setEnabled(false);
        search2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                search2.setIcon(getResizableIconFromResource("/images/search.png"));
                ((JTextField) AreaSearch).setText("");
                search2.setEnabled(false);

            }
        });

        Findnext = new JCommandButton(null, getResizableIconFromResource("/images/nexts.png"));
        Findnext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                djvubean.setMode(DjVuBean.PAN_MODE);
                Band.updateMode(djvubean.getMode());
                searchDocument(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());

            }
        });
        Findnext.setEnabled(false);
        FindPrev = new JCommandButton(null, getResizableIconFromResource("/images/prev.png"));
        FindPrev.setEnabled(false);
        FindPrev.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                djvubean.setMode(DjVuBean.PAN_MODE);
                Band.updateMode(djvubean.getMode());
                searchDocument(false);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());

            }
        });
        AreaSearch = new JTextField(8);
        AreaSearch.setEnabled(false);
        ((JTextField) AreaSearch).addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {

                        djvubean.setMode(DjVuBean.TEXT_MODE);
                        djvubean.setMode(DjVuBean.PAN_MODE);
                        Band.updateMode(djvubean.getMode());
                        searchDocument(true);
                        pageSelectBox.setSelectedItem(djvubean.getPage());
                        GoToArea.setText("" + djvubean.getPage());
                    }
                });
            }
        });

        ((JTextField) AreaSearch).getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                search2.setIcon(getResizableIconFromResource("/images/Delete.png"));
                search2.setEnabled(e.getDocument().getLength() > 0);
                Findnext.setEnabled(e.getDocument().getLength() > 0);
                FindPrev.setEnabled(e.getDocument().getLength() > 0);
            }

            public void removeUpdate(DocumentEvent e) {
                search2.setEnabled(e.getDocument().getLength() > 0);
                Findnext.setEnabled(e.getDocument().getLength() > 0);
                FindPrev.setEnabled(e.getDocument().getLength() > 0);

                if (((JTextField) AreaSearch).getText().equals("")) {
                    search2.setIcon(getResizableIconFromResource("/images/Search.png"));
                }

            }

            public void changedUpdate(DocumentEvent e) {
                search2.setIcon(getResizableIconFromResource("/images/Delete.png"));
                search2.setEnabled(e.getDocument().getLength() > 0);
                Findnext.setEnabled(e.getDocument().getLength() > 0);
                FindPrev.setEnabled(e.getDocument().getLength() > 0);

            }
        });
        djvu.getRibbon().addTaskbarComponent(Redo);
        djvu.getRibbon().addTaskbarComponent(undo);
        djvu.getRibbon().addTaskbarComponent(TBprint);
        djvu.getRibbon().addTaskbarComponent(TBBlank);
        djvu.getRibbon().addTaskbarComponent(save);
        djvu.getRibbon().addTaskbarComponent(open);
        //  ribbon.getRibbon().addTaskbarComponent(new JLabel("                                                                                                                     "));
        djvu.getRibbon().addTaskbarComponent(search);
        djvu.getRibbon().addTaskbarComponent(AreaSearch);
        djvu.getRibbon().addTaskbarComponent(search2);
        djvu.getRibbon().addTaskbarComponent(FindPrev);
        djvu.getRibbon().addTaskbarComponent(Findnext);

    }

    private void createAppMenuButton(final DjvuStart djvu) {
        print = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/print48.png"), "Print", Action.PrintAction(), JCommandButton.CommandButtonKind.ACTION_ONLY);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                try {
                    Appmenu = new RibbonApplicationMenu();
                    Exit = new RibbonApplicationMenuEntryFooter(getResizableIconFromResource("/images/Exit32.png"),
                            "Close Djvu", new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    djvu.setVisible(false);
                                }
                            });
                    Exit.setActionKeyTip("c");

                    Create = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/Blank48.png"),
                            "Create", null, JCommandButton.CommandButtonKind.POPUP_ONLY);
                    AppBlank = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/Blank48.png"),
                            "Blank", new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    new CreatDjVu.NewDjvu().createFrame();
                                }
                            }, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    AppBlank.setDescriptionText("Create a Blank Djvu");
                    F_clip = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/clipboard48.png"), "From Clipboard",new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new CreatDjVu.NewDjvuFromClib().createFrame();

                        }
                    }, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    F_clip.setDescriptionText("Create a Djvu from the content in Clipboard");

                    OPen = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/Open48.png"), "Open", null, JCommandButton.CommandButtonKind.POPUP_ONLY);
                    Re_file = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/RecentFile 48.png"), "Recent Files", null, JCommandButton.CommandButtonKind.POPUP_ONLY);
                    Re_file.setDescriptionText("Open a Djvu From The Recent Files ");
                    Re_file.setPopupCallback(new PopupPanelCallback() {
                        public JPopupPanel getPopupPanel(JCommandButton jcb) {
                            JPopupPanel RecentPane = new JPopupPanel() {
                            };
                            RecentPane.add(new StartPage().getrecentfile());
                            return RecentPane;
                        }

                    });

                    new_Open = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/Open48.png"), "From Computer", new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            try {
                                djvu.opendialog();
                            } catch (IOException ex) {
                                Logger.getLogger(RibbonMenuCreation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    new_Open.setDescriptionText("Open New Djvu");

                    Save = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/save48.png"), "Save", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    SaveAs = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/Save as 48.png"), "Save As", null, JCommandButton.CommandButtonKind.POPUP_ONLY);
                    djvuu = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/Djvu 48.png"), "AS Djvu", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    djvuu.setDescriptionText("Save The File as Djvu FILE");
                    AppPdf = new RibbonApplicationMenuEntrySecondary(getResizableIconFromResource("/images/PDF 48.png"), "As PDF", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    AppPdf.setDescriptionText("Save The File as PDF FILE");

                    AboutDjvu = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/AboutDjvu48.png"), "About Djvu", new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            // ��� �� ���� 
                            String url = "http://djvuplus.weebly.com/about-us.html";
                            String os = System.getProperty("os.name").toLowerCase();
                            Runtime rt = Runtime.getRuntime();

                            try {

                                if (os.indexOf("win") >= 0) {

                                    // this doesn't support showing urls in the form of "page.html#nameLink" 
                                    rt.exec("rundll32 url.dll,FileProtocolHandler " + url);

                                } else if (os.indexOf("mac") >= 0) {

                                    rt.exec("open " + url);

                                } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

							        // Do a best guess on unix until we get a platform independent way
                                    // Build a list of browsers to try, in this order.
                                    String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                                        "netscape", "opera", "links", "lynx", "chrome"};

                                    // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                                    StringBuffer cmd = new StringBuffer();
                                    for (int i = 0; i < browsers.length; i++) {
                                        cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
                                    }

                                    rt.exec(new String[]{"sh", "-c", cmd.toString()});

                                } else {
                                    return;
                                }
                            } catch (Exception e) {
                                return;
                            }
                            return;

                        }
                    }, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    Panel = new JRibbonApplicationMenuPopupPanel(new JRibbonApplicationMenuButton(djvu.getRibbon()), Button.Appmenu);
                    options = new RibbonApplicationMenuEntryFooter(getResizableIconFromResource("/images/Tools48.png"), " Djvu Options", null);
                    Close = new RibbonApplicationMenuEntryPrimary(getResizableIconFromResource("/images/Exit32.png"), " Exit", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
                    Close.setEnabled(false);
                    Create.addSecondaryMenuGroup("Create", AppBlank, F_clip);
                    Appmenu.addMenuEntry(Create);
                    OPen.addSecondaryMenuGroup("Open", Re_file, new_Open);
                    Appmenu.addMenuSeparator();
                    Appmenu.addMenuEntry(OPen);
                    Appmenu.addMenuSeparator();
                    Appmenu.addMenuEntry(Save);
                    SaveAs.addSecondaryMenuGroup("Save AS", djvuu, AppPdf);
                    Appmenu.addMenuEntry(SaveAs);
                    Appmenu.addMenuSeparator();
                    Appmenu.addMenuEntry(print);
                    Appmenu.addMenuSeparator();
                    Appmenu.addMenuEntry(AboutDjvu);
                    Appmenu.addMenuEntry(Close);
                    Appmenu.addFooterEntry(Exit);
                    Appmenu.addFooterEntry(options);
                    Appmenu.setDefaultCallback(new RibbonApplicationMenuEntryPrimary.PrimaryRolloverCallback() {
                        public void menuEntryActivated(JPanel pnl) {
                            JCommandButtonPanel Com = new JCommandButtonPanel(CommandButtonDisplayState.MEDIUM);
                            String name = "Djvu++";
                            Com.addButtonGroup(name);
                            AboutUS = new JCommandButton("About Us", getResizableIconFromResource("/images/AboutUs48.png"));
                            help = new JCommandButton("DJVU Help", getResizableIconFromResource("/images/DjvuHelp48.png"));
                            help.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    boolean flag = true;

                                    View_Help H = new View_Help();
                                    if (flag) {
                                        H.setVisible(true);
                                        flag = false;
                                    } else {
                                        H.setVisible(false);
                                        flag = true;
                                    }

                                }
                            });

                            Contact = new JCommandButton("Contact Us", getResizableIconFromResource("/images/ContactUs48.png"));
                            Com.addButtonToLastGroup(help);
                            help.setHorizontalAlignment(SwingUtilities.LEFT);
                            Com.addButtonToLastGroup(Contact);
                            Contact.setHorizontalAlignment(SwingUtilities.LEFT);
                            Com.addButtonToLastGroup(AboutUS);
                            AboutUS.setHorizontalAlignment(SwingUtilities.LEFT);
                            Com.setMaxButtonColumns(1);

                            pnl.setLayout(new BorderLayout());
                            pnl.add(Com, FlowLayout.LEFT);

                        }
                    });

                } catch (Exception exp) {
                    exp.printStackTrace(DjVuOptions.err);
                    System.gc();
                }
                djvu.getRibbon().setApplicationMenu(Appmenu);
            }

        });
    }

    public JFlowRibbonBand getViewSetting(final DjvuStart djvu) {
        JFlowRibbonBand ViewSetting = new JFlowRibbonBand("View Setting", null);
        final JCheckBox AppmenuVis = new JCheckBox("Show App Menu Button");
        AppmenuVis.setSelected(true);
        AppmenuVis.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!AppmenuVis.isSelected()) {
                    djvu.getRibbon().setApplicationMenu(null);
                } else {
                    createAppMenuButton(djvu);
                }
            }
        });

        final JCheckBox TaskBarVis = new JCheckBox("Show TaskBar");
        TaskBarVis.setSelected(true);
        TaskBarVis.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!TaskBarVis.isSelected()) {
                    djvu.getRibbon().removeAllTaskbarComponents();
                    djvu.getRibbon().setApplicationMenu(null);
                    djvu.setTitle(null);
                    djvu.updateTitle();
                } else {
                    ConfigureTaskBarComponents(djvu);
                    createAppMenuButton(djvu);
                    //djvu.updateTitle();
                }
            }
        });
        JPanel jpCheckBoxes = new JPanel();
        jpCheckBoxes.setLayout(new GridLayout(2, 1));
        jpCheckBoxes.add(AppmenuVis);
        jpCheckBoxes.add(TaskBarVis);

        final JRadioButton ShowStatus = new JRadioButton("Show Status Bar");
        ShowStatus.setSelected(true);
        ShowStatus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (ShowStatus.isSelected()) {
                    djvu.add(new StatusBar().getStatusBar(), BorderLayout.SOUTH);
                }
            }
        });

        final JRadioButton HideStatus = new JRadioButton("Auto-Hide Status Bar");
        HideStatus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (HideStatus.isSelected()) {
                    djvu.add(new JPanel(), BorderLayout.SOUTH);
                }
            }
        });

        JRadioButton AutoHide = new JRadioButton("Hide Status Bar");

        JPanel StatusRadioButtons = new JPanel();
        StatusRadioButtons.setLayout(new GridLayout(3, 1));
        StatusRadioButtons.add(ShowStatus);
        StatusRadioButtons.add(AutoHide);
        StatusRadioButtons.add(HideStatus);

        // Create a radio-button group to group three buttons
        ButtonGroup group = new ButtonGroup();
        group.add(ShowStatus);
        group.add(AutoHide);
        group.add(HideStatus);
        JRibbonComponent panel2 = new JRibbonComponent(StatusRadioButtons);
        ViewSetting.addFlowComponent(panel2);
        JRibbonComponent panel = new JRibbonComponent(jpCheckBoxes);
        ViewSetting.addFlowComponent(panel);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.FlowThreeRows(ViewSetting.getControlPanel()));
        ViewSetting.setResizePolicies(resizePolicies);
        return ViewSetting;
    }

    public JFlowRibbonBand getTextmarkupBand(final DjvuStart djvu) {
        JFlowRibbonBand TextMarkup = new JFlowRibbonBand("Text Markup", null);
        HighLight = new JCommandButton("", getResizableIconFromResource("/images/highlight48.png"));
        HighLight.setDisplayState(CommandButtonDisplayState.SMALL);
        HighLight.setEnabled(false);
        HighLight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvu.getRibbon().setVisible(under, false);
                djvu.getRibbon().setVisible(strike, false);
                djvu.getRibbon().setVisible(insert, false);
                djvu.getRibbon().setVisible(high, true);
                djvu.getRibbon().setVisible(replace, false);
                djvu.getRibbon().setVisible(squiggly, true);

            }
        });

        TextMarkup.addFlowComponent(HighLight);
        UnderLine2 = new JCommandButton(null, getResizableIconFromResource("/images/underline248.png"));
        UnderLine2.setDisplayState(CommandButtonDisplayState.SMALL);
        UnderLine2.setEnabled(false);
        UnderLine2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                djvu.getRibbon().setVisible(under, false);
                djvu.getRibbon().setVisible(strike, false);
                djvu.getRibbon().setVisible(insert, false);
                djvu.getRibbon().setVisible(high, false);
                djvu.getRibbon().setVisible(replace, false);
                djvu.getRibbon().setVisible(squiggly, true);

            }
        });
        TextMarkup.addFlowComponent(UnderLine2);
        UnderLine = new JCommandButton("", getResizableIconFromResource("/images/underLine48.png"));
        UnderLine.setDisplayState(CommandButtonDisplayState.SMALL);
        UnderLine.setEnabled(false);
        UnderLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvu.getRibbon().setVisible(under, true);
                djvu.getRibbon().setVisible(strike, false);
                djvu.getRibbon().setVisible(insert, false);
                djvu.getRibbon().setVisible(high, false);
                djvu.getRibbon().setVisible(replace, false);
                djvu.getRibbon().setVisible(squiggly, false);

            }
        });

        TextMarkup.addFlowComponent(UnderLine);
        Strikeout = new JCommandButton("", getResizableIconFromResource("/images/strike out48.png"));
        Strikeout.setDisplayState(CommandButtonDisplayState.SMALL);
        Strikeout.setEnabled(false);
        Strikeout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                djvu.getRibbon().setVisible(under, false);
                djvu.getRibbon().setVisible(strike, true);
                djvu.getRibbon().setVisible(insert, false);
                djvu.getRibbon().setVisible(high, false);
                djvu.getRibbon().setVisible(replace, false);
                djvu.getRibbon().setVisible(squiggly, false);
            }
        });
        TextMarkup.addFlowComponent(Strikeout);
        Strikeout2 = new JCommandButton("", getResizableIconFromResource("/images/strike out248.png"));
        Strikeout2.setDisplayState(CommandButtonDisplayState.SMALL);
        Strikeout2.setEnabled(false);
        Strikeout2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                djvu.getRibbon().setVisible(under, false);
                djvu.getRibbon().setVisible(strike, false);
                djvu.getRibbon().setVisible(insert, false);
                djvu.getRibbon().setVisible(high, false);
                djvu.getRibbon().setVisible(replace, true);
                djvu.getRibbon().setVisible(squiggly, false);

            }
        });
        TextMarkup.addFlowComponent(Strikeout2);
        Strikeout3 = new JCommandButton("", getResizableIconFromResource("/images/strikeout348.png"));
        Strikeout3.setDisplayState(CommandButtonDisplayState.SMALL);
        Strikeout3.setEnabled(false);
        Strikeout3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvu.getRibbon().setVisible(under, false);
                djvu.getRibbon().setVisible(strike, false);
                djvu.getRibbon().setVisible(insert, true);
                djvu.getRibbon().setVisible(high, false);
                djvu.getRibbon().setVisible(replace, false);
                djvu.getRibbon().setVisible(squiggly, false);

            }
        });
        TextMarkup.addFlowComponent(Strikeout3);

        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.FlowTwoRows(TextMarkup.getControlPanel()));
        TextMarkup.setResizePolicies(resizePolicies);
        return TextMarkup;
    }

}
