package game.javafx;

import game.logic.Cell;
import game.logic.Figure;
import game.logic.Game;
import game.logic.Moves;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

import org.tinylog.Logger;

public class GameController implements Initializable {

    public boolean player1Turn = true;
    public boolean moveSuccessful = false;

    public int gridHeight = 6;
    public int gridWidth = 7;

    public Cell[][] grid = Game.createGrid();
    public Figure[] figures = Game.createFigures(grid);
    public Circle[] circles = new Circle[gridWidth * 2];
    public Figure selectedFigure = new Figure();

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
    public void forwardButtonPress(ActionEvent actionEvent) {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveForward(selectedFigure, gridHeight, grid);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.debug("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveForward(selectedFigure, gridHeight, grid);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.debug("Select a figure.");
            }
        }

    }

    @FXML
    public void leftButtonPress(ActionEvent actionEvent) {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveDiagonallyLeft(selectedFigure, gridHeight, gridWidth, grid, figures);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.debug("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveDiagonallyLeft(selectedFigure, gridHeight, gridWidth, grid, figures);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.debug("Select a figure.");
            }
        }
    }

    @FXML
    public void rightButtonPress(ActionEvent actionEvent) {
        if (player1Turn) {
            if (selectedFigure.getColor().equals("red")) {
                moveSuccessful = Moves.moveDiagonallyRight(selectedFigure, gridHeight, gridWidth, grid, figures);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Select a figure.");
                Logger.debug("Select a figure.");
            }
        } else {
            if (selectedFigure.getColor().equals("blue")) {
                moveSuccessful = Moves.moveDiagonallyRight(selectedFigure, gridHeight, gridWidth, grid, figures);
                Game.printGrid(figures, grid);
                if (moveSuccessful) {
                    player1Turn = !player1Turn;
                    moveSuccessful = false;
                    refreshUI();
                } else {
                    infoLabel.setText("Illegal move.");
                    Logger.debug("Illegal move.");
                }
            } else {
                infoLabel.setText("Illegal move.");
                Logger.debug("Select a figure.");
            }
        }
    }

    public void refreshUI () {

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
                        System.out.println(figure);
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

        if (!Moves.canPlayerMove(player1Turn, figures, gridHeight, gridWidth, grid)) {
            if (player1Turn) {
                Logger.debug(Game.player2 + " nyert.");
            } else {
                Logger.debug(Game.player1 + " nyert.");
            }
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
        Game.printGrid(figures, grid);
        turnLabelSet(player1Turn);
        selectedFigure.setColor("blue");
    }
}
