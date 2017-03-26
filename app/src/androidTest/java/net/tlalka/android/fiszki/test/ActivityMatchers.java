package net.tlalka.android.fiszki.test;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ActivityMatchers {

    public static Matcher<View> textMatches(String pattern) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("text pattern matches");
            }

            @Override
            public boolean matchesSafely(TextView textView) {
                return textView.getText().toString().matches(pattern);
            }
        };
    }

    public static Matcher<View> isDrawable() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable image");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }
}
