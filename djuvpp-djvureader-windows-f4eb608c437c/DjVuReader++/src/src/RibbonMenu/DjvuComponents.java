package src.RibbonMenu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class DjvuComponents implements Initializable{
   // public Button hand;
    @FXML
   public ComboBox myCombobox;

   public Button file;
    @FXML
    Button hand;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

     /*  myCombobox.getItems().addAll("Clipboard", "Copy", "Paste");
        // Set the cellFactory property
        myCombobox.setCellFactory(new ShapeCellFactory());
// Set the buttonCell property
        myCombobox.setButtonCell(new StringShapeCell());
        myCombobox.getSelectionModel().select( 0 );
        myCombobox.setVisible( true );*/
        //set hand button disable
       // hand.setDisable(true);

    }

    public void handClick(){
       /*hand.setOnAction(e->{
            System.out.println("gggg");
            System.out.println("gggg2");
            System.out.println("gggg3");
        }
        );*/
    /* Jfxdemos j=new Jfxdemos();
        j.start(new Stage());
        System.out.println("File");*/

    }

}
