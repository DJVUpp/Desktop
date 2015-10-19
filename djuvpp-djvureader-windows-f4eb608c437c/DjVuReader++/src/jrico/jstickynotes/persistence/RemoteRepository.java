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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import jrico.jstickynotes.model.Note;
import jrico.jstickynotes.util.XmlReaderWriter;

/**
 * Manages the persistence of notes in emails.
 * 
 * @author Jonatan Rico (jrico) jnrico@gmail.com
 * 
 */
public class RemoteRepository implements NoteRepository {

    private static final String FOLDER_NAME = "JStickyNotes";

    private Session session;
    private Store store;
    private Folder folder;

    private String host = "imap.gmail.com";
    private String username = "";
    private String password = "";

    @Override
    public void add(Note note) {
        boolean alreadyOpened = false;
        try {
            alreadyOpened = openSession();
            delete(note);

            MimeMessage message = new MimeMessage(session);
            String xml = XmlReaderWriter.writeObjectsToString(note, note.getCategories());
            message.setSubject(String.valueOf(note.getId()));
            message.setText(xml);
            message.saveChanges();
            folder.appendMessages(new Message[] { message });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!alreadyOpened) {
                closeSession();
            }
        }
    }

    @Override
    public void delete(Note note) {
        boolean alreadyOpened = false;
        try {
            alreadyOpened = openSession();
            Message messages[] = folder.getMessages();
            boolean deleted = false;
            for (int i = 0; i < messages.length && !deleted; i++) {
                Message message = messages[i];
                String subject = message.getSubject();
                if (subject != null && !subject.trim().equals("")) {
                    long id = Long.parseLong(subject);
                    if (id == note.getId()) {
                        message.setFlag(Flag.DELETED, true);
                        folder.expunge();
                        deleted = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!alreadyOpened) {
                closeSession();
            }
        }
    }

    @Override
    public List<Note> retrieve() {
        List<Note> notes = new ArrayList<Note>();
        boolean alreadyOpened = false;
        try {
            alreadyOpened = openSession();

            Message messages[] = folder.getMessages();
            for (int i = 0, n = messages.length; i < n; i++) {
                Note note = XmlReaderWriter.readObjectFromString(messages[i].getContent().toString());
                if (note != null) {
                    notes.add(note);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!alreadyOpened) {
                closeSession();
            }
        }
        return notes;
    }

    @Override
    public void update(Note note) {
        add(note);
    }

    public boolean openSession() throws Exception {
        boolean alreadyOpened = true;
        // Get session
        if (session == null) {
            alreadyOpened = false;
            session = Session.getDefaultInstance(new Properties(), null);
        }

        // Get the store
        if (store == null) {
            alreadyOpened = false;
            store = session.getStore("imaps");
            store.connect(host, username, password);
        }

        // Get folder
        if (folder == null) {
            alreadyOpened = false;
            folder = store.getFolder(FOLDER_NAME);

            if (!folder.exists()) {
                System.out.println("Creating folder");
                folder.create(Folder.HOLDS_MESSAGES);
            }
            folder.open(Folder.READ_WRITE);
        }
        return alreadyOpened;
    }

    public void closeSession() {
        try {
            if (folder != null) {
                folder.close(false);
            }
            if (store != null) {
                store.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            folder = null;
            store = null;
            session = null;
        }
    }
}
