package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.annimon.stream.IntStream;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.domain.services.CacheService;
import net.tlalka.android.fiszki.model.entities.Lesson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class TestAndroidTest extends AbstractAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    public ListController listController;

    @Inject
    public CacheService cacheService;

    @Before
    public void setup() {
        getEspressoComponent(activityRule.getActivity()).inject(this);
    }

    @Test
    public void simulateFullTest() {

        // given
        mainPage.openTests();
        testListPage.openItem(0);

        // when
        IntStream.range(0, getWordSize()).forEach(i -> {
            testPage.valid();
        });

        // then
    }

    @Test
    public void closeTestActivity() {

        // given
        mainPage.valid();
        mainPage.openTests();

        testListPage.valid();
        testListPage.openItem(0);

        // when
        testPage.valid();
        testPage.closeActivity();

        // then
        mainPage.valid();
    }

    private int getWordSize() {
        Lesson lesson = listController.getLessonList().get(0);
        return cacheService.getWords(lesson).size();
    }
}
