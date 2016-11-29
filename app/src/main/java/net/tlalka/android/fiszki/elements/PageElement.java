package net.tlalka.android.fiszki.elements;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.activities.BasePageActivity;
import net.tlalka.android.fiszki.activities.HelpActivity;
import net.tlalka.android.fiszki.activities.LessonsActivity;
import net.tlalka.android.fiszki.activities.WordsActivity;

public enum PageElement {

    LESSONS(R.string.nav_lessons, LessonsActivity.class),
    TESTS(R.string.nav_tests, WordsActivity.class),
    OPTIONS(R.string.nav_words, WordsActivity.class),
    HELP(R.string.nav_help, HelpActivity.class);

    private final int resourceId;
    private final Class<? extends BasePageActivity> activityClass;

    PageElement(int resourceId, Class<? extends BasePageActivity> activityClass) {
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
