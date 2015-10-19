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

import java.awt.Image;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import jrico.jstickynotes.JStickyNotes;

public enum Icon {
    JSTICKY_NOTES_16("icon16.png"),
    JSTICKY_NOTES_24("icon24.png"),
    JSTICKY_NOTES_32("icon32.png"),
    JSTICKY_NOTES_48("icon48.png"),

    DELETE("delete.png"),
    MAIL("mail.png"),
    HIDDEN("hidden.png"),
    SHOWN("shown.png"),
    ALWAYS_ON_TOP_UNSET("alwaysOnTopUnset.png"),
    ALWAYS_ON_TOP_SET("alwaysOnTopSet.png"),
    COLOR("color.png"),
    FONT("font.png");

    private ImageIcon imageIcon;

    private Icon(String name) {
        imageIcon = new ImageIcon(JStickyNotes.class.getResource("/jrico/jstickynotes/resource/icons/" + name));
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public static ImageIcon getJStickyNotesImageIcon(int size) {
        ImageIcon icon = null;

        if (size > 0 && size <= 20) {
            icon = JSTICKY_NOTES_16.getImageIcon();
        } else if (size > 20 && size <= 28) {
            icon = JSTICKY_NOTES_24.getImageIcon();
        } else if (size > 28 && size <= 42) {
            icon = JSTICKY_NOTES_32.getImageIcon();
        } else if (size > 42) {
            icon = JSTICKY_NOTES_48.getImageIcon();
        }

        return icon;
    }

    public static List<Image> getJStickyNotesImages() {
        return Arrays.asList(JSTICKY_NOTES_16.getImageIcon().getImage(), JSTICKY_NOTES_24.getImageIcon().getImage(),
            JSTICKY_NOTES_32.getImageIcon().getImage(), JSTICKY_NOTES_48.getImageIcon().getImage());
    }
}
