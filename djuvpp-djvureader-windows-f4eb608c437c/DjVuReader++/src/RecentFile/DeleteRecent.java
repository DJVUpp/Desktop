/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecentFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author islam youssief
 */
public class DeleteRecent {
    
        public static void DelRecentFiles() {                                          
        

 String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null,
                                "Do you want to delete all recently files?", "Refresh ?",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                ObjButtons, ObjButtons[1]);
        //yes option
                        if (PromptResult == 0) {
                        
        File fil = new File("RecentBooks.txt");
        if (fil.exists()) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(fil);
                writer.print("");
            
            } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, " problem in deleting recently files  ! ~ ");
            }
                                                      
            finally {
                writer.close();
                 }
        }
                        }
                        
        }
}



    