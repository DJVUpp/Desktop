/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.RibbonMenu;

import com.lizardtech.djvubean.DjVuBean;
import javafx.collections.ModifiableObservableListBase;
import javafx.embed.swing.SwingNode;

/**
 * A custom observable list to store the book pages in it the
 *
 * @param <T> The content that the observable list carries.
 * @author Osama
 */
public class BookObservableList<T> extends ModifiableObservableListBase<T> {

    private DjVuBean djvuBean;

    public BookObservableList(DjVuBean djVuBean) {
        this.djvuBean = djVuBean;
        
        if (this.djvuBean == null) {
            System.err.println(" ----------------------------------------------> NULL DjVuBean !!");
            System.exit(0);
        }
    }

    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        System.out.println("getting element at: " + index);
        SwingNode swingNode = new SwingNode();
        this.djvuBean.setPage(index);
        swingNode.setContent(this.djvuBean);

        return (T) swingNode;
    }

    @Override
    public int size() {
        // TODO: return the book pages number.
        return 10;
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
