/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotation;

import static com.lizardtech.djvubean.annotations.markup;
import static com.lizardtech.djvubean.annotations.drawing;
import static com.lizardtech.djvubean.annotations.popupnote;

/**
 *
 * @author fouad
 */
public class annotation {

    public static void highlights() {
        markup = 1;
        drawing = 0;
    }

    public static void underline() {
        markup = 2;
        drawing = 0;
    }

    public static void strick() {
        markup = 3;
        drawing = 0;
    }

    public static void replace() {
        markup = 4;
        drawing = 0;
    }

    public static void insert() {
        markup = 5;
        drawing = 0;
    }

    public static void hand() {
        markup = 6;
        drawing = 0;
    }

    public static void rectangle() {
        drawing = 1;
        markup = 0;
        popupnote = 0;
    }

    public static void oval() {
        drawing = 2;
        markup = 0;
        popupnote = 0;
    }

    public static void roundrect() {
        drawing = 3;
        markup = 0;
        popupnote = 0;
    }

    public static void line() {
        drawing = 4;
        markup = 0;
        popupnote = 0;
    }

    public static void text() {
        drawing = 0;
        markup = 7;
        popupnote = 0;
    }

    public static void zoomarea() {
        drawing = 0;
        markup = 8;
        popupnote = 0;
    }

    public static void zoom100() {
        drawing = 0;
        markup = 9;
        popupnote = 0;
    }

    public static void fitpage() {
        drawing = 0;
        markup = 10;
        popupnote = 0;
    }

    public static void fitwidth() {
        drawing = 0;
        markup = 11;
        popupnote = 0;
    }

    public static void fitisible() {
        drawing = 0;
        markup = 12;
        popupnote = 0;
    }

    public static void zoomin() {
        drawing = 0;
        markup = 13;
        popupnote = 0;
    }

    public static void zoomout() {
        drawing = 0;
        markup = 14;
        popupnote = 0;
    }

    public static void zoom25() {
        drawing = 0;
        markup = 15;
        popupnote = 0;
    }

    public static void zoom50() {
        drawing = 0;
        markup = 16;
        popupnote = 0;
    }

    public static void zoom75() {
        drawing = 0;
        markup = 17;
        popupnote = 0;
    }

    public static void zoom125() {
        drawing = 0;
        markup = 18;
        popupnote = 0;
    }

    public static void zoom200() {
        drawing = 0;
        markup = 19;
        popupnote = 0;
    }

    public static void zoom400() {
        drawing = 0;
        markup = 20;
        popupnote = 0;
    }
    
    public static void zoom600() {
        drawing = 0;
        markup = 21;
        popupnote = 0;
    }
    public static void zoom800() {
        drawing = 0;
        markup = 22;
        popupnote = 0;
    }
    public static void snapClick() {
        drawing = 0;
        markup = 23;
        popupnote = 0;
    }
    

}
