import Datalayer.DataLayer;
import Domain.GameSession;
import Control.UserInput;
import View.Print;
import jcurses.system.Toolkit;

public class Main {
    public static void main(String[] args) {
        Toolkit.init();
        GameSession newGame = new GameSession();
        UserInput userInput = new UserInput();
        Print print = new Print(newGame.getField(), newGame.getPlayer());
        while (!newGame.isReadyToStart()) {
            print.printRules();
            userInput.waitForInput();
            newGame.waitForStart(userInput.returnInput());
        }
        if (newGame.getInGame()) print.printField(newGame.getCurrentLevelNumber());
        while (newGame.getInGame()) {
            userInput.waitForInput();
            print.printInfoMessage(newGame.workWithInput(userInput.returnInput()), newGame.getCurrentLevelNumber());
            print.setField(newGame.getField(), newGame.getPlayer());
        }
        print.printResultOfGame(newGame);
        newGame.getPlayer().setEndTime();
        newGame.getPlayer().setEndLevel(newGame.getCurrentLevelNumber() + 1);
        DataLayer.saveProgress(newGame.getPlayer());
        userInput.waitForResumeOrExit();
        if (userInput.returnInput() == 'N' || userInput.returnInput() == 'n') {
            print.printResultTable();
            userInput.waitForExit();
        }
        Toolkit.shutdown();

    }

}