/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lizardtech.djvubean;

/**
 *
 * @author fouad
 */
import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
//import org.jpedal.objects.acroforms.overridingImplementations.CustomImageIcon;
class CustomTitlebar extends JDialog
{
JPanel p;
JMenuBar mb;
JButton close,min,max;
JTextArea title,note;
JScrollPane scroll;
JLabel text;
JMenu menu;
JMenuItem menuitem1,menuitem2,menuitem3,menuitem4;

 

Font f=new Font("Arial",Font.BOLD,12);
int pX,pY;
    

    public CustomTitlebar(int y)
    {
        createAndShowGUI(y);
    }
    
    private void createAndShowGUI(int y)
    {
        // Custom look and feel
        try
        {
         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
           
        }catch(Exception e){}
       
        // date and time 
        String hostname = "";

         try {
             InetAddress addr = InetAddress.getLocalHost();
             hostname = addr.getHostName();
         } catch (UnknownHostException ex) {
             System.out.println("Hostname can not be resolved");
         }
         // for current date 
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
        
        
        
        
        
        
        setTitle("Custom Titlebar");
        setUndecorated(true);        
        
        // Create JMenuBar
        mb=new JMenuBar();
        mb.setLayout(new BorderLayout());
        
      
        
        // Create panel
        p=new JPanel();
        p.setOpaque(false);
        p.setLayout(new GridLayout(1,3));
        
        // Create buttons
        min=new JButton("-");
        max=new JButton("+");
        close=new JButton("x");
        close.setBackground(Color.pink);
        min.setBackground(Color.pink);
        max.setBackground(Color.pink);
        
        min.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                // minimize
            //   setState(ICONIFIED);
            }
        });
        
        max.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                maximize();
            }
        });
        
        close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                // terminate program
                //System.exit(0);
                setVisible(false);
            }
        });
        
        // set focus painted false
        // i don't like it, so i removed it
        // if you like, you can remove these steps
//        min.setFocusPainted(false);
//        max.setFocusPainted(false);
//        close.setFocusPainted(false);
        
        // font, again if you don't like you can
        // remove these steps, also remove the Font object
        min.setFont(f);
        max.setFont(f);
        close.setFont(f);
        
        // Add buttons 
        p.add(close);
      //  p.add(max);
       // p.add(min);
        
        // To East,  style!
        mb.add(p,BorderLayout.EAST);
        
        
        text=new JLabel();
        text.setFont(f);
        text.setText("insert text");
       
       mb.add(text,BorderLayout.CENTER);
        
       
        // Add mouse listener for JMenuBar mb
        mb.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me)
            {
                // Get x,y and store them
                pX=me.getX();
                pY=me.getY();
            }
        });
        
        // Add MouseMotionListener for detecting drag
        mb.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent me)
            {
                // Set the location
                // get the current location x-co-ordinate and then get
                // the current drag x co-ordinate, add them and subtract most recent
                // mouse pressed x co-ordinate
                // do same for y co-ordinate
                setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
                
                
            }
        });
        
        
        
        // Set the menu bar
        setJMenuBar(mb);
        
        // create menus
        
        menu=new JMenu("Options");
        
        
       
        menuitem1=new JMenuItem("properties");
        menu.add(menuitem1);
        menu.addSeparator();
        menuitem2=new JMenuItem("close popup note");
        menu.add(menuitem2);
        menu.addSeparator();
        menuitem3=new JMenuItem("delete");
        menu.add(menuitem3);
        
        
        mb.add(menu,BorderLayout.WEST);
        mb.setUI ( new BasicMenuBarUI (){
    public void paint ( Graphics g, JComponent c ){
       g.setColor ( Color.LIGHT_GRAY);
       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
    }
} );
        
        
        
        
        
        
        title=new JTextArea();
        title.setBackground(Color.pink);
        title.setText( "    "+ hostname + "       " + dateFormat.format(date));
        title.setEditable(false);
        
        note =new JTextArea();
        note.setBackground(Color.LIGHT_GRAY);
       
        scroll=new JScrollPane(note);
        
        
        add(title,BorderLayout.PAGE_START);
        add(scroll,BorderLayout.CENTER);
        // Set size, visibility,shape and center it
        
        setModal(true);
        setLocation(1130, y + 80);
        setSize(220,150);
        setVisible(true);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0,0,getWidth(),getHeight(),5,5));
      //  setLocationRelativeTo(null);
        
    }
    private void maximize()
    {
    // Get GraphicsEnvironment object for getting GraphicsDevice object
    GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    // Get the screen devices
    GraphicsDevice[] g=env.getScreenDevices();
    
    // I only have one, the first one
    // If current window is full screen, set fullscreen window to null
    // else set the current screen
    g[0].setFullScreenWindow(g[0].getFullScreenWindow()==this?null:this);
    }
}
