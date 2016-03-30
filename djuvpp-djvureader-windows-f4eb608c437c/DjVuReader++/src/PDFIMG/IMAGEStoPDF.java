/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDFIMG;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
/**
 * Convert Image To PDF Updated By Abdelrhman Mohamed 
 * @author FATHI HOSSAM
 */
public class IMAGEStoPDF {
   /**
    * Convert Images To Pdf
    * @param RESOURCES
    * @param result
    * @param path
    * @param s
    * @throws FileNotFoundException
    * @throws DocumentException
    * @throws BadElementException
    * @throws IOException 
    */
    public static void convertToIMG(String[] RESOURCES,String result,String path,int s) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
        // step 3
        document.open();
        // step 4
        // Adding a series of images
        Image img;
       for (int i = 0; i < RESOURCES.length; i++) {
            img = Image.getInstance(String.format(path, RESOURCES[i]));
            //if biggger than 800*700 scale it to be fit with page
            if(img.getScaledHeight()>800 &&img.getScaledWidth()>700)
            img.scaleAbsolute(document.getPageSize().getWidth()-50,document.getPageSize().getHeight()-50);
            else{
            img.setAbsolutePosition(document.getPageSize().getWidth()/5, document.getPageSize().getHeight()/2);
            }
            document.add(img);
        }
        // step 5
        document.close();
                     
        if(s==1){
               JOptionPane.showMessageDialog(null,"Converted Successfully");
        }
    }
    
}
