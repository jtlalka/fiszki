package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.annimon.stream.IntStream;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class LessonListActivityAndroidTest extends AbstractAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    public ListController listController;

    @Before
    public void setup() {
        getEspressoComponent(activityRule.getActivity()).inject(this);
    }

    @Test
    public void openLessonFromList() {
        IntStream
                .range(0, listController.getLessonList().size())
                .forEach(this::openLessonByIndex);
    }

    private void openLessonByIndex(int lessonIndex) {

        // given
        mainPage.valid();
        mainPage.openLessons();

        // when
        lessonListPage.valid();
        lessonListPage.openLesson(lessonIndex);

        // then
        lessonPage.valid();
        lessonPage.closeActivity();
    }
}
