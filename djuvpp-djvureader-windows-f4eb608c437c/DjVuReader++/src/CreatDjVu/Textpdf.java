/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreatDjVu;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lizardtech.djvubean.RibbonMenu.ColorSelector;
import com.lizardtech.djvubean.RibbonMenu.DjvuComponents;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author FATHI HOSSAM
 */
public class Textpdf {
     public void createPdf(String filename,String text)
	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        
        BaseColor col=new BaseColor(ColorSelector.bColor.getRed(), ColorSelector.bColor.getGreen(), ColorSelector.bColor.getBlue());
      

      
       Font font=new Font(Font.FontFamily.HELVETICA, DjvuComponents.font.getSize(), DjvuComponents.font.getStyle(),col);
     //   for (String booksfile1 : booksfile) {
            document.add(new Paragraph(text,font));
//        }
        // step 5
        document.close();
    }
}
