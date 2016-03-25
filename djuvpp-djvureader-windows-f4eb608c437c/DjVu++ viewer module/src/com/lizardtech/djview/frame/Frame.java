package com.lizardtech.djview.frame;

import com.lizardtech.djview.Applet;
import com.lizardtech.djview.FullBookView;
import com.lizardtech.djvu.DjVuOptions;
import com.lizardtech.djvu.Document;
import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.outline.OutlineTabbedPane;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

/**
 * A class for displaying djvu documents with javaw. Very simmular to the DjVuLibre djview command.
 *
 * @author Bill C. Riemers
 * @version $Revision: 1.10 $
 */
public final class Frame extends JFrame implements AppletStub {

    // ~ Static fields/initializers
    // ---------------------------------------------
    private static final Class classApplet;
    public static DjVuBean Bean;
    private static final Method isValidDjVuMethod;
    private static final String classAppletName = "com.lizardtech.djview.Applet";
    public static Document document;
    public JPanel CardPanel;

    static {

        Class xclassApplet = null;
        try {
            xclassApplet = Class.forName(classAppletName);
        } catch (final ClassNotFoundException exp) {
            exp.printStackTrace(DjVuOptions.err);
            System.exit(1);
        }
        classApplet = xclassApplet;
        Method xisValidDjVuMethod = null;
        try {
            xisValidDjVuMethod = classApplet.getMethod("isValidDjVu", null);
        } catch (final Throwable exp) {
            exp.printStackTrace(DjVuOptions.err);
            System.exit(1);
        }
        isValidDjVuMethod = xisValidDjVuMethod;
    }

    // ~ Instance fields
    // --------------------------------------------------------
    private final AppletContext appletContext;
    private final Hashtable parameters = new Hashtable();
    public OutlineTabbedPane Thumbpanel;
    public FullBookView FullBook;

    private final TextField input = new TextField();
    private URL documentBase = null;

    private boolean isDjVu = false;
    private HashMap<String, Component> componentMap;

    /**
     * Creates a new Frame object.
     */
    public Frame() {
        this(null);
    }

    /**
     * Creates a new Frame object.
     *
     * @param url The initial URL to load.
     */
    public Frame(final String url) {

        appletContext = new AppletContext() {

            public void showStatus(final String status) {

                DjVuOptions.out.println("status: " + status);
            }

            public void showDocument(final URL url, String target) {
                final Frame f = new Frame();
                f.setURL(url);

            }

            public void showDocument(final URL url) {

                setURL(url);

            }

            public void setStream(final String key, InputStream stream) {
            }

            public Iterator getStreamKeys() {
                return null;
            }

            public InputStream getStream(final String key) {
                return null;
            }

            public Image getImage(final URL url) {
                return null;
            }

            public AudioClip getAudioClip(final URL url) {
                return null;
            }

            public Enumeration getApplets() {
                return null;
            }

            public Applet getApplet(final String name) {
                return null;
            }
        };

        try {

            documentBase = (new File(System.getProperties().getProperty("user.dir", "/"), "/index.djvu")).toURL();

        } catch (final MalformedURLException ignored) {

        }

        setURL(url);

    }

    /**
     * Get the applet context, used for retrieving web contents.
     *
     * @return null
     */
    public AppletContext getAppletContext() {
        return appletContext;
    }

    /**
     * Get the codebase for the applet.
     *
     * @return null.
     */
    public URL getCodeBase() {
        return null;
    }

    /**
     * Get the document base for the applet.
     *
     * @return null.
     */
    public URL getDocumentBase() {

        return documentBase;
    }

    /**
     * Lookup a parameter.
     *
     * @param name The name of the parameter to lookup.
     *
     * @return the parameter value.
     */
    public String getParameter(String name) {
        Object retval = parameters.get(name);

        return (retval != null) ? retval.toString() : null;
    }

    /**
     * Set the URL to display in this frame.
     *
     * @param url to display.
     */
    public void setURL(final String url) {
        if ((url == null) || (url.length() == 0)) {
            setURL((URL) null);
        } else {
            try {
                setURL(new URL(getDocumentBase(), url));
            } catch (final MalformedURLException ignored) {
            }
        }
    }

    /**
     * Set the URL to display in this frame.
     *
     * @param url to display.
     */
    public void setURL(final URL url) {
        String urlString = "";

        Component component = null;

        if (url != null) {
            urlString = url.toString();
            JEditorPane jeditorPane = null;

            if (jeditorPane == null) {
                try {
                    document = new Document(url);
                    // TODO: create a method/setter to to this.
                    com.lizardtech.djvubean.outline.CreateThumbnails.document = document;
                } catch (final Throwable exp) {
                    try {
                        jeditorPane = new JEditorPane(url);
                    } catch (final Throwable ignored) {
                    }
                }
            } else if ((jeditorPane != null)) {
                try {
                    component = new JScrollPane(jeditorPane);
                    jeditorPane.setCaretPosition(0);
                } catch (Throwable ignored) {
                }
            }

            if (component == null) {
                parameters.put("data", urlString);

                try {
                    final Applet applet = (Applet) classApplet.newInstance();
                    applet.setStub(this);
                    applet.init();

                    Thumbpanel = new OutlineTabbedPane(Bean, this);
                    component = applet;
                    CardPanel = new JPanel(new CardLayout());

                    FullBook = new FullBookView(Bean, this, CardPanel);
                    com.lizardtech.djvubean.DjVuBean.frame = this;
                    isDjVu = ((Boolean) isValidDjVuMethod.invoke(applet, null)).booleanValue();
                } catch (final Throwable exp) {
                    exp.printStackTrace(DjVuOptions.err);
                    component = new TextArea("No Data Loaded");
                    isDjVu = false;
                }

            }
        } else {
            isDjVu = false;
        }

        CardPanel.add(component, "Bean");
        add(CardPanel, BorderLayout.CENTER);
        validate();
    }

    /**
     * Called by the buttons and text fields when an update occures.
     *
     * @param e DOCUMENT ME!
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }

    /**
     * This applet may also be invoked as a program using javaw.
     *
     * @param args Should contain the target URL.
     */
    /**
     * Test if the applet active?
     *
     * @return true
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Resize the window.
     *
     * @param width The new window width.
     * @param height The new window height.
     */
    public void appletResize(int width, int height) {
        setSize(width, height);
    }

    private void createComponentMap() {
        componentMap = new HashMap<String, Component>();
        Component[] components = this.getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
    }

    public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        } else {
            return null;
        }
    }

}
