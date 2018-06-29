package net.tlalka.fiszki.core.modules

import android.app.Activity
import android.os.Parcelable

import butterknife.ButterKnife
import dagger.Module
import dagger.Provides
import net.tlalka.fiszki.core.annotations.ActivityScope
import net.tlalka.fiszki.model.dto.LessonDto
import net.tlalka.fiszki.model.dto.WelcomeDto
import org.parceler.Parcels

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun getThis(): Activity {
        return activity
    }

    @Provides
    @ActivityScope
    fun getWelcomeDto(): WelcomeDto {
        val argsBundle = activity.intent.extras
        return Parcels.unwrap<WelcomeDto>(argsBundle!!.getParcelable<Parcelable>(WelcomeDto::class.java.name))
    }

    @Provides
    @ActivityScope
    fun getLessonDto(): LessonDto {
        val argsBundle = activity.intent.extras
        return Parcels.unwrap<LessonDto>(argsBundle!!.getParcelable<Parcelable>(LessonDto::class.java.name))
    }

    init {
        ButterKnife.bind(activity)
    }
}
