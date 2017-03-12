package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LessonListActivityAndroidTest extends AndroidBaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void selectFirstLesson() {

        // given
        mainPage.valid();
        mainPage.openLessons();

        // when
        lessonListPage.valid();
        lessonListPage.openLesson(0);

        // then
        lessonPage.valid();
    }
}
