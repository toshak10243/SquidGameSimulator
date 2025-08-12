package entity;

public class Player {
    private int id;
    private String name;
    private int age;
    private String status;
    private int currentGameLevel;

    // ✅ Default constructor (required for GameManager)
    public Player() {}

    // ✅ Parameterized constructors
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.status = "Active";
        this.currentGameLevel = 1;
    }

    public Player(int id, String name, int age, String status, int currentGameLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
        this.currentGameLevel = currentGameLevel;
    }

    // ✅ Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getStatus() { return status; }
    public int getCurrentGameLevel() { return currentGameLevel; }

    // ✅ Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setStatus(String status) { this.status = status; }
    public void setCurrentGameLevel(int level) { this.currentGameLevel = level; }
}
