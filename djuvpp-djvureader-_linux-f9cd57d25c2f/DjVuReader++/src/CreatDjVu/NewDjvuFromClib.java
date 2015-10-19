/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CreatDjVu;

/**
 *
 * @author Hussein Mohamed
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.lizardtech.djview.frame.DjvuStart;
import static com.lizardtech.djview.frame.DjvuStart.bookList;
import static com.lizardtech.djview.frame.DjvuStart.curropen;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.DjvuStart.url_name;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class NewDjvuFromClib{
    public static  JTextArea textAreaClip;
    public static void createFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame("Write Here");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setAlwaysOnTop(true);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                String data = null;
                try {
                     data = (String) Toolkit.getDefaultToolkit() 
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(NewDjvuFromClib.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewDjvuFromClib.class.getName()).log(Level.SEVERE, null, ex);
                }
                 

                textAreaClip = new JTextArea(15, 50);
                textAreaClip.setWrapStyleWord(true);
                textAreaClip.setText(data);
                frame.setAlwaysOnTop(true);
                textAreaClip.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textAreaClip);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                final JTextField input = new JTextField(20);
                JLabel name =new JLabel("DjVu Name : ");
                JButton button = new JButton("Create");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //FileWriter w = null;
                        String te = textAreaClip.getText().trim();
                        try {
                            if (textAreaClip.getText().isEmpty() || te.equals("")) {
                                 JOptionPane.showMessageDialog(null, "Text is empty ");
                            }else {
                                    if (!input.getText().trim().isEmpty()) {
                                        try {
//                            w = new FileWriter("newDjvu.txt");
//                            BufferedWriter bu = new BufferedWriter(w);
//                            textArea.write(bu);
//                            bu.close();
//                            w.close();
                                     new Textpdf().createPdf("C:\\DjVu++Task\\newDjvu.pdf", textAreaClip.getText());
                                     System.out.println("finish");
                                        } finally {
                                            PDF2DjVu.PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\newDjvu.pdf", "D:\\" + input.getText(), 4);
                                            System.out.println("finished");
                                            File open = new File("D:\\" + input.getText() + ".djvu");
                                            String url;
                                            url = "" + open.toURI();
                                            url = url.substring(5, url.length());
                                            String name = open.getName();
                                            if (!curropen.contains(name)) {
                                                name_url.put(name, url);
                                                url_name.put(url, name);
                                                curropen.add(name);
                                                bookList.add(url);
                                                DjvuStart.openBookInNewTab(url, name);

                                            } else if (curropen.contains(name)) {
                                                tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
                                            } else {
                                            }
                                            input.setText("");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Input name of file");
                                    }

                                }
                            
                        } catch (Exception ex) {
                           
                        } finally {
//                            File write = new File("C:\\DjVu++Task\\newDjvu.pdf");
//                            if (write.exists()) {
//                                write.delete();
//                            }

                        }
                    }

                });
                DefaultCaret caret = (DefaultCaret) textAreaClip.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(name);
                inputpanel.add(input);
                inputpanel.add(button);
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

