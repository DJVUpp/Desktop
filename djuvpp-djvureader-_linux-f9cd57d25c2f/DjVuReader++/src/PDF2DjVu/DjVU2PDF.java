package PDF2DjVu;

import javax.swing.JOptionPane;

public class DjVU2PDF {
    static String path="";
    
	public static void convertDjvu2PDF(String sourcepath,String targetPath ,int s )
	{
             
		try
		{
                   // ddjvu --format=tiff page.djvu page.tiff
                //String tiff_path="/home/"+System.getProperty("user.name")+"/tifoutimg.tiff";
	        //String Qua="\"";
                //String[] command = {"convert" ,"-density", "300", sourcepath, 
                  //  "-depth", "8" ,tiff_path};
                    System.out.println("SOurce path:"+sourcepath);
                    System.out.println("TargetPath"+targetPath);
                    
                 String[] command = {"ddjvu" ,"--format=pdf", sourcepath,targetPath};
                    System.out.println("Cmd :"+command);
                ProcessBuilder probuilder = new ProcessBuilder( command );
               
                Process process = probuilder.start();
        
                //Wait to get exit value
        try {
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
            if(exitValue==0){
                s=1;
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
              //  TIFF_To_PDF.convertTif2PDF(tiff_path,targetPath);
                
                if (s==1){
               
              //  JOptionPane.showMessageDialog(null,"Convert Successful");
                }
	        } 
		catch (Exception e) 
	        { 
                     System.out.println("Error In DJVU2TIFF calss covert");
	            e.printStackTrace();
                   
	        }
	    
        }
}
