package net.tlalka.android.fiszki.test.components

import dagger.Subcomponent
import net.tlalka.android.fiszki.core.annotations.ActivityScope
import net.tlalka.android.fiszki.core.components.ActivityComponent
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.view.activities.LessonAndroidTest
import net.tlalka.android.fiszki.view.activities.TestAndroidTest

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface TestEspressoComponent : ActivityComponent {

    fun inject(lessonAndroidTest: LessonAndroidTest)

    fun inject(testAndroidTest: TestAndroidTest)
}
