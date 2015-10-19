package helpMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class View_Help  extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l=new JLabel(new ImageIcon("src/image/Help(2).png")); 
	JLabel l2;
	JLabel welcome1=new JLabel("   Reprorter  Can Create PDF  File,Can Draw Image,Can Make Report ,Can Make Cv");
	JLabel welcome2=new JLabel("   Specific Developer Team In Java Application.");
	JLabel welcome3=new JLabel("   Specific Graphics Team For Design.");
	JLabel welcome4=new JLabel("   Specific works In Computer .");
	JLabel welcome5=new JLabel("   Specific Web Designner Team .");
	JLabel welcome6=new JLabel("   For Call For Our Company 01091687523 .");
	JButton b=new JButton("      OK      ");
	
	// Create table column names
	private String[] columnNames = 
	 {"Questions","Answers"};
	
	 // Create table data
	 private Object[][] data = { 
			 {"",""},
	 {"what's DjVu","DjVu Reader For Document With Compresstion Size"},
	 {"",""},
	 {"",""},
	 {"what's DjVu","You Can Convert PDF To DjVu and vice versa"},
	 {"",""},
	 {"",""},
	 {"How Make PDF","in start Menu click on DjVu TO PDF"},
	 {"",""},
	 {"",""},
	 {"How Convert TO Djvu ","in start Menu You Can Select PDF and Select Your Path"},
	 {"",""},
	 {"",""},
	 {"How Do OCR","in RobbinMenu You can select OCR For 2 Language and the select Areaon DjVu Document"},
	 {"",""},
	 {"",""},
	 {" How To OCR ARABIC","After Opening Document You Can Select And Then Draw Rectangle Over Document"},
	 {"",""},
	 {"",""},
	 {"How Do COR English","You Can Select English From RobbinMenu"},
	 {"",""},
	 {"",""},
	 {"DjVu size","advantage DjVu small zsize Document"},
	 {"",""},
	 {"",""},
	 {"Howwhat's Support OCR Language","TWO Language Arabic And English"},
	 {"",""},
	 {"",""},
	 {"How Zoom ","Select Open And Select Zoom Out Or Zoom In."},
	 {"",""},
	 {"",""},
	 {"How Fit Page","Open DjVu Document and then Select Fit Page From RobbinMenu"},
	 {"",""},
	 {"",""},
	 {"How To Open Multiple","Select More Than One Document."},
	 {"",""},
	 {"",""},
	 {"How do I find Word","in The North You Can Click On Find Button With Small Camera Image and the Enter Your Word."},
	 {"",""},
	 {"",""},
	 {"How I Do OCR On Image","in start Menu You Can CHOOSE Ocr Rectangle"},
	 {"",""},
	 {"",""},
	 
	 
	 }; 
	
	 // Create a table
	 private JTable jTable1 = new JTable(data, columnNames); 
	
	
	
	public View_Help() {
		l2=new JLabel("Reporter Answer Question For "+System.getProperty("os.name"));
		jTable1.setEnabled(false);
	    jTable1.setBorder(new BevelBorder(1));
	    jTable1.setFont(new Font("SansSerif",Font.CENTER_BASELINE,13));
	    jTable1.setForeground(Color.DARK_GRAY);
	    jTable1.setGridColor(Color.LIGHT_GRAY);
	    jTable1.setShowHorizontalLines(false);
	    jTable1.setShowVerticalLines(false);
	    
	
		setSize(550,500);
		setVisible(true);
		setTitle("View Help");
		//setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		Font font=new Font(Font.SANS_SERIF,Font.BOLD,25);
		l2.setFont(font);
		l2.setForeground(Color.LIGHT_GRAY);
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		welcome1.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		welcome2.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		welcome3.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		welcome4.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		welcome5.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		welcome6.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,12));
		
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(l);
		p2.add(l2);
		
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(b);
		b.setBorder(new BevelBorder(0));
		
		
		add(p2,BorderLayout.NORTH);
		add(new JScrollPane(jTable1),BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
				
			}
		});
	}
	

}
