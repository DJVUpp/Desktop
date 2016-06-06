package RibbonMenu;


import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WordLikeMenuButton extends Application {

    @Override
    public void start(Stage primaryStage) {


        ChoiceBox<String> seasons = new ChoiceBox<String>();
        seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");
// Get the selected value
      //  String selectedValue = seasons.getValue();
// Set a new value
      //  seasons.setValue("Fall");
        BorderPane root = new BorderPane();
        root.setCenter(seasons);
        Scene scene = new Scene(root, 350, 75);
        URL url = this.getClass().getResource("../resource/fxribbon.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
