package net.tlalka.android.fiszki.injections.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.activities.LessonStatsActivity;
import net.tlalka.android.fiszki.activities.LessonsActivity;
import net.tlalka.android.fiszki.activities.MainActivity;
import net.tlalka.android.fiszki.activities.WelcomeActivity;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.injections.modules.ActivityModule;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(LessonsActivity lessonsActivity);

    void inject(LessonActivity lessonActivity);

    void inject(LessonStatsActivity lessonStatsActivity);
}
