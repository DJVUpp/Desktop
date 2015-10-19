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

import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import jrico.jstickynotes.model.Note;
import jrico.jstickynotes.model.Preferences;

/**
 * Manages the notes and their local (files) or remote (emails) persistence.
 * 
 * @author Jonatan Rico (jrico) jnrico@gmail.com
 * 
 */
public class NoteManager implements PropertyChangeListener {

    private LocalRepository localRepository;

    private RemoteRepository remoteRepository;

    private Map<Note, Note> notes;

    private BlockingQueue<Note> transactions;

    private Preferences preferences;
    Point point;
    public NoteManager(Preferences preferences,Point point1) {
        this.point=point1;
        this.preferences = preferences;
        localRepository = new LocalRepository();
        remoteRepository = new RemoteRepository();
        notes = new HashMap<Note, Note>();
        transactions = new LinkedBlockingQueue<Note>();
        Thread thread = Executors.defaultThreadFactory().newThread(new TransactionCommiter());
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Note note = (Note) pce.getSource();
        if (!pce.getPropertyName().equals(Note.STATUS_PROPERTY)) {
            note.setStatus(Note.MODIFIED_STATUS);
        } else if (note.getStatus() == Note.MODIFIED_STATUS || note.getStatus() == Note.DELETED_STATUS) {
            transactions.offer(note);
        }
    }

    public Note createNote() {
        Note note = new Note();
        note.setId(System.currentTimeMillis());
        note.setCategories(new ArrayList<String>());
        note.setStatus(Note.CREATED_STATUS);
        note.setRelativeLocation(point);
        note.setSize(new Dimension(150, 150));
        note.setColor(preferences.getDefaultNoteColor());
        note.setFont(preferences.getDefaultFont());
        note.setFontColor(preferences.getDefaultFontColor());
        note.addPropertyChangeListener(this);
        notes.put(note, note);
        transactions.offer(note);
        return note;
    }

    public List<Note> getLocalStoredNotes() {
        return getStoredNotes(localRepository);
    }

    public List<Note> getRemoteStoredNotes() {
        return Collections.emptyList();// getStoredNotes(remoteRepository);
    }

    private List<Note> getStoredNotes(NoteRepository noteRepository) {
        List<Note> storedNotes = noteRepository.retrieve();
        for (Note note : storedNotes) {
            note.addPropertyChangeListener(this);
            if (notes.containsKey(note)) {
                Note oldNote = notes.remove(note);
                oldNote.removePropertyChangeListeners();
            }
            notes.put(note, note);
        }
        return storedNotes;
    }

    private class TransactionCommiter implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Note note = transactions.take();
                    if (note.getStatus() == Note.CREATED_STATUS) {
                        note.setStatus(Note.STORED_STATUS);
                        System.out.println("TransactionCommiter.run() - creating the note " + note);
                        localRepository.add(note);
                        if (note.getType() == Note.REMOTE_TYPE) {
                            // remoteRepository.add(note);
                        }
                    } else if (note.getStatus() == Note.MODIFIED_STATUS) {
                        System.out.println("TransactionCommiter.run() - updating the note " + note);
                        note.setStatus(Note.STORED_STATUS);
                        localRepository.update(note);
                        if (note.getType() == Note.REMOTE_TYPE) {
                            // remoteRepository.update(note);
                        }
                    } else if (note.getStatus() == Note.DELETED_STATUS) {
                        System.out.println("TransactionCommiter.run() - removing the note " + note);
                        notes.remove(note);
                        localRepository.delete(note);
                        if (note.getType() == Note.REMOTE_TYPE) {
                            // remoteRepository.delete(note);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
