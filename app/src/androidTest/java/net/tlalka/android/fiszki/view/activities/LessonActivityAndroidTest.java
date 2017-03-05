package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.domain.services.CacheService;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LessonActivityAndroidTest extends AndroidBaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    public ListController listController;

    @Inject
    public CacheService cacheService;

    @Before
    public void setup() {
        clickListItem(R.id.main_list_view, 0);
        clickListItem(R.id.lesson_list_view, 0);

        // DI
        getEspressoComponent(activityRule.getActivity()).inject(this);
    }

    @Test
    public void testSimulateLessonActivity() {

        // given
        Lesson lesson = listController.getLessonList().get(0);
        List<Word> words = cacheService.getWords(lesson);

        // when
        for (int i = 0; i < words.size(); i++) {
            clickItem(R.id.button_correct);
        }

        // then
        isVisible(R.id.lesson_score_total);
    }
}
