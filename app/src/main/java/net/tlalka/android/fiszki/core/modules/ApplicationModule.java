package net.tlalka.android.fiszki.core.modules;

import javax.inject.Singleton;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.view.navigations.Navigator;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application getApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    public Navigator getNavigator() {
        return new Navigator();
    }
}
