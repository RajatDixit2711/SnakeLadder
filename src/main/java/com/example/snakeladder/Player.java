package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Collection;
import java.util.concurrent.TransferQueue;

public class Player {
    private Circle coin;



   private String name;
   private int currentPosition;

  private   static Board gameBoard= new Board();

    public Player (int tileSize, Color coinColor,String playerName)
    {
        coin= new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition=0;
       movePlayer(1);

        name= playerName;

    }
    public void movePlayer(int diceValue)
    {
        if(currentPosition+diceValue<=100)
        {
            currentPosition+=diceValue;

            TranslateTransition secondMove= null,firstMove= translateAnimation(diceValue);
            int newPosition= gameBoard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1 )
            {
                currentPosition=newPosition;
               secondMove=translateAnimation(6);
            }
            if(secondMove==null)
            {
                firstMove.play();
            }
            else {
                SequentialTransition sequentialTransition=new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(1000)),secondMove);
                sequentialTransition.play();
            }
        }

        /*    int x= gameBoard.getXCordinate(currentPosition);
            int y= gameBoard.getYCordinate(currentPosition);
        coin.setTranslateX(x);
        coin.setTranslateY(y);*/


    }
    private TranslateTransition translateAnimation(int diceValue)
    {
        TranslateTransition animation = new TranslateTransition(Duration.millis( diceValue*200),coin);
        animation.setToX(gameBoard.getXCordinate(currentPosition));
        animation.setToY(gameBoard.getYCordinate(currentPosition));
        animation.setAutoReverse(false);
        return  animation;

    }
    public void startingPosition()
    {
        currentPosition=0;
        movePlayer(1);
    }
    boolean isWinner()
    {
        if(currentPosition==100)
        {
            return true;
        }
        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition()
    {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
