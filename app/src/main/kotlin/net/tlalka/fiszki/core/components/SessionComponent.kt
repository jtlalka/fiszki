package net.tlalka.fiszki.core.components

import dagger.Subcomponent
import net.tlalka.fiszki.core.annotations.SessionScope
import net.tlalka.fiszki.core.modules.ActivityModule
import net.tlalka.fiszki.core.modules.SessionModule

@SessionScope
@Subcomponent(modules = [SessionModule::class])
interface SessionComponent {

    fun add(activityModule: ActivityModule): ActivityComponent
}
