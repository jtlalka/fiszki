package net.tlalka.android.fiszki.injections.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.activities.HelpActivity;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.activities.LessonListActivity;
import net.tlalka.android.fiszki.activities.LessonStatsActivity;
import net.tlalka.android.fiszki.activities.MainActivity;
import net.tlalka.android.fiszki.activities.WelcomeActivity;
import net.tlalka.android.fiszki.activities.WordsActivity;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.injections.modules.ActivityModule;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(LessonActivity lessonActivity);

    void inject(LessonListActivity lessonListActivity);

    void inject(LessonStatsActivity lessonStatsActivity);

    void inject(WordsActivity wordsActivity);

    void inject(HelpActivity helpActivity);
}
