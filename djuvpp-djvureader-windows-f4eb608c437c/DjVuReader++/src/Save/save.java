package Save;

import static CreatDjVu.NewDjvu.textArea;
import static CreatDjVu.NewDjvuFromClib.textAreaClip;
import com.itextpdf.text.DocumentException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *This function used to save DJVU files
 * @author islam youssief
 */
public class save {
/**
 * This function used to save Djvu file
 */
    public static void saveFile()
    {
          String text;
                      try{
                                    text = textArea.getText().trim();
                      }catch(Exception v)
                      {             text = textAreaClip.getText().trim();
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
                            {JOptionPane.showMessageDialog(null, " ~ Finished Successfully~ ");}
    }
    }
                  
