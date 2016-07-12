/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import src.RibbonMenu.DjvuMain;

/**
 *
 * @author fouad
 */
public class converts {

    public void djvutoimage() {
        String fullpath = null;
        String name;
        String exefile = "ddjvu.exe";
        String outputfile = "tiffff.tif";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select DJVU");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Djvu Files", "*.djvu"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        int flag = 0;
        try {
            fullpath = selectedFiles.get(0).getAbsolutePath();

        } catch (Exception e) {

            flag = 1;
        }

        if (flag == 0) {
            Convertings conv = new Convertings();
            //First Convert Djvu to tiff
            conv.StartConverting(exefile, fullpath, outputfile, "-format=tiff");

        }

    }

    public void pdftodjvu() {
        String fullpath = null;
        String name;
        String exefile = "pdf2djvu.exe";
        String outputfile = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        int flag = 0;
        try {
            fullpath = selectedFiles.get(0).getAbsolutePath();
            outputfile = selectedFiles.get(0).getName() + ".djvu";
        } catch (Exception e) {
            flag = 1;
        }

        if (flag == 0) {
            Convertings conv = new Convertings();
            conv.StartConverting(exefile, fullpath, outputfile, "-o");
        }
    }

    public void imagetopdf() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg", "*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        ImagesToPDF con = new ImagesToPDF();
        System.err.println(selectedFiles.get(0).getAbsolutePath());
        try {
            con.IMGToPDF(selectedFiles.get(0).getAbsolutePath(), "C:\\DjVu++Task\\Out.pdf");
        } catch (DocumentException ex) {
            Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void djvutopdf() {
        String fullpath = null;
        String name;
        String exefile = "ddjvu.exe";
        String outputfile = "tiffff.tif";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select DJVU");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Djvu Files", "*.djvu"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        int flag = 0;
        try {
            fullpath = selectedFiles.get(0).getAbsolutePath();
        } catch (Exception e) {
            flag = 1;
        }

        if (flag == 0) {
            Convertings conv = new Convertings();
            //First Convert Djvu to tiff
            conv.StartConverting(exefile, fullpath, outputfile, "-format=tiff");
            //Then Convert Tiff to pdf
            conv.convertTif2PDF("C:\\DjVu++Task\\tiffff.tif", "C:\\DjVu++Task\\tiffff.pdf");
        }
    }

    public void pdftoimage() {
        String fullpath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDf");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        int flag = 0;
        try {
            fullpath = selectedFiles.get(0).getAbsolutePath();
        } catch (Exception e) {
            flag = 1;
        }
        if (flag == 0) {
            Convertings conv = new Convertings();
            conv.PdfToIMG(fullpath, "C:\\DjVu++Task\\");
        }
    }

}
