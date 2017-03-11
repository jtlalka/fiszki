package net.tlalka.android.fiszki.view.pages;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

public abstract class AbstractPage {

    protected static void clickItem(int resId) {
        onView(withId(resId)).perform(click());
    }

    protected static void clickItem(int resId, int stringId) {
        onView(allOf(withId(resId), withText(stringId))).perform(click());
    }

    protected static void clickListItem(int resId, int itemNumber) {
        onData(anything()).inAdapterView(withId(resId)).atPosition(itemNumber).perform(click());
    }

    protected static void isVisible(int resId) {
        onView(withId(resId)).check(matches(isDisplayed()));
    }

    protected static void isVisible(int resId, int stringId) {
        onView(allOf(withId(resId), withText(stringId))).check(matches(isDisplayed()));
    }
}
