package Domain;

import jcurses.system.CharColor;

public class Final {
    public static final int MAP_HEIGHT = 21; // Пример высоты карты
    public static final int MAP_WIDTH = 78;  // Пример ширины карты
    public static final int ROOMS_PER_SIDE = 3; // Количество комнат на сторону
    public static final int SECTOR_WIDTH = 26; // Ширина сектора
    public static final int SECTOR_HEIGHT = 7; // Высота сектора
    public static final int NUM_ROOMS = ROOMS_PER_SIDE * ROOMS_PER_SIDE; // Количество комнат
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final CharColor YELLOW = new CharColor(CharColor.BLACK, CharColor.YELLOW);
    public static final CharColor GREEN = new CharColor(CharColor.BLACK, CharColor.GREEN);
    public static final CharColor RED = new CharColor(CharColor.BLACK, CharColor.RED);
    public static final CharColor WHITE = new CharColor(CharColor.BLACK, CharColor.WHITE);
    public static final CharColor BLACK = new CharColor(CharColor.BLACK, CharColor.BLACK);
    public static final CharColor BLUE = new CharColor(CharColor.BLACK, CharColor.BLUE);
    public static final int ZOMBIE = 0;
    public static final int VAMPIRE = 1;
    public static final int GHOST = 2;
    public static final int OGRE = 3;
    public static final int SNAKE = 4;
    public static final int MAX_AGILITY = 10;
    public static final int GOLD = 0;
    public static final int FOOD = 1;
    public static final int AGILITY = 2;
    public static final int STRENGTH = 3;
    public static final int MAX_HEALTH = 4;
    public static final int ELIXIR = 5;
    public static final int ELIXIR_PLUS_MAX_HEALTH = 0;
    public static final int ELIXIR_MINUS_MAX_HEALTH = 1;
    public static final int ELIXIR_PLUS_AGILITY = 2;
    public static final int ELIXIR_MINUS_AGILITY = 3;
    public static final int ELIXIR_PLUS_STRENGTH = 4;
    public static final int ELIXIR_MINUS_STRENGTH = 5;
    public static final int ELIXIR_ZERO_GOLD = 6;
    public static final int ELIXIR_MAX_AGILITY = 7;
    public static final int ELIXIR_MIN_AGILITY = 8;
    public static final int ELIXIR_MIN_HEALTH = 9;
    public static final int ELIXIR_MIN_STRENGTH = 10;
}