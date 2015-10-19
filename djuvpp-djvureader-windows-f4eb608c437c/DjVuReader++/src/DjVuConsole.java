

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import com.lizardtech.djvu.DjVuOptions;

/**
 * This class implements a simple Java Console for use on platforms
 * which have no other console.
 *
 * @author docbill
 */
public class DjVuConsole 
  extends OutputStream
  implements WindowListener
{
  // This is the text area to display.
  private final TextArea text=new TextArea();
  
  // The buffer is used to accumulate one full line 
  // prior to updating the display.
  private final StringBuffer buffer=new StringBuffer();
  
  // This is the frame to display.
  private Frame frame;
  
  /**
   * Creates a new instance of DjVuConsole 
   */
  public DjVuConsole() 
  {
    frame=new Frame("Java DjVu Console");
    frame.setLayout( new BorderLayout() );
    frame.add( "Center", text );
    DjVuOptions.out=DjVuOptions.err=new PrintStream(this);
    frame.setSize(640, 480);
    frame.setVisible(true);
    frame.addWindowListener(this);
  }

    
  /** 
   * Called when the window is closing.
   */
  public void windowClosing(WindowEvent e)
  {
    DjVuOptions.out=System.out;
    DjVuOptions.err=System.err;
    frame.setVisible(false);
    frame.dispose();
    frame=null;
  }
  
  /** 
   * Called when the window is activated.
   */
  public void windowActivated(WindowEvent e) {}
  
  /** 
   * Called when the window is closed.
   */
  public void windowClosed(WindowEvent e) {}

  /** 
   * Called when the window is deactivated.
   */
  public void windowDeactivated(WindowEvent e) {}
  
  /** 
   * Called when the window is deiconified.
   */
  public void windowDeiconified(WindowEvent e) {}
  
  /** 
   * Called when the window is iconified.
   */
  public void windowIconified(WindowEvent e) {}
  
  /**
   * Called when the window is opened
   */
  public void windowOpened(WindowEvent e) {}
  
  /**
   * Write the specified character to the console window.
   *
   * @param c the character to write
   */
  public void write(int c)
  {
    switch(c)
    {
      case '\n':
        buffer.append((char)c);
        text.append(buffer.toString());
        buffer.setLength(0);
        break;
      case '\r':
        break;
      default:
        buffer.append((char)c);
    }
  }
}
