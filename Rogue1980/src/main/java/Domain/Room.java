package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final int width;
    private final int height;
    private final List<MapPoint> walls;
    private final List<MapPoint> roomPoints;
    private final Coordinate upLeft;
    private final Coordinate botRight;
    private boolean wallsVisible;
    private final List<Enemy> enemies;
    private final List<Item> items;
    private final List<Coordinate> used;

    public Room(int i, int j) {
        this.wallsVisible = false;
        this.used = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        this.width = generateWidth();
        this.height = generateHeight();
        this.upLeft = generateCoordinateUpLeft(i, j);
        this.botRight = generateCoordinateBotRight();
        this.walls = generateWalls();
        this.roomPoints = generateRoom();
    }

    private int generateWidth() {
        Random random = new Random();
        return random.nextInt(Final.SECTOR_WIDTH - 5) + 4;
    }

    private int generateHeight() {
        Random random = new Random();
        return random.nextInt(Final.SECTOR_HEIGHT - 5) + 4;
    }

    private Coordinate generateCoordinateUpLeft(int i, int j) {
        Random random = new Random();
        int x;
        int y;
        if (this.width == 24) x = 1 + Final.SECTOR_WIDTH * j;
        else x = random.nextInt(Final.SECTOR_WIDTH - this.width - 2) + 1 + Final.SECTOR_WIDTH * j;
        if (this.height == 5) y = 1 + Final.SECTOR_HEIGHT * i;
        else y = random.nextInt(Final.SECTOR_HEIGHT - this.height - 2) + 1 + Final.SECTOR_HEIGHT * i;
        return new Coordinate(x, y);
    }

    private Coordinate generateCoordinateBotRight() {
        return new Coordinate(this.upLeft.getX() + this.width - 1, this.upLeft.getY() + this.height - 1);
    }

    private List<MapPoint> generateWalls() {
        List<MapPoint> walls = new ArrayList<>();
        for (int i = upLeft.getY(); i < upLeft.getY() + height; i++) {
            for (int j = upLeft.getX(); j < upLeft.getX() + width; j++) {
                if (i == upLeft.getY() || i == upLeft.getY() + height - 1 ||
                        j == upLeft.getX() || j == upLeft.getX() + width - 1) {
                    walls.add(new MapPoint(new Coordinate(j, i), false));

                }
            }
        }
        return walls;
    }

    private List<MapPoint> generateRoom() {
        List<MapPoint> roomPoints = new ArrayList<>();
        for (int i = upLeft.getY(); i < upLeft.getY() + height; i++) {
            for (int j = upLeft.getX(); j < upLeft.getX() + width; j++) {
                if (i == upLeft.getY() || i == upLeft.getY() + height - 1 ||
                        j == upLeft.getX() || j == upLeft.getX() + width - 1) {
                } else {
                    roomPoints.add(new MapPoint(new Coordinate(j, i), false));
                }
            }
        }
        return roomPoints;
    }

    public void makeVisibleRoomPoint() {
        for (MapPoint roomPoint : roomPoints) {
            roomPoint.setVisibility(true);
        }
        for (Enemy enemy : enemies) {
            if (enemy.getType() != Final.GHOST) enemy.makeVisible();
        }
        for (Item item : items) {
            item.makeVisible();
        }
        this.wallsVisible = true;
    }

    public void makeInvisibleRoomPoint() {
        for (MapPoint roomPoint : roomPoints) {
            roomPoint.setVisibility(false);
            for (Enemy enemy : enemies) {
                enemy.makeInvisible();
            }
            for (Item item : items) {
                item.makeInvisible();
            }
        }
    }

    public void makeVisibleWallPoints() {
        for (MapPoint roomPoint : walls) {
            roomPoint.setVisibility(true);
        }
    }

    public boolean checkInRoom(Coordinate coordinate) {
        for (MapPoint roomPoint : roomPoints) {
            if (roomPoint.getCoordinate().getX() == coordinate.getX() && roomPoint.getCoordinate().getY() == coordinate.getY())
                return true;
        }
        return false;
    }

    public boolean checkOnWall(Coordinate coordinate) {
        for (MapPoint wall : walls) {
            if (wall.getCoordinate().getX() == coordinate.getX() && wall.getCoordinate().getY() == coordinate.getY())
                return true;
        }
        return false;
    }

    private Coordinate getRandomEmptyPosition() {
        Random rand = new Random();
        Coordinate pos = roomPoints.get(rand.nextInt(roomPoints.size())).getCoordinate();
        while (!checkIsEmpty(pos)) {
            pos = roomPoints.get(rand.nextInt(roomPoints.size())).getCoordinate();
        }
        return pos;
    }

    public void addEnemy(int level) {
        int n = level < 8 ? 1 : (level < 14 ? 2 : 3);
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            if (rand.nextInt(100) >= 50) {
                Coordinate pos = getRandomEmptyPosition();
                enemies.add(new Enemy(level, pos));
                used.add(pos);
            }
        }
    }

    public void addItem(int level) {
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            if (rand.nextInt(100) >= 50) {
                Coordinate pos = getRandomEmptyPosition();
                items.add(new Item(level, pos));
                used.add(pos);
            }
        }
    }

    private boolean checkIsEmpty(Coordinate pos) {
        for (Coordinate coordinate : used) {
            if (coordinate.getX() == pos.getX() && coordinate.getY() == pos.getY()) return false;
        }
        return true;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void moveEnemies(Coordinate playerPos) {
        for (Enemy enemy : enemies) {
            enemy.move(playerPos, this.used, this.roomPoints);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public Coordinate getBotRight() {
        return botRight;
    }

    public Coordinate getUpLeft() {
        return upLeft;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<MapPoint> getRoomPoints() {
        return roomPoints;
    }

    public List<MapPoint> getWallPoints() {
        return walls;
    }

    public boolean isWallsVisible() {
        return wallsVisible;
    }

    public void addUsed(Coordinate position) {
        this.used.add(position);
    }
}