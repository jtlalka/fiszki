package net.tlalka.android.fiszki.test.modules;

import android.app.Application;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.test.DependencyProvider;
import net.tlalka.android.fiszki.view.navigations.Navigator;

public class TestApplicationModule extends ApplicationModule {

    public TestApplicationModule(Application application) {
        super(application);
    }

    @Override
    public Application getApplication() {
        return super.getApplication();
    }

    @Override
    public Navigator getNavigator() {
        return DependencyProvider.getNavigator();
    }
}
