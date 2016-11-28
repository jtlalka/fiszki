package net.tlalka.android.fiszki.elements;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.activities.HelpActivity;
import net.tlalka.android.fiszki.activities.LessonListActivity;
import net.tlalka.android.fiszki.activities.StartActivity;
import net.tlalka.android.fiszki.activities.WordsActivity;

public enum MenuElement {

    LESSONS(R.string.nav_lessons, LessonListActivity.class),
    TESTS(R.string.nav_tests, StartActivity.class),
    OPTIONS(R.string.nav_words, WordsActivity.class),
    HELP(R.string.nav_help, HelpActivity.class);

    private final int resourceId;
    private final Class<?> activityClass;

    MenuElement(int resourceId, Class<?> activityClass) {
        this.resourceId = resourceId;
        this.activityClass = activityClass;
    }

    public Integer getResourceId() {
        return this.resourceId;
    }

    public Class<?> getActivityClass() {
        return this.activityClass;
    }
}
