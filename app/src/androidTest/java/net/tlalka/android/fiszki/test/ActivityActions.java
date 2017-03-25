package net.tlalka.android.fiszki.test;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.TreeIterables;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Matcher;

import java.util.concurrent.atomic.AtomicReference;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ActivityActions {

    public static ViewAction readTest(final AtomicReference<String> reference) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                reference.set(((TextView) view).getText().toString());
            }
        };
    }

    public static ViewAction waitForUpdate(final long timeout) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "wait for text new value";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                final String value1 = getTextById(view);

                for (int i = 0, step = 50; i < timeout; i += step) {
                    uiController.loopMainThreadForAtLeast(step);
                    String value2 = getTextById(view);

                    if (!isCompare(value1, value2)) {
                        return;
                    }
                }
            }

            private String getTextById(final View view) {
                return ((TextView) view).getText().toString();
            }

            private boolean isCompare(String value1, String value2) {
                return value1 == null && value2 == null || value1 != null && value1.equals(value2);
            }
        };
    }

    public static ViewAction waitForView(final int resId, final long timeout) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for resource id: " + resId;
            }

            @Override
            public void perform(UiController uiController, final View view) {
                for (int i = 0, step = 50; i < timeout; i += step) {
                    uiController.loopMainThreadForAtLeast(step);

                    if (isView(view, resId)) {
                        return;
                    }
                }
            }

            private boolean isView(final View view, final int resId) {
                for (View item : TreeIterables.breadthFirstViewTraversal(view)) {
                    if (withId(resId).matches(item)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
