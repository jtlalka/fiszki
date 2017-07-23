package net.tlalka.android.fiszki.test.components;

import javax.inject.Singleton;

import dagger.Component;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface TestApplicationComponent {

    TestSessionComponent add(SessionModule sessionModule);
}
