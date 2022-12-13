package dhbw.on22;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.*;

public class Game extends Application {

    private Parent root;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("2048 Game");
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene mainscene = new Scene(root, 500, 550);
        mainscene.getStylesheets().add(Game.class.getResource("Game.css").toExternalForm());
        mainscene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                System.out.println("up");
            }
            else if (e.getCode() == KeyCode.A) {
                System.out.println("left");
            }
            else if (e.getCode() == KeyCode.S) {
                System.out.println("down");
            }
            else if (e.getCode() == KeyCode.D) {
                System.out.println("right");
            }
        });
        primaryStage.setScene(mainscene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); //Launch the app
    }

    public void updateScore(int score) {
        Label lbl = (Label) root.lookup("#score");
        lbl.setText("SCORE: " + score);
    }

    public void updateGuiTile(int x, int y, int value) {
        Label lbl = (Label) root.lookup("#x" + x + "y" + y);
        lbl.setText(Integer.toString(value));
    }


}
