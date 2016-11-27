package net.tlalka.android.fiszki.elements;

import net.tlalka.android.fiszki.activities.HelpActivity;
import net.tlalka.android.fiszki.activities.LessonListActivity;
import net.tlalka.android.fiszki.activities.WordsActivity;
import net.tlalka.android.fiszki.activities.StartActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum MenuElement {

    LESSONS("Lekcje", LessonListActivity.class),
    TESTS("Testy", StartActivity.class),
    OPTIONS("Opcje", WordsActivity.class),
    HELP("Pomoc", HelpActivity.class);

    private String name;
    private Class<?> classValue;

    private static final Map<String, MenuElement> menuMap = new HashMap<>();
    private static final List<String> menuList = new LinkedList<>();

    static {
        for (final MenuElement menuElement : MenuElement.values()) {
            menuMap.put(menuElement.getName(), menuElement);
            menuList.add(menuElement.getName());
        }
    }

    private MenuElement(String name, Class<?> classValue) {
        this.name = name;
        this.classValue = classValue;
    }

    public static MenuElement getValue(String key) {
        return menuMap.get(key);
    }

    public static List<String> getKeys() {
        return menuList;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getClassValue() {
        return this.classValue;
    }
}
