
package RecentFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class used to save paths of books and there names
 * @author ISLAM_YOUSSIEF
 */
public class FileSavePath {
   /**
    * This function is made to store the Recently files 
    * @param path
    * @throws IOException 
    */
   
    public static void save(String path) throws IOException{
        FileWriter fstream = new FileWriter("RecentBooks.txt",true);
		BufferedWriter out = new BufferedWriter(fstream);
                List<String> list = new ArrayList<String>();
                BufferedReader br = new BufferedReader(new FileReader("RecentBooks.txt"));
                String readline = "";
      
    
       
          try{
          while ((readline = br.readLine()) != null) {
                    list.add(readline);
                   }
                    if (!list.contains(path)) 
                    { System.out.println("New path added :"+path);       
//This code is to accept only DJVU files in the recently files
		        if(path.contains("djvu"))
                        {
                            out.write(path);
                            out.newLine();
                        }
                      
                     }else{
                      System.out.println("File found");
                     } 
      	out.close();
      }
      
      catch(IOException e)
      {System.out.println("Error in reading the file ! ");}
	
      }
}
    	



