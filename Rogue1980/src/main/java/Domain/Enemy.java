package Domain;

import java.util.List;
import java.util.Random;

public class Enemy {
    private Coordinate position;
    private int type;
    private int health;
    private int agility;
    private int hostility;
    private int strength;
    private int attackCounter;
    private int gold;
    private boolean visibility;
    private int directionUpDown;
    private int directionLeftRight;
    private boolean sleep;

    public Enemy(int level, Coordinate position) {
        this.visibility = false;
        this.position = position;
        this.sleep = false;
        Random rand = new Random();
        int type = rand.nextInt(5);
        switch (type) {
            case Final.ZOMBIE -> {
                this.type = Final.ZOMBIE;
                this.agility = 1;
                this.hostility = 2;
                this.strength = 2 * (level + 1);
                this.health = 2 * (level + 1);
                this.attackCounter = 0;
                this.gold = 9;
            }
            case Final.VAMPIRE -> {
                this.type = Final.VAMPIRE;
                this.agility = 3;
                this.hostility = 3;
                this.strength = 2 * (level + 1);
                this.health = 3 * (level + 1);
                this.attackCounter = 0;
                this.gold = 14;
            }
            case Final.GHOST -> {
                this.type = Final.GHOST;
                this.agility = 3;
                this.hostility = 1;
                this.strength = (level + 1);
                this.health = (level + 1);
                this.attackCounter = 0;
                this.gold = 6;
            }
            case Final.OGRE -> {
                this.type = Final.OGRE;
                this.agility = 1;
                this.hostility = 3;
                this.strength = 4 * (level + 1);
                this.health = 4 * (level + 1);
                this.attackCounter = 0;
                this.gold = 17;
            }
            case Final.SNAKE -> {
                this.type = Final.SNAKE;
                this.agility = 4;
                this.hostility = 3;
                this.strength = 2 * (level + 1);
                this.health = (level + 1);
                this.attackCounter = 0;
                this.gold = 11;
                this.directionUpDown = Final.UP;
                this.directionLeftRight = Final.LEFT;
            }
        }
    }

    public Coordinate getPosition() {
        return position;
    }

    public int getAgility() {
        return agility;
    }

    public int getType() {
        return type;
    }

    public void makeVisible() {
        this.visibility = true;
    }

    public void makeInvisible() {
        this.visibility = false;
    }

    public boolean getVisibility() {
        return this.visibility;
    }

    public void move(Coordinate playerPos, List<Coordinate> used, List<MapPoint> roomPoints) {
        Random rand = new Random();
        switch (this.type) {
            case Final.ZOMBIE, Final.VAMPIRE -> {
                if (Math.abs((playerPos.getX() - position.getX())) <= hostility && Math.abs((playerPos.getY() - position.getY())) <= hostility) {
                    int x = position.getX();
                    int y = position.getY();
                    if (playerPos.getX() > x) x += 1;
                    else if (playerPos.getX() < x) x -= 1;
                    else if (playerPos.getY() > y) y += 1;
                    else if (playerPos.getY() < y) y -= 1;
                    Coordinate pos = new Coordinate(x, y);
                    if (checkIsEmpty(pos, used, playerPos)) {
                        findAndDeleteCoordinate(this.position, used);
                        this.position = pos;
                        used.add(pos);
                    }
                }
            }
            case Final.GHOST -> {
                Coordinate pos = roomPoints.get(rand.nextInt(roomPoints.size())).getCoordinate();
                while (!checkIsEmpty(pos, used, playerPos)) {
                    pos = roomPoints.get(rand.nextInt(roomPoints.size())).getCoordinate();
                }
                findAndDeleteCoordinate(this.position, used);
                this.position = pos;
                used.add(pos);
                int chance = rand.nextInt(100);
                if (chance >= 50) {
                    if (getVisibility()) makeInvisible();
                    else makeVisible();
                }
            }
            case Final.OGRE -> {
                if (Math.abs((playerPos.getX() - position.getX())) <= hostility && Math.abs((playerPos.getY() - position.getY())) <= hostility) {
                    int x = position.getX();
                    int y = position.getY();
                    if (playerPos.getX() > x + 1) x += 2;
                    else if (playerPos.getX() < x + 1) x -= 2;
                    else if (playerPos.getY() > y + 1) y += 2;
                    else if (playerPos.getY() < y + 1) y -= 2;
                    Coordinate pos = new Coordinate(x, y);
                    if (checkIsEmpty(pos, used, playerPos) && checkInRoom(pos, roomPoints)) {
                        findAndDeleteCoordinate(this.position, used);
                        this.position = pos;
                        used.add(pos);
                    }
                }
            }
            case Final.SNAKE -> {
                if (Math.abs((playerPos.getX() - position.getX())) <= hostility && Math.abs((playerPos.getY() - position.getY())) <= hostility) {
                    int x = position.getX();
                    int y = position.getY();
                    if (playerPos.getX() > x) {
                        if (directionUpDown == Final.UP) {
                            x += 1;
                            y += 1;
                            directionUpDown = Final.DOWN;
                        } else {
                            x += 1;
                            y -= 1;
                            directionUpDown = Final.UP;
                        }
                    } else if (playerPos.getX() < x) {
                        if (directionUpDown == Final.UP) {
                            x -= 1;
                            y += 1;
                            directionUpDown = Final.DOWN;
                        } else {
                            x -= 1;
                            y -= 1;
                            directionUpDown = Final.UP;
                        }
                    } else if (playerPos.getY() > y) {
                        if (directionLeftRight == Final.LEFT) {
                            x -= 1;
                            y += 1;
                            directionUpDown = Final.RIGHT;
                        } else {
                            x += 1;
                            y += 1;
                            directionUpDown = Final.LEFT;
                        }
                    } else if (playerPos.getY() < y) {
                        if (directionLeftRight == Final.LEFT) {
                            x -= 1;
                            y -= 1;
                            directionUpDown = Final.RIGHT;
                        } else {
                            x += 1;
                            y -= 1;
                            directionUpDown = Final.LEFT;
                        }
                    }
                    Coordinate pos = new Coordinate(x, y);
                    if (checkIsEmpty(pos, used, playerPos) && checkInRoom(pos, roomPoints)) {
                        findAndDeleteCoordinate(this.position, used);
                        this.position = pos;
                        used.add(pos);
                    }
                }
            }
        }
    }

    private boolean checkIsEmpty(Coordinate pos, List<Coordinate> used, Coordinate playerPos) {
        for (Coordinate coordinate : used) {
            if (coordinate.getX() == pos.getX() && coordinate.getY() == pos.getY()) return false;
        }
        return playerPos.getX() != pos.getX() || playerPos.getY() != pos.getY();
    }

    public void findAndDeleteCoordinate(Coordinate pos, List<Coordinate> used) {
        for (int i = 0; i < used.size(); i++) {
            if (used.get(i).getX() == pos.getX() && used.get(i).getY() == pos.getY()) {
                used.remove(i);
                break;
            }
        }
    }

    public boolean checkInRoom(Coordinate coordinate, List<MapPoint> roomPoints) {
        for (MapPoint roomPoint : roomPoints) {
            if (roomPoint.getCoordinate().getX() == coordinate.getX() && roomPoint.getCoordinate().getY() == coordinate.getY())
                return true;
        }
        return false;
    }

    public void setAttackCounter() {
        this.attackCounter++;
    }

    public boolean getSleep() {
        return this.sleep;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setSleep() {
        this.sleep = !this.sleep;
    }

    public void setHealth(int damage) {
        this.health -= damage;
    }

    public int getAttackCounter() {
        return this.attackCounter;
    }

    public int getHealth() {
        return this.health;
    }

    public int getGold() {
        return gold;
    }
}
