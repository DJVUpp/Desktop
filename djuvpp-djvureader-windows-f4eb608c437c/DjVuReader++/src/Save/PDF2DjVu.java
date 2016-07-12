package Save;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * This class of Turning from PDF to DjVu
 *
 * @author islam youssief
 */
public class PDF2DjVu {

    static String pdf2DjvuCommandLine = "";
    
    static File SaveDjVu;

    static int res = 0; //that var will be used to control the saving state
    
    /**
     *  This function is set for the save Dialog 
     *   
     * @return 
     */
  public  static File setframe() {
//        Frame FrameSave = new java.awt.Frame();
//        FileDialog SaveDjVu = new FileDialog(FrameSave, "Save DjVu", FileDialog.SAVE);
//        SaveDjVu.setVisible(true);
//        FrameSave.dispose();
//        return SaveDjVu;        
//    }

      FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Djvu file");
                setExtFilters(fileChooser);
        Stage secondStage=null;
                
                File file = fileChooser.showSaveDialog(secondStage);
                if (file == null) {
             JOptionPane.showMessageDialog(null, " You have Canceled saving !  ");
             File excepFile =new File("");
             return excepFile;
                }
  
  return file;
             
  }
  
 private static void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("All DJVU", "*.djvu")
        );
    }
    /**
    * this function is made to return the frame
    * @return the Save Dialog  
    */
    public  static File getframe() {

        return PDF2DjVu.SaveDjVu;
    }
/**
 * This function is made to turn PDF File into DJVU File
 * @param pdfPath
 * @param djvuPath 
 */
    public static void PDF2DjVu(String pdfPath, String djvuPath) {
        try {
            pdf2DjvuCommandLine = GetDir.getDir();
        } catch (IOException | InterruptedException e1) {
                 JOptionPane.showMessageDialog(null, "Error while reading the location of the file !!");
                         }

        System.out.println("Command:" + pdf2DjvuCommandLine);
        try {   /*pdf2djvu using command line !! */

            String run = pdf2DjvuCommandLine + "\\pdf2djvu.exe " + pdfPath + " -o " + djvuPath + ".djvu";
            Process proc = Runtime.getRuntime().exec(run);
            System.out.println("command is "+run);
            proc.waitFor();
            proc.destroy();

        } catch (IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Error while creating a Djvu file !!");
                       
        } finally {

                 if (res == 0 ) {
                        SaveDjVu = setframe();
 
                //saving the file 
                if (SaveDjVu.getPath() != null) {
                    File filedir = new File(SaveDjVu.getPath());
               //show the directory of the DJVU file 
            Path p1 = Paths.get(djvuPath + ".djvu");
            System.out.println(p1);
            //String path2 = SaveDjVu.getDirectory() + SaveDjVu.getFile();
            String path2 = SaveDjVu.getPath();
            String path22 = path2 + ".djvu";
            Path p2 = Paths.get(path22);
            System.out.println("P2 :  "+p2);
            res = 1;
                    try {
                        Files.copy(p1, p2, REPLACE_EXISTING);
                          String url;
                          File open = new File(p2.toString());
                          System.out.println("this is open  "+open);
                          url = "" + open.toURI();
                          url = url.substring(5, url.length());
                          String name = open.getName();
                          
                          // FIXME: fix the next line.
//                         src.RibbonMenu.DjvuMain.openBookInNewTab(url, name);
                      
                                        
                        JOptionPane.showMessageDialog(null, " ~ Finished Successfully ~ ");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error while reading the location of the file !!");
                    } finally {
                        File del = new File(djvuPath + ".djvu");
                        if (del.exists()) {
                            del.delete();
                        }
                    }

                }
                if(SaveDjVu.getPath()==null);
                {
                 File canceledfile = new File("C:\\DjVu++Task\\NameOfDjvuFile.djvu");
                if (canceledfile.exists()) 
                    canceledfile.delete();
                }
                }
                 else {
                // overwrite option 
                int ans;
                 String[] buttonss = {"YES", "NO"};
                 ans = JOptionPane.showOptionDialog(null, " Overwrite ? ", " ~~  Overwrite ?  ~~",
                 JOptionPane.DEFAULT_OPTION, 0, null, buttonss, buttonss[0]);
                 if (ans == 0) {
                               File c = getframe();
                 //show the directory of the DJVU file 
                                Path firstPath = Paths.get(djvuPath + ".djvu");
                                System.out.println(firstPath);
                   // String path2 = c.getDirectory() + c.getFile();
                    String path2 = c.getPath();
                   
                    String path22 = path2 + ".djvu";
                    Path secondPath = Paths.get(path22);
                    System.out.println(secondPath);
                    res = 1;
                    //saving the file 
                    if (c.getPath()!= null) {
                        try {
                            Files.copy(firstPath, secondPath, REPLACE_EXISTING);
                            String Newurl;
                            File open = new File(secondPath.toString());
                            String Newname = open.getName();
                            Newname = open.getName();
                            Newurl = ""+ open.toURI();
                            Newurl = Newurl.substring(5, Newurl.length());
                            JOptionPane.showMessageDialog(null, " ~ Your Modification Is Saved ~ ");
                          
                    
                           
                            
                            
                            
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error while reading the location of the file !!");
                        } finally {
                            File del = new File(djvuPath + ".djvu");
                            if (del.exists()) {
                                del.delete();
                            }
                                  }
                        }
                }
//                 control the save state
                else if (ans==1)
                { 
                    res=0;
                }
     }
                   }   

   
     
}   

}   
