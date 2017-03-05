package net.tlalka.android.fiszki.test;

import android.app.Activity;
import net.tlalka.android.fiszki.core.AppFiszki;
import net.tlalka.android.fiszki.core.components.ActivityComponent;
import net.tlalka.android.fiszki.core.components.DaggerApplicationComponent;
import net.tlalka.android.fiszki.test.modules.TestActivityModule;
import net.tlalka.android.fiszki.test.modules.TestApplicationModule;
import net.tlalka.android.fiszki.test.modules.TestSessionModule;

public class TestFiszki extends AppFiszki {

    @Override
    public ActivityComponent getActivityComponent(Activity activity) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new TestApplicationModule(this))
                .build()
                .add(new TestSessionModule(getBaseContext()))
                .add(new TestActivityModule(activity));
    }
}
