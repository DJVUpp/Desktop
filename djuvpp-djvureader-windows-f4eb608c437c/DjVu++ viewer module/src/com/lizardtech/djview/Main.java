package com.lizardtech.djview;

import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvubean.DjVuBean;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class is a Main class to test the module functionality
 *
 * @author Osama
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 63639160383693133L;
	public static final HashMap<String, DjVuBean> beanMap = new HashMap<String, DjVuBean>(); // TODO:
																								// remove
																								// static
	private ArrayList<String> bookList = new ArrayList<String>();
	private Map<String, String> name_url = new ConcurrentHashMap<String, String>();
	private Map<String, String> url_name = new ConcurrentHashMap<String, String>();

	/**
	 * Creates new form Main
	 */
	public Main() {
		// initComponents();
		try {
			opendialog();
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new Main();
		});
	}

	private void opendialog() throws IOException {

		FileDialog fd = new FileDialog(this, "open djvu file", FileDialog.LOAD);
		fd.setMultipleMode(true);
		fd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/djvuNewIcon.png")));
		fd.setVisible(true);
		if (fd.getDirectory() != null) {
			File files[] = fd.getFiles();
			for (File file : files) {

				String url;
				url = "" + file.toURI().toURL();
				url = url.substring(5, url.length());
				String name = file.getName();
				name_url.put(name, url);
				url_name.put(url, name);
				bookList.add(url);
				openBookInNewTab(url, name);

			}

		}

	}

	public void openBookInNewTab(final String url, String name) {

		// SwingUtilities.invokeLater(new Runnable() {
		// @Override
		// public void run() {
		name = url_name.get(url);
		name_url.put(name, url.substring(1));
		url_name.put(url.substring(1), name);
		bookList.add(url.substring(1));

		Frame f = new Frame(url);
		f.setVisible(true);
		f.setSize(700, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Container pane = f.getContentPane();
		// tabbedPane.add(name, pane);
		// tabbedPane.setSelectedComponent(pane);
		// String tabName =
		// tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		// if (!tabName.equals("GetStart")) {

	}

}
