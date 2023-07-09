package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayAgainController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    public Button replayButton;
    public Button closeButton;
    public ImageView winningImage;
    static Image xWonImage = new Image(Objects.requireNonNull(PlayAgainController.class.getResourceAsStream("xWon.png")));
    static Image oWonImage = new Image(Objects.requireNonNull(PlayAgainController.class.getResourceAsStream("oWon.png")));
    static Image drawImage = new Image(Objects.requireNonNull(PlayAgainController.class.getResourceAsStream("draw.png")));

    public void replayButtonAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playingPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        PlayingPageController.xWon = false;
        PlayingPageController.oWon = false;
    }
    public void closeButtonAction(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        winningImage.setImage(playAgainImageHelper.getImage());
    }
}
