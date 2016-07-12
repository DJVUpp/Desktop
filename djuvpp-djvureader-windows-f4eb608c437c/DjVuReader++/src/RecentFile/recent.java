package RecentFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;


/**
 *This class returns the names and paths of the books open before from recently file
 * @author islam youssief
 */
public class recent {
   File file = null;
    FileReader reader = null;
    BufferedReader bufferedReader = null;
    String filerecent;
    String books[];
    File bookfiless[];
 String Realbooks[];
    List<String> booksfile = new ArrayList<String>();

    int i = 0;
    JList booklist;
    
    
    
    
    /**
     * Read the text 
     */
    public recent()  {
              
        file = new File("RecentBooks.txt");
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            while ((filerecent = bufferedReader.readLine()) != null) {
                booksfile.add(i, filerecent);
                i++;
            }
                int w = 0;
            books = new String[i];
            bookfiless = new File[i];
           
            for (int a = i-1; a >= 0; a--) {
                bookfiless[a] = new File(booksfile.get(w));
                books[a] = bookfiless[a].getName();
                w++;
            }

        } catch (Exception ex) 
        {
          JOptionPane.showMessageDialog(null,   "Recently file was deleted !!","Information",JOptionPane.INFORMATION_MESSAGE);
        //Create new recentlyfile storing recentlyBooks if it was deleted
        File file = new File("RecentBooks.txt");
        if (!file.exists()) {
              try {
                  BufferedWriter br = new BufferedWriter(new FileWriter(file));
              } catch (IOException ex1) {
        JOptionPane.showMessageDialog(null,   "There is a problem in creating new recently file !!","Information",JOptionPane.INFORMATION_MESSAGE);
              }
        }
       }             
  
    
    }

        
 
                
    /**
     *This function returns the names of recently files books 
     * @return 
     */
    public String[] getrecentfile() {
 
   String myBooks[] = null;
       return books;
    
    }          
         /**
          * This function returns the paths of recently files books
          * @return 
          */       
  public File[] getbookfile() {
        return bookfiless;
    }
    
}
