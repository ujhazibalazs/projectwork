package Game;

import java.util.Scanner;

public class Main {

    public static Cell[][] createGrid(int gridHeight, int gridWidth) {
        Cell[][] grid = new Cell[gridHeight][gridWidth];
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = new Cell();
            }
        }
        return  grid;
    }

    public static Figure[] createFigures(int gridHeight, int gridWidth, Cell[][] grid) {

        Figure[] figures = new Figure[gridWidth*2];

        int currWidth = 0;

        for (int i = 0; i < figures.length; i++) {
            figures[i] = new Figure();
            figures[i].setIndex(i);
            if (currWidth == gridWidth) {
                currWidth = 0;
            }
            if (i < gridWidth) {
                figures[i].setColor("red");
                figures[i].setHeight(0);
                figures[i].setWidth(currWidth);
                grid[0][currWidth].setEmpty(false);
            }
            else {
                figures[i].setColor("blue");
                figures[i].setHeight(gridHeight-1);
                figures[i].setWidth(currWidth);
                grid[gridHeight-1][currWidth].setEmpty(false);
            }
            currWidth++;
        }

        return figures;

    }

    //Printing the map of the game out on the console
    public static void printGrid(Figure[] figures, int height, int width, Cell[][] grid) {
        System.out.println();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if(grid[i][j].getEmpty() && !grid[i][j].getWall())
                    System.out.print("[O]");
                else if(grid[i][j].getEmpty() && grid[i][j].getWall())
                    System.out.print("[/]");
                else {
                    for (int k = 0; k < figures.length; k++) {
                        if (!figures[k].isCaptured()) {
                            if (figures[k].getHeight() == i && figures[k].getWidth() == j) {
                                if (figures[k].getColor().equals("red")) {
                                    System.out.print("[R]");
                                }
                                else {
                                    System.out.print("[B]");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        int gridHeight = 6;
        int gridWidth = 7;

        Cell[][] grid = createGrid(gridHeight, gridWidth);

        grid[2][4].setWall(true);
        grid[3][2].setWall(true);

        Figure[] figures = createFigures(gridHeight, gridWidth, grid);

        Scanner scanner = new Scanner(System.in);
        System.out.print("First player's name: ");
        String player1 = scanner.nextLine();
        System.out.println("Welcome, " + player1 + "!");
        System.out.print("Second player's name: ");
        String player2 = scanner.nextLine();
        while (player1.equals(player2)) {
            System.out.print("Second player's name: ");
            player2 = scanner.nextLine();
        }
        System.out.println("Welcome, " + player2 + "!");

        boolean player1Turn = true;
        boolean moveSuccessful = false;
        int figureIndex;
        String move;

        while (true) {
            if (player1Turn) {
                System.out.println(player1 + "'s turn");
                printGrid(figures, gridHeight, gridWidth, grid);
                while (!moveSuccessful) {
                    System.out.println("Which figure do you want to move? [1 - 7]");
                    figureIndex = scanner.nextInt();
                    while (figureIndex < 1 || figureIndex > 7) {
                        System.out.println("Which figure do you want to move? [1 - 7]");
                        figureIndex = scanner.nextInt();
                    }
                    figureIndex -= 1;
                    System.out.println("What move would you like to perform? [f, dl, dr]");
                    //TODO FIX THIS
                    scanner.nextLine();
                    move = scanner.nextLine();
                    while (!move.equals("f") && !move.equals("dl") && !move.equals("dr")) {
                        System.out.println("What move would you like to perform? [f, dl, dr]");
                        move = scanner.nextLine();
                    }
                    switch (move) {
                        case "f":
                            moveSuccessful = Moves.MoveForward(figures[figureIndex], gridHeight, grid);
                            break;
                        case "dl":
                            moveSuccessful = Moves.MoveDiagonallyLeft(figures[figureIndex], gridHeight, gridWidth, grid, figures);
                            break;
                        case "dr":
                            moveSuccessful = Moves.MoveDiagonallyRight(figures[figureIndex], gridHeight, gridWidth, grid, figures);
                            break;
                    }
                }
                printGrid(figures, gridHeight, gridWidth, grid);
                moveSuccessful = !moveSuccessful;
                player1Turn = !player1Turn;
            }
            else {
                System.out.println(player2 + "'s turn");
                printGrid(figures, gridHeight, gridWidth, grid);
                while (!moveSuccessful) {
                    System.out.println("Which figure do you want to move? [1 - 7]");
                    figureIndex = scanner.nextInt();
                    while (figureIndex < 1 || figureIndex > 7) {
                        System.out.println("Which figure do you want to move? [1 - 7]");
                        figureIndex = scanner.nextInt();
                    }
                    figureIndex += 6;
                    System.out.println("What move would you like to perform? [f, dl, dr]");
                    //TODO FIX THIS
                    scanner.nextLine();
                    move = scanner.nextLine();
                    while (!move.equals("f") && !move.equals("dl") && !move.equals("dr")) {
                        System.out.println("What move would you like to perform? [f, dl, dr]");
                        move = scanner.nextLine();
                    }
                    switch (move) {
                        case "f":
                            moveSuccessful = Moves.MoveForward(figures[figureIndex], gridHeight, grid);
                            break;
                        case "dl":
                            moveSuccessful = Moves.MoveDiagonallyLeft(figures[figureIndex], gridHeight, gridWidth, grid, figures);
                            break;
                        case "dr":
                            moveSuccessful = Moves.MoveDiagonallyRight(figures[figureIndex], gridHeight, gridWidth, grid, figures);
                            break;
                    }
                }
                printGrid(figures, gridHeight, gridWidth, grid);
                moveSuccessful = !moveSuccessful;
                player1Turn = !player1Turn;
            }
        }

    }
}