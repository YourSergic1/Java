package Control;

import jcurses.system.InputChar;
import jcurses.system.Toolkit;

public class UserInput {
    private int userInput;

    public UserInput() {
        userInput = ' ';
    }

    public void waitForInput() {
        InputChar inputChar = Toolkit.readCharacter();
        userInput = inputChar.getCode();
    }

    public void waitForExit() {
        InputChar inputChar = Toolkit.readCharacter();
        userInput = inputChar.getCode();
        while (userInput != 'Q' && userInput != 'q') {
            inputChar = Toolkit.readCharacter();
            userInput = inputChar.getCode();
        }
    }

    public void waitForResumeOrExit() {
        InputChar inputChar = Toolkit.readCharacter();
        userInput = inputChar.getCode();
        while (userInput != 'Q' && userInput != 'q' && userInput != 'n' && userInput != 'N') {
            inputChar = Toolkit.readCharacter();
            userInput = inputChar.getCode();
        }
    }

    public int returnInput() {
        return userInput;
    }
}
