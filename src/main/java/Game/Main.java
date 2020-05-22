package Game;

public class Main {

    public static void startGame() {

    }

    //Printing the map of the game out on the console
    public static void printGrid(Figure[] figures, int height, int width, Cell[][] grid) {
        System.out.println();
        int figindex = 0;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if(grid[i][j].getEmpty() && !grid[i][j].getWall())
                    System.out.print("[O]");
                else if(grid[i][j].getEmpty() && grid[i][j].getWall())
                    System.out.print("[/]");
                else {
                    for (int k = 0; k < figures.length; k++) {
                        if (figures[k].getHeight() == i && figures[k].getWidth() == j) {
                            if (figures[k].getColor() == "red") {
                                System.out.print("[R]");
                            }
                            else {
                                System.out.print("[B]");
                            }
                        }
                    }
                }
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell();
            }
        }

        //Putting walls in the grid
        grid[2][4].setWall(true);
        grid[3][2].setWall(true);

        Figure[] figures = new Figure[width*2];

        //Instantiating each element of the figures
        //Setting the width of the figures, making taken cells not empty
        int currwidth = 0;
        for (int i = 0; i < figures.length; i++) {
            figures[i] = new Figure();
            figures[i].setIndex(i);
            if (currwidth == width) {
                currwidth = 0;
            }
            if (i < width) {
                figures[i].setColor("red");
                figures[i].setHeight(0);
                figures[i].setWidth(currwidth);
                grid[0][currwidth].setEmpty(false);
                currwidth++;
            }
            else {
                figures[i].setColor("blue");
                figures[i].setHeight(height-1);
                figures[i].setWidth(currwidth);
                grid[height-1][currwidth].setEmpty(false);
                currwidth++;
            }
        }

        //Checking information about the figures
        for (int i = 0; i < figures.length; i++) {
            System.out.println(figures[i].getIndex() + ". figure: " + figures[i].getColor() + " Height:" + figures[i].getHeight() + " Width: " + figures[i].getWidth());
        }

        printGrid(figures, height, width, grid);

        Moves.MoveDiagonallyLeft(figures[0], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[0], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[0], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[8], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[9], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[11], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[9], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[0], height, width, grid);
        Moves.MoveForward(figures[0], height, grid);
        Moves.MoveForward(figures[6], height, grid);
        Moves.MoveDiagonallyRight(figures[6], height, width, grid);
        Moves.MoveDiagonallyRight(figures[6], height, width, grid);
        Moves.MoveDiagonallyLeft(figures[6], height, width, grid);

        printGrid(figures, height, width, grid);

        //Putting the figures on the grid
    }
}
