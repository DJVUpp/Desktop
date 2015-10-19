package SnippingTool;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JRootPane;


public class OverlayFrame extends JFrame
{
    private static final long serialVersionUID = 4640312842620083014L;
    public static boolean isActive = false;
    private Overlay overlayPanel;
    String args="";
    public OverlayFrame(String args)
    {
        this.args=args;
        isActive = true; // the overlay is currently opened.
        this.setUndecorated(true);
        if (OperatingSystem.isWindows())
        {
            getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            this.setBounds(getScreenSize());
        } else
        {
            this.setBounds(getScreenSize());
        }
        overlayPanel = new Overlay(this,args);
        this.add(overlayPanel);
        this.setOpacity(1f);
        this.setVisible(true);
        this.addWindowListener(new WindowListener()
        {

            @Override
            public void windowOpened(WindowEvent arg0)
            {
            }

            @Override
            public void windowIconified(WindowEvent arg0)
            {
            }

            @Override
            public void windowDeiconified(WindowEvent arg0)
            {
            }

            @Override
            public void windowDeactivated(WindowEvent arg0)
            {
            }

            @Override
            public void windowClosing(WindowEvent arg0)
            {

            }

            @Override
            public void windowClosed(WindowEvent arg0)
            {
                isActive = false;
            }

            @Override
            public void windowActivated(WindowEvent arg0)
            {
            }
        });
    }

    private Rectangle getScreenSize()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();

        for (GraphicsDevice device : devices)
        {
            GraphicsConfiguration[] gc = device.getConfigurations();
            for (int i = 0; i < gc.length; i++)
            {
                bounds = bounds.union(gc[i].getBounds());
            }
        }

        return bounds;
    }

    public void setMode(int mode)
    {
        overlayPanel.setMode(mode);
    }
    public void disposeAll()
    {
        isActive = false;
        overlayPanel.removeAll();
        overlayPanel.dispose();
        this.removeAll();
        this.dispose();
        System.gc();
    }
}
