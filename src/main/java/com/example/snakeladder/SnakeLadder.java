package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize= 40,width= 10,height=10;
    public static final int buttonLine= height*tileSize+50, infoLine = height*tileSize+20;

    private  static Dice dice= new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStarted=false, playerOneTurn=false,playerTwoTurn=false;

    private Pane createContent()
    {
      Pane root= new Pane();
      root.setPrefSize(tileSize*width,tileSize*height+100);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {


                Tile tile= new Tile (tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }

        }

        Image image= new Image("C:\\Users\\Rajat Dixit\\IdeaProjects\\SnakeLadder\\src\\main\\Screenshot (3).png");
        ImageView board= new ImageView();
        board.setImage(image);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Buttons

        Button playerOneButton= new Button("Player One");
        Button playerTwoButton= new Button("Player Two");
        Button  startButton = new Button("Start");
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(30);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);



        Label playerOneLabel= new Label(" ");
        Label playerTwoLabel= new Label(" ");
        Label diceLabel = new Label(" Start the game ");
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(280);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);
        playerOne =new Player(tileSize, Color.BLACK," Rajat");
        playerTwo =new Player(tileSize-5, Color.WHITE," Rockey");
           // action player
         playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override

             public void handle(ActionEvent actionEvent) {
                 if(gameStarted)
                 {
                     if(playerOneTurn)
                     {
                         int diceValue= dice.getRollerDiceValue();
                         diceLabel.setText("Dice value : "+diceValue);
                         playerOne.movePlayer(diceValue);
                         // winning Condition
                         if(playerOne.isWinner())
                         {
                             diceLabel.setText("Winner is"+playerOne.getName());
                             playerOneTurn=false;
                             playerOneButton.setDisable(true);
                             playerOneLabel.setText("");

                             playerTwoTurn=false;
                             playerTwoButton.setDisable(true);
                             playerTwoLabel.setText(" ");

                             startButton.setDisable(false);
                             startButton.setText("Restart");
                         }
                         else {

                             playerOneTurn=false;
                             playerOneButton.setDisable(false);
                             playerOneLabel.setText("");
                             playerTwoTurn=true;
                             playerTwoButton.setDisable(false);
                             playerTwoLabel.setText("Your Turn"+playerTwo.getName());
                         }


                     }
                 }

             }
         });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent actionEvent) {
                if(gameStarted)
                {
                    if(playerTwoTurn)
                    {
                        int diceValue= dice.getRollerDiceValue();
                        diceLabel.setText("Dice value : "+diceValue);
                        playerTwo.movePlayer(diceValue);
                        // winning Condition
                        if(playerTwo.isWinner())
                        {
                            diceLabel.setText("Winner is"+playerTwo.getName());
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);

                            playerOneLabel.setText("");
                           playerTwoTurn=false;  //yha
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");
                            //playerTwo.startingPosition();
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }
                        else {
                            playerOneTurn=true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn"+playerOne.getName());
                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true );
                            playerTwoLabel.setText(" ");
                        }


                    }
                }

            }
        });
             startButton.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent actionEvent) {
                     gameStarted=true;
                     diceLabel.setText("Game Started");
                     startButton.setDisable(true);
                     playerOneTurn= true;
                     playerOneLabel.setText("Your Turn "+playerOne.getName());
                     playerOneButton.setDisable(false);
                    playerOne.startingPosition();
                     playerTwoTurn= false;
                     playerTwoLabel.setText("");
                     playerTwoButton.setDisable(true);
                     playerTwo.startingPosition();
                 }
             });

        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel,playerOne.getCoin(),playerTwo.getCoin());



        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeLadder.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}