package net.tlalka.android.fiszki.view.activities;

import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.test.ActivityLazyRule;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityAndroidTest extends AndroidBaseTest {

    @Rule
    public ActivityLazyRule<MainActivity> activityRule = new ActivityLazyRule<>(MainActivity.class);

    @Test
    public void testShouldOpenMainActivity() {

        // when
        activityRule.launchActivity();

        // then
        isVisible(R.id.main_list_view);
    }
}
