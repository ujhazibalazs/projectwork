package game.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovesTest {

    @Test
    void getFigureByPosition() {
        Cell[][] grid = Game.createGrid();
        Figure[] figures = Game.createFigures(grid);
        Figure figure = new Figure();

        for (int i = 0; i < grid[0].length; i++) {
            figure = Moves.getFigureByPosition(figures, 0, i);
            assertEquals("red", figure.getColor());
        }
        for (int i = 0; i < grid[0].length; i++) {
            figure = Moves.getFigureByPosition(figures, 5, i);
            assertEquals("blue", figure.getColor());
        }
    }

    @Test
    void canPlayerMove() {
    }

    @Test
    void canMoveForward() {
    }

    @Test
    void canMoveDiagonallyLeft() {
    }

    @Test
    void canMoveDiagonallyRight() {
    }

    @Test
    void moveForward() {
    }

    @Test
    void moveDiagonallyLeft() {
    }

    @Test
    void moveDiagonallyRight() {
    }
}