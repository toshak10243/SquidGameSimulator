// File: entity/Game.java
package entity;

public class Game {
    private int level;
    private String name;
    private String backgroundImagePath;

    public Game(int level, String name, String backgroundImagePath) {
        this.level = level;
        this.name = name;
        this.backgroundImagePath = backgroundImagePath;
    }

    public int getLevel() { return level; }
    public String getName() { return name; }
    public String getBackgroundImagePath() { return backgroundImagePath; }
}
