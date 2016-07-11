/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lizardtech.djvubean;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author eng hoda
 */
public class Text {
    
     static   JTextArea textArea = new JTextArea("please wait Your Text appear here ");
     static   JScrollPane scrollPane = new JScrollPane(textArea);
     public  String re(String s)
    {  
        String output="";
        for (int i=s.length()-1;i>=0;i--)
        {
            char ch=s.charAt(i);
            output+=ch;
            
        } 
    return output;
    }
     
     
     public void write(String txt){
             File myFile =new File("C:\\");
    
    FileOutputStream fop;
    String v1=txt;
        try {
            fop = new FileOutputStream(myFile);
           
           
            myFile.createNewFile();
            byte[]s1=v1.getBytes();
            fop.write(s1);
            
            
            
        } catch (FileNotFoundException e)
        {
        
        }
        catch (IOException e) 
        {
        
        }
     }
     
     
     public String read() throws FileNotFoundException, IOException{
        FileReader fr=new FileReader("C:\\");
        BufferedReader br=new BufferedReader(fr);
        String s;
        List<String> tmp = new ArrayList<String>();

        
     do{
        s = br.readLine();  
        tmp.add(s);}
           while(s!=null);
        String result="";
        for(int i=tmp.size()-2;i>=0;i--) {
        
        result+=tmp.get(i)+"\n";
        }
        return result;
             }
     
     
     public void showtext(String text)
     {
         
          JFrame frame= new JFrame("Select Text");
          frame.setLocationRelativeTo(null);
          frame.setSize(400,300);
          
           URL iconURL = getClass().getResource("/images/DjvuIcon.png");
           ImageIcon icon = new ImageIcon(iconURL);
           frame.setIconImage(icon.getImage());
       
          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          frame.setVisible(true);
          frame.setLocationRelativeTo(null);
          
          textArea.setBackground(Color.RED);
          textArea.setForeground(Color.BLACK);
          textArea.setText(text);
          textArea.setFont(new Font("arial", 0, 16));
        
          frame.add(scrollPane);
          frame.setLocation(400, 600);
     }
    
}
