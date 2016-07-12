package Save;
///these imports are from itextpdf.jar in the library 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *This class to turn from text to pdf 
 * @author islam youssief 
 */
public class Textpdf {

    /**
     *This class to create a Pdf file 
     * @param filename
     * @param text
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(String filename, String text) throws DocumentException, IOException {
    
        BaseFont EnglishFont = BaseFont.createFont("C:\\Windows\\Fonts\\MODREN.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font EnglishArialFont = new Font(EnglishFont,20);
       
        BaseFont ArabicFont = BaseFont.createFont("C:\\Windows\\Fonts\\MAJALLAB.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font ArabicArialFont = new Font(ArabicFont,20);
        
          
              
         Document document = new Document(PageSize.LETTER);

   
         PdfWriter.getInstance(document, new FileOutputStream(filename)); 
         document.open();

         LanguageProcessor al =new ArabicLigaturizer();
         
         
         String H[];
        H=text.split("\n");
  int i=0;String Collector;
  
  
            
         for( i=0;i<H.length;i++)
         {  
             Collector=H[i];
             
             System.out.println(Collector);
              
         Collector=Collector+"\n" ;    
       Paragraph Arabicprag= new Paragraph(al.process( Collector ),ArabicArialFont);
 
         
         
         
         Paragraph Englishprag= new Paragraph(Collector,EnglishArialFont);
              Arabicprag.setAlignment(Element.ALIGN_RIGHT);
              Englishprag.setAlignment(Element.ALIGN_RIGHT);
         
             document.add(Arabicprag);        
             document.add(Englishprag);
         
         }
         
        document.close();
        System.out.println("pdf complete");
        
        
        
        
        
       
    }//end of createPdf
}//end of class

