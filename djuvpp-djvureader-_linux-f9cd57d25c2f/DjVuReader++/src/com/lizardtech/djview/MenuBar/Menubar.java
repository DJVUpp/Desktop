package com.lizardtech.djview.MenuBar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lizardtech.djvu.DjVuOptions;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.toolbar.ToggleButton;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class Menubar extends Component
        implements ActionListener, ItemListener, PropertyChangeListener {

    DjVuBean djvubean = null;
    // for menubar bar
    protected final JMenuBar jmenubar = new JMenuBar();
    // create file menu
    protected final JMenu filemenu = new JMenu("File");
    // create edit menu
    protected final JMenu editmenu = new JMenu("Edit");
    // create view menu
    protected final JMenu viewmenu = new JMenu("View");
    // create help menu
    protected final JMenu helpmenu = new JMenu("Help");
    // create text menu
    protected final JMenu toolmenu = new JMenu("Tools");

    /**
     * ******* create items for file menu *********
     */
    protected final JMenuItem openfileItem = new JMenuItem("Open");
    protected final JMenuItem closeItem = new JMenuItem("Close");
    protected final JMenuItem saveItem = new JMenuItem("Save");
    protected final JMenu saveasSubMenu = new JMenu("Save as");
    // items for saveas sub menu
    protected final JMenuItem savePDFItem = new JMenuItem("As PDF");
    protected final JMenuItem saveDJVUItem = new JMenuItem("As DJVU");
    protected final JMenuItem saveWORDItem = new JMenuItem("As WORD");

    protected final JMenuItem printItem = new JMenuItem("Print");
    protected final JMenu shareSubMenu = new JMenu("Share");
    // items for share sub menu
    protected final JMenuItem emailItem = new JMenuItem("E_Mail");
    protected final JMenuItem facebookTwitterItem = new JMenuItem("Facebook/Twitter");
    protected final JMenuItem recentItem = new JMenuItem("Recent Files");
    // items for recent files sub menu

    protected final JMenuItem exitItem = new JMenuItem("exit");

    /**
     * ******* create items for edit menu *********
     */
    protected final JMenuItem undoItem = new JMenuItem("Undo");
    protected final JMenuItem redoItem = new JMenuItem("Redo");
    protected final JMenuItem cutItem = new JMenuItem("Cut");
    protected final JMenuItem copyItem = new JMenuItem("Copy");
    protected final JMenuItem PasteItem = new JMenuItem("Paste");
    protected final JMenuItem deleteItem = new JMenuItem("Delete");
    protected final JMenuItem selectAllItem = new JMenuItem("Select All");
    protected final JMenuItem DeselectAllItem = new JMenuItem("Deselect All");

    /**
     * ******* create items for view menu *********
     */
    protected final JMenu rotateViewSubMenu = new JMenu(" Rotate View ");
    // items for rotate menu item
    protected final JMenuItem rotateLeftItem = new JMenuItem("Rotate Left");
    protected final JMenuItem rotateRightItem = new JMenuItem("Rotate Right");

    protected final JMenu pageNavigationSubMenu = new JMenu(" Page Navigaion ");
    // items for page navigation menu item
    protected final JMenuItem firstpageItem = new JMenuItem("First Page");
    protected final JMenuItem lastpageItem = new JMenuItem("Last Page");
    protected final JMenuItem nextpageItem = new JMenuItem("Next Page");
    protected final JMenuItem prevpageItem = new JMenuItem("Previous Page");
    protected final JMenuItem pageNumberItem = new JMenuItem("Page Number");

    protected final JMenu pageDisplaySubMenu = new JMenu(" page Display ");
    // items for page display menu item
    protected final JMenuItem singlePageViewItem = new JMenuItem("Single Page view");
    protected final JMenuItem EnableScrolling = new JMenuItem("Enable Scrolling");
    protected final JMenuItem towPageViewItem = new JMenuItem("Tow Page View");
    protected final JMenuItem towPageScrolling = new JMenuItem("Tow Page Scrolling");

    protected final JMenu zoomSubMenu = new JMenu(" Zoom ");
    // items for zoom menu item
    protected final JMenuItem dynamicZoomItem = new JMenuItem("Dynamic Zoom Item");
    protected final JMenuItem marqueeZoomItem = new JMenuItem("Marquee Zoom Item");
    protected final JMenuItem zoomToItem = new JMenuItem("zoom To");
    protected final JMenuItem zoomInItem = new JMenuItem("zoom In");
    protected final JMenuItem zoomOutItem = new JMenuItem("zoom Out");
    protected final JMenuItem actualSizeItem = new JMenuItem("Actual Size");
    protected final JMenuItem fitPageItem = new JMenuItem("Fit Page");
    protected final JMenuItem fitWidthItem = new JMenuItem("Fit Width");

    protected final JMenuItem stickyNoteItem = new JMenuItem("Sticky Note");
    protected final JMenuItem HighlightItem = new JMenuItem("Highlight Text");
    protected final JMenuItem readModeItem = new JMenuItem("Read Mode");
    protected final JMenuItem fullScreanItem = new JMenuItem("Full Screan");

    /**
     * ******* create items for help menu *********
     */
    protected final JMenuItem aboutUsMenuItem = new JMenuItem("About Us ");

    /**
     * ******* create items for tool menu *********
     */
    protected final JMenuItem selectModeItem = new JMenuItem("Select Mode");
    protected final JMenuItem panModeItem = new JMenuItem("Hand Mode");
    protected final JMenuItem textModeItem = new JMenuItem("Text Mode");
    protected final JMenuItem takeSnapshotItem = new JMenuItem("Take a Snapshot");
    protected final JMenuItem SearchInFileItem = new JMenuItem("Search In File");
    protected final JMenuItem searchInInternetItem = new JMenuItem("Search in Internet");
    protected final JMenuItem translateItem = new JMenuItem("Translate");
    protected final JMenuItem CopyToClipBoardItem = new JMenuItem("Copy To Clipboard");

    public Menubar() {

        jmenubar.setVisible(true);
// add menus in menubar
        jmenubar.add(filemenu);
        jmenubar.add(editmenu);
        jmenubar.add(viewmenu);
        jmenubar.add(toolmenu);
        jmenubar.add(helpmenu);

// add menu items in file menu
        
        filemenu.add(openfileItem);
        filemenu.add(closeItem);
        filemenu.addSeparator();
        filemenu.add(saveItem);
        filemenu.add(saveasSubMenu);
        saveasSubMenu.add(saveDJVUItem);
        saveasSubMenu.add(savePDFItem);
        saveasSubMenu.add(saveWORDItem);
        filemenu.addSeparator();
        
       
        
        filemenu.add(printItem);
        filemenu.add(shareSubMenu);
        shareSubMenu.add(emailItem);
        shareSubMenu.add(facebookTwitterItem);
        filemenu.addSeparator();
        filemenu.add(recentItem);

        filemenu.add(exitItem);
        exitItem.addActionListener(this);

// add menu items in edit menu
        undoItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println(djvubean.getSize());
            }
        });

        editmenu.add(redoItem);
        editmenu.addSeparator();
        editmenu.add(cutItem);
        editmenu.add(copyItem);
        editmenu.add(PasteItem);
        editmenu.add(deleteItem);
        editmenu.addSeparator();
        editmenu.add(selectAllItem);
        editmenu.add(DeselectAllItem);

// add menu items in view menu
        viewmenu.add(rotateViewSubMenu);
        rotateViewSubMenu.add(rotateLeftItem);
        rotateViewSubMenu.add(rotateRightItem);
        viewmenu.add(pageNavigationSubMenu);
        pageNavigationSubMenu.add(firstpageItem);
        pageNavigationSubMenu.add(nextpageItem);
        pageNavigationSubMenu.add(prevpageItem);
        pageNavigationSubMenu.add(lastpageItem);
        pageNavigationSubMenu.add(pageNumberItem);
        viewmenu.addSeparator();
        viewmenu.add(pageDisplaySubMenu);
        pageDisplaySubMenu.add(singlePageViewItem);
        pageDisplaySubMenu.add(EnableScrolling);
        pageDisplaySubMenu.add(towPageViewItem);
        pageDisplaySubMenu.add(towPageScrolling);
        viewmenu.add(zoomSubMenu);
        zoomSubMenu.add(zoomToItem);
        zoomSubMenu.add(zoomInItem);
        zoomSubMenu.add(zoomOutItem);
        zoomSubMenu.add(dynamicZoomItem);
        zoomSubMenu.add(marqueeZoomItem);
        zoomSubMenu.addSeparator();
        zoomSubMenu.add(actualSizeItem);
        zoomSubMenu.add(fitPageItem);
        zoomSubMenu.add(fitWidthItem);
        viewmenu.addSeparator();
        viewmenu.add(stickyNoteItem);
        viewmenu.add(HighlightItem);
        viewmenu.addSeparator();
        viewmenu.add(readModeItem);
        viewmenu.add(fullScreanItem);

// add menu items in tool menu
        toolmenu.add(selectModeItem);
        toolmenu.add(panModeItem);
        toolmenu.add(textModeItem);
        toolmenu.addSeparator();
        toolmenu.add(takeSnapshotItem);
        toolmenu.addSeparator();
        toolmenu.add(SearchInFileItem);
        toolmenu.add(searchInInternetItem);
        toolmenu.add(translateItem);
        toolmenu.addSeparator();
        toolmenu.add(CopyToClipBoardItem);

// add menu items in help menu
        helpmenu.add(aboutUsMenuItem);

        lastpageItem.addActionListener(this);
        firstpageItem.addActionListener(this);
        prevpageItem.addActionListener(this);
        nextpageItem.addActionListener(this);

        fitWidthItem.addActionListener(this);
        fitPageItem.addActionListener(this);
        actualSizeItem.addActionListener(this);
        zoomInItem.addActionListener(this);
        zoomOutItem.addActionListener(this);

        marqueeZoomItem.addActionListener(this);
        selectModeItem.addActionListener(this);
        panModeItem.addActionListener(this);
        textModeItem.addActionListener(this);
        SearchInFileItem.addActionListener(this);
        
    }

    public JMenuBar getMenubar() {
        return this.jmenubar;
    }

    public JMenuItem getopenitem() {
        return openfileItem;
    }

    public JMenuItem getfitPageItem() {
        return fitPageItem;
    }

    public void setbean(DjVuBean bean) {
        this.djvubean = bean;
    }

    public void actionPerformed(final ActionEvent event) {
        try {
            final Object source = event.getSource();

            if (lastpageItem == source) {
                djvubean.setPageString(DjVuBean.LAST_PAGE);
            } else if (firstpageItem == source) {
                djvubean.setPageString(DjVuBean.FIRST_PAGE);
            } else if (prevpageItem == source) {
                djvubean.setPageString(DjVuBean.PREV_PAGE);
            } else if (nextpageItem == source) {
                djvubean.setPageString(DjVuBean.NEXT_PAGE);
            } else if (fitWidthItem == source) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_WIDTH);
            } else if (fitPageItem == source) {
                djvubean.setZoom(DjVuBean.ZOOM_FIT_PAGE);
            } else if (actualSizeItem == source) {
                djvubean.setZoom(DjVuBean.ZOOM100);
            } else if (zoomInItem == source) {
                djvubean.setZoom(DjVuBean.ZOOM_IN);
            } else if (zoomOutItem == source) {
                djvubean.setZoom(DjVuBean.ZOOM_OUT);
            } else if (marqueeZoomItem == source) {
//            if (marqueeZoomItem.isSelected()) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);
//            } else if (!(panModeItem.isSelected() || textModeItem.isSelected())) {
//                djvubean.setMode(DjVuBean.LAST_MODE);
//            }
            } else if (panModeItem == source) {
//            if (panModeItem.isSelected()) {
                djvubean.setMode(DjVuBean.PAN_MODE);
//            } else if (!(textModeItem.isSelected() || marqueeZoomItem.isSelected())) {
//                djvubean.setMode(DjVuBean.LAST_MODE);
//            }
            } else if (textModeItem == source) {
//            if (textModeItem.isSelected()) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
//            } else if (!(panModeItem.isSelected() || marqueeZoomItem.isSelected())) {
//                djvubean.setMode(DjVuBean.LAST_MODE);
//            }
            } else if (selectModeItem == source) {
                djvubean.setMode(DjVuBean.SELECT_MODE);

            } else if (SearchInFileItem == source) {
                djvubean.properties.put("addOn.finder", "true");
            }
        } catch (final Throwable exp) {
            exp.printStackTrace(DjVuOptions.err);
            System.gc();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        final Object source = e.getSource();
        if (marqueeZoomItem == source) {
            if (marqueeZoomItem.isSelected()) {
                djvubean.setMode(DjVuBean.ZOOM_MODE);
                System.out.println(DjVuBean.ZOOM_MODE);
            } else if (!(panModeItem.isSelected() || textModeItem.isSelected())) {
                djvubean.setMode(DjVuBean.LAST_MODE);
            }
        } else if (panModeItem == source) {
            if (panModeItem.isSelected()) {
                djvubean.setMode(DjVuBean.PAN_MODE);
            } else if (!(textModeItem.isSelected() || marqueeZoomItem.isSelected())) {
                djvubean.setMode(DjVuBean.LAST_MODE);
            }
        } else if (textModeItem == source) {
            if (textModeItem.isSelected()) {
                djvubean.setMode(DjVuBean.TEXT_MODE);
            } else if (!(panModeItem.isSelected() || marqueeZoomItem.isSelected())) {
                djvubean
                        .setMode(DjVuBean.LAST_MODE);
            }
        }

    }

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
