/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djview.frame;

import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;

import com.lizardtech.djvubean.DjVuBean;

import static com.lizardtech.djvubean.RibbonMenu.DjvuComponents.djvubean;

import com.lizardtech.djvubean.RibbonMenu.DjvuRibbonComponents;
import com.lizardtech.djvubean.RibbonMenu.RibbonGetIcon;
import com.lizardtech.djvubean.RibbonMenu.RibbonMenuCreation;

//import static com.lizardtech.djvubean.RibbonMenu.RibbonMenuCreation.getResizableIconFromResource;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;

/**
 *
 * @author niessuh
 */
public class PageSelect extends RibbonMenuCreation {

    public static JCommandButton FirstPage;
    public static JCommandButton LastPage;
    public static JCommandButton nextPage;
    public static JCommandButton prevPage;
    public static JCommandButton next;
    public static JCommandButton prev;
    public int PageLast;
    private int Pagenext;
    public JPanel PageNav;
    public static JComboBox pageSelectBox;
    private static JPanel Goto;
    public static JCommandButton GotoFirstPage;
    public static JCommandButton GotoLastPage;
    public static JCommandButton GotonextPage;
    public static JCommandButton GotoprevPage;
    public static JTextField GoToArea;
    private DjvuRibbonComponents Button=new DjvuRibbonComponents();
    public JPanel getPageSelectPanel() {
        this.PageNav = new JPanel();
        next = new JCommandButton(null, getResizableIconFromResource("/images/next view48.png"));
        next.setEnabled(false);
        next.setDisplayState(CommandButtonDisplayState.SMALL);
        next.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = 0;
                if (djvubean.getPage() != djvubean.SeqView.get(djvubean.SeqView.size() - 1)) {
                     
                    if (djvubean.SeqView.contains(djvubean.getPage())) {
                        index = djvubean.SeqView.indexOf(djvubean.getPage());

                    }
                    djvubean.setPage(djvubean.SeqView.get(index + 1));
                    pageSelectBox.setSelectedItem(djvubean.getPage());
                    GoToArea.setText("" + djvubean.getPage());
                    
                }
                else{
                     if (djvubean.getPage() ==1)
                    {
                        prev.setEnabled(false);
                        DjvuRibbonComponents.prevview.setEnabled(false);
                    }
                     next.setEnabled(false);
                     DjvuRibbonComponents.NextView.setEnabled(false);
                }
                
                   

            }
        });
        prev = new JCommandButton(null, getResizableIconFromResource("/images/previous view48.png"));
        prev.setEnabled(false);
        prev.setDisplayState(CommandButtonDisplayState.SMALL);
        prev.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index = 0;

                if (djvubean.getPage() !=1) {
                      
                    if (djvubean.SeqView.contains(djvubean.getPage())) {
                        index = djvubean.SeqView.indexOf(djvubean.getPage());

                    }
                    
                    djvubean.setPage(djvubean.SeqView.get(index - 1));
                    pageSelectBox.setSelectedItem(djvubean.getPage());
                    GoToArea.setText("" + djvubean.getPage());
                    next.setEnabled(true);
                    DjvuRibbonComponents.NextView.setEnabled(true);
                }
                else{
                    if (djvubean.getPage() == djvubean.SeqView.get(djvubean.SeqView.size() - 1))
                    {
                        next.setEnabled(false);
                        DjvuRibbonComponents.NextView.setEnabled(false);
                    }
                     prev.setEnabled(false);
                  DjvuRibbonComponents.prevview.setEnabled(false);
                }
            }
        });
        FirstPage = new JCommandButton(getResizableIconFromResource("/images/FirstPage.png"));

        FirstPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.FIRST_PAGE);
                FirstPage.setEnabled(false);
                prevPage.setEnabled(false);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GotoFirstPage.setEnabled(false);
                GotoprevPage.setEnabled(false);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
        
        LastPage = new JCommandButton(getResizableIconFromResource("/images/LastPage.png"));

        LastPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.LAST_PAGE);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(false);
                nextPage.setEnabled(false);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(false);
                GotonextPage.setEnabled(false);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
        nextPage = new JCommandButton(getResizableIconFromResource("/images/nexts.png"));
        // nextPage.setEnabled(false);
        
        
        nextPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.NEXT_PAGE);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
      
        prevPage = new JCommandButton(getResizableIconFromResource("/images/prev.png"));
        //  prevPage.setEnabled(false);
        prevPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.PREV_PAGE);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
         prevPage.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if(e.getKeyCode()==KeyEvent.VK_LEFT)
                {
                     djvubean.setPageString(DjVuBean.PREV_PAGE);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        pageSelectBox = new JComboBox();
        pageSelectBox.setEditable(true);
        pageSelectBox.setEnabled(true);

        pageSelectBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {

                    int Page = (Integer) pageSelectBox.getSelectedItem();
                    if (Page == 1) {
                    	 DjvuRibbonComponents.prevview.setEnabled(false);
                        prev.setEnabled(false);
                        FirstPage.setEnabled(false);
                        prevPage.setEnabled(false);
                        LastPage.setEnabled(true);
                        nextPage.setEnabled(true);
                        GotoFirstPage.setEnabled(false);
                        GotoprevPage.setEnabled(false);
                        GotoLastPage.setEnabled(true);
                        GotonextPage.setEnabled(true);
                        djvubean.setPage(Page);
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else if (Page == djvubean.getDocumentSize()) {
                        FirstPage.setEnabled(true);
                        prevPage.setEnabled(true);
                        LastPage.setEnabled(false);
                        nextPage.setEnabled(false);
                        GotoFirstPage.setEnabled(true);
                        GotoprevPage.setEnabled(true);
                        GotoLastPage.setEnabled(false);
                        GotonextPage.setEnabled(false);
                        djvubean.setPage(Page);
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else if (Page > 0 && Page < djvubean.getDocumentSize()) {
                        FirstPage.setEnabled(true);
                        prevPage.setEnabled(true);
                        LastPage.setEnabled(true);
                        nextPage.setEnabled(true);
                        GotoFirstPage.setEnabled(true);
                        GotoprevPage.setEnabled(true);
                        GotoLastPage.setEnabled(true);
                        GotonextPage.setEnabled(true);
                        PageLast = djvubean.getPage();
                        djvubean.setPage(Page);
                        Pagenext = djvubean.getPage();
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input the page not found in Document ,please try again!");
                        pageSelectBox.setSelectedItem(djvubean.getPage());
                        GoToArea.setText("" + djvubean.getPage());

                    }
                     if (Page== djvubean.SeqView.get(djvubean.SeqView.size() - 1))
                    {
                        next.setEnabled(false);
                        DjvuRibbonComponents.NextView.setEnabled(false);
                    }

                } catch (Exception Exc) {

                    String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                    if (!tabName.equals("GetStart")) {
                        // JOptionPane.showMessageDialog(null,"Invalid input ,please try again!");
                        djvubean.setPage(djvubean.getPage());
                        pageSelectBox.setSelectedItem(djvubean.getPage());
                        GoToArea.setText("" + djvubean.getPage());
                    }
                }
            }
        });

        PageNav.add(FirstPage);
        PageNav.add(prevPage);
        PageNav.add(pageSelectBox);
        PageNav.add(nextPage);
        PageNav.add(LastPage);
        PageNav.add(prev);
        PageNav.add(next);
        return PageNav;
    }

    public JPanel getGotoPanel() {
        Goto = new JPanel();
        Goto.setPreferredSize(new Dimension(230, 30));
        Goto.setMaximumSize(Goto.getPreferredSize());
        Goto.setMinimumSize(Goto.getPreferredSize());

        GotoFirstPage = new JCommandButton(new PageSelect().getResizableIconFromResource("/images/FirstPage.png"));

        GotoFirstPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.FIRST_PAGE);
                GotoFirstPage.setEnabled(false);
                GotoprevPage.setEnabled(false);
                GotonextPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                FirstPage.setEnabled(false);
                prevPage.setEnabled(false);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                GoToArea.setText("" + djvubean.getPage());
                pageSelectBox.setSelectedItem(djvubean.getPage());
            }
        });
        GotoLastPage = new JCommandButton(new PageSelect().getResizableIconFromResource("/images/LastPage.png"));

        GotoLastPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.LAST_PAGE);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(false);
                GotonextPage.setEnabled(false);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(false);
                nextPage.setEnabled(false);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
        GotonextPage = new JCommandButton(new PageSelect().getResizableIconFromResource("/images/nexts.png"));
        // nextPage.setEnabled(false);
        GotonextPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.NEXT_PAGE);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
        GotoprevPage = new JCommandButton(new PageSelect().getResizableIconFromResource("/images/prev.png"));
        //  prevPage.setEnabled(false);
        GotoprevPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                djvubean.setPageString(DjVuBean.PREV_PAGE);
                GotoFirstPage.setEnabled(true);
                GotoprevPage.setEnabled(true);
                GotoLastPage.setEnabled(true);
                GotonextPage.setEnabled(true);
                FirstPage.setEnabled(true);
                prevPage.setEnabled(true);
                LastPage.setEnabled(true);
                nextPage.setEnabled(true);
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
            }
        });
        GoToArea = new JTextField(6);
        GoToArea.setEnabled(false);
        GoToArea.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    int Page = Integer.parseInt(GoToArea.getText());
                    if (Page == 1) {
                    	 DjvuRibbonComponents.prevview.setEnabled(false);
                         prev.setEnabled(false);
                        FirstPage.setEnabled(false);
                        prevPage.setEnabled(false);
                        LastPage.setEnabled(true);
                        nextPage.setEnabled(true);
                        GotoFirstPage.setEnabled(false);
                        GotoprevPage.setEnabled(false);
                        GotoLastPage.setEnabled(true);
                        GotonextPage.setEnabled(true);
                        djvubean.setPage(Page);
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else if (Page == djvubean.getDocumentSize()) {
                        FirstPage.setEnabled(true);
                        prevPage.setEnabled(true);
                        LastPage.setEnabled(false);
                        nextPage.setEnabled(false);
                        GotoFirstPage.setEnabled(true);
                        GotoprevPage.setEnabled(true);
                        GotoLastPage.setEnabled(false);
                        GotonextPage.setEnabled(false);
                        djvubean.setPage(Page);
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else if (Page > 0 && Page < djvubean.getDocumentSize()) {
                        FirstPage.setEnabled(true);
                        prevPage.setEnabled(true);
                        LastPage.setEnabled(true);
                        nextPage.setEnabled(true);
                        GotoFirstPage.setEnabled(true);
                        GotoprevPage.setEnabled(true);
                        GotoLastPage.setEnabled(true);
                        GotonextPage.setEnabled(true);
                        djvubean.setPage(Page);
                        pageSelectBox.setSelectedItem(Page);
                        GoToArea.setText("" + djvubean.getPage());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input the page not found in Document ,please try again!");
                        pageSelectBox.setSelectedItem(djvubean.getPage());
                        GoToArea.setText("" + djvubean.getPage());

                    }
                    if (Page== djvubean.SeqView.get(djvubean.SeqView.size() - 1))
                    {
                        next.setEnabled(false);
                        DjvuRibbonComponents.NextView.setEnabled(false);
                    }

                } catch (Exception Exc) {
                      if(tabbedPane.getTabCount()!=0){
                    String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                    if (!tabName.equals("GetStart")) {
                        // JOptionPane.showMessageDialog(null,"Invalid input ,please try again!");
                        djvubean.setPage(djvubean.getPage());
                        pageSelectBox.setSelectedItem(djvubean.getPage());
                        GoToArea.setText("" + djvubean.getPage());
                    }
                }
                }
            }
        });

        Goto.add(GotoFirstPage);
        Goto.add(GotoprevPage);
        Goto.add(GoToArea);
        Goto.add(GotonextPage);
        Goto.add(GotoLastPage);

        return Goto;
    }

}
