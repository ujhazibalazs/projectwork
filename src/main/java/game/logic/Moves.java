package game.logic;

import org.tinylog.Logger;

/**
 * Class for holding methods required for the game's logic.
 */
public class Moves {

    /**
     * Gets the figure on the given coordinates.
     * @param figures the array of figures to look through
     * @param height the height of the column
     * @param width the width of the row
     * @return the figure at the given position
     */
    public static Figure getFigureByPosition(Figure[] figures, int height, int width) {
        Figure figure = new Figure();
        for (int i = 0; i < figures.length; i++) {
            if (figures[i].getHeight() == height && figures[i].getWidth() == width) {
                if (!figures[i].isCaptured()) {
                    figure = figures[i];
                }
            }
        }
        return figure;
    }

    /**
     * Checks whether the given player can move or not.
     * @param player1Turn the player who has to move
     * @param figures the player's figures
     * @param grid the grid to check on
     * @return {@code true} if the given player can make any move, {@code false} otherwise
     */
    public static boolean canPlayerMove(boolean player1Turn, Figure[] figures, Cell[][] grid) {
        int minIndex;
        int maxIndex;

        if (player1Turn) {
            minIndex = 0;
            maxIndex = 7;
        } else {
            minIndex = 7;
            maxIndex = 14;
        }

        for (int i = minIndex; i < maxIndex; i++) {
            if (canMoveForward(figures[i], grid)) {
                return true;
            }
        }
        for (int i = minIndex; i < maxIndex; i++) {
            if (canMoveDiagonallyLeft(figures[i], grid, figures)) {
                return true;
            }
        }
        for (int i = minIndex; i < maxIndex; i++) {
            if (canMoveDiagonallyRight(figures[i], grid, figures)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the given figure can move forward or not.
     * @param figure the figure to check
     * @param grid the grid to check on
     * @return {@code true} if the given figure can move forward, {@code false} otherwise
     */
    public static  boolean canMoveForward(Figure figure, Cell[][] grid) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < Game.GRIDHEIGHT) {
                    return grid[figure.getHeight() + 1][figure.getWidth()].isEmpty()
                            && !grid[figure.getHeight() + 1][figure.getWidth()].isWall();
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1) {
                    return grid[figure.getHeight() - 1][figure.getWidth()].isEmpty()
                            && !grid[figure.getHeight() - 1][figure.getWidth()].isWall();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks whether the given figure can move diagonally left or not.
     * @param figure the figure to check
     * @param grid the grid to check on
     * @param figures the player's figures
     * @return {@code true} if the given figure can move diagonally left, {@code false} otherwise
     */
    public static boolean canMoveDiagonallyLeft (Figure figure, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < Game.GRIDHEIGHT && figure.getWidth() + 1 < Game.GRIDWIDTH) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()
                            && !grid[figure.getHeight() + 1][figure.getWidth() + 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() + 1);
                            return !figure2.getColor().equals(figure.getColor());
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()
                            && !grid[figure.getHeight() - 1][figure.getWidth() - 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() - 1);
                            return !figure2.getColor().equals(figure.getColor());
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks whether the given figure can move diagonally right or not.
     * @param figure the figure to check
     * @param grid the grid to check on
     * @param figures the player's figures
     * @return {@code true} if the given figure can move diagonally right, {@code false} otherwise
     */
    public static boolean canMoveDiagonallyRight(Figure figure, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < Game.GRIDHEIGHT && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth() - 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() - 1);
                            return !figure2.getColor().equals(figure.getColor());
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() + 1 < Game.GRIDWIDTH) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth() + 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() + 1);
                            return !figure2.getColor().equals(figure.getColor());
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Moves the given figure forward if it is possible.
     * @param figure the figure to move
     * @param grid the grid representing the current state of the game
     * @return {@code true} if the figure successfully moved forward, {@code false} otherwise
     */
    public static boolean moveForward(Figure figure, Cell[][] grid) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < Game.GRIDHEIGHT) {
                    if (grid[figure.getHeight() + 1][figure.getWidth()].isEmpty()
                            && !grid[figure.getHeight() + 1][figure.getWidth()].isWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() - 1][figure.getWidth()].setEmpty(true);
                        return true;
                    } else {
                        Logger.info("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            } else {
                if (figure.getHeight()-1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth()].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth()].isWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() + 1][figure.getWidth()].setEmpty(true);
                        return true;
                    } else {
                        Logger.info("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            }
        } else {
            Logger.info("This figure is already captured.");
            return false;
        }
    }

    /**
     * Moves the given figure diagonally left if it is possible.
     * @param figure the figure to move
     * @param grid the grid representing the current state of the game
     * @param figures the player's figures
     * @return {@code true} if the figure successfully moved diagonally left, {@code false} otherwise
     */
    public static boolean moveDiagonallyLeft(Figure figure, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < Game.GRIDHEIGHT && figure.getWidth() + 1 < Game.GRIDWIDTH) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()
                            && !grid[figure.getHeight() + 1][figure.getWidth() + 1].isWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() - 1][figure.getWidth() - 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() + 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight() - 1][figure.getWidth() - 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                Logger.info("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            Logger.info("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()
                            && !grid[figure.getHeight() - 1][figure.getWidth() - 1].isWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() + 1][figure.getWidth() + 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() - 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight() + 1][figure.getWidth() + 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                Logger.info("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            Logger.info("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            }
        } else {
            Logger.info("This figure is already captured.");
            return false;
        }
    }

    /**
     * Moves the given figure diagonally right if it is possible.
     * @param figure the figure to move
     * @param grid the grid representing the current state of the game
     * @param figures the player's figures
     * @return {@code true} if the figure successfully moved diagonally right, {@code true} otherwise
     */
    public static boolean moveDiagonallyRight(Figure figure, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < Game.GRIDHEIGHT && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth() - 1].isWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() - 1][figure.getWidth() + 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() - 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight() - 1][figure.getWidth() + 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                Logger.info("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            Logger.info("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() + 1 < Game.GRIDWIDTH) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth() + 1].isWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() + 1][figure.getWidth() - 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() + 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight() + 1][figure.getWidth() - 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                Logger.info("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            Logger.info("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    Logger.info("You are at the edge of the grid.");
                    return false;
                }
            }
        } else {
            Logger.info("This figure is already captured.");
            return false;
        }
    }
}
