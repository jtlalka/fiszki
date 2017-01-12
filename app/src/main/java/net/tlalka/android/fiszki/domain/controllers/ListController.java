package net.tlalka.android.fiszki.domain.controllers;

import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.model.entities.Lesson;

import javax.inject.Inject;
import java.util.List;

@ActivityScope
public class ListController {

    @Inject
    protected LessonService lessonService;

    @Inject
    protected StorageService storageService;

    @Inject
    public ListController() {
    }

    public List<Lesson> getLessonList() {
        return lessonService.getLessons(storageService.getLanguage());
    }
}
