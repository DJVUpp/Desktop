package PDF2DjVu;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
 
public class TIFF_To_PDF {
 
	public static void convertTif2PDF(String tifPath,String path)
	{
	      System.out.println("one");
	      String arg[]={tifPath};
	      System.out.println("one2");
	    if (arg.length < 1) {
	      System.out
	         .println("Usage: Tiff2Pdf file1.tif [file2.tif ... fileN.tif]");
	      System.exit(1);
	    }
	    String tiff;
	    String pdf;
	    System.out.println("two");
	    for (int i = 0; i < arg.length; i++) {
	       tiff = arg[i];
	       pdf = path+".pdf";
	       Document document = new Document(PageSize.LETTER, 0, 0, 0, 0);
	       try {
	          PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
	          int pages = 0;
	          document.open();
	          PdfContentByte cb = writer.getDirectContent();
	          RandomAccessFileOrArray ra = null;
	          int comps = 0;
	          try {
	             ra = new RandomAccessFileOrArray(tiff);
	             comps = TiffImage.getNumberOfPages(ra);
	          } 
	          catch (Throwable e) {
	             System.out.println("Exception in " + tiff + " "
	               + e.getMessage());
	             continue;
	          }
	          System.out.println("Processing: " + tiff);
	          for (int c = 0; c < comps; ++c) {
	             try {
	                Image img = TiffImage.getTiffImage(ra, c + 1);
	                if (img != null) {
	                  System.out.println("page " + (c + 1));
	                  System.out.println("img.getDpiX() : "+img.getDpiX());
	                  System.out.println("img.getDpiY() : "+img.getDpiY());
	                  img.scalePercent(6200f / img.getDpiX(), 6200f / img.getDpiY());
	                  //img.scalePercent(img.getDpiX(), img.getDpiY());
	                  //document.setPageSize(new Rectangle(img.getScaledWidth(), img.getScaledHeight()));
	                  img.setAbsolutePosition(0, 0);
	                  cb.addImage(img);
	                  document.newPage();
	                  ++pages;
	                }
	              } 
	              catch (Throwable e) {
	                System.out.println("Exception " + tiff + " page "
	                  + (c + 1) + " " + e.getMessage());
	              }
	          }
	          ra.close();
	          document.close();
	          } 
	          catch (Throwable e) {
	             e.printStackTrace();
	          }
	          System.out.println("done");
	       }
	    }
}