package net.tlalka.fiszki.test.components

import dagger.Component
import net.tlalka.fiszki.core.annotations.ApplicationScope
import net.tlalka.fiszki.core.modules.ApplicationModule
import net.tlalka.fiszki.core.modules.SessionModule

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface TestApplicationComponent {

    fun add(sessionModule: SessionModule): TestSessionComponent
}
