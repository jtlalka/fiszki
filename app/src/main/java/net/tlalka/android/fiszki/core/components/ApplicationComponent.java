package net.tlalka.android.fiszki.core.components;

import dagger.Component;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    SessionComponent add(SessionModule sessionModule);
}
