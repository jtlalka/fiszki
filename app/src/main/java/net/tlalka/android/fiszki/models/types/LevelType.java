package net.tlalka.android.fiszki.models.types;

public enum LevelType {

    BEGINNER("0", 0),
    ELEMENTARY("A1", 100),
    INTERMEDIATE("B1", 300),
    ADVANCED("C1", 500),
    PROFICIENT("C2", 600);

    private final String name;
    private final int value;

    LevelType(String name, int value) {
        this.name = name;
        this.value= value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
