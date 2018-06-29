package net.tlalka.fiszki.core.components

import dagger.Component
import net.tlalka.fiszki.core.annotations.ApplicationScope
import net.tlalka.fiszki.core.modules.ApplicationModule
import net.tlalka.fiszki.core.modules.SessionModule

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun add(sessionModule: SessionModule): SessionComponent
}
