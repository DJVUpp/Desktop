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

import static jrico.jstickynotes.JStickyNotes.BUNDLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jrico.jstickynotes.util.Pair;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Jonatan Rico
 */
public class FontChooser extends JDialog {

    public static final String fontSizes[] = { "8", "10", "11", "12", "14", "16", "18", "20", "24", "30", "36", "40",
            "48", "60", "72" };

    public static final String REGULAR_STYLE = BUNDLE.getString("FontChooser.regularStyle.text");
    public static final String BOLD_STYLE = BUNDLE.getString("FontChooser.boldStyle.text");
    public static final String ITALIC_STYLE = BUNDLE.getString("FontChooser.italicStyle.text");
    public static final String BOLD_ITALIC_STYLE = BUNDLE.getString("FontChooser.boldItalicStyle.text");

    public static final String fontStyles[] = { REGULAR_STYLE, BOLD_STYLE, ITALIC_STYLE, BOLD_ITALIC_STYLE };

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    // //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel fontLabel;
    private JLabel styleLabel;
    private JLabel sizeLabel;
    private JTextField fontTextField;
    private JTextField styleTextField;
    private JTextField sizeTextField;
    private JScrollPane fontScroll;
    private JList fontList;
    private JScrollPane styleScroll;
    private JList styleList;
    private JScrollPane sizeScroll;
    private JList sizeList;
    private JLabel colorLabel;
    private JLabel fontColorLabel;
    private JLabel fontPreviewLabel;
    private JPanel buttonBar;
    private JSeparator buttonsSeparator;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    private Font selectedFont;

    private int option;

    public FontChooser(Window owner) {
        super(owner);
        initComponents();
        fillFonts();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        ResourceBundle bundle = ResourceBundle.getBundle("jrico.jstickynotes.resource.jstickynotes");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        fontLabel = new JLabel();
        styleLabel = new JLabel();
        sizeLabel = new JLabel();
        fontTextField = new JTextField();
        styleTextField = new JTextField();
        sizeTextField = new JTextField();
        fontScroll = new JScrollPane();
        fontList = new JList();
        styleScroll = new JScrollPane();
        styleList = new JList();
        sizeScroll = new JScrollPane();
        sizeList = new JList();
        colorLabel = new JLabel();
        fontColorLabel = new JLabel();
        fontPreviewLabel = new JLabel();
        buttonBar = new JPanel();
        buttonsSeparator = new JSeparator();
        okButton = new JButton();
        cancelButton = new JButton();
        CellConstraints cc = new CellConstraints();

        // ======== this ========
        setModal(true);
        setTitle(bundle.getString("FontChooser.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // ======== dialogPane ========
        {
            dialogPane.setBorder(Borders.DIALOG_BORDER);
            dialogPane.setLayout(new BorderLayout());

            // ======== contentPanel ========
            {
//                contentPanel.setLayout(new FormLayout("default, $lcgap, default:grow, 2*($rgap, default:grow(0.5))",
//                    "2*(default, $lgap), fill:default:grow, $lgap, fill:default, $ugap, fill:default"));

                // ---- fontLabel ----
                fontLabel.setText(bundle.getString("FontChooser.fontLabel.text"));
                contentPanel.add(fontLabel, cc.xywh(1, 1, 3, 1));

                // ---- styleLabel ----
                styleLabel.setText(bundle.getString("FontChooser.styleLabel.text"));
                contentPanel.add(styleLabel, cc.xy(5, 1));

                // ---- sizeLabel ----
                sizeLabel.setText(bundle.getString("FontChooser.sizeLabel.text"));
                contentPanel.add(sizeLabel, cc.xy(7, 1));

                // ---- fontTextField ----
                fontTextField.setEditable(false);
                contentPanel.add(fontTextField, cc.xywh(1, 3, 3, 1));

                // ---- styleTextField ----
                styleTextField.setEditable(false);
                contentPanel.add(styleTextField, cc.xy(5, 3));

                // ---- sizeTextField ----
                sizeTextField.setEditable(false);
                contentPanel.add(sizeTextField, cc.xy(7, 3));

                // ======== fontScroll ========
                {

                    // ---- fontList ----
                    fontList.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                            fontValueChanged(e);
                        }
                    });
                    fontScroll.setViewportView(fontList);
                }
                contentPanel.add(fontScroll, cc.xywh(1, 5, 3, 1));

                // ======== styleScroll ========
                {

                    // ---- styleList ----
                    styleList.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                            fontValueChanged(e);
                        }
                    });
                    styleScroll.setViewportView(styleList);
                }
                contentPanel.add(styleScroll, cc.xy(5, 5));

                // ======== sizeScroll ========
                {

                    // ---- sizeList ----
                    sizeList.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                            fontValueChanged(e);
                        }
                    });
                    sizeScroll.setViewportView(sizeList);
                }
                contentPanel.add(sizeScroll, cc.xy(7, 5));

                // ---- colorLabel ----
                colorLabel.setText(bundle.getString("FontChooser.colorLabel.text"));
                contentPanel.add(colorLabel, cc.xy(1, 7));

                // ---- fontColorLabel ----
                fontColorLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        fontColorLabelMouseClicked(e);
                    }
                });
                contentPanel.add(fontColorLabel, cc.xy(3, 7));

                // ---- fontPreviewLabel ----
                fontPreviewLabel.setText(bundle.getString("FontChooser.fontPreviewLabel.text"));
                fontPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(fontPreviewLabel, cc.xywh(1, 9, 7, 1));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            // ======== buttonBar ========
            {
                buttonBar.setBorder(null);
                //buttonBar.setLayout(new FormLayout("$glue, $button, $rgap, $button", "$ugap, default, $ugap, pref"));
                buttonBar.add(buttonsSeparator, cc.xywh(1, 2, 4, 1));

                // ---- okButton ----
                okButton.setText(bundle.getString("FontChooser.okButton.text"));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, cc.xy(2, 4));

                // ---- cancelButton ----
                cancelButton.setText(bundle.getString("FontChooser.cancelButton.text"));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed(e);
                    }
                });
                buttonBar.add(cancelButton, cc.xy(4, 4));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // //GEN-END:initComponents
        fontColorLabel.setOpaque(true);
    }

    private void fillFonts() {
        // fill the font list
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        DefaultListModel fontListModel = new DefaultListModel();
        for (int i = 0; i < fontNames.length; i++) {
            fontListModel.addElement(fontNames[i]);
        }
        fontList.setModel(fontListModel);
        fontList.setSelectedIndex(0);

        // fill the size list
        DefaultListModel sizeListModel = new DefaultListModel();
        for (int i = 0; i < fontSizes.length; i++) {
            sizeListModel.addElement(fontSizes[i]);
        }
        sizeList.setModel(sizeListModel);
        sizeList.setSelectedIndex(0);

        // fill the size list
        DefaultListModel styleListModel = new DefaultListModel();
        for (int i = 0; i < fontStyles.length; i++) {
            styleListModel.addElement(fontStyles[i]);
        }
        styleList.setModel(styleListModel);
        styleList.setSelectedIndex(0);
    }

    @Override
    public void setVisible(boolean isVisible) {
        if (isVisible) {
            option = JOptionPane.CANCEL_OPTION;
        }
        super.setVisible(isVisible);
    }

    private void fontValueChanged(ListSelectionEvent e) {
        if (fontList.getSelectedValue() != null && styleList.getSelectedValue() != null
                && sizeList.getSelectedValue() != null) {
            String font = (String) fontList.getSelectedValue();
            String style = (String) styleList.getSelectedValue();
            String size = (String) sizeList.getSelectedValue();

            selectedFont = new Font(font, getSytle(style), Integer.parseInt(size));

            fontPreviewLabel.setFont(selectedFont);
            fontTextField.setText(font);
            styleTextField.setText(style);
            sizeTextField.setText(size);
        }
    }

    private void fontColorLabelMouseClicked(MouseEvent e) {
        Color color = JColorChooser.showDialog(null, "Select the default color", fontColorLabel.getBackground());
        if (color != null) {
            fontColorLabel.setBackground(color);
            fontPreviewLabel.setForeground(color);
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        option = JOptionPane.OK_OPTION;
        setVisible(false);
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private int getSytle(String style) {
        int result = Font.PLAIN;
        if (style.equals(BOLD_STYLE)) {
            result = Font.BOLD;
        } else if (style.equals(ITALIC_STYLE)) {
            result = Font.ITALIC;
        } else if (style.equals(BOLD_ITALIC_STYLE)) {
            result = Font.BOLD | Font.ITALIC;
        }
        return result;
    }

    private String getSytle(int style) {
        String result = REGULAR_STYLE;
        if ((style & Font.BOLD) == Font.BOLD && (style & Font.ITALIC) == Font.ITALIC) {
            result = BOLD_ITALIC_STYLE;
        } else if ((style & Font.BOLD) == Font.BOLD) {
            result = BOLD_STYLE;
        } else if ((style & Font.ITALIC) == Font.ITALIC) {
            result = ITALIC_STYLE;
        }
        return result;
    }

    public Font getSelectedFont() {
        return selectedFont;
    }

    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
        fontList.setSelectedValue(selectedFont.getFamily(), true);
        sizeList.setSelectedValue(String.valueOf(selectedFont.getSize()), true);
        styleList.setSelectedValue(getSytle(selectedFont.getStyle()), true);
    }

    public Color getFontColor() {
        return fontColorLabel.getBackground();
    }

    public void setFontColor(Color color) {
        fontColorLabel.setBackground(color);
        fontPreviewLabel.setForeground(color);
    }

    public int getOption() {
        return option;
    }

    public static Pair<Font, Color> showDialog(Window owner, Font initialFont, Color initialColor) {
        FontChooser fontChooser = new FontChooser(owner);
        fontChooser.setSelectedFont(initialFont);
        fontChooser.setFontColor(initialColor);
        fontChooser.pack();
        fontChooser.setLocationRelativeTo(owner);
        fontChooser.setVisible(true);
        Pair<Font, Color> pair = null;
        if (fontChooser.getOption() == JOptionPane.OK_OPTION) {
            pair = Pair.create(fontChooser.getSelectedFont(), fontChooser.getFontColor());
        }
        return pair;
    }
}
