package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainAndroidTest extends AbstractAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openLessonsActivity() {

        // given
        mainPage.valid();

        // when
        mainPage.openLessons();

        // then
        lessonListPage.valid();
    }

    @Test
    public void openTestsActivity() {

        // given
        mainPage.valid();

        // when
        mainPage.openTests();

        // then
        testListPage.valid();
    }
}
