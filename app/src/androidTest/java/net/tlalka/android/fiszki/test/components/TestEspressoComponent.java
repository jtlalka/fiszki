package net.tlalka.android.fiszki.test.components;

import dagger.Subcomponent;
import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.core.components.ActivityComponent;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.view.activities.LessonActivityAndroidTest;
import net.tlalka.android.fiszki.view.activities.LessonListActivityAndroidTest;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface TestEspressoComponent extends ActivityComponent {

    void inject(LessonActivityAndroidTest lessonActivityAndroidTest);

    void inject(LessonListActivityAndroidTest lessonListActivityAndroidTest);
}
