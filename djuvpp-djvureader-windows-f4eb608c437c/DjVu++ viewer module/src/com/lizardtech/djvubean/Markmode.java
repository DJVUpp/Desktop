
package com.lizardtech.djvubean;

import com.lizardtech.djvu.DjVuOptions;
import java.awt.*;
import java.awt.event.*;
import static com.lizardtech.djvubean.annotations.popupnote;



/**
 * MarkMode is use mouse listener to detect pixels on a DjVuBean.
 */
class Markmode
  implements MouseListener, MouseMotionListener
{ 
    
    
         // A graphics context for the panel
   public static int xx;
   public static int yy;  
   public static  int destx, desty;
   public static int  xmin,xmax,ymin,ymax;
      //~ Instance fields --------------------------------------------------------

  /** DjVuBean to select text. */
  protected final DjVuBean djvuBean;

  /** Most recent coordinate. */
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

   
  

  //~ Constructors -----------------------------------------------------------

  /**
   * Creates a new ZoomMode object.
   *
   * @param bean DjVuBean object to listen to.
   */
  public Markmode(DjVuBean bean)
  {
   djvuBean = bean;
   
  }
  
 

  //~ Methods ----------------------------------------------------------------

  /**
   * Called when the mouse is clicked.  Performs no operation.
   *
   * @param event describing mouse action.
   */
  public void mouseClicked(MouseEvent event) {
     djvuBean.requestFocus();
  }

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
        select.setBounds(k, start.y, i1, j1);
        x=k;
        y=l;
        z=i1;
        w=j1;
        djvuBean.setSelect(select);
        last.setLocation(start.x, start.y);
        
       
         
      
     
      }
      
   
    }
    
  }
  
 

  /**
   * Called when the mouse pointer enters the component.  Performs no
   * operation.
   *
   * @param event describing mouse action.
   * 
   * 
   */
  
  
  public void mouseEntered(MouseEvent event) {}

  /**
   * Called when the mouse pointer exits the component.  Performs no
   * operation.
   *
   * @param event describing mouse action.
   */
  public void mouseExited(MouseEvent event) {
      
      
 
  }

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
    
    }
    catch(final Throwable exp)
    {
      exp.printStackTrace(DjVuOptions.err);
      System.gc();
    }
        xx=event.getX();
        yy=event.getY();
         djvuBean.addMouseMotionListener(this);
        
      
  }

  /**
   * Called when the mouse button is released. The selected area will be
   * zoomed to.
   *
   * @param event describing mouse action.
   */
  
  
  public void mouseReleased(MouseEvent event)
          
 {
    
     if (popupnote==1){
     new CustomTitlebar(yy);
     }
     
     

  }
  
  
  
  
    
       
}

 