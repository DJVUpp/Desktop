package Save;

import static CreatDjVu.NewDjvu.textArea;
import static CreatDjVu.NewDjvuFromClib.textAreaClip;
import com.itextpdf.text.DocumentException;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * This class returns SaveAs PDF AND SaveAs DJVU
 * @author islam youssief
 */
public class SaveAS {
     static   String text;
 
     /**
      * This is function used to Save as PDF file 
      */
    public static void saveAsPdf() {

                      try{
                                    text = textArea.getText().trim();
                      }catch(Exception v)
                      {             text = textAreaClip.getText().trim();
                      }  
                             
                            try{ 
                             
                          if (text.equals("")) 
                                JOptionPane.showMessageDialog(null, "There was nothing written to be Stored  ~ ");
                          else {
                               
                                    try {
//                                            FileDialog pathSave = new FileDialog(new java.awt.Frame(), "Save pdf", FileDialog.SAVE);
//                                            pathSave.setVisible(true);
//                                            pathSave.setAlwaysOnTop(true);
//                                            
                                             FileChooser pathSave = new FileChooser();
                                             pathSave.setTitle("Save As pdf");
                                             setExtFilters(pathSave);
                                             Stage secondStage=null;
                
                                       File file = pathSave.showSaveDialog(secondStage);
                                      if (file == null) {
             JOptionPane.showMessageDialog(null, " You have Canceled saving !  ");
               return;
                                      }
                                          String path=file.getPath()+".pdf";
                                            new Textpdf().createPdf(path, text);
                                            JOptionPane.showMessageDialog(null, " ~ Finished Successfully ~ ");
                                        }
                                     catch (DocumentException | IOException ex) {
                                           JOptionPane.showMessageDialog(null, " ~ Error while Writing in the pdf ~ ");}
                                }
                          
                            }
                    catch(Exception w)
                            {JOptionPane.showMessageDialog(null, " ~ Finished Successfully ~ ");}
                               
                      }
       
    
    
 private static void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files ", "*.*"),
                new FileChooser.ExtensionFilter("All PDF ", "*.pdf")
        );
    }
/**
 * This is function used to Save as PDF file 
 */
public static void saveAsDjvu()
{

         String text;
                      try{
                                    text = textArea.getText();
                      }catch(Exception v)
                      {             text = textAreaClip.getText();
                      }
                       
                       
                      try{
                        //check if the text is empty
                            if (text.equals("")) {
                                JOptionPane.showMessageDialog(null, "Text is empty ");
                            } else {
                                    try {
                                        //creating a new pdf 
                                         new Textpdf().createPdf("C:\\DjVu++Task\\newDjvu.pdf", text);
                                        //Turning the PDF 2 DjVu
                                        PDF2DjVu.PDF2DjVu("C:\\DjVu++Task\\newDjvu.pdf", "C:\\DjVu++Task\\" + "NameOfDjvuFile");
                                     
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
                        catch(Exception A)
                            {JOptionPane.showMessageDialog(null, " ~ Finished Successfully ~ ");}
                   }


}
        
