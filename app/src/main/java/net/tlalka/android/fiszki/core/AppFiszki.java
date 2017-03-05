package net.tlalka.android.fiszki.core;

import android.app.Activity;
import android.app.Application;
import net.tlalka.android.fiszki.core.components.ActivityComponent;
import net.tlalka.android.fiszki.core.components.ApplicationComponent;
import net.tlalka.android.fiszki.core.components.DaggerApplicationComponent;
import net.tlalka.android.fiszki.core.components.SessionComponent;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;

public class AppFiszki extends Application {

    private ApplicationComponent applicationComponent;

    private SessionComponent sessionComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = initApplicationComponent();
        this.sessionComponent = initSessionComponent();
    }

    private ApplicationComponent initApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private SessionComponent initSessionComponent() {
        return this.applicationComponent.add(new SessionModule(getBaseContext()));
    }

    public ActivityComponent getActivityComponent(Activity activity) {
        return this.sessionComponent.add(new ActivityModule(activity));
    }
}
