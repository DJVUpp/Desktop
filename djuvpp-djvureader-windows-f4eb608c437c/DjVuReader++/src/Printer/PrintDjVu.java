package Printer;



import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintDjVu implements Printable {
   
    public int print(Graphics g,PageFormat pageFormat, int pageIndex)throws PrinterException {
           
        try {
            // print
            GetBook.GetBookImage();
        } catch (IOException ex) {
            Logger.getLogger(PrintDjVu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            if (pageIndex < GetBook.getPrintBookImage().length) {
                
                g.drawImage( GetBook.getPrintBookImage()[pageIndex], 0, 0, null);
                return PAGE_EXISTS;
            } else {
                return NO_SUCH_PAGE;   
            }  
        
    }
}
    
