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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Key;
import java.util.*;

public class Game extends Application {

    private Parent root;
    Tile[][] board = new Tile[4][4];
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("2048 Game");
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene mainscene = new Scene(root, 500, 550);
        mainscene.getStylesheets().add(Game.class.getResource("Game.css").toExternalForm());
        for(int i = 1; i<=4; i++){
            for (int j = 1; j<=4; j++){
                board[i-1][j-1] = new Tile();

            }
        }
        randomTile(true);
        randomTile(true);
        updateGuiTiles();
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
    public void randomTile(boolean two){
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        if (board[x][y].getVal() == 0 ){
            if(two) {
                board[x][y].setVal(2);
            }else{
                board[x][y].setVal((r.nextInt(1)+1)*2);
            }
        }else{
            randomTile(two);
        }
    }
    public void updateGuiTiles() {
        for(int x = 1; x<=4; x++){
            for (int y = 1; y<=4; y++){
                Label lbl = (Label) root.lookup("#x" + x + "y" + y);
                lbl.setText(Integer.toString(board[x-1][y-1].getVal()));
                lbl.setBackground(new Background(new BackgroundFill(board[x-1][y-1].getColor(), null, null)));

            }
        }

    }
}
