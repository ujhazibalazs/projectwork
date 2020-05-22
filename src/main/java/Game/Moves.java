package Game;

public class Moves {

    private static Figure getFigureByPosition(Figure[] figures, int height, int width) {
        Figure figure = new Figure();
        for (int i = 0; i < figures.length; i++) {
            if (figures[i].getHeight() == height && figures[i].getWidth() == width) {
                figure = figures[i];
            }
        }
        return figure;
    }

    public static boolean MoveForward(Figure figure, int gridHeight, Cell[][] grid) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight) {
                    if (grid[figure.getHeight()+1][figure.getWidth()].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()].getWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()-1][figure.getWidth()].setEmpty(true);
                        return true;
                    }
                    else {
                        System.out.println("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                }
                else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
            else {
                if (figure.getHeight()-1 > -1) {
                    if (grid[figure.getHeight()-1][figure.getWidth()].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()].getWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()+1][figure.getWidth()].setEmpty(true);
                        return true;
                    }
                    else {
                        System.out.println("The cell is not empty, or you are trying to step on a wall.");
                        return false;
                    }
                }
                else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
        }
        else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }

    public static boolean MoveDiagonallyLeft(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight && figure.getWidth()+1 < gridWidth) {
                    if (grid[figure.getHeight()+1][figure.getWidth()+1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()+1].getWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()-1][figure.getWidth()-1].setEmpty(true);
                        return true;
                    }
                    else {
                        if (!grid[figure.getHeight()+1][figure.getWidth()+1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()+1, figure.getWidth()+1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight()-1][figure.getWidth()-1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            }
                            else {
                                System.out.println("You cannot step on the same color.");
                                return false;
                            }
                        }
                        else {
                            System.out.println("You cannot step on a wall.");
                            return false;
                        }
                    }
                }
                else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
            else {
                if (figure.getHeight()-1 > -1 && figure.getWidth()-1 > -1) {
                    if (grid[figure.getHeight()-1][figure.getWidth()-1].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()-1].getWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()+1][figure.getWidth()+1].setEmpty(true);
                        return true;
                    }
                    else {
                        if (!grid[figure.getHeight()-1][figure.getWidth()-1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()-1, figure.getWidth()-1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight()+1][figure.getWidth()+1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            }
                            else {
                                System.out.println("You cannot step on the same color.");
                                return false;
                            }
                        }
                        else {
                            System.out.println("You cannot step on a wall.");
                            return false;
                        }
                    }
                }
                else {
                    System.out.println("You are at the edge of the map.");
                    return false;
                }
            }
        }
        else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }

    public static boolean MoveDiagonallyRight(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight && figure.getWidth()-1 > -1) {
                    if (grid[figure.getHeight()+1][figure.getWidth()-1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()-1].getWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()-1][figure.getWidth()+1].setEmpty(true);
                        return true;
                    }
                    else {
                        if (!grid[figure.getHeight()+1][figure.getWidth()-1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()+1, figure.getWidth()-1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() + 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight()-1][figure.getWidth()+1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                                return false;
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                            return false;
                        }
                    }
                }
                else {
                    System.out.println("pálya végéhez ért");
                    return false;
                }
            }
            else {
                if (figure.getHeight()-1 > -1 && figure.getWidth()+1 < gridWidth) {
                    if (grid[figure.getHeight()-1][figure.getWidth()+1].getEmpty() && !grid[figure.getHeight()-1][figure.getWidth()+1].getWall()) {
                        figure.setHeight(figure.getHeight() - 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()+1][figure.getWidth()-1].setEmpty(true);
                        return true;
                    }
                    else {
                        if (!grid[figure.getHeight()-1][figure.getWidth()+1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()-1, figure.getWidth()+1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight()+1][figure.getWidth()-1].setEmpty(true);
                                figure2.setCaptured(true);
                                return true;
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                                return false;
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                            return false;
                        }
                    }
                }
                else {
                    System.out.println("pálya végéhez ért");
                    return false;
                }
            }
        }
        else {
            System.out.println("This figure is already captured.");
            return false;
        }
    }
}
