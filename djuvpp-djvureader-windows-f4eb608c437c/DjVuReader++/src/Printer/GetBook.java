/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import static com.lizardtech.djview.frame.DjvuStart.beanMap;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import com.lizardtech.djvu.Document;
import com.lizardtech.djvubean.*;
import com.lizardtech.djvubean.outline.CreateThumbnails;
//import static com.lizardtech.djvubean.outline.OutlineTabbedPane.docUrl;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author FATHI HOSSAM
 */
public class GetBook {
    
    private static Image PrinterBookImages[];
    public static void GetBookImage() throws IOException{
                 DjVuBean bean=beanMap.get(name_url.get(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())));
                 final Document document = new Document();
                 document.read(bean.getURL());
                 PrinterBookImages = new Image[bean.getDocumentSize()];
                    for (int i = 0; i < PrinterBookImages.length; i++) {
                        //for Printer 
                        BufferedImage imPrinter;
                        imPrinter = CreateThumbnails.generateThumbnail(i, 638, 960);
                        PrinterBookImages[i]=imPrinter;
                     //   ImageIO.write((RenderedImage)PrinterBookImages[i], "jpg", new File("D:\\gh\\"+(i+1)+".jpg"));
                        
                    }
                   
    }
                    
       
   public static Image[] getPrintBookImage(){
   
     return PrinterBookImages;
   }            
                    
    
}
    

