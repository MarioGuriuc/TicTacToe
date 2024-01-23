package com.example.tictactoe;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class PlayingPageController {
    public static boolean xWon = false;
    public static boolean oWon = false;
    private final int[] board = new int[9];
    private final Image imageX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("X.png")));
    private final Image image0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("0.png")));
    public Button firstPosition, secondPosition, thirdPosition, fourthPosition, fifthPosition, sixthPosition, seventhPosition, eighthPosition, ninthPosition;
    public Button[] positions = new Button[9];
    public ImageView imagePos0, imagePos1, imagePos2, imagePos3, imagePos4, imagePos5, imagePos6, imagePos7, imagePos8;
    public ImageView[] imagePos = new ImageView[9];
    public ImageView turnImage;
    public ImageView winPosition012, winPosition345, winPosition678, winPosition036, winPosition147, winPosition258, winPosition048, winPosition246;
    public ImageView[] winPositions = new ImageView[8];
    private int turn = 0;
    private int drawChecker = 0;
    Random computerChoice = new Random();

    @FXML
    public void initialize() {
        positions[0] = firstPosition;
        positions[1] = secondPosition;
        positions[2] = thirdPosition;
        positions[3] = fourthPosition;
        positions[4] = fifthPosition;
        positions[5] = sixthPosition;
        positions[6] = seventhPosition;
        positions[7] = eighthPosition;
        positions[8] = ninthPosition;
        imagePos[0] = imagePos0;
        imagePos[1] = imagePos1;
        imagePos[2] = imagePos2;
        imagePos[3] = imagePos3;
        imagePos[4] = imagePos4;
        imagePos[5] = imagePos5;
        imagePos[6] = imagePos6;
        imagePos[7] = imagePos7;
        imagePos[8] = imagePos8;
        winPositions[0] = winPosition012;
        winPositions[1] = winPosition345;
        winPositions[2] = winPosition678;
        winPositions[3] = winPosition036;
        winPositions[4] = winPosition147;
        winPositions[5] = winPosition258;
        winPositions[6] = winPosition048;
        winPositions[7] = winPosition246;
    }

    public void setDisableButtons(boolean value) {
        for (int i = 0; i < 9; i++) {
            positions[i].setDisable(value);
        }
    }

    public void setWinPositionImage(int pos) {
        winPositions[pos].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("line1.png"))));
    }

    public void setWinPositionImage(int a, int b, int c) {
        if (a == 0 && b == 1 && c == 2) {
            setWinPositionImage(0);
        } else if (a == 3 && b == 4 && c == 5) {
            setWinPositionImage(1);
        } else if (a == 6 && b == 7 && c == 8) {
            setWinPositionImage(2);
        } else if (a == 0 && b == 3 && c == 6) {
            setWinPositionImage(3);
        } else if (a == 1 && b == 4 && c == 7) {
            setWinPositionImage(4);
        } else if (a == 2 && b == 5 && c == 8) {
            setWinPositionImage(5);
        } else if (a == 0 && b == 4 && c == 8) {
            setWinPositionImage(6);
        } else if (a == 2 && b == 4 && c == 6) {
            setWinPositionImage(7);
        }
    }

    private void drawTurnImage(ActionEvent event, int pos) {
        if (turn % 2 == 0) {
            turnImage.setImage(image0);
        } else {
            turnImage.setImage(imageX);
        }
        switch (pos) {
            case 0 -> {
                if ((turn % 2 == 0)) imagePos[0].setImage(image0);
                else imagePos[0].setImage(imageX);
            }
            case 1 -> {
                if ((turn % 2 == 0)) imagePos[1].setImage(image0);
                else imagePos[1].setImage(imageX);
            }
            case 2 -> {
                if ((turn % 2 == 0)) imagePos[2].setImage(image0);
                else imagePos[2].setImage(imageX);
            }
            case 3 -> {
                if ((turn % 2 == 0)) imagePos[3].setImage(image0);
                else imagePos[3].setImage(imageX);
            }
            case 4 -> {
                if ((turn % 2 == 0)) imagePos[4].setImage(image0);
                else imagePos[4].setImage(imageX);
            }
            case 5 -> {
                if ((turn % 2 == 0)) imagePos[5].setImage(image0);
                else imagePos[5].setImage(imageX);
            }
            case 6 -> {
                if ((turn % 2 == 0)) imagePos[6].setImage(image0);
                else imagePos[6].setImage(imageX);
            }
            case 7 -> {
                if ((turn % 2 == 0)) imagePos[7].setImage(image0);
                else imagePos[7].setImage(imageX);
            }
            case 8 -> {
                if ((turn % 2 == 0)) imagePos[8].setImage(image0);
                else imagePos[8].setImage(imageX);
            }
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event1 -> {
            try {
                setDisableButtons(false);
                playAgain(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        boolean winCheck = checkWin();
        if (drawChecker == 8 && !winCheck) {
            playAgainImageHelper.setImage(PlayAgainController.drawImage);
            setDisableButtons(true);
            pause.play();
        } else if (winCheck) {
            if (oWon) playAgainImageHelper.setImage(PlayAgainController.oWonImage);
            else if (xWon) playAgainImageHelper.setImage(PlayAgainController.xWonImage);
            setDisableButtons(true);
            pause.play();
        }
        drawChecker++;
    }

    private boolean checkPos(int pos) {
        return (board[pos] == 1) || (board[pos] == -1);
    }

    private void placeOnPosition(ActionEvent event, int pos) {
        if (checkPos(pos)) return;
        board[pos] = (turn % 2) == 0 ? -1 : 1;
        drawTurnImage(event, pos);
        if (turn % 2 == 0) turn++;
        else turn--;
        if (MainPageController.computer) computerPlace(event);
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

    public void placeOnNinthPosition(ActionEvent event) {
        placeOnPosition(event, 8);
    }

    private boolean checkWin() {
        if ((board[0] == board[1] && board[1] == board[2]) && (board[0] == -1 || board[0] == 1)) {
            if (board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 1, 2);
            return true;
        }
        if ((board[0] == board[3] && board[3] == board[6]) && (board[0] == -1 || board[0] == 1)) {
            if (board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 3, 6);
            return true;
        }
        if ((board[0] == board[4] && board[4] == board[8]) && (board[0] == -1 || board[0] == 1)) {
            if (board[0] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(0, 4, 8);
            return true;
        }
        if ((board[1] == board[4] && board[4] == board[7]) && (board[1] == -1 || board[1] == 1)) {
            if (board[1] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(1, 4, 7);
            return true;
        }
        if ((board[2] == board[4] && board[4] == board[6]) && (board[2] == -1 || board[2] == 1)) {
            if (board[2] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(2, 4, 6);
            return true;
        }
        if ((board[2] == board[5] && board[5] == board[8]) && (board[2] == -1 || board[2] == 1)) {
            if (board[2] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(2, 5, 8);
            return true;
        }
        if ((board[3] == board[4] && board[4] == board[5]) && (board[3] == -1 || board[3] == 1)) {
            if (board[3] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(3, 4, 5);
            return true;
        }
        if ((board[6] == board[7] && board[7] == board[8]) && (board[6] == -1 || board[6] == 1)) {
            if (board[6] == -1) oWon = true;
            else xWon = true;
            setWinPositionImage(6, 7, 8);
            return true;
        }
        return false;
    }

    public void computerPlace(ActionEvent event) {
        if (drawChecker == 9 || checkWin()) return;
        boolean placed = false;
        while (!placed) {
            int position = computerChoice.nextInt(9);
            placed = true;
            if (checkPos(position)) placed = false;
            else {
                board[position] = (turn % 2) == 0 ? -1 : 1;
                drawTurnImage(event, position);
                if (turn % 2 == 0) turn++;
                else turn--;
            }
        }
    }

    public void playAgain(ActionEvent event) throws IOException {
        for (int i = 0; i < 9; i++) {
            board[i] = 0;
        }
        for (int i = 0; i < 9; i++) {
            imagePos[i].setImage(null);
        }
        turn = 0;
        drawChecker = 0;
        xWon = false;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playAgainPage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}