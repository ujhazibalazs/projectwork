package Game;

public class Moves {

    public static void MoveForward(Figure figure, int gridHeight, Cell[][] grid) {
        if (figure.getColor().equals("red")) {
            if (figure.getHeight()+1 < gridHeight) {
                if (grid[figure.getHeight()+1][figure.getWidth()].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()].getWall()) {
                    figure.setHeight(figure.getHeight() + 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()-1][figure.getWidth()].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
        else {
            if (figure.getHeight()-1 > -1) {
                if (grid[figure.getHeight()-1][figure.getWidth()].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()].getWall()) {
                    figure.setHeight(figure.getHeight() - 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()+1][figure.getWidth()].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
    }

    public static void MoveDiagonallyLeft(Figure figure, int gridHeight, int gridWidth, Cell[][] grid) {
        if (figure.getColor().equals("red")) {
            if (figure.getHeight()+1 < gridHeight && figure.getWidth()+1 < gridWidth) {
                if (grid[figure.getHeight()+1][figure.getWidth()+1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()+1].getWall()) {
                    figure.setHeight(figure.getHeight() + 1);
                    figure.setWidth(figure.getWidth() + 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()-1][figure.getWidth()-1].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
        else {
            if (figure.getHeight()-1 > -1 && figure.getWidth()-1 > -1) {
                if (grid[figure.getHeight()-1][figure.getWidth()-1].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()-1].getWall()) {
                    figure.setHeight(figure.getHeight() - 1);
                    figure.setWidth(figure.getWidth() - 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()+1][figure.getWidth()+1].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
    }

    public static void MoveDiagonallyRight(Figure figure, int gridHeight, int gridWidth, Cell[][] grid) {
        if (figure.getColor() == "red") {
            if (figure.getHeight()+1 < gridHeight && figure.getWidth()-1 > -1) {
                if (grid[figure.getHeight()+1][figure.getWidth()-1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()-1].getWall()) {
                    figure.setHeight(figure.getHeight() + 1);
                    figure.setWidth(figure.getWidth() - 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()-1][figure.getWidth()+1].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
        else {
            if (figure.getHeight()-1 > -1 && figure.getWidth()+1 > gridWidth) {
                if (grid[figure.getHeight()-1][figure.getWidth()+1].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()+1].getWall()) {
                    figure.setHeight(figure.getHeight() - 1);
                    figure.setWidth(figure.getWidth() + 1);
                    grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                    grid[figure.getHeight()+1][figure.getWidth()-1].setEmpty(true);
                }
                else {
                    System.out.println("Nem üres a mező, vagy fal.");
                }
            }
            else {
                System.out.println("pálya végéhez ért");
            }
        }
    }
}
