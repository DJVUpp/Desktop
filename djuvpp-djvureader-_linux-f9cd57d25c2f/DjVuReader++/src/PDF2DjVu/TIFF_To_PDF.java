package PDF2DjVu;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
 
public class TIFF_To_PDF {
 
	public static void convertTif2PDF(String tifPath,String path)
	{
	      System.out.println("one");
	      String arg[]={tifPath};
	      System.out.println("one2");
	    if (arg.length < 1) {
	      System.out
	         .println("Usage: Tiff2Pdf file1.tif [file2.tif ... fileN.tif]");
	      System.exit(1);
	    }
	      try{
                String run2="convert "+tifPath+" " +path+".pdf";
                 
                System.out.println(run2);
	        final Process p2 = Runtime.getRuntime().exec(run2);
                
                p2.waitFor();
                System.out.println("Process 2 exit value "+p2.exitValue());
                p2.destroy();
                
              }catch(Exception e){
                  System.out.println(":( Error while convert .tiff image to pdf"+e);
              }
	    }
}