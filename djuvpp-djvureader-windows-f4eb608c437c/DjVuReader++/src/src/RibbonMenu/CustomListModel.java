/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.RibbonMenu;

import static com.lizardtech.djview.FullBookView.PagesCount;
import com.lizardtech.djvubean.DjVuBean;
import javafx.collections.ModifiableObservableListBase;

/**
 * A custom observable list to store the book pages in it the
 *
 * @param <T> The content that the observable list carries.
 * @author Osama
 */
public class CustomListModel<T> extends ModifiableObservableListBase<T> {

    private final DjVuBean djVuBean;

    public CustomListModel(final DjVuBean djVuBean) {
        this.djVuBean = djVuBean;
    }

    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        System.out.println("getting element at: " + index);

//        this.djVuBean.setPage(index);
//        SwingNode tempNode = new SwingNode();
//        tempNode.setContent(djVuBean);
//        
//        BorderPane pane = new BorderPane();
//
//        SwingNode tempNode = new SwingNode();
//        JButton button = new JButton("test");
//        tempNode.setContent(button);
//        pane.setCenter(tempNode);
//        pane.setBottom(new Label(String.valueOf(index)));
        djVuBean.setPage(index);

        return (T) djVuBean;
    }

    @Override
    public int size() {
        // TODO: return the book pages number.
        return PagesCount;
    }

    @Override
    protected void doAdd(int index, T element) {
        // NOTE: function is not implemented
    }

    @Override
    protected T doSet(int index, T element) {
        // NOTE: function is not implemented
        return element;
    }

    @Override
    protected T doRemove(int index) {
        // NOTE: function is not implemented
        return null;
    }
}

