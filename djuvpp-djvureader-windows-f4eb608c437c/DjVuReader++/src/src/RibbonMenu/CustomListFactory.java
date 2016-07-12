/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.RibbonMenu;

import javafx.scene.control.ListCell;
import com.lizardtech.djvubean.DjVuBean;
import javafx.embed.swing.SwingNode;
import javax.swing.JPanel;

/**
 *
 * @author Osama
 */
class CustomListFactory<T> extends ListCell<T> {
    
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        
        if (item instanceof DjVuBean && !empty) {
            if (item != null) {
                SwingNode tempNode = new SwingNode();
                tempNode.setContent((JPanel) item);
                setGraphic(tempNode);
            }
        }
    }
}
