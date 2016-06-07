package src.RibbonMenu;

import com.lizardtech.djview.frame.Frame;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javax.swing.JFrame;

/**
 * Created by pedro_000 on 1/6/2015.
 */
public class DjvuMain extends Application {

    private Frame contentFrame;
    private ListView outline;
    private ListView pages;
    private final int OUTLINE_FIXED_CELL_SIZE = 220;
    private final int OUTLINE_WIDTH = 125;
    private final int OUTLINE_HEIGHT = 170;
    private final int PAGE_WIDTH = 720;
    private final int PAGE_HEIGHT = 770;

    /* private final  com.pixelduke.javafx.ribbon.RibbonMenu.com.lizardtech.djvubean.RibbonMenu.DjvuComponents Band = new com.pixelduke.javafx.ribbon.RibbonMenu.com.lizardtech.djvubean.RibbonMenu.DjvuComponents();
     public static final HashMap<String, com.pixelduke.javafx.ribbon.RibbonMenu.com.lizardtech.djvubean.DjVuBean> beanMap = new HashMap<String, com.pixelduke.javafx.ribbon.RibbonMenu.com.lizardtech.djvubean.DjVuBean>(); // TODO: remove static
     private ArrayList<String> bookList = new ArrayList<String>();
     private Map<String, String> name_url = new ConcurrentHashMap<String, String>();
     private Map<String, String> url_name = new ConcurrentHashMap<String, String>();*/
    static final String RESOURCE = "AdvancedRibbonFXML.fxml";

    MenuBar barr = new MenuBar();
    Menu file = new Menu("File");
    MenuItem itemNew = new MenuItem("New            ");
    MenuItem itemSave = new MenuItem("Save          ");
    MenuItem itemSaveAs = new MenuItem("Save As     ");
    MenuItem itemClose = new MenuItem("Close        ");

    @Override
    public void start(Stage primaryStage) throws Exception {
        // init the book to open
        opendialog();

        URL resource = getClass().getResource(RESOURCE);
        BorderPane root = FXMLLoader.load(resource);

        AnchorPane editorRoot = new AnchorPane();
        // file.setText("File");
        barr.setStyle("-fx-background-color: #fff;");
        file.getItems().addAll(itemNew, itemSave, itemSaveAs, itemClose);
        file.setStyle("-fx-background-color: #337AB7;\n"
                + " -fx-color:#fff;\n"
                + "    -fx-text-fill: white;\n"
                + "    -fx-border: none;\n"
                + "    -fx-min-width: 60;");
        barr.getMenus().addAll(file);
        editorRoot.getChildren().add(barr);

        // create tabPane
        TabPane t = new TabPane();
        final StackPane tabs = new StackPane();

        Tab viewTab = new Tab();

        // BorderPane to add the two ScrollPanes to it
        // Then add this BorderPane to the tab after arrange its content as i do
        BorderPane view = new BorderPane();

        initOutlineView();
        initPagesView();
        view.setLeft(outline);
        view.setCenter(pages);
        //  pane.getChildren().add(swingNode); // Adding swing node
        root.getChildren()
                .addAll(editorRoot);
        // Add pane to the tab
        tabs.setAlignment(Pos.CENTER);

        tabs.getChildren()
                .add(view);
        viewTab.setContent(tabs);

        viewTab.setText("Hola");

        t.getTabs()
                .addAll(viewTab);
        root.setCenter(t);
       // root.getChildren().addAll(pane);

        // root.setCenter(swingNode);
        Scene scene = new Scene(root);
        URL url = this.getClass().getResource("../resource/fxribbon.css");
        if (url
                == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();

        scene.getStylesheets()
                .add(css);
        URL url2 = this.getClass().getResource("../resource/fxribbon.css");

        // ScenicView.show(scene);
        primaryStage.getIcons()
                .add(new Image(getClass().getResourceAsStream("Djvuicon.png")));
        primaryStage.setTitle(
                "Djvu++");
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    /**
     * Initialize the outline listView and creates the view Renderer.
     */
    private void initOutlineView() {
        BookObservableList<ImageView> outlineData = new BookObservableList<>(OUTLINE_WIDTH, OUTLINE_HEIGHT);

        outline = new ListView<>();
        outline.setItems(outlineData);
//        outline.setFixedCellSize(OUTLINE_HEIGHT + 2);

        outline.setMinWidth(OUTLINE_WIDTH);
    }

    /**
     * Initialize the pages listView and creates the view Renderer.
     */
    private void initPagesView() {
        pages = new ListView();

        BookObservableList<ImageView> pagesData = new BookObservableList<>(PAGE_WIDTH, PAGE_HEIGHT);
        pages.setItems(pagesData);
//        outline.setFixedCellSize(PAGE_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void opendialog() throws IOException {

        FileDialog fd = new FileDialog(new JFrame(), "open djvu file", FileDialog.LOAD);
        fd.setMultipleMode(true);
        fd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/djvuNewIcon.png")));
        fd.setVisible(true);
        if (fd.getDirectory() != null) {
            File files[] = fd.getFiles();
            for (File file : files) {
                String url;
                url = "" + file.toURI().toURL();
                url = url.substring(5, url.length());
                String name = file.getName();

                // TODO: change implementation of openBookInNewTab
                openBookInNewTab(url, name);
            }
        }
    }

    public void openBookInNewTab(final String url, String name) {
        this.contentFrame = new Frame(url);
    }
}
