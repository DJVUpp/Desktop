package com.lizardtech.djview.frame;

import RecentFile.FileSavePath;
import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.RibbonMenu.DjvuComponents;
import com.lizardtech.djvubean.RibbonMenu.RibbonMenuCreation;
import com.newgroup.tabs.BasicTabUI;
import com.newgroup.tabs.ITabFactory;
import com.newgroup.tabs.ITabbedPaneWindow;
import com.newgroup.tabs.ITabbedPaneWindowFactory;
import com.newgroup.tabs.Tab;
import com.newgroup.tabs.jhrome.JhromeTabbedPaneUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

@SuppressWarnings("serial")
public class DjvuStart extends RibbonMenuCreation implements ITabbedPaneWindow, ITabbedPaneWindowFactory, ITabFactory {

    /**
     * used to create the main window of program
     */
    public static DjvuStart djvu;
    /**
     * used to set bean for each tab
     */
    private static final DjvuComponents Band = new DjvuComponents();
    /**
     * used to store the all opening book while the program is active store the
     * URL for book
     */
    public static ArrayList<String> bookList = new ArrayList<String>();
    /**
     * store the beans of opening book in hashmap key is the URL of the this
     * book value is the Bean
     */
    public static HashMap<String, DjVuBean> beanMap = new HashMap<String, DjVuBean>();
    /**
     * store the name of books by store the URL of this book key ->URL of book
     * value ->name of book
     */
    public static Map<String, String> url_name = new ConcurrentHashMap<String, String>();
    /**
     * store the url of book by name of this book key -> name of book value ->
     * the URL of this book
     */
    public static Map<String, String> name_url = new ConcurrentHashMap<String, String>();
    /**
     * store the current opening book
     */
    public static ArrayList<String> curropen;
    private static String[] argsurls;
    public static ArrayList unsavedbook = new ArrayList();

    /**
     * the entry point of application
     *
     * @param args (urls of book which will be open)
     */
    public static void main(final String[] args) {
        djvu.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
                    DjvuStart f = null;
                    if (args.length > 0) {
                        f = new DjvuStart();
                        f.start();
                        //f.argsstart(args);
                        argsurls = args;
                    } else {
                        f = new DjvuStart();
                        f.start();
                    }

                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public DjvuStart() throws IOException {
        initGUI();
    }

    private void argsstart(String[] args) throws IOException {
        for (String url : args) {
            FileSavePath.save(url);
            String path;
            path = new File(url).toURI().toURL().toString();
            String url2 = path.substring(5);
            url = url2;
            String name = url.substring(url.lastIndexOf('/') + 1, url.length());
            name = name.replaceAll("%20", " ");
            if (!curropen.contains(name)) {
                name_url.put(name, url);
                url_name.put(url, name);
                curropen.add(name);
                bookList.add(url);
                openBookInNewTab(url, name);
            } else {
                tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
            }

        }
    }

    private void initGUI() throws IOException {

        File fil5 = new File("C:\\DjVu++Task");
        if (!fil5.exists()) {
            fil5.mkdir();
        }

        //Recent check the file is found
        File file = new File("RecentBooks.txt");
        if (!file.exists()) {
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
        }
        setTitle("Djvu++");
        tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new JhromeTabbedPaneUI());
        tabbedPane.putClientProperty(JhromeTabbedPaneUI.TAB_CLOSE_BUTTONS_VISIBLE, true);

        add(tabbedPane, BorderLayout.CENTER);
        SetRibbonMenu(this);
        add(new StatusBar().getStatusBar(), BorderLayout.SOUTH);

        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public synchronized void stateChanged(ChangeEvent e) {
                updateTitle();
                if (tabbedPane.getTabCount() > 0) {
                    Band.setbean(beanMap.get(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()))));

                    String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                    if (!tabName.equals("GetStart")) {
                        Band.EnableForDjvuButton();
                    } else {
                        Band.DisenableForDjvuButton();
                    }
                    curropen = new ArrayList<String>();
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        curropen.add(tabbedPane.getTitleAt(i));

                    }

                    for (Map.Entry<String, DjVuBean> entry : beanMap.entrySet()) {
                        final String bookname = entry.getKey();
                        // DjVuBean djVuBean = entry.getValue();
                        if (!curropen.contains(bookname)) {
                            beanMap.remove(name_url.get(bookname));
                            Thread t = new Thread(new Runnable() {

                                public void run() {
                                    for (String string : bookList) {
                                        File file = new File(string);
                                        if (file.getName().equals(bookname)) {
                                            bookList.remove(string);
                                        }
                                    }
                                }
                            });

                        }

                    }

                }
            }
        });
        tabbedPane.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentRemoved(ContainerEvent e) {
                if (tabbedPane.getTabCount() == 0) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Band.DisenableForDjvuButton();
                }

            }
        });
        tabbedPane.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("indexForTitle".equals(evt.getSource())) {
                    updateTitle();

                }

            }
        });
    }

    public synchronized void opendialog() throws IOException {

        FileDialog fd = new FileDialog(this, "open djvu file", FileDialog.LOAD);
        fd.setMultipleMode(true);
        fd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));
        fd.show();
        if (fd.getDirectory() != null) {
            File files[] = fd.getFiles();
            for (File file : files) {
                FileSavePath.save(file.getPath());

                String url;
                url = "" + file.toURI().toURL();
                url = url.substring(5, url.length());
                String name = file.getName();
                if (!curropen.contains(name)) {
                    name_url.put(name, url);
                    url_name.put(url, name);
                    curropen.add(name);
                    bookList.add(url);
                    openBookInNewTab(url, name);

                } else {
                    tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
                }
            }

        } else {
        }

    }

    public static void openBookInNewTab(final String url, String name) {

        //SwingUtilities.invokeLater(new Runnable() {
        //  @Override
        //public void run() {
        name = url_name.get(url);
        if (!curropen.contains(name)) {
            name_url.put(name, url.substring(1));
            url_name.put(url.substring(1), name);
            curropen.add(name);
            bookList.add(url.substring(1));

        }
        Frame f = new Frame(url);

        Container pane = f.getContentPane();
        tabbedPane.add(name, pane);

        tabbedPane.setSelectedComponent(pane);

        String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        if (!tabName.equals("GetStart")) {
            Band.setbean(beanMap.get(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()))));
        }

        //  }
        //});
    }

    public void updateTitle() {
        int index = tabbedPane.getSelectedIndex();
        setTitle(index < 0 ? "Djvu++" : "                                            Djvu++ - " + tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
    }

    public static JTabbedPane tabbedPane;

    public Tab createTab() {
        return new Tab();
    }

    public Tab createTabWithContent() {
        Tab tab = new Tab();
        tab.setTitle("GetStart");
        // tab.setContent(new JPanel());
        return tab;
    }

    public ITabbedPaneWindow createWindow() {
        try {
            return new DjvuStart();
        } catch (IOException ex) {
            Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public Window getWindow() {
        return this;
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    try {
                        djvu = new DjvuStart();
                    } catch (IOException ex) {
                        Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Tab newTab = djvu.createTabWithContent();

                    djvu.getTabbedPane().addTab(newTab.getTitle(), new StartPage().getContentPane());
                    Toolkit tk = Toolkit.getDefaultToolkit();
                    int xSize = ((int) tk.getScreenSize().getWidth());

                    djvu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    djvu.setSize(xSize - 50, 600);
                    djvu.setLocationRelativeTo(null);
                    djvu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    djvu.setVisible(true);
                    djvu.setApplicationIcon(getResizableIconFromResource("/images/DjvuIcon.png"));
                    djvu.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                String[] buttons = {"Close all tabs", "Close current tab"};

                                int rc = JOptionPane.showOptionDialog(null, "Do you wanted to close all tabs or current tab ?", "Confirmation",
                                        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
                                 File del = new File("C:\\DjVu++Task\\" + tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
                                switch (rc) {
                                    case 0:
                                        djvu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        if (del.exists()) {
                                                    del.delete();
                                                }
                                        break;
                                    case 1:
                                         if (!unsavedbook.contains(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))) {
                                            beanMap.remove(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())));
                                            tabbedPane.remove(tabbedPane.getSelectedComponent());
                                        } else if (unsavedbook.contains(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))) {
                                            String[] buttonss = {"YES", "NO"};
                                            int out = JOptionPane.showOptionDialog(null, "Do you want save before close ?", "Close",
                                                    JOptionPane.WARNING_MESSAGE, 0, null, buttonss, buttonss[0]);

                                            if (out == 0) {
                                                FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save DjVu", FileDialog.SAVE);
                                                SaveDjVu.setVisible(true);
                                                String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                                                String path1 = path + ".djvu";
                                                Path p1 = Paths.get("C:\\DjVu++Task\\" + tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
                                                Path p2 = Paths.get(path1);

                                 
                                                if (SaveDjVu.getFile() != null) {
                                                    try {
                                                        Files.copy(p1, p2, REPLACE_EXISTING);
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(BasicTabUI.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    beanMap.remove(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())));
                                                    tabbedPane.remove(tabbedPane.getSelectedComponent());

                                                   if (del.exists()) {
                                                    del.delete();
                                                }

                                                }

                                            }
                                            if (out == 1) {
                                                beanMap.remove(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())));
                                                tabbedPane.remove(tabbedPane.getSelectedComponent());
                                                
                                                if (del.exists()) {
                                                    del.delete();
                                                    del.deleteOnExit();
                                                }
                                            }
                                        }

//                                    if(unsavedbook.contains(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))){
//                                    int y=JOptionPane.showConfirmDialog(djvu, "Do you want save ?");
// 
//                                        System.out.println(y);
//                                    }
                                        break;
                                }
                            } catch (HeadlessException t) {
                                djvu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                        }
                    });
                    if (argsurls != null) {
                        argsstart(argsurls);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DjvuStart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

}
