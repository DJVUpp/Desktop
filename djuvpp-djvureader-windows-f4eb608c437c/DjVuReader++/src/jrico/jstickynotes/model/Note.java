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

package jrico.jstickynotes.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import jrico.jstickynotes.util.Bean;

/**
 * A note is a logical representation of a desktop's window containing text information.
 * 
 * @author Jonatan Rico (jrico) jnrico@gmail.com
 * 
 */
public class Note extends Bean implements Serializable {

    /*
     * Properties
     */

    public static final String ID_PROPERTY = "id";

    public static final String TYPE_PROPERTY = "type";

    public static final String VERSION_PROPERTY = "version";

    public static final String STATUS_PROPERTY = "status";

    public static final String COLOR_PROPERTY = "color";

    public static final String RELATIVE_LOCATION_PROPERTY = "relativeLocation";

    public static final String SIZE_PROPERTY = "size";

    public static final String VISIBLE_PROPERTY = "visible";

    public static final String ALWAYS_ON_TOP_PROPERTY = "alwaysOnTop";

    public static final String FONT_PROPERTY = "font";

    public static final String FONT_COLOR_PROPERTY = "fontColor";

    public static final String TEXT_PROPERTY = "text";

    public static final String CATEGORIES_PROPERTY = "categories";

    /**
     * The note is stored only in the machine where it was created.
     */
    public static final int LOCAL_TYPE = 1;

    /**
     * The note is stored remotely so it is available everywhere.
     */
    public static final int REMOTE_TYPE = 2;

    /**
     * The note has been created but it is not persistent yet.
     */
    public static final int CREATED_STATUS = 1;

    /**
     * The note has been stored and it is persistent now.
     */
    public static final int STORED_STATUS = 2;

    /**
     * The note has been modified but the latest changes have not been stored.
     */
    public static final int MODIFIED_STATUS = 3;

    /**
     * The note is marked to be removed.
     */
    public static final int DELETED_STATUS = 4;

    /**
     * The note's unique identifier.
     */
    private long id;

    /**
     * The note's type.{@link #LOCAL_TYPE}, {@link #REMOTE_TYPE}.
     */
    private int type;

    /**
     * The note's current version.
     */
    private int version;

    /**
     * The note's status. {@link #CREATED_STATUS}, {@link #STORED_STATUS}, {@link #MODIFIED_STATUS},
     * {@link #DELETED_STATUS}.
     */
    private int status;

    /**
     * The note's color.
     */
    private Color color;

    /**
     * The note's relative location on the screen. This value is not an absolute position, it is a percentage.
     */
    private Point relativeLocation;

    /**
     * The note's size in pixels.
     */
    private Dimension size;

    /**
     * Whether the note is visible or not.
     */
    private boolean visible;

    /**
     * Whether the note will be always on top or not.
     */
    private boolean alwaysOnTop;

    /**
     * The note's font.
     */
    private Font font;

    /**
     * The note's font color.
     */
    private Color fontColor;

    /**
     * The note's text.
     */
    private String text;

    /**
     * The list of categories that this note belongs to.
     */
    private List<String> categories;

    public Note() {
    }

    /**
     * Returns this note's unique identifier.
     * 
     * @return the identifier
     * @see #setId
     */
    public long getId() {
        return id;
    }

    /**
     * Sets this note's unique identifier.
     * 
     * @param id
     *            the identifier
     * @see #getId
     */
    public void setId(long id) {
        long oldId = getId();
        this.id = id;
        getNotifier().firePropertyChange(ID_PROPERTY, oldId, id);
    }

    /**
     * Returns this note's type.
     * 
     * @return the type
     * @see #setType
     */
    public int getType() {
        return type;
    }

    /**
     * Sets this note's type.
     * 
     * @param type
     *            the type
     * @see #LOCAL_TYPE
     * @see #REMOTE_TYPE
     * @see #getType
     */
    public void setType(int type) {
        int oldType = getType();
        this.type = type;
        getNotifier().firePropertyChange(TYPE_PROPERTY, oldType, type);
    }

    /**
     * Returns this note's current version number.
     * 
     * @return the version
     * @see #setVersion
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets this note's current version number.
     * 
     * @param version
     *            the version
     * @see #getVersion
     */
    public void setVersion(int version) {
        int oldVersion = getVersion();
        this.version = version;
        getNotifier().firePropertyChange(VERSION_PROPERTY, oldVersion, version);
    }

    /**
     * Returns this note's current status.
     * 
     * @return the status
     * @see #setStatus
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets this note's status.
     * 
     * @param status
     *            the status
     * @see #CREATED_STATUS
     * @see #STORED_STATUS
     * @see #MODIFIED_STATUS
     * @see #DELETED_STATUS
     * @see #getStatus
     */
    public void setStatus(int status) {
        int oldStatus = getStatus();
        this.status = status;
        getNotifier().firePropertyChange(STATUS_PROPERTY, oldStatus, status);
    }

    /**
     * Returns this notes color as an integer value described by {@link Color#getRGB}.
     * 
     * @return the color
     * @see #getColor
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets this note's color. The color is an integer value described by {@link Color#getRGB}.
     * 
     * @param color
     *            the color
     * @see #getColor
     */
    public void setColor(Color color) {
        Color oldColor = getColor();
        this.color = color;
        getNotifier().firePropertyChange(COLOR_PROPERTY, oldColor, color);
    }

    /**
     * Returns this note's relative location on the screen.
     * 
     * @return the relative location as a {@link Point}
     * @see #setRelativeLocation
     */
    public Point getRelativeLocation() {
        return relativeLocation;
    }

    /**
     * Sets this note's relative location on the screen. This value is not an absolute position, it is a percentage.
     * 
     * @param relativeLocation
     *            the relative location as a {@link Point}
     * @see #getRelativeLocation
     */
    public void setRelativeLocation(Point relativeLocation) {
        Point oldRelativeLocation = getRelativeLocation();
        this.relativeLocation = relativeLocation;
        getNotifier().firePropertyChange(RELATIVE_LOCATION_PROPERTY, oldRelativeLocation, relativeLocation);
    }

    /**
     * Returns this note's size.
     * 
     * @return the size
     * @see #setSize
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Sets this note's size.
     * 
     * @param size
     *            the size
     * @see #getSize
     */
    public void setSize(Dimension size) {
        Dimension oldSize = getSize();
        this.size = size;
        getNotifier().firePropertyChange(SIZE_PROPERTY, oldSize, size);
    }

    /**
     * Returns true if this note displayed on the screen.
     * 
     * @return true if this note is visible
     * @see #setVisible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets whether this note is displayed on the screen or not.
     * 
     * @param visible
     *            true or false
     * @see #isVisible
     */
    public void setVisible(boolean visible) {
        boolean oldVisible = isVisible();
        this.visible = visible;
        getNotifier().firePropertyChange(VISIBLE_PROPERTY, oldVisible, visible);

        if (!visible && isAlwaysOnTop()) {
            setAlwaysOnTop(false);
        }
    }

    /**
     * Returns true if this note is always on top.
     * 
     * @return true if this note is displayed always on top
     * @see #setAlwaysOnTop
     */
    public boolean isAlwaysOnTop() {
        return alwaysOnTop;
    }

    /**
     * Sets whether this note is displayed always on top or not.
     * 
     * @param alwaysOnTop
     *            true of false
     * @see #isAlwaysOnTop
     */
    public void setAlwaysOnTop(boolean alwaysOnTop) {
        boolean oldAlwaysOnTop = isAlwaysOnTop();
        this.alwaysOnTop = alwaysOnTop;
        getNotifier().firePropertyChange(ALWAYS_ON_TOP_PROPERTY, oldAlwaysOnTop, alwaysOnTop);

        if (alwaysOnTop && !isVisible()) {
            setVisible(true);
        }
    }

    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @param font
     *            the font to set
     */
    public void setFont(Font font) {
        Font oldFont = getFont();
        this.font = font;
        getNotifier().firePropertyChange(FONT_PROPERTY, oldFont, font);
    }

    /**
     * @return the fontColor
     */
    public Color getFontColor() {
        return fontColor;
    }

    /**
     * @param fontColor
     *            the fontColor to set
     */
    public void setFontColor(Color fontColor) {
        Color oldFontColor = getFontColor();
        this.fontColor = fontColor;
        getNotifier().firePropertyChange(FONT_COLOR_PROPERTY, oldFontColor, fontColor);
    }

    /**
     * Returns this note's text.
     * 
     * @return the text
     * @see #setText
     */
    public String getText() {
        return text;
    }

    /**
     * Sets this note's text.
     * 
     * @param text
     *            the text
     * @see #getText
     */
    public void setText(String text) {
        String oldText = getText();
        this.text = text;
        getNotifier().firePropertyChange(TEXT_PROPERTY, oldText, text);
    }

    /**
     * Returns the categories this note belongs to.
     * 
     * @return the categories
     * @see #setCategories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets the categories this note belongs to.
     * 
     * @param categories
     * @see #getCategories
     */
    public void setCategories(List<String> categories) {
        List<String> oldCategories = getCategories();
        this.categories = categories;
        getNotifier().firePropertyChange(CATEGORIES_PROPERTY, oldCategories, categories);
    }

    /**
     * Add a new category which this note belongs to.
     * 
     * @param category
     *            the new category
     * @see #removeCategory
     */
    public void addCategory(String category) {
        categories.add(category);
        getNotifier().firePropertyChange(CATEGORIES_PROPERTY, Collections.emptyList(), categories);
    }

    /**
     * Removes a category which this note belongs to.
     * 
     * @param category
     *            the category
     * @see #removeCategory
     */
    public void removeCategory(String category) {
        categories.remove(category);
        getNotifier().firePropertyChange(CATEGORIES_PROPERTY, Collections.emptyList(), categories);
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object instanceof Note) {
            Note note = (Note) object;
            isEquals = getId() == note.getId();
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }
}
