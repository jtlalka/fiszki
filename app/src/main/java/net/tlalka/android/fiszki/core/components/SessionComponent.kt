package net.tlalka.android.fiszki.core.components

import dagger.Subcomponent
import net.tlalka.android.fiszki.core.annotations.SessionScope
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.core.modules.SessionModule

@SessionScope
@Subcomponent(modules = [SessionModule::class])
interface SessionComponent {

    fun add(activityModule: ActivityModule): ActivityComponent
}