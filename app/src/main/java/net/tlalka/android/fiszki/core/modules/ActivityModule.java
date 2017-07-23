package net.tlalka.android.fiszki.core.modules;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
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
    public Activity getActivity() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    public WelcomeDto getWelcomeDto() {
        Bundle argsBundle = activity.getIntent().getExtras();
        return Parcels.unwrap(argsBundle.getParcelable(WelcomeDto.class.getName()));
    }

    @Provides
    @ActivityScope
    public LessonDto getLessonDto() {
        Bundle argsBundle = activity.getIntent().getExtras();
        return Parcels.unwrap(argsBundle.getParcelable(LessonDto.class.getName()));
    }
}
