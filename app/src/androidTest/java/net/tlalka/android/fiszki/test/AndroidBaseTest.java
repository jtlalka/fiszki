package net.tlalka.android.fiszki.test;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.test.components.DaggerTestApplicationComponent;
import net.tlalka.android.fiszki.test.components.TestEspressoComponent;
import org.junit.BeforeClass;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

public abstract class AndroidBaseTest {

    @BeforeClass
    public static void setupApplication() {
        StorageHelper storageHelper = new StorageHelper(getContext());
        storageHelper.setBoolean(StorageType.WELCOME_VIEW, false);
        storageHelper.setEnum(StorageType.LANGUAGE, LanguageType.EN);
        storageHelper.setEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    protected static void clickItem(int resId) {
        onView(withId(resId)).perform(click());
    }

    protected static void clickListItem(int resId, int itemNumber) {
        onData(anything()).inAdapterView(withId(resId)).atPosition(itemNumber).perform(click());
    }

    protected static void isVisible(int resId) {
        onView(withId(resId)).check(matches(isDisplayed()));
    }

    protected static Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    protected static TestEspressoComponent getEspressoComponent(Activity activity) {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(activity.getApplication()))
                .build()
                .add(new SessionModule(activity.getBaseContext()))
                .add(new ActivityModule(activity));
    }
}
