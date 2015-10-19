package com.lizardtech.djview;

import com.lizardtech.djview.frame.Frame;
import static com.lizardtech.djview.frame.PageSelect.GoToArea;
import static com.lizardtech.djview.frame.PageSelect.pageSelectBox;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.lizardtech.djvubean.DjVuBean;
import com.lizardtech.djvubean.outline.CreateThumbnails;
import com.lizardtech.djvubean.outline.OutlineTabbedPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class FullBookView
        extends JPanel {

    public JScrollPane ThumblainsScrollPane;
    private JPanel ThumblainsPane[];
    private JList ThumblainsList;
    private BufferedImage img[];
    private ImageIcon BookImages[];
    private JLabel PagesLabel[];
    private JPanel Rotatedpane;
    
    private JScrollPane RotatedpaneScrollPane;
    private BufferedImage RotatedpaneImage;
    private JLabel RotatedpaneLabel;
    public static int PagesCount;
    public static boolean Continous=false;
    public static  boolean Is_Rotated=false;

    public static void setPagesCount(int pagesCount) {
        PagesCount = pagesCount;
    }
    
    private DjVuBean djvubean;
    private Frame  frame;
    private  JPanel topPanel;
    private JPanel beanPanel;
    private CardLayout cardLayout;
    private int Width;
    private int Height;
    private int Pagenum=0;
    
    
    public JPanel getTopPanel() {
        return topPanel;
    }
   

    public FullBookView(final DjVuBean djvubean, final Frame frame, JPanel beanPanel) {
        
        this.djvubean = djvubean;
        this.frame=frame;
        this.beanPanel=beanPanel;
        cardLayout=(CardLayout)beanPanel.getLayout();
        Width=720;
        Height=768;
        
        Rotatedpane= new JPanel(new BorderLayout());
        RotatedpaneScrollPane = new JScrollPane( Rotatedpane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
        RotatedpaneLabel=new JLabel();
        topPanel= new JPanel(new BorderLayout());
        topPanel.setBackground(Color.gray);
        Rotatedpane.setBackground(Color.gray);
        createImages();
        img=new BufferedImage[PagesCount];
        
        
        beanPanel.add(topPanel,"FullBook");
        beanPanel.add(RotatedpaneScrollPane,"Rotate");
        cardLayout.first(beanPanel);
        
          new Thread(new Runnable() {

            public void run() {
                     for (int i = 0; i < PagesCount; i++) {

                    try {
                        img[i] = CreateThumbnails.generateThumbnail(i,Width , Height);
                        BookImages[i] = new ImageIcon(img[i]);
                        // add the images to jlabels with text
                        PagesLabel[i].setSize(Width, Height);
                        PagesLabel[i].setIcon(BookImages[i]);
                    
                        ThumblainsPane[i].add(PagesLabel[i]);
                    } catch (Exception ex) {
                       // Logger.getLogger(OutlineTabbedPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }

           
           
            }
            
        
        }).start();
           
    }
   public void createImages()
   {
           
       ThumblainsList = new JList();
        ThumblainsScrollPane = new JScrollPane(ThumblainsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BookImages = new ImageIcon[PagesCount];
        PagesLabel = new JLabel[PagesCount];
        ThumblainsPane = new JPanel[PagesCount];
		// get  images of book
        // Set every page in 
        for (int i = 0; i < PagesCount; i++) {

            PagesLabel[i] = new JLabel("" + (i + 1));
            PagesLabel[i].setHorizontalTextPosition(JLabel.CENTER);
            
            PagesLabel[i].setVerticalTextPosition(JLabel.BOTTOM);
            
            PagesLabel[i].setForeground(Color.GRAY);

            // create the corresponding panels
            ThumblainsPane[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            ThumblainsPane[i].add(PagesLabel[i]);
            ThumblainsPane[i].setForeground(Color.GRAY);

        }

		// tell the ThumblainsList to use the panel array for its data
        ThumblainsList.setListData(ThumblainsPane);
        ThumblainsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                 if (e.getClickCount() == 1) {
                     JList JLelement = (JList) e.getSource();
                      djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
                frame.Thumbpanel.ThumblainsList.setSelectedIndex(JLelement.getSelectedIndex());
                Pagenum= ((JLelement.getSelectedIndex() + 1));
                 }
                if (e.getClickCount() == 2) {
                  
                JList JLelement = (JList) e.getSource();
                djvubean.setPageString("" + (JLelement.getSelectedIndex() + 1));
                pageSelectBox.setSelectedItem(djvubean.getPage());
                GoToArea.setText("" + djvubean.getPage());
                Pagenum= ((JLelement.getSelectedIndex() + 1) );
                    Switch_FullBookView(false);
            }
            }
        });
        ThumblainsList.setCellRenderer(new com.lizardtech.djview.ImageListCellRenderer());

        ThumblainsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ThumblainsList.setLayoutOrientation(JList.VERTICAL);
        ThumblainsList.setFixedCellWidth(Width);
        ThumblainsList.setFixedCellHeight( Height+10);
        ThumblainsList.setFocusable(false);

        // put our JList in a JScrollPane
        ThumblainsScrollPane.setPreferredSize(beanPanel.getPreferredSize());
        ThumblainsScrollPane.setWheelScrollingEnabled(true);
        topPanel.add(ThumblainsScrollPane,BorderLayout.CENTER);
         Rotatedpane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (e.getClickCount() == 2) {
                    
                     Switch_FullBookView(false);
                     
                  
            }
            }
        });
       


        
   }

public void Switch_FullBookView(Boolean Is_Continous)
{
    if(Is_Continous)
    { 
        if(Continous) return;
        cardLayout.show(beanPanel,"FullBook");
        Continous=true;
        Is_Rotated=false;
    }
    else 
    {
      
        cardLayout.show(beanPanel,"Bean");
        Is_Rotated=false;
        Continous=false;
   
    }
         
       


}
   public void rotate (final int degree)
   {
        RotatedpaneImage=rotateImage(img[djvubean.getPage()-1], degree);
        img[djvubean.getPage()-1]=RotatedpaneImage;
        

        RotatedpaneLabel.setIcon(new ImageIcon(RotatedpaneImage));
        
        Rotatedpane.add(RotatedpaneLabel,BorderLayout.CENTER);
     
        cardLayout.show(beanPanel,"Rotate");
        Is_Rotated=true;
        Continous=false;
        
      
        
   }

    private Boolean switchFlag(Boolean X) {
        if (X == false) 
            return true;
         else 
            return false;
        }
   public static BufferedImage rotateImage(BufferedImage src, double degrees) {
           //     if (degrees==0) return src;
		double radians = Math.toRadians(degrees);

		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();

		double sin = Math.abs(Math.sin(radians));
		double cos = Math.abs(Math.cos(radians));
		int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
		int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

		BufferedImage result = new BufferedImage(newWidth, newHeight,
				src.getType());
		Graphics2D g = result.createGraphics();
		g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
		g.rotate(radians, srcWidth / 2, srcHeight / 2);
		g.drawRenderedImage(src, null);

		return result;
	}
   
}
