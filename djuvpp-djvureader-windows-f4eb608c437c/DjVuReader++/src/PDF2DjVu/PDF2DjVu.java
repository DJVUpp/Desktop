package PDF2DjVu;

import com.lizardtech.djview.frame.GetDir;
import java.io.IOException;
import javax.swing.JOptionPane;

public class PDF2DjVu {
    static String  pdf2DjvuCommandLine="";
   
    public static void PDF2DjVu(String pdfPath,String djvuPath,int s)
	{
   	    try {
			  pdf2DjvuCommandLine=GetDir.getDir();
                         
		    }
	    catch (IOException e1) 
	    {
			e1.printStackTrace();
		} catch (InterruptedException e1)
	    {
			e1.printStackTrace();
		}
        
            System.out.println("Command:"+pdf2DjvuCommandLine);
		try
		{
                    
		    String run=pdf2DjvuCommandLine+"\\pdf2djvu.exe "+pdfPath+" -o "+djvuPath+".djvu";
	             
	            Process p = Runtime.getRuntime().exec(run);
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
