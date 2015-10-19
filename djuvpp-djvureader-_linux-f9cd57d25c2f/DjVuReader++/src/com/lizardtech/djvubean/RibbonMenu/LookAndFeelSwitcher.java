package com.lizardtech.djvubean.RibbonMenu;

import com.lizardtech.djview.frame.DjvuStart;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;

public class LookAndFeelSwitcher {

    static class LookAndFeelInfoObj {

        LookAndFeelInfo lafInfo;

        String displayName;

        public LookAndFeelInfoObj(LookAndFeelInfo lafInfo, String displayName) {
            this.lafInfo = lafInfo;
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public interface LocaleCallback {

        public void onLocaleSelected(Locale selected);
    }

    public static JComboBox getLookAndFeelSwitcher(final JFrame frame) {
//		LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
//		LookAndFeelInfoObj[] lafObjs = new LookAndFeelInfoObj[lafs.length];
//		for (int i = 0; i < lafs.length; i++) {
//			lafObjs[i] = new LookAndFeelInfoObj(lafs[i], lafs[i].getName());
//		}
        String items[] = {"Change Skin", "GraphiteGlass", "OfficeBlue2007", "DustCoffee", "Autumn"};
        final JComboBox result = new JComboBox(items);
//		for (int i = 0; i < lafs.length; i++) {
//			if (lafs[i].getName().equals(UIManager.getLookAndFeel().getName())) {
//				result.setSelectedIndex(i);
//				break;
//			}
//		}

        result.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        boolean wasDecoratedByOS = !frame.isUndecorated();
                        try {
							//LookAndFeelInfoObj selected = (LookAndFeelInfoObj) result
                            //		.getSelectedItem();
                            switch (result.getSelectedIndex()) {
                                case 1:
                                    UIManager.setLookAndFeel(new SubstanceGraphiteAquaLookAndFeel());
                                    SwingUtilities.updateComponentTreeUI(frame);
                                   
                                    
                                    break;
                                case 2:
                                    UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
                                    SwingUtilities.updateComponentTreeUI(frame);
                                    // tabbedPane.setUI(new JhromeTabbedPaneUI());
                                    break;
                                //  new DjvuStart().tabbedPane.setUI(new JhromeTabbedPaneUI());
                                case 3:
                                    UIManager.setLookAndFeel(new SubstanceDustCoffeeLookAndFeel());
                                    SwingUtilities.updateComponentTreeUI(frame);
                                    //tabbedPane.setUI(new JhromeTabbedPaneUI());
                                    break;
                                case 4:
                                    UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
                                    SwingUtilities.updateComponentTreeUI(frame);
                                    //tabbedPane.setUI(new JhromeTabbedPaneUI());
                                    break;
                            }
                            boolean canBeDecoratedByLAF = UIManager
                                    .getLookAndFeel()
                                    .getSupportsWindowDecorations();
                            if (canBeDecoratedByLAF == wasDecoratedByOS) {
                                boolean wasVisible = frame.isVisible();

                                frame.setVisible(false);
                                frame.dispose();
                                if (!canBeDecoratedByLAF) {
								// see the java docs under the method
                                    // JFrame.setDefaultLookAndFeelDecorated(boolean
                                    // value) for description of these 2 lines:
                                    frame.setUndecorated(false);
                                    frame.getRootPane().setWindowDecorationStyle(
                                            JRootPane.NONE);

                                } else {
                                    frame.setUndecorated(true);
                                    frame.getRootPane().setWindowDecorationStyle(
                                            JRootPane.FRAME);
                                }
                                frame.setVisible(wasVisible);
                                wasDecoratedByOS = !frame.isUndecorated();
                            }

                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(LookAndFeelSwitcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                ;
            }
        );
        }
                        

		
	});
                return result;
    }
}
