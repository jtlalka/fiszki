package net.tlalka.fiszki.test

import android.app.Activity
import android.support.test.rule.ActivityTestRule

class ActivityLazyRule<T : Activity>(activityClass: Class<T>) : ActivityTestRule<T>(activityClass, true, false) {

    fun launchActivity(): T {
        return super.launchActivity(super.getActivityIntent())
    }
}
