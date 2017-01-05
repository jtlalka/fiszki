package net.tlalka.android.fiszki.core.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.view.activities.HelpActivity;
import net.tlalka.android.fiszki.view.activities.LessonActivity;
import net.tlalka.android.fiszki.view.activities.LessonListActivity;
import net.tlalka.android.fiszki.view.activities.LessonStatsActivity;
import net.tlalka.android.fiszki.view.activities.MainActivity;
import net.tlalka.android.fiszki.view.activities.WelcomeActivity;
import net.tlalka.android.fiszki.view.activities.WordsActivity;

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
