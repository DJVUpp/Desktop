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

package jrico.jstickynotes.model;

import java.awt.Color;
import java.awt.Font;

import jrico.jstickynotes.util.Bean;

public class Preferences extends Bean {

    public static final Color DEFAULT_COLOR = new Color(0xfffaaa);

    /*
     * Properties
     */

    public static final String DEFAULT_COLOR_PROPERTY = "defaultNoteColor";
    public static final String FONT_PROPERTY = "defaultFont";
    public static final String FONT_COLOR_PROPERTY = "defaultFontColor";

    private Color defaultNoteColor;

    private Font defaultFont;

    private Color defaultFontColor;

    /**
     * @return the defaultNoteColor
     */
    public Color getDefaultNoteColor() {
        return defaultNoteColor;
    }

    /**
     * @param defaultNoteColor
     *            the defaultNoteColor to set
     */
    public void setDefaultNoteColor(Color defaultNoteColor) {
        Color oldDefaultColor = getDefaultNoteColor();
        this.defaultNoteColor = defaultNoteColor;
        getNotifier();
        notifier.firePropertyChange(DEFAULT_COLOR_PROPERTY, oldDefaultColor, defaultNoteColor);
    }

    /**
     * @return the defaultFont
     */
    public Font getDefaultFont() {
        return defaultFont;
    }

    /**
     * @param defaultFont
     *            the defaultFont to set
     */
    public void setDefaultFont(Font defaultFont) {
        Font oldDefaultFont = getDefaultFont();
        this.defaultFont = defaultFont;
        getNotifier();
        notifier.firePropertyChange(FONT_PROPERTY, oldDefaultFont, defaultFont);
    }

    /**
     * @return the defaultFontColor
     */
    public Color getDefaultFontColor() {
        return defaultFontColor;
    }

    /**
     * @param defaultFontColor
     *            the defaultFontColor to set
     */
    public void setDefaultFontColor(Color defaultFontColor) {
        Color oldFontColor = getDefaultFontColor();
        this.defaultFontColor = defaultFontColor;
        getNotifier();
        notifier.firePropertyChange(FONT_COLOR_PROPERTY, oldFontColor, defaultFontColor);
    }

}
