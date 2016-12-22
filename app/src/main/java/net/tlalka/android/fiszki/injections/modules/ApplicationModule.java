package net.tlalka.android.fiszki.injections.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.navigations.Navigator;

import javax.inject.Singleton;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator getNavigator() {
        return new Navigator();
    }
}
