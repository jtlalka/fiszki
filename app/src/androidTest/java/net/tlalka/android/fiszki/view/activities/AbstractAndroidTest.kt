package net.tlalka.android.fiszki.view.activities

import android.app.Activity
import android.content.Context
import android.support.test.InstrumentationRegistry
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.core.modules.ApplicationModule
import net.tlalka.android.fiszki.core.modules.SessionModule
import net.tlalka.android.fiszki.model.helpers.StorageHelper
import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.model.types.StorageType
import net.tlalka.android.fiszki.test.components.DaggerTestApplicationComponent
import net.tlalka.android.fiszki.test.components.TestEspressoComponent
import net.tlalka.android.fiszki.view.pages.LessonListPage
import net.tlalka.android.fiszki.view.pages.LessonPage
import net.tlalka.android.fiszki.view.pages.LessonScorePage
import net.tlalka.android.fiszki.view.pages.MainPage
import net.tlalka.android.fiszki.view.pages.TestListPage
import net.tlalka.android.fiszki.view.pages.TestPage
import net.tlalka.android.fiszki.view.pages.TestScorePage
import net.tlalka.android.fiszki.view.pages.WelcomePage
import org.junit.BeforeClass

abstract class AbstractAndroidTest {

    protected val mainPage = MainPage()
    protected val welcomePage = WelcomePage()
    protected val lessonPage = LessonPage()
    protected val lessonListPage = LessonListPage()
    protected val lessonScorePage = LessonScorePage()
    protected val testPage = TestPage()
    protected val testListPage = TestListPage()
    protected val testScorePage = TestScorePage()

    protected fun getEspressoComponent(activity: Activity): TestEspressoComponent {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(activity.application))
                .build()
                .add(SessionModule(activity.baseContext))
                .add(ActivityModule(activity))
    }

    companion object {
        val testLanguage = LanguageType.EN
        val testTranslation = LanguageType.PL

        @JvmStatic
        @BeforeClass
        fun setupApplication() {
            val storageHelper = StorageHelper(getContext())
            storageHelper.setBoolean(StorageType.WELCOME_VIEW, false)
            storageHelper.setEnum(StorageType.LANGUAGE, testLanguage)
            storageHelper.setEnum(StorageType.TRANSLATION, testTranslation)
        }

        fun getContext(): Context {
            return InstrumentationRegistry.getInstrumentation().targetContext
        }
    }
}
