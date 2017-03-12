package net.tlalka.android.fiszki.view.activities;

import android.support.test.runner.AndroidJUnit4;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.test.ActivityLazyRule;
import net.tlalka.android.fiszki.test.AndroidBaseTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        welcomePage.valid();

        // when
        welcomePage.clickMessage();

        // then
        mainPage.valid();
    }

    @Test
    public void clickOnActivityLayout() {

        // given
        welcomePage.valid();

        // when
        welcomePage.clickLayout();

        // then
        mainPage.valid();
    }
}
