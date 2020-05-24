package game;

import lombok.Data;

@Data
public class Figure {
    private int index;
    private String color;
    private int height;
    private int width;
    private boolean captured = false;

    public int getIndex() {
        return index;
    }

    public void setIndex(int value) {
        this.index = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

    //Előre lépés (fel, vagy le? melyik csapat tagja?)

    //Átlósan lépés (balra, jobbra fel, vagy le? melyik csapat tagja?)
}
