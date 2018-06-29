package net.tlalka.fiszki.core.modules

import android.app.Activity
import butterknife.ButterKnife
import dagger.Module
import dagger.Provides
import net.tlalka.fiszki.core.annotations.ActivityScope
import net.tlalka.fiszki.model.dto.parcel.LessonDto
import net.tlalka.fiszki.model.dto.parcel.WelcomeDto

@Module
class ActivityModule(@get:Provides @get:ActivityScope val activity: Activity) {

    @Provides
    @ActivityScope
    fun getWelcomeDto(): WelcomeDto {
        return activity.intent.getParcelableExtra(WelcomeDto::class.java.name)
    }

    @Provides
    @ActivityScope
    fun getLessonDto(): LessonDto {
        return activity.intent.getParcelableExtra(LessonDto::class.java.name)
    }

    init {
        ButterKnife.bind(activity)
    }
}
