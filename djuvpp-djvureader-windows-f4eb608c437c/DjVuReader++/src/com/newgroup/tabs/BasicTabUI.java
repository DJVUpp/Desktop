/*
 Copyright 2012 James Edwards

 This file is part of Jhrome.

 Jhrome is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Jhrome is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with Jhrome.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.newgroup.tabs;

import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.unsavedbook;
import com.newgroup.SwingUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.newgroup.tabs.jhrome.JhromeTabbedPaneUI;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * The UI for a {@link Tab}.
 *
 * @author andy.edwards
 */
public class BasicTabUI extends TabUI {

    public static final String CLOSE_BUTTON_LISTENER = "sexydock.closeButtonListener";

    public static final String CLOSE_BUTTON_VISIBLE = "sexydock.closeButtonVisible";

    public BasicTabUI() {
        init();
    }

    Tab tab;

    JLabel label;
    Component displayedTabComponent;
    JButton closeButton;

    Color disabledForeground = Color.GRAY;
    Color rolloverForeground = new Color(80, 80, 80);
    Color unselectedForeground = new Color(80, 80, 80);
    Color selectedForeground = Color.BLACK;

    Color disabledBackground = new Color(191, 191, 202);
    Color rolloverBackground = Color.RED;					// new Color( 231 , 231 , 239 );
    Color unselectedBackground = new Color(211, 211, 222);
    Color selectedBackground = new Color(248, 248, 248);

    PropertyChangeHandler propertyChangeHandler = new PropertyChangeHandler();

    private void init() {
        label = new JLabel();
        closeButton = new JButton("X");

        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        label.setForeground(unselectedForeground);
        label.setOpaque(false);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTabbedPane tabbedPane = SwingUtils.getJTabbedPaneAncestor(tab);
                ITabCloseButtonListener closeButtonListener = PropertyGetter.get(ITabCloseButtonListener.class, tab, CLOSE_BUTTON_LISTENER, JTabbedPane.class, JhromeTabbedPaneUI.TAB_CLOSE_BUTTON_LISTENER);
                int index = tabbedPane.indexOfComponent(tab.getContent());
                File del =new File("C:\\DjVu++Task\\" + tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
                if (closeButtonListener != null) {

                    if (index >= 0 && !unsavedbook.contains(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))) {
                        closeButtonListener.tabCloseButtonPressed(tabbedPane, index);
                    } else if (unsavedbook.contains(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))) {
                        String[] buttons = {"YES", "NO"};
                        int out = JOptionPane.showOptionDialog(null, "Do you want save before close ?", "Close",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);

                        if (out == 0) {
                            FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save DjVu", FileDialog.SAVE);
                            SaveDjVu.setVisible(true);
                            String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                            String path1 = path + ".djvu";
                            Path p1 = Paths.get("C:\\DjVu++Task\\" + tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
                            Path p2 = Paths.get(path1);
                            
                            if (SaveDjVu.getFile() != null) {
                                try {
                                    Files.copy(p1, p2, REPLACE_EXISTING);
                                } catch (IOException ex) {
                                    Logger.getLogger(BasicTabUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                closeButtonListener.tabCloseButtonPressed(tabbedPane, index);
                                del.delete();

                            }

                        }
                        if (out == 1) {
                            closeButtonListener.tabCloseButtonPressed(tabbedPane, index);
                            del.delete();
                            if(del.exists()){
                                del.deleteOnExit();
                            }
                        }
                    }
                }
            }
        });
    }

    protected Tab getTab() {
        return tab;
    }

    protected void onHighlightTimerEvent(ActionEvent e) {
        if (tab != null) {
            tab.repaint();
        }
    }

    public static BasicTabUI createUI(JComponent c) {
        return new BasicTabUI();
    }

    @SuppressWarnings("serial")
    private class TabLayout extends BorderLayout {

        @Override
        public Dimension minimumLayoutSize(Container target) {
            Dimension minSize = super.minimumLayoutSize(target);
            if (minSize != null) {
                Insets insets = target.getInsets();
                minSize.width = insets.left + insets.right;
            }
            return minSize;
        }
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        tab = (Tab) c;

        tab.setLayout(new TabLayout());
        tab.add(label, BorderLayout.CENTER);
        tab.add(closeButton, BorderLayout.EAST);

        tab.addPropertyChangeListener(propertyChangeHandler);
    }

    @Override
    public void uninstallUI(JComponent c) {
        if (c != tab) {
            throw new IllegalArgumentException("c is not the Tab this UI is bound to");
        }

        super.uninstallUI(c);

        tab = (Tab) c;

        tab.removePropertyChangeListener(propertyChangeHandler);
        tab.remove(label);
        tab.remove(closeButton);
        tab.setLayout(null);

        tab = null;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        tab = (Tab) c;
        update();
        super.paint(g, c);
    }

    protected void update() {
        label.setText(tab.getTitle());
        label.setIcon(tab.getIcon());
        label.setDisplayedMnemonic(tab.getMnemonic());
        label.setDisplayedMnemonicIndex(tab.getDisplayedMnemonicIndex());

        JTabbedPane tabbedPane = SwingUtils.getJTabbedPaneAncestor(tab);

        boolean enabled = tab.isEnabled() && (tabbedPane == null || tabbedPane.isEnabled());

        closeButton.setVisible(PropertyGetter.get(Boolean.class, tab, CLOSE_BUTTON_VISIBLE, JTabbedPane.class, JhromeTabbedPaneUI.TAB_CLOSE_BUTTONS_VISIBLE, false));
        closeButton.setEnabled(enabled);

        if (tab.getTabComponent() != displayedTabComponent) {
            if (displayedTabComponent != null) {
                tab.remove(displayedTabComponent);
            }

            displayedTabComponent = tab.getTabComponent();

            if (displayedTabComponent != null) {
                tab.remove(label);
                tab.add(displayedTabComponent, BorderLayout.CENTER);
            } else {
                tab.add(label, BorderLayout.CENTER);
            }
        }

        if (!enabled) {
            label.setForeground(getDisabledForeground());
        } else if (tab.isSelected()) {
            label.setForeground(getSelectedForeground());
        } else if (tab.isRollover()) {
            label.setForeground(getRolloverForeground());
        } else {
            label.setForeground(getUnselectedForeground());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jhrome.IJhromeTab#isDraggableAt(java.awt.Point)
     */
    @Override
    public boolean isDraggableAt(Tab tab, Point p) {
        return isHoverableAt(tab, p) && !closeButton.contains(SwingUtilities.convertPoint(tab, p, closeButton));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jhrome.IJhromeTab#isSelectableAt(java.awt.Point)
     */
    @Override
    public boolean isSelectableAt(Tab tab, Point p) {
        return isDraggableAt(tab, p);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jhrome.IJhromeTab#isHoverableAt(java.awt.Point)
     */
    @Override
    public boolean isHoverableAt(Tab tab, Point p) {
        Insets insets = tab.getInsets();
        Rectangle bounds = tab.getBounds();
        bounds.x = insets.left;
        bounds.width -= insets.left + insets.right;
        bounds.y = insets.top;
        bounds.height -= insets.top + insets.bottom;
        return bounds.contains(p);
    }

    public Color getUnselectedLabelColor() {
        return unselectedForeground;
    }

    public void setUnselectedLabelColor(Color unselectedLabelColor) {
        this.unselectedForeground = unselectedLabelColor;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JLabel getLabel() {
        return label;
    }

    private class PropertyChangeHandler implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            update();
        }
    }

    public Color getDisabledForeground() {
        return disabledForeground != null ? disabledForeground : getUnselectedForeground();
    }

    public Color getRolloverForeground() {
        return rolloverForeground != null ? rolloverForeground : getSelectedForeground();
    }

    public Color getUnselectedForeground() {
        return unselectedForeground != null ? unselectedForeground : getSelectedForeground();
    }

    public Color getSelectedForeground() {
        return selectedForeground;
    }

    public Color getDisabledBackground() {
        return disabledBackground != null ? disabledBackground : getUnselectedBackground();
    }

    public Color getRolloverBackground() {
        return rolloverBackground != null ? rolloverBackground : getSelectedBackground();
    }

    public Color getUnselectedBackground() {
        return unselectedBackground != null ? unselectedBackground : getSelectedBackground();
    }

    public Color getSelectedBackground() {
        return selectedBackground;
    }
}
