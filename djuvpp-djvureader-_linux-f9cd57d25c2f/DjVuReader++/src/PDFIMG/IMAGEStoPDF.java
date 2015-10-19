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
 *
 * @author FATHI HOSSAM
 */
public class IMAGEStoPDF {
   public static void convertToIMG(String[] RESOURCES,String result,String path,int s) throws FileNotFoundException, DocumentException, BadElementException, IOException {
System.out.println(":)");
       
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
        // step 3
        document.open();
        // step 4
        // Adding a series of images
        Image img;
System.out.println(":) :)");        
       for (String image:RESOURCES) {
           System.out.println(image);
            
           img = Image.getInstance(path+image);
            
            document.setPageSize(img);
            document.newPage();
            img.setAbsolutePosition(0, 0);
            document.add(img);
            System.out.println(":(");
        }
       System.out.println(":) :) :) Pdf is outo");
        // step 5
        document.close();
                     
        if(s==1){
               JOptionPane.showMessageDialog(null,"Converted Successfully");
            //JOptionPane.showMessageDialog(null, "Finished\nFile save in :\nC:\\DjVu++Task\\ImagestoPDF\\"+RESOURCES[0].substring(0,RESOURCES[0].lastIndexOf("."))+".pdf");
        }
    }

}
