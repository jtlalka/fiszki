package net.tlalka.android.fiszki.view.activities;

import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.test.ActivityLazyRule;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityAndroidTest extends AndroidBaseTest {

    @Rule
    public ActivityLazyRule<MainActivity> activityRule = new ActivityLazyRule<>(MainActivity.class);

    @Before
    public void setup() {
        StorageHelper storageHelper = new StorageHelper(getContext());
        storageHelper.setBoolean(StorageType.WELCOME_VIEW, true);

        activityRule.launchActivity();
    }

    @Test
    public void clickOnWelcomeMessage() {

        // given
        String message = activityRule.getActivity().getString(R.string.welcome_message);

        // when
        onView(allOf(withId(R.id.welcome_message), withText(message))).perform(click());

        // then
        onView(withId(R.id.main_list_view)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnActivityLayout() {

        // when
        onView(withId(R.id.welcome_layout)).perform(click());

        // then
        onView(withId(R.id.main_list_view)).check(matches(isDisplayed()));
    }
}
