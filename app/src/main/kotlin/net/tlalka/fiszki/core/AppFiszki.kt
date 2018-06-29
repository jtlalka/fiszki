package net.tlalka.fiszki.core

import android.app.Activity
import android.app.Application
import net.tlalka.fiszki.core.components.ActivityComponent
import net.tlalka.fiszki.core.components.ApplicationComponent
import net.tlalka.fiszki.core.components.DaggerApplicationComponent
import net.tlalka.fiszki.core.components.SessionComponent
import net.tlalka.fiszki.core.modules.ActivityModule
import net.tlalka.fiszki.core.modules.ApplicationModule
import net.tlalka.fiszki.core.modules.SessionModule

class AppFiszki : Application() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private val sessionComponent: SessionComponent by lazy {
        applicationComponent.add(SessionModule(baseContext))
    }

    fun getActivityComponent(activity: Activity): ActivityComponent {
        return sessionComponent.add(ActivityModule(activity))
    }
}
