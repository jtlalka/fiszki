package net.tlalka.android.fiszki.view.activities;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.test.DependencyProvider;
import net.tlalka.android.fiszki.view.navigations.Navigator;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityAndroidTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> activityRule = new ActivityTestRule<>(WelcomeActivity.class);

    @BeforeClass
    public static void testSetup() {
        DependencyProvider.setNavigator(Mockito.mock(Navigator.class));
        DependencyProvider.setWelcomeDto(Mockito.mock(WelcomeDto.class));
    }

    @Test
    public void testCloseWelcomeActivity() {

        // given
        Navigator navigator = DependencyProvider.getNavigator();

        // when
        onView(withId(R.id.welcome_layout)).perform(click());

        // then
        verify(navigator, times(1)).finish(any(Activity.class));
    }
}
