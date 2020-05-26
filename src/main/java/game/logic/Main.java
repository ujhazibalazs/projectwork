package game.logic;

import game.javafx.GameApplication;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("gui")) {
                Application.launch(GameApplication.class);
            } else {
                System.out.println("Use gui as argument or leave it empty.");
            }
        } else {
            Game.startGame(false);
        }
    }

}
