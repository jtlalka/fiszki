package net.tlalka.android.fiszki.view.elements;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.view.activities.BasePageActivity;
import net.tlalka.android.fiszki.view.activities.HelpActivity;
import net.tlalka.android.fiszki.view.activities.LessonListActivity;
import net.tlalka.android.fiszki.view.activities.TestListActivity;
import net.tlalka.android.fiszki.view.activities.WordsActivity;

public enum PageElement {

    LESSONS(R.string.nav_lessons, LessonListActivity.class),
    TESTS(R.string.nav_tests, TestListActivity.class),
    WORDS(R.string.nav_words, WordsActivity.class),
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
