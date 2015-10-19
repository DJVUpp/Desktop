/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvubean.RibbonMenu;

import Printer.PrintDjVu;
import Printer.PrinterBook;
import helpMenu.View_Help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 * this class include all ribbon actions
 *
 * @author niessuh
 */
public class RibbonActions {

    private static final DjvuRibbonComponents Button = new DjvuRibbonComponents();

    public RibbonActions() {

    }

    public ActionListener HelpButtonAction() {
        ActionListener Help = new ActionListener() {
            boolean flag = true;

            public void actionPerformed(ActionEvent e) {
View_Help H = new  View_Help();
                if (flag) {
                    H.setVisible(true);
                    flag = false;
                } else {
                    H.setVisible(false);
                    flag = true;
                }
               

            }
        };
        return Help;

    }

    public  ActionListener PrintAction() {
        ActionListener printer = new ActionListener() {

           
          public void actionPerformed(ActionEvent e) {
            try {
    			PrinterBook.PrintBook();
    		} catch (PrinterException e1) {
    			
    			e1.printStackTrace();
    		}
          }
        };
          return printer; 
    }
}
