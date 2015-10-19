package SnippingTool;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class CaptureScreen
{
    private Rectangle screenRectangle;
    private ScreenBounds bounds = new ScreenBounds();

    public BufferedImage getScreenCapture()
    {
        screenRectangle = bounds.getBounds();
        Robot robot = null;
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
            Logger.Log(e);
            e.printStackTrace();
        }
        return robot.createScreenCapture(screenRectangle);
    }
}