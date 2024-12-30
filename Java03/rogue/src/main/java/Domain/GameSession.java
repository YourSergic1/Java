package Domain;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private final char[][] field;
    public List<Level> levels;
    public Level currentLevel;
    private int currentLevelNumber;
    Character player;
    private boolean inGame;
    private boolean win;
    private boolean readyToStart;

    public GameSession() {
        levels = new ArrayList<>(); // Инициализация списка уровней
        this.inGame = true;
        this.win = false;
        this.readyToStart = false;
        this.currentLevelNumber = 0;
        field = new char[Final.MAP_HEIGHT][Final.MAP_WIDTH]; // Заполняем поле пустотой
        for (int i = 0; i < Final.MAP_HEIGHT; i++) {
            for (int j = 0; j < Final.MAP_WIDTH; j++) {
                field[i][j] = ' ';
            }
        }
        for (int i = 0; i < 21; i++) {
            levels.add(new Level(i));
        }
        this.currentLevel = levels.get(currentLevelNumber);
        this.player = new Character(currentLevel.getStartPoint());
        levelToField();
    }

    private void levelToField() {
        for (int i = 0; i < Final.MAP_HEIGHT; i++) {
            for (int j = 0; j < Final.MAP_WIDTH; j++) {
                field[i][j] = ' ';
            }
        }
        currentLevel.changeVisibility(player.getPosition());
        for (Room each : currentLevel.getRooms()) {
            for (MapPoint point : each.getRoomPoints()) {
                if (point.getVisibility()) field[point.getCoordinate().getY()][point.getCoordinate().getX()] = '.';
                else field[point.getCoordinate().getY()][point.getCoordinate().getX()] = ' ';
            }
            for (MapPoint point : each.getWallPoints()) {
                if (point.getVisibility()) field[point.getCoordinate().getY()][point.getCoordinate().getX()] = '#';
            }
        }
        for (Corridor each : currentLevel.getCorridors()) {
            for (MapPoint point : each.getPoints()) {
                if (point.getVisibility()) field[point.getCoordinate().getY()][point.getCoordinate().getX()] = '*';
            }
        }
        for (Room room : currentLevel.getRooms()) {
            for (Enemy enemy : room.getEnemies()) {
                if (enemy.getType() == Final.ZOMBIE && enemy.getVisibility())
                    field[enemy.getPosition().getY()][enemy.getPosition().getX()] = 'Z';
                if (enemy.getType() == Final.VAMPIRE && enemy.getVisibility())
                    field[enemy.getPosition().getY()][enemy.getPosition().getX()] = 'V';
                if (enemy.getType() == Final.GHOST && enemy.getVisibility())
                    field[enemy.getPosition().getY()][enemy.getPosition().getX()] = 'G';
                if (enemy.getType() == Final.OGRE && enemy.getVisibility())
                    field[enemy.getPosition().getY()][enemy.getPosition().getX()] = 'O';
                if (enemy.getType() == Final.SNAKE && enemy.getVisibility())
                    field[enemy.getPosition().getY()][enemy.getPosition().getX()] = 'S';
            }
        }
        for (Room room : currentLevel.getRooms()) {
            for (Item item : room.getItems()) {
                if (item.getType() == Final.GOLD && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = '$';
                if (item.getType() == Final.FOOD && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = 'F';
                if (item.getType() == Final.AGILITY && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = 'A';
                if (item.getType() == Final.STRENGTH && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = '%';
                if (item.getType() == Final.MAX_HEALTH && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = 'M';
                if (item.getType() == Final.ELIXIR && item.getVisibility())
                    field[item.getPosition().getY()][item.getPosition().getX()] = 'E';
            }
        }
        if (currentLevel.getEndPoint().getVisibility())
            field[currentLevel.getEndPoint().getCoordinate().getY()][currentLevel.getEndPoint().getCoordinate().getX()] = 'X';
        this.field[player.getPosition().getY()][player.getPosition().getX()] = '@';
    }

    public List<String> workWithInput(int input) {
        List<String> message = new ArrayList<>();
        int direction;
        if (input == 'w' || input == 'W') {
            direction = Final.UP;
            message = player.move(field, direction, currentLevel);
            currentLevel.moveEnemies(player.getPosition());
        }
        if (input == 'd' || input == 'D') {
            direction = Final.RIGHT;
            message = player.move(field, direction, currentLevel);
            currentLevel.moveEnemies(player.getPosition());
        }
        if (input == 's' || input == 'S') {
            direction = Final.DOWN;
            message = player.move(field, direction, currentLevel);
            currentLevel.moveEnemies(player.getPosition());
        }
        if (input == 'a' || input == 'A') {
            direction = Final.LEFT;
            message = player.move(field, direction, currentLevel);
            currentLevel.moveEnemies(player.getPosition());
        }
        checkInGame();
        checkEnemies(player);
        checkItems();
        levelToField();
        if (input == 'Q' || input == 'q') inGame = false;
        if ((input == 'N' || input == 'n') && player.getPosition().getX() == currentLevel.getEndPoint().getCoordinate().getX() && player.getPosition().getY() == currentLevel.getEndPoint().getCoordinate().getY())
            nextLevel();
        return message;
    }

    private void nextLevel() {
        currentLevelNumber++;
        if (currentLevelNumber < 21) {
            player.setGold(100);
            player.setStrength(currentLevelNumber + 1);
            player.setMaxHealth(10);
            player.setHealth(player.getMaxHealth());
            currentLevel = levels.get(currentLevelNumber);
            this.player.setPosition(currentLevel.getStartPoint());
            for (int i = 0; i < Final.MAP_HEIGHT; i++) {
                for (int j = 0; j < Final.MAP_WIDTH; j++) {
                    field[i][j] = ' ';
                }
            }
            levelToField();
        } else {
            this.win = true;
            this.inGame = false;
        }
    }

    public void waitForStart(int input) {
        if (input == 'N' || input == 'n') this.readyToStart = true;
        if (input == 'Q' || input == 'q') {
            this.inGame = false;
            this.readyToStart = true;
        }
    }

    private void checkEnemies(Character player) {
        for (Room room : currentLevel.getRooms()) {
            if (!room.getEnemies().isEmpty()) {
                for (int i = 0; i < room.getEnemies().size(); i++) {
                    if (room.getEnemies().get(i).getHealth() <= 0) {
                        player.setGold(room.getEnemies().get(i).getGold());
                        room.getEnemies().remove(i);
                        player.setDefeatedEnemiesCounter();
                        break;
                    }
                }
            }
        }
    }

    private void checkItems() {
        for (Room room : currentLevel.getRooms()) {
            if (!room.getItems().isEmpty()) {
                for (int i = 0; i < room.getItems().size(); i++) {
                    if (!room.getItems().get(i).isInGame()) {
                        room.getItems().remove(i);
                        break;
                    }
                }
            }
        }
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public Character getPlayer() {
        return player;
    }

    private void checkInGame() {
        if (player.getHealth() <= 0) {
            this.inGame = false;
            this.win = false;
        }
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public char[][] getField() {
        return field;
    }

    public boolean getInGame() {
        return inGame;
    }

    public boolean isWin() {
        return win;
    }

}
