
package Save;

///these imports are from itextpdf.jar in the library 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lizardtech.djvubean.RibbonMenu.DjvuComponents;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;



/**
 *This class to turn from text to pdf 
 * @author islam youssief 
 */
public class Textpdf1 {
    
     /**
     *This class to create a Pdf file 
     * @param filename
     * @param text
     * @throws DocumentException
     * @throws IOException
     */
    
    public void createPdf(String filename, String text) throws DocumentException, IOException {
/*    
     //this is the used font   
    BaseColor col =new BaseColor(ColorSelector.bColor.getRed(), 
            ColorSelector.bColor.getGreen(), ColorSelector.bColor.getBlue());
    BaseFont ArabicFont = BaseFont.createFont("C:\\Windows\\Fonts\\MAJALLAB.ttf",
            BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font font=new Font(Font.FontFamily.HELVETICA, DjvuComponents.font.getSize(),
            DjvuComponents.font.getStyle(),col);
    Font ArabicArialFont = new Font(ArabicFont  , DjvuComponents.font.getSize(),
            DjvuComponents.font.getStyle(),col);
        
        //Font ArabicArialFont = new Font(ArabicFont,20);
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, new FileOutputStream(filename)); 
        document.open();
        LanguageProcessor al =new ArabicLigaturizer();
        String splitedText[];
        splitedText=text.split("\n");
       
        int i=0;String OriginalCollector;
 
        String ArabicCodePoint;
        for( i=0;i<splitedText.length;i++)
         {  
               OriginalCollector=splitedText[i];
                    //                   Collector=Collector+"\n" ;    
             Paragraph Arabicprag= new Paragraph(al.process( OriginalCollector ),ArabicArialFont);
             ArabicCodePoint = Arabicprag.toString();
              
              Paragraph Originalfont= new Paragraph(OriginalCollector,font);
        
             if(OriginalCollector.compareTo(ArabicCodePoint)==0)
              document.add(Originalfont);
                      else
              document.add(Arabicprag);
              
         }
        document.close();
        System.out.println("pdf complete");
    
        
        
*/

        
        
// step 1
      //    Rectangle pageSize = new Rectangle(216, 720);

//         pageSize.setBorderColor(BaseColor.RED);
//         pageSize.setBackgroundColor(BaseColor.BLACK);

   //     Document document = new Document();
        // step 2
    }
}





    
                  //BaseFont EnglishFont = BaseFont.createFont("C:\\Windows\\Fonts\\MODREN.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                  //Font EnglishArialFont = new Font(EnglishFont,20);
       
        
