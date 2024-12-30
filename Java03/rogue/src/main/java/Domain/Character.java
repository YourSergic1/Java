package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Character {
    private Coordinate position;
    private int maxHealth;
    private int health;
    private int agility;
    private int strength;
    private int gold;
    private boolean sleep;
    private int defeatedEnemiesCounter;
    private int eatenFoodCounter;
    private int drunkElixirCounter;
    private int attackCounter;
    private int enemiesAttackCounter;
    private int boxCounter;
    private String endTime;
    private int endLevel;

    Character(Coordinate position) {
        this.endLevel = 1;
        this.endTime = "--";
        this.defeatedEnemiesCounter = 0;
        this.eatenFoodCounter = 0;
        this.drunkElixirCounter = 0;
        this.attackCounter = 0;
        this.boxCounter = 0;
        this.enemiesAttackCounter = 0;
        this.sleep = false;
        this.agility = 3;
        this.position = position;
        this.maxHealth = 20;
        this.strength = 2;
        this.health = maxHealth;
        this.gold = 0;
    }

    public List<String> move(char[][] field, int direction, Level level) {
        List<String> messages = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if (direction == Final.UP) {
            if (field[y - 1][x] == '.' || field[y - 1][x] == '*' || field[y - 1][x] == 'X') {
                position.setNew(x, y - 1);
                this.boxCounter++;
            } else if (field[y - 1][x] == 'S' || field[y - 1][x] == 'V' || field[y - 1][x] == 'G' || field[y - 1][x] == 'O' || field[y - 1][x] == 'Z') {
                messages = fight(new Coordinate(x, y - 1), level);
            } else if (field[y - 1][x] == 'F' || field[y - 1][x] == '%' || field[y - 1][x] == 'E' || field[y - 1][x] == 'A' || field[y - 1][x] == 'M' || field[y - 1][x] == '$') {
                messages = eat(new Coordinate(x, y - 1), level);
                position.setNew(x, y - 1);
                this.boxCounter++;
            }
        } else if (direction == Final.RIGHT) {
            if (field[y][x + 1] == '.' || field[y][x + 1] == '*' || field[y][x + 1] == 'X') {
                position.setNew(x + 1, y);
                this.boxCounter++;
            } else if (field[y][x + 1] == 'S' || field[y][x + 1] == 'V' || field[y][x + 1] == 'G' || field[y][x + 1] == 'O' || field[y][x + 1] == 'Z') {
                messages = fight(new Coordinate(x + 1, y), level);
            } else if (field[y][x + 1] == 'F' || field[y][x + 1] == '%' || field[y][x + 1] == 'E' || field[y][x + 1] == 'A' || field[y][x + 1] == 'M' || field[y][x + 1] == '$') {
                messages = eat(new Coordinate(x + 1, y), level);
                position.setNew(x + 1, y);
                this.boxCounter++;
            }
        } else if (direction == Final.DOWN) {
            if (field[y + 1][x] == '.' || field[y + 1][x] == '*' || field[y + 1][x] == 'X') {
                position.setNew(x, y + 1);
                this.boxCounter++;
            } else if (field[y + 1][x] == 'S' || field[y + 1][x] == 'V' || field[y + 1][x] == 'G' || field[y + 1][x] == 'O' || field[y + 1][x] == 'Z') {
                messages = fight(new Coordinate(x, y + 1), level);
            } else if (field[y + 1][x] == 'F' || field[y + 1][x] == '%' || field[y + 1][x] == 'E' || field[y + 1][x] == 'A' || field[y + 1][x] == 'M' || field[y + 1][x] == '$') {
                messages = eat(new Coordinate(x, y + 1), level);
                position.setNew(x, y + 1);
                this.boxCounter++;
            }
        } else if (direction == Final.LEFT) {
            if (field[y][x - 1] == '.' || field[y][x - 1] == '*' || field[y][x - 1] == 'X') {
                position.setNew(x - 1, y);
                this.boxCounter++;
            } else if (field[y][x - 1] == 'S' || field[y][x - 1] == 'V' || field[y][x - 1] == 'G' || field[y][x - 1] == 'O' || field[y][x - 1] == 'Z') {
                messages = fight(new Coordinate(x - 1, y), level);
            } else if (field[y][x - 1] == 'F' || field[y][x - 1] == '%' || field[y][x - 1] == 'E' || field[y][x - 1] == 'A' || field[y][x - 1] == 'M' || field[y][x - 1] == '$') {
                messages = eat(new Coordinate(x - 1, y), level);
                position.setNew(x - 1, y);
                this.boxCounter++;
            }
        }
        return messages;
    }

    public Coordinate getPosition() {
        return position;
    }

    public List<String> fight(Coordinate enemyPos, Level level) {
        List<String> messages = new ArrayList<>();
        int roomNumber = 0;
        for (int i = 1; i < level.getRooms().size(); i++) {
            if (level.getRooms().get(i).checkInRoom(enemyPos)) {
                roomNumber = i;
                break;
            }
        }
        Enemy enemy = level.getRooms().get(roomNumber).getEnemies().getFirst();
        for (int i = 1; i < level.getRooms().get(roomNumber).getEnemies().size(); i++) {
            if (enemyPos.getX() == level.getRooms().get(roomNumber).getEnemies().get(i).getPosition().getX() && enemyPos.getY() == level.getRooms().get(roomNumber).getEnemies().get(i).getPosition().getY()) {
                enemy = level.getRooms().get(roomNumber).getEnemies().get(i);
                break;
            }
        }
        Random rand = new Random();
        int chance = rand.nextInt(11);
        if (this.agility >= chance) {
            if (!this.sleep) {
                if (enemy.getType() == Final.OGRE && !enemy.getSleep()) {
                    this.health = this.health - enemy.getStrength();
                    enemy.setHealth(this.strength);
                    messages.add("You--hit--the--enemy.");
                    messages.add("Ogre--counterattacked--you.");
                    this.enemiesAttackCounter++;
                    this.attackCounter++;
                } else if (enemy.getType() == Final.VAMPIRE && enemy.getAttackCounter() == 0) {
                    messages.add("You--missed--the--hit.");
                } else {
                    enemy.setHealth(this.strength);
                    messages.add("You--hit--the--enemy.");
                    this.attackCounter++;
                }
            } else {
                messages.add("You--didn't--hit--you--are--sleeping.");
                this.sleep = false;
            }
        } else {
            messages.add("You--missed--the--hit.");
        }
        chance = rand.nextInt(11);
        if (enemy.getAgility() >= chance) {
            if (enemy.getType() == Final.VAMPIRE) {
                this.maxHealth -= enemy.getStrength();
                this.health -= enemy.getStrength();
                messages.add("Vampire--stol--your--health.");
            } else if (enemy.getType() == Final.SNAKE) {
                if (!this.sleep) {
                    chance = rand.nextInt(10);
                    if (chance > 4) {
                        this.sleep = true;
                        messages.add("Snake--put--you--to--sleep.");
                    }
                }
            } else if (enemy.getType() == Final.OGRE) {
                if (enemy.getSleep()) enemy.setSleep();
                else {
                    this.health -= enemy.getStrength();
                    messages.add("Enemy--hit--you.");
                    this.enemiesAttackCounter++;
                }
            }
            if (enemy.getType() != Final.OGRE) {
                messages.add("Enemy--hit--you.");
                this.health -= enemy.getStrength();
                this.enemiesAttackCounter++;
            }
        } else messages.add("Enemy--missed--the--hit.");
        enemy.setAttackCounter();
        return messages;
    }

    public int getGold() {
        return gold;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public void setGold(int gold) {
        this.gold = this.gold + gold;
    }

    public void setStrength(int strength) {
        this.strength = this.strength + strength;
    }


    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = this.maxHealth + maxHealth;
    }

    public void setDefeatedEnemiesCounter() {
        this.defeatedEnemiesCounter++;
    }

    public List<String> eat(Coordinate itemPos, Level level) {
        List<String> messages = new ArrayList<>();
        int roomNumber = 0;
        for (int i = 1; i < level.getRooms().size(); i++) {
            if (level.getRooms().get(i).checkInRoom(itemPos)) {
                roomNumber = i;
                break;
            }
        }
        Item item = level.getRooms().get(roomNumber).getItems().getFirst();
        for (int i = 1; i < level.getRooms().get(roomNumber).getItems().size(); i++) {
            if (itemPos.getX() == level.getRooms().get(roomNumber).getItems().get(i).getPosition().getX() && itemPos.getY() == level.getRooms().get(roomNumber).getItems().get(i).getPosition().getY()) {
                item = level.getRooms().get(roomNumber).getItems().get(i);
                break;
            }
        }
        if (item.getType() == Final.GOLD) {
            this.gold += item.getCost();
            messages.add("You--found-the-gold.");
        } else if (item.getType() == Final.FOOD) {
            this.eatenFoodCounter++;
            this.health = this.maxHealth;
            messages.add("You--ate--the--food.Your--HP-is-MAX.");
        } else if (item.getType() == Final.AGILITY) {
            if (this.agility < Final.MAX_AGILITY) {
                this.agility++;
                messages.add("You--got--the--1--agility.");
            } else {
                messages.add("Your--agility--is--max.");
            }
        } else if (item.getType() == Final.STRENGTH) {
            this.strength += item.getStrength();
            messages.add("You--got--+--to--strength.");
        } else if (item.getType() == Final.MAX_HEALTH) {
            this.maxHealth += 21;
            messages.add("Your--max--health--raised.");
        } else if (item.getType() == Final.ELIXIR) {
            this.drunkElixirCounter++;
            if (item.getSubtype() == Final.ELIXIR_PLUS_MAX_HEALTH) {
                this.maxHealth += 21;
                messages.add("You--drunk--the-elixir.");
                messages.add("Your--max--health--raised.");
            } else if (item.getSubtype() == Final.ELIXIR_MINUS_MAX_HEALTH) {
                this.maxHealth -= 21;
                if (this.health > this.maxHealth) this.health = this.maxHealth;
                messages.add("You--drunk--the-elixir.");
                messages.add("Your--max--health--reduced.");
            } else if (item.getSubtype() == Final.ELIXIR_PLUS_AGILITY) {
                if (this.agility < Final.MAX_AGILITY) {
                    this.agility++;
                    messages.add("You--drunk--the-elixir.");
                    messages.add("You--got--the--1--agility.");
                } else {
                    messages.add("You--drunk--the-elixir.");
                    messages.add("Your--agility--is--max.");
                }
            } else if (item.getSubtype() == Final.ELIXIR_MINUS_AGILITY) {
                if (this.agility > 1) {
                    this.agility--;
                    messages.add("You--drunk--the-elixir.");
                    messages.add("You--got--the--minus--1--agility.");
                } else {
                    messages.add("You--drunk--the-elixir.");
                    messages.add("Your--agility--is--min.");
                }
            } else if (item.getSubtype() == Final.ELIXIR_PLUS_STRENGTH) {
                this.strength += item.getStrength();
                messages.add("You--drunk--the-elixir.");
                messages.add("You--got--+--to--strength.");

            } else if (item.getSubtype() == Final.ELIXIR_MINUS_STRENGTH) {
                this.strength += item.getStrength();
                if (this.strength < 0) this.strength = 1;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--got--minus--to--strength.");

            } else if (item.getSubtype() == Final.ELIXIR_ZERO_GOLD) {
                this.gold = 0;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--lost--your--gold.");

            } else if (item.getSubtype() == Final.MAX_AGILITY) {
                this.agility = Final.MAX_AGILITY;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--got--max--agility.");

            } else if (item.getSubtype() == Final.ELIXIR_MIN_AGILITY) {
                this.agility = 1;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--got--min--agility.");
            } else if (item.getSubtype() == Final.ELIXIR_MIN_HEALTH) {
                this.health = 1;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--lost--your--health.");
            } else if (item.getSubtype() == Final.ELIXIR_MIN_STRENGTH) {
                this.strength = 1;
                messages.add("You--drunk--the-elixir.");
                messages.add("You--lost--your--strength.");
            }
        }
        item.setInGame();
        return messages;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefeatedEnemiesCounter() {
        return defeatedEnemiesCounter;
    }

    public int getEatenFoodCounter() {
        return eatenFoodCounter;
    }

    public int getDrunkElixirCounter() {
        return drunkElixirCounter;
    }

    public int getBoxCounter() {
        return boxCounter;
    }

    public int getAttackCounter() {
        return attackCounter;
    }

    public int getEnemiesAttackCounter() {
        return enemiesAttackCounter;
    }

    public void setEndTime() {
        // Получаем текущее время в Московской временной зоне (MSK)
        ZonedDateTime moscowTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        // Форматируем время для вывода
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd--HH:mm:ss");

        // Выводим время в терминал
        this.endTime = moscowTime.format(formatter);
    }

    public void setEndLevel(int endLevel) {
        this.endLevel = endLevel;
    }

    public int getEndLevel() {
        return endLevel;
    }

    public String getEndTime() {
        return endTime;
    }
}
