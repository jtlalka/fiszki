package net.tlalka.android.fiszki.view.pages;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static net.tlalka.android.fiszki.test.ActivityActions.readTest;
import static net.tlalka.android.fiszki.test.ActivityActions.waitForUpdate;
import static net.tlalka.android.fiszki.test.ActivityMatchers.isDrawable;
import static net.tlalka.android.fiszki.test.ActivityMatchers.textMatches;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

public abstract class AbstractPage {

    public abstract void valid();

    protected void clickItem(int resId) {
        onView(withId(resId)).perform(click());
    }

    protected void clickItem(int resId, int stringId) {
        onView(allOf(withId(resId), withText(stringId))).perform(click());
    }

    protected void clickListItem(int resId, int itemNumber) {
        onData(anything()).inAdapterView(withId(resId)).atPosition(itemNumber).perform(click());
    }

    protected void matchText(int resId, String pattern) {
        onView(withId(resId)).check(matches(textMatches(pattern)));
    }

    protected void isText(int resId, String text) {
        onView(withId(resId)).check(matches(withText(text)));
    }

    protected void isVisible(int resId) {
        onView(withId(resId)).check(matches(isDisplayed()));
    }

    protected void isVisible(int resId, int stringId) {
        onView(allOf(withId(resId), withText(stringId))).check(matches(isDisplayed()));
    }

    protected void isImageDescription(int descriptionId) {
        onView(allOf(withContentDescription(descriptionId), isDrawable())).check(matches(isDisplayed()));
    }

    protected void isUpdated(int resId) {
        onView(withId(resId)).perform(waitForUpdate(TimeUnit.SECONDS.toMillis(1)));
    }

    public String getText(int resId) {
        AtomicReference<String> reference = new AtomicReference<>();
        onView(withId(resId)).perform(readTest(reference));
        return reference.get();
    }
}
