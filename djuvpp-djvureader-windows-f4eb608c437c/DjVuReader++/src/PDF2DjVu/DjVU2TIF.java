package PDF2DjVu;

import static PDF2DjVu.TIFF_To_PDF.convertTif2PDF;
import com.lizardtech.djview.frame.GetDir;
import java.io.IOException;
import javax.swing.JOptionPane;

public class DjVU2TIF {
    static String path="";
    
	public static void convertDjvu2PDF(String sourcepath,String targetPath ,int s )
	{
               try {
			 path=GetDir.getDir();
		    }
	    catch (IOException e1) 
	    {
			e1.printStackTrace();
		} catch (InterruptedException e1)
	    {
			e1.printStackTrace();
		}
		try
		{
		String run=path+"/ddjvu.exe -format=tiff  "+sourcepath+"  C:\\DjVu++Task\\tiffff.tif";     
	        Process p = Runtime.getRuntime().exec(run);
	        System.out.println("Converted Successfuly");
	        p.waitFor();
                convertTif2PDF("C:\\DjVu++Task\\tiffff.tif",targetPath);
                if (s==1){
                //JOptionPane.showMessageDialog(null, "Finished\nFile save in : \nC:\\DjVu++Task\\DjVutoPDF\\"+sourcepath.substring(sourcepath.lastIndexOf("\\"),sourcepath.lastIndexOf("."))+".pdf");
                JOptionPane.showMessageDialog(null,"Convert Successful");
                }
	        } 
		catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    
	        

        }
}
