/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djview.frame;

//import static com.lizardtech.djview.frame.DjvuStart.getResizableIconFromResource;
import com.lizardtech.djvubean.DjVuBean;
import static com.lizardtech.djvubean.DjVuBean.nscale;
import com.lizardtech.djvubean.RibbonMenu.DjvuComponents;
import static com.lizardtech.djvubean.RibbonMenu.DjvuComponents.djvubean;
import com.lizardtech.djvubean.RibbonMenu.DjvuRibbonComponents;
import static com.lizardtech.djvubean.RibbonMenu.DjvuRibbonComponents.*;
import com.lizardtech.djvubean.RibbonMenu.RibbonGetIcon;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;

/**
 *
 * @author niessuh
 */
public class StatusBar implements RibbonGetIcon{

    private static final DjvuRibbonComponents Button = new DjvuRibbonComponents();
    private static final DjvuComponents Band = new DjvuComponents();

    public static JCommandButton Zooms;
    // public static DjVuBean djvubean;
    JPanel StatusPanel;
    public static JComboBox Page_Select;
    JPanel Pages;
    public static JSlider jsldHort;
    public JPanel SLiderPanel;
    public static JCommandButton Plus;
    public static JCommandButton mins;
    public static JCommandButton continousMode;
    public static JCommandButton facingmode;
    public static JCommandButton SinglepageMode;
    public static JPanel BookModepanel;
    public static JLabel ConvertStatus;
    public static JLabel ConvertStatus2;
    public static Frame frame;
    public  boolean first=true;

    public StatusBar() {

        Page_Select = new JComboBox();
        Page_Select.setEnabled(false);
        ConvertStatus=new JLabel();
        ConvertStatus2=new JLabel("please Wait");
        ConvertStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BAR.gif")));
        ConvertStatus.setVisible(false);
        ConvertStatus2.setVisible(false);
        this.Pages = new JPanel();
        this.StatusPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        StatusPanel.setPreferredSize(new Dimension(600, 36));
     //    StatusPanel.  setLayout(new GridBagLayout());
          
       //   StatusPanel. setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 10, 10, 10)));
        StatusPanel.setMaximumSize(StatusPanel.getPreferredSize());
        StatusPanel.setMinimumSize(StatusPanel.getPreferredSize());
        Page_Select = new JComboBox();
        Page_Select.setEnabled(false);
        Zooms = new JCommandButton("");
        Zooms.setEnabled(false);
        jsldHort = new JSlider(JSlider.HORIZONTAL);
        jsldHort.setPreferredSize(new Dimension(120, 25));
        jsldHort.setMaximumSize(jsldHort.getPreferredSize());
        jsldHort.setMinimumSize(jsldHort.getPreferredSize());
        SLiderPanel = new JPanel();
        Plus = new JCommandButton(getResizableIconFromResource("/images/plus.png"));
        Plus.setEnabled(false);
        mins = new JCommandButton(getResizableIconFromResource("/images/minus.png"));
        mins.setEnabled(false);
        SinglepageMode = new JCommandButton(getResizableIconFromResource("/images/single page24.png"));
        SinglepageMode.setEnabled(false);
        continousMode = new JCommandButton(getResizableIconFromResource("/images/continous24.png"));
        continousMode.setEnabled(false);
        facingmode = new JCommandButton(getResizableIconFromResource("/images/facing24.png"));
        facingmode.setEnabled(false);
        jsldHort.setMaximum(1200);
        jsldHort.setMinimum(25);
        jsldHort.setEnabled(false);
        SLiderPanel.add(mins);
        SLiderPanel.add(jsldHort);
        SLiderPanel.add(Plus);
        BookModepanel = new JPanel();
        BookModepanel.add(SinglepageMode);
        BookModepanel.add(continousMode);
        BookModepanel.add(facingmode);

    }

    static String ZoomFormat;

    public JPanel getStatusBar() {

        SinglepageMode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if( frame.FullBook.Continous)
                        frame.FullBook.Switch_FullBookView(false);
                djvubean.setPageLayout(DjVuBean.SINGLE);
                SinglepageMode.getActionModel().setSelected(true);
//                facingmode.getActionModel().setSelected(false);
//                Continous.getActionModel().setSelected(false);
                UpdatePageLayout(getPageLayout(djvubean));
            }
        });
        facingmode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                 if( frame.FullBook.Continous)
                        frame.FullBook.Switch_FullBookView(false);
                djvubean.setPageLayout(DjVuBean.BOOK);
                UpdatePageLayout(getPageLayout(djvubean));
            }
        });
       
        continousMode.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                                 frame.FullBook.Switch_FullBookView(true);  
                                 UpdatePageLayout(getPageLayout(djvubean));
            }
        });
        Plus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 1200) {
                    Plus.setEnabled(false);
                } else {
                    Plus.setEnabled(true);
                }

                djvubean.setZoom(DjVuBean.ZOOM_IN);
                ZoomFormat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(ZoomFormat);
                jsldHort.setValue(nscale);

            }
        });
        mins.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nscale == 25) {
                    mins.setEnabled(false);
                } else {
                    mins.setEnabled(true);
                }
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
                ZoomFormat = nscale + "%";
                DjvuRibbonComponents.ZoomB.setSelectedItem(ZoomFormat);

                jsldHort.setValue(nscale);
            }
        });

        Zooms.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
        Zooms.setDisplayState(CommandButtonDisplayState.MEDIUM);
        Zooms.setText("");
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
        Button.FP = new JCommandMenuButton("Fit page", getResizableIconFromResource("/images/fit page48.png"));
        Button.FP.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(52);

            }
        });
        Button.FW = new JCommandMenuButton("Fit Width", getResizableIconFromResource("/images/fit width48.png"));
        Button.FW.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom("115%");
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(115);

            }
        });
        Button.FV = new JCommandMenuButton("Fit Visible  ", getResizableIconFromResource("/images/fit visible48.png"));
        Button.FV.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
                DjvuRibbonComponents.ZoomB.setSelectedItem("146%");
                jsldHort.setValue(146);

            }
        });
        Button.AS = new JCommandMenuButton("Actual Size", getResizableIconFromResource("/images/actual size48.png"));
        Button.AS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setZoom(DjVuBean.ZOOM100);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
                jsldHort.setValue(100);

            }
        });
        Zooms.setPopupCallback(new PopupPanelCallback() {
            public JPopupPanel getPopupPanel(JCommandButton commandButton) {
                JCommandPopupMenu pan = new JCommandPopupMenu();
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
        jsldHort.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = jsldHort.getValue();

                String ZoomValue = value + "%";
                DjVuBean.ZOOMSlider = ZoomValue;
                djvubean.setZoom(DjVuBean.ZOOMSlider);
                Zooms.setText(djvubean.getZoom());
                DjvuRibbonComponents.ZoomB.setSelectedItem(djvubean.getZoom());
            }
        });
       StatusPanel.add(ConvertStatus2);
       StatusPanel.add(ConvertStatus);
       StatusPanel.add(new JLabel("                                                                                              "));
        StatusPanel.add(new PageSelect().getPageSelectPanel());
        StatusPanel.add(new JLabel("                                  "));
        StatusPanel.add(BookModepanel);
        StatusPanel.add(Zooms);
        StatusPanel.add(SLiderPanel);
        return StatusPanel;
    }

    public static void UpdatePageLayout(int layoyt) {
        switch (layoyt) {
            case 1:
                SinglepageMode.getActionModel().setSelected(true);
                DjvuRibbonComponents.Single.getActionModel().setSelected(true);
                if (continousMode.getActionModel().isSelected()
                        || facingmode.getActionModel().isSelected()
                        || DjvuRibbonComponents.Continous.getActionModel().isSelected()
                        || DjvuRibbonComponents.facing.getActionModel().isSelected()) {
                    facingmode.getActionModel().setSelected(false);
                    continousMode.getActionModel().setSelected(false);
                    DjvuRibbonComponents.Continous.getActionModel().setSelected(false);
                    DjvuRibbonComponents.facing.getActionModel().setSelected(false);

                }
                break;
            case 2:
                continousMode.getActionModel().setSelected(true);
                Continous.getActionModel().setSelected(true);
                if (SinglepageMode.getActionModel().isSelected()
                        || facingmode.getActionModel().isSelected()
                        || DjvuRibbonComponents.Single.getActionModel().isSelected()
                        || DjvuRibbonComponents.facing.getActionModel().isSelected()) {
                    facingmode.getActionModel().setSelected(false);
                    SinglepageMode.getActionModel().setSelected(false);
                    DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                    DjvuRibbonComponents.facing.getActionModel().setSelected(false);

                }
                break;
            case 3:
                facingmode.getActionModel().setSelected(true);
                facing.getActionModel().setSelected(true);
                if (SinglepageMode.getActionModel().isSelected()
                        || continousMode.getActionModel().isSelected()
                        || DjvuRibbonComponents.Single.getActionModel().isSelected()
                        || DjvuRibbonComponents.Continous.getActionModel().isSelected()) {
                    SinglepageMode.getActionModel().setSelected(false);
                    continousMode.getActionModel().setSelected(false);
                    DjvuRibbonComponents.Single.getActionModel().setSelected(false);
                    DjvuRibbonComponents.Continous.getActionModel().setSelected(false);

                }

        }
    }
    static int pagelayout;

    public static int getPageLayout(DjVuBean Bean) {
      
        String PL = Bean.getPageLayout();
        if (PL.equals("single")) {

            pagelayout = 1;
        } else if (PL.equals("book")) {
            pagelayout = 3;

        } else {
            pagelayout = 2;
        }
        return pagelayout;
    }

    public ResizableIcon getResizableIconFromResource(String resource) {
           ResizableIcon icon = ImageWrapperResizableIcon
                .getIcon(this.getClass().getResource(resource), new Dimension(16, 16));
        return icon;
    }
    
    private Boolean switchFlag(Boolean X) {
        if (X == false) {
            return true;
        } else {
            return false;
        }
    }
}
