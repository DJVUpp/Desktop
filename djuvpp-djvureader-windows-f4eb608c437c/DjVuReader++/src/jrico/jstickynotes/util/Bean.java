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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Bean {

    protected PropertyChangeSupport notifier;

    protected synchronized PropertyChangeSupport getNotifier() {
        return notifier == null ? notifier = new PropertyChangeSupport(this) : notifier;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getNotifier().addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        getNotifier().addPropertyChangeListener(property, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getNotifier().removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListeners() {
        getNotifier();
        for (PropertyChangeListener listener : notifier.getPropertyChangeListeners()) {
            notifier.removePropertyChangeListener(listener);
        }
    }
}
