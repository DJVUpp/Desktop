package src.RibbonMenu;

import RecentFile.FileSavePath;
import RecentFile.recent;
import com.itextpdf.text.DocumentException;
import com.lizardtech.djview.frame.Frame;
import com.lizardtech.djvubean.DjVuBean;
import convert.Convertings;
import convert.ImagesToPDF;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JList;
import javax.swing.JOptionPane;
import convert.converts;
import static java.nio.file.Files.list;
import javafx.util.Callback;

public class DjvuMain extends Application {

    public Frame contentFrame;
    converts convs = new converts();

    private ListView outline;
    private ListView<DjVuBean> pages;
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

    ListView<String> rescentFileList;
    ListView<String> createList;
    ListView<String> openOption;
    Button saveAsBtn;
    Button blankBtn = new Button();
    Button Filebtn = new Button();

    VBox vb;
    Button openBtn;
    Button createbtn;
    BorderPane menuBorder;
    ScrollPane scrollPane;
//static    String tabName;
    public static TabPane DjvuTabs = new TabPane();

    // get fxml resources
    // static final String RESOURCE = "AdvancedRibbonFXML.fxml";
// public static Scene mainScene;
    // start up page content
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setPrefHeight(700);
        grid.setPrefWidth(1400);
        grid.getStyleClass().addAll("pane");

        //GridPane Configuration (Padding, Gaps, etc.)
        grid.setPadding(new Insets(150, 50, 50, 150));
        grid.setHgap(40);
        grid.setVgap(15);
        grid.setGridLinesVisible(false);

        //Nodes
        Label px = new Label();
        px.setMinWidth(535);
        px.setMinHeight(40);
        px.getStyleClass().addAll("t");

        Label p = new Label();
        p.setMinWidth(250);
        p.setMinHeight(40);
        p.getStyleClass().addAll("recentFile");

        Image fileImage = new Image(getClass().getResourceAsStream("../resource/images/file-list.JPG"));

        // Add recently files list view
        ListView<String> startRecentFileList = new ListView<String>();

        //calling recent to get the names and paths of recently files   
        recent r = new recent();
        File[] bookfiless = r.getbookfile();//paths
        String[] books = r.getrecentfile();//names

        JList booklist = new JList(books);
        booklist.setVisibleRowCount(8);
        ObservableList<String> recentItems = FXCollections.observableArrayList(books);

        startRecentFileList.setItems(recentItems);
        startRecentFileList.setPrefHeight(250);
        startRecentFileList.setCellFactory(param -> new ListCell<String>() {
            private ImageView rescentImageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {

                    rescentImageView.setImage(fileImage);

                    setText(name);
                    setGraphic(rescentImageView);
                }
            }
        });

        /**
         * This Function returns the recently files list
         */
        startRecentFileList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String url, name;
                File file;

                for (int i = 0; i < books.length; i++) {
                    if (startRecentFileList.getSelectionModel().getSelectedIndex() == i) {
                        if (bookfiless[i].exists()) {
                            file = bookfiless[i];
                            try {
                                url = "" + file.toURI().toURL();

                                url = url.substring(5, url.length());
                                name = file.getName();
                                openBookInNewTab(url, name);
                            } catch (MalformedURLException ex) {
                                JOptionPane.showMessageDialog(null, "Problem in Openning Book In A New Tab ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " File Does Not Exist ! ");
                            RecentFile.DeleteRecent.DelRecentFiles();
                        }
                    }
                }
            }

        });

        //Set Column and Row Constraints
        ColumnConstraints column1 = new ColumnConstraints(100, 250, 250);
        ColumnConstraints column2 = new ColumnConstraints(550, 535, 535);
        ColumnConstraints column3 = new ColumnConstraints();
        RowConstraints row2 = new RowConstraints(75, 75, 75);
        RowConstraints rowEmpty = new RowConstraints();

        //Set Grow Priorities
        column3.setHgrow(Priority.ALWAYS);
        row2.setVgrow(Priority.ALWAYS);

        //Add Constraints to Columns & Rows
        grid.getColumnConstraints().addAll(column1, column2, column3);
        grid.getRowConstraints().add(0, rowEmpty);
        grid.getRowConstraints().add(1, rowEmpty);
        grid.getRowConstraints().add(2, rowEmpty);
        grid.getRowConstraints().add(3, rowEmpty);

        HBox taskBtnRow1 = new HBox(25);

        //Nodes
        Button djvuToImage = new Button();
        Button imageToDjvu = new Button();
        Button pdfToDjvu = new Button();

        pdfToDjvu.setMinWidth(158);
        pdfToDjvu.setMinHeight(108);
        pdfToDjvu.getStyleClass().addAll("pdfToDjvu");

        djvuToImage.setMinWidth(158);
        djvuToImage.setMinHeight(108);
        djvuToImage.getStyleClass().addAll("djvuToImage");

        imageToDjvu.setMinWidth(158);
        imageToDjvu.setMinHeight(108);
        imageToDjvu.getStyleClass().addAll("imageToDjvu");

        //Add nodes to HBox
        taskBtnRow1.getChildren().addAll(pdfToDjvu, djvuToImage, imageToDjvu);
        //DJVU To Image
        djvuToImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                convs.djvutoimage();
            }
        });

        //image to djvu
        imageToDjvu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Convertings con = new Convertings();
                try {
                    con.convertimagestodjvu();
                } catch (DocumentException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        //pdf to djvu
        pdfToDjvu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                convs.pdftodjvu();

            }
        });

        HBox taskBtnRow2 = new HBox(25);

        //Nodes
        Button imageToPdf = new Button();
        Button djvuToPdf = new Button();
        Button pdfToImage = new Button();

        imageToPdf.setMinWidth(158);
        imageToPdf.setMinHeight(108);
        imageToPdf.getStyleClass().addAll("imageToPdf");

        djvuToPdf.setMinWidth(158);
        djvuToPdf.setMinHeight(108);
        djvuToPdf.getStyleClass().addAll("djvuToPdf");

        pdfToImage.setMinWidth(158);
        pdfToImage.setMinHeight(108);
        pdfToImage.getStyleClass().addAll("pdfToImage");
        //image to pdf
        imageToPdf.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg", "*.tif"));
                List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
                ImagesToPDF con = new ImagesToPDF();
                System.err.println(selectedFiles.get(0).getAbsolutePath());
                try {
                    con.IMGToPDF(selectedFiles.get(0).getAbsolutePath(), "C:\\DjVu++Task\\Out.pdf");
                } catch (DocumentException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DjvuMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        //djvu to pdf`
        djvuToPdf.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                convs.djvutopdf();

            }
        });
        //Pdf to Image

        pdfToImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                convs.pdftoimage();
            }
        });
        //Add nodes to HBox
        taskBtnRow2.getChildren().addAll(imageToPdf, djvuToPdf, pdfToImage);
        //Set Row & Column Index for Nodes

        Label androidApp = new Label();
        androidApp.setMinHeight(250);
        androidApp.setMinWidth(255);
        androidApp.getStyleClass().addAll("an-logo");

        Button androidBtn = new Button();
        androidBtn.setMinWidth(255);
        androidBtn.setMinHeight(70);
        androidBtn.getStyleClass().addAll("android-btn");

        VBox androidVb = new VBox();
        androidVb.setMinWidth(255);
        androidVb.setMinHeight(335);
        androidVb.setSpacing(10);

        androidVb.getChildren().addAll(androidApp, androidBtn);

        // page.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        GridPane.setConstraints(p, 0, 1, 2, 1);
        GridPane.setConstraints(startRecentFileList, 0, 2, 1, 4);
        GridPane.setConstraints(px, 1, 1);
        GridPane.setConstraints(taskBtnRow1, 1, 2);
        GridPane.setConstraints(taskBtnRow2, 1, 3);

        GridPane.setConstraints(androidVb, 2, 0, 1, 5);

        grid.getChildren().addAll(p, startRecentFileList, px, taskBtnRow1, taskBtnRow2, androidVb);

        return grid;
    }

    public AnchorPane addAnchorPane(GridPane grid) {
        AnchorPane anchorpane = new AnchorPane();

        anchorpane.getChildren().addAll(grid);

        AnchorPane.setTopAnchor(grid, 10.0);

        return anchorpane;
    }

    // browser pc to select djvu document to be opened
    private VBox browseDjvu() {
        VBox browseDjvu = new VBox();
        browseDjvu.setSpacing(10);
        browseDjvu.setPadding(new Insets(0, 20, 10, 20));
        Label browseLabel = new Label("Computer");

        // when this button click make it to open file dialog to select document
        Button browseBtn = new Button();
        browseBtn.getStyleClass().add("browse-btn");

        browseLabel.getStyleClass().add("browse");
//        browseBtn.setContentDisplay(ContentDisplay.CENTER);
        browseDjvu.getChildren().addAll(browseLabel, browseBtn);

        browseBtn.setOnAction(e -> {

            try {
                opendialog();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "You have not select a Djvu file !", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return browseDjvu;

    }
//////////////////////////////////////////////////////////////////////////////

    // Create from blank
    private VBox createFomBlank() {

        VBox createFomBlank = new VBox();
        createFomBlank.setSpacing(10);
        createFomBlank.setPadding(new Insets(0, 20, 10, 20));

        Label blankLabel = new Label("Create Blank Djvu");
        blankLabel.getStyleClass().add("blank-label");

        Label createLabel = new Label("Create a djvu from a blank page");

        // when this button click it allow user to create djvu from blank
        blankBtn.getStyleClass().add("blank-btn");

        createFomBlank.getChildren().addAll(blankLabel, createLabel, blankBtn);

        return createFomBlank;

    }

    // Create from clipboard
    private VBox createFomClipboard() {

        VBox createFomClipboard = new VBox();
        createFomClipboard.setSpacing(10);
        createFomClipboard.setPadding(new Insets(0, 20, 10, 20));

        Label fileLabel = new Label("Create From Clipboard");
        fileLabel.getStyleClass().add("blank-label");

        Label createLabel = new Label("Create a Djvu from clipboard");

        // when this button clicked allow the user to create djvu from clipboard
        // Button Filebtn=new Button();
        Filebtn.getStyleClass().add("clip-btn");

        createFomClipboard.getChildren().addAll(fileLabel, createLabel, Filebtn);

        return createFomClipboard;

    }

    // open file from recent or browse computer documents
    private VBox open() {

        Image fromRecent = new Image(getClass().getResourceAsStream("../resource/images/recent.JPG"));
        Image fromComputer = new Image(getClass().getResourceAsStream("../resource/images/computer.JPG"));
        Image[] openImages = {fromRecent, fromComputer};

        VBox vbCreateFile = new VBox();
        vbCreateFile.setSpacing(10);
        vbCreateFile.setPadding(new Insets(0, 20, 10, 20));

        Label open = new Label("Open");

        open.getStyleClass().add("open");

        openOption = new ListView<String>();

        ObservableList<String> items = FXCollections.observableArrayList(
                "Recent Documents", "Computer");
        openOption.setItems(items);
        openOption.setPrefHeight(350);
        openOption.setCellFactory(param -> new ListCell<String>() {
            private ImageView openImageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals("Recent Documents")) {
                        openImageView.setImage(openImages[0]);
                    } else if (name.equals("Computer")) {
                        openImageView.setImage(openImages[1]);
                    }

                    setText(name);
                    setGraphic(openImageView);
                }
            }
        });
        openOption.getStyleClass().add("list-views");
//
        openOption.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                //  System.out.println(newValue);
                if (newValue.equals("Computer")) {
                    menuBorder.setLeft(open());
                    menuBorder.setRight(browseDjvu());
                } else if (newValue.equals("Recent Documents")) {
                    menuBorder.setLeft(open());
                    menuBorder.setRight(openRecent());
                }

            }
        });
        vbCreateFile.getChildren().addAll(open, openOption);

        return vbCreateFile;
    }

    // open file from recent files
    private VBox openRecent() {

        Image fileImage = new Image(getClass().getResourceAsStream("../resource/images/file-list.JPG"));

        Image[] listOfImages = {fileImage};
        VBox vbCreateFile = new VBox();
        vbCreateFile.setSpacing(10);
        vbCreateFile.setPadding(new Insets(0, 20, 10, 20));

        Label recent = new Label("Recent Documents");

        recent.getStyleClass().add("recent-label");

        rescentFileList = new ListView<String>();

        rescentFileList.getStyleClass().add("no-border");

        // provide path to recent documents to be opened
        recent r = new recent();
        File[] bookfiless = r.getbookfile();//paths
        String[] books = r.getrecentfile();//names
        ObservableList<String> items = FXCollections.observableArrayList(books);
        rescentFileList.setItems(items);
        rescentFileList.setPrefHeight(350);
        rescentFileList.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {

                    imageView.setImage(listOfImages[0]);

                    setText(name);
                    setGraphic(imageView);
                }
            }
        });

        vbCreateFile.getChildren().addAll(recent, rescentFileList);

        rescentFileList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String url, name;
                File file;

                for (int i = 0; i < books.length; i++) {
                    if (rescentFileList.getSelectionModel().getSelectedIndex() == i) {
                        if (bookfiless[i].exists()) {
                            file = bookfiless[i];
                            try {
                                url = "" + file.toURI().toURL();

                                url = url.substring(5, url.length());
                                name = file.getName();
                                openBookInNewTab(url, name);
                            } catch (MalformedURLException ex) {
                                JOptionPane.showMessageDialog(null, "Problem in Openning Book In A New Tab ");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, " File Does Not Exist ! ");

                            String ObjButtons[] = {"Yes", "No"};
                            int PromptResult = JOptionPane.showOptionDialog(null,
                                    "Do you want to delete all recently files?", "Refresh ?",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                    ObjButtons, ObjButtons[1]);
                            //yes option
                            if (PromptResult == 0) {
                                RecentFile.DeleteRecent.DelRecentFiles();
                            }
                        }
                    }
                }
            }

        });

        return vbCreateFile;
    }

    // show options in the menu for create djvu
    private VBox createDjvu() {

        VBox vbCreate = new VBox();
        vbCreate.setSpacing(10);
        vbCreate.setPadding(new Insets(0, 20, 10, 20));

        Label create = new Label("Create");
        create.getStyleClass().add("open");

        Image fromBlank = new Image(getClass().getResourceAsStream("../resource/images/blank-list.JPG"));
        Image fromClipboard = new Image(getClass().getResourceAsStream("../resource/images/clip-list.JPG"));
        Image[] createImages = {fromBlank, fromClipboard};

        createList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Blank", "From Clipboard");
        createList.setItems(items);
        createList.setPrefHeight(350);
        createList.getStyleClass().add("s");
        createList.setCellFactory(param -> new ListCell<String>() {
            private ImageView createImageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals("Blank")) {
                        createImageView.setImage(createImages[0]);
                    } else if (name.equals("From Clipboard")) {
                        createImageView.setImage(createImages[1]);
                    }

                    setText(name);
                    setGraphic(createImageView);
                }
            }
        });

        createList.getStyleClass().add("list-views");

        createList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                //  System.out.println(newValue);
                if (newValue.equals("Blank")) {
                    menuBorder.setRight(createFomBlank());
                } else if (newValue.equals("From Clipboard")) {

                    menuBorder.setRight(createFomClipboard());
                }

            }
        });

        vbCreate.getChildren().addAll(create, createList);

        return vbCreate;
    }

    // Save As Pdf or Djvu
    private VBox saveAs() {
        VBox saveAsVb = new VBox();
        saveAsVb.setSpacing(10);
        saveAsVb.setPadding(new Insets(0, 20, 10, 20));

        Label djvuLabel = new Label("Save As Djvu");
        djvuLabel.getStyleClass().add("blank-label");

        // save as djvu button
        // provide the code to saveAS Djvu
        Button saveAsDjvubtn = new Button();
        saveAsDjvubtn.getStyleClass().add("djvu-btn");
        /**
         * This is the function of SaveAs DJVU file
         */
        saveAsDjvubtn.setOnAction(e -> {

            Save.SaveAS.saveAsDjvu();
        });

        Label pdfLabel = new Label("Save As PDF");
        pdfLabel.getStyleClass().add("save-pdf-label");

        //provide the code to saveAs Pdf
        Button saveAsPdfbtn = new Button();
        saveAsPdfbtn.getStyleClass().add("pdf-btn");

        saveAsVb.getChildren().addAll(djvuLabel, saveAsDjvubtn, pdfLabel, saveAsPdfbtn);

        /**
         * This is the function of save as PDF button
         */
        saveAsPdfbtn.setOnAction(e -> {

            Save.SaveAS.saveAsPdf();
        });

        return saveAsVb;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // init the book to open
        //   opendialog();

        /* URL resource = getClass().getResource(RESOURCE);
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
        
         Tab viewTab = new Tab();*/
        // BorderPane to add the two ScrollPanes to it
        // Then add this BorderPane to the tab after arrange its content as i do
        // BorderPane view = new BorderPane();
        //  initOutlineView();
        //initPagesView();
      /*  view.setLeft(outline);
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
        
         t.getTabs().addAll(viewTab);*/
//        root.setCenter(t);
        //=======================================  root.setCenter(swingNode);

        /*   Scene scene = new Scene(root);
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
        
         primaryStage.show();*/
        // file button
        Button file = new Button();
        file.setText("File");
        file.getStyleClass().add("file-btn");

        URL resource = getClass().getResource(RESOURCE);
        BorderPane mainLayout = FXMLLoader.load(resource);

        // final SwingNode swingNode = new SwingNode();
        //How to create new tab
        //first create new tab ex  Tab viewTab2 =new Tab();
        // for document you can create function that will open Djvu document in new tab
        // for example to add borderPane to be displayed in the tab you can create it   BorderPane view = new BorderPane();
        //the set the content to the BorderPane    view2.setCenter(saveAs());
        //you can add the borderPane to the Tab using  viewTab2.setContent(view2);
        //As we have  TabPane DjvuTabs =new TabPane(); so we can add as many tabs as we need to it.  DjvuTabs.getTabs().addAll(viewTab,viewTab2);
        // create tabPane
        final StackPane tabes = new StackPane();

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(contentFrame.Bean);

        Tab viewTab = new Tab();
        Tab viewTab2 = new Tab();

        // create different scene to switch between them.
        Scene mainScene = new Scene(mainLayout);
        URL url = this.getClass().getResource("../resource/fxribbon.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        mainScene.getStylesheets().add(css);

        // borderPane that contains Djvu start page contents
        BorderPane view = new BorderPane();
        view.getStylesheets().add(css);

        //get the grid view then add it to the boarderPane
        view.setCenter(addAnchorPane(addGridPane()));

        BorderPane view2 = new BorderPane();
        view2.getStylesheets().add(css);

        // only example for showing how you can create more tabs
        // add djvu to tab
        view2.setCenter(swingNode);

        viewTab2.setContent(view2);

        // adding file button to be shown at the most corner
        final StackPane fileLayout = new StackPane();
        AnchorPane fileAnchorPane = new AnchorPane();
        fileAnchorPane.getChildren().add(file);
        fileLayout.getChildren().addAll(fileAnchorPane);
        URL urlFile = this.getClass().getResource("../resource/style.css");
        if (urlFile == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String cssFile = urlFile.toExternalForm();

        Scene fileScene = new Scene(fileLayout, 1500, 800);
        fileScene.getStylesheets().add(cssFile);

        mainLayout.getChildren().addAll(fileAnchorPane);

        tabes.setAlignment(Pos.CENTER);
        tabes.getChildren().addAll(view);

        viewTab.setContent(tabes);
        viewTab.setText("Start Page");

        viewTab2.setContent(view2);
        viewTab2.setText("Book.djvu");

        // set up the menu
        HBox fileRoot = new HBox();
        fileRoot.getStyleClass().add("whiteBackground");
        HBox recentRoot = new HBox();

        VBox menu = new VBox();
        menu.setStyle("-fx-background-color: blue;");
        menu.setFillWidth(true);
        Button backBtn = new Button();
        backBtn.setPrefWidth(150);
        backBtn.getStyleClass().add("arrow");
        // switch the scene when the back button clicked
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // switch the scene when the back button clicked
                primaryStage.setScene(mainScene);
            }
        });

        /**
         * This is the action of save Djvu button
         */
        Button saveBtn = new Button("Save");
        saveBtn.setPrefWidth(150);
        saveBtn.getStyleClass().add("custom-menu-button");
        saveBtn.setOnAction(e -> {

            Save.save.saveFile();
        });

        Button aboutBtn = new Button("About Us");
        aboutBtn.setPrefWidth(150);
        aboutBtn.getStyleClass().add("custom-menu-button");

        Button exitBtn = new Button("Close");
        exitBtn.setPrefWidth(150);
        exitBtn.getStyleClass().add("custom-menu-button");

        exitBtn.setOnAction(e -> Platform.exit());

        saveAsBtn = new Button("Save As");
        saveAsBtn.setPrefWidth(150);
        saveAsBtn.getStyleClass().add("custom-menu-button");
        createbtn = new Button("Create");
        createbtn.setPrefWidth(150);
        createbtn.getStyleClass().add("custom-menu-button");
        openBtn = new Button("Open");
        openBtn.setPrefWidth(150);
        openBtn.getStyleClass().add("custom-menu-button");

        VBox logoVb = new VBox();
        logoVb.setPadding(new Insets(300, 5, 0, 20));

        Label logo = new Label();
        logo.getStyleClass().add("djvu-logo");
        logoVb.getChildren().add(logo);

        // add items to the menu
        menu.getChildren().addAll(backBtn, openBtn, createbtn, saveBtn, saveAsBtn, aboutBtn, exitBtn, logoVb);

        menuBorder = new BorderPane();

        menuBorder.setPadding(new Insets(20, 0, 20, 20));
        menuBorder.setLeft(open());
        menuBorder.setRight(openRecent());

        // open menu button action to set menu border contents
        openBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                menuBorder.setLeft(open());
                menuBorder.setRight(openRecent());

            }
        });

        // create menu button action to set menu border contents
        createbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuBorder.setLeft(createDjvu());
                menuBorder.setRight(createFomBlank());
            }
        });

        // saveAs menu button action to set menu border contents
        saveAsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                menuBorder.setLeft(saveAs());
                menuBorder.setRight(new VBox());

            }
        });

        // switch the scene when the file button clicked
        file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuBorder.setLeft(open());
                menuBorder.setRight(openRecent());
                primaryStage.setScene(fileScene);
            }
        });

        blankBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreatDjVu.NewDjvu.createFrame();
                primaryStage.setScene(mainScene);
            }
        });

        Filebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreatDjVu.NewDjvuFromClib.createFrame();
                primaryStage.setScene(mainScene);
            }
        });
        /*   .setOnAction((ActionEvent event) -> {
         // switch the scene when the back button clicked
          
         });*/

        fileRoot.getChildren().addAll(menu, menuBorder);

        fileLayout.getChildren().add(fileRoot);

        // add more tabs to 
        DjvuTabs.getTabs().addAll(viewTab);
        mainLayout.setCenter(DjvuTabs);

        // Used to show and test layout and components
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Djvuicon.png")));
        primaryStage.setTitle("Djvu++");
        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    /**
     * Initialize the outline listView and creates the view Renderer.
     */
    private void initOutlineView(DjVuBean djVuBean) {
        /*   CustomListModel<ImageView> outlineData = new CustomListModel<>(OUTLINE_WIDTH, OUTLINE_HEIGHT);

         outline = new ListView<>();
         outline.setItems(outlineData);
         //        outline.setFixedCellSize(OUTLINE_HEIGHT + 2);

         outline.setMinWidth(OUTLINE_WIDTH);
         */
    }

    /**
     * Initialize the pages listView and creates the view Renderer.
     */
    private void initPagesView(final DjVuBean djVuBean) {
        pages = new ListView<DjVuBean>();

        CustomListModel<DjVuBean> pagesData = new CustomListModel<>(djVuBean);
//        ArrayList<SwingNode> data = new ArrayList<>();
//        for (int i = 0; i < djVuBean.getDocument().size(); i++) {
//            djVuBean.setPage(i);
//            SwingNode tempNode = new SwingNode();
//            tempNode.setContent(djVuBean);
//
//            data.add(tempNode);
//        }
//
//        ObservableList<SwingNode> pagesData = FXCollections.observableArrayList(data);

        pages.setItems(pagesData);
        pages.setCellFactory(new Callback<ListView<DjVuBean>, ListCell<DjVuBean>>() {
            @Override
            public ListCell<DjVuBean> call(ListView<DjVuBean> list) {
                return new CustomListFactory();
            }
        }
        );

//        TODO: remove hardconded parameter.
        pages.setFixedCellSize(1150);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void opendialog() throws IOException {

        FileChooser fd = new FileChooser();
        fd.setTitle("Open Djvu File");
        setExtFilters(fd);
        Stage primaryStage = null;
        File file = fd.showOpenDialog(primaryStage);

        if (file == null) {
            JOptionPane.showMessageDialog(null, "You have cancelled opening Djvu file !", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        /**
         * To check whether the file stored in recently files or not And Save the path in the
         * recently files
         */
        String path = file.getPath();
        FileSavePath.save(path);
        if (file != null) {
            String url = "" + file.toURI().toURL();
            url = url.substring(5, url.length());
            String name = file.getName();
            openBookInNewTab(url, name);
        }
    }

    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("DJVU", "*.djvu")
        );
    }

    public void openBookInNewTab(String url, String name) {

//         this.contentFrame = new Frame(url);
//       tabName=name;
        contentFrame = new Frame(url);
        final DjVuBean tempBean = contentFrame.Bean;

        String tabName;
        tabName = name;
        Tab viewTab2 = new Tab();
        BorderPane view2 = new BorderPane();

//        SwingNode swingNode = new SwingNode();
//        swingNode.setContent(tempBean);
//
        initPagesView(tempBean);
//        view2.setCenter(swingNode);
        view2.setCenter(pages);
        viewTab2.setContent(view2);

        viewTab2.setText(tabName);
        DjvuTabs.getTabs().add(viewTab2);

    }

}
