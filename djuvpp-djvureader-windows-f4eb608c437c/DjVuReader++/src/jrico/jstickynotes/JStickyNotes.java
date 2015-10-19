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

package jrico.jstickynotes;

import com.lizardtech.djview.frame.DjvuStart;
import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.RibbonMenu.DjvuComponents;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import jrico.jstickynotes.gui.Icon;
import jrico.jstickynotes.gui.PreferencesDialog;
import jrico.jstickynotes.gui.StickyNote;
import jrico.jstickynotes.model.Note;
import jrico.jstickynotes.persistence.NoteManager;
import jrico.jstickynotes.persistence.PreferencesManager;

public class JStickyNotes implements PropertyChangeListener, ActionListener ,MouseListener{

    public static final String DIRECTORY = System.getProperty("user.home") + File.separator + ".jstickynotes";

    public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("jrico.jstickynotes.resource.jstickynotes");

    public static final String JSTICKYNOTES_TEXT = BUNDLE.getString("JStickyNotes.text");
    public static final String CREATE_NOTE_TEXT = BUNDLE.getString("JStickyNotes.createNoteItem.text");
    public static final String SHOW_ALL_NOTES_TEXT = BUNDLE.getString("JStickyNotes.showAllItem.text");
    public static final String HIDE_ALL_NOTES_TEXT = BUNDLE.getString("JStickyNotes.hideAllItem.text");
    public static final String PREFERENCES_TEXT = BUNDLE.getString("JStickyNotes.preferencesItem.text");
    public static final String ABOUT_TEXT = BUNDLE.getString("JStickyNotes.aboutItem.text");
    public static final String ABOUT_DIALOG_TEXT = BUNDLE.getString("JStickyNotes.aboutDialog.text");
    public static final String ABOUT_DIALOG_TITLE_TEXT = BUNDLE.getString("JStickyNotes.aboutDialogTitle.text");
    public static final String EXIT_TEXT = BUNDLE.getString("JStickyNotes.exitItem.text");

    public static final int NONE = 0;
    public static final int ALWAYS_ON_TOP = 1;
    public static final int VISIBLE = 2;
    public static final int ALL = 3;

    private PreferencesManager preferencesManager;
    private NoteManager noteManager;
    private Map<Note, StickyNote> stickyNotes;
    private JFrame frame=DjvuStart.djvu;
    private StickyNote childWindowParent;
    private int showMode;
   
    private final DjVuBean djvuBean;
    File storenotefile;
    Point start;
  

    public JStickyNotes(DjVuBean bean) {
       
        this.djvuBean = bean;
        storenotefile =new File("x.txt");
        if(!storenotefile.exists()){
            try {
                BufferedWriter Bw=new BufferedWriter(new FileWriter(storenotefile));
            } catch (IOException ex) {
                Logger.getLogger(JStickyNotes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.djvuBean.addMouseListener(this);
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            System.out.println("Creating the directory: " + DIRECTORY);
            directory.mkdir();
        }
        preferencesManager = new PreferencesManager();
        noteManager = new NoteManager(preferencesManager.getPreferences(),start);
        stickyNotes = new HashMap<Note, StickyNote>();
        new StoredNotesRetriever().execute();
    }


    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        Object source = pce.getSource();
        if (source instanceof Note && pce.getNewValue().equals(Note.DELETED_STATUS)) {
            stickyNotes.remove(source);
        } else if (source instanceof StickyNote) {
            childWindowParent = ((Boolean) pce.getNewValue()) ? (StickyNote) source : null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = ((MenuItem) e.getSource()).getLabel();
        if (CREATE_NOTE_TEXT.equals(label)) {
            createNote();
        } else if (SHOW_ALL_NOTES_TEXT.equals(label)) {
            showNotes(ALL);
        } else if (HIDE_ALL_NOTES_TEXT.equals(label)) {
            showNotes(NONE);
        } else if (PREFERENCES_TEXT.equals(label)) {
            new PreferencesDialog(frame, preferencesManager.getPreferences()).setVisible(true);
        } else if (ABOUT_TEXT.equals(label)) {
            JLabel about = new JLabel(ABOUT_DIALOG_TEXT);
            about.setIcon(Icon.getJStickyNotesImageIcon(48));
            JOptionPane.showMessageDialog(frame, about, ABOUT_DIALOG_TITLE_TEXT, JOptionPane.PLAIN_MESSAGE);
        } else if (EXIT_TEXT.equals(label)) {
            System.exit(0);
        }
    }

    public void createNote() {

        try {
            boolean flage = true;
//            Formatter output = new Formatter("x.txt");
//            output.close();
            
            Scanner input = new Scanner(storenotefile);
            String bookname = DjvuStart.tabbedPane.getTitleAt(DjvuStart.tabbedPane.getSelectedIndex()).replaceAll(" ", "");
            System.out.println("************ "+bookname);
            int pagenum = djvuBean.getPage();
            long nodeid = 0;
//            System.out.println("111111111111  "+input.next());
            while (input.hasNext()) {
                //System.out.println(input.next());
              //  System.out.println("inside the wile\n"+input.next()+"( "+bookname+") "+input.nextInt()+"("+pagenum+" ) "+input.nextLong());
                if (input.next().equals(bookname) && input.nextInt() == pagenum) {
                                        System.out.println("equqls "+bookname);
                    long id_note=(Long.parseLong(input.next()));
                    nodeid = id_note;
                    System.out.println("777  "+nodeid + "    >>>>> "+stickyNotes.keySet().toArray());
                    for (Note wnote : stickyNotes.keySet()) {
                        System.out.println(wnote.getId());
                        if (wnote.getId() == nodeid) {
                            System.out.println("------------"+wnote.getText());
                            StickyNote stickyNote = stickyNotes.get(wnote);
                            stickyNote.setVisible(true);
                            flage = false;
                            break;
                        }
                    }

                }

            }
            if (flage) {
                System.out.println("inside the create");
                   // storenotefile = new File("booknotes");
                    FileWriter fw=new FileWriter(storenotefile,true);
                    BufferedWriter bw=new BufferedWriter(fw);
               // Formatter oo = new Formatter("x.txt");
//
                Note note = noteManager.createNote();
                note.setVisible(true);
                note.addPropertyChangeListener(Note.STATUS_PROPERTY, this);
                StickyNote stickyNote = new StickyNote(DjvuStart.djvu, note, start);
                stickyNote.addPropertyChangeListener(StickyNote.CHILD_WINDOW_OPENED, this);
                stickyNotes.put(note, stickyNote);
                stickyNote.startEditingText();
                nodeid=note.getId();
                    bw.newLine();
                    bw.write(bookname+" ");
                    bw.write(String.valueOf(pagenum)+" ");
                    bw.write(String.valueOf(nodeid));
                    bw.newLine();
                    bw.close();
                //oo.format("%s %d %d", bookname,pagenum,nodeid);
                System.out.println("########### "+nodeid);
                //oo.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(JStickyNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.djvuBean.removeMouseListener(this);
        
    }

    public void showNote() {
        try {
            
            
            this.djvuBean.removeMouseListener(this);
             boolean flage = true;
            Scanner input = new Scanner(storenotefile);
            String bookname = DjvuStart.tabbedPane.getTitleAt(DjvuStart.tabbedPane.getSelectedIndex()).replaceAll(" ", "");
            System.out.println("************ "+bookname);
            int pagenum = djvuBean.getPage();
            long nodeid = 0;
//            System.out.println("111111111111  "+input.next());
            while (input.hasNext()) {
                //System.out.println(input.next());
              //  System.out.println("inside the wile\n"+input.next()+"( "+bookname+") "+input.nextInt()+"("+pagenum+" ) "+input.nextLong());
                if (input.next().equals(bookname) && input.nextInt() == pagenum) {
                                        System.out.println("equqls "+bookname);
                    long id_note=(Long.parseLong(input.next()));
                    nodeid = id_note;
                    System.out.println("777  "+nodeid + "    >>>>> "+stickyNotes.keySet().toArray());
                    for (Note wnote : stickyNotes.keySet()) {
                        System.out.println(wnote.getId());
                        if (wnote.getId() == nodeid) {
                            System.out.println("------------"+wnote.getText());
                            StickyNote stickyNote = stickyNotes.get(wnote);
                            stickyNote.setVisible(true);
                           flage=false;
                            break;
                        }
                    }

                }

            }
            if (flage) {
            int op = JOptionPane.showConfirmDialog(djvuBean, "there is no notes in this page,"
                    + "click ok to add one or cancel", "notes", 0);
            if (op == 0) {
                createNote();
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(JStickyNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void showNotes(int mode) {
        showMode = mode;
        for (Note note : stickyNotes.keySet()) {
            StickyNote stickyNote = stickyNotes.get(note);
            if (mode == NONE) {
                stickyNote.setVisible(false);
            } else if (mode == ALWAYS_ON_TOP) {
                stickyNote.setVisible(note.isAlwaysOnTop());
            } else if (mode == VISIBLE) {
                stickyNote.setVisible(note.isVisible());
            } else if (mode == ALL) {
                stickyNote.setVisible(true);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("rrrr");
        djvuBean.requestFocus();
        start = new Point(
                e.getX(),
                e.getY());
       
      if(DjvuComponents.Notestatus.equals("Create")){
          createNote();
      }else{
          showNote();
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class StoredNotesRetriever extends SwingWorker<Void, List<Note>> {
        @Override
        @SuppressWarnings("unchecked")
        protected Void doInBackground() throws Exception {
            publish(noteManager.getLocalStoredNotes());
            System.out.println("JStickyNotes.StoredNotesRetriever.doInBackground() - local stored notes retrieved");
            publish(noteManager.getRemoteStoredNotes());
            System.out.println("JStickyNotes.StoredNotesRetriever.doInBackground() - remote stored notes retrieved");
            return null;
        }

        @Override
        protected void process(List<List<Note>> chunks) {
            for (List<Note> notes : chunks) {
                for (Note note : notes) {
                    if (stickyNotes.containsKey(note)) {
                        StickyNote oldStickyNote = stickyNotes.get(note);
                        oldStickyNote.dispose();
                    }
                    note.addPropertyChangeListener(Note.STATUS_PROPERTY, JStickyNotes.this);
                    StickyNote stickyNote = new StickyNote(frame, note,start);
                    stickyNote.addPropertyChangeListener(StickyNote.CHILD_WINDOW_OPENED, JStickyNotes.this);
                    stickyNotes.put(note, stickyNote);
                }
            }
        }
    }

//    public static void main(String[] args) throws Exception {
////        for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
////            if (lafi.getName().equals("Nimbus")) {
////                UIManager.setLookAndFeel(lafi.getClassName());
////            }
////        }
////        SwingUtilities.invokeLater(new JStickyNotes());
//    }
}
