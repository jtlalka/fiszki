package net.tlalka.android.fiszki.elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum LessonElement {

    COLORS("Lekcja 1", "Kolory"),
    ANIMALS("Lekcja 2", "Zwierzeta"),
    FRUITS("Lekcja 3", "Owoce"),
    SPORT("Lekcja 4", "Sport"),
    WORK("Lekcja 5", "Zawory, praca"),
    FOOD("Lekcja 6", "Jedzenie");

    private String name;
    private String desc;

    private static final Map<String, LessonElement> menuLementMap = new HashMap<>();
    private static final List<String> menuLementList = new LinkedList<>();

    static {
        for (final LessonElement menuElement : LessonElement.values()) {
            menuLementMap.put(menuElement.getName(), menuElement);
            menuLementList.add(menuElement.getName());
        }
    }

    private LessonElement(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static LessonElement getValue(String key) {
        return menuLementMap.get(key);
    }

    public static List<String> getKeys() {
        return menuLementList;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return this.desc;
    }
}
