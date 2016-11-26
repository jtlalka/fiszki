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

    private static final Map<String, LessonElement> menuElementMap = new HashMap<>();
    private static final List<String> menuElementList = new LinkedList<>();

    static {
        for (final LessonElement menuElement : LessonElement.values()) {
            menuElementMap.put(menuElement.getName(), menuElement);
            menuElementList.add(menuElement.getName());
        }
    }

    private LessonElement(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static LessonElement getValue(String key) {
        return menuElementMap.get(key);
    }

    public static List<String> getKeys() {
        return menuElementList;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return this.desc;
    }
}
