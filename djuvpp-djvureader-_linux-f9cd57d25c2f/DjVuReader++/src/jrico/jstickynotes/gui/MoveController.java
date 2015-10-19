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
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jrico.jstickynotes.util.Bean;
import jrico.jstickynotes.util.Screen;

public class MoveController extends Bean implements MouseListener, MouseMotionListener {

    public static final String RELATIVE_LOCATION_PROPERTY = "MoveController.relativeLocation";

    private Point location;
    private MouseEvent pressed;
    private boolean isDragging;
    private boolean ignoreEvents;
    private Component componentToListen;
    private Component componentToMove;

    public MoveController(Component componentToListen, Component componentToMove) {
        this.componentToListen = componentToListen;
        this.componentToMove = componentToMove;

        componentToListen.addMouseListener(this);
        componentToListen.addMouseMotionListener(this);
    }

    public MoveController(Component component) {
        this(component, component);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        pressed = me;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (!ignoreEvents) {
            isDragging = true;
            location = componentToMove.getLocation(location);
            int x = location.x - pressed.getX() + me.getX();
            int y = location.y - pressed.getY() + me.getY();
            componentToMove.setLocation(x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!ignoreEvents) {
            if (isDragging) {
                isDragging = false;
                Point relativeLocation = Screen.getRelativeLocation(componentToMove.getLocation());
                getNotifier();
                notifier.firePropertyChange(RELATIVE_LOCATION_PROPERTY, null, relativeLocation);
            }
        }
    }

    public void setIgnoreEvents(boolean ignoreEvents) {
        this.ignoreEvents = ignoreEvents;
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

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
