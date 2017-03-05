package net.tlalka.android.fiszki.view.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LessonListActivityAndroidTest extends AndroidBaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        clickListItem(R.id.main_list_view, 0);
    }

    @Test
    public void testSelectLesson() {

        // given
        getEspressoComponent(activityRule.getActivity()).inject(this);

        // when
        clickListItem(R.id.lesson_list_view, 0);

        // then
        isVisible(R.id.lesson_show_word);
        isVisible(R.id.lesson_check_word);
    }
}
