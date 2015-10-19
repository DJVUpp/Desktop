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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.plaf.basic.BasicTextPaneUI;

import jrico.jstickynotes.model.Note;
import jrico.jstickynotes.util.Screen;

@SuppressWarnings("serial")
public class StickyNote extends JWindow implements PropertyChangeListener {

    public static final String CHILD_WINDOW_OPENED = "childWindowOpened";

    private JFrame parent;
    private Note note;
    private StickyNoteTextPane text;
    private JScrollPane scroll;
    private StickyNoteHeader header;
    private MoveController textMoveController;
    private MoveController scrollMoveController;
    private ResizeController scrollResizeController;
    private MoveController headerMoveController;
    Point point;
    public StickyNote(JFrame parent, Note note ,Point point1) {
        super(parent);
        this.point=point1;
        this.parent = parent;
        this.note = note;
        note.addPropertyChangeListener(this);
        init();
    }

    private void init() {
        Color color = note.getColor();
        ToFrontListener toFrontListener = new ToFrontListener();

        // create text pane
        text = new StickyNoteTextPane();
        text.setText(note.getText());
        text.setUI(new BasicTextPaneUI());
        text.setBackground(color);
        text.setFont(note.getFont());
        text.setForeground(note.getFontColor());
        text.addMouseListener(toFrontListener);
        text.addPropertyChangeListener(this);
        textMoveController = new MoveController(text, this);
        textMoveController.addPropertyChangeListener(this);

        // create scroll pane
        int borderSize = ResizeController.RESIZE_THRESHOLD;
        scroll = new JScrollPane() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g2d);
            }
        };
        scroll.setBorder(BorderFactory.createEmptyBorder(0, borderSize, borderSize, borderSize));
        scroll.setBackground(color);
        scroll.setForeground(color);
        scroll.setViewportView(text);
        scroll.addMouseListener(toFrontListener);
        scrollMoveController = new MoveController(scroll, this);
        scrollResizeController = new ResizeController(scroll, this);
        scrollMoveController.addPropertyChangeListener(this);
        scrollResizeController.addPropertyChangeListener(this);
        add(scroll, BorderLayout.CENTER);

        // create the header
        header = new StickyNoteHeader(this);
        header.setFont(note.getFont());
        header.setForeground(note.getFontColor());
        header.setBackground(color);
        header.addMouseListener(toFrontListener);
        headerMoveController = new MoveController(header, this);
        headerMoveController.addPropertyChangeListener(this);
        add(header, BorderLayout.NORTH);

        // window properties
        setSize(note.getSize());
        setAlwaysOnTop(note.isAlwaysOnTop());
        setLocationRelativeTo(null);
//        setLocation(point);
        setVisible(note.isVisible());
    }

    public Note getNote() {
        return note;
    }

    public void startEditingText() {
        text.startEditing();
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Object source = pce.getSource();
        String property = pce.getPropertyName();

        if (property.equals(MoveController.RELATIVE_LOCATION_PROPERTY)) {
            // process the events fired by MoveControllers attached to the
            // components
            Point relativeLocation = (Point) pce.getNewValue();
            note.setRelativeLocation(relativeLocation);
        } else if (source == text) {
            processTextEvents(pce);
        } else if (source == scrollResizeController) {
            processScrollResizeControllerEvents(pce);
        } else if (source == note) {
            processNoteEvents(pce);
        }
    }

    @Override
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        super.firePropertyChange(propertyName, oldValue, newValue);
    }

    private void processTextEvents(PropertyChangeEvent pce) {
        String property = pce.getPropertyName();

        if (property.equals(StickyNoteTextPane.EDITING_PROPERTY)) {
            boolean isEditing = (Boolean) pce.getNewValue();
            // if the text component is in editing state, disable the
            // MoveContoller associated to the text component, enable it
            // otherwise
            textMoveController.setIgnoreEvents(isEditing);
            setFocusable(isEditing);
        } else if (property.equals(StickyNoteTextPane.TEXT_PROPERTY)) {
            String text = (String) pce.getNewValue();
            note.setText(text);
        }
    }

    private void processScrollResizeControllerEvents(PropertyChangeEvent pce) {
        String property = pce.getPropertyName();

        if (property.equals(ResizeController.DRAGGING_PROPERTY)) {
            // if the scroll component is in resizing state, disable the
            // MoveContoller associated to the scroll component, enable it
            // otherwise
            scrollMoveController.setIgnoreEvents((Boolean) pce.getNewValue());
        } else if (property.equals(ResizeController.SIZE_PROPERTY)) {
            Dimension size = (Dimension) pce.getNewValue();
            note.setSize(size);
        }
    }

    private void processNoteEvents(PropertyChangeEvent pce) {
        String property = pce.getPropertyName();

        if (Note.STATUS_PROPERTY.equals(property) && ((Integer) pce.getNewValue()) == Note.DELETED_STATUS) {
            dispose();
        } else if (Note.FONT_PROPERTY.equals(property)) {
            text.setFont((Font) pce.getNewValue());
        } else if (Note.FONT_COLOR_PROPERTY.equals(property)) {
            text.setForeground((Color) pce.getNewValue());
        } else if (Note.COLOR_PROPERTY.equals(property)) {
            Color color = (Color) pce.getNewValue();
            text.setBackground(color);
            scroll.setForeground(color);
            header.setBackground(color);
        } else if (Note.ALWAYS_ON_TOP_PROPERTY.equals(property)) {
            boolean isAlwaysOnTop = (Boolean) pce.getNewValue();
            setAlwaysOnTop(isAlwaysOnTop);

            if (!isAlwaysOnTop && !parent.isActive()) {
                setVisible(false);
            }
        } else if (Note.VISIBLE_PROPERTY.equals(property)) {
            boolean visible = (Boolean) pce.getNewValue();

            if (!visible) {
                setVisible(visible);
            }
        }
    }

    private class ToFrontListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            toFront();
        }
    }
}
