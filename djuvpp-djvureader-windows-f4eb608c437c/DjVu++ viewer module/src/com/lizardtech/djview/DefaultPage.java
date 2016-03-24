package com.lizardtech.djview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DefaultPage extends JPanel {

    /**
     * Create the panel.
     */
    public DefaultPage(int width, int height) {
        setBackground(Color.GRAY);

        JPanel page = new JPanel(new GridBagLayout());
        page.setPreferredSize(new Dimension(width, height));
        page.setBackground(Color.WHITE);
        add(page);

//        JLabel icon;
//        icon = new JLabel();
//        icon.setSize(width, height);
//        icon.setIcon(new ImageIcon(DefaultPage.class.getResource("/images/single page48.png")));
//        page.add(icon);
    }
}
