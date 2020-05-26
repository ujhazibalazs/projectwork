package game.logic;

import java.util.Scanner;

public class Game {

    private static final int GRIDHEIGHT = 6;
    private static final int GRIDWIDTH = 7;

    public static String player1;
    public static String player2;

    public static Cell[][] createGrid() {
        Cell[][] grid = new Cell[GRIDHEIGHT][GRIDWIDTH];
        for (int i = 0; i < GRIDHEIGHT; i++) {
            for (int j = 0; j < GRIDWIDTH; j++) {
                grid[i][j] = new Cell();
            }
        }
        return  grid;
    }

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

    public static void printGrid(Figure[] figures, Cell[][] grid) {
        System.out.println();
        for (int i = 0; i < GRIDHEIGHT; i++) {
            for (int j = 0; j < GRIDWIDTH; j++) {
                if (grid[i][j].getEmpty() && !grid[i][j].getWall()) {
                    System.out.print("[O]");
                } else if (grid[i][j].getEmpty() && grid[i][j].getWall()) {
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

    public static void startGame(boolean gui) {
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
                        moveSuccessful = Moves.moveForward(figures[figureIndex], GRIDHEIGHT, grid);
                        break;
                    case "dl":
                        moveSuccessful = Moves.moveDiagonallyLeft(figures[figureIndex], GRIDHEIGHT, GRIDWIDTH, grid, figures);
                        break;
                    case "dr":
                        moveSuccessful = Moves.moveDiagonallyRight(figures[figureIndex], GRIDHEIGHT, GRIDWIDTH, grid, figures);
                        break;
                    default:
                        break;
                }
            }
            printGrid(figures, grid);
            moveSuccessful = false;
            player1Turn = !player1Turn;
        }
    }

}
