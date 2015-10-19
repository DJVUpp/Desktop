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

package jrico.jstickynotes.util;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import jrico.jstickynotes.model.Note;

public class Screen {

    private Screen() {
    }

    public static Point getLocation(Note note) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        Point relativeLocation = note.getRelativeLocation();
        int x = Percentage.getValue(relativeLocation.x, screenSize.width);
        int y = Percentage.getValue(relativeLocation.y, screenSize.height);
        return new Point(x, y);
    }

    public static Point getRelativeLocation(Point point) {
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int relativeX = Percentage.getPercentage(point.x, screenSize.width);
        int relativeY = Percentage.getPercentage(point.y, screenSize.height);
        return new Point(relativeX, relativeY);
    }
}
