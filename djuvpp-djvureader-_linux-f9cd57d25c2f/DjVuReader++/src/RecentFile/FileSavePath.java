
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecentFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FATHI HOSSAM
 */
public class FileSavePath {
   
    public static void save(String path) throws IOException{
                FileWriter fstream = new FileWriter("RecentBooks.txt",true);
		BufferedWriter out = new BufferedWriter(fstream);
                List<String> list = new ArrayList<String>();
                BufferedReader br = new BufferedReader(new FileReader("RecentBooks.txt"));
        
                String r = "";
       //       if((r=br.readLine())!=null)
         
                    while ((r = br.readLine()) != null) {
                     list.add(r);
                   }
                    if (!list.contains(path)) 
                    {
		      out.write(path);
		      out.newLine();
                     }else{
                      System.out.println("File found");
                     } 
    
		//close buffer writer
		out.close();
	}
}
    	
    
    
           
        
        
    
    

