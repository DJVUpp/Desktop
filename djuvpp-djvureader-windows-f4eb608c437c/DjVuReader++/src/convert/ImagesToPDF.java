package convert;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import static com.lizardtech.djview.frame.Frame.document;
import convert.ProgressBar;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import convert.ProgressBar;
import java.util.List;
import javafx.stage.FileChooser;
/**
 *Convert Image to Pdf
 * @author YOUNG PROGRAMMER
 */
public class ImagesToPDF {
    static String openimg = null;
    static String PDFFullPath = null;
    static String[] imagesname;
    int percentage = 0;
    public  void IMGToPDF(String RESOURCES, String result) throws DocumentException, FileNotFoundException, BadElementException, IOException{
        
        ProgressBar progrsbar=new ProgressBar();
      
        progrsbar.showProgress();
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
        // step 3
        document.open();
        // step 4
        Image img;
            img = Image.getInstance(RESOURCES);
            Image.getInstance(img);
            document.add(img);
   progrsbar.updatePercent(100);
    document.close();    
    }
        
        // step 5
        

    
    public static void convertToIMG(String[] RESOURCES, String result, String path, int s) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        // step 1
        int percentage = 0;
        ProgressBar progrsbar=new ProgressBar();
      
        progrsbar.showProgress();
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
        // step 3
        document.open();
        // step 4
        // Adding a series of images
        Image img;
        double Convertedimagescount;
        
        
        for (int i = 0; i < RESOURCES.length; i++) {
            img = Image.getInstance(String.format(path, RESOURCES[i]));
            Image.getInstance(img);
            document.add(img);
            
            Convertedimagescount =((double)i+1/(double)RESOURCES.length)*100;
            System.out.println(""+Convertedimagescount);
            percentage=(int)Convertedimagescount;
            
            
        }
        progrsbar.updatePercent(percentage);
        // step 5
        document.close();

    }

    public  void ConvertingImagesToPdf() {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select DJVU");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Djvu Files", "*.djvu"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
       
        try {
            openimg = fileChooser.getInitialDirectory() + "\\%s";

            
            imagesname = new String[selectedFiles.size()];
            boolean ch = true;
            if (fileChooser.getInitialFileName() != null) {
                for (int i = 0; i < selectedFiles.size(); i++) {
                    imagesname[i] = selectedFiles.get(i).getName();
                    PDFFullPath = "C:\\DjVu++Task\\ImagestoPDF\\" + imagesname[i].substring(0, imagesname[i].lastIndexOf(".")) + ".pdf";
                    String sn = imagesname[i].substring(imagesname[i].lastIndexOf("."));
                    sn = sn.toLowerCase();
                    System.err.println("ok");
                    if (sn.equals(".jpg") | sn.equals(".jpeg") | sn.equals(".tif") | sn.equals(".png")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "File is selected is not image\nPlease select image");
                        openimg = null;
                        ch = false;
                        break;
                    }
                }
                if (ch) {
                    FileChooser Savefiles = new FileChooser();
                    Savefiles.setTitle("Select DJVU");
                    Savefiles.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Djvu Files", "*.djvu"));
                    List<File> selectedFile = (List<File>) fileChooser.showSaveDialog(null);
       
                    FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save Pdf", FileDialog.SAVE);
                    SaveDjVu.setVisible(true);
                    String path = Savefiles.getInitialDirectory() + Savefiles.getInitialFileName();
                    if (path != null) {
                        convertToIMG(imagesname, path + ".pdf", openimg, 1);
                    }
                }
            }
        } catch (Exception t) {
        }
    }
    
}