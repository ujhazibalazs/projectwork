package game.logic;

import game.javafx.GameApplication;
import javafx.application.Application;
import org.tinylog.Logger;

/**
 * The main class of the game.
 */
public class Main {

    /**
     * The main method of the Main class.
     * @param args program arguments
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("gui")) {
                Application.launch(GameApplication.class);
            } else {
                Logger.debug("Use gui as argument or leave it empty.");
            }
        } else {
            Game.startGame();
        }
    }

}
