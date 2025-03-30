package Domain;

import java.util.Random;

public class Item {
    private final int type;
    private int subtype;
    private int health;
    private int maxHealth;
    private int agility;
    private int strength;
    private int cost;
    private boolean visibility;
    private final Coordinate position;
    private boolean inGame;

    public Item(int level, Coordinate position) {
        this.inGame = true;
        this.position = position;
        this.visibility = false;
        Random rand = new Random();
        this.type = rand.nextInt(6);
        this.health = 0;
        this.maxHealth = 0;
        this.agility = 0;
        this.strength = 0;
        this.cost = 0;
        this.subtype = 11;
        switch (type) {
            case Final.GOLD -> {
                this.cost = 21;
            }
            case Final.FOOD -> {
                this.health = 1;
            }
            case Final.AGILITY -> {
                this.agility = 1;
            }
            case Final.STRENGTH -> {
                this.strength = level + 1;
            }
            case Final.MAX_HEALTH -> {
                this.maxHealth = 21;
            }
            case Final.ELIXIR -> {
                this.subtype = rand.nextInt(11);
                switch (subtype) {
                    case Final.ELIXIR_PLUS_MAX_HEALTH -> {
                        this.maxHealth = 21;
                    }
                    case Final.ELIXIR_MINUS_MAX_HEALTH -> {
                        this.maxHealth = -21;
                    }
                    case Final.ELIXIR_PLUS_AGILITY -> {
                        this.agility = 1;
                    }
                    case Final.ELIXIR_MINUS_AGILITY -> {
                        this.agility = -1;
                    }
                    case Final.ELIXIR_PLUS_STRENGTH -> {
                        this.strength = level + 1;
                    }
                    case Final.ELIXIR_MINUS_STRENGTH -> {
                        this.strength = -level + 1;
                    }
                    case Final.ELIXIR_ZERO_GOLD -> {
                        this.cost = 0;
                    }
                    case Final.ELIXIR_MAX_AGILITY -> {
                        this.agility = Final.MAX_AGILITY;
                    }
                    case Final.ELIXIR_MIN_AGILITY -> {
                        this.agility = 1;
                    }
                    case Final.ELIXIR_MIN_HEALTH -> {
                        this.health = 1;
                    }
                    case Final.ELIXIR_MIN_STRENGTH -> {
                        this.strength = 1;
                    }
                }
            }
        }
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

    public int getType() {
        return type;
    }

    public Coordinate getPosition() {
        return position;
    }

    public int getCost() {
        return cost;
    }

    public int getStrength() {
        return strength;
    }

    public int getSubtype() {
        return subtype;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame() {
        this.inGame = false;
    }
}