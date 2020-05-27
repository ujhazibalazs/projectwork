package game.logic;

import java.util.Scanner;

/**
 * Class holding methods for running the game without graphical user interface.
 */
public class Game {

    /**
     * The height of the columns.
     */
    public static final int GRIDHEIGHT = 6;

    /**
     * The width of the rows.
     */
    public static final int GRIDWIDTH = 7;

    /**
     * The name of player one.
     */
    public static String player1;

    /**
     * The name of player two.
     */
    public static String player2;

    /**
     * Initializes a grid made of Cell objects.
     * @return the initialized grid
     */
    public static Cell[][] createGrid() {
        Cell[][] grid = new Cell[GRIDHEIGHT][GRIDWIDTH];
        for (int i = 0; i < GRIDHEIGHT; i++) {
            for (int j = 0; j < GRIDWIDTH; j++) {
                grid[i][j] = new Cell();
            }
        }
        return  grid;
    }

    /**
     * Initializes the figures with default values and sets up the grid.
     * @param grid the grid to be set up
     * @return an array of initialized figures
     */
    public static Figure[] createFigures(Cell[][] grid) {
        Figure[] figures = new Figure[GRIDWIDTH*2];
        int currWidth = 0;
        for (int i = 0; i < figures.length; i++) {
            figures[i] = new Figure();
            figures[i].setIndex(i);
            if (currWidth == GRIDWIDTH) {
                currWidth = 0;
            }
            if (i < GRIDWIDTH) {
                figures[i].setColor("red");
                figures[i].setHeight(0);
                figures[i].setWidth(currWidth);
                grid[0][currWidth].setEmpty(false);
            } else {
                figures[i].setColor("blue");
                figures[i].setHeight(GRIDHEIGHT - 1);
                figures[i].setWidth(currWidth);
                grid[GRIDHEIGHT - 1][currWidth].setEmpty(false);
            }
            currWidth++;
        }
        return figures;
    }

    /**
     * Prints out the current state of the game.
     * @param figures the array of figures to be printed out
     * @param grid the grid to be printed out
     */
    public static void printGrid(Figure[] figures, Cell[][] grid) {
        System.out.println();
        for (int i = 0; i < GRIDHEIGHT; i++) {
            for (int j = 0; j < GRIDWIDTH; j++) {
                if (grid[i][j].isEmpty() && !grid[i][j].isWall()) {
                    System.out.print("[O]");
                } else if (grid[i][j].isEmpty() && grid[i][j].isWall()) {
                    System.out.print("[/]");
                } else {
                    for (int k = 0; k < figures.length; k++) {
                        if (!figures[k].isCaptured()) {
                            if (figures[k].getHeight() == i && figures[k].getWidth() == j) {
                                if (figures[k].getColor().equals("red")) {
                                    System.out.print("[R]");
                                } else {
                                    System.out.print("[B]");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Asks the users for their names through the console.
     */
    private static void getPlayerNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First player's name: ");
        player1 = scanner.nextLine();
        System.out.println("Welcome, " + player1 + "!");
        System.out.print("Second player's name: ");
        player2 = scanner.nextLine();
        while (player1.equals(player2)) {
            System.out.print("Second player's name: ");
            player2 = scanner.nextLine();
        }
        System.out.println("Welcome, " + player2 + "!");
    }

    /**
     * Asks the user for the index of the figure they wish to move.
     * @param player1Turn the player who currently has to move
     * @return the chosen figure's index number
     */
    private static int getChosenFigureIndex(boolean player1Turn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which figure do you want to move? [1 - 7]");
        int figureIndex = scanner.nextInt();
        while (figureIndex < 1 || figureIndex > 7) {
            System.out.println("Which figure do you want to move? [1 - 7]");
            figureIndex = scanner.nextInt();
        }
        if (player1Turn) {
            figureIndex -= 1;
        } else {
            figureIndex += 6;
        }
        return figureIndex;
    }

    /**
     * Asks the user for the type of move they wish to perform.
     * @return the chosen type of move
     */
    private static String getChosenMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What move would you like to perform? [f, dl, dr]");
        String move = scanner.nextLine();
        while (!move.equals("f") && !move.equals("dl") && !move.equals("dr")) {
            System.out.println("What move would you like to perform? [f, dl, dr]");
            move = scanner.nextLine();
        }
        return move;
    }

    /**
     * Starts the game in no graphical user interface mode.
     */
    public static void startGame() {
        Cell[][] grid = createGrid();
        grid[2][4].setWall(true);
        grid[3][2].setWall(true);
        Figure[] figures = createFigures(grid);
        getPlayerNames();
        boolean player1Turn = true;
        boolean moveSuccessful = false;
        int figureIndex;
        String move;
        while (true) {
            if (player1Turn) {
                System.out.println(player1 + "'s turn");
            } else {
                System.out.println(player2 + "'s turn");
            }
            printGrid(figures, grid);
            while (!moveSuccessful) {
                figureIndex = getChosenFigureIndex(player1Turn);
                move = getChosenMove();
                switch (move) {
                    case "f":
                        moveSuccessful = Moves.moveForward(figures[figureIndex], grid);
                        break;
                    case "dl":
                        moveSuccessful = Moves.moveDiagonallyLeft(figures[figureIndex], grid, figures);
                        break;
                    case "dr":
                        moveSuccessful = Moves.moveDiagonallyRight(figures[figureIndex], grid, figures);
                        break;
                    default:
                        break;
                }
            }
            printGrid(figures, grid);
            moveSuccessful = false;
            player1Turn = !player1Turn;
            if (!Moves.canPlayerMove(player1Turn, figures, grid)) {
                if (player1Turn) {
                    System.out.println(player2 + " won.");
                } else {
                    System.out.println(player1 + " won.");
                }
                break;
            }
        }
    }

}
