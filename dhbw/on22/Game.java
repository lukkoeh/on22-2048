package dhbw.on22;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Game extends Application {

    private Parent root;
    private static int score = 0;
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

        restartGame();

        mainscene.setOnSwipeRight(e->{
            moveRight();
        });
        mainscene.setOnSwipeLeft(e->{
            moveLeft();
        });
        mainscene.setOnSwipeUp(e->{
            moveUp();
        });
        mainscene.setOnSwipeDown(e->{
            moveDown();
        });

        mainscene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {

                moveUp();

            }
            else if (e.getCode() == KeyCode.A) {

                moveLeft();

            }
            else if (e.getCode() == KeyCode.S) {

                moveDown();

            }
            else if (e.getCode() == KeyCode.D) {

                moveRight();

            }
        });
        primaryStage.setScene(mainscene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); //Launch the app
    }



    public void mergeHorizontal(int col, int row, boolean left) {
        if (left) {
            for (int x = 0; x < col; x++) {
                if (board[col][row].getVal() == board[x][row].getVal() &&  board[col][row].getVal() == board[x+1][row].getVal()) {
                    board[x][row].setVal(board[x][row].getVal() * 2);
                    board[col][row].setVal(0);

                }
            }
        } else {
            for (int x = 3; x > col; x--) {
                if (board[col][row].getVal() == board[x][row].getVal() &&  board[col][row].getVal() == board[x-1][row].getVal()) {
                    board[x][row].setVal(board[x][row].getVal() * 2);
                    board[col][row].setVal(0);

                }
            }

        }
    }
    public void mergeVertical(int row, int col, boolean down) {
        if (down) {
            for (int y = 3; y > col; y--) {
                if (board[row][col].getVal() == board[row][y].getVal() && board[row][col].getVal() == board[row][y-1].getVal()) {
                    board[row][y].setVal(board[row][col].getVal() * 2);
                    board[row][col].setVal(0);
                }
            }
        } else {
            for (int y = 0; y < col; y++) {
                if (board[row][col].getVal() == board[row][y].getVal() && board[row][col].getVal() == board[row][y+1].getVal()) {
                    board[row][y].setVal(board[row][col].getVal() * 2);
                    board[row][col].setVal(0);
                }
            }

        }
    }

    public void moveUp() {
        boolean moved = false;

        for (int x = 0; x < 4; x++){
            boolean combined = false;
            for (int y = 1; y <= 3; y++){

                if (board[x][y].getVal()!=0 && board[x][y-1].getVal()==0){  //Nicht 0, y-Nachbar 0
                    board[x][y-1].setVal(board[x][y].getVal());
                    board[x][y].setVal(0);
                    y=0;
                    moved=true;
                }
                else if((!combined) && board[x][y].getVal()!=0 && board[x][y-1].getVal() == board[x][y].getVal()){  //Nicht 0, y-Nachbar gleich aktuelles Tile
                    mergeVertical(x, y, false);
                    score += board[x][y-1].getVal();
                    updateScore();
                    y=0;
                    moved = true;
                    combined = true;
                }

            }

        }
        if(moved){
            randomTile();
        }

        updateGuiTiles();
        checkWin();
        checkLoose();
    }

    public void moveDown(){
        boolean moved = false;

        for (int x = 0; x < 4; x++){
            boolean combined = false;
            for (int y = 2; y >=0; y--){

                if (board[x][y].getVal()!=0 && board[x][y+1].getVal()==0){  //Nicht 0, y-Nachbar 0
                    board[x][y+1].setVal(board[x][y].getVal());
                    board[x][y].setVal(0);
                    y=3;
                    moved=true;
                }
                else if((!combined) && board[x][y].getVal()!=0 && board[x][y+1].getVal() == board[x][y].getVal()){  //Nicht 0, y-Nachbar gleich aktuelles Tile
                    mergeVertical(x, y, true);
                    score += board[x][y+1].getVal();
                    updateScore();
                    y=3;
                    moved = true;
                    combined = true;
                }

            }

        }
        if(moved){
            randomTile();
        }

        updateGuiTiles();
        checkWin();
        checkLoose();
    }



    public void moveLeft(){
        boolean moved = false;

        for (int y = 0; y < 4; y++){
            boolean combined = false;
            for (int x = 1; x <= 3; x++){

                if (board[x][y].getVal()!=0 && board[x-1][y].getVal()==0){  //Nicht 0, y-Nachbar 0
                    board[x-1][y].setVal(board[x][y].getVal());
                    board[x][y].setVal(0);
                    x=0;
                    moved=true;
                }
                else if((!combined) && board[x][y].getVal()!=0 && board[x-1][y].getVal() == board[x][y].getVal()){  //Nicht 0, y-Nachbar gleich aktuelles Tile
                    mergeHorizontal(x,y, true);
                    score += board[x-1][y].getVal();
                    updateScore();
                    x=0;
                    moved = true;
                    combined = true;
                }
            }
        }
        if(moved){
            randomTile();
        }
        updateGuiTiles();
        checkWin();
        checkLoose();
    }
    public void moveRight(){
        boolean moved = false;

        for (int y = 0; y < 4; y++){
            boolean combined = false;
            for (int x = 0; x <= 2; x++){

                if (board[x][y].getVal()!=0 && board[x+1][y].getVal()==0){  //Nicht 0, y-Nachbar 0
                    board[x+1][y].setVal(board[x][y].getVal());
                    board[x][y].setVal(0);
                    x=-1;
                    moved=true;
                }
                else if((!combined) && board[x][y].getVal()!=0 && board[x+1][y].getVal() == board[x][y].getVal()){  //Nicht 0, y-Nachbar gleich aktuelles Tile
                    mergeHorizontal(x, y, false);
                    score += board[x+1][y].getVal();
                    updateScore();
                    x=-1;
                    moved = true;
                    combined = true;
                }
            }
        }
        if(moved){
            randomTile();
        }
        updateGuiTiles();
        checkWin();
        checkLoose();
    }
    public void updateScore() {
        Label lbl = (Label) root.lookup("#score");
        lbl.setText("SCORE: " + score);
    }

    public void randomTile(){

        Random r = new Random();
        int x = r.nextInt(4);
        int y = r.nextInt(4);

        if (board[x][y].getVal() == 0 ){

                board[x][y].setVal((r.nextInt(2) + 1) * 2);

        }else{

            randomTile();
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

    public void clearTiles() {
        for (int x=0; x<4; x++) {
            for (int y=0; y<4; y++) {
                board[x][y].setVal(0);
            }
        }
    }

    public void restartGame() {
        clearTiles();
        randomTile();
        randomTile();
        score = 0;
        updateScore();
        updateGuiTiles();
    }

     public void showDialog(boolean won) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (won) {
            alert.setTitle("GEWONNEN");
            alert.setHeaderText("Du hast gewonnen, willst du erneut spielen?");
        } else {
            alert.setTitle("VERLOREN");
            alert.setHeaderText("Du hast verloren, willst du es nochmal versuchen?");
        }
         ButtonType btyes = new ButtonType("Ja");
         ButtonType btno = new ButtonType("Nein");

         alert.getButtonTypes().setAll(btyes, btno);

         Optional<ButtonType> result = alert.showAndWait();

         if (result.get() == btyes) {
                restartGame();
         } else {
             Platform.exit();
         }
     }

     public void checkWin() {
        for(int x = 0;x < 4;x++) {
            for(int y = 0;y < 4;y++) {
                if (board[x][y].getVal() == 2048) {
                    showDialog(true);
                }
            }
        }
     }

     public void checkLoose() {
        boolean combineable = false;
       if (isFull()) {
           for(int x = 0;x < 4;x++) {
               for (int y = 0; y < 3; y++) {
                   if (board[x][y].getVal() == board[x][y+1].getVal() && y <= 2) {
                       combineable =true;
                   }
               }
           }
           for(int y = 0;y < 4;y++) {
               for (int x = 0; x < 3; x++) {
                   if (board[x][y].getVal() == board[x+1][y].getVal() && x <= 2) {
                       combineable =true;
                   }
               }
           }
           if (!combineable) {
               showDialog(false);
           }
       }
     }

     public boolean isFull() {
        boolean full = true;
         for(int x = 0;x < 4;x++) {
             for (int y = 0; y < 4; y++) {
                 if (board[x][y].getVal() == 0) {
                     full = false;
                 }
             }
         }
         return full;
     }
}
