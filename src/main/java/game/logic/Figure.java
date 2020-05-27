package game.logic;

import lombok.Data;

/**
 * Class representing a figure in the game.
 */
@Data
public class Figure {
    private int index;
    private String color;
    private int height;
    private int width;
    private boolean captured = false;
}
