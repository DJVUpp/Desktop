/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import com.itextpdf.text.DocumentException;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdelrhman Mohamed
 */
public class Convertings {
    
    /**
     * Run Commands By Thread
     * @param progressBar
     * @param command
     * @param barValue
     * @throws InterruptedException 
     */
    
    String imdjvu;
    String openimgs = "";
    String PDFFullsource = null;
    File imagess[];
    String[] imageNAME;

    public void runCommand(ProgressBar progressBar, String command, int barValue) throws InterruptedException {
        ConvertThread copyThread = new ConvertThread(command);
        copyThread.start();
        // wait for thread to die
        
        
        copyThread.join();
        progressBar.updatePercent(barValue);
        
    
        }
    /**
     * Class to Convert by thread
     */
    public static class ConvertThread extends Thread {
        public String cmd;

        public ConvertThread(String command) {
            this.cmd = command;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                String line;
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
                p.destroy();
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
                
            }
            
        }
}
    public  void StartConverting(String EXEfile,String Inputfilepath,String Outputfile,String Com) {
        try {
            ProgressBar progressBar = new ProgressBar();
            progressBar.showProgress();
            // first command
            
            String Command ="C:\\Program Files (x86)\\DjVu++\\"+EXEfile+" "+Inputfilepath+" "+Com+" "+"C:\\DjVu++Task\\"+Outputfile;  
            runCommand(progressBar, Command, 25);
            runCommand(progressBar, Command, 50);
            runCommand(progressBar, Command, 75);
            runCommand(progressBar, Command, 100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Convertings.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    //Converting Tif(Images) To Pdf
    public void convertTif2PDF(String tifPath,String path)
	{
	      System.out.println("one");
	      String arg[]={tifPath};
	      System.out.println("one2");
	    if (arg.length < 1) {
	      System.out
	         .println("Usage: Tiff2Pdf file1.tif [file2.tif ... fileN.tif]");
	      System.exit(1);
	    }
	    String tiff;
	    String pdf;
	    System.out.println("two");
	    for (int i = 0; i < arg.length; i++) {
	       tiff = arg[i];
	       pdf = path+".pdf";
	       Document document = new Document(PageSize.LETTER, 0, 0, 0, 0);
	       try {
	          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
	          int pages = 0;
	          document.open();
	          PdfContentByte cb = writer.getDirectContent();
	          RandomAccessFileOrArray ra = null;
	          int comps = 0;
	          try {
	             ra = new RandomAccessFileOrArray(tiff);
	             comps = TiffImage.getNumberOfPages(ra);
	          } 
	          catch (Throwable e) {
	             System.out.println("Exception in " + tiff + " "
	               + e.getMessage());
	             continue;
	          }
	          System.out.println("Processing: " + tiff);
	          for (int c = 0; c < comps; ++c) {
	             try {
	                Image img = TiffImage.getTiffImage(ra, c + 1);
	                if (img != null) {
	                  System.out.println("page " + (c + 1));
	                  System.out.println("img.getDpiX() : "+img.getDpiX());
	                  System.out.println("img.getDpiY() : "+img.getDpiY());
	                  img.scalePercent(6200f / img.getDpiX(), 6200f / img.getDpiY());
	                  //img.scalePercent(img.getDpiX(), img.getDpiY());
	                  //document.setPageSize(new Rectangle(img.getScaledWidth(), img.getScaledHeight()));
	                  img.setAbsolutePosition(0, 0);
	                  cb.addImage(img);
	                  document.newPage();
	                  ++pages;
	                }
	              } 
	              catch (Throwable e) {
	                System.out.println("Exception " + tiff + " page "
	                  + (c + 1) + " " + e.getMessage());
	              }
	          }
	          ra.close();
	          document.close();
	          } 
	          catch (Throwable e) {
	             e.printStackTrace();
	          }
	          System.out.println("done");
	       }
	    }
    //Convert Pdf to Images
    public  void convertToIMG(String pathpdf,String output,int s) {
		
		File file = new File(pathpdf);
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "r");

			FileChannel channel = raf.getChannel();
			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			// draw the first page to an image
			int num=pdffile.getNumPages();
			for(int i=0;i<num;i++)
			{
				PDFPage page = pdffile.getPage(i);
				
				//get the width and height for the doc at the default zoom				
				int width=(int)page.getBBox().getWidth();
				int height=(int)page.getBBox().getHeight();				
				
				Rectangle rect = new Rectangle(0,0,width,height);
				int rotation=page.getRotation();
				Rectangle rect1=rect;
				if(rotation==90 || rotation==270)
					rect1=new Rectangle(0,0,rect.height,rect.width);
				
				//generate the image
				BufferedImage img = (BufferedImage)page.getImage(
							rect.width, rect.height, //width & height
							rect1, // clip rect
							null, // null for the ImageObserver
							true, // fill background with white
							true  // block until drawing is done
					);
                               
				ImageIO.write(img, "png", new File(output+(i+1)+".png"));
			}
		} 
		catch (FileNotFoundException e1) {
			System.err.println(e1.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
    }    
    public void PdfToIMG(String pathpdf,String output) {
		
		File file = new File(pathpdf);
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "r");

			FileChannel channel = raf.getChannel();
			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			// draw the first page to an image
			int num=pdffile.getNumPages();
			for(int i=0;i<num;i++)
			{
				PDFPage page = pdffile.getPage(i);
				
				//get the width and height for the doc at the default zoom				
				int width=(int)page.getBBox().getWidth();
				int height=(int)page.getBBox().getHeight();				
				
				Rectangle rect = new Rectangle(0,0,width,height);
				int rotation=page.getRotation();
				Rectangle rect1=rect;
				if(rotation==90 || rotation==270)
					rect1=new Rectangle(0,0,rect.height,rect.width);
				
				//generate the image
				BufferedImage img = (BufferedImage)page.getImage(
							rect.width, rect.height, //width & height
							rect1, // clip rect
							null, // null for the ImageObserver
							true, // fill background with white
							true  // block until drawing is done
					);
                               
				ImageIO.write(img, "png", new File(output+(i+1)+".png"));
			}
		} 
		catch (FileNotFoundException e1) {
			System.err.println(e1.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}
    }  
    /**
     * convert images to djvu
     * @param evt 
     */
    public void convertimagestodjvu() throws DocumentException, IOException {                                            
                    String path ="C:\\DjVu++Task\\ImgDjVu";
                     String fullpath=null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.jpeg","*.png","*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        int flag=0;
        try {
             fullpath=selectedFiles.get(0).getAbsolutePath();
            
        } catch (Exception e) {
            flag=1;
        }
        
          if (flag==0)
          {
                    ImagesToPDF h=new ImagesToPDF();
                    h.IMGToPDF(fullpath, "C:\\DjVu++Task\\pdfDjvu.pdf");
                    StartConverting("pdf2djvu.exe","C:\\DjVu++Task\\pdfDjvu.pdf","C:\\DjVu++Task\\"+selectedFiles.get(0).getName()+".djvu"," -o "); 
                    

                }
    }
            }
        
                                               
    