/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.RibbonMenu;

import static com.lizardtech.djview.FullBookView.PagesCount;
import com.lizardtech.djvubean.outline.CreateThumbnails;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.collections.ModifiableObservableListBase;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javax.swing.JPanel;

/**
 * A custom observable list to store the book pages in it the
 *
 * @param <T> The content that the observable list carries.
 * @author Osama
 */
public class BookObservableList<T> extends ModifiableObservableListBase<T> {

    private DefaultPage defaultElement;     // the dafault value to return if 
    private final int WIDTH;
    private final int HEIGHT;

    public BookObservableList(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        // TODO: init default page
    }

    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        System.out.println("getting element at: " + index);
        //        if (list.get(index) == null) {
//            return defaultElement;
//        }
//
//        return list.get(index);
//        CreateThumbnails.generateThumbnail(index, WIDTH, HEIGHT);
        BufferedImage BImg = null;

        try {
            BImg = CreateThumbnails.generateThumbnail(index, WIDTH, HEIGHT);
        } catch (IOException ex) {
        }

        WritableImage image = SwingFXUtils.toFXImage(BImg, null);
        ImageView imgview = new ImageView(image);

        return (T) imgview;
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
