package SnippingTool;



import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class AnimatedTrayIcon implements Runnable
{
    private final int delayMs;
    private List<Image> frames;
    private boolean animate;
    private String originalIconPath;

    public AnimatedTrayIcon(String basePath, int numFrames, int delayMs)
    {
        this.delayMs = delayMs;
        frames = new ArrayList<>();
        for (int i = 1; i < numFrames; i++)
        {
            frames.add(new ImageIcon(this.getClass().getResource(basePath + i + ".png")).getImage());
        }
    }

    @Override
    public void run()
    {
        animate = true;
        while (animate)
        {
            for (Image frame : frames)
            {
                SnippingToolPlusPlus.trayIcon.setImage(frame);
                try
                {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e)
                {
                    Logger.Log(e);
                    e.printStackTrace();
                }
            }
        }
        SnippingToolPlusPlus.trayIcon.setImage(new ImageIcon(this.getClass().getResource(originalIconPath)).getImage());
    }

    public void stopAnimating()
    {
        animate = false;
    }

    public void setOriginalIconPath(String iconPath)
    {
        this.originalIconPath = iconPath;
    }

    public static AnimatedTrayIcon getDefaultIcon()
    {
        AnimatedTrayIcon defaultIcon = new AnimatedTrayIcon(
            Config.TRAY_ICON_BASE_DIR,
            Config.TRAY_ICON_NUM_FRAMES,
            Config.TRAY_ICON_FRAME_DELAY_MS);
        defaultIcon.setOriginalIconPath(Config.TRAY_ICON_STATIC);
        return defaultIcon;
    }
}
