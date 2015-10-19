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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTextPane;

public class StickyNoteTextPane extends JTextPane implements MouseListener, MouseMotionListener, KeyListener,
        FocusListener {

    public static final String EDITING_PROPERTY = "StickyNoteTextPane.editing";

    public static final String TEXT_PROPERTY = "StickyNoteTextPane.text";

    public static final double CLEARER_FACTOR = 0.5;

    public StickyNoteTextPane() {
        setFocusable(false);
        setOpaque(false);
        setSelectionColor(Color.BLACK);
        setSelectedTextColor(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addFocusListener(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Creates a two-stops gradient
        Color c1 = getBackground();
        int red = getClearerRgbComponent(c1.getRed());
        int green = getClearerRgbComponent(c1.getGreen());
        int blue = getClearerRgbComponent(c1.getBlue());
        Color c2 = new Color(red, green, blue);
        GradientPaint p = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
        // Saves the state
        Paint oldPaint = g2d.getPaint();
        // Paints the background
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        // Restores the state
        g2d.setPaint(oldPaint);
        // Paints borders, text...
        super.paintComponent(g2d);
    }

    public void startEditing() {
        firePropertyChange(EDITING_PROPERTY, false, true);
        setFocusable(true);
        requestFocus();
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!hasFocus()) {
            setCursor(Cursor.getDefaultCursor());
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            startEditing();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        select(0, 0);
        setFocusable(false);
        setCursor(Cursor.getDefaultCursor());
        firePropertyChange(EDITING_PROPERTY, true, false);
        firePropertyChange(TEXT_PROPERTY, null, getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            focusLost(null);
        }
    }

    private int getClearerRgbComponent(int rgbComponent) {
        int delta = 255 - rgbComponent;
        delta = (int) (delta * CLEARER_FACTOR);
        return rgbComponent + delta;
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
