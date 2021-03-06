package Save;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class returns the directory and name of files
 * @author islam youssief
 */
public class GetDir {
	
	 static ArrayList<String> list=new ArrayList<String>();
	 
	 static String finalName="";
	 
	public static String getDir() throws IOException, InterruptedException 	
	{	
		getFiles(new File("C:\\"));
		
		for(int i=0;i<list.size();i++)
		{
			finalName=getSubFiles(new File(list.get(i)));
		}
		return finalName;
	
	}
	/**
         * This function returns the files paths and subdirectories
         * @param file 
         */
	public static void getFiles(File file)
	{
	 String fileName="Program";
	
	 //String path="";
		
     if(file.isDirectory()) 
     {
	   // All files and subdirectories
	  File[] files=file.listFiles();
		 for(int i = 0; files != null&& i < files.length; i++)
		 {
			if(files[i].toString().contains(fileName))
			{
				fileName=files[i].getPath();
				list.add(fileName);
				
			}
		 }
	  }
	}
	/**
         * This function returns the subfiles 
         * @param file
         * @return 
         */
	public static String getSubFiles(File file)
	{
	 String fileName="DjVu++";
	
	 //String path="";
		
     if(file.isDirectory()) 
     {
	   // All files and subdirectories
	  File[] files=file.listFiles();
		 for(int i = 0; files != null&& i < files.length; i++)
		 {
			if(files[i].toString().contains(fileName))
			{
				fileName=files[i].getPath();
				finalName=fileName;
				
			}
		 }
	  }
	return fileName;
	}

}
