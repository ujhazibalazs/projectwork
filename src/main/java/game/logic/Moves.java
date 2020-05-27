package game.logic;

public class Moves {

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

    public static boolean canPlayerMove(boolean player1Turn, Figure[] figures, int gridHeight, int gridWidth, Cell[][] grid) {

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
            if (canMoveForward(figures[i], gridHeight, grid)) {
                return true;
            }
        }
        for (int i = minIndex; i < maxIndex; i++) {
            if (canMoveDiagonallyLeft(figures[i], gridHeight, gridWidth, grid, figures)) {
                return true;
            }
        }
        for (int i = minIndex; i < maxIndex; i++) {
            if (canMoveDiagonallyRight(figures[i], gridHeight, gridWidth, grid, figures)) {
                return true;
            }
        }

        return false;
    }

    public static  boolean canMoveForward(Figure figure, int gridHeight, Cell[][] grid) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < gridHeight) {
                    if (grid[figure.getHeight() + 1][figure.getWidth()].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth()].isWall()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth()].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth()].isWall()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public static boolean canMoveDiagonallyLeft (Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < gridHeight && figure.getWidth() + 1 < gridWidth) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth() + 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() + 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth() - 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() - 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                return true;
                            } else {
                                return false;
                            }
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

    public static boolean canMoveDiagonallyRight(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < gridHeight && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth() - 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() - 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() + 1 < gridWidth) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth() + 1].isWall()) {
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() + 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                return true;
                            } else {
                                return false;
                            }
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

    public static boolean moveForward(Figure figure, int gridHeight, Cell[][] grid) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight) {
                    if (grid[figure.getHeight() + 1][figure.getWidth()].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth()].isWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() - 1][figure.getWidth()].setEmpty(true);
                        return true;
                    } else {
                        System.out.println("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                } else {
                    System.out.println("You are at the edge of the map.");
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
                        System.out.println("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                } else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
        } else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }

    public static boolean moveDiagonallyLeft(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < gridHeight && figure.getWidth() + 1 < gridWidth) {
                    if (grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty() && !grid[figure.getHeight() + 1][figure.getWidth() + 1].isWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() - 1][figure.getWidth() - 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() + 1][figure.getWidth() + 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() + 1, figure.getWidth() + 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight() - 1][figure.getWidth() - 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                System.out.println("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            System.out.println("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() - 1 > -1) {
                    if (grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty() && !grid[figure.getHeight() - 1][figure.getWidth() - 1].isWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight() + 1][figure.getWidth() + 1].setEmpty(true);
                        return true;
                    } else {
                        if (!grid[figure.getHeight() - 1][figure.getWidth() - 1].isEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight() - 1, figure.getWidth() - 1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight() + 1][figure.getWidth() + 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                System.out.println("You cannot step on the same color.");
                                return false;
                            }
                        } else {
                            System.out.println("You cannot step on a wall.");
                            return false;
                        }
                    }
                } else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
        } else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }

    public static boolean moveDiagonallyRight(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight() + 1 < gridHeight && figure.getWidth() - 1 > -1) {
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
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight() - 1][figure.getWidth() + 1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            } else {
                                System.out.println("Azonos színűre nem léphet.");
                                return false;
                            }
                        } else {
                            System.out.println("Falra nem léphet.");
                            return false;
                        }
                    }
                } else {
                    System.out.println("pálya végéhez ért");
                    return false;
                }
            } else {
                if (figure.getHeight() - 1 > -1 && figure.getWidth() + 1 < gridWidth) {
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
                                System.out.println("Azonos színűre nem léphet.");
                                return false;
                            }
                        } else {
                            System.out.println("Falra nem léphet.");
                            return false;
                        }
                    }
                } else {
                    System.out.println("pálya végéhez ért");
                    return false;
                }
            }
        } else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }
}
