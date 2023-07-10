package com.example.tictactoe;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class vsComputerController {
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
    public ImageView turnImage;
    public ImageView winPosition012;
    public ImageView winPosition345;
    public ImageView winPosition678;
    public ImageView winPosition036;
    public ImageView winPosition147;
    public ImageView winPosition258;
    public ImageView winPosition048;
    public ImageView winPosition246;
    private final int[] board = new int[9];
    private int turn = 0;
    private int drawChecker = 0;
    public static boolean xWon = false;
    public static boolean oWon = false;
    Random computerChoice = new Random();
    private final Image imageX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("X.png")));
    private final Image image0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("0.png")));

    public void setDisableButtons(boolean value) {
        firstPosition.setDisable(value);
        secondPosition.setDisable(value);
        thirdPosition.setDisable(value);
        fourthPosition.setDisable(value);
        fifthPosition.setDisable(value);
        sixthPosition.setDisable(value);
        seventhPosition.setDisable(value);
        eighthPosition.setDisable(value);
        ninthPosition.setDisable(value);
    }

    public void setWinPositionImage(int a, int b, int c) {
        if(a == 0 && b == 1 && c == 2) {
            winPosition012.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 3 && b == 4 && c == 5) {
            winPosition345.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 6 && b == 7 && c == 8) {
            winPosition678.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 0 && b == 3 && c == 6) {
            winPosition036.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 1 && b == 4 && c == 7) {
            winPosition147.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 2 && b == 5 && c == 8) {
            winPosition258.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 0 && b == 4 && c == 8) {
            winPosition048.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
        else if(a == 2 && b == 4 && c == 6) {
            winPosition246.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
        }
    }

    private void drawTurnImage(ActionEvent event, int pos) {
        if(turn % 2 == 0) {
            turnImage.setImage(image0);
        }
        else {
            turnImage.setImage(imageX);
        }
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
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event1 -> {
            try {
                setDisableButtons(false);
                playAgain(event);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        boolean winCheck = checkWin();
        if(drawChecker == 8 && !winCheck) {
            playAgainImageHelper.setImage(PlayAgainController.drawImage);
            setDisableButtons(true);
            pause.play();
        }
        else if(winCheck) {
            if(oWon) playAgainImageHelper.setImage(PlayAgainController.oWonImage);
            else if(xWon) playAgainImageHelper.setImage(PlayAgainController.xWonImage);
            setDisableButtons(true);
            pause.play();
        }
        drawChecker++;
    }

    private boolean checkPos(int pos) {
        return (board[pos] == 1) || (board[pos] == -1);
    }

    public void placeOnPosition(ActionEvent event, int pos) {
        if(checkPos(pos)) return;
        board[pos] = (turn % 2) == 0 ? -1 : 1;
        drawTurnImage(event, pos);
        if(turn % 2 == 0) turn++; else turn--;
        computerPlace(event);
    }

    public void computerPlace(ActionEvent event) {
        if(drawChecker == 9 || checkWin()) return;
        boolean placed = false;
        while(!placed) {
            int position = computerChoice.nextInt(9);
            placed = true;
            if(checkPos(position)) placed = false;
            else {
                board[position] = (turn % 2) == 0 ? -1 : 1;
                drawTurnImage(event, position);
                if(turn % 2 == 0) turn++; else turn--;
            }
        }
    }

    public void placeOnFirstPosition(ActionEvent event) {
        placeOnPosition(event, 0);
    }
    public void placeOnSecondPosition(ActionEvent event) {
        placeOnPosition(event, 1);
    }
    public void placeOnThirdPosition(ActionEvent event) {
        placeOnPosition(event, 2);
    }
    public void placeOnFourthPosition(ActionEvent event) {
        placeOnPosition(event, 3);
    }
    public void placeOnFifthPosition(ActionEvent event) {
        placeOnPosition(event, 4);
    }
    public void placeOnSixthPosition(ActionEvent event) {
        placeOnPosition(event, 5);
    }
    public void placeOnSeventhPosition(ActionEvent event) {
        placeOnPosition(event, 6);
    }
    public void placeOnEighthPosition(ActionEvent event) {
        placeOnPosition(event, 7);
    }
    public void placeOnNinethPosition(ActionEvent event) {
        placeOnPosition(event, 8);
    }

    private boolean checkWin() {
        if((board[0] == board[1] && board[1] == board[2]) && (board[0] == -1 || board[0] == 1)) {
            if(board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 1, 2);
            return true;
        }
        if((board[0] == board[3] && board[3] == board[6]) && (board[0] == -1 || board[0] == 1)){
            if(board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 3, 6);
            return true;
        }
        if((board[0] == board[4] && board[4] == board[8]) && (board[0] == -1 || board[0] == 1)){
            if(board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 4, 8);
            return true;
        }
        if((board[1] == board[4] && board[4] == board[7]) && (board[1] == -1 || board[1] == 1)) {
            if(board[1] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(1, 4, 7);
            return true;
        }
        if((board[2] == board[4] && board[4] == board[6]) && (board[2] == -1 || board[2] == 1)) {
            if(board[2] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(2, 4, 6);
            return true;
        }
        if((board[2] == board[5] && board[5] == board[8]) && (board[2] == -1 || board[2] == 1)) {
            if(board[2] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(2, 5, 8);
            return true;
        }
        if((board[3] == board[4] && board[4] == board[5]) && (board[3] == -1 || board[3] == 1)) {
            if(board[3] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(3, 4, 5);
            return true;
        }
        if((board[6] == board[7] && board[7] == board[8]) && (board[6] == -1 || board[6] == 1)) {
            if(board[6] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(6, 7, 8);
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
            drawChecker = 0;
            xWon = false;
            oWon = false;
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playAgainPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}