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

    public static void MoveForward(Figure figure, int gridHeight, Cell[][] grid) {
        if (!figure.isCaptured()) {
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
        else {
            System.out.println("This figure is already captured.");
        }
    }

    public static void MoveDiagonallyLeft(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight && figure.getWidth()+1 < gridWidth) {
                    if (grid[figure.getHeight()+1][figure.getWidth()+1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()+1].getWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() + 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()-1][figure.getWidth()-1].setEmpty(true);
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
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                        }
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
                        if (!grid[figure.getHeight()-1][figure.getWidth()-1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()-1, figure.getWidth()-1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() - 1);
                                grid[figure.getHeight()+1][figure.getWidth()+1].setEmpty(true);
                                figure2.setCaptured(true);
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                        }
                    }
                }
                else {
                    System.out.println("pálya végéhez ért");
                }
            }
        }
        else {
            System.out.println("This figure is already captured.");
        }
    }

    public static void MoveDiagonallyRight(Figure figure, int gridHeight, int gridWidth, Cell[][] grid, Figure[] figures) {
        if (!figure.isCaptured()) {
            if (figure.getColor().equals("red")) {
                if (figure.getHeight()+1 < gridHeight && figure.getWidth()-1 > -1) {
                    if (grid[figure.getHeight()+1][figure.getWidth()-1].getEmpty() && !grid[figure.getHeight()+1][figure.getWidth()-1].getWall()) {
                        figure.setHeight(figure.getHeight() + 1);
                        figure.setWidth(figure.getWidth() - 1);
                        grid[figure.getHeight()][figure.getWidth()].setEmpty(false);
                        grid[figure.getHeight()-1][figure.getWidth()+1].setEmpty(true);
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
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                        }
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
                        if (!grid[figure.getHeight()-1][figure.getWidth()+1].getEmpty()) {
                            Figure figure2 = getFigureByPosition(figures, figure.getHeight()-1, figure.getWidth()+1);
                            if (!figure2.getColor().equals(figure.getColor())) {
                                //Capturing the figure
                                figure.setHeight(figure.getHeight() - 1);
                                figure.setWidth(figure.getWidth() + 1);
                                grid[figure.getHeight()+1][figure.getWidth()-1].setEmpty(true);
                                figure2.setCaptured(true);
                            }
                            else {
                                System.out.println("Azonos színűre nem léphet.");
                            }
                        }
                        else {
                            System.out.println("Falra nem léphet.");
                        }
                    }
                }
                else {
                    System.out.println("pálya végéhez ért");
                }
            }
        }
        else {
            System.out.println("This figure is already captured.");
        }
    }
}
