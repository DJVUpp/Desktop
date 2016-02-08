/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvubean.RibbonMenu;

import com.lizardtech.djvu.DjVuOptions;
import com.lizardtech.djvubean.DjVuBean;
import static com.lizardtech.djvubean.DjVuBean.nscale;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.JFlowRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

/**
 *
 * @author niessuh
 */
// FIXME: Delete.
public class DjvuComponents {

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

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Font font = new Font("Dialog", Font.PLAIN, 12);

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
            }
        });
        Button.Z100 = new JCommandMenuButton("100%", null);
        Button.Z100.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z25 = new JCommandMenuButton("25%", null);
        Button.Z25.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM25);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z50 = new JCommandMenuButton("50%", null);
        Button.Z50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM50);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z125 = new JCommandMenuButton("125%", null);
        Button.Z125.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("125%");
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z200 = new JCommandMenuButton("200%", null);
        Button.Z200.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z600 = new JCommandMenuButton("600%", null);
        Button.Z600.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM200);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z800 = new JCommandMenuButton("800%", null);
        Button.Z800.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM800);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.Z400 = new JCommandMenuButton("400%", null);
        Button.Z400.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM400);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource(new ImageIcon("/images/fit page48.png").toString()));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                modeNum = 52;
                UpdateZoom(modeNum);
            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource(new ImageIcon("/images/fit width48.png").toString()));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                modeNum = 115;
                UpdateZoom(modeNum);
            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource(new ImageIcon("/images/fit visible48.png").toString()));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                modeNum = 146;
                UpdateZoom(modeNum);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource(new ImageIcon("/images/actual size48.png").toString()));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
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

    /**
     * this method to Create The UIOptionBand and return it to add in the Ribbon
     *
     * @param Djvu
     * @return
     */
    public JRibbonBand getUiOptionsBand() {
        JRibbonBand Uioptions = new JRibbonBand("UI Options", null);
        Uioptions.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesRestrictive(Uioptions));
        Uioptions.startGroup();
        Button.Switch = new JCommandButton("Change "
                + "ToolBar Mode", null);
        Uioptions.addCommandButton(Button.Switch, RibbonElementPriority.TOP);
        Uioptions.startGroup();

        List<RibbonBandResizePolicy> resizePolicies = new ArrayList<RibbonBandResizePolicy>();
        resizePolicies.add(new CoreRibbonResizePolicies.Mirror(Uioptions.getControlPanel()));
        Uioptions.setResizePolicies(resizePolicies);
        return Uioptions;
    }

    private float stroke = 3f;
    Graphics2D editG2D = null;

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
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
                // TODO: rotate the page.
                // com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(-90);

            }
        });

        Button.Rotateright2 = new JCommandMenuButton("Rotate "
                + "Right", getResizableIconFromResource("/images/rotate right48.png"));

        Button.Rotateright2.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Button.Rotateright2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //  djvubean.setRotationDegrees(90f);
                //  TODO: rotate the page.

                //  com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(90);
            }
        });
        Button.RotatePages2 = new JCommandButton("Rotate"
                + " pages", getResizableIconFromResource("/images/rotate left48.png"));
        Button.RotatePages2.setEnabled(false);

        Button.RotatePages2.setCommandButtonKind(JCommandButton.CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);

        Button.RotatePages2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //  TODO: rotate the page.
                //  com.lizardtech.djview.frame.StatusBar.frame.FullBook.rotate(-90);

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
        DjvuRibbonComponents.Single = new JCommandButton("Single", getResizableIconFromResource("/images/single page24.png"));
        DjvuRibbonComponents.Single.setEnabled(false);

        DjvuRibbonComponents.Single.addActionListener((ActionEvent e) -> {
            djvubean.setPageLayout(DjVuBean.SINGLE);
            DjvuRibbonComponents.Single.getActionModel().setSelected(true);
            DjvuRibbonComponents.Continous.getActionModel().setSelected(false);
            DjvuRibbonComponents.facing.getActionModel().setSelected(false);
        });
        DjvuRibbonComponents.Continous = new JCommandButton("Continous", getResizableIconFromResource("/images/continous24.png"));
        DjvuRibbonComponents.Continous.setEnabled(false);
        DjvuRibbonComponents.Continous.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                DjvuRibbonComponents.Continous.getActionModel().setSelected(true);
                DjvuRibbonComponents.facing.getActionModel().setSelected(false);
            }
        });

        DjvuRibbonComponents.facing = new JCommandButton("facing", getResizableIconFromResource("/images/facing24.png"));
        DjvuRibbonComponents.facing.setEnabled(false);
        DjvuRibbonComponents.facing.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageLayout(DjVuBean.BOOK);
                DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                DjvuRibbonComponents.Continous.getActionModel().setSelected(false);
                DjvuRibbonComponents.facing.getActionModel().setSelected(true);
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
//                Button.HighLight.setEnabled(true);
//                Button.Strikeout.setEnabled(true);
//                Button.UnderLine.setEnabled(true);
                Button.FitVisable.setEnabled(true);
                Button.ConvertBandZoom.setEnabled(true);
                Button.ConvertBandHand.setEnabled(true);
                Button.ConvertBandSelect.setEnabled(true);
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
                DjvuRibbonComponents.ZoomB.setEnabled(false);
                Button.FitVisable.setEnabled(false);
                Button.RotateLeft.setEnabled(false);
                Button.RotateRight.setEnabled(false);
//                Button.TyperWriter.setEnabled(false);
                // Button.HighLight.setEnabled(false);
                // Button.Strikeout.setEnabled(false);
                // Button.UnderLine.setEnabled(false);
                Button.ConvertBandZoom.setEnabled(false);
                Button.ConvertBandHand.setEnabled(false);
                Button.ConvertBandSelect.setEnabled(false);
                Button.ZoomIn.setEnabled(false);
                Button.Zoomout.setEnabled(false);
                DjvuRibbonComponents.ZoomB.setSelectedItem("");
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
                if (Button.ZoomArea.getActionModel().isSelected()) {
                    Button.ZoomArea.getActionModel().setSelected(false);
                }

                break;
            case DjVuBean.TEXT_MODE:
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
