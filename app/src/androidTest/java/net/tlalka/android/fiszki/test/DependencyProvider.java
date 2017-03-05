package net.tlalka.android.fiszki.test;

import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.view.navigations.Navigator;

public class DependencyProvider {

    private static Navigator navigator;
    private static WelcomeDto welcomeDto;
    private static LessonDto lessonDto;

    private DependencyProvider() {
    }

    public static Navigator getNavigator() {
        return navigator;
    }

    public static void setNavigator(Navigator navigator) {
        DependencyProvider.navigator = navigator;
    }

    public static WelcomeDto getWelcomeDto() {
        return welcomeDto;
    }

    public static void setWelcomeDto(WelcomeDto welcomeDto) {
        DependencyProvider.welcomeDto = welcomeDto;
    }

    public static LessonDto getLessonDto() {
        return lessonDto;
    }

    public static void setLessonDto(LessonDto lessonDto) {
        DependencyProvider.lessonDto = lessonDto;
    }
}
