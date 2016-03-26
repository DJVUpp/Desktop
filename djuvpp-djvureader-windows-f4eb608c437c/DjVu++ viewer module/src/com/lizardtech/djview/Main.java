package com.lizardtech.djview;

import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvu.DjVuOptions;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
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

	/**
	 * Creates new form Main
	 */
	public Main() {
//		DjVuOptions.err = System.err;

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
				openBookInNewTab(url, name);
			}

		}

	}

	public void openBookInNewTab(final String url, String name) {
		Frame f = new Frame(url);
		f.setVisible(true);
		f.setSize(700, 900);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
