package net.tlalka.android.fiszki.core

import android.app.Activity
import android.app.Application

import net.tlalka.android.fiszki.core.components.ActivityComponent
import net.tlalka.android.fiszki.core.components.ApplicationComponent
import net.tlalka.android.fiszki.core.components.DaggerApplicationComponent
import net.tlalka.android.fiszki.core.components.SessionComponent
import net.tlalka.android.fiszki.core.modules.ActivityModule
import net.tlalka.android.fiszki.core.modules.ApplicationModule
import net.tlalka.android.fiszki.core.modules.SessionModule

class AppFiszki : Application() {

    private var applicationComponent: ApplicationComponent? = null

    private var sessionComponent: SessionComponent? = null

    override fun onCreate() {
        super.onCreate()

        this.applicationComponent = initApplicationComponent()
        this.sessionComponent = initSessionComponent()
    }

    private fun initApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    private fun initSessionComponent(): SessionComponent {
        return this.applicationComponent!!.add(SessionModule(baseContext))
    }

    fun getActivityComponent(activity: Activity): ActivityComponent {
        return this.sessionComponent!!.add(ActivityModule(activity))
    }
}