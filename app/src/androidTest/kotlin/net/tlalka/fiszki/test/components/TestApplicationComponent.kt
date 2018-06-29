package net.tlalka.fiszki.test.components

import dagger.Component
import net.tlalka.fiszki.core.modules.ApplicationModule
import net.tlalka.fiszki.core.modules.SessionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface TestApplicationComponent {

    fun add(sessionModule: SessionModule): TestSessionComponent
}
