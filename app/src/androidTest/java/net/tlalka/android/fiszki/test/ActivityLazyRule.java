package net.tlalka.android.fiszki.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

public class ActivityLazyRule<T extends Activity> extends ActivityTestRule<T> {

    public ActivityLazyRule(Class<T> activityClass) {
        super(activityClass, true, false);
    }

    public T launchActivity() {
        return super.launchActivity(super.getActivityIntent());
    }
}
