/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvu;

import com.lizardtech.djvubean.outline.CreateThumbnails;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A list model for a JList with memory performance in mind, this model contains of a JPanels
 *
 * @author Osama
 */
// TODO: create a data buffer to pre-render images to enhance performance.
public class PagesModel extends AbstractListModel {

    protected final int SIZE;
    protected final int WIDTH;
    protected final int HEIGHT;
    private JPanel tempPanel;
    private JLabel tempLabel;

    public PagesModel(int size, int width, int height) {
        this.SIZE = size;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public JPanel getElementAt(int index) {
//        System.out.println("getting page number: " + index);
        try {
            return getPage(index);
        } catch (IOException ex) {
            System.err.println("Error rendering image: " + ex.getMessage());
        }
        return null;

//        return new JPanel();
    }

    // TODO: fasten the page eliminate page drawing wait time.
    protected JPanel getPage(int pageNo) throws IOException {
        tempLabel = new JLabel("" + (pageNo + 1));
        tempLabel.setHorizontalTextPosition(JLabel.CENTER);
        tempLabel.setVerticalTextPosition(JLabel.BOTTOM);
        tempLabel.setForeground(Color.GRAY);

        // create the corresponding panels
        tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(tempLabel);
        tempPanel.setForeground(Color.GRAY);

        // add the images to jlabels with text
        tempLabel.setSize(WIDTH, HEIGHT);
        tempLabel.setIcon(new ImageIcon(CreateThumbnails.generateThumbnail(pageNo, WIDTH, HEIGHT)));

//        ----------------------------------------------
//        ----------------------------------------------
        return tempPanel;
    }
}
