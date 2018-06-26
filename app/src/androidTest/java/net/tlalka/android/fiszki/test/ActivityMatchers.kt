package net.tlalka.android.fiszki.test

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import org.hamcrest.Description
import org.hamcrest.Matcher

object ActivityMatchers {

    fun isDrawable(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has drawable image")
            }

            public override fun matchesSafely(imageView: ImageView): Boolean {
                return imageView.drawable != null
            }
        }
    }

    fun textMatches(pattern: String): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("text pattern matches")
            }

            public override fun matchesSafely(textView: TextView): Boolean {
                return textView.text.toString().matches(pattern.toRegex())
            }
        }
    }
}
