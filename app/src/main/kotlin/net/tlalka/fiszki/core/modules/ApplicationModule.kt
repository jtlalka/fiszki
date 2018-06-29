package net.tlalka.fiszki.core.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import net.tlalka.fiszki.core.annotations.ApplicationScope
import net.tlalka.fiszki.view.navigations.Navigator

@Module
class ApplicationModule(@get:Provides @get:ApplicationScope val application: Application) {

    @Provides
    @ApplicationScope
    fun getNavigator(): Navigator {
        return Navigator()
    }
}
