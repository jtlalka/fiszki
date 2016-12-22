package net.tlalka.android.fiszki.services;

import android.app.Application;
import android.content.Context;
import net.tlalka.android.fiszki.injections.components.ApplicationComponent;
import net.tlalka.android.fiszki.injections.components.DaggerApplicationComponent;
import net.tlalka.android.fiszki.injections.components.SessionComponent;
import net.tlalka.android.fiszki.injections.modules.ApplicationModule;
import net.tlalka.android.fiszki.injections.modules.SessionModule;

public class AppHelper extends Application {

    private static Context context;

    private ApplicationComponent applicationComponent;

    private SessionComponent sessionComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();

        //TODO: remove static dependency
        context = this;
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

    public static Context getContext() {
        return context;
    }
}
