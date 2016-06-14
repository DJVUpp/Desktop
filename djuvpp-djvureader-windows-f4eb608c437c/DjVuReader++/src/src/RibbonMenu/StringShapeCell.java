package RibbonMenu;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            case "paste":
                img = new Image(getClass().getResourceAsStream("../resource/images/Paste.png"));
                imgView= new ImageView(img);
                break;
            default:
                imgView = null;
        }
        return imgView;
    }
}