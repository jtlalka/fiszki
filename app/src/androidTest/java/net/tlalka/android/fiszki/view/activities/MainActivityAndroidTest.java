package net.tlalka.android.fiszki.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.test.DependencyProvider;
import net.tlalka.android.fiszki.view.navigations.Navigator;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @BeforeClass
    public static void testSetup() {
        DependencyProvider.setNavigator(new Navigator());
        DependencyProvider.setWelcomeDto(new WelcomeDto());
    }

    @Test
    public void testShouldOpenMainActivity() {

        // given
        StorageHelper storageHelper = new StorageHelper(getContext());
        storageHelper.setBoolean(StorageType.WELCOME_VIEW, true);
        activityRule.launchActivity(new Intent());

        // when
        onView(withId(R.id.welcome_layout)).perform(click());

        // then
        onView(withId(R.id.main_list_view)).check(matches(isDisplayed()));
    }

    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
}
