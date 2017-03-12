package net.tlalka.android.fiszki.test;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ActivityMatchers {

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
