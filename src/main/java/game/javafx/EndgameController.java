package game.javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndgameController implements Initializable {

    private String winnerName;

    @FXML
    private Label winnerLabel;

    @FXML
    private Button backButton;

    @FXML
    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showWinner(ActionEvent actionEvent) {
        Platform.runLater(() -> winnerLabel.setText(winnerName));
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
