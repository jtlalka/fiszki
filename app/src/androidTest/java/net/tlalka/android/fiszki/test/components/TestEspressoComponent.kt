package net.tlalka.android.fiszki.test.components

import dagger.Subcomponent
import net.tlalka.android.fiszki.core.annotations.ActivityScope
import net.tlalka.android.fiszki.core.components.ActivityComponent
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.view.activities.LessonActivityTest
import net.tlalka.android.fiszki.view.activities.TestActivityTest
import net.tlalka.android.fiszki.view.activities.WordsActivityTest

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface TestEspressoComponent : ActivityComponent {

    fun inject(lessonActivityTest: LessonActivityTest)

    fun inject(testActivityTest: TestActivityTest)

    fun inject(wordsActivityTest: WordsActivityTest)
}
