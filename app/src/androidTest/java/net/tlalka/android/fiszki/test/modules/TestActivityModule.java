package net.tlalka.android.fiszki.test.modules;

import android.app.Activity;
import net.tlalka.android.fiszki.core.modules.ActivityModule;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.test.DependencyProvider;

public class TestActivityModule extends ActivityModule {

    public TestActivityModule(Activity activity) {
        super(activity);
    }

    @Override
    public Activity getActivity() {
        return super.getActivity();
    }

    @Override
    public WelcomeDto getWelcomeDto() {
        return DependencyProvider.getWelcomeDto();
    }

    @Override
    public LessonDto getLessonDto() {
        return DependencyProvider.getLessonDto();
    }
}