package game.javafx;

import game.logic.Cell;
import game.logic.Figure;
import game.logic.Game;
import game.logic.Moves;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.inject.Inject;

public class GameController implements Initializable {

    public boolean player1Turn = true;
    public boolean moveSuccessful = false;

    public Cell[][] grid = Game.createGrid();
    public Figure[] figures = Game.createFigures(grid);
    public Circle[] circles = new Circle[Game.GRIDWIDTH * 2];
    public Figure selectedFigure = new Figure();

    private String winnerName;

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private Label p1nameLabel;

    @FXML
    private Label p2nameLabel;

    @FXML
    private Label turnLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label infoLabel;

    @FXML
    private Button forwardButton;

    @FXML
    public void onClickCircle(MouseEvent mouseEvent) {
        Node node = mouseEvent.getPickResult().getIntersectedNode();
        int width = GridPane.getColumnIndex(node);
        int height = GridPane.getRowIndex(node);
        Figure figure = Moves.getFigureByPosition(figures, height, width);
        if (player1Turn) {
            if (figure.getColor().equals("red")) {
                selectedFigure = figure;
                infoLabel.setText("Select a button to move.");
            } else {
                infoLabel.setText("Select a red figure.");
            }
        } else {
            if (figure.getColor().equals("blue")) {
                selectedFigure = figure;
                infoLabel.setText("Select a button to move.");
            } else {
                infoLabel.setText("Select a blue figure.");
            }
        }
    }

    @FXML
    public void forwardButtonPress(ActionEvent actionEvent) throws IOException {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveForward(selectedFigure, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.info("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveForward(selectedFigure, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.info("Select a figure.");
            }
        }

    }

    @FXML
    public void leftButtonPress(ActionEvent actionEvent) throws IOException {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveDiagonallyLeft(selectedFigure, grid, figures);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.info("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveDiagonallyLeft(selectedFigure, grid, figures);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.info("Select a figure.");
            }
        }
    }

    @FXML
    public void rightButtonPress(ActionEvent actionEvent) throws IOException {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveDiagonallyRight(selectedFigure, grid, figures);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.info("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveDiagonallyRight(selectedFigure, grid, figures);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.info("Illegal move.");
                }
            } else {
                infoLabel.setText("Illegal move.");
                Logger.info("Select a figure.");
            }
        }
    }

    public void refreshUI() throws IOException {

        turnLabelSet(player1Turn);
        infoLabel.setText("Select a figure.");
        gridPane.getChildren().clear();
        gridPane.setStyle("-fx-background-color: white");

        for (int i = 0; i < figures.length; i++) {
            if (!figures[i].isCaptured()) {
                circles[i] = new Circle();
                circles[i].setRadius(17);
                circles[i].setStroke(Color.BLACK);
                circles[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node node = mouseEvent.getPickResult().getIntersectedNode();
                        int width = GridPane.getColumnIndex(node);
                        int height = GridPane.getRowIndex(node);

                        Figure figure = Moves.getFigureByPosition(figures, height, width);
                        if (player1Turn) {
                            if (figure.getColor().equals("red")) {
                                selectedFigure = figure;
                                infoLabel.setText("Select a button to move.");
                            } else {
                                infoLabel.setText("Select a red figure.");
                            }
                        } else {
                            if (figure.getColor().equals("blue")) {
                                selectedFigure = figure;
                                infoLabel.setText("Select a button to move.");
                            } else {
                                infoLabel.setText("Select a blue figure.");
                            }
                        }
                    }
                });
                GridPane.setHalignment(circles[i], HPos.CENTER);
                if (figures[i].getColor().equals("red")) {
                    circles[i].setFill(Color.RED);
                } else {
                    circles[i].setFill(Color.DODGERBLUE);
                }
                int height = figures[i].getHeight();
                int width = figures[i].getWidth();
                gridPane.add(circles[i], width, height);
            }
        }

        Pane wall1 = new Pane();
        wall1.setStyle("-fx-background-color: gray");
        Pane wall2 = new Pane();
        wall2.setStyle("-fx-background-color: gray");

        gridPane.add(wall1, 4, 2);
        gridPane.add(wall2, 2, 3);

        if (!Moves.canPlayerMove(player1Turn, figures, grid)) {
            if (player1Turn) {
                Logger.info(Game.player2 + " won.");
                winnerName = Game.player2;
            } else {
                Logger.info(Game.player1 + " won.");
                winnerName = Game.player1;
            }

            Stage stage = (Stage)forwardButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/endgame.fxml"));
            Parent root = loader.load();

            EndgameController endgameController = loader.getController();
            endgameController.setWinnerName(winnerName);
            Logger.debug("winner name: " + winnerName);

            stage.setScene(new Scene(root));
            stage.show();
            /*
            fxmlLoader.setLocation(getClass().getResource("/fxml/endgame.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<EndgameController>getController().setWinnerName(winnerName);
            Stage stage = (Stage)forwardButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

             */
        }

    }

    public void turnLabelSet(boolean player1Turn) {
        if (player1Turn) {
            turnLabel.setText(Game.player1);
        } else {
            turnLabel.setText(Game.player2);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        p1nameLabel.setText(Game.player1);
        p2nameLabel.setText(Game.player2);
        grid[2][4].setWall(true);
        grid[3][2].setWall(true);
        turnLabelSet(player1Turn);
        selectedFigure.setColor("blue");
    }

}
