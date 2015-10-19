/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreatDjVu;

import com.lizardtech.djview.frame.DjvuStart;
import static com.lizardtech.djview.frame.DjvuStart.curropen;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.DjvuStart.url_name;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.scene.control.ListView;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author FATHI HOSSAM
 */
public class NewDjvu {

    public static JTextArea textArea;

    public static void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Write here");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                //frame.setAlwaysOnTop(true);
                textArea = new JTextArea(15, 50);
                textArea.setWrapStyleWord(true);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                final JTextField input = new JTextField(20);
                JLabel name = new JLabel("DjVu Name : ");
                JButton create = new JButton("Create");
                JButton edite = new JButton("Edite");
                edite.setEnabled(false);
                
                
                edite.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        create.setEnabled(true);
                        edite.setEnabled(false);
                        textArea.setEditable(true);
                    }
                });
                
                
                create.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //FileWriter w = null;
                        String te = textArea.getText().trim();
                        try {
                            if (textArea.getText().isEmpty() || te.equals("")) {
                                JOptionPane.showMessageDialog(null, "Text is empty ");
                            } else {
                                if (!input.getText().trim().isEmpty()) {
                                    try {
//                            w = new FileWriter("newDjvu.txt");
//                            BufferedWriter bu = new BufferedWriter(w);
//                            textArea.write(bu);
//                            bu.close();
//                            w.close();
                                        new Textpdf().createPdf("C:\\DjVu++Task\\newDjvu.pdf", textArea.getText());
                                        System.out.println("finish");
                                    } finally {
                                        PDF2DjVu.PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\newDjvu.pdf", "C:\\DjVu++Task\\" + input.getText(), 4);
                                        System.out.println("finished");
                                        File open = new File("C:\\DjVu++Task\\" + input.getText() + ".djvu");
                                        String url;
                                        url = "" + open.toURI();
                                        url = url.substring(5, url.length());
                                        String name = open.getName();
                                        if (!curropen.contains(name)) {
                                            name_url.put(name, url);
                                            url_name.put(url, name);
                                            curropen.add(name);
                                            DjvuStart.unsavedbook.add(url);
                                            DjvuStart.openBookInNewTab(url, name);

                                        } else if (curropen.contains(name)) {
                                            tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
                                        } else {
                                        }
                                        create.setEnabled(false);
                                        edite.setEnabled(true);
                                        textArea.setEditable(false);
                                        input.setText("");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Input name of file");
                                }

                            }

                        } catch (Exception ex) {

                        } finally {
                            File write = new File("C:\\DjVu++Task\\newDjvu.pdf");
                            if (write.exists()) {
                                write.delete();
                            }

                        }
                    }
                });
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(name);
                inputpanel.add(input);
                inputpanel.add(create);
                inputpanel.add(edite);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(true);

            }
        });
    }

}
