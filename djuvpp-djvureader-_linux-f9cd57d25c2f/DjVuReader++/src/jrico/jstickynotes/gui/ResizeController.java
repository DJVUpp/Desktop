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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jrico.jstickynotes.util.Bean;

public class ResizeController extends Bean implements MouseListener, MouseMotionListener, ComponentListener {

    public static final String DRAGGING_PROPERTY = "ResizeController.resizing";
    public static final String SIZE_PROPERTY = "ResizeController.size";

    public static final int MINIMUM_SIZE = 50;
    public static final int RESIZE_THRESHOLD = 5;

    private Component componentToListen;
    private Component componentToResize;
    private Point location;
    private boolean isInside;
    private Rectangle resizingArea;
    private int deltaX;
    private int deltaY;

    public ResizeController(Component componentToListen, Component componentToResize) {
        this.componentToListen = componentToListen;
        this.componentToResize = componentToResize;
        resizingArea = new Rectangle();

        componentToListen.addMouseListener(this);
        componentToListen.addMouseMotionListener(this);
        componentToListen.addComponentListener(this);
    }

    public ResizeController(Component component) {
        this(component, component);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        isInside = resizingArea.contains(me.getX(), me.getY());
        if (isInside) {
            deltaX = componentToListen.getWidth() - me.getX();
            deltaY = componentToListen.getHeight() - me.getY();
            getNotifier();
            notifier.firePropertyChange(DRAGGING_PROPERTY, false, true);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (isInside) {
            location = componentToResize.getLocation(location);
            int x = me.getXOnScreen() - location.x;
            int y = me.getYOnScreen() - location.y;
            x += deltaX;
            y += deltaY;
            x = x >= MINIMUM_SIZE ? x : MINIMUM_SIZE;
            y = y >= MINIMUM_SIZE ? y : MINIMUM_SIZE;
            componentToResize.setSize(x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInside) {
            deltaX = 0;
            deltaY = 0;
            getNotifier();
            notifier.firePropertyChange(SIZE_PROPERTY, null, new Dimension(componentToResize.getWidth(),
                componentToResize.getHeight()));
            updateArea();
            notifier.firePropertyChange(DRAGGING_PROPERTY, true, false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (resizingArea.contains(e.getX(), e.getY())) {
            componentToListen.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            componentToListen.setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        updateArea();
    }

    public void updateArea() {
        int x = componentToListen.getSize().width - RESIZE_THRESHOLD;
        int y = componentToListen.getSize().height - RESIZE_THRESHOLD;
        resizingArea.setBounds(x, y, RESIZE_THRESHOLD, RESIZE_THRESHOLD);
    }

    public Rectangle getResizingArea() {
        return resizingArea;
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
