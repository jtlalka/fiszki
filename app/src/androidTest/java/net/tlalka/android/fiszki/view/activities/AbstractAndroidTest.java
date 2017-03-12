package net.tlalka.android.fiszki.view.activities;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.core.modules.ApplicationModule;
import net.tlalka.android.fiszki.core.modules.SessionModule;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.test.components.DaggerTestApplicationComponent;
import net.tlalka.android.fiszki.test.components.TestEspressoComponent;
import net.tlalka.android.fiszki.view.pages.LessonListPage;
import net.tlalka.android.fiszki.view.pages.LessonPage;
import net.tlalka.android.fiszki.view.pages.LessonScorePage;
import net.tlalka.android.fiszki.view.pages.MainPage;
import net.tlalka.android.fiszki.view.pages.WelcomePage;
import org.junit.BeforeClass;

public abstract class AbstractAndroidTest {

    protected static final MainPage mainPage = new MainPage();
    protected static final WelcomePage welcomePage = new WelcomePage();
    protected static final LessonPage lessonPage = new LessonPage();
    protected static final LessonListPage lessonListPage = new LessonListPage();
    protected static final LessonScorePage lessonScorePage = new LessonScorePage();

    @BeforeClass
    public static void setupApplication() {
        StorageHelper storageHelper = new StorageHelper(getContext());
        storageHelper.setBoolean(StorageType.WELCOME_VIEW, false);
        storageHelper.setEnum(StorageType.LANGUAGE, LanguageType.EN);
        storageHelper.setEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    protected static Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    protected static TestEspressoComponent getEspressoComponent(Activity activity) {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(activity.getApplication()))
                .build()
                .add(new SessionModule(activity.getBaseContext()))
                .add(new ActivityModule(activity));
    }
}
