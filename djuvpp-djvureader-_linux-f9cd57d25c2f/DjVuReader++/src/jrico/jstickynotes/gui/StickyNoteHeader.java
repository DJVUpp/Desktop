/*
 * JStickyNotes, Copyright (C) Feb 13, 2009 - Jonatan Rico (jrico) jnrico@gmail.com
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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jrico.jstickynotes.model.Note;
import jrico.jstickynotes.util.Pair;

@SuppressWarnings("serial")
public class StickyNoteHeader extends JPanel implements PropertyChangeListener {

    public static final String DELETE_TEXT = BUNDLE.getString("StickyNoteHeader.deleteTooltip.text");
    public static final String DELETE_DIALOG_TEXT = BUNDLE.getString("StickyNoteHeader.deleteDialog.text");
    public static final String DELETE_DIALOG_TITLE_TEXT = BUNDLE.getString("StickyNoteHeader.deleteDialogTitle.text");
    public static final String MAIL_TEXT = BUNDLE.getString("StickyNoteHeader.mailTooltip.text");
    public static final String CHANGE_FONT_TEXT = BUNDLE.getString("StickyNoteHeader.changeFontTooltip.text");
    public static final String CHANGE_COLOR_TEXT = BUNDLE.getString("StickyNoteHeader.changeColorTooltip.text");
    public static final String CHANGE_COLOR_DIALOG_TITLE_TEXT = BUNDLE
        .getString("StickyNoteHeader.changeColorDialogTitle.text");
    public static final String ALWAYS_ON_TOP_TEXT = BUNDLE.getString("StickyNoteHeader.alwaysOnTopTooltip.text");
    public static final String SHOWN_TEXT = BUNDLE.getString("StickyNoteHeader.shownTooltip.text");
    public static final String HIDDEN_TEXT = BUNDLE.getString("StickyNoteHeader.hiddenTooltip.text");

    private StickyNote stickyNote;
    private Note note;
    private JLabel deleteLabel;
    private JLabel mailLabel;
    private JLabel fontLabel;
    private JLabel colorLabel;
    private JLabel alwaysOnTopLabel;
    private JLabel shownLabel;

    public StickyNoteHeader(StickyNote stickyNote) {
        super(new FlowLayout(FlowLayout.RIGHT));
        this.stickyNote = stickyNote;
        note = stickyNote.getNote();
        note.addPropertyChangeListener(this);
        initComponents();
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getSource() == note) {
            String property = e.getPropertyName();
            if (Note.ALWAYS_ON_TOP_PROPERTY.equals(property)) {
                updateAlwaysOnTopLabel();
            } else if (Note.VISIBLE_PROPERTY.equals(property)) {
                updateShownLabel();
            } else if (Note.FONT_PROPERTY.equals(property)) {
                setFont(note.getFont());
            } else if (Note.FONT_COLOR_PROPERTY.equals(property)) {
                setForeground(note.getFontColor());
            } else if (Note.COLOR_PROPERTY.equals(property)) {
                setBackground(note.getColor());
            }
        }
    }

    private void initComponents() {
        // create header actions
        ActionPerformer actionPerformer = new ActionPerformer();
        deleteLabel = new JLabel(Icon.DELETE.getImageIcon());
        deleteLabel.setToolTipText(DELETE_TEXT);
        deleteLabel.setBorder(BorderFactory.createEmptyBorder());
        deleteLabel.addMouseListener(actionPerformer);
        add(deleteLabel);

        mailLabel = new JLabel(Icon.MAIL.getImageIcon());
        mailLabel.setToolTipText(MAIL_TEXT);
        mailLabel.setBorder(BorderFactory.createEmptyBorder());
        mailLabel.addMouseListener(actionPerformer);
        // TODO uncomment the next line to add mail storage support
        // add(mailLabel);

        fontLabel = new JLabel(Icon.FONT.getImageIcon());
        fontLabel.setToolTipText(CHANGE_FONT_TEXT);
        fontLabel.setBorder(BorderFactory.createEmptyBorder());
        fontLabel.addMouseListener(actionPerformer);
        add(fontLabel);

        colorLabel = new JLabel(Icon.COLOR.getImageIcon());
        colorLabel.setToolTipText(CHANGE_COLOR_TEXT);
        colorLabel.setBorder(BorderFactory.createEmptyBorder());
        colorLabel.addMouseListener(actionPerformer);
        add(colorLabel);

        alwaysOnTopLabel = new JLabel();
        alwaysOnTopLabel.setToolTipText(ALWAYS_ON_TOP_TEXT);
        alwaysOnTopLabel.setBorder(BorderFactory.createEmptyBorder());
        alwaysOnTopLabel.addMouseListener(actionPerformer);
        updateAlwaysOnTopLabel();
        add(alwaysOnTopLabel);

        shownLabel = new JLabel();
        shownLabel.setToolTipText(SHOWN_TEXT);
        shownLabel.setBorder(BorderFactory.createEmptyBorder());
        shownLabel.addMouseListener(actionPerformer);
        updateShownLabel();
        add(shownLabel);
    }

    private void updateShownLabel() {
        shownLabel.setIcon(note.isVisible() ? Icon.SHOWN.getImageIcon() : Icon.HIDDEN.getImageIcon());
        shownLabel.setToolTipText(note.isVisible() ? SHOWN_TEXT : HIDDEN_TEXT);
    }

    private void updateAlwaysOnTopLabel() {
        alwaysOnTopLabel.setIcon(note.isAlwaysOnTop() ? Icon.ALWAYS_ON_TOP_SET.getImageIcon()
                : Icon.ALWAYS_ON_TOP_UNSET.getImageIcon());
    }

    private class ActionPerformer extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            if (deleteLabel == source) {
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, false, true);
                int option = JOptionPane.showConfirmDialog(stickyNote.getOwner(), DELETE_DIALOG_TEXT,
                    DELETE_DIALOG_TITLE_TEXT, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, true, false);
                if (option == JOptionPane.YES_OPTION) {
                    note.setStatus(Note.DELETED_STATUS);
                }
            } else if (mailLabel == source) {
                note.setType(Note.REMOTE_TYPE);
            } else if (fontLabel == source) {
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, false, true);
                Pair<Font, Color> pair = FontChooser.showDialog(stickyNote.getOwner(), getFont(), getForeground());
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, true, false);
                if (pair != null) {
                    note.setFont(pair.getObjectA());
                    note.setFontColor(pair.getObjectB());
                }
            } else if (colorLabel == source) {
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, false, true);
                Color color = JColorChooser.showDialog(stickyNote.getOwner(), CHANGE_COLOR_DIALOG_TITLE_TEXT,
                    getBackground());
                stickyNote.firePropertyChange(StickyNote.CHILD_WINDOW_OPENED, true, false);
                if (color != null) {
                    note.setColor(color);
                }
            } else if (alwaysOnTopLabel == source) {
                note.setAlwaysOnTop(!note.isAlwaysOnTop());
            } else if (shownLabel == source) {
                note.setVisible(!note.isVisible());
            }
        }
    }
}
