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
            
            document.add(img);
        }
        // step 5
        document.close();
                     
        if(s==1){
               JOptionPane.showMessageDialog(null,"Converted Successfully");
            //JOptionPane.showMessageDialog(null, "Finished\nFile save in :\nC:\\DjVu++Task\\ImagestoPDF\\"+RESOURCES[0].substring(0,RESOURCES[0].lastIndexOf("."))+".pdf");
        }
    }
//    public static void main(String[] args) throws IOException, FileNotFoundException, DocumentException {
//        File [] img = new File[4];
//        img[0]=new File("D:\\FILMEY\\Downloads\\Image\\1.jpg");
//        img[1]=new File("D:\\FILMEY\\Downloads\\Image\\12.jpg");
//        img[2]=new File("D:\\FILMEY\\Downloads\\Image\\23.jpg");
//        img[3]=new File("D:\\FILMEY\\Downloads\\Image\\13.jpg");
//        String [] name = new String[img.length];
//        for(int i = 0;i<img.length;i++){
//            name[i]=img[i].getName();
//        }
//        String path =img[0].getPath();
//        System.out.println(path);
//        convertToIMG(name,"D:\\fa.pdf","D:\\FILMEY\\Downloads\\Image\\%s");
//    }
    
}
