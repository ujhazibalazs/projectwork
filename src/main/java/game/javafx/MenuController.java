package game.javafx;

import game.logic.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML
    private Button startButton1;

    @FXML
    private void startButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage)startButton1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/naming.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
