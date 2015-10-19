/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvubean.RibbonMenu;

import CreatDjVu.NewDjvu;
import CreatDjVu.NewDjvuFromClib;
import MainPage.ChoosePanel;
import static MainPage.OCROnImagePanel.frame;
import PDF2DjVu.DjVU2TIF;
import PDF2DjVu.PDF2DjVu;
import PDFIMG.IMAGEStoPDF;
import static PDFIMG.PDF2IMG.convertToIMG;
import SnippingTool.SnippingToolPlusPlus;
import com.lizardtech.djview.frame.DjvuStart;
import static com.lizardtech.djview.frame.DjvuStart.bookList;
import static com.lizardtech.djview.frame.DjvuStart.curropen;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.DjvuStart.url_name;
import com.lizardtech.djview.frame.PageSelect;
import static com.lizardtech.djview.frame.PageSelect.*;
import com.lizardtech.djview.frame.StatusBar;
import static com.lizardtech.djview.frame.StatusBar.*;

import com.lizardtech.djvu.DjVuOptions;
import com.lizardtech.djvu.Document;
import com.lizardtech.djvubean.DjVuBean;
import static com.lizardtech.djvubean.DjVuBean.nscale;
import static com.lizardtech.djvubean.RibbonMenu.RibbonMenuCreation.*;

import com.lizardtech.djvubean.outline.CreateThumbnails;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import jrico.jstickynotes.JStickyNotes;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.CommandToggleButtonGroup;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.JFlowRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

/**
 *
 * @author niessuh
 */
public class DjvuComponents implements RibbonGetIcon {

    /*to call JCommandButtons from DjvuRibbonComponents class */
    private static final DjvuRibbonComponents Button = new DjvuRibbonComponents();
    public static int documentSize;
    private final String ZoNum[] = {"25%", "50%", "75%", "100%", "125%", "150%", "175%", "300%", "200%", "400%", "600%", "800%", "1200%"};
    private String Zoomformat;
    private static int modeNum;
    private String ComboBoxString;
    public static DjVuBean djvubean;
    public static JEditorPane Pane = new JEditorPane();
    String openimg = null;
    String PDFFullPath = null;
    String[] imagesname;
    String imdjvu;
    String openimgs = "";
    String PDFFullsource = null;
    File imagess[];
    String[] imageNAME;
    String fullPDF = null;
    FileDialog djvuFileSelectDialog = null;
    String djvuSelectFullPath = null;
    String newPDFFullPath = null;
    FileDialog DjVu2pdf = null;

    /**
     * to Create the Cursor icon
     */
    static Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     * this method to Create The HomeToolsBand and return it to add in the
     * Ribbon
     *
     * @param Djvu
     * @return
     */
    public JRibbonBand getHomeToolsBand(final DjvuStart Djvu) {

        JRibbonBand Tools = new JRibbonBand("TOOLS", null);
        try {

            Button.Hand = new JCommandButton("Hand", getResizableIconFromResource("/images/hand.png"));
            //  Button.Hand.setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Button.Hand.getActionModel().setSelected(true);
            Button.Hand.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    djvubean.setMode(DjVuBean.PAN_MODE);
                    updateMode(djvubean.getMode());
                    djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    Button.Clib_Copy.setEnabled(false);
                    Button.Clib_Cut.setEnabled(false);
                    Button.Clib_Past.setEnabled(false);
                    Button.Clib_Selectall.setEnabled(false);

                }
            });

            RichTooltip HandT = new RichTooltip("Hand    Alt+3", "Click and drag to pan around the docoment ");
            Button.Hand.setActionRichTooltip(HandT);
            Tools.addCommandButton(Button.Hand, RibbonElementPriority.TOP);
            Button.Hand.setEnabled(false);
            Button.Select = new JCommandButton("Select"
                    + " Text", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
            RichTooltip SelectT = new RichTooltip("SelectText  Alt+6", "Select Text for copy or adding comments ");
            Button.Select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    djvubean.setMode(DjVuBean.TEXT_MODE);
                    updateMode(djvubean.getMode());
                    djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    if (djvubean.getTextArea().getText() != null) {
                        Button.Clib_Copy.setEnabled(true);
                        Button.Clib_Cut.setEnabled(true);
                        Button.Clib_Past.setEnabled(true);
                        Button.Clib_Selectall.setEnabled(true);
                    }

                }
            });
            Button.Select.setEnabled(false);
            Button.Select.setActionRichTooltip(SelectT);
            Tools.addCommandButton(Button.Select, RibbonElementPriority.TOP);
            Button.ZoomArea = new JCommandButton("Zoom"
                    + " Area", getResizableIconFromResource(new ImageIcon("/images/select-annotation48.png").toString()));
            Button.ZoomArea.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    djvubean.setMode(DjVuBean.ZOOM_MODE);
                    updateMode(djvubean.getMode());
                    Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                    djvubean.setCursor(c);
                }
            });
            Tools.addCommandButton(Button.ZoomArea, RibbonElementPriority.TOP);
            Button.ZoomArea.setEnabled(false);
            Button.SnapShot = new JCommandButton("SnapShot", getResizableIconFromResource("/images/snap-shot48.png"));
            Button.SnapShot.setEnabled(false);
            Button.SnapShot.addActionListener(new ActionListener() {

                @SuppressWarnings("empty-statement")
                public void actionPerformed(ActionEvent e) {
                    String pa = null;
                    try {
                        if (!djvubean.isDisplayable()) {
                            while (true);
                        }
                        final Document document = new Document();
                        document.read(djvubean.getURL());
                        System.err.println(djvubean.getURL());
                        BufferedImage img = CreateThumbnails.generateThumbnail(djvubean.getPage() - 1, djvubean.getWidth(), djvubean.getHeight());
                        Image imge = img;
                        FileDialog fd = new FileDialog(Djvu, "Save", FileDialog.SAVE);
                        fd.show();
                        pa = fd.getFile().contains(".png") ? fd.getFile() : fd.getFile() + ".png";
                        File savePath = new File("" + fd.getDirectory() + pa);
                        ImageIO.write((RenderedImage) imge, "png", savePath);
                    } catch (final Exception exp) {
                        if (pa == null) {
                            JOptionPane.showMessageDialog(null, "Must Choose your Save Director");
                        }
                        exp.printStackTrace(DjVuOptions.err);
                        System.gc();
                    }

                }
            });

            Tools.addCommandButton(Button.SnapShot, RibbonElementPriority.MEDIUM);
            Button.Clipboard = new JCommandButton("Clipboard", getResizableIconFromResource(new ImageIcon("/images/clipboard48.png").toString()));
            Button.Clipboard.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
            Button.Clib_Cut = new JCommandMenuButton("   Cut", getResizableIconFromResource(new ImageIcon("/images/cut.png").toString()));

            Button.Clib_Cut.setEnabled(false);
            Button.Clib_Copy = new JCommandMenuButton("   Copy", getResizableIconFromResource(new ImageIcon("/images/Copy.png").toString()));

            Button.Clib_Copy.setEnabled(false);
            Button.Clib_Copy.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String myString = djvubean.getText();
                    StringSelection stringSelection = new StringSelection(myString);
                    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clpbrd.setContents(stringSelection, null);

                }
            });
            Button.Clib_Past = new JCommandMenuButton("   Past", getResizableIconFromResource(new ImageIcon("/images/Paste.png").toString()));
            Button.Clib_Past.setEnabled(false);
            Button.Clib_Selectall = new JCommandMenuButton("SelectAll", getResizableIconFromResource(new ImageIcon("/images/select-annotation48.png").toString()));
            Button.Clib_Selectall.setEnabled(false);
            Button.Clipboard.setPopupCallback(new PopupPanelCallback() {
                public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                    JCommandPopupMenu Menu = new JCommandPopupMenu();
                    Menu.addMenuButton(Button.Clib_Cut);
                    Menu.addMenuButton(Button.Clib_Copy);
                    Menu.addMenuButton(Button.Clib_Past);
                    Menu.addMenuButton(Button.Clib_Selectall);
                    return Menu;
                }
            });
            Tools.addCommandButton(Button.Clipboard, RibbonElementPriority.MEDIUM);
            Button.BookMark = new JCommandButton("BookMark", getResizableIconFromResource(new ImageIcon("/images/BookMark.PNG").toString()));
            Button.BookMark.setEnabled(false);
            //  Tools.addCommandButton(Button.BookMark, RibbonElementPriority.MEDIUM);
            List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
            resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Tools.getControlPanel()));
            Tools.setResizePolicies(resizePolicies);
        } catch (Exception e1) {

            e1.printStackTrace();
        }
        return Tools;
    }

    /**
     * this method to Create The ViewBand and return it to add in the Ribbon
     *
     * @return
     */
    public JFlowRibbonBand getHomeViewBand() {
        JFlowRibbonBand View = new JFlowRibbonBand("View", null);
        Button.Asize = new JCommandButton("Actual"
                + " Size", getResizableIconFromResource(new ImageIcon("/images/actual size48.png").toString()));

        Button.Asize.setEnabled(false);
        Button.Asize.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.Asize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zoomformat = DjVuBean.ZOOM100;
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(100);
                modeNum = 100;
                UpdateZoom(modeNum);

            }
        });
        Button.FitWidth = new JCommandButton("Fit Width ", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FitWidth.setEnabled(false);
        Button.FitWidth.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.FitWidth.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                DjvuRibbonComponents.ZoomB.setSelectedItem("115%");
                jsldHort.setValue(115);
                modeNum = 115;
                UpdateZoom(modeNum);

            }
        });
        Button.Fitpage = new JCommandButton("Fit Page  ", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.Fitpage.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.Fitpage.setEnabled(false);
        Button.Fitpage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zoomformat = "53%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(53);
                modeNum = 53;
                UpdateZoom(modeNum);

            }
        });
        Button.FitVisable = new JCommandButton("Fit Visible  ", getResizableIconFromResource(new ImageIcon("/images/fit visible48.png").toString()));
        Button.FitVisable.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.FitVisable.setEnabled(false);
        Button.FitVisable.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        DjvuRibbonComponents.ZoomB = new JComboBox(ZoNum);
        DjvuRibbonComponents.ZoomB.setEditable(true);
        DjvuRibbonComponents.ZoomB.setSelectedItem("");

        DjvuRibbonComponents.ZoomB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String DjvuComboBoxString = (String) DjvuRibbonComponents.ZoomB.getSelectedItem();
                    int index = DjvuRibbonComponents.ZoomB.getSelectedIndex();
                    if (index == 0) {
                        mins.setEnabled(false);
                        Button.Zoomout.setEnabled(false);
                        Button.ZOUT.setEnabled(false);
                        Button.ZoomIn.setEnabled(true);
                        Plus.setEnabled(true);
                        Button.ZIN.setEnabled(true);

                    } else if (index == ZoNum.length - 1) {
                        Plus.setEnabled(false);
                        Button.ZoomIn.setEnabled(false);
                        Button.ZIN.setEnabled(false);
                        Button.ZOUT.setEnabled(true);
                        Button.Zoomout.setEnabled(true);
                        mins.setEnabled(true);

                    } else if (!DjvuComboBoxString.equals("")) {
                        Button.ZoomIn.setEnabled(true);
                        Button.Zoomout.setEnabled(true);
                        Plus.setEnabled(true);
                        mins.setEnabled(true);
                        Button.ZIN.setEnabled(true);
                        Button.ZOUT.setEnabled(true);

                    }

                    int dotIndex = 0;
                    for (int i = 0; i < DjvuComboBoxString.length(); i++) {
                        if (DjvuComboBoxString.charAt(i) == '.') {
                            dotIndex = i;
                        }
                    }
                    if (dotIndex == 0) {
                        ComboBoxString = DjvuComboBoxString;
                    } else {
                        ComboBoxString = DjvuComboBoxString.substring(0, dotIndex);
                    }

                    if (ComboBoxString.endsWith("%") && (int) Double.parseDouble(ComboBoxString.substring(0, ComboBoxString.length() - 1)) > 0) {

                        if ((int) Double.parseDouble(ComboBoxString.substring(0, ComboBoxString.length() - 1)) < 25) {

                            djvubean.setZoom(DjVuBean.ZOOM25);
                            String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                            jsldHort.setValue((int) Double.parseDouble(value));

                        } else {

                            djvubean.setZoom(ComboBoxString);
                            String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                            jsldHort.setValue((int) Double.parseDouble(value));
                            UpdateZoom((int) Double.parseDouble(value));
                        }
                    } else if (!ComboBoxString.endsWith("%") && (int) Double.parseDouble(ComboBoxString) > 0) {

                        if ((int) Double.parseDouble(ComboBoxString) < 25) {

                            djvubean.setZoom(DjVuBean.ZOOM25);
                            String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                            jsldHort.setValue((int) Double.parseDouble(value));

                        } else {

                            DjvuRibbonComponents.ZoomB.setSelectedItem(ComboBoxString + "%");
                            DjVuBean.ZOOMComBox = ComboBoxString + "%";
                            djvubean.setZoom(DjVuBean.ZOOMComBox);
                            String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                            jsldHort.setValue((int) Double.parseDouble(value));
                            UpdateZoom((int) Double.parseDouble(value));

                        }

                    } else if (!ComboBoxString.endsWith("%") && (int) Double.parseDouble(ComboBoxString) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input ,please try again!");
                        DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                        djvubean.setZoom(djvubean.getZoom());
                        String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                        UpdateZoom((int) Double.parseDouble(value));

                    } else if (ComboBoxString.endsWith("%") && (int) Double.parseDouble(ComboBoxString.substring(0, ComboBoxString.length() - 1)) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input ,please try again!");
                        DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                        djvubean.setZoom(djvubean.getZoom());
                        String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                        UpdateZoom((int) Double.parseDouble(value));

                    }

                } catch (Exception EXC) {

                    if (ComboBoxString.equals("")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input ,please try again!");
                        if (tabbedPane.getTabCount() != 0) {
                            String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                            if (!tabName.equals("GetStart")) {

                                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                                djvubean.setZoom(djvubean.getZoom());
                                String value = djvubean.getZoom().substring(0, djvubean.getZoom().length() - 1);
                                updateMode((int) Double.parseDouble(value));
                            }
                        }
                    }
                }
            }
        });
        View.addFlowComponent(Button.Asize);
        Button.ZoomIn = new JCommandButton(getResizableIconFromResource("/images/zoom in48.png"));
        Button.ZoomIn.setEnabled(false);
        Button.ZoomIn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Button.ZoomIn.setEnabled(false);
                } else {
                    Button.ZoomIn.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.ZoomIn.setBorder(null);
        Button.Zoomout = new JCommandButton(null, getResizableIconFromResource(new ImageIcon("/images/zoom out48.png").toString()));
        Button.Zoomout.setEnabled(false);
        Button.Zoomout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    Button.Zoomout.setEnabled(false);
                } else {
                    Button.Zoomout.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);

                jsldHort.setValue(nscale);
            }
        });

        Button.strip = new JCommandButtonStrip();
        Button.strip.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.strip.add(Button.Zoomout, 0);
        Button.strip.add(Button.ZoomIn, 1);
        View.addFlowComponent(Button.strip);

        DjvuRibbonComponents.ZoomB.setEnabled(false);
        //   Button.ZoomB.setBackground(Color.white);
        DjvuRibbonComponents.ZoomB.setBorder(null);

        Button.RC = new JRibbonComponent(DjvuRibbonComponents.ZoomB);
        Button.RC.setDisplayPriority(RibbonElementPriority.TOP);
        View.addFlowComponent(Button.RC);

        View.addFlowComponent(Button.Fitpage);

        Button.RotateLeft = new JCommandMenuButton("Rotate "
                + "Left", getResizableIconFromResource(new ImageIcon("/images/rotate left48.png").toString()));
        Button.RotateLeft.setEnabled(false);
        Button.RotateLeft.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.RotateLeft.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(-90);

            }
        });

        Button.RotateRight = new JCommandMenuButton("Rotate "
                + "Right", getResizableIconFromResource(new ImageIcon("/images/rotate right48.png").toString()));
        Button.RotateRight.setEnabled(false);

        Button.RotateRight.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.RotateRight.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //  djvubean.setRotationDegrees(90f);

                com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(90);

            }
        });
        View.addFlowComponent(Button.FitWidth);
        View.addFlowComponent(Button.FitVisable);
        View.addFlowComponent(Button.RotateLeft);
        View.addFlowComponent(Button.RotateRight);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.FlowThreeRows(View.getControlPanel()));
        View.setResizePolicies(resizePolicies);
        return View;
    }

    /**
     * this method to Create The EditBand and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getHomeEditBand() {
        JRibbonBand Edit = new JRibbonBand("Edit", null);
        Button.EditText = new JCommandButton("Edit"
                + " Text", getResizableIconFromResource(new ImageIcon("/images/JPG48.png").toString()));
        Button.EditText.setEnabled(false);
        Button.EditObject = new JCommandButton("Edit"
                + " Object", getResizableIconFromResource(new ImageIcon("/images/GIF48.png").toString()));
        Button.EditObject.setEnabled(false);
        Button.EditObject.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.EditObject.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JPopupPanel pan = new JPopupPanel() {
                };
                pan.setBackground(Color.WHITE);
                return pan;
            }
        });
        Edit.addCommandButton(Button.EditText, RibbonElementPriority.TOP);
        Edit.addCommandButton(Button.EditObject, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Edit.getControlPanel()));
        Edit.setResizePolicies(resizePolicies);
        return Edit;
    }

    /**
     * this method to Create The CommentBand and return it to add in the Ribbon
     *
     * @return
     */
    public static String Notestatus;

    public JRibbonBand getHomeCommentBand() {
        JRibbonBand Comment = new JRibbonBand("Comment", null);
//        Button.TyperWriter = new JCommandButton("TyperWriter", getResizableIconFromResource("/images/type writer48.png"));
//        Button.TyperWriter.setEnabled(false);
//        Comment.addCommandButton(Button.TyperWriter, RibbonElementPriority.TOP);
        // Button.createnote = new JCommandMenuButton("Create Note", getResizableIconFromResource(new ImageIcon("/images/Comment48.png").toString()));
        Button.Note = new JCommandButton("Create Note", getResizableIconFromResource(new ImageIcon("/images/notes48.png").toString()));

        Button.Note.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new JStickyNotes(djvubean);
                Notestatus = "Create";
            }
        });
        Button.shownote = new JCommandButton("Show Note", getResizableIconFromResource(new ImageIcon("/images/icon48.png").toString()));
        Button.shownote.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new JStickyNotes(djvubean);
                Notestatus = "Show";
            }
        });
//        Button.Note = new JCommandButton("Note", getResizableIconFromResource(new ImageIcon("/images/notes48.png").toString()));
//        Button.Note.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.Note.setEnabled(false);
//        Button.Note.setPopupCallback(new PopupPanelCallback() {
//            @Override
//            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
//                JCommandPopupMenu Menu = new JCommandPopupMenu();
//                Menu.addMenuButton(Button.createnote);
//                Menu.addMenuButton(Button.shownote);
//                return Menu;
//            }
//        });
//        Comment.addCommandButton(Button.Note, RibbonElementPriority.TOP);
//        Button.HighLight = new JCommandButton("HighLight", getResizableIconFromResource(new ImageIcon("/images/highlight48.png").toString()));
//        Button.HighLight.setEnabled(false);
//        Comment.addCommandButton(Button.HighLight, RibbonElementPriority.MEDIUM);
//        Button.Strikeout = new JCommandButton("Strikeout", getResizableIconFromResource(new ImageIcon("/images/strike out48.png").toString()));
//        Button.Strikeout.setEnabled(false);
//        Comment.addCommandButton(Button.Strikeout, RibbonElementPriority.MEDIUM);
//        Button.UnderLine = new JCommandButton("UnderLine", getResizableIconFromResource(new ImageIcon("/images/underLine48.png").toString()));
//        Button.UnderLine.setEnabled(false);
        Comment.addCommandButton(Button.Note, RibbonElementPriority.TOP);
        Comment.addCommandButton(Button.shownote, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Comment.getControlPanel()));
        Comment.setResizePolicies(resizePolicies);
        return Comment;

    }
    public static String Titel = null;
    public static String ArgsType = null;

    public JRibbonBand getHomeConvertBand() {
        JRibbonBand Convert = new JRibbonBand("OCR", null);
        Button.OCREnglish = new JCommandMenuButton("OCR"
                + " English", getResizableIconFromResource(new ImageIcon("/images/ocr english48.png").toString()));
        Button.OCREnglish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SnippingToolPlusPlus("en");
                ArgsType = "English";
                Titel = "OCR English";

            }
        });

        Button.OCRArabic = new JCommandMenuButton("OCR"
                + " Arabic", getResizableIconFromResource(new ImageIcon("/images/ocr arabic48.png").toString()));

        Button.OCRArabic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new SnippingToolPlusPlus("ara");
                Titel = "OCR Arabic";
                ArgsType = "Arabic";
            }
        });
        Button.franch = new JCommandMenuButton("OCR"
                + " Franch", getResizableIconFromResource(new ImageIcon("/images/ocr arabic48.png").toString()));

        Button.franch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new SnippingToolPlusPlus("fr");
                ArgsType = "Franch";
                Titel = "OCR Franch";

            }
        });

        Button.Ocr = new JCommandButton("OCR", getResizableIconFromResource(new ImageIcon("/images/ocr48.png").toString()));
        Button.Ocr.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        RichTooltip occr = new RichTooltip("Ocr", "Convert text With in image to Searchable and editable");
        Button.Ocr.setActionRichTooltip(occr);
        Button.Ocr.setPopupCallback(new PopupPanelCallback() {
            @Override
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.OCREnglish);

                Menu.addMenuButton(Button.OCRArabic);
                Menu.addMenuButton(Button.franch);

                return Menu;
            }
        });
        Button.ImagesToOcr2 = new JCommandMenuButton("OCR"
                + " ForImage", getResizableIconFromResource("/images/OCRforImage 48.png"));
        frame = new JFrame();
        Button.ImagesToOcr2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.add(new ChoosePanel());
                frame.setVisible(true);
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Choose Your Image Language");
                frame.setSize(340, 80);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));

            }
        });
              frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);
                    frame.dispose();
                } else {

                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

        });

        Convert.addCommandButton(Button.Ocr, RibbonElementPriority.TOP);
        Convert.addCommandButton(Button.ImagesToOcr2, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Convert.getControlPanel()));
        Convert.setResizePolicies(resizePolicies);
        return Convert;

    }

    /**
     * this method to Create The organizationBand and return it to add in the
     * Ribbon
     *
     * @return
     */
    public JRibbonBand getHomeorgBand() {
        JRibbonBand pageOrganization = new JRibbonBand("page Organization", null);
        Button.RotatePages2 = new JCommandButton("Rotate"
                + " pages", getResizableIconFromResource(new ImageIcon("/images/Document32.png").toString()));
        Button.RotatePages2.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.RotatePages2.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();

                Button.RotateLeft = new JCommandMenuButton("Rotate "
                        + "Left", getResizableIconFromResource(new ImageIcon("/images/rotate left48.png").toString()));

                Button.RotateLeft.setDisplayState(CommandButtonDisplayState.MEDIUM);
                Button.RotateRight = new JCommandMenuButton("Rotate "
                        + "Right", getResizableIconFromResource(new ImageIcon("/images/rotate right48.png").toString()));

                Button.RotateRight.setDisplayState(CommandButtonDisplayState.MEDIUM);

                Menu.addMenuButton(Button.RotateLeft);
                Menu.addMenuButton(Button.RotateRight);
                return Menu;
            }
        });
        pageOrganization.addCommandButton(Button.RotatePages2, RibbonElementPriority.TOP);

        Button.insert = new JCommandButton("insert", null);
        Button.insert.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.insert.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JPopupPanel pan = new JPopupPanel() {
                };

                return pan;
            }
        });
        pageOrganization.addCommandButton(Button.insert, RibbonElementPriority.MEDIUM);
        Button.Delete = new JCommandButton("Delete", null);
        pageOrganization.addCommandButton(Button.Delete, RibbonElementPriority.MEDIUM);
        Button.ExtractallImages = new JCommandButton("Extract", null);
        pageOrganization.addCommandButton(Button.ExtractallImages, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(pageOrganization.getControlPanel()));
        pageOrganization.setResizePolicies(resizePolicies);
        return pageOrganization;
    }

    /**
     * this method to Create The OCRBand and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getOCRBand() {
        JRibbonBand OCRBand = new JRibbonBand("Ocr", null);
        Button.OCREnglish2 = new JCommandMenuButton("OCR"
                + " English", getResizableIconFromResource(new ImageIcon("/images/ocr english48.png").toString()));
        Button.OCREnglish2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SnippingToolPlusPlus("en");
                ArgsType = "English";
                Titel = "OCR English";
            }
        });

        Button.OCRArabic2 = new JCommandMenuButton("OCR"
                + " Arabic", getResizableIconFromResource("/images/ocr arabic48.png"));
        Button.OCRArabic2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new SnippingToolPlusPlus("ara");
                ArgsType = "Arabic";
                Titel = "OCR Arabic";

            }
        });
        Button.franch2 = new JCommandMenuButton("OCR"
                + " franch", getResizableIconFromResource("/images/ocr arabic48.png"));
        Button.franch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new SnippingToolPlusPlus("fr");
                ArgsType = "Franch";
                Titel = "OCR Franch";

            }
        });
        Button.Ocr2 = new JCommandButton("OCR", getResizableIconFromResource("/images/ocr48.png"));
        Button.Ocr2.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        RichTooltip tip = new RichTooltip("Ocr", "Convert text With in image to Searchable and editable");
        Button.Ocr2.setActionRichTooltip(tip);
        Button.Ocr2.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.OCREnglish2);

                Menu.addMenuButton(Button.OCRArabic2);
                Menu.addMenuButton(Button.franch2);

                return Menu;
            }
        });
        Button.ImagesToOcr = new JCommandMenuButton("OCR"
                + " ForImage", getResizableIconFromResource("/images/OCRforImage 48.png"));
        frame = new JFrame();
        Button.ImagesToOcr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.add(new ChoosePanel());
                frame.setVisible(true);
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);

                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Choose Your Image Language");
                frame.setSize(340, 80);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));

            }
        });
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);
                    frame.dispose();
                } else {

                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

        });

        OCRBand.addCommandButton(Button.Ocr2, RibbonElementPriority.TOP);
        OCRBand.addCommandButton(Button.ImagesToOcr, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.Mirror(OCRBand.getControlPanel()));
        OCRBand.setResizePolicies(resize);
        return OCRBand;
    }

    /**
     * this method to Create The Convert Band and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getConvetrBand() {
        JRibbonBand ConvertBand = new JRibbonBand("Convert", null);
        ConvertBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(ConvertBand));
        ConvertBand.startGroup();
        Button.Djvu_to_pdf = new JCommandMenuButton("TO PDF", getResizableIconFromResource("/images/to PDF 48.png"));
        Button.Djvu_to_pdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);

                try {
                    djvuFileSelectDialog = new FileDialog(new java.awt.Frame(), "Select DjVu", FileDialog.LOAD);
                    djvuFileSelectDialog.setVisible(true);
                    djvuSelectFullPath = djvuFileSelectDialog.getDirectory() + djvuFileSelectDialog.getFile();
                    if (djvuFileSelectDialog.getFile() != null) {
                        if (!djvuSelectFullPath.contains(" ")) {
                            String d = djvuSelectFullPath.substring(djvuSelectFullPath.lastIndexOf("."));
                            d = d.toLowerCase();
                            int s = 1;
                            if (d.equals(".djvu")) {
                                DjVu2pdf = new FileDialog(new java.awt.Frame(), "Save PDF", FileDialog.SAVE);
                                DjVu2pdf.setVisible(true);
                                newPDFFullPath = DjVu2pdf.getDirectory() + DjVu2pdf.getFile();

                                if (DjVu2pdf.getFile() != null) {
                                    if (!newPDFFullPath.contains(" ")) {
                                        DjVU2TIF.convertDjvu2PDF(djvuSelectFullPath, newPDFFullPath, s);

                                    } else {
                                        djvuSelectFullPath = null;
                                        JOptionPane.showMessageDialog(null, "Path contain space");
                                    }
                                } else {
                                    djvuSelectFullPath = null;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "File selected is not Djvu");
                                djvuSelectFullPath = null;
                            }
                        } else {
                            djvuSelectFullPath = null;
                            JOptionPane.showMessageDialog(null, "Path contain space !!");
                        }
                    }

                } catch (Exception ex) {
                } finally {
                    File write = new File("C:\\DjVu++Task\\tiffff.tif");
                    if (write.exists()) {
                        write.delete();
                    }
                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);

                }

            }
        });

        Button.Djvu_toImage = new JCommandMenuButton("TO Images", getResizableIconFromResource("/images/DJVU toImage 48.png"));
        Button.Djvu_toImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);

                try {
                    djvuFileSelectDialog = new FileDialog(new java.awt.Frame(), "Select DjVu", FileDialog.LOAD);
                    djvuFileSelectDialog.setVisible(true);
                    djvuSelectFullPath = djvuFileSelectDialog.getDirectory() + djvuFileSelectDialog.getFile();
                    if (djvuFileSelectDialog.getFile() != null) {
                        if (!djvuSelectFullPath.contains(" ")) {
                            String d = djvuSelectFullPath.substring(djvuSelectFullPath.lastIndexOf("."));
                            d = d.toLowerCase();

                            if (d.equals(".djvu")) {
                                FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save Images", FileDialog.SAVE);
                                SaveDjVu.setVisible(true);
                                String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                                if (path != null && SaveDjVu.getFile() != null) {
                                    DjVU2TIF.convertDjvu2PDF(djvuSelectFullPath, "C:\\DjVu++Task\\outp", 0);
                                    convertToIMG("C:\\DjVu++Task\\outp.pdf", path, 2);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "File selected is not Djvu");
                                djvuSelectFullPath = null;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Path select contain space");
                        }
                    }
                } catch (Exception ex) {
                } finally {
                    File write = new File("C:\\DjVu++Task\\tiffff.tif");
                    if (write.exists()) {
                        write.delete();
                    }

                    ConvertStatus.setVisible(false);

                    ConvertStatus2.setVisible(false);

                    File tf = new File("C:\\DjVu++Task\\outp.pdf");
                    if (tf.exists()) {
                        tf.delete();
                    }
                }

            }
        });

        Button.DJvuConverts = new JCommandButton("Djvu", getResizableIconFromResource("/images/DJVU 48.png"));
        Button.DJvuConverts.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.DJvuConverts.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.Djvu_toImage);
                Menu.addMenuButton(Button.Djvu_to_pdf);
                return Menu;
            }
        });

        ConvertBand.addCommandButton(Button.DJvuConverts, RibbonElementPriority.TOP);
        ConvertBand.startGroup();
        Button.Image_to_Djvu = new JCommandMenuButton("TO Djvu", getResizableIconFromResource("/images/image toDJVU 48.png"));
        Button.Image_to_Djvu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);

                FileDialog imagestoconvert2djvu = new FileDialog(new java.awt.Frame(), "Select Images", FileDialog.LOAD);
                imagestoconvert2djvu.setMultipleMode(true);
                imagestoconvert2djvu.setVisible(true);
                String t = imagestoconvert2djvu.getDirectory();
                openimgs = imagestoconvert2djvu.getDirectory() + "\\%s";
                boolean ch = true;
                try {
                    if (t != null) {
                        imagess = imagestoconvert2djvu.getFiles();
                        imageNAME = new String[imagess.length];
                        for (int i = 0; i < imagess.length; i++) {
                            imageNAME[i] = imagess[i].getName();
                            String sn = imageNAME[i].substring(imageNAME[i].lastIndexOf("."));
                            sn = sn.toLowerCase();
                            if (sn.equals(".jpg") | sn.equals(".jpeg") | sn.equals(".tif") | sn.equals(".png")) {

                            } else {
                                JOptionPane.showMessageDialog(null, "File selected not image\nPlease select image");
                                t = null;
                                ch = false;
                                break;
                            }

                        }
                        if (ch) {
                            FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save DjVu", FileDialog.SAVE);
                            SaveDjVu.setVisible(true);
                            String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                            if (path != null && SaveDjVu.getFile() != null) {
                                if (!path.contains(" ")) {
                                    IMAGEStoPDF.convertToIMG(imageNAME, "C:\\DjVu++Task\\pdfDjvu.pdf", openimgs, 2);

                                    PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\pdfDjvu.pdf", path, 2);

                                } else {
                                    JOptionPane.showMessageDialog(null, "Path to save contain  space ");
                                    t = null;
                                    imdjvu = null;
                                    path = null;
                                    ch = false;
                                    openimgs = null;
                                }
                            }
                        }
                    }
                } catch (Exception exe) {
                } finally {

                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);

                    File tf = new File("C:\\DjVu++Task\\pdfDjvu.pdf");
                    if (tf.exists()) {
                        tf.delete();
                    }
                }
            }
        });

        Button.Image_to_Pdf = new JCommandMenuButton("TO PDF", getResizableIconFromResource("/images/image toPDF 48.png"));
        Button.Image_to_Pdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileDialog openIMGS = new FileDialog(new java.awt.Frame(), "Select Images", FileDialog.LOAD);
                openIMGS.setMultipleMode(true);
                openIMGS.setVisible(true);
                ConvertStatus.setVisible(true);
                ConvertStatus2.setVisible(true);

                try {
                    openimg = openIMGS.getDirectory() + "\\%s";
                    if (openimg != null) {
                        File files[] = openIMGS.getFiles();
                        imagesname = new String[files.length];
                        boolean ch = true;

                        for (int i = 0; i < files.length; i++) {
                            imagesname[i] = files[i].getName();
                            PDFFullPath = "C:\\DjVu++Task\\ImagestoPDF\\" + imagesname[i].substring(0, imagesname[i].lastIndexOf(".")) + ".pdf";
                            String sn = imagesname[i].substring(imagesname[i].lastIndexOf("."));
                            sn = sn.toLowerCase();
                            if (sn.equals(".jpg") | sn.equals(".jpeg") | sn.equals(".tif") | sn.equals(".png")) {

                            } else {
                                JOptionPane.showMessageDialog(null, "File is selected is not image\nPlease select image");
                                openimg = null;
                                ch = false;
                                break;
                            }
                        }
                        if (ch) {
                            FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save Pdf", FileDialog.SAVE);
                            SaveDjVu.setVisible(true);
                            String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                            if (path != null && SaveDjVu.getFile() != null) {
                                IMAGEStoPDF.convertToIMG(imagesname, path + ".pdf", openimg, 1);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Not Selected images");
                    }
                } catch (Exception t) {
                } finally {
                    ConvertStatus.setVisible(false);

                    ConvertStatus2.setVisible(false);

                }

            }
        });
        Button.ImageConverts = new JCommandButton("Images", getResizableIconFromResource("/images/imageButton 48.png"));
        Button.ImageConverts.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.ImageConverts.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.Image_to_Djvu);
                Menu.addMenuButton(Button.Image_to_Pdf);
                return Menu;
            }
        });
        ConvertBand.addCommandButton(Button.ImageConverts, RibbonElementPriority.TOP);
        ConvertBand.startGroup();
        Button.Pdf_to_Djvu = new JCommandMenuButton("TO Djvu", getResizableIconFromResource("/images/toDJVU 48.png"));
        Button.Pdf_to_Djvu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ConvertStatus.setVisible(true);
                    ConvertStatus2.setVisible(true);

                    FileDialog PdfFile = new FileDialog(new java.awt.Frame(), "select PDF", FileDialog.LOAD);
                    PdfFile.setVisible(true);
                    fullPDF = PdfFile.getDirectory() + PdfFile.getFile();
                    ////////////////////
                    if (fullPDF != null) {
                        String d = fullPDF.substring(fullPDF.lastIndexOf("."));
                        d = d.toLowerCase();
                        if (d.equals(".pdf")) {
                            ///////////////////   
                            if (!fullPDF.contains(" ")) {
                                FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save DjVu", FileDialog.SAVE);
                                SaveDjVu.setVisible(true);
                                String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                                /////////////////
                                if (path != null && !path.contains(" ") && SaveDjVu.getFile() != null) {
                                    //PDF2DjVu.PDF2DjVu(fullPDF,"C:\\DjVu++Task\\PDFtoDjvu\\"+fullPDF.substring(fullPDF.lastIndexOf("\\"),fullPDF.lastIndexOf(".")),1);
                                    PDF2DjVu.PDF2DjVu(fullPDF, path, 1);
                                    //String l="C:\\DjVu++Task\\PDFtoDjvu\\"+fullPDF.substring(fullPDF.lastIndexOf("\\"),fullPDF.lastIndexOf("."))+".djvu";
                                    File le = new File(path + ".djvu");
                                    String url;
                                    url = "" + le.toURI();
                                    url = url.substring(5, url.length());
                                    String name = le.getName();
                                    ///////     //////
                                    if (!curropen.contains(name)) {
                                        name_url.put(name, url);
                                        url_name.put(url, name);
                                        curropen.add(name);
                                        bookList.add(url);
                                        DjvuStart.openBookInNewTab(url, name);
                                        DjvuStart.unsavedbook.add(url);

                                    } else if (curropen.contains(name)) {
                                        tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
                                    } else {
                                    }
                                    /////     ///////
                                } else {
                                    fullPDF = null;
                                    JOptionPane.showMessageDialog(null, "Place to save  contain space or don't put name  ");
                                }
                                /////////////////    

                            } else {
                                // bar.setVisible(false);
                                ConvertStatus.setVisible(false);
                                ConvertStatus2.setVisible(false);

                                JOptionPane.showMessageDialog(null, "Path to save  contain space  ");
                                fullPDF = null;
                            }
                            ////////////////////         
                        } else {
                            //  bar.setVisible(false);
                            ConvertStatus.setVisible(false);
                            JOptionPane.showMessageDialog(null, "File selected not pdf  ");
                            fullPDF = null;
                        }

                    }
                } catch (Exception ex) {
                } finally {
                    // bar.setVisible(false);
                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);

                }
            }
        });

        Button.Pdf_to_images = new JCommandMenuButton("TO Images", getResizableIconFromResource("/images/PDF toImage 48.png"));
        Button.Pdf_to_images.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ConvertStatus.setVisible(true);
                    ConvertStatus2.setVisible(true);

                    FileDialog PdfFilePath = new FileDialog(new java.awt.Frame(), "select PDF", FileDialog.LOAD);
                    PdfFilePath.setVisible(true);
                    String PDFpath = PdfFilePath.getDirectory() + "\\" + PdfFilePath.getFile();
                    if (PDFpath != null) {
                        String d = PDFpath.substring(PDFpath.lastIndexOf("."));
                        d = d.toLowerCase();
                        if (d.equals(".pdf")) {
                            FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save Images", FileDialog.SAVE);
                            SaveDjVu.setVisible(true);
                            String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                            if (path != null && SaveDjVu.getFile() != null) {
                                convertToIMG(PDFpath, path, 1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "File selected is not PDF");
                            PDFpath = null;
                        }
                    } else {
                    }
                } catch (Exception ex) {
                } finally {
                    ConvertStatus.setVisible(false);
                    ConvertStatus2.setVisible(false);

                }

            }
        });

        Button.PdfConverts = new JCommandButton("PDF", getResizableIconFromResource("/images/PDF 48.png"));
        Button.PdfConverts.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.PdfConverts.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.Pdf_to_Djvu);
                Menu.addMenuButton(Button.Pdf_to_images);
                return Menu;
            }
        });

        ConvertBand.addCommandButton(Button.PdfConverts, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.Mirror(ConvertBand.getControlPanel()));
        ConvertBand.setResizePolicies(resize);
        return ConvertBand;
    }

    /**
     * this method to Create The ToolBand and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getToolConvertBand() {
        JRibbonBand conTool = new JRibbonBand("Tools", null);
        Button.ConvertBandHand = new JCommandButton("Hand", getResizableIconFromResource(new ImageIcon("/images/hand.png").toString()));
        Button.ConvertBandHand.setEnabled(false);
        Button.ConvertBandHand.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.PAN_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        conTool.addCommandButton(Button.ConvertBandHand, RibbonElementPriority.MEDIUM);
        Button.ConvertBandSelect = new JCommandButton("Select", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.ConvertBandSelect.setEnabled(false);
        Button.SelectText = new JCommandMenuButton("Select Text", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.SelectText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ZoomArea2 = new JCommandMenuButton("Zoom Area", getResizableIconFromResource(new ImageIcon("/images/select-annotation48.png").toString()));
        Button.ZoomArea2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);

                updateMode(djvubean.getMode());
                Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                djvubean.setCursor(c);
            }
        });

        Button.ConvertBandSelect.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.ConvertBandSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ConvertBandSelect.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.SelectText);
                pan.addMenuButton(Button.ZoomArea2);
                return pan;
            }
        });
        conTool.addCommandButton(Button.ConvertBandSelect, RibbonElementPriority.MEDIUM);

        Button.ConvertBandZoom = new JCommandButton("Zoom", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ConvertBandZoom.setEnabled(false);
        Button.ConvertBandZoom.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.ConvertBandZoom.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.ConvertBandZoom.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });

        Button.ZIN = new JCommandMenuButton("Zoom In", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ZIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Button.ZIN.setEnabled(false);
                } else {
                    Button.ZIN.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });

        Button.ZOUT = new JCommandMenuButton("Zoom Out", getResizableIconFromResource(new ImageIcon("/images/zoom out48.png").toString()));
        Button.ZOUT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    Button.ZOUT.setEnabled(false);
                } else {
                    Button.ZOUT.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.Z100 = new JCommandMenuButton("100%", null);
        Button.Z100.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
            }
        });
        Button.Z25 = new JCommandMenuButton("25%", null);
        Button.Z25.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM25);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(25);
            }
        });
        Button.Z50 = new JCommandMenuButton("50%", null);
        Button.Z50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM50);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(50);
            }
        });
        Button.Z75 = new JCommandMenuButton("75%", null);
        Button.Z75.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM75);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(75);
            }
        });
        Button.Z125 = new JCommandMenuButton("125%", null);
        Button.Z125.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("125%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(125);
            }
        });
        Button.Z200 = new JCommandMenuButton("200%", null);
        Button.Z200.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(200);
            }
        });
        Button.Z600 = new JCommandMenuButton("600%", null);
        Button.Z600.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(600);
            }
        });
        Button.Z800 = new JCommandMenuButton("800%", null);
        Button.Z800.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM800);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(800);
            }
        });
        Button.Z400 = new JCommandMenuButton("400%", null);
        Button.Z400.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM400);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(400);
            }
        });
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(52);
                modeNum = 52;
                UpdateZoom(modeNum);
            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(115);
                modeNum = 115;
                UpdateZoom(modeNum);
            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource(new ImageIcon("/images/fit visible48.png").toString()));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource(new ImageIcon("/images/actual size48.png").toString()));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
                modeNum = 100;
                UpdateZoom(modeNum);
            }
        });
        Button.ConvertBandZoom.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.ZIN);
                pan.addMenuButton(Button.ZOUT);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.Z800);
                pan.addMenuButton(Button.Z600);
                pan.addMenuButton(Button.Z400);
                pan.addMenuButton(Button.Z200);
                pan.addMenuButton(Button.Z100);
                pan.addMenuButton(Button.Z125);
                pan.addMenuButton(Button.Z75);
                pan.addMenuButton(Button.Z50);
                pan.addMenuButton(Button.Z25);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.AS);
                pan.addMenuButton(Button.FP);
                pan.addMenuButton(Button.FW);
                pan.addMenuButton(Button.FV);
                // pan.setToDismissOnChildClick(false);               
                return pan;
            }
        });
        conTool.addCommandButton(Button.ConvertBandZoom, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> Resize = new ArrayList<RibbonBandResizePolicy>();
        Resize.add(new CoreRibbonResizePolicies.Mirror(conTool.getControlPanel()));
        conTool.setResizePolicies(Resize);
        return conTool;
    }

    public JRibbonBand getToolForViewBand() {
        JRibbonBand conTool = new JRibbonBand("Tools", null);
        Button.ViewHand = new JCommandButton("Hand", getResizableIconFromResource(new ImageIcon("/images/hand.png").toString()));
        Button.ViewHand.setEnabled(false);
        Button.ViewHand.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.PAN_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        conTool.addCommandButton(Button.ViewHand, RibbonElementPriority.MEDIUM);
        Button.ViewSelect = new JCommandButton("Select", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.ViewSelect.setEnabled(false);
        Button.SelectText = new JCommandMenuButton("Select Text", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.SelectText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ZoomArea2 = new JCommandMenuButton("Zoom Area", getResizableIconFromResource(new ImageIcon("/images/select-annotation48.png").toString()));
        Button.ZoomArea2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);

                updateMode(djvubean.getMode());
                Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                djvubean.setCursor(c);
            }
        });

        Button.ViewSelect.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.ViewSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ViewSelect.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.SelectText);
                pan.addMenuButton(Button.ZoomArea2);
                return pan;
            }
        });
        conTool.addCommandButton(Button.ViewSelect, RibbonElementPriority.MEDIUM);

        Button.ViewZoom = new JCommandButton("Zoom", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ViewZoom.setEnabled(false);
        Button.ViewZoom.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.ViewZoom.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.ViewZoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.ZIN = new JCommandMenuButton("Zoom In", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ZIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Button.ZIN.setEnabled(false);
                } else {
                    Button.ZIN.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });

        Button.ZOUT = new JCommandMenuButton("Zoom Out", getResizableIconFromResource(new ImageIcon("/images/zoom out48.png").toString()));
        Button.ZOUT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    Button.ZOUT.setEnabled(false);
                } else {
                    Button.ZOUT.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.Z100 = new JCommandMenuButton("100%", null);
        Button.Z100.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
            }
        });
        Button.Z25 = new JCommandMenuButton("25%", null);
        Button.Z25.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM25);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(25);
            }
        });
        Button.Z50 = new JCommandMenuButton("50%", null);
        Button.Z50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM50);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(50);
            }
        });
        Button.Z125 = new JCommandMenuButton("125%", null);
        Button.Z125.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("125%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(125);
            }
        });
        Button.Z200 = new JCommandMenuButton("200%", null);
        Button.Z200.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(200);
            }
        });
        Button.Z600 = new JCommandMenuButton("600%", null);
        Button.Z600.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(600);
            }
        });
        Button.Z800 = new JCommandMenuButton("800%", null);
        Button.Z800.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM800);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(800);
            }
        });
        Button.Z400 = new JCommandMenuButton("400%", null);
        Button.Z400.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM400);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(400);
            }
        });
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(52);
                modeNum = 52;
                UpdateZoom(modeNum);
            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(115);
                modeNum = 115;
                UpdateZoom(modeNum);
            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource(new ImageIcon("/images/fit visible48.png").toString()));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource(new ImageIcon("/images/actual size48.png").toString()));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
                modeNum = 100;
                UpdateZoom(modeNum);
            }
        });
        Button.ViewZoom.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.ZIN);
                pan.addMenuButton(Button.ZOUT);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.Z800);
                pan.addMenuButton(Button.Z600);
                pan.addMenuButton(Button.Z400);
                pan.addMenuButton(Button.Z200);
                pan.addMenuButton(Button.Z100);
                pan.addMenuButton(Button.Z125);
                pan.addMenuButton(Button.Z75);
                pan.addMenuButton(Button.Z50);
                pan.addMenuButton(Button.Z25);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.AS);
                pan.addMenuButton(Button.FP);
                pan.addMenuButton(Button.FW);
                pan.addMenuButton(Button.FV);
                // pan.setToDismissOnChildClick(false);               
                return pan;
            }
        });
        conTool.addCommandButton(Button.ViewZoom, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> Resize = new ArrayList<RibbonBandResizePolicy>();
        Resize.add(new CoreRibbonResizePolicies.Mirror(conTool.getControlPanel()));
        conTool.setResizePolicies(Resize);
        return conTool;
    }

    public JRibbonBand getToolForEditBand() {
        JRibbonBand conTool = new JRibbonBand("Tools", null);
        Button.EditHand = new JCommandButton("Hand", getResizableIconFromResource(new ImageIcon("/images/hand.png").toString()));
        Button.EditHand.setEnabled(false);
        Button.EditHand.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.PAN_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        conTool.addCommandButton(Button.EditHand, RibbonElementPriority.MEDIUM);
        Button.EditSelect = new JCommandButton("Select", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.EditSelect.setEnabled(false);
        Button.SelectText = new JCommandMenuButton("Select Text", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.SelectText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ZoomArea2 = new JCommandMenuButton("Zoom Area", getResizableIconFromResource("/images/select-annotation48.png"));
        Button.ZoomArea2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);

                updateMode(djvubean.getMode());
                Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                djvubean.setCursor(c);
            }
        });

        Button.EditSelect.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.EditSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.EditSelect.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.SelectText);
                pan.addMenuButton(Button.ZoomArea2);
                return pan;
            }
        });
        conTool.addCommandButton(Button.EditSelect, RibbonElementPriority.MEDIUM);

        Button.EditZoom = new JCommandButton("Zoom", getResizableIconFromResource("/images/zoom in48.png"));
        Button.EditZoom.setEnabled(false);
        Button.EditZoom.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.EditZoom.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.EditZoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.ZIN = new JCommandMenuButton("Zoom In", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ZIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Button.ZIN.setEnabled(false);
                } else {
                    Button.ZIN.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });

        Button.ZOUT = new JCommandMenuButton("Zoom Out", getResizableIconFromResource(new ImageIcon("/images/zoom out48.png").toString()));
        Button.ZOUT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    Button.ZOUT.setEnabled(false);
                } else {
                    Button.ZOUT.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.Z100 = new JCommandMenuButton("100%", null);
        Button.Z100.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
            }
        });
        Button.Z25 = new JCommandMenuButton("25%", null);
        Button.Z25.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM25);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(25);
            }
        });
        Button.Z50 = new JCommandMenuButton("50%", null);
        Button.Z50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM50);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(50);
            }
        });
        Button.Z125 = new JCommandMenuButton("125%", null);
        Button.Z125.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("125%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(125);
            }
        });
        Button.Z200 = new JCommandMenuButton("200%", null);
        Button.Z200.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(200);
            }
        });
        Button.Z600 = new JCommandMenuButton("600%", null);
        Button.Z600.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(600);
            }
        });
        Button.Z800 = new JCommandMenuButton("800%", null);
        Button.Z800.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM800);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(800);
            }
        });
        Button.Z400 = new JCommandMenuButton("400%", null);
        Button.Z400.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM400);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(400);
            }
        });
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(52);
                modeNum = 52;
                UpdateZoom(modeNum);
            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(115);
                modeNum = 115;
                UpdateZoom(modeNum);
            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource(new ImageIcon("/images/fit visible48.png").toString()));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource(new ImageIcon("/images/actual size48.png").toString()));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
                modeNum = 100;
                UpdateZoom(modeNum);
            }
        });
        Button.EditZoom.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.ZIN);
                pan.addMenuButton(Button.ZOUT);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.Z800);
                pan.addMenuButton(Button.Z600);
                pan.addMenuButton(Button.Z400);
                pan.addMenuButton(Button.Z200);
                pan.addMenuButton(Button.Z100);
                pan.addMenuButton(Button.Z125);
                pan.addMenuButton(Button.Z75);
                pan.addMenuButton(Button.Z50);
                pan.addMenuButton(Button.Z25);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.AS);
                pan.addMenuButton(Button.FP);
                pan.addMenuButton(Button.FW);
                pan.addMenuButton(Button.FV);
                // pan.setToDismissOnChildClick(false);               
                return pan;
            }
        });
        conTool.addCommandButton(Button.EditZoom, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> Resize = new ArrayList<RibbonBandResizePolicy>();
        Resize.add(new CoreRibbonResizePolicies.Mirror(conTool.getControlPanel()));
        conTool.setResizePolicies(Resize);
        return conTool;
    }

    public JRibbonBand getToolForCommentBand() {
        JRibbonBand conTool = new JRibbonBand("Tools", null);
        Button.CommentHand = new JCommandButton("Hand", getResizableIconFromResource(new ImageIcon("/images/hand.png").toString()));
        Button.CommentHand.setEnabled(false);
        Button.CommentHand.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.PAN_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        conTool.addCommandButton(Button.CommentHand, RibbonElementPriority.MEDIUM);
        Button.CommentSelect = new JCommandButton("Select", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.CommentSelect.setEnabled(false);
        Button.SelectText = new JCommandMenuButton("Select Text", getResizableIconFromResource(new ImageIcon("/images/textselect48.png").toString()));
        Button.SelectText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.ZoomArea2 = new JCommandMenuButton("Zoom Area", getResizableIconFromResource(new ImageIcon("/images/select-annotation48.png").toString()));
        Button.ZoomArea2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);

                updateMode(djvubean.getMode());
                Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                djvubean.setCursor(c);
            }
        });

        Button.CommentSelect.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.CommentSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
                updateMode(djvubean.getMode());
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }
        });
        Button.CommentSelect.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.SelectText);
                pan.addMenuButton(Button.ZoomArea2);
                return pan;
            }
        });
        conTool.addCommandButton(Button.CommentSelect, RibbonElementPriority.MEDIUM);

        Button.CommentZoom = new JCommandButton("Zoom", getResizableIconFromResource("/images/zoomin48.png"));
        Button.CommentZoom.setEnabled(false);
        Button.CommentZoom.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
        Button.CommentZoom.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.CommentZoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.ZIN = new JCommandMenuButton("Zoom In", getResizableIconFromResource("/images/zoom in48.png"));
        Button.ZIN.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Button.ZIN.setEnabled(false);
                } else {
                    Button.ZIN.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_IN);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });

        Button.ZOUT = new JCommandMenuButton("Zoom Out", getResizableIconFromResource("/images/zoom out48.png"));
        Button.ZOUT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    Button.ZOUT.setEnabled(false);
                } else {
                    Button.ZOUT.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                Zoomformat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(Zoomformat);
                jsldHort.setValue(nscale);
            }
        });
        Button.Z100 = new JCommandMenuButton("100%", null);
        Button.Z100.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
            }
        });
        Button.Z25 = new JCommandMenuButton("25%", null);
        Button.Z25.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM25);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(25);
            }
        });
        Button.Z50 = new JCommandMenuButton("50%", null);
        Button.Z50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM50);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(50);
            }
        });
        Button.Z125 = new JCommandMenuButton("125%", null);
        Button.Z125.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("125%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(125);
            }
        });
        Button.Z200 = new JCommandMenuButton("200%", null);
        Button.Z200.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(200);
            }
        });
        Button.Z600 = new JCommandMenuButton("600%", null);
        Button.Z600.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(600);
            }
        });
        Button.Z800 = new JCommandMenuButton("800%", null);
        Button.Z800.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM800);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(800);
            }
        });
        Button.Z400 = new JCommandMenuButton("400%", null);
        Button.Z400.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM400);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(400);
            }
        });
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(52);
                modeNum = 52;
                UpdateZoom(modeNum);
            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(115);
                modeNum = 115;
                UpdateZoom(modeNum);
            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource("/images/fit visible48.png"));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource("/images/actual size48.png"));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);
                modeNum = 100;
                UpdateZoom(modeNum);
            }
        });
        Button.CommentZoom.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
                pan.addMenuButton(Button.ZIN);
                pan.addMenuButton(Button.ZOUT);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.Z800);
                pan.addMenuButton(Button.Z600);
                pan.addMenuButton(Button.Z400);
                pan.addMenuButton(Button.Z200);
                pan.addMenuButton(Button.Z100);
                pan.addMenuButton(Button.Z125);
                pan.addMenuButton(Button.Z75);
                pan.addMenuButton(Button.Z50);
                pan.addMenuButton(Button.Z25);
                pan.addMenuSeparator();
                pan.addMenuButton(Button.AS);
                pan.addMenuButton(Button.FP);
                pan.addMenuButton(Button.FW);
                pan.addMenuButton(Button.FV);
                // pan.setToDismissOnChildClick(false);               
                return pan;
            }
        });
        conTool.addCommandButton(Button.CommentZoom, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> Resize = new ArrayList<RibbonBandResizePolicy>();
        Resize.add(new CoreRibbonResizePolicies.Mirror(conTool.getControlPanel()));
        conTool.setResizePolicies(Resize);
        return conTool;
    }

    /**
     * this method to Create The CreateBand and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getCreateBand() {
        JRibbonBand create = new JRibbonBand("Create", null);
        Button.Blank = new JCommandButton("Blank", getResizableIconFromResource("/images/Blank48.png"));
        RichTooltip Blank = new RichTooltip("Blank ", "Create a Blank Djvu");
        Button.Blank.setActionRichTooltip(Blank);
        Button.Blank.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new CreatDjVu.NewDjvu().createFrame();
            }
        });
        Button.FromClipbord = new JCommandButton("From"
                + "Clipboard", getResizableIconFromResource("/images/from clipboard48.png"));
        RichTooltip Clip = new RichTooltip("Blank ", "Create a Djvu From the content on Clipboard");
        Button.FromClipbord.setActionRichTooltip(Clip);
        Button.FromClipbord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new CreatDjVu.NewDjvuFromClib().createFrame();
            }
        });

//        Button.FFile = new JCommandButton("From"
//                + "File", getResizableIconFromResource("/images/from file48.png"));
//        Button.FFile.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
//        create.addCommandButton(Button.FFile, RibbonElementPriority.TOP);
        create.addCommandButton(Button.Blank, RibbonElementPriority.TOP);
        create.addCommandButton(Button.FromClipbord, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.Mirror(create.getControlPanel()));
        create.setResizePolicies(resize);
        return create;
    }

    /**
     * this method to Create The ConvertBand and return it to add in the Ribbon
     *
     * @return
     */
    public JRibbonBand getConvetBand() {
        JRibbonBand ConvertBand = new JRibbonBand("Convert", null);
        Button.OCREnglish2 = new JCommandMenuButton("OCR"
                + " English", getResizableIconFromResource(new ImageIcon("/images/ocr english48.png").toString()));
        Button.OCREnglish2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setMode(DjVuBean.SELECT_MODE);

            }
        });

        Button.OCRArabic2 = new JCommandMenuButton("OCR"
                + " Arabic", getResizableIconFromResource("/images/ocr arabic48.png"));
        Button.OCRArabic2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                djvubean.setMode(DjVuBean.SELECT_MODE, "ar");

            }
        });
        Button.franch2 = new JCommandMenuButton("OCR"
                + " franch", getResizableIconFromResource("/images/ocr arabic48.png"));
        Button.franch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                djvubean.setMode(DjVuBean.SELECT_MODE, "fr");

            }
        });
        Button.Ocr2 = new JCommandButton("OCR", getResizableIconFromResource("/images/ocr48.png"));
        Button.Ocr2.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        RichTooltip tip = new RichTooltip("Ocr", "Convert text With in image to Searchable and editable");
        Button.Ocr2.setActionRichTooltip(tip);
        Button.Ocr2.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.OCREnglish2);

                Menu.addMenuButton(Button.OCRArabic2);
                Menu.addMenuButton(Button.franch2);

                return Menu;
            }
        });
        ConvertBand.addCommandButton(Button.Ocr2, RibbonElementPriority.TOP);
        //  ConvertBand.startGroup();
        Button.Pdf = new JCommandMenuButton("convert"
                + " Djvu", getResizableIconFromResource("/images/DOC48.png"));
        RichTooltip pdf = new RichTooltip("PDF", "Convert From DJVU into PDF");
        Button.Pdf.setActionRichTooltip(pdf);
        Button.Djvu = new JCommandMenuButton("convert "
                + "PDF", getResizableIconFromResource("/images/DOC48.png"));
        RichTooltip Djvu = new RichTooltip("Djvu", "Convert PDF into Djvu");
        Button.Djvu.setActionRichTooltip(Djvu);
        //ConvertBand.addCommandButton(Button.Pdf, RibbonElementPriority.TOP);
        // ConvertBand.addCommandButton(Button.Djvu, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.Mirror(ConvertBand.getControlPanel()));
        ConvertBand.setResizePolicies(resize);
        return ConvertBand;
    }

    /**
     * this method to Create The FontBand and return it to add in the Ribbon
     *
     * @return
     */
    public static Font font = new Font("Dialog", Font.PLAIN, 12);

    public JFlowRibbonBand getFontBand() {
        JFlowRibbonBand fontBand = new JFlowRibbonBand("Font", null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        Button.fontCombo = new JComboBox(fonts);
        Button.fontCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Button.fontCombo = (JComboBox) e.getSource();
                font = new Font((String) Button.fontCombo.getSelectedItem(), font.getStyle(), font.getSize());
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }

            }
        });
        JRibbonComponent fontComboWrapper = new JRibbonComponent(DjvuRibbonComponents.fontCombo);
        fontComboWrapper.setKeyTip("SF");
        fontBand.addFlowComponent(fontComboWrapper);
        DjvuRibbonComponents.sizeCombo = new JComboBox(new Object[]{"11", "12", "13", "14", "16", "18", "20", "22", "26", "27", "28", "32", "48"});
        DjvuRibbonComponents.sizeCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DjvuRibbonComponents.sizeCombo = (JComboBox) e.getSource();
                font = new Font(font.getFontName(), font.getStyle(), Integer.parseInt((String) DjvuRibbonComponents.sizeCombo.getSelectedItem()));
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }

            }
        });
        JRibbonComponent sizeComboWrapper = new JRibbonComponent(Button.sizeCombo);
        sizeComboWrapper.setKeyTip("SS");
        fontBand.addFlowComponent(sizeComboWrapper);

        JCommandButtonStrip styleStrip = new JCommandButtonStrip();

        DjvuRibbonComponents.styleBoldButton = new JCommandToggleButton("", getResizableIconFromResource("/images/bold.gif"));
        //  DjvuRibbonComponents.styleBoldButton.getActionModel().setSelected(true);
        DjvuRibbonComponents.styleBoldButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                font = new Font(font.getFontName(), Font.BOLD, font.getSize());
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }

            }
        });
        DjvuRibbonComponents.styleBoldButton.setActionKeyTip("1");
        styleStrip.add(DjvuRibbonComponents.styleBoldButton);

        DjvuRibbonComponents.styleItalicButton = new JCommandToggleButton("", getResizableIconFromResource("/images/italics.gif"));
        DjvuRibbonComponents.styleItalicButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                font = new Font(font.getFontName(), Font.ITALIC, font.getSize());
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }

            }
        });
        DjvuRibbonComponents.styleItalicButton.setActionKeyTip("2");
        styleStrip.add(DjvuRibbonComponents.styleItalicButton);
//
//        DjvuRibbonComponents.styleUnderlineButton = new JCommandToggleButton(
//                "", getResizableIconFromResource("/images/underline.gif"));
//        DjvuRibbonComponents.styleUnderlineButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Map<TextAttribute, Object> map = new HashMap<TextAttribute, Object>();
//                map.put(TextAttribute.FONT, font);
//                map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//                font = Font.getFont(map);
//
//                if (NewDjvu.textArea != null) {
//                    NewDjvu.textArea.setFont(font);
//                }
//                if (NewDjvuFromClib.textAreaClip != null) {
//                    NewDjvuFromClib.textAreaClip.setFont(font);
//                }
//
//            }
//        });
//        DjvuRibbonComponents.styleUnderlineButton.setActionKeyTip("3");
//        styleStrip.add(DjvuRibbonComponents.styleUnderlineButton);

//        Button.styleStrikeThroughButton = new JCommandToggleButton(
//                "", getResizableIconFromResource("/images/strike out48.png"));
////		Button.styleStrikeThroughButton 
////				.setActionRichTooltip(new RichTooltip(
////						resourceBundle
////								.getString("FontStrikethrough.tooltip.textActionTitle"),
////						resourceBundle
////								.getString("FontStrikethrough.tooltip.textActionParagraph1")));
//        Button.styleStrikeThroughButton.setActionKeyTip("4");
//       // styleStrip.add(Button.styleStrikeThroughButton);
        fontBand.addFlowComponent(styleStrip);
        fontBand.addFlowComponent(new ColorSelector().getCommandbuttonColor());
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.FlowThreeRows(fontBand.getControlPanel()));
        fontBand.setResizePolicies(resize);

        return fontBand;
    }

    /**
     * this method to Create The ParagraphBand and return it to add in the
     * Ribbon
     *
     * @return
     */
    public JFlowRibbonBand getParagraphBand() {
        JFlowRibbonBand paragraph = new JFlowRibbonBand("Paragraph", null);
        JCommandButtonStrip intentStrip = new JCommandButtonStrip();
        DjvuRibbonComponents.indentLeftButton = new JCommandButton("", getResizableIconFromResource("/images/indent_left.gif"));
        DjvuRibbonComponents.indentLeftButton.setActionKeyTip("AO");
        DjvuRibbonComponents.indentLeftButton.setDisplayState(CommandButtonDisplayState.SMALL);
        DjvuRibbonComponents.indentRightButton = new JCommandButton("", getResizableIconFromResource("/images/indent_right.gif"));
        DjvuRibbonComponents.indentRightButton.setActionKeyTip("AI");
        DjvuRibbonComponents.indentRightButton.setDisplayState(CommandButtonDisplayState.SMALL);
        intentStrip.add(DjvuRibbonComponents.indentLeftButton);
        intentStrip.add(DjvuRibbonComponents.indentRightButton);

        JCommandButtonStrip alignStrip = new JCommandButtonStrip();
        CommandToggleButtonGroup alignGroup = new CommandToggleButtonGroup();

        DjvuRibbonComponents.alignLeftButton = new JCommandToggleButton("",
                getResizableIconFromResource("/images/justify_left.gif"));
        DjvuRibbonComponents.alignLeftButton.setActionKeyTip("AL");
        //Button.alignLeftButton.getActionModel().setSelected(true);
        alignGroup.add(DjvuRibbonComponents.alignLeftButton);
        alignStrip.add(DjvuRibbonComponents.alignLeftButton);
        DjvuRibbonComponents.alignLeftButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet left = new SimpleAttributeSet();
                    StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
                    doc.setParagraphAttributes(0, doc.getLength(), left, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet left = new SimpleAttributeSet();
                    StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
                    doc.setParagraphAttributes(0, doc.getLength(), left, false);
                }

            }
        });

        DjvuRibbonComponents.alignCenterButton = new JCommandToggleButton("",
                getResizableIconFromResource("/images/justify_Center.gif"));
        DjvuRibbonComponents.alignCenterButton.setActionKeyTip("AC");
        DjvuRibbonComponents.alignCenterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }

            }
        });
        alignGroup.add(DjvuRibbonComponents.alignCenterButton);
        alignStrip.add(DjvuRibbonComponents.alignCenterButton);

        DjvuRibbonComponents.alignRightButton = new JCommandToggleButton("",
                getResizableIconFromResource("/images/justify_right.gif"));
        DjvuRibbonComponents.alignRightButton.setActionKeyTip("AR");
        DjvuRibbonComponents.alignRightButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet right = new SimpleAttributeSet();
                    StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
                    doc.setParagraphAttributes(0, doc.getLength(), right, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet right = new SimpleAttributeSet();
                    StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
                    doc.setParagraphAttributes(0, doc.getLength(), right, false);
                }

            }
        });
        alignGroup.add(DjvuRibbonComponents.alignRightButton);
        alignStrip.add(DjvuRibbonComponents.alignRightButton);

        DjvuRibbonComponents.alignFillButton = new JCommandToggleButton("",
                getResizableIconFromResource("/images/justify_justify.gif"));
        DjvuRibbonComponents.alignFillButton.setActionKeyTip("AF");
        DjvuRibbonComponents.alignFillButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet Fill = new SimpleAttributeSet();
                    StyleConstants.setAlignment(Fill, StyleConstants.ALIGN_JUSTIFIED);
                    doc.setParagraphAttributes(0, doc.getLength(), Fill, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet Fill = new SimpleAttributeSet();
                    StyleConstants.setAlignment(Fill, StyleConstants.ALIGN_JUSTIFIED);
                    doc.setParagraphAttributes(0, doc.getLength(), Fill, false);
                }

            }
        });
        alignGroup.add(DjvuRibbonComponents.alignFillButton);
        alignStrip.add(DjvuRibbonComponents.alignFillButton);
        paragraph.addFlowComponent(alignStrip);
        // paragraph.addFlowComponent(intentStrip);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.FlowTwoRows(paragraph.getControlPanel()));
        paragraph.setResizePolicies(resize);
        return paragraph;

    }

    /**
     * this method to Create The EditContentBand and return it to add in the
     * Ribbon
     *
     * @return
     */
    public JRibbonBand getEditContent() {
        JRibbonBand Editcont = new JRibbonBand("Edit contents", null);
        Editcont.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(Editcont));
        Editcont.startGroup();
        Button.EditText1 = new JCommandButton("Edit"
                + " Text", getResizableIconFromResource("/images/JPG48.png"));
        Editcont.addCommandButton(Button.EditText1, RibbonElementPriority.TOP);
        Button.EditObject1 = new JCommandButton("Edit"
                + " Object", getResizableIconFromResource("/images/GIF48.png"));
        Button.EditObject1.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.EditObject1.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JPopupPanel pan = new JPopupPanel() {
                };
                pan.setBackground(Color.WHITE);
                return pan;
            }
        });
        Editcont.addCommandButton(Button.EditObject1, RibbonElementPriority.TOP);
        Button.JoinSplit = new JCommandButton("Join&SplitText", getResizableIconFromResource("/images/Document32.png"));
        Editcont.addCommandButton(Button.JoinSplit, RibbonElementPriority.MEDIUM);
        Editcont.startGroup();
        Button.AddText = new JCommandButton("Add Text", getResizableIconFromResource("/images/textselect48.png"));
        Button.AddImg = new JCommandButton("Add Image", getResizableIconFromResource("/images/GIF48.png"));
        Button.AddImg.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.AddImg.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JPopupPanel pan = new JPopupPanel() {
                };
                pan.setBackground(Color.WHITE);
                return pan;
            }
        });
        Button.AddShaps = new JCommandButton("Add Shapes", getResizableIconFromResource("/images/GIF48.png"));
        Button.AddShaps.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Button.AddShaps.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JPopupPanel pan = new JPopupPanel() {
                };
                pan.setBackground(Color.WHITE);
                return pan;
            }
        });
        Editcont.addCommandButton(Button.AddText, RibbonElementPriority.MEDIUM);
        Editcont.addCommandButton(Button.AddImg, RibbonElementPriority.MEDIUM);
        Editcont.addCommandButton(Button.AddShaps, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> resize = new ArrayList<RibbonBandResizePolicy>();
        resize.add(new CoreRibbonResizePolicies.Mirror(Editcont.getControlPanel()));
        Editcont.setResizePolicies(resize);
        return Editcont;
    }

    /**
     * this method to Create The UIOptionBand and return it to add in the Ribbon
     *
     * @param Djvu
     * @return
     */
    public JRibbonBand getUiOptionsBand(DjvuStart Djvu) {
        JRibbonBand Uioptions = new JRibbonBand("UI Options", null);
        Uioptions.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(Uioptions));
        Uioptions.startGroup();
        Button.Switch = new JCommandButton("Change "
                + "ToolBar Mode", null);
        Uioptions.addCommandButton(Button.Switch, RibbonElementPriority.TOP);
        Uioptions.startGroup();
        System.err.println(DjvuStart.djvu);
        JRibbonComponent look_feel = new JRibbonComponent(null, "Change "
                + "Djvu++ Skin", LookAndFeelSwitcher.getLookAndFeelSwitcher(Djvu));
        look_feel.setDisplayPriority(RibbonElementPriority.TOP);
        Uioptions.addRibbonComponent(look_feel);

        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Uioptions.getControlPanel()));
        Uioptions.setResizePolicies(resizePolicies);
        return Uioptions;
    }

    /**
     *
     * @return
     */
    public JRibbonBand getTypewriterBand() {
        JRibbonBand Typewriter = new JRibbonBand("TypeWriter", null);
        Typewriter.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(Typewriter));
        Typewriter.startGroup();
        Button.Note2 = new JCommandButton("Note", getResizableIconFromResource("/images/notes48.png"));
        Button.Note2.setEnabled(false);
        Typewriter.addCommandButton(Button.Note2, RibbonElementPriority.TOP);
        Typewriter.startGroup();
        Button.TWriter = new JCommandButton("TyperWriter", getResizableIconFromResource("/images/type writer48.png"));
        Button.TWriter.setEnabled(false);
        Button.TextBox = new JCommandButton("TextBox", getResizableIconFromResource("/images/callout48.png"));
        Button.TextBox.setEnabled(false);
        Button.Callout = new JCommandButton("Callout", getResizableIconFromResource("/images/textbox48.png"));
        Button.Callout.setEnabled(false);
        Typewriter.addCommandButton(Button.TWriter, RibbonElementPriority.TOP);
        Typewriter.addCommandButton(Button.Callout, RibbonElementPriority.MEDIUM);
        Typewriter.addCommandButton(Button.TextBox, RibbonElementPriority.MEDIUM);

        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Typewriter.getControlPanel()));
        Typewriter.setResizePolicies(resizePolicies);
        return Typewriter;
    }

    /**
     *
     * @return
     */
    private float stroke = 3f;
    Graphics2D editG2D = null;

    public JRibbonBand getDrawingBand() {
        JRibbonBand Drawing = new JRibbonBand("Drawing", null);
        Drawing.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(Drawing));
        Drawing.startGroup();
        Button.Rect = new JCommandButton("", getResizableIconFromResource("/images/rectangle48.png"));
        Button.Rect.setDisplayState(CommandButtonDisplayState.SMALL);
        Button.Rect.setEnabled(false);
        Drawing.addCommandButton(Button.Rect, RibbonElementPriority.MEDIUM);
        Button.Oval = new JCommandButton("", getResizableIconFromResource("/images/oval48.png"));
        Button.Oval.setDisplayState(CommandButtonDisplayState.SMALL);
        Button.Oval.setEnabled(false);

        Drawing.addCommandButton(Button.Oval, RibbonElementPriority.MEDIUM);
        Button.Polygon = new JCommandButton("", getResizableIconFromResource("/images/polygon48.png"));
        Button.Polygon.setEnabled(false);
        Button.Polygon.setDisplayState(CommandButtonDisplayState.SMALL);
        Drawing.addCommandButton(Button.Polygon, RibbonElementPriority.MEDIUM);
        Button.Cloud = new JCommandButton("", getResizableIconFromResource("/images/cloud48.png"));
        Button.Cloud.setDisplayState(CommandButtonDisplayState.SMALL);
        Button.Cloud.setEnabled(false);
        Drawing.addCommandButton(Button.Cloud, RibbonElementPriority.MEDIUM);
        Button.arrow = new JCommandButton("", getResizableIconFromResource("/images/arrow48.png"));
        Button.arrow.setDisplayState(CommandButtonDisplayState.SMALL);
        Button.arrow.setEnabled(false);
        Drawing.addCommandButton(Button.arrow, RibbonElementPriority.MEDIUM);
        Button.Line = new JCommandButton("", getResizableIconFromResource("/images/line48.png"));
        Button.Line.setDisplayState(CommandButtonDisplayState.SMALL);
        Button.Line.setEnabled(false);
        Drawing.addCommandButton(Button.Line, RibbonElementPriority.MEDIUM);
        Drawing.startGroup();
        Button.pencil = new JCommandButton("Pensil", getResizableIconFromResource("/images/pencil48.png"));
        Button.pencil.setEnabled(false);
        //djvubean.addMouseMotionListener(new MouseAdapter);
        Button.pencil.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//               djvubean.setMode(DjVuBean.SELECT_MODE);
//                editG2D=(Graphics2D) djvubean.getGraphics();
//                  editG2D.setColor(Color.RED);
//                   editG2D.setStroke(new BasicStroke(stroke));
//        editG2D.drawLine(SelectMode.x, SelectMode.y,SelectMode.w, SelectMode.z);
            }
        });
        Drawing.addCommandButton(Button.pencil, RibbonElementPriority.MEDIUM);
        Button.Eraser = new JCommandButton("Eraser", getResizableIconFromResource("/images/eraser48.png"));
        Button.Eraser.setEnabled(false);
        Drawing.addCommandButton(Button.Eraser, RibbonElementPriority.MEDIUM);
        Drawing.startGroup();
        Button.AreaHighLight = new JCommandButton("Area"
                + "Highlight", getResizableIconFromResource("/images/areaHighlight48.png"));
        Button.AreaHighLight.setEnabled(false);
        Drawing.addCommandButton(Button.AreaHighLight, RibbonElementPriority.TOP);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Drawing.getControlPanel()));
        Drawing.setResizePolicies(resizePolicies);
        return Drawing;
    }

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

    public JFlowRibbonBand getGotoBand() {
        JFlowRibbonBand Goto = new JFlowRibbonBand("GO To", getResizableIconFromResource("/images/search.png"));
        Goto.addFlowComponent(new PageSelect().getGotoPanel());
        DjvuRibbonComponents.NextView = new JCommandButton("Next View", getResizableIconFromResource("/images/next view48.png"));
        DjvuRibbonComponents.NextView.setEnabled(false);
        DjvuRibbonComponents.NextView.setDisplayState(CommandButtonDisplayState.MEDIUM);
        DjvuRibbonComponents.NextView.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = 0;
                if (djvubean.getPage() != djvubean.SeqView.get(djvubean.SeqView.size() - 1)) {

                    if (djvubean.SeqView.contains(djvubean.getPage())) {
                        index = djvubean.SeqView.indexOf(djvubean.getPage());

                    }
                    djvubean.setPage(djvubean.SeqView.get(index + 1));
                    pageSelectBox.setSelectedItem(djvubean.getPage());
                    GoToArea.setText("" + djvubean.getPage());

                } else {
                    if (djvubean.getPage() == 1) {
                        DjvuRibbonComponents.prevview.setEnabled(false);

                        prev.setEnabled(false);
                    }
                    DjvuRibbonComponents.NextView.setEnabled(false);
                    next.setEnabled(false);
                }

            }
        });
        DjvuRibbonComponents.prevview = new JCommandButton("Previous View", getResizableIconFromResource("/images/previous view48.png"));
        DjvuRibbonComponents.prevview.setEnabled(false);
        DjvuRibbonComponents.prevview.setDisplayState(CommandButtonDisplayState.MEDIUM);
        DjvuRibbonComponents.prevview.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = 0;

                if (djvubean.getPage() != 1) {

                    if (djvubean.SeqView.contains(djvubean.getPage())) {
                        index = djvubean.SeqView.indexOf(djvubean.getPage());

                    }

                    djvubean.setPage(djvubean.SeqView.get(index - 1));
                    pageSelectBox.setSelectedItem(djvubean.getPage());
                    GoToArea.setText("" + djvubean.getPage());
                    DjvuRibbonComponents.NextView.setEnabled(true);
                    next.setEnabled(true);
                } else {
                    if (djvubean.getPage() == djvubean.SeqView.get(djvubean.SeqView.size() - 1)) {
                        DjvuRibbonComponents.NextView.setEnabled(false);
                        next.setEnabled(false);
                    }
                    DjvuRibbonComponents.prevview.setEnabled(false);
                    prev.setEnabled(false);
                }
            }
        });
        Goto.addFlowComponent(DjvuRibbonComponents.prevview);
        Goto.addFlowComponent(DjvuRibbonComponents.NextView);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.FlowTwoRows(Goto.getControlPanel()));
        Goto.setResizePolicies(resizePolicies);
        return Goto;
    }

    public JRibbonBand getPageDisplay() {
        JRibbonBand PageDisplay = new JRibbonBand("Page Display", null);
        PageDisplay.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(PageDisplay));
        PageDisplay.startGroup();
        Button.RotateLeft2 = new JCommandMenuButton("Rotate "
                + "Left", getResizableIconFromResource("/images/rotate left48.png"));
        Button.RotateLeft2.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.RotateLeft2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(-90);

            }
        });

        Button.Rotateright2 = new JCommandMenuButton("Rotate "
                + "Right", getResizableIconFromResource("/images/rotate right48.png"));

        Button.Rotateright2.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.Rotateright2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //  djvubean.setRotationDegrees(90f);

                com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(90);

            }
        });
        Button.RotatePages2 = new JCommandButton("Rotate"
                + " pages", getResizableIconFromResource("/images/rotate left48.png"));
        Button.RotatePages2.setEnabled(false);

        Button.RotatePages2.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

        Button.RotatePages2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(-90);

            }
        });

        Button.RotatePages2.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu Menu = new JCommandPopupMenu();
                Menu.addMenuButton(Button.RotateLeft2);
                Menu.addMenuButton(Button.Rotateright2);
                return Menu;
            }
        });
        PageDisplay.addCommandButton(Button.RotatePages2, RibbonElementPriority.TOP);
        PageDisplay.startGroup();
        mins.setEnabled(false);
        DjvuRibbonComponents.Single = new JCommandButton("Single", getResizableIconFromResource("/images/single page24.png"));
        DjvuRibbonComponents.Single.setEnabled(false);

        DjvuRibbonComponents.Single.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (StatusBar.frame.FullBook.Continous) {
                    StatusBar.frame.FullBook.Switch_FullBookView(false);
                }
                djvubean.setPageLayout(DjVuBean.SINGLE);
                DjvuRibbonComponents.Single.getActionModel().setSelected(true);
                DjvuRibbonComponents.Continous.getActionModel().setSelected(false);
                DjvuRibbonComponents.facing.getActionModel().setSelected(false);
                UpdatePageLayout(getPageLayout(djvubean));
            }
        });
        DjvuRibbonComponents.Continous = new JCommandButton("Continous", getResizableIconFromResource("/images/continous24.png"));
        DjvuRibbonComponents.Continous.setEnabled(false);
        DjvuRibbonComponents.Continous.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StatusBar.frame.FullBook.Switch_FullBookView(true);
                DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                DjvuRibbonComponents.Continous.getActionModel().setSelected(true);
                DjvuRibbonComponents.facing.getActionModel().setSelected(false);
                UpdatePageLayout(getPageLayout(djvubean));
            }
        });

        DjvuRibbonComponents.facing = new JCommandButton("facing", getResizableIconFromResource("/images/facing24.png"));
        DjvuRibbonComponents.facing.setEnabled(false);
        DjvuRibbonComponents.facing.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (StatusBar.frame.FullBook.Continous) {
                    StatusBar.frame.FullBook.Switch_FullBookView(false);
                }
                djvubean.setPageLayout(DjVuBean.BOOK);
                DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                DjvuRibbonComponents.Continous.getActionModel().setSelected(false);
                DjvuRibbonComponents.facing.getActionModel().setSelected(true);
                UpdatePageLayout(getPageLayout(djvubean));
            }
        });
        PageDisplay.addCommandButton(DjvuRibbonComponents.Single, RibbonElementPriority.MEDIUM);
        PageDisplay.addCommandButton(DjvuRibbonComponents.Continous, RibbonElementPriority.MEDIUM);
        PageDisplay.addCommandButton(DjvuRibbonComponents.facing, RibbonElementPriority.MEDIUM);
        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(PageDisplay.getControlPanel()));
        PageDisplay.setResizePolicies(resizePolicies);
        return PageDisplay;
    }

    /**
     * this method it called to Change The State of Components From DisEnable TO
     * Enable
     */
    public void EnableForDjvuButton() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                Button.Hand.setEnabled(true);
                Button.Select.setEnabled(true);
                Button.ZoomArea.setEnabled(true);
                Button.SnapShot.setEnabled(true);
                Button.BookMark.setEnabled(true);
                Button.Asize.setEnabled(true);
                Button.FitWidth.setEnabled(true);
                Button.Fitpage.setEnabled(true);
                Button.ZoomIn.setEnabled(true);
                Button.Zoomout.setEnabled(true);
                DjvuRibbonComponents.ZoomB.setEnabled(true);
                Button.RotateLeft.setEnabled(true);
                Button.RotateRight.setEnabled(true);
                // Button.TyperWriter.setEnabled(true);
                Button.Note.setEnabled(true);
                Button.shownote.setEnabled(true);
                print.setEnabled(true);
                TBprint.setEnabled(true);
//                Button.HighLight.setEnabled(true);
//                Button.Strikeout.setEnabled(true);
//                Button.UnderLine.setEnabled(true);
                if (djvubean.getPage() > 1) {
                    FirstPage.setEnabled(true);
                    prevPage.setEnabled(true);
                    GotoFirstPage.setEnabled(true);
                    GotoprevPage.setEnabled(true);
                } else {
                    FirstPage.setEnabled(false);
                    prevPage.setEnabled(false);
                    GotoFirstPage.setEnabled(false);
                    GotoprevPage.setEnabled(false);

                }
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                GoToArea.setEnabled(true);
                Button.FitVisable.setEnabled(true);
                Button.pencil.setEnabled(true);
                Button.Rect.setEnabled(true);
                Button.Oval.setEnabled(true);
                Button.Line.setEnabled(true);
                Button.Polygon.setEnabled(true);
                Button.Cloud.setEnabled(true);
                Button.arrow.setEnabled(true);
                Button.Eraser.setEnabled(true);
                Button.TWriter.setEnabled(true);
                Button.Callout.setEnabled(true);
                Button.TextBox.setEnabled(true);
                Button.Note2.setEnabled(true);
                Button.AreaHighLight.setEnabled(true);
                HighLight.setEnabled(true);
                UnderLine.setEnabled(true);
                UnderLine2.setEnabled(true);
                Strikeout.setEnabled(true);
                Strikeout2.setEnabled(true);
                Strikeout3.setEnabled(true);
                pageSelectBox.setEnabled(true);
                Button.ConvertBandZoom.setEnabled(true);
                Button.ConvertBandHand.setEnabled(true);
                Button.ConvertBandSelect.setEnabled(true);
                Zooms.setEnabled(true);
                jsldHort.setEnabled(true);
                SinglepageMode.setEnabled(true);
                continousMode.setEnabled(true);
                facingmode.setEnabled(true);
                Plus.setEnabled(true);
                mins.setEnabled(true);
                DjvuRibbonComponents.Single.setEnabled(true);
                DjvuRibbonComponents.Continous.setEnabled(true);
                DjvuRibbonComponents.facing.setEnabled(true);
                Button.RotatePages2.setEnabled(true);
                Button.ViewHand.setEnabled(true);
                Button.ViewZoom.setEnabled(true);
                Button.ViewSelect.setEnabled(true);
                Button.EditHand.setEnabled(true);
                Button.EditZoom.setEnabled(true);
                Button.EditSelect.setEnabled(true);
                Button.CommentHand.setEnabled(true);
                Button.CommentZoom.setEnabled(true);
                Button.CommentSelect.setEnabled(true);
                Button.Ocr.setEnabled(true);
                Button.Ocr2.setEnabled(true);

            }
        });
    }

    /**
     * this method it called to Change The State of Components From Enable TO
     * DisEnable
     */
    public void DisenableForDjvuButton() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Button.ViewHand.setEnabled(false);
                Button.Hand.setEnabled(false);
                Button.Select.setEnabled(false);
                Button.ZoomArea.setEnabled(false);
                Button.SnapShot.setEnabled(false);
                Button.Clib_Copy.setEnabled(false);
                Button.Clib_Cut.setEnabled(false);
                Button.Clib_Past.setEnabled(false);
                Button.Clib_Selectall.setEnabled(false);
                Button.BookMark.setEnabled(false);
                Button.Asize.setEnabled(false);
                Button.FitWidth.setEnabled(false);
                Button.Fitpage.setEnabled(false);
                print.setEnabled(false);
                TBprint.setEnabled(false);
                DjvuRibbonComponents.ZoomB.setEnabled(false);
                Button.FitVisable.setEnabled(false);
                Button.RotateLeft.setEnabled(false);
                Button.RotateRight.setEnabled(false);
//                Button.TyperWriter.setEnabled(false);
                Button.Note.setEnabled(false);
                Button.shownote.setEnabled(false);
                // Button.HighLight.setEnabled(false);
                // Button.Strikeout.setEnabled(false);
                // Button.UnderLine.setEnabled(false);
                FirstPage.setEnabled(false);
                LastPage.setEnabled(false);
                next.setEnabled(false);
                prev.setEnabled(false);
                nextPage.setEnabled(false);
                prevPage.setEnabled(false);
                Button.NextView.setEnabled(false);
                Button.prevview.setEnabled(false);
                pageSelectBox.setEnabled(false);
                GotoFirstPage.setEnabled(false);
                GotoLastPage.setEnabled(false);
                GotonextPage.setEnabled(false);
                GotoprevPage.setEnabled(false);
                GoToArea.setEnabled(false);
                Button.ConvertBandZoom.setEnabled(false);
                Button.ConvertBandHand.setEnabled(false);
                Button.ConvertBandSelect.setEnabled(false);
                Zooms.setEnabled(false);
                jsldHort.setEnabled(false);
                Plus.setEnabled(false);
                mins.setEnabled(false);
                SinglepageMode.setEnabled(false);
                continousMode.setEnabled(false);
                facingmode.setEnabled(false);
                Button.ZoomIn.setEnabled(false);
                Button.Zoomout.setEnabled(false);
                DjvuRibbonComponents.ZoomB.setSelectedItem("");
                Zooms.setText("");
                pageSelectBox.setSelectedItem("");
                GoToArea.setText("");
                Button.pencil.setEnabled(false);
                Button.Rect.setEnabled(false);
                Button.Oval.setEnabled(false);
                Button.Line.setEnabled(false);
                Button.Polygon.setEnabled(false);
                Button.Cloud.setEnabled(false);
                Button.arrow.setEnabled(false);
                Button.Eraser.setEnabled(false);
                Button.TWriter.setEnabled(false);
                Button.Callout.setEnabled(false);
                Button.TextBox.setEnabled(false);
                Button.AreaHighLight.setEnabled(false);
                HighLight.setEnabled(false);
                UnderLine.setEnabled(false);
                UnderLine2.setEnabled(false);
                Button.Note2.setEnabled(false);
                Strikeout.setEnabled(false);
                Strikeout2.setEnabled(false);
                Strikeout3.setEnabled(false);
                DjvuRibbonComponents.Single.setEnabled(false);
                DjvuRibbonComponents.Continous.setEnabled(false);
                DjvuRibbonComponents.facing.setEnabled(false);
                Button.RotatePages2.setEnabled(false);
                Button.ViewHand.setEnabled(false);
                Button.ViewZoom.setEnabled(false);
                Button.ViewSelect.setEnabled(false);
                Button.EditHand.setEnabled(false);
                Button.EditZoom.setEnabled(false);
                Button.EditSelect.setEnabled(false);
                Button.CommentHand.setEnabled(false);
                Button.CommentZoom.setEnabled(false);
                Button.CommentSelect.setEnabled(false);
                Findnext.setEnabled(false);
                FindPrev.setEnabled(false);
                Button.Ocr.setEnabled(false);
                Button.Ocr2.setEnabled(false);
                AreaSearch.setEnabled(false);
            }
        });

    }

    /**
     * this method to initial the DjvuBean OBject
     *
     * @param bean
     */
    public void setbean(DjVuBean bean) {
        try {
            djvubean = bean;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //       System.out.println(djvubean + "///////////////**********************************************/////////");
                    if (tabbedPane.getTabCount() != 0) {
                        String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                        if (!tabName.equals("GetStart")) {
                            EnableForDjvuButton();
                            LoadDjvuPages(djvubean);
                            updateMode(djvubean.getMode());
                            UpdatePageLayout(getPageLayout(djvubean));
                            UpdateZoom(getZoomMode(djvubean));
                            DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                            Zooms.setText(djvubean.getZoom());
                            GoToArea.setText("" + djvubean.getPage());

                        } else {

                            DisenableForDjvuButton();
                        }
                    }
                }
            });
        } catch (Exception exp) {
            exp.printStackTrace(DjVuOptions.err);
            System.gc();
        }
    }

    /**
     *
     * this method called when Bean is set
     */
    private void LoadDjvuPages(final DjVuBean djvubean) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                pageSelectBox.removeAllItems();
                documentSize = djvubean.getDocumentSize();
                for (int i = 0; i < documentSize;) {
                    pageSelectBox.addItem(++i);
                }

            }
        });
    }

    /**
     * this method to change the mode of Command Buttons such if hand Button is
     * Active change the state of it when the mode change;
     *
     * @param mode
     */
    public void updateMode(final int mode) {
//    try { throw new Exception("updateMode("+mode+")"); } catch(final Throwable exp) { exp.printStackTrace(DjVuOptions.err); }
        switch (mode) {
            case DjVuBean.PAN_MODE:
                Button.Hand.getActionModel().setSelected(true);
                Button.ConvertBandHand.getActionModel().setSelected(true);
                Button.ViewHand.getActionModel().setSelected(true);
                Button.EditHand.getActionModel().setSelected(true);
                Button.CommentHand.getActionModel().setSelected(true);
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                if (Button.Select.getActionModel().isSelected()
                        || Button.ConvertBandSelect.getActionModel().isSelected()
                        || Button.ViewSelect.getActionModel().isSelected()
                        || Button.CommentSelect.getActionModel().isSelected()
                        || Button.EditSelect.getActionModel().isSelected()) {
                    Button.Select.getActionModel().setSelected(false);
                    Button.ConvertBandSelect.getActionModel().setSelected(false);
                    Button.ViewSelect.getActionModel().setSelected(false);
                    Button.EditSelect.getActionModel().setSelected(false);
                    Button.CommentSelect.getActionModel().setSelected(false);
                }
                if (Button.ZoomArea.getActionModel().isSelected()) {
                    Button.ZoomArea.getActionModel().setSelected(false);
                }

                break;
            case DjVuBean.TEXT_MODE:
                Button.Select.getActionModel().setSelected(true);
                Button.ConvertBandSelect.getActionModel().setSelected(true);
                Button.ViewSelect.getActionModel().setSelected(true);
                Button.EditSelect.getActionModel().setSelected(true);
                Button.CommentSelect.getActionModel().setSelected(true);
                djvubean.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                if (Button.Hand.getActionModel().isSelected()
                        || Button.ConvertBandHand.getActionModel().isSelected()
                        || Button.ViewHand.getActionModel().isSelected()
                        || Button.CommentHand.getActionModel().isSelected()
                        || Button.EditHand.getActionModel().isSelected()) {
                    Button.Hand.getActionModel().setSelected(false);
                    Button.ConvertBandHand.getActionModel().setSelected(false);
                    Button.ViewHand.getActionModel().setSelected(false);
                    Button.EditHand.getActionModel().setSelected(false);
                    Button.CommentHand.getActionModel().setSelected(false);

                }
                if (Button.ZoomArea.getActionModel().isSelected()) {
                    Button.ZoomArea.getActionModel().setSelected(false);
                }
                break;
            case DjVuBean.ZOOM_MODE:
                Button.ZoomArea.getActionModel().setSelected(true);
                //  Cursor c = toolkit.createCustomCursor(getImageFromResource("/images/search.png"), new Point(djvubean.getX(), djvubean.getY()), "img");
                // djvubean.setCursor(c);
                if (Button.Hand.getActionModel().isSelected()
                        || Button.ConvertBandHand.getActionModel().isSelected()
                        || Button.ViewHand.getActionModel().isSelected()
                        || Button.CommentHand.getActionModel().isSelected()
                        || Button.EditHand.getActionModel().isSelected()) {
                    Button.Hand.getActionModel().setSelected(false);
                    Button.ConvertBandHand.getActionModel().setSelected(false);
                    Button.ViewHand.getActionModel().setSelected(false);
                    Button.EditHand.getActionModel().setSelected(false);
                    Button.CommentHand.getActionModel().setSelected(false);

                }
                if (Button.Select.getActionModel().isSelected()
                        || Button.ConvertBandSelect.getActionModel().isSelected()
                        || Button.ViewSelect.getActionModel().isSelected()
                        || Button.CommentSelect.getActionModel().isSelected()
                        || Button.EditSelect.getActionModel().isSelected()) {
                    Button.Select.getActionModel().setSelected(false);
                    Button.ConvertBandSelect.getActionModel().setSelected(false);
                    Button.ViewSelect.getActionModel().setSelected(false);
                    Button.EditSelect.getActionModel().setSelected(false);
                    Button.CommentSelect.getActionModel().setSelected(false);
                }
                break;
        }
    }

    public void UpdateZoom(int zoom) {
        switch (zoom) {
            case 100:
                Button.Asize.getActionModel().setSelected(true);
                if (Button.Fitpage.getActionModel().isSelected() || Button.FitWidth.getActionModel().isSelected() || Button.FitVisable.getActionModel().isSelected()) {
                    Button.Fitpage.getActionModel().setSelected(false);
                    Button.FitVisable.getActionModel().setSelected(false);
                    Button.FitWidth.getActionModel().setSelected(false);

                }
                break;
            case 53:
                Button.Fitpage.getActionModel().setSelected(true);
                if (Button.Asize.getActionModel().isSelected() || Button.FitWidth.getActionModel().isSelected() || Button.FitVisable.getActionModel().isSelected()) {
                    Button.Asize.getActionModel().setSelected(false);
                    Button.FitVisable.getActionModel().setSelected(false);
                    Button.FitWidth.getActionModel().setSelected(false);

                }
                break;
            case 146:
                Button.FitVisable.getActionModel().setSelected(true);
                if (Button.Fitpage.getActionModel().isSelected() || Button.FitWidth.getActionModel().isSelected() || Button.Asize.getActionModel().isSelected()) {
                    Button.Fitpage.getActionModel().setSelected(false);
                    Button.Asize.getActionModel().setSelected(false);
                    Button.FitWidth.getActionModel().setSelected(false);

                }
                break;
            case 115:
                Button.FitWidth.getActionModel().setSelected(true);
                if (Button.Fitpage.getActionModel().isSelected() || Button.Asize.getActionModel().isSelected() || Button.FitVisable.getActionModel().isSelected()) {
                    Button.Fitpage.getActionModel().setSelected(false);
                    Button.FitVisable.getActionModel().setSelected(false);
                    Button.Asize.getActionModel().setSelected(false);

                }
                break;
            default:
                Button.Fitpage.getActionModel().setSelected(false);
                Button.FitVisable.getActionModel().setSelected(false);
                Button.Asize.getActionModel().setSelected(false);
                Button.FitWidth.getActionModel().setSelected(false);
        }
    }

//            case DjVuBean.SELECT_MODE:
//                selectMode.setSelected(true);
//
//                if (panMode.isSelected()) {
//                    panMode.setSelected(false);
//                }
//
//                if (textMode.isSelected()) {
//                    textMode.setSelected(false);
//                }
//
//                if (zoomMode.isSelected()) {
//                    zoomMode.setSelected(false);
//                }
//
//                setZoomEnabled(true);
//
//                break;
    private int getZoomMode(DjVuBean bean) {
        int ZoomMode = Integer.parseInt(bean.getZoom().substring(0, bean.getZoom().length() - 1));
        return ZoomMode;
    }

    public Image getImageFromResource(String resource) {
        Image image = null;
        try {
            toolkit = Toolkit.getDefaultToolkit();
            System.err.println(resource.substring(1));
            image = toolkit.getImage(DjvuComponents.class.getClass().getResource(resource));
            // Image image = new ImageIcon(getClass().getClassLoader().getResource(resource.substring(1))).getImage();

        } catch (Exception e) {

        }
        return image;
    }

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
}
