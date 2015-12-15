/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPage;

import PDF2DjVu.PDF2DjVu;
import com.lizardtech.djview.frame.DjvuStart;
import static com.lizardtech.djview.frame.DjvuStart.bookList;
import static com.lizardtech.djview.frame.DjvuStart.curropen;
import static com.lizardtech.djview.frame.DjvuStart.name_url;
import static com.lizardtech.djview.frame.DjvuStart.openBookInNewTab;
import static com.lizardtech.djview.frame.DjvuStart.tabbedPane;
import static com.lizardtech.djview.frame.DjvuStart.url_name;
import java.awt.FileDialog;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author salah Ben Atwa
 */
public class PDF2DjVuPanel extends javax.swing.JPanel {

    /**
     * Creates new form PDF2DjVuPanel
     */
    public PDF2DjVuPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(204, 51, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PDF 48.png"))); // NOI18N
        jButton1.setToolTipText("Select PDF File");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 64, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/toDJVU 48.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 58, 50));

        bar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pbar.gif"))); // NOI18N
        add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, 20));
        bar.setVisible(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Select Your PDF");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Convert PDF into Djvu");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ttask.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 140));
    }// </editor-fold>//GEN-END:initComponents
       String fullPDF = null;
    File le = null;

       // check name of book is arabic or english 
    public static boolean isProbablyArabic(String s) {
        for (int i = 0; i < s.length();) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0) {
                return true;
            }
            i += Character.charCount(c);
        }
        return false;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
               bar.setVisible(true); 
          try
        {
            FileDialog PdfFile=new FileDialog(new java.awt.Frame(),"select PDF",FileDialog.LOAD);
            PdfFile.setVisible(true);
            fullPDF = PdfFile.getDirectory()+PdfFile.getFile();
           ////////////////////
            if (fullPDF != null) {
                String d = fullPDF.substring(fullPDF.lastIndexOf("."));
                d = d.toLowerCase();
                if (d.equals(".pdf")) {
                 ///////////////////   
                    if (!fullPDF.contains(" ")) {
                        FileDialog SaveDjVu = new FileDialog(new java.awt.Frame(), "Save DjVu", FileDialog.SAVE);
                        SaveDjVu.setVisible(true);
                        String path = SaveDjVu.getDirectory() + SaveDjVu.getFile();
                     /////////////////
                        if (path != null && !path.contains(" ") && SaveDjVu.getFile() != null) {
                            //PDF2DjVu.PDF2DjVu(fullPDF,"C:\\DjVu++Task\\PDFtoDjvu\\"+fullPDF.substring(fullPDF.lastIndexOf("\\"),fullPDF.lastIndexOf(".")),1);
                          
                            PDF2DjVu.PDF2DjVu(fullPDF, path, 1);
                            //String l="C:\\DjVu++Task\\PDFtoDjvu\\"+fullPDF.substring(fullPDF.lastIndexOf("\\"),fullPDF.lastIndexOf("."))+".djvu";
                           
                            File le = new File(path + ".djvu");
                            String url;
                            url = "" + le.toURI();
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+url);
                            url = url.substring(5, url.length());
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+url);
                            String name = le.getName();
                    
                            if (!curropen.contains(name)) {
                                name_url.put(name, url);
                                url_name.put(url, name);
                                curropen.add(name);
                                bookList.add(url);
                                DjvuStart.openBookInNewTab(url, name);

                            } else if (curropen.contains(name)) {
                                tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
                            } else {}
                   
                        } else {
                            fullPDF = null;
                            JOptionPane.showMessageDialog(null, "Place to save  contain space or don't put name  ");
                        }
                    /////////////////    
                    
                }else {
                    bar.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Path to save  contain space  ");
                    fullPDF = null;
                }
           ////////////////////         
                }
                
                else{
                     bar.setVisible(false);
                      JOptionPane.showMessageDialog(null, "File selected not pdf  ");
                      fullPDF = null;
               }
            
            }}catch(Exception ex){}finally{
                 bar.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}