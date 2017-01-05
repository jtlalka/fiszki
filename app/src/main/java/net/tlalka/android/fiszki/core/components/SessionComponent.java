package net.tlalka.android.fiszki.core.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;

@SessionScope
@Subcomponent(modules = SessionModule.class)
public interface SessionComponent {

    ActivityComponent add(ActivityModule activityModule);
}
