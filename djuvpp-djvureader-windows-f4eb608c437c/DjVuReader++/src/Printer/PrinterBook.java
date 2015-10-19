/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

/**
 *
 * @author FATHI HOSSAM
 */
public class PrinterBook {

    public static void PrintBook() throws PrinterException {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                 
                 try {
                 UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());

                } catch (Exception cnf) {
                }
                PrinterJob job = PrinterJob.getPrinterJob();
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                PageFormat pf = job.getPageFormat(aset);
                job.setPrintable(new PrintDjVu(), pf);
                if (job.printDialog(aset)) {
                     try {  
                         job.print(aset);
                     } catch (PrinterException ex) {
                        
                     }
                }
                
    
            }
        });

    }
}
