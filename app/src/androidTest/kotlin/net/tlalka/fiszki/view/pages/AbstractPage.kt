package net.tlalka.fiszki.view.pages

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import net.tlalka.fiszki.test.ActivityActions.readTest
import net.tlalka.fiszki.test.ActivityActions.waitForUpdate
import net.tlalka.fiszki.test.ActivityMatchers.isDrawable
import net.tlalka.fiszki.test.ActivityMatchers.textMatches
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference



abstract class AbstractPage {

    abstract fun valid()

    protected fun clickItem(resId: Int) {
        onView(withId(resId))
                .perform(click())
    }

    protected fun clickItem(resId: Int, stringId: Int) {
        onView(allOf(withId(resId), withText(stringId)))
                .perform(click())
    }

    protected fun clickItem(resId: Int, text: String) {
        onView(allOf(withId(resId), withText(text)))
                .perform(click())
    }

    protected fun clickListItem(resId: Int, itemNumber: Int) {
        onData(anything())
                .inAdapterView(withId(resId))
                .atPosition(itemNumber)
                .perform(click())
    }

    protected fun matchText(resId: Int, pattern: String) {
        onView(withId(resId))
                .check(matches(textMatches(pattern)))
    }

    protected fun isText(resId: Int, text: String) {
        onView(withId(resId))
                .check(matches(withText(text)))
    }

    protected fun isVisible(resId: Int) {
        onView(withId(resId))
                .check(matches(isDisplayed()))
    }

    protected fun isVisible(resId: Int, stringId: Int) {
        onView(allOf(withId(resId), withText(stringId)))
                .check(matches(isDisplayed()))
    }

    protected fun isImageDescription(descriptionId: Int) {
        onView(allOf(withContentDescription(descriptionId), isDrawable()))
                .check(matches(isDisplayed()))
    }

    protected fun isUpdated(resId: Int) {
        onView(withId(resId))
                .perform(waitForUpdate(TimeUnit.SECONDS.toMillis(1)))
    }

    protected fun getText(resId: Int): String {
        val reference = AtomicReference<String>()
        onView(withId(resId)).perform(readTest(reference))
        return reference.get()
    }
}
