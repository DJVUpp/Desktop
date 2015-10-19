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

package jrico.jstickynotes.persistence;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.UIManager;

import jrico.jstickynotes.JStickyNotes;
import jrico.jstickynotes.model.Preferences;
import jrico.jstickynotes.util.XmlReaderWriter;

public class PreferencesManager implements PropertyChangeListener {

    public static final String PREFERENCES_FILE = JStickyNotes.DIRECTORY + File.separator + "preferences.xml";

    private Preferences preferences;

    public PreferencesManager() {
        File file = new File(PREFERENCES_FILE);
        if (file.exists()) {
            preferences = XmlReaderWriter.readObjectFromFile(PREFERENCES_FILE);
        } else {
            preferences = new Preferences();
            preferences.setDefaultNoteColor(Preferences.DEFAULT_COLOR);
            preferences.setDefaultFont(UIManager.getFont("Label.font"));
            preferences.setDefaultFontColor(Color.BLACK);
            XmlReaderWriter.writeObjectsToFile(PREFERENCES_FILE, preferences);
        }

        preferences.addPropertyChangeListener(this);
    }

    public Preferences getPreferences() {
        return preferences;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("PreferencesManager.propertyChange()");
        XmlReaderWriter.writeObjectsToFile(PREFERENCES_FILE, preferences);

    }
}
