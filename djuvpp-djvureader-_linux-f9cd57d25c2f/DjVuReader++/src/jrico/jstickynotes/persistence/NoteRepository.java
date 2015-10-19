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

package jrico.jstickynotes.persistence;

import java.util.List;

import jrico.jstickynotes.model.Note;

/**
 * Specifies the methods a note repository must implement in order to add, update, delete and retrieve notes from a
 * persistence unit.
 * 
 * @author Jonatan Rico (jrico) jnrico@gmail.com
 * 
 */
public interface NoteRepository {

    /**
     * Adds this note to the persistence unit.
     * 
     * @param note
     *            the note
     */
    public void add(Note note);

    /**
     * Updates the existing note in the persistence unit.
     * 
     * @param note
     *            the note
     */
    public void update(Note note);

    /**
     * Deletes this note from the persistence unit.
     * 
     * @param note
     *            the note
     */
    public void delete(Note note);

    /**
     * Retrieves all notes stored in the persistence unit.
     * 
     * @return a {@link List} of all stored notes
     */
    public List<Note> retrieve();

}
