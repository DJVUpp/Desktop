/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ocr;

import Save.GetDir;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.fxml.FXML;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Mahmoud
 */
public class OCRImage {
   
  static   JTextArea textArea = new JTextArea("please wait Your Text appear here ");
  static   JScrollPane scrollPane = new JScrollPane(textArea);
  static   String line = "";
  static   String tessarctPath="";
  static   String path="";
   /**
   * 
   * @param imagePath
   * @param lan 
   */
   @FXML
      public static void OCRForImage(String imagePath) 
  { 
           
	  
          // this to get the direction of tesseract 
	    try {
			 path=GetDir.getDir();
		    }
	    catch (IOException e1) 
	    {
			e1.printStackTrace();
		}
	    catch (InterruptedException e1)
	    {
			e1.printStackTrace();
		}
      tessarctPath=path; 
	   
	   try 
       {
           String run=null;
           
             run=tessarctPath+"\\tesseract.exe "+imagePath+"  output";
          
          final Process p = Runtime.getRuntime().exec(run);
          p.waitFor();
          
          JFrame ff= new JFrame();
          ff.setLocationRelativeTo(null);
          ff.setSize(400,300);
         // ff.setResizable(false);
          ff.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
          ff.setVisible(true);
          ff.setLocationRelativeTo(null);
          
          
          File file = new File("output.txt");
          Reader fileReader = new InputStreamReader(new FileInputStream(file),"UTF8");
          BufferedReader reader = new BufferedReader(fileReader);
          
          StringBuffer stringBuffer = new StringBuffer();
          String line;
          while ((line = reader.readLine()) != null) 
          {
              stringBuffer.append(line);
              stringBuffer.append("\n");
          }
          
          fileReader.close();
          
          textArea.setBackground(Color.RED);
          textArea.setForeground(Color.BLACK);
          textArea.setText(stringBuffer.toString());
          textArea.setFont(new Font("arial", 0, 22));
        
          ff.add(scrollPane);
          
        
         
      
       }
	   catch (IOException e)
	    {
          e.printStackTrace();
      }
	   catch (InterruptedException e) 
       {
          e.printStackTrace();
       }

  }
      /**
     * 
     * @param s to define arabic or english of the picture words
     */
     public static void ocr_image()
     {
         //to browse on computer and choose the image to do ocr
     FileDialog imageocr = new FileDialog(new java.awt.Frame(), "Select Images ", FileDialog.LOAD);
     imageocr.setVisible(true);
     // take the path of the picture 
     String imagepath = imageocr.getDirectory()+ imageocr.getFile();
     //check the exetention of the file if image path is not empty then doing ocr using OCRForImage function
     String exeOfImage = null;
     if (imagepath != null) {
         
         try {
             
             
     exeOfImage = imagepath.substring(imagepath.lastIndexOf("."));
     exeOfImage = exeOfImage.toLowerCase();
     if (exeOfImage.equals(".jpg") | exeOfImage.equals(".jpeg") | exeOfImage.equals(".tif") | exeOfImage.equals(".png")|exeOfImage.equals(".gif")|exeOfImage.equals(".bmp")) {
     //doing ocr to the image
         OCRForImage(imagepath);       
                }
     else 
     {
         // if the doesn't select an image show message to upload pic
         JOptionPane.showMessageDialog(null, "plz upload photo");
     }  }
         catch (Exception e) {
             System.out.print("exit");
         }
        finally{
         System.out.println("image path : "+imagepath);
         }
     }          
}
}
