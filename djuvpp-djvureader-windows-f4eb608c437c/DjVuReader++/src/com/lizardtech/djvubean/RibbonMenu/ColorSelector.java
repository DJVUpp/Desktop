/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lizardtech.djvubean.RibbonMenu;

import CreatDjVu.NewDjvu;
import CreatDjVu.NewDjvuFromClib;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.common.popup.JColorSelectorPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;

/**
 *
 * @author niessuh
 */
public class ColorSelector {
    final JCheckBox hasTheme = new JCheckBox("theme");
		
       final JCheckBox hasStandard = new JCheckBox("standard");
		
       final JCheckBox hasRecent = new JCheckBox("recent");
	public static Color bColor =Color.BLACK;	
       /*
     *THIS iner Class it use to paint icons for COLOR commend Button
     *
     */

         protected static class ColorIcon implements ResizableIcon {
		int w;
		int h;
		Color color;

		public ColorIcon(Color color) {
			this.color = color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(color);
			g.fillRect(x, y, w, h);
			g.setColor(Color.GRAY);
			g.drawRect(x, y, w - 1, h - 1);
		}

		@Override
		public int getIconWidth() {
			return w;
		}

		@Override
		public int getIconHeight() {
			return h;
		}

		@Override
		public void setDimension(Dimension newDimension) {
			w = newDimension.width;
			h = newDimension.height;
		}
	}
       
       /*this method to return the Color CommandButton 
       */
       public JCommandButton getCommandbuttonColor(){
                
                 hasTheme.setSelected(true);
                 hasStandard.setSelected(true);
                 hasRecent.setSelected(true);
		final ColorIcon colorIcon = new ColorIcon(bColor);

		JCommandButton jcb = new JCommandButton(colorIcon);
		jcb.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
		jcb.setDisplayState(CommandButtonDisplayState.SMALL);
		jcb.setFlat(false);
		final JColorSelectorPopupMenu.ColorSelectorCallback callback = new JColorSelectorPopupMenu.ColorSelectorCallback() {
			@Override
			public void onColorSelected(Color color) {
				//Actions
			}

			@Override
			public void onColorRollover(Color color) {
				if (color != null) {
					//Actions
                                    if(NewDjvu.textArea!=null)
                                    NewDjvu.textArea.setForeground(color);
                                     if(NewDjvuFromClib.textAreaClip!=null)
                                    NewDjvuFromClib.textAreaClip.setForeground(color);
                                      bColor = color;
                                     
//				Editcont.setBackground(bColor);
//				colorIcon.setColor(bColor);
				} else {
					//Actions
					colorIcon.setColor(bColor);
				}
			}
		};
              
		final Color defaultPanelColor = Color.gray;
		jcb.setPopupCallback(new PopupPanelCallback() {
			@Override
			public JPopupPanel getPopupPanel(JCommandButton commandButton) {
				JColorSelectorPopupMenu result = new JColorSelectorPopupMenu(
						callback);
				final JCommandMenuButton automaticColor = new JCommandMenuButton(
						"Automatic",
						new ColorIcon(defaultPanelColor));
				automaticColor.getActionModel().addActionListener(
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								callback.onColorSelected(defaultPanelColor);
								JColorSelectorPopupMenu
										.addColorToRecentlyUsed(defaultPanelColor);
							}
						});
				automaticColor.getActionModel().addChangeListener(
						new ChangeListener() {
							boolean wasRollover = automaticColor
									.getActionModel().isRollover();

							@Override
							public void stateChanged(ChangeEvent e) {
								boolean isRollover = automaticColor
										.getActionModel().isRollover();
								if (wasRollover && !isRollover) {
									callback.onColorRollover(null);
								}
								if (!wasRollover && isRollover) {
									callback.onColorRollover(Color.black);
								}
								wasRollover = isRollover;
							}
						});
				result.addMenuButton(automaticColor);

				if (hasTheme.isSelected()) {
					result.addColorSectionWithDerived("Themes Colors",
							new Color[] { new Color(255, 255, 255),
									new Color(0, 0, 0),
									new Color(160, 160, 160),
									new Color(16, 64, 128),
									new Color(80, 128, 192),
									new Color(180, 80, 80),
									new Color(160, 192, 80),
									new Color(128, 92, 160),
									new Color(80, 160, 208),
									new Color(255, 144, 64) });
				}
				if (hasStandard.isSelected()) {
					result.addColorSection("Resent Colors",
							new Color[] { new Color(140, 0, 0),
									new Color(253, 0, 0),
									new Color(255, 160, 0),
									new Color(255, 255, 0),
									new Color(144, 240, 144),
									new Color(0, 128, 0),
									new Color(160, 224, 224),
									new Color(0, 0, 255), new Color(0, 0, 128),
									new Color(128, 0, 128) });
				}
				if (hasRecent.isSelected()) {
					result.addRecentSection("Recent Colors");
				}

				JCommandMenuButton moreButton = new JCommandMenuButton(
						"More Colors..",
						null);
				moreButton.getActionModel().addActionListener(
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
//								SwingUtilities.invokeLater(new Runnable() {
//									@Override
//									public void run() {
//                                                                   
//										Color color = JColorChooser.showDialog(
//                                                                                  
//												 new DjvuStart().getDjvu(),
//												"Color chooser", bColor);
//										if (color != null) {
//											callback.onColorSelected(color);
//											JColorSelectorPopupMenu
//													.addColorToRecentlyUsed(color);
//										}
//									}
//								});
							}
						});
				result.addMenuButton(moreButton);
				return result;
			}
		});
                return  jcb;
       }
}
