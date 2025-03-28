package View;

import Datalayer.DataLayer;
import Domain.Character;
import Domain.GameSession;
import jcurses.system.CharColor;
import jcurses.system.Toolkit;
import Domain.Final;

import java.util.List;

public class Print {
    private char[][] field;
    private Character player;

    public Print(char[][] field, Character player) {
        this.field = field;
        this.player = player;
    }

    public void setField(char[][] field, Character player) {
        this.field = field;
        this.player = player;
    }

    public void printField(int currentLevelNumber) {
        // Очищаем поле перед выводом
        Toolkit.clearScreen(Final.BLACK);
        // Выводим новое содержимое поля
        for (int i = 0; i < Final.MAP_HEIGHT; i++) {
            for (int j = 0; j < Final.MAP_WIDTH; j++) {
                char currentChar = field[i][j];
                CharColor color = Final.WHITE;
                if (currentChar == '#' || currentChar == 'O' || currentChar == '$') {
                    color = Final.YELLOW;
                } else if (currentChar == 'Z' || currentChar == '@' || currentChar == 'X' || currentChar == 'F' || currentChar == 'A') {
                    color = Final.GREEN;
                } else if (currentChar == 'V' || currentChar == 'M' || currentChar == '%') {
                    color = Final.RED;
                } else if (currentChar == 'E') {
                    color = Final.BLUE;
                }
                // Выводим символ с соответствующим цветом
                Toolkit.printString(String.valueOf(currentChar), j, i, color);
            }
        }
        Toolkit.printString("Health:" + player.getHealth() + "/" + player.getMaxHealth() + "--" + "Strength:" + player.getStrength() + "--" + "Gold:" + player.getGold() + "--" + "Level:" + (currentLevelNumber + 1), 3, 21, Final.RED);
    }

    public void printInfoMessage(List<String> messages, int currentLevelNumber) {
        if (!messages.isEmpty()) {
            printField(currentLevelNumber); // Отрисовываем поле

            // Объединяем строки в одно сообщение
            String message = String.join(" ", messages);

            // Выводим сообщение
            Toolkit.printString(message, 3, 22, Final.WHITE);
        } else printField(currentLevelNumber);
    }

    public void printRules() {
        // Очищаем поле перед выводом
        Toolkit.clearScreen(Final.BLACK);
        // Выводим новое содержимое поля
        Toolkit.printString("KEYS:", Final.MAP_WIDTH / 2 - 3, 0, Final.RED);
        Toolkit.printString("Press--N--to--start.", 5, 2, Final.WHITE);
        Toolkit.printString("Press--Q--to--exit.", 5, 3, Final.WHITE);
        Toolkit.printString("Press--W--to--move--UP.", 5, 4, Final.WHITE);
        Toolkit.printString("Press--D--to--move--RIGHT.", 5, 5, Final.WHITE);
        Toolkit.printString("Press--A--to--move--LEFT.", 5, 6, Final.WHITE);
        Toolkit.printString("Press--S--to--move--DOWN.", 5, 7, Final.WHITE);

        Toolkit.printString("GAME--LOGIC.", Final.MAP_WIDTH / 2 - 5, 10, Final.RED);
        Toolkit.printString("If--you--want--to--fight--move--at--enemy--direction.", 5, 12, Final.WHITE);
        Toolkit.printString("If--you--want--to--grab--move--at--treasure--direction.", 5, 13, Final.WHITE);
        Toolkit.printString("If--you--want--to--go-to-the-next-level:--press--N--on--X--mark.", 5, 14, Final.WHITE);
        Toolkit.printString("You--need--to--pass--21--level.", 5, 15, Final.WHITE);

        Toolkit.printString("DESIGNATIONS.", Final.MAP_WIDTH / 2 - 5, 17, Final.RED);
        Toolkit.printString("@--Player.", 5, 19, Final.WHITE);
        Toolkit.printString("Enemies:V--Vampire,G--Ghost,O-Ogre,S--Snake,Z--Zombie.", 5, 20, Final.WHITE);
        Toolkit.printString("X--Next--Level.", 5, 21, Final.WHITE);
        Toolkit.printString("TREASURE:$--gold,F--food,A--agility,%--strength,M--max--health,", 5, 22, Final.WHITE);
        Toolkit.printString("E-elixir(random--change--of--health--or-strength--or--agility).", 5, 23, Final.WHITE);

    }

    public void printResultOfGame(GameSession game) {
        // Очищаем поле перед выводом
        Toolkit.clearScreen(Final.BLACK);
        // Выводим новое содержимое поля
        if (!game.isWin()) Toolkit.printString("YOU--LOST!", Final.MAP_WIDTH / 2 - 4, 5, Final.RED);
        else Toolkit.printString("YOU--WIN!", Final.MAP_WIDTH / 2 - 4, 5, Final.RED);

        Toolkit.printString("You--passed--the--" + game.getCurrentLevelNumber() + "--level.", Final.MAP_WIDTH / 2 - 4, 7, Final.GREEN);
        Toolkit.printString("You--got--the--" + (game.getPlayer().getGold()) + "--gold.", Final.MAP_WIDTH / 2 - 4, 8, Final.GREEN);
        Toolkit.printString("You--killed--the--" + (game.getPlayer().getDefeatedEnemiesCounter()) + "--enemies.", Final.MAP_WIDTH / 2 - 4, 9, Final.GREEN);
        Toolkit.printString("You--attacked--the--enemies--" + (game.getPlayer().getAttackCounter()) + "--times.", Final.MAP_WIDTH / 2 - 4, 10, Final.GREEN);
        Toolkit.printString("You--were--attacked--" + (game.getPlayer().getEnemiesAttackCounter()) + "--times.", Final.MAP_WIDTH / 2 - 4, 11, Final.GREEN);
        Toolkit.printString("You--make--the--" + (game.getPlayer().getBoxCounter()) + "--steps.", Final.MAP_WIDTH / 2 - 4, 12, Final.GREEN);
        Toolkit.printString("You--ate--the--" + (game.getPlayer().getEatenFoodCounter()) + "--food.", Final.MAP_WIDTH / 2 - 4, 13, Final.GREEN);
        Toolkit.printString("You--drunk--the--" + (game.getPlayer().getDrunkElixirCounter()) + "--elixirs.", Final.MAP_WIDTH / 2 - 4, 14, Final.GREEN);

        Toolkit.printString("Press--Q--to-exit.", Final.MAP_WIDTH / 2 - 4, 16, Final.WHITE);
        Toolkit.printString("Press--N--to-watch--score--table.", Final.MAP_WIDTH / 2 - 4, 17, Final.WHITE);
    }

    public void printResultTable() {
        Toolkit.clearScreen(Final.BLACK);
        // Выводим новое содержимое поля
        Toolkit.printString("RESULTS:", Final.MAP_WIDTH / 2 - 6, 1, Final.RED);
        List<Character> players = DataLayer.loadProgress();

        int count = 0;
        for (int i = players.size(); i > 0 && count < 24; count++, i--) {
            Toolkit.printString(players.get(i - 1).getEndTime() + "--lvl:" + players.get(i - 1).getEndLevel() + "--$:" + players.get(i - 1).getGold() + "--steps:" + players.get(i - 1).getBoxCounter() + "--F:" + players.get(i - 1).getEatenFoodCounter() + "--kills:" + players.get(i - 1).getDefeatedEnemiesCounter() + "--E:" + players.get(i - 1).getDrunkElixirCounter(), 1, 3 + count, Final.WHITE);
        }

        Toolkit.printString("Press--Q--to--exit.", Final.MAP_WIDTH / 2 - 20, 28, Final.GREEN);
    }
}
