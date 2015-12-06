/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lizardtech.djvubean;


import com.lizardtech.djvu.DjVuOptions;

import static com.lizardtech.djvubean.RibbonMenu.DjvuComponents.djvubean;

import com.lizardtech.djvubean.outline.CreateThumbnails;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;



/**
 *
 * @author ahmed
 */
public class SelectMode
    implements MouseListener, MouseMotionListener{
	
     private Image image;
    
     public String arabic="";
      //~ Instance fields --------------------------------------------------------

  /** DjVuBean to select text. */
  protected final DjVuBean djvuBean;

  /** Most recient coordinate. */
  protected final Point last = new Point();

  /** Start point where the user pressed down on the mouse. */
  protected Point start = null;

  /** The area selected. */
  public static Rectangle select = new Rectangle();
  int x=0,y=0,w=0,z=0;
  private int srcx, srcy;

   /**
    *  Latest mouse coordinates during drag operation.
    */

   private int destx, desty;
  
    //~ Constructors -----------------------------------------------------------

  public SelectMode(DjVuBean bean)
  {
    djvuBean = bean;
  }

  public SelectMode(DjVuBean bean,String arabic)
  {
    djvuBean = bean;
    this.arabic=arabic;
  }
//~ Methods ----------------------------------------------------------------

 


/**
   * Called when the mouse is clicked.  Performs no operation.
   *
   * @param event describing mouse action.
   */
  public void mouseClicked(MouseEvent event) {djvuBean.requestFocus();}

  /**
   * Called when the mouse is dragged.  Highlights the rectangle if the start
   * position has been set.
   *
   * @param event describing mouse action.
   */
  public void mouseDragged(MouseEvent event)
  {
      destx = event.getX ();
      desty = event.getY ();
      
    if(start != null)
    {
      int i = event.getX();
      int j = event.getY();

      if((last.x != i) || (last.y != j))
      {
        int k = (i <= start.x)
          ? i
          : start.x;
        int l = (j <= start.y)
          ? j
          : start.y;
        int i1 = (i <= start.x)
          ? (start.x - i)
          : (i - start.x);
        int j1 = (j <= start.y)
          ? (start.y - j)
          : (j - start.y);
        select.setBounds(k, l, i1, j1);
        x=k;
        y=l;
        z=i1;
        w=j1;
        djvuBean.setSelect(select);
        last.setLocation(i, j);
//        EditorPanel.  selection = new Rectangle2D.Double(0, 0, djvubean.getWidth(), djvubean.getHeight());
//          EditorPanel.selection.setFrame(select);
//        
      }
    //  new SnipingTool(start.x,start.y,select.width,select.height);
    }
  }

  /**
   * Called when the mouse pointer enters the component.  Performs no
   * operation.
   *
   * @param event describing mouse action.
   */
  public void mouseEntered(MouseEvent event) {}

  /**
   * Called when the mouse pointer exits the component.  Performs no
   * operation.
   *
   * @param event describing mouse action.
   */
  public void mouseExited(MouseEvent event) {}

  /**
   * Called when the mouse pointer is moved.  Performs no operation.
   *
   * @param event describing mouse action.
   */
  public void mouseMoved(MouseEvent event) {}

  /**
   * Called when the mouse button is pressed.  The start location will be
   * set.
   *
   * @param event describing mouse action.
   */
  public void mousePressed(MouseEvent event)
  {
    try
    {
        destx = srcx = event.getX ();
        desty = srcy = event.getY ();
        
      djvuBean.requestFocus();
      start = new Point(
          event.getX(),
          event.getY());
      last.setLocation(start);
      select.setBounds(start.x, start.y, 0, 0);
      djvuBean.addMouseMotionListener(this);
      djvuBean.setSelect(select);
    }
    catch(final Throwable exp)
    {
      exp.printStackTrace(DjVuOptions.err);
      System.gc();
    }
  }

  /**
   * Called when the mouse button is released. The selected area will be
   * 
   *
   * @param event describing mouse action.
   */
  public void mouseReleased(MouseEvent event)
  {
    try
    {
        start= null;
        djvuBean.removeMouseMotionListener(this);
        djvuBean.setMode(DjVuBean.PAN_MODE);
        crop();
        
    }
    catch(final Throwable exp)
    {
      exp.printStackTrace(DjVuOptions.err);
      System.gc();
    }
  }
  
  public boolean crop ()
   {
      // There is nothing to crop if the selection rectangle is only a single
      // point.

      if (srcx == destx && srcy == desty)
          return true;

      // Assume success.

      boolean succeeded = true;

      // Compute upper-left and lower-right coordinates for selection rectangle
      // corners.

      int x1 = (srcx < destx) ? srcx : destx;
      int y1 = (srcy < desty) ? srcy : desty;

      int x2 = (srcx > destx) ? srcx : destx;
      int y2 = (srcy > desty) ? srcy : desty;

      // Compute width and height of selection rectangle.

      int width = (x2-x1)+1;
      int height = (y2-y1)+1;

      // Create a buffer to hold cropped image.

      BufferedImage biCrop = new BufferedImage (width, height,
                                                BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = biCrop.createGraphics ();

      // Perform the crop operation.

      try
      {
          BufferedImage bi = CreateThumbnails.getScreenShot(djvubean);
          BufferedImage bi2 = bi.getSubimage (x1, y1, width, height);
          g2d.drawImage (bi2, null, 0, 0);
      }
      catch (RasterFormatException e)
      {
         succeeded = false;
      }

      g2d.dispose ();

      if (succeeded)
          setImage (biCrop); // Implicitly remove selection rectangle.
      else
      {
          // Prepare to remove selection rectangle.

          srcx = destx;
          srcy = desty;

      }

      return succeeded;
   }
  
  public void setImage (BufferedImage image)
   {
      // Save the image for later repaint.

      this.image = image;

      // Set this panel's preferred size to the image's size, to influence the
      // display File f = new File("MyFile.png");
      File f = new File("cropImage.png");
         try {
             ImageIO.write(image, "PNG", f);
          
         } catch (IOException ex) {
             Logger.getLogger(SelectMode.class.getName()).log(Level.SEVERE, null, ex);
         }
 
       
      // Present scrollbars as necessary.
      // Prepare to remove any selection rectangle.

      srcx = destx;
      srcy = desty;
   }
}
