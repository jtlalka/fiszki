package net.tlalka.android.fiszki.core;

import android.app.Application;
import net.tlalka.android.fiszki.core.components.ApplicationComponent;
import net.tlalka.android.fiszki.core.components.DaggerApplicationComponent;
import net.tlalka.android.fiszki.core.components.SessionComponent;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;

public class AppFiszki extends Application {

    private ApplicationComponent applicationComponent;

    private SessionComponent sessionComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        this.sessionComponent = this.applicationComponent
                .add(new SessionModule(getBaseContext()));
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public SessionComponent getSessionComponent() {
        return this.sessionComponent;
    }
}
