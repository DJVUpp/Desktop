/*
 * JStickyNotes, Copyright (C) Feb 23, 2009 - Jonatan Rico (jrico) jnrico@gmail.com
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package jrico.jstickynotes.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import jrico.jstickynotes.model.Preferences;
import jrico.jstickynotes.util.Pair;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Jonatan Rico
 */
@SuppressWarnings("serial")
public class PreferencesDialog extends JDialog {

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    // //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JSeparator buttonsSeparator;
    private JButton okButton;
    private JButton cancelButton;
    private JTabbedPane preferencesTabbedPane;
    private JScrollPane generalScroll;
    private JPanel generalPanel;
    private JLabel generalDescriptionLabel;
    private JButton colorButton;
    private JLabel colorLabel;
    private JButton fontButton;
    private JLabel fontLabel;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    private Preferences preferences;

    public PreferencesDialog(JFrame owner, Preferences preferences) {
        super(owner);
        this.preferences = preferences;
        initComponents();
    }

    private void fontButtonActionPerformed(ActionEvent e) {
        Pair<Font, Color> pair = FontChooser.showDialog(this, fontLabel.getFont(), fontLabel.getForeground());
        if (pair != null) {
            fontLabel.setFont(pair.getObjectA());
            fontLabel.setForeground(pair.getObjectB());
        }
    }

    private void colorButtonActionPerformed(ActionEvent e) {
        Color color = JColorChooser.showDialog(this, "JStickyNotes - Choose color", preferences.getDefaultNoteColor());
        if (color != null) {
            colorLabel.setBackground(color);
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        preferences.setDefaultFont(fontLabel.getFont());
        preferences.setDefaultFontColor(fontLabel.getForeground());
        preferences.setDefaultNoteColor(colorLabel.getBackground());
        setVisible(false);
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        ResourceBundle bundle = ResourceBundle.getBundle("jrico.jstickynotes.resource.jstickynotes");
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        buttonsSeparator = new JSeparator();
        okButton = new JButton();
        cancelButton = new JButton();
        preferencesTabbedPane = new JTabbedPane();
        generalScroll = new JScrollPane();
        generalPanel = new JPanel();
        generalDescriptionLabel = new JLabel();
        colorButton = new JButton();
        colorLabel = new JLabel();
        fontButton = new JButton();
        fontLabel = new JLabel();
        CellConstraints cc = new CellConstraints();

        // ======== this ========
        setModal(true);
        setTitle(bundle.getString("PreferencesDialog.this.title"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // ======== dialogPane ========
        {
            dialogPane.setBorder(Borders.DIALOG_BORDER);
            dialogPane.setLayout(new BorderLayout());

            // ======== buttonBar ========
            {
                buttonBar.setBorder(null);
                buttonBar.setLayout(new FormLayout("$glue, $button, $rgap, $button", "$ugap, default, $ugap, pref"));
                buttonBar.add(buttonsSeparator, cc.xywh(1, 2, 4, 1));

                // ---- okButton ----
                okButton.setText(bundle.getString("PreferencesDialog.okButton.text"));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, cc.xy(2, 4));

                // ---- cancelButton ----
                cancelButton.setText(bundle.getString("PreferencesDialog.cancelButton.text"));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed(e);
                    }
                });
                buttonBar.add(cancelButton, cc.xy(4, 4));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            // ======== preferencesTabbedPane ========
            {

                // ======== generalScroll ========
                {

                    // ======== generalPanel ========
                    {
                        generalPanel.setBorder(Borders.TABBED_DIALOG_BORDER);
                        generalPanel.setLayout(new FormLayout("2*($button, $lcgap), default:grow",
                            "default, $ugap, fill:default, $lgap, fill:default"));

                        // ---- generalDescriptionLabel ----
                        generalDescriptionLabel.setText(bundle
                            .getString("PreferencesDialog.generalDescriptionLabel.text"));
                        generalPanel.add(generalDescriptionLabel, cc.xywh(1, 1, 5, 1));

                        // ---- colorButton ----
                        colorButton.setText(bundle.getString("PreferencesDialog.colorButton.text"));
                        colorButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                colorButtonActionPerformed(e);
                            }
                        });
                        generalPanel.add(colorButton, cc.xy(1, 3));
                        generalPanel.add(colorLabel, cc.xy(3, 3));

                        // ---- fontButton ----
                        fontButton.setText(bundle.getString("PreferencesDialog.fontButton.text"));
                        fontButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                fontButtonActionPerformed(e);
                            }
                        });
                        generalPanel.add(fontButton, cc.xy(1, 5));

                        // ---- fontLabel ----
                        fontLabel.setText(bundle.getString("PreferencesDialog.fontLabel.text"));
                        generalPanel.add(fontLabel, cc.xywh(3, 5, 3, 1));
                    }
                    generalScroll.setViewportView(generalPanel);
                }
                preferencesTabbedPane.addTab(bundle.getString("PreferencesDialog.generalPanel.tab.title"),
                    generalScroll);

            }
            dialogPane.add(preferencesTabbedPane, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // //GEN-END:initComponents
        colorLabel.setOpaque(true);
        colorLabel.setBackground(preferences.getDefaultNoteColor());
        fontLabel.setFont(preferences.getDefaultFont());
        fontLabel.setForeground(preferences.getDefaultFontColor());
    }
}
