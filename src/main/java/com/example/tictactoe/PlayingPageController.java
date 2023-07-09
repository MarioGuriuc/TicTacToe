package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PlayingPageController {
    private Stage stage;
    private Scene scene;
    public Button firstPosition;
    public Button secondPosition;
    public Button thirdPosition;
    public Button fourthPosition;
    public Button fifthPosition;
    public Button sixthPosition;
    public Button seventhPosition;
    public Button eighthPosition;
    public Button ninthPosition;
    public ImageView imagePos0;
    public ImageView imagePos1;
    public ImageView imagePos2;
    public ImageView imagePos3;
    public ImageView imagePos4;
    public ImageView imagePos5;
    public ImageView imagePos6;
    public ImageView imagePos7;
    public ImageView imagePos8;
    private final int[] board = new int[9];
    private int turn = 0;
    private int drawChecker = 0;
    public static boolean xWon = false;
    public static boolean oWon = false;
    private final Image imageX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("X.png")));
    private final Image image0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("0.png")));

    private void drawTurnImage(ActionEvent event, int pos) throws IOException {
        switch(pos) {
            case 0 -> {
                if ((turn % 2) == 0) imagePos0.setImage(image0);
                else imagePos0.setImage(imageX);
            }
            case 1 -> {
                if ((turn % 2) == 0) imagePos1.setImage(image0);
                else imagePos1.setImage(imageX);
            }
            case 2 -> {
                if ((turn % 2) == 0) imagePos2.setImage(image0);
                else imagePos2.setImage(imageX);
            }
            case 3 -> {
                if ((turn % 2) == 0) imagePos3.setImage(image0);
                else imagePos3.setImage(imageX);
            }
            case 4 -> {
                if ((turn % 2) == 0) imagePos4.setImage(image0);
                else imagePos4.setImage(imageX);
            }
            case 5 -> {
                if ((turn % 2) == 0) imagePos5.setImage(image0);
                else imagePos5.setImage(imageX);
            }
            case 6 -> {
                if ((turn % 2) == 0) imagePos6.setImage(image0);
                else imagePos6.setImage(imageX);
            }
            case 7 -> {
                if ((turn % 2) == 0) imagePos7.setImage(image0);
                else imagePos7.setImage(imageX);
            }
            case 8 -> {
                if ((turn % 2) == 0) imagePos8.setImage(image0);
                else imagePos8.setImage(imageX);
            }
        }
        boolean winCheck = checkWin();
        if(drawChecker == 8 && !winCheck) {
            playAgainImageHelper.setImage(PlayAgainController.drawImage);
            playAgain(event);
        }
        else if(winCheck) {
            if(oWon) playAgainImageHelper.setImage(PlayAgainController.oWonImage);
            else if(xWon) playAgainImageHelper.setImage(PlayAgainController.xWonImage);
            playAgain(event);
        }
        drawChecker++;
    }
    private boolean checkPos(int pos) {
        return (board[pos] == 1) || (board[pos] == -1);
    }

    public void placeOnFirstPosition(ActionEvent event) throws IOException {
        if(checkPos(0)) return;
        board[0] = (turn % 2) == 0 ? -1 : 1;
        drawTurnImage(event, 0);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnSecondPosition(ActionEvent event) throws IOException {
        if(checkPos(1)) return;
        board[1] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 1);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnThirdPosition(ActionEvent event) throws IOException {
        if(checkPos(2)) return;
        board[2] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 2);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnFourthPosition(ActionEvent event) throws IOException {
        if(checkPos(3)) return;
        board[3] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 3);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnFifthPosition(ActionEvent event) throws IOException {
        if(checkPos(4)) return;
        board[4] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 4);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnSixthPosition(ActionEvent event) throws IOException {
        if(checkPos(5)) return;
        board[5] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 5);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnSeventhPosition(ActionEvent event) throws IOException {
        if(checkPos(6)) return;
        board[6] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 6);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnEighthPosition(ActionEvent event) throws IOException {
        if(checkPos(7)) return;
        board[7] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 7);
        if(turn % 2 == 0) turn++; else turn--;
    }
    public void placeOnNinethPosition(ActionEvent event) throws IOException {
        if(checkPos(8)) return;
        board[8] = (turn == 0) ? -1 : 1;
        drawTurnImage(event, 8);
        if(turn % 2 == 0) turn++; else turn--;
    }
    private boolean checkWin() {
        if((board[0] == board[1] && board[1] == board[2]) && (board[0] == -1 || board[0] == 1)) {
            if(board[0] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[0] == board[3] && board[3] == board[6]) && (board[0] == -1 || board[0] == 1)){
            if(board[0] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[0] == board[4] && board[4] == board[8]) && (board[0] == -1 || board[0] == 1)){
            if(board[0] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[1] == board[4] && board[4] == board[7]) && (board[1] == -1 || board[1] == 1)) {
            if(board[1] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[2] == board[4] && board[4] == board[6]) && (board[2] == -1 || board[2] == 1)) {
            if(board[2] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[2] == board[5] && board[5] == board[8]) && (board[2] == -1 || board[2] == 1)) {
            if(board[2] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[3] == board[4] && board[4] == board[5]) && (board[3] == -1 || board[3] == 1)) {
            if(board[3] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        if((board[6] == board[7] && board[7] == board[8]) && (board[6] == -1 || board[6] == 1)) {
            if(board[6] == -1) oWon = true;
            else xWon = true;
            return true;
        }
        return false;
    }
    public void playAgain(ActionEvent event) throws IOException {
        {
            for(int i = 0; i < 9; i++) {
                board[i] = 0;
            }
            imagePos0.setImage(null);
            imagePos1.setImage(null);
            imagePos2.setImage(null);
            imagePos3.setImage(null);
            imagePos4.setImage(null);
            imagePos5.setImage(null);
            imagePos6.setImage(null);
            imagePos7.setImage(null);
            imagePos8.setImage(null);
            turn = 0;
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playAgainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}