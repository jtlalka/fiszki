package net.tlalka.android.fiszki.core.components

import dagger.Component
import net.tlalka.android.fiszki.core.modules.ApplicationModule
import net.tlalka.android.fiszki.core.modules.SessionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun add(sessionModule: SessionModule): SessionComponent
}
