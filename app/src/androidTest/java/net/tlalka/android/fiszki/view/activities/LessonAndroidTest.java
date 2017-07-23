package net.tlalka.android.fiszki.view.activities;

import javax.inject.Inject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.annimon.stream.IntStream;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.domain.services.WordService;
import net.tlalka.android.fiszki.model.entities.Lesson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LessonAndroidTest extends AbstractAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    public ListController listController;

    @Inject
    public WordService wordService;

    @Before
    public void setup() {
        getEspressoComponent(activityRule.getActivity()).inject(this);
    }

    @Test
    public void simulateFullLesson() {

        // given
        mainPage.openLessons();
        lessonListPage.openItem(0);

        // when
        IntStream.range(0, getWordSize()).forEach(i -> {
            lessonPage.clickTranslation();
            lessonPage.clickCorrect();
        });

        // then (score)
        lessonScorePage.valid();
        lessonScorePage.clickLessons();

        // then (lesson)
        lessonListPage.valid();
    }

    @Test
    public void simulateInvalidAnswer() {

        // given
        mainPage.openLessons();
        lessonListPage.openItem(0);

        // when
        lessonPage.clickIncorrect();
        lessonPage.clickIncorrect();

        // then (lesson)
        lessonPage.valid();
        lessonPage.matchProgress(getWordSize());
        lessonPage.closeActivity();

        // then (main)
        mainPage.valid();
    }

    private int getWordSize() {
        Lesson lesson = listController.getLessonList().get(0);
        return wordService.getWords(lesson).size();
    }
}
