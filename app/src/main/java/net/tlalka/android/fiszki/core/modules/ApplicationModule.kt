package net.tlalka.android.fiszki.core.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import net.tlalka.android.fiszki.view.navigations.Navigator
import javax.inject.Singleton

@Module
class ApplicationModule(@get:Provides @get:Singleton val application: Application) {

    @Provides
    @Singleton
    fun getNavigator(): Navigator {
        return Navigator()
    }
}
