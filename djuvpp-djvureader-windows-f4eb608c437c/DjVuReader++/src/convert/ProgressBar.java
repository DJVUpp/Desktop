/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import static com.lizardtech.djvubean.DjVuBean.frame;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import javafx.scene.image.Image;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBar {

    private JFrame progressFrame;
    private JProgressBar progressBar;

    /**
     * Create the application.
     */
    public ProgressBar() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private  void initialize() {

        progressFrame = new JFrame();
        URL iconURL = getClass().getResource("/src/RibbonMenu/Djvuicon.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        progressFrame.setIconImage(icon.getImage());
        progressFrame.setAlwaysOnTop(true);
        progressFrame.setTitle("Start Converting Please Wait..");
        progressFrame.setResizable(false);
        progressFrame.setBounds(380, 360, 640, 79);
        progressFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        progressFrame.getContentPane().setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        progressBar.setBackground(new Color(255, 255, 255));
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(102,178,255));
        progressBar.setBounds(0, 0, 634, 52);

        progressFrame.getContentPane().add(progressBar);
    }

    public void showProgress() {
        initialize();
        progressFrame.setVisible(true);
    }

    public void closeProgress() {
        progressFrame.dispose();
        progressFrame.setVisible(false);
    }

    public void updatePercent(int value) {
        progressBar.setValue(value);
    }
}