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
import static com.lizardtech.djview.frame.DjvuStart.curropen;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.DjvuStart.url_name;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class NewDjvuFromClib {

    public static JTextPane textAreaClip;

    public static void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame("Write here");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setAlwaysOnTop(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                textAreaClip = new JTextPane();
                String data = null;
                try {
                    data = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(NewDjvuFromClib.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewDjvuFromClib.class.getName()).log(Level.SEVERE, null, ex);
                }

                textAreaClip.setText(data);
                textAreaClip.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textAreaClip);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                final JTextField input = new JTextField(20);
                JLabel name = new JLabel("DjVu Name : ");
                final JButton create = new JButton("Create");
                final JButton edite = new JButton("Edite");
                edite.setEnabled(false);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(frame,
                                "Are you sure to close this window?", "Really Closing?",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                ObjButtons, ObjButtons[1]);
                        if (PromptResult == 0) {

                            frame.dispose();
                        } else {

                            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                    }

                });
                edite.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        create.setEnabled(true);
                        edite.setEnabled(false);
                        textAreaClip.setEditable(true);
                    }
                });

                create.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //FileWriter w = null;

                        String te = textAreaClip.getText().trim();
                        try {

                            if (textAreaClip.getText().isEmpty() || te.equals("")) {
                                JOptionPane.showMessageDialog(null, "Text is empty ");
                            } else {

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
                                        textAreaClip.setEditable(false);
                                        input.setText("");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Input name of file");
//                                  
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
                DefaultCaret caret = (DefaultCaret) textAreaClip.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(name);
                inputpanel.add(input);
                inputpanel.add(create);
                inputpanel.add(edite);

                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.getContentPane().add(BorderLayout.SOUTH, inputpanel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setSize(500, 500);
                frame.setResizable(true);

            }
        });
    }
}
