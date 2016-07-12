package src.RibbonMenu;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class StringShapeCell extends ListCell<String> {
    @Override
    public void updateItem(String item, boolean empty) {
// Need to call the super first
        super.updateItem(item, empty);
// Set the text and graphic for the cell
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            ImageView imgView = this.getShape(item);
            setGraphic(imgView);
        }
    }

    public ImageView getShape(String shapeType) {
        ImageView imgView = null;
        Image img=null;
        switch (shapeType.toLowerCase()) {
            case "clipboard":

                img = new Image(getClass().getResourceAsStream("../resource/images/clipboard48.png"));
                imgView= new ImageView(img);
                
                break;
            case "copy":
                img = new Image(getClass().getResourceAsStream("../resource/images/copy.png"));
                imgView= new ImageView(img);
                break;
            case "select text":
                img =new Image(getClass().getResourceAsStream("../resource/images/select-annotation16.png"));
                imgView= new ImageView(img);
                
                
                break;
            case "annotation":
                img = new Image(getClass().getResourceAsStream("../resource/images/select-annotation16.png"));
                imgView= new ImageView(img);
                break;
            case "zoom in":
                img = new Image(getClass().getResourceAsStream("../resource/images/zoom in48.png"));
                imgView= new ImageView(img);
                break;
            case "zoom out":
                img = new Image(getClass().getResourceAsStream("../resource/images/zoom out48.png"));
                imgView= new ImageView(img);
                break;
            case "actual size":
                img = new Image(getClass().getResourceAsStream("../resource/images/actual size16.png"));
                imgView= new ImageView(img);
                break;
            case "fit page":
                img = new Image(getClass().getResourceAsStream("../resource/images/fit page48.png"));
                imgView= new ImageView(img);
                break;
            case "fit width":
                img = new Image(getClass().getResourceAsStream("../resource/images/fit width48.png"));
                imgView= new ImageView(img);
                break;
            case "fit visible":
                img = new Image(getClass().getResourceAsStream("../resource/images/fit visible48.png"));
                imgView= new ImageView(img);
                break;
            default:
                imgView = null;
        }
        return imgView;
    }
}