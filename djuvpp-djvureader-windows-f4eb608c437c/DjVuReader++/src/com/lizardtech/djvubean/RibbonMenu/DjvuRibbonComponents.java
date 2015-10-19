/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lizardtech.djvubean.RibbonMenu;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntrySecondary;
import org.pushingpixels.flamingo.internal.ui.ribbon.appmenu.JRibbonApplicationMenuPopupPanel;

/**
 *
 * @author niessuh
 */
public class DjvuRibbonComponents {

    public JCommandButton Hand;
    public JCommandButton HighLight;
    public JCommandButton ConvertBandHand;
    public JCommandButton Switch;
    public JCommandButton Select;
    public JCommandButton SnapShot;
    public JCommandButton BookMark;
    public JCommandButton Clipbord;
    public JCommandButton Blank;
    public JCommandButton FromClipbord;
    public JCommandButton Asize;
    public JCommandButton Fitpage;
    public JCommandButton FitWidth;
    public JCommandButton FitVisable;
    public JCommandMenuButton RotateLeft;
    public JCommandMenuButton RotateRight;
    public static JCommandButton Continous;
    public static JCommandButton Single;
    public static JCommandButton facing;
    public JCommandButton EditText;
    public JCommandButton EditText1;
    public static JCommandButton NextView;
    public static JCommandButton prevview;
    public JCommandButton TyperWriter;
    public JCommandButton Note;
    public JCommandButton ConvertBandZoom;
    public JCommandButton Strikeout;
    public JCommandButton UnderLine;
    public JCommandButton ChangToolbarmod;
    public JCommandButton AddImg;
    public JCommandButton FClip;
    public JCommandButton FFile;
    public JCommandButton AddShaps;
    public JCommandButton FBlank;
    public JCommandButton Delete;
    public JCommandButton ExtractallImages;
    public JCommandButton Dsign;
    public JCommandMenuButton OCRArabic;
    public JCommandButton Ocr2;
    public JCommandMenuButton OCREnglish;
    public JCommandMenuButton OCRArabic2;
    public JCommandMenuButton OCREnglish2;
    public JCommandMenuButton Pdf;
    public JCommandMenuButton Djvu;
    public JCommandMenuButton createnote;
    public JCommandButton shownote;
    public JCommandButton ZoomArea;
    public JCommandButton CSelectText;
    public JCommandButton CSelectAnn;
    public JCommandButton AddText;
    public JCommandButton JoinSplit;
    public JCommandButton Clipboard;
    public JCommandButton Ocr;
    public JCommandButton DJvuConvert;
    public JCommandButton ConvertBandSelect;
    public JCommandButton EditObject;
    public JCommandButton EditObject1;
    public JCommandButton Rotate;
    public JCommandButton RotatePages2;
    public JCommandButton insert;
    public JCommandButton PSelect;
    public JCommandButton Zoom;
    public JCommandButton open;
    public JCommandButton save;
    public JCommandButton Email;
    public JCommandButton undo;
    public JCommandButton Redo;
    public JCommandButton search;
    public JCommandButton TWriter;
    public JCommandButton Note2;
    public JCommandButton TextBox;
    public JCommandButton Callout;
    public JCommandButton TBBlank;
    public JCommandButton ZoomIn;
    public JCommandButton Zoomout;
    public JCommandButton Rect;
    public JCommandButton Oval;
    public JCommandButton Polygon;
    public JCommandButton Cloud;
    public JCommandButton arrow;
    public JCommandButton pencil;
    public JCommandButton Line;
    public JCommandButton Eraser;
    public JCommandButton AreaHighLight;
    public JTextField AreaSearch;
    public JCommandButton search2;
    public static JComboBox ZoomB;
    public JCommandMenuButton Clib_Selectall;
    public JCommandMenuButton Clib_Past;
    public JCommandMenuButton Clib_Copy;
    public JCommandMenuButton Clib_Cut;
    public JRibbonComponent RC;
    public JCommandButtonStrip strip;
    public JCommandButton help;
    public JCommandButton AboutUS;
    public JCommandButton Contact;
    public JCommandButton Appmenublank;
    public JCommandMenuButton hun;
    public JCommandMenuButton franch;
    public JCommandMenuButton franch2;
    public JCommandMenuButton Z100;
    public JCommandMenuButton Z25;
    public JCommandMenuButton Z50;
    public JCommandMenuButton Z75;
    public JCommandMenuButton Z200;
    public JCommandMenuButton Z600;
    public JCommandMenuButton Z400;
    public JCommandMenuButton Z800;
    public JCommandMenuButton FP;
    public JCommandMenuButton FW;
    public JCommandMenuButton AS;
    public JCommandMenuButton ZIN;
    public JCommandMenuButton ZOUT;
    public JCommandMenuButton SelectText;
    public JCommandMenuButton ZoomArea2;
    public JCommandButton ImagesToOcr;
    public JCommandButton ImagesToOcr2;
    public JCommandButton DJvuConverts;
    public JCommandButton PdfConverts;
    public JCommandButton ImageConverts;
    public static JComboBox fontCombo;
    public static JComboBox sizeCombo;
    public static JCommandButton indentLeftButton;
    public static JCommandButton indentRightButton;
    public static JCommandToggleButton styleBoldButton;
    public static JCommandToggleButton styleItalicButton;
    public static JCommandToggleButton styleUnderlineButton;
    public static JCommandToggleButton styleStrikeThroughButton;
    public static JCommandToggleButton alignLeftButton;
    public static JCommandToggleButton alignCenterButton;
    public static JCommandToggleButton alignRightButton;
    public static JCommandToggleButton alignFillButton;

//...................................................
    public RibbonApplicationMenu Appmenu;
    public RibbonApplicationMenuEntryPrimary Create;
    public RibbonApplicationMenuEntryPrimary AboutDjvu;
    public RibbonApplicationMenuEntryPrimary OPen;
    public RibbonApplicationMenuEntryPrimary Save;
    public RibbonApplicationMenuEntryPrimary SaveAs;
    public RibbonApplicationMenuEntryPrimary print;
    public RibbonApplicationMenuEntryPrimary Close;
    public RibbonApplicationMenuEntrySecondary Description;
    public RibbonApplicationMenuEntrySecondary AppBlank;
    public RibbonApplicationMenuEntrySecondary F_clip;
    public RibbonApplicationMenuEntrySecondary new_Open;
    public RibbonApplicationMenuEntrySecondary Re_file;
    public RibbonApplicationMenuEntrySecondary AppPdf;
    public RibbonApplicationMenuEntrySecondary djvu;
    public RibbonApplicationMenuEntrySecondary Word;
    public JRibbonApplicationMenuPopupPanel Panel;
    public RibbonApplicationMenuEntryFooter Exit;
    public RibbonApplicationMenuEntryFooter options;
    public JCommandButton ViewHand;
    public JCommandButton ViewZoom;
    public JCommandButton ViewSelect;
    public JCommandButton EditHand;
    public JCommandButton EditZoom;
    public JCommandButton EditSelect;
    public JCommandButton CommentHand;
    public JCommandButton CommentZoom;
    public JCommandButton CommentSelect;
    public JCommandMenuButton Z125;
    public JCommandMenuButton FV;
    public JCommandMenuButton Pdf_to_Djvu;
    public JCommandMenuButton Pdf_to_images;
    public JCommandMenuButton Image_to_Djvu;
    public JCommandMenuButton Image_to_Pdf;
    public JCommandMenuButton Djvu_to_pdf;
    public JCommandMenuButton Djvu_toImage;
    public JCommandMenuButton RotateLeft2;
    public JCommandMenuButton Rotateright2;

    public DjvuRibbonComponents() {

    }
}
