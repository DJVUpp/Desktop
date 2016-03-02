/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvu;

import com.lizardtech.djvubean.outline.CreateThumbnails;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A list model for a JList with memory performance in mind, this model contains of a JPanels
 *
 * @author Osama
 */
// TODO: create a data buffer to pre-render images to enhance performance.
public class ThumbnailsModel extends PagesModel {

    private ImageIcon Braker;

    public ThumbnailsModel(int size, int width, int height) {
        super(size, width, height);

        try {
            Braker = new ImageIcon(
                    CreateThumbnails.Resizeimage((BufferedImage) (ImageIO.read(this.getClass().getResource("/images/Braker.jpg"))), 124, 45));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected JPanel getPage(int pageNo) throws IOException {
        JLabel tempLabel = new JLabel("" + (pageNo + 1));
        tempLabel.setHorizontalTextPosition(JLabel.CENTER);
        tempLabel.setVerticalTextPosition(JLabel.BOTTOM);

        BufferedImage img;
        img = CreateThumbnails.generateThumbnail(pageNo, WIDTH, HEIGHT);
        // add the images to jlabels with text
        tempLabel.setIcon(combosed_image(new ImageIcon(img), Braker));
        // create the corresponding panels 
        JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tempPanel.add(tempLabel);

        return tempPanel;
    }

    private ImageIcon combosed_image(ImageIcon img1, ImageIcon img2) {
        int pad = 0;
        final BufferedImage compositeImage = new BufferedImage(img1.getIconWidth(), img1.getIconHeight() + img2.getIconHeight() + pad, BufferedImage.TYPE_INT_ARGB);
        final Graphics graphics = compositeImage.createGraphics();

        // Iterate over the icons, painting each icon and adding some padding space between them
        graphics.drawImage(img1.getImage(), 0, 0, null);
        graphics.drawImage(img2.getImage(), 0, img1.getIconHeight() + pad, null);

        return new ImageIcon(compositeImage);
    }
}
