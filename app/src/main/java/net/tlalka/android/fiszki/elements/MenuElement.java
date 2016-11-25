package net.tlalka.android.fiszki.elements;

import net.tlalka.android.fiszki.activities.HelpActivity;
import net.tlalka.android.fiszki.activities.LessonsListActivity;
import net.tlalka.android.fiszki.activities.OptionsActivity;
import net.tlalka.android.fiszki.activities.StartActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum MenuElement {

    LESSONS("Lekcje", LessonsListActivity.class),
    TESTS("Testy", StartActivity.class),
    OPTIONS("Opcje", OptionsActivity.class),
    HELP("Pomoc", HelpActivity.class);

    private String name;
    private Class<?> classValue;

    private static final Map<String, MenuElement> menuLementMap = new HashMap<>();
    private static final List<String> menuLementList = new LinkedList<>();

    static {
        for (final MenuElement menuElement : MenuElement.values()) {
            menuLementMap.put(menuElement.getName(), menuElement);
            menuLementList.add(menuElement.getName());
        }
    }

    private MenuElement(String name, Class<?> classValue) {
        this.name = name;
        this.classValue = classValue;
    }

    public static MenuElement getValue(String key) {
        return menuLementMap.get(key);
    }

    public static List<String> getKeys() {
        return menuLementList;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getClassValue() {
        return this.classValue;
    }
}
