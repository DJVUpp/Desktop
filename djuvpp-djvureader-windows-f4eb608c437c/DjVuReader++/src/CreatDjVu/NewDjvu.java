package CreatDjVu;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.im.InputContext;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;
/**
 *This class to create a new DJVu  
 * @author islam youssief
 */
public class NewDjvu {

   
   
    /**
     *This is JTextPane holds the text Area from the Clip;
     */
    public static JTextPane textArea;

    /**
     *This function is to create the frame 
     */

    public static void createFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame   frame = new JFrame("Write here");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setAlwaysOnTop(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/DjvuIcon.png")));
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                textArea = new JTextPane();
                textArea.setFont(new Font("Arial", 0, 16));
                JScrollPane scroller = new JScrollPane(textArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                        
                
   //To control the Cursor Direction between English and Arabic 
                  /******************************/
InputContext currenyText = InputContext.getInstance();
 if(currenyText.getLocale().getDisplayLanguage()=="Arabic")
 {
     Locale arabicSaudiArabia = new Locale.Builder().setLanguage("ar").setRegion("SA").build();
     textArea.setComponentOrientation( ComponentOrientation.getOrientation(arabicSaudiArabia));
 }
 
 if(currenyText.getLocale().getDisplayLanguage()=="English")
 {
     Locale EnglishCursor = new Locale.Builder().setLanguage("en").build();
    textArea.setComponentOrientation( ComponentOrientation.getOrientation(EnglishCursor));
  }
                 /******************************/


                
                
                //JButton SaveDjvu = new JButton(" ~ Save Djvu ~");
                //JButton Exit = new JButton("Exit");
                
   /* Close the window or nor ? */
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
                        } else {//cancelling the closing of the file

                            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                    }
                });

   /**
    * implementation of buttons
    */            
//Exit button function
              /*
                Exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      frame.dispose();
                                     }
                });
                */
//Save button function
                
                /*
                        SaveDjvu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String te = textArea.getText().trim();
                       //check if the text is empty
                            if (textArea.getText().isEmpty() || te.equals("")) {
                                JOptionPane.showMessageDialog(null, "Text is empty ");
                            } else {
                                    try {
                                        //creating a new pdf 
                                         new Textpdf().createPdf("C:\\DjVu++Task\\newDjvu.pdf", textArea.getText());
                                        //Turning the PDF 2 DjVu
                                        PDF2DjVu.PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\newDjvu.pdf", "C:\\DjVu++Task\\" + "NameOfDjvuFile");
                                     
                                    } 
                                    catch (DocumentException | IOException | HeadlessException ex) 
                                        { JOptionPane.showMessageDialog(null, " ~ Process Canceled --> hint : Make sure of your storage ! ~ ");}
                                                            
                                    }

                            //delete the temporary pdf file saved in the directory
                            File write = new File("C:\\DjVu++Task\\newDjvu.pdf");
                            if (write.exists()) {
                                write.delete();
                            }
                            
                        }
                   
                });
                 */
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
//                inputpanel.add(SaveDjvu);
               // inputpanel.add(Exit);
                //inputpanel.add(edite);
                //inputpanel.add(Top);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.setVisible(true);
                frame.setResizable(true);

          
//Dimensions in the main window
                frame.setLocation(0,180);
                frame.setSize(1365,550);               

//Dimensions in File menu
//                frame.setSize(969,700);
//                frame.setLocation(409,26);
               
                 
                
            }

        });
        
    }

}
