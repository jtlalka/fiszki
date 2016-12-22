package net.tlalka.android.fiszki.injections.modules;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.dto.WelcomeDto;
import org.parceler.Parcels;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
        ButterKnife.bind(activity);
    }

    @Provides
    @ActivityScope
    Activity activity() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    WelcomeDto welcomeDto() {
        Bundle argsBundle = activity.getIntent().getExtras();
        return Parcels.unwrap(argsBundle.getParcelable(WelcomeDto.class.getName()));
    }

    @Provides
    @ActivityScope
    LessonDto lessonDto() {
        Bundle argsBundle = activity.getIntent().getExtras();
        return Parcels.unwrap(argsBundle.getParcelable(LessonDto.class.getName()));
    }
}
