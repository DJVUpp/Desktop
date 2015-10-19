package PDF2DjVu;

import com.lizardtech.djview.frame.GetDir;
import java.io.IOException;
import javax.swing.JOptionPane;

public class PDF2DjVu {
    static String  pdf2DjvuCommandLine="";
   
    public static void PDF2DjVu(String pdfPath,String djvuPath,int s)
	{
		try
		{
                    
		    //String run=pdf2DjvuCommandLine+"\\pdf2djvu.exe "+pdfPath+" -o "+djvuPath+".djvu";
                   //String run="pdf2djvu -o "+djvuPath+".djvu"+pdfPath+".pdf";
	           String run="pdf2djvu -o "+djvuPath+".djvu  "+pdfPath;
                            final Process p = Runtime.getRuntime().exec(run);
                            System.out.println(run);
                    System.out.println("Convert Run Successfullly");
	            p.waitFor();
                    p.destroy();
                           if(s==1){
                            JOptionPane.showMessageDialog(null,"Convert Sucessfully");
                           }
                           if (s==2){
                               //JOptionPane.showMessageDialog(null,"Convert Sucessfully\nFile save in :\n"+djvuPath);
                            JOptionPane.showMessageDialog(null,"Convert Sucessfully");

                           }
                          
	        } 
		catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
               
	    
		
	}

	

}
