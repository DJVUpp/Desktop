/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFIMG;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author FATHI HOSSAM
 */
public class PDF2IMG {
    
    public static void convertToIMG(String pathpdf,String output,int s) {
		
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
                if(s==1||s==2){
                    JOptionPane.showMessageDialog(null,"Converted Successfully");
         //JOptionPane.showMessageDialog(null, "Finished\nFile save in :\n"+output.substring(0,output.lastIndexOf("\\")));
                }
                //if(s==2){
         //JOptionPane.showMessageDialog(null, "Finished\nFile save in :\n"+output.substring(0,output.lastIndexOf("\\")));
                //}
    }    
}
