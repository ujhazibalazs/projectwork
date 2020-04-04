package Game;

public class Cell {
    private boolean empty = true;
    private boolean wall = false;

    public boolean getEmpty() {
        return empty;
    }

    public void setEmpty(boolean value) {
        this.empty = value;
    }

    public boolean getWall() {
        return wall;
    }

    public void setWall(boolean value) {
        this.wall = value;
    }
}
