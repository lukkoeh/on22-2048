package dhbw.on22;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Game extends Application {

    private static int padding = 5;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("2048 Game");
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene mainscene = new Scene(root, 500, 550);
        mainscene.getStylesheets().add(Game.class.getResource("Game.css").toExternalForm());
        primaryStage.setScene(mainscene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); //Launch the app
    }


}
