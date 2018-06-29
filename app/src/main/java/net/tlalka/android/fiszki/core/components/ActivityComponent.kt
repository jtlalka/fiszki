package net.tlalka.android.fiszki.core.components

import dagger.Subcomponent
import net.tlalka.android.fiszki.core.annotations.ActivityScope
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.view.activities.HelpActivity
import net.tlalka.android.fiszki.view.activities.LessonActivity
import net.tlalka.android.fiszki.view.activities.LessonListActivity
import net.tlalka.android.fiszki.view.activities.LessonScoreActivity
import net.tlalka.android.fiszki.view.activities.MainActivity
import net.tlalka.android.fiszki.view.activities.SettingsActivity
import net.tlalka.android.fiszki.view.activities.TestActivity
import net.tlalka.android.fiszki.view.activities.TestListActivity
import net.tlalka.android.fiszki.view.activities.TestScoreActivity
import net.tlalka.android.fiszki.view.activities.WelcomeActivity
import net.tlalka.android.fiszki.view.activities.WordsActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(welcomeActivity: WelcomeActivity)

    fun inject(lessonActivity: LessonActivity)

    fun inject(lessonListActivity: LessonListActivity)

    fun inject(lessonScoreActivity: LessonScoreActivity)

    fun inject(testActivity: TestActivity)

    fun inject(testListActivity: TestListActivity)

    fun inject(testScoreActivity: TestScoreActivity)

    fun inject(wordsActivity: WordsActivity)

    fun inject(helpActivity: HelpActivity)

    fun inject(settingsActivity: SettingsActivity)
}
