package net.tlalka.android.fiszki.injections.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.injections.annotations.SessionScope;
import net.tlalka.android.fiszki.injections.modules.ActivityModule;
import net.tlalka.android.fiszki.injections.modules.SessionModule;

@SessionScope
@Subcomponent(modules = SessionModule.class)
public interface SessionComponent {

    ActivityComponent add(ActivityModule activityModule);
}
