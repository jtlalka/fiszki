package net.tlalka.fiszki.test.components

import dagger.Subcomponent
import net.tlalka.fiszki.core.annotations.ActivityScope
import net.tlalka.fiszki.core.components.ActivityComponent
import net.tlalka.fiszki.core.modules.ActivityModule
import net.tlalka.fiszki.view.activities.LessonActivityTest
import net.tlalka.fiszki.view.activities.TestActivityTest
import net.tlalka.fiszki.view.activities.WordsActivityTest

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface TestEspressoComponent : ActivityComponent {

    fun inject(lessonActivityTest: LessonActivityTest)

    fun inject(testActivityTest: TestActivityTest)

    fun inject(wordsActivityTest: WordsActivityTest)
}
