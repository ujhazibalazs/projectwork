package Game;

public class Main {

    public static void startGame() {

    }

    //Printing the map of the game out on the console
    public static void printGrid(int height, int width, Cell[][] grid) {
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if(grid[i][j].getEmpty() && !grid[i][j].getWall())
                    System.out.print("[O]");
                else if(grid[i][j].getEmpty() && grid[i][j].getWall())
                    System.out.print("[/]");
                else System.out.print("[X]");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        //Height, and width of the grid
        int height = 6;
        int width = 7;

        Cell[][] grid = new Cell[height][width];

        //Instantiating each element of the grid
        for (int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell();
            }
        }

        //Putting walls in the grid
        grid[2][4].setWall(true);
        grid[3][2].setWall(true);

        printGrid(height, width, grid);

        Figure[] figures = new Figure[width*2];

        //Instantiating each element of the figures
        for (int i=0; i<figures.length; i++) {
            figures[i] = new Figure();
            figures[i].setIndex(i);
            if (i<width) figures[i].setColor("red");
            else figures[i].setColor("blue");
        }

        //Checking information about the figures
        for (int i=0; i<figures.length; i++) {
            System.out.println(figures[i].getIndex() + ". figure: " + figures[i].getColor());
        }

        //Putting the figures on the grid
    }
}
