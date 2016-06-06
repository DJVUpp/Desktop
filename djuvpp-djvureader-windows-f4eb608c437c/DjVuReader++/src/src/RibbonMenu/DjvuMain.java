package src.RibbonMenu;

import com.lizardtech.djview.frame.Frame;
import java.awt.Color;
import java.awt.Dimension;
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
//import org.scenicview.ScenicView;

import java.net.URL;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by pedro_000 on 1/6/2015.
 */
public class DjvuMain extends Application {

    private Frame contentFrame;

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

        /*
         FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
         Parent textAreaHolder = (Parent) primaryLoader.load();
         FXMLLoader secondaryLoader = new FXMLLoader(getClass().getResource("second.fxml"));
         Parent textAreaUser = (Parent) secondaryLoader.load();
         FXMLPrimaryController primaryController = (FXMLPrimaryController) textAreaHolder.getController();
         FXMLsecondController secondController = (FXMLsecondController) textAreaUser.getController();
         secondController.textProperty().bind(primaryController.textProperty())
         */
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

        final SwingNode swingNode = new SwingNode();
        // create tabPane
        TabPane t = new TabPane();
        final StackPane tabes = new StackPane();
        /*
         *  StackPane tabB_stack = new StackPane();
         tabB_stack.setAlignment(Pos.CENTER);
         tabB_stack.getChildren().add(new Label("Label@Tab B"));
         tabB.setContent(tabB_stack);
         tabPane.getTabs().add(tabB);
         Tab tabB = new Tab();
         * */
        Tab viewTab = new Tab();

        opendialog();

        JPanel contentPanel = new JPanel();

//        System.out.println("OUT ---------------> " + viewTab.getContent().getBoundsInLocal());
//        System.out.println("OUT ---------------> " + primaryStage.getmin);
//        contentPanel.setBackground(Color.red);
        swingNode.setContent(contentPanel);

      //  Pane pane = new Pane();
  // BorderPane to add the two ScrollPanes to it
        // Then add this BorderPane to the tab after arrange its content as i do
        BorderPane view = new BorderPane();
        // hBox this ex for any thing you wanto to add to the ScrollPane
        // ex:-http://stackoverflow.com/questions/28037818/how-to-add-a-scrollbar-in-javafx
        // Left ScrollPane
        contentPanel.add(contentFrame.getContentPane());
        contentFrame.setPreferredSize(new Dimension(1400, 500));
        contentPanel.setPreferredSize(new Dimension(1400, 500));

        
        ScrollPane lScrollPane = new ScrollPane();
        
        // i try to add swingNode as we did to test but it does not work
        // you can try an other way now you have the boderPane contanins the two l&r scrollPanes 
        // then that borderPane called view added to the tab now evey thing ok
        //it works on Netbeans without any exceptions except the one we have before related to javaFx 
        ScrollPane rScrollPane = new ScrollPane(swingNode);
        // to get height Automatic
        lScrollPane.setFitToHeight(true);
        lScrollPane.setMinWidth(200);

        view.setLeft(lScrollPane);


        // set center to take the reast ot the layout so we can take only 200 for lScrollPane width the for the rScrollPane
        // it wil take the reast Automatically
        rScrollPane.setFitToHeight(true);
        rScrollPane.setFitToWidth(true);

        view.setCenter(rScrollPane);
        
      //  pane.getChildren().add(swingNode); // Adding swing node
        root.getChildren().addAll(editorRoot);
        // Add pane to the tab
        tabes.setAlignment(Pos.CENTER);
        tabes.getChildren().add(view);
        viewTab.setContent(tabes);
        viewTab.setText("Hola");
        
        t.getTabs().addAll(viewTab);
        root.setCenter(t);
       // root.getChildren().addAll(pane);

        // root.setCenter(swingNode);
        Scene scene = new Scene(root);
        URL url = this.getClass().getResource("../resource/fxribbon.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        URL url2 = this.getClass().getResource("../resource/fxribbon.css");

        // ScenicView.show(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Djvuicon.png")));
        primaryStage.setTitle("Djvu++");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    /* private void opendialog() throws IOException {

     FileDialog fd = new FileDialog(this, "open djvu file", FileDialog.LOAD);
     fd.setMultipleMode(true);
     fd.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/djvuNewIcon.png")));
     fd.show();
     if (fd.getDirectory() != null) {
     File files[] = fd.getFiles();
     for (File file : files) {

     String url;
     url = "" + file.toURI().toURL();
     url = url.substring(5, url.length());
     String name = file.getName();
     name_url.put(name, url);
     url_name.put(url, name);
     bookList.add(url);
     openBookInNewTab(url, name);

     }

     }

     }

     public void openBookInNewTab(final String url, String name) {

     //SwingUtilities.invokeLater(new Runnable() {
     //  @Override
     //public void run() {
     name = url_name.get(url);
     name_url.put(name, url.substring(1));
     url_name.put(url.substring(1), name);
     bookList.add(url.substring(1));

     com.lizardtech.djview.frame.Frame f = new com.lizardtech.djview.frame.Frame(url);
     f.setVisible(true);
     f.setSize(700, 900);
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //        Container pane = f.getContentPane();
     //        tabbedPane.add(name, pane);
     //        tabbedPane.setSelectedComponent(pane);
     //        String tabName = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
     //        if (!tabName.equals("GetStart")) {
     Band.setbean(beanMap.entrySet().iterator().next().getValue());

     }*/

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
