/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OCR;

import com.lizardtech.djview.frame.GetDir;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author FATHI HOSSAM
 */
public class OcrStartpage {
    
    public static void OCRimage(String path){
        String pathdir="";
        JTextArea textArea = new JTextArea("please wait Your Text appear here ");
        JScrollPane scrollPane = new JScrollPane(textArea);
            try {
	           pathdir=GetDir.getDir();
		    }
	    catch (IOException e1) 
	    {
			e1.printStackTrace();
		} catch (InterruptedException e1)
	    {
			e1.printStackTrace();
		}
        
        try {
            final Process p = Runtime.getRuntime().exec(pathdir+"\\tesseract.exe "+path.trim()+" output");
            p.waitFor();
            JFrame f= new JFrame();
            f.setLocation(10,10);
            f.setSize(400,300);
            f.setVisible(true);
            f.setTitle("OCR English");
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            File file = new File("output.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) 
            {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            // JOptionPane.showMessageDialog(null, stringBuffer.toString());
            textArea.setBackground(Color.RED);
            textArea.setForeground(Color.BLACK);
            textArea.setText(stringBuffer.toString());
            textArea.setForeground(Color.WHITE);
            f.add(scrollPane);
    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
    }
    
}
