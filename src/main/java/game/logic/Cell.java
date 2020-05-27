package game.logic;

import lombok.Data;

/**
 * Class representing a cell of a grid.
 */
@Data
public class Cell {
    private boolean empty = true;
    private boolean wall = false;
}
