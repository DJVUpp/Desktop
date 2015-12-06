package com.lizardtech.djvubean.RibbonMenu;

import com.lizardtech.djview.frame.DjvuStart;
import java.awt.Dimension;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;

/**
 * this the main class for Ribbon menu and it extends from Jribbonframe ,this
 * frame for ribbon menus only and in this ribbon can add the bands and tasks
 */
public class RibbonMenuCreation extends JRibbonFrame implements RibbonGetIcon {
    /*to call JCommandButtons from DjvuRibbonComponents class */

    private static final DjvuRibbonComponents Button = new DjvuRibbonComponents();
    /*to call Bands from RibbonBands Class*/
    private static final DjvuComponents Band = new DjvuComponents();
    /*to call Action Methods in Ribbon Actions*/
    private static final RibbonActions Action = new RibbonActions();
    /**
     * Components for TaskBar
     */
    boolean flage = true;
    /* The Main method that Load the Ribbon Menu */

    public void SetRibbonMenu(final DjvuStart djvu) {
    }

//this method to get icon from resource and set it into commponents
    public ResizableIcon getResizableIconFromResource(String resource) {
        ResizableIcon icon = null;
        try {
            icon = ImageWrapperResizableIcon
                    .getIcon(this.getClass().getClassLoader().getResourceAsStream(resource.substring(1)), new Dimension(16, 16));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return icon;
    }

}
