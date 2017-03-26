package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.annimon.stream.IntStream;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.domain.services.WordService;
import net.tlalka.android.fiszki.model.entities.Word;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TestAndroidTest extends AbstractAndroidTest {

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
    public void simulateFullTest() {

        // given
        List<Word> words = getWords(0);

        mainPage.openTests();
        testListPage.openItem(0);

        // when
        IntStream.range(0, words.size()).forEach(i -> {
            testPage.matchProgress(i + 1);

            String wordValue = testPage.getWord();
            String transValue = getTranslation(words, wordValue);

            if (testPage.getAnswer1().equals(transValue)) {
                testPage.clickAnswer1();
            } else if (testPage.getAnswer2().equals(transValue)) {
                testPage.clickAnswer2();
            } else if (testPage.getAnswer3().equals(transValue)) {
                testPage.clickAnswer3();
            } else if (testPage.getAnswer4().equals(transValue)) {
                testPage.clickAnswer4();
            }
        });

        // then (score)
        testScorePage.valid();
        testScorePage.clickTests();

        // then (tests)
        testListPage.valid();
    }

    @Test
    public void simulateInvalidAnswer() {

        // given
        mainPage.openTests();
        testListPage.openItem(0);
        testPage.matchProgress(1);

        // when
        String wordValue = testPage.getWord();
        String transValue = getTranslation(getWords(0), wordValue);

        if (!testPage.getAnswer1().equals(transValue)) {
            testPage.clickAnswer1();
        } else if (!testPage.getAnswer2().equals(transValue)) {
            testPage.clickAnswer2();
        } else if (!testPage.getAnswer3().equals(transValue)) {
            testPage.clickAnswer3();
        } else if (!testPage.getAnswer4().equals(transValue)) {
            testPage.clickAnswer4();
        }

        // then (test)
        testPage.valid();
        testPage.matchProgress(1);
        testPage.closeActivity();

        // then (main)
        mainPage.valid();
    }

    private List<Word> getWords(int index) {
        return wordService.getWords(listController.getLessonList().get(index));
    }

    private String getTranslation(List<Word> words, String wordValue) {
        return wordService.getTranslation(getWord(words, wordValue), testTranslation).getValue();
    }

    private Word getWord(List<Word> words, String wordValue) {
        return Stream.of(words)
                .filter(w -> w.getValue().equals(wordValue))
                .single();
    }
}
