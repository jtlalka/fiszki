package net.tlalka.android.fiszki.injections.components;

import dagger.Component;
import net.tlalka.android.fiszki.injections.modules.ApplicationModule;
import net.tlalka.android.fiszki.injections.modules.SessionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    SessionComponent add(SessionModule sessionModule);
}
