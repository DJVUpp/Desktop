package src.RibbonMenu;

import CreatDjVu.NewDjvu;
import CreatDjVu.NewDjvuFromClib;
import static Ocr.OCRImage.ocr_image;
import Save.GetDir;
import annotation.annotation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import com.lizardtech.djvubean.DjVuBean;
import convert.*;

import convert.Convertings;
import convert.ImagesToPDF;
import convert.converts;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
        

public class DjvuComponents implements Initializable {
    

    public static String Command = "";
    static String openfile = null;
    static String PDFFullPath = null;
    static String[] imagesname;

    annotation anno = new annotation();
    converts convs=new converts();
    public static Font font = new Font("Dialog", Font.PLAIN, 12);

    @FXML
    ComboBox myCombobox, zoomComboBox, commentZoomComboBox, commentSelectComboBox, viewSelectComboBox, editZoomComboBox,
            viewZoomComboBox, convertSelectComboBox, convertZoomComboBox, fontFamillyComboBox, fontWidthComboBox, editSelectComboBox;
    @FXML
    Button file;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        myCombobox.getItems().addAll("Clipboard", "Copy", "Paste");
        // Set the cellFactory property
        myCombobox.setCellFactory(new ShapeCellFactory());
// Set the buttonCell property
        myCombobox.setButtonCell(new StringShapeCell());
        myCombobox.getSelectionModel().select(0);
        myCombobox.setVisible(true);

        //////////////////////////////////////////////////////////////
        // set zoomComboBox items
        zoomComboBox.getItems().addAll("25%", "50%", "75%", "100%", "125%", "200%", "400%");
        // Set the cellFactory property
        zoomComboBox.getSelectionModel().select(0);
        zoomComboBox.setVisible(true);
        zoomComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (zoomComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    anno.zoom25();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    anno.zoom50();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 2) {
                    anno.zoom75();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 3) {
                    anno.zoom100();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 4) {
                    anno.zoom125();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 5) {
                    anno.zoom200();
                } else if (zoomComboBox.getSelectionModel().getSelectedIndex() == 6) {
                    anno.zoom400();
                }

            }
        });

        /////////////////////////////////
        commentZoomComboBox.getItems().addAll("Zoom In", "Zoom Out", "Actual Size", "Fit Page", "Fit Width", "Fit Visible",
                "800%", "600%", "400%", "200%", "100%", "75%", "50%", "25%");
        // Set the cellFactory property
        commentZoomComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        commentZoomComboBox.setButtonCell(new StringShapeCell());
        commentZoomComboBox.getSelectionModel().select(0);
        commentZoomComboBox.setVisible(true);
        commentZoomComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 0) {

                    anno.zoomin();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    anno.zoomout();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 2) {
                    anno.zoom100();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 3) {
                    anno.fitpage();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 4) {
                    anno.fitwidth();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 5) {
                    anno.fitisible();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 6) {
                    anno.zoom800();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 7) {
                    anno.zoom600();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 8) {
                    anno.zoom400();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 9) {
                    anno.zoom200();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 10) {
                    anno.zoom100();
                }

                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 11) {
                    anno.zoom75();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 12) {
                    anno.zoom50();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 13) {
                    anno.zoom25();
                }
            }
        });

        ///////////////////////////////////////////////////////////////
        commentSelectComboBox.getItems().addAll("Select Text", "Annotation");

        // Set the cellFactory property
        commentSelectComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        commentSelectComboBox.setButtonCell(new StringShapeCell());
        commentSelectComboBox.getSelectionModel().select(-1);
        commentSelectComboBox.setVisible(true);
        commentSelectComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {

                if (commentSelectComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    anno.text();
                } else {
                    anno.zoomarea();

                }
            }

        });

        ////////////////////////////////////////////////////////
        viewSelectComboBox.getItems().addAll("Select Text", "Annotation");

        // Set the cellFactory property
        viewSelectComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        viewSelectComboBox.setButtonCell(new StringShapeCell());
        viewSelectComboBox.getSelectionModel().select(-1);
        viewSelectComboBox.setVisible(true);
        viewSelectComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (commentSelectComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    anno.text();
                } else {
                    anno.zoomarea();

                }
            }
        });

        ///////////////////////////////////
        viewZoomComboBox.getItems().addAll("Zoom In", "Zoom Out", "Actual Size", "Fit Page", "Fit Width", "Fit Visible",
                "800%", "600%", "400%", "200%", "100%", "75%", "50%", "25%");
        // Set the cellFactory property
        viewZoomComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        viewZoomComboBox.setButtonCell(new StringShapeCell());
        viewZoomComboBox.getSelectionModel().select(0);
        viewZoomComboBox.setVisible(true);

        viewZoomComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 0) {

                    anno.zoomin();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    anno.zoomout();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 2) {
                    anno.zoom100();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 3) {
                    anno.fitpage();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 4) {
                    anno.fitwidth();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 5) {
                    anno.fitisible();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 6) {
                    anno.zoom800();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 7) {
                    anno.zoom600();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 8) {
                    anno.zoom400();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 9) {
                    anno.zoom200();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 10) {
                    anno.zoom100();
                }

                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 11) {
                    anno.zoom75();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 12) {
                    anno.zoom50();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 13) {
                    anno.zoom25();
                }
            }
        });

        /////////////////////////////////////////////////////////////////////
        convertSelectComboBox.getItems().addAll("Select Text", "Annotation");

        // Set the cellFactory property
        convertSelectComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        convertSelectComboBox.setButtonCell(new StringShapeCell());
        convertSelectComboBox.getSelectionModel().select(-1);
        convertSelectComboBox.setVisible(true);
        convertSelectComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {

                if (commentSelectComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    anno.text();
                } else {
                    anno.zoomarea();

                }

            }
        });

        convertZoomComboBox.getItems().addAll("Zoom In", "Zoom Out", "Actual Size", "Fit Page", "Fit Width", "Fit Visible",
                "800%", "600%", "400%", "200%", "100%", "75%", "50%", "25%");
        // Set the cellFactory property
        convertZoomComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        convertZoomComboBox.setButtonCell(new StringShapeCell());
        convertZoomComboBox.getSelectionModel().select(0);
        convertZoomComboBox.setVisible(true);

        convertZoomComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 0) {

                    anno.zoomin();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    anno.zoomout();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 2) {
                    anno.zoom100();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 3) {
                    anno.fitpage();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 4) {
                    anno.fitwidth();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 5) {
                    anno.fitisible();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 6) {
                    anno.zoom800();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 7) {
                    anno.zoom600();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 8) {
                    anno.zoom400();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 9) {
                    anno.zoom200();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 10) {
                    anno.zoom100();
                }

                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 11) {
                    anno.zoom75();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 12) {
                    anno.zoom50();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 13) {
                    anno.zoom25();
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////
        editZoomComboBox.getItems().addAll("Zoom In", "Zoom Out", "Actual Size", "Fit Page", "Fit Width", "Fit Visible",
                "800%", "600%", "400%", "200%", "100%", "75%", "50%", "25%");
        // Set the cellFactory property
        editZoomComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        editZoomComboBox.setButtonCell(new StringShapeCell());
        editZoomComboBox.getSelectionModel().select(0);
        editZoomComboBox.setVisible(true);
        editZoomComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 0) {

                    anno.zoomin();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 1) {
                    anno.zoomout();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 2) {
                    anno.zoom100();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 3) {
                    anno.fitpage();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 4) {
                    anno.fitwidth();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 5) {
                    anno.fitisible();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 6) {
                    anno.zoom800();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 7) {
                    anno.zoom600();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 8) {
                    anno.zoom400();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 9) {
                    anno.zoom200();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 10) {
                    anno.zoom100();
                }

                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 11) {
                    anno.zoom75();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 12) {
                    anno.zoom50();
                }
                if (commentZoomComboBox.getSelectionModel().getSelectedIndex() == 13) {
                    anno.zoom25();
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////
        editSelectComboBox.getItems().addAll("Select Text", "Annotation");

        // Set the cellFactory property
        editSelectComboBox.setCellFactory(new ShapeCellFactory());
        // Set the buttonCell property
        editSelectComboBox.setButtonCell(new StringShapeCell());
        editSelectComboBox.getSelectionModel().select(-1);
        editSelectComboBox.setVisible(true);

        editSelectComboBox.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {

                if (commentSelectComboBox.getSelectionModel().getSelectedIndex() == 0) {
                    anno.text();
                } else {
                    anno.zoomarea();

                }
            }
        });

        /////////////////////////////////////////////////////////////////
        fontFamillyComboBox.getItems().addAll("Times New Roman (Headings CS)", "Agency FB","Aldhabi","Algerian","Andalus","Arabic Typesetting","Arial","Arial Black","Arial Narrow","Arial Rounded MT Bold");
        // Set the font style property
   
        fontFamillyComboBox.getSelectionModel().select(0);
        fontFamillyComboBox.setVisible(true);
        fontFamillyComboBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
              if (fontFamillyComboBox.getSelectionModel().getSelectedIndex()==0)
                {
                    if (fontFamillyComboBox.getSelectionModel().getSelectedIndex()==0)
            {
                font = new Font((String) fontFamillyComboBox.getStyle(), font.getStyle(), font.getSize());
                if (NewDjvu.textArea != null) {
                    //NewDjvu.textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
                }
            }
                }
            }
        });
        
        /////////////////////////////////////////////////////////////////

        fontWidthComboBox.getItems().addAll("8", "9", "10", "11", "12", ",13", "14", "15", "16");
        // Set the size property
        fontWidthComboBox.getSelectionModel().select(0);
        fontWidthComboBox.setVisible(true);
        fontWidthComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==0)
            {
                font = new Font(font.getFontName(), font.getSize(), 10);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==1)
            {
                font = new Font(font.getFontName(), font.getSize(), 12);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==2)
            {
                font = new Font(font.getFontName(), font.getSize(), 14);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==3)
            {
                font = new Font(font.getFontName(), font.getSize(), 16);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==4)
            {
                font = new Font(font.getFontName(), font.getSize(), 18);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==5)
            {
                font = new Font(font.getFontName(), font.getSize(), 20);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==6)
            {
                font = new Font(font.getFontName(), font.getSize(), 22);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==7)
            {
                font = new Font(font.getFontName(), font.getSize(), 24);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            if (fontWidthComboBox.getSelectionModel().getSelectedIndex()==8)
            {
                font = new Font(font.getFontName(), font.getSize(), 26);
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
            }
            
            
            
            
            }
        });

    }

    //Convert pdf to djvus
    @FXML
    public void convertpdfdjvu() {
        String fullpath = null;
        String name;
        String exefile = "pdf2djvu.exe";
        String outputfile = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        fullpath = selectedFiles.get(0).getAbsolutePath();
        outputfile = selectedFiles.get(0).getName() + ".djvu";

        Convertings conv = new Convertings();
        conv.StartConverting(exefile, fullpath, outputfile, "-o");

    }

    //Convert djvu to pdfs
    @FXML
    public void convertdjvu2pdf() {
        String fullpath = null;
        String name;
        String exefile = "ddjvu.exe";
        String outputfile = "tiffff.tif";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select DJVU");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Djvu Files", "*.djvu"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        fullpath = selectedFiles.get(0).getAbsolutePath();

        Convertings conv = new Convertings();
        //First Convert Djvu to tiff
        conv.StartConverting(exefile, fullpath, outputfile, "-format=tiff");
        //Then Convert Tiff to pdf
        conv.convertTif2PDF("C:\\DjVu++Task\\tiffff.tif", "C:\\DjVu++Task\\tiffff.pdf");

    }

    //Convert Images To Pdf
    @FXML
    public void convertImagesToPdf() throws DocumentException, BadElementException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg", "*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        ImagesToPDF con = new ImagesToPDF();
        System.err.println(selectedFiles.get(0).getAbsolutePath());
        con.IMGToPDF(selectedFiles.get(0).getAbsolutePath(), "C:\\DjVu++Task\\Out.pdf");
    }

    @FXML
    public void converImagesToDjvu() throws DocumentException, IOException {
        Convertings con = new Convertings();
        con.convertimagestodjvu();
    }

    @FXML
    public void convertPdfToImg() {
        String fullpath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDf");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Pdf Files", "*.pdf"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        fullpath = selectedFiles.get(0).getAbsolutePath();
        Convertings conv = new Convertings();
        conv.PdfToIMG(fullpath, "C:\\DjVu++Task\\");
    }

    @FXML
    public void convertDjvuToImage() {
        String fullpath = null;
        String name;
        String exefile = "ddjvu.exe";
        String outputfile = "tiffff.tif";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select DJVU");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Djvu Files", "*.djvu"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        fullpath = selectedFiles.get(0).getAbsolutePath();

        Convertings conv = new Convertings();
        //First Convert Djvu to tiff
        conv.StartConverting(exefile, fullpath, outputfile, "-format=tiff");

    }

    @FXML
    public void handClick() {

        anno.hand();

    }

    @FXML
    public void higlightClick() {

        anno.highlights();

    }

    @FXML
    public void underlineClick() {

        anno.underline();

    }

    @FXML
    public void strickClick() {

        anno.strick();

    }

    @FXML
    public void insertClick() {

        anno.insert();

    }

    @FXML
    public void replaceClick() {

        anno.replace();

    }

    @FXML
    public void rectangleClick() {

        anno.rectangle();

    }

    @FXML
    public void ovalClick() {

        anno.oval();

    }

    @FXML
    public void roundrectClick() {

        anno.roundrect();

    }

    @FXML
    public void lineClick() {

        anno.line();

    }

    @FXML
    public void textClick() {

        anno.text();

    }

    @FXML
    public void zoomClick() {

        anno.zoomarea();

    }

    @FXML
    public void ActualClick() {

        anno.zoom100();

    }

    @FXML
    public void fitpageClick() {

        anno.fitpage();

    }

    @FXML
    public void fitwidthClick() {

        anno.fitwidth();

    }

    @FXML
    public void fitvisiblClick() {

        anno.fitwidth();

    }

    @FXML
    public void rotateliftClick() {

        //    anno.rotaelift();
    }

    @FXML
    public void rotateightClick() {

        //  anno.rotaeright();
    }

    @FXML
    public void zoomoutClick() {

        anno.zoomout();
    }

    @FXML
    public void zoominClick() {

        anno.zoomin();
    }

    @FXML
    public void snapClick() {

        anno.snapClick();
    }
    
    
     @FXML
    public void DelRecentFiles() throws IOException {
RecentFile.DeleteRecent.DelRecentFiles();
    
    }
    
       
                        
    
    @FXML
    public void createfromclipboard() {

              CreatDjVu.NewDjvuFromClib.createFrame();
              
    }
            
    @FXML
    public void createfromblank() {

       
              CreatDjVu.NewDjvu.createFrame();
              
    }
    
    
     @FXML
    public void pdftodjvuclick() {
         convs.pdftodjvu();
    }
    
     @FXML
    public void pdftomageclick() {
          convs.pdftoimage();
    }
    
    @FXML
    public void djvutopdfclick() {
        convs.djvutopdf();
         
    }
    
    @FXML
    public void djvutoimageclick() {
        convs.djvutoimage();
         
    }
     @FXML
    public void imagetodfclick() {
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpeg","*.jpg","*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        ImagesToPDF con=new ImagesToPDF();
        System.err.println(selectedFiles.get(0).getAbsolutePath());
                try {
                    con.IMGToPDF(selectedFiles.get(0).getAbsolutePath(),"C:\\DjVu++Task\\Out.pdf");
                } catch (DocumentException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                }
         
    }
     @FXML
    public void imagetodjvuclick() {
        
        
         Convertings con=new Convertings();
                try {
                    con.convertimagestodjvu();
                } catch (DocumentException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                }
         
    }
    /////////////////////////////////////////////////////
    //Create Note
    
    @FXML
    public void noteCreatClick() {
            anno.insert();
    }
    
    /////////////////////////////////////////////////////
    //set Bold Property
    @FXML
    public void boldClick() {
            
              font = new Font(font.getFontName(), Font.BOLD, font.getSize());
              if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }
                    
    }
    //set Italic Property
    @FXML
    public void italicClick() {
        font = new Font(font.getFontName(), Font.ITALIC, font.getSize());
                if (NewDjvu.textArea != null) {
                    NewDjvu.textArea.setFont(font);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    NewDjvuFromClib.textAreaClip.setFont(font);
                }   
    }
    //Set underline property
    @FXML
    public void underlineTextClick() {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setUnderline(attributeSet, true);
        NewDjvu.textArea.getStyledDocument().setCharacterAttributes(0, NewDjvu.textArea.getText().length(),
        attributeSet, false);
        
    }
    //set align left property
    @FXML
    public void alignLeftClick() {
        
                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet left = new SimpleAttributeSet();
                    StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
                    doc.setParagraphAttributes(0, doc.getLength(), left, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet left = new SimpleAttributeSet();
                    StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
                    doc.setParagraphAttributes(0, doc.getLength(), left, false);
                }
        
    }
    //set align center property
    @FXML
    public void alignCenterClick() {
        
                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                }
        
    }
    //set align right property
    @FXML
    public void alignRightClick() {
        
                if (NewDjvu.textArea != null) {

                    StyledDocument doc = NewDjvu.textArea.getStyledDocument();
                    SimpleAttributeSet right = new SimpleAttributeSet();
                    StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
                    doc.setParagraphAttributes(0, doc.getLength(), right, false);
                }
                if (NewDjvuFromClib.textAreaClip != null) {
                    StyledDocument doc = NewDjvuFromClib.textAreaClip.getStyledDocument();
                    SimpleAttributeSet right = new SimpleAttributeSet();
                    StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
                    doc.setParagraphAttributes(0, doc.getLength(), right, false);
                }
        
    }
    //set color property
    @FXML
    public void colorClick() {
        
            /*System.out.println("colorPicker.getValue()");
            String text = NewDjvu.textArea.getSelectedText();
            int cursorPosition = NewDjvu.textArea.getCaretPosition();

            StyleContext context = new StyleContext();
            Style style;

            NewDjvu.textArea.replaceSelection("");

            style = context.addStyle("mystyle", null);
            style.addAttribute(StyleConstants.FontSize, new Integer(16));
            NewDjvu.textArea.getStyledDocument().insertString(cursorPosition - text.length(), text, style);*/
                
            if (NewDjvu.textArea != null) {
                //NewDjvu.textArea.setFill(colorPicker.getValue(););
                NewDjvu.textArea.setForeground(Color.yellow);
            }
            if (NewDjvuFromClib.textAreaClip != null) {
               
                NewDjvuFromClib.textAreaClip.setForeground(Color.BLUE);
            }
    }
    
      @FXML
    public void ocrEnglishClick() throws IOException {
         ocr_image();
       
    }
   
    public void ocrArabicClick(){
       
            //DjvuComponents.class.getResourceAsStream("/packageB/yourfile.ext");
            System.out.println("zzzzzzz");
            String run=null;
               //  im= ocr_image();
                //String dd=im.substring(0, 2)+"\\"+im.substring(2, im.length());
              //   writ2file(im);
                // ConsoleApplication3.exe
              run="E:\\Study 2015-2016\\Graduation Project\\DjvuFinal1\\DjvuFinal\\src\\src\\Ocr\\runfiles"+"\\finalOCRmat.exe";
                final Process p;
                 try {
                     p = Runtime.getRuntime().exec(run ,null, new File("E:\\Study 2015-2016\\Graduation Project\\DjvuFinal1\\DjvuFinal\\src\\src\\Ocr\\runfiles\\"));
                   
                         p.waitFor();
                     } catch (InterruptedException ex) {
                         Logger.getLogger(DjvuComponents.class.getName()).log(Level.SEVERE, null, ex);
                     }
                  catch (IOException ex) {
                     Logger.getLogger(DjvuComponents.class.getName()).log(Level.SEVERE, null, ex);
                 }
               // ConsoleApplication3.exe
              run="E:\\Study 2015-2016\\Graduation Project\\DjvuFinal1\\DjvuFinal\\src\\src\\Ocr\\runfiles"+"\\ConsoleApplication3.exe";
                System.out.println("the second ");
            /*  final Process f;
                 try {
                     f = Runtime.getRuntime().exec(run);
                   //  BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    
                         f.waitFor();
                     } catch (InterruptedException ex) {
                         Logger.getLogger(DjvuComponents.class.getName()).log(Level.SEVERE, null, ex);
                     }
                  catch (IOException ex) {
                     Logger.getLogger(DjvuComponents.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
                 try {
                Runtime rt = Runtime.getRuntime();
                //Process pr = rt.exec("cmd /c dir");
                Process pr = rt.exec(run);
 
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                String line=null;
 
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
 
                int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
 
            } catch(Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
            System.out.println("xxxxxxx");
    }
 
  
  //499326793

    
    
    
    
}
