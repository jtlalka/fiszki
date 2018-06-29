package net.tlalka.fiszki.core.components

import dagger.Component
import net.tlalka.fiszki.core.modules.ApplicationModule
import net.tlalka.fiszki.core.modules.SessionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun add(sessionModule: SessionModule): SessionComponent
}
