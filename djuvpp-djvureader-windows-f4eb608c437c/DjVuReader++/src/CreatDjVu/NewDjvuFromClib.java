package CreatDjVu;

import static CreatDjVu.NewDjvu.textArea;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

/**
 * This class to create a new Djvu From Clipboard
 *
 * @author islam youssief
 */
public class NewDjvuFromClib {

    /**
     * This is the main function
     *
     * @param args
     */
   

    /**
     * This is JTextPane holds the text Area from the Clip;
     */
    public static JTextPane textAreaClip;

    /**
     * This function is to create the frame
     */
   // public static JFrame frame;
    public static void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            
            public void run() {
        JFrame frame = new JFrame("Write here");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setAlwaysOnTop(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                textAreaClip = new JTextPane();
                String data = null;
 //               JButton edite = new JButton("Edit");
//                JButton SaveDjvu = new JButton(" ~ Save Djvu ~");
//                edite.setEnabled(false);
                try {
                    System.out.println((String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor));
                    data = (String) Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(DataFlavor.stringFlavor);
                  
                  //Flavor is not a text  
                  if (data.equals(""))
                  { 
                      JOptionPane.showMessageDialog(null,   "Nothing stored in the clipboard !","Information",JOptionPane.INFORMATION_MESSAGE);
                     
                  
                  }        
                
                } catch (UnsupportedFlavorException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Nothing stored in the clipboard !!");
 }

                  textAreaClip.setFont(new Font("Arial", 0, 16));
                textAreaClip.setText(data);
           JScrollPane scroller = new JScrollPane(textAreaClip);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
     //           JButton create = new JButton(" ~ Save Djvu ~ ");
                //JButton Exit = new JButton(" Exit ");
                //Exit.setEnabled(true);
                
                
                
                
                //That to control the cursor between English and Arabic 
                  /******************************/
InputContext currenyText = InputContext.getInstance();
 if(currenyText.getLocale().getDisplayLanguage()=="Arabic")
 {
     Locale arabicSaudiArabia = new Locale.Builder().setLanguage("ar").setRegion("SA").build();
     textAreaClip.setComponentOrientation( ComponentOrientation.getOrientation(arabicSaudiArabia));
 }
 
 if(currenyText.getLocale().getDisplayLanguage()=="English")
 {
     Locale EnglishCursor = new Locale.Builder().setLanguage("en").build();
    textAreaClip.setComponentOrientation( ComponentOrientation.getOrientation(EnglishCursor));
  }
                 /******************************/
 
 
 
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(frame,
                                "Are you sure to close this window?", "Really Closing?",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                ObjButtons, ObjButtons[1]);
                        //closing the frame
                        if (PromptResult == 0) {
                            frame.dispose();
                        } else {

                            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                    }

                });
 /**
 *  implementation of Buttons 
 */
    
                
 /*         
                create.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                     frame.setAlwaysOnTop(false);
                        String te = textAreaClip.getText().trim();
                        try {
//check if the text is empty
                            if (textAreaClip.getText().isEmpty() || te.equals("")) {
                                JOptionPane.showMessageDialog(null, "Text is empty ");
                            } else {

//creating a new pdf 
                                
                                        new Textpdf().createPdf("C:\\DjVu++Task\\newDjvu.pdf", textAreaClip.getText());
                                        PDF2DjVu.PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\newDjvu.pdf", "C:\\DjVu++Task\\" + "ss");

                            
                                    }

                            
                            
                        }
                        catch (HeadlessException | DocumentException | IOException ex) {
                             if (textAreaClip.getText().isEmpty())
                            JOptionPane.showMessageDialog(null, "Nothing stored in the clipboard !", "Information", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            createFrame();

                        } finally {//delete the temporary pdf file saved in the directory
                            File write = new File("C:\\DjVu++Task\\newDjvu.pdf");
                            if (write.exists()) {
                                write.delete();
                            }
                        }
                    
                    }
                });
     */
                DefaultCaret caret = (DefaultCaret) textAreaClip.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.getContentPane().add(BorderLayout.SOUTH, inputpanel);
                frame.setVisible(true);
                frame.setResizable(true);
     
//Dimensions in the main window
                frame.setLocation(0,180);
                frame.setSize(1365,550);                
            

//Dimensions in File menu (don't remove them)
//                frame.setSize(969,700);
//                frame.setLocation(409,26);
                
            }
        });
    }
}
