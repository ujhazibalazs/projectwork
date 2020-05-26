package game.javafx;

import game.logic.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NamingController implements Initializable {

    @FXML
    private TextField p1name;

    @FXML
    private TextField p2name;

    @FXML
    private Label sameNameLabel;

    @FXML
    private Button startButton;

    @FXML
    private void startButtonAction(ActionEvent event) throws Exception {
        if (!isSameName() && !isEmptyName()) {
            Game.player1 = p1name.getText();
            Game.player2 = p2name.getText();
            Stage stage = (Stage)startButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } else if (isEmptyName()) {
            sameNameLabel.setText("The names cannot be empty.");
        } else {
            sameNameLabel.setText("The names cannot match.");
        }
    }

    private boolean isSameName() {
        if (p1name.getText().equals(p2name.getText())) {
            return true;
        }
        return false;
    }

    private boolean isEmptyName() {
        //TODO NAME CANNOT BE SPACE
        if (p1name.getText().equals("") || p2name.getText().equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
