package net.tlalka.android.fiszki.domain.controllers;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.domain.services.WordService;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;

@ActivityScope
public class ListController {

    @Inject
    protected StorageService storageService;

    @Inject
    protected LessonService lessonService;

    @Inject
    protected WordService wordService;

    @Inject
    public ListController() {
    }

    public List<Lesson> getLessonList() {
        return lessonService.getLessons(storageService.getLanguage());
    }

    public Map<Long, List<Word>> getWordsMap() {
        return Stream.of(wordService.getWords(storageService.getLanguage()))
                .collect(Collectors.groupingBy(w -> w.getLesson().getId()));
    }

    public Map<Long, Word> getTranslationMap() {
        return Stream.of(wordService.getWords(storageService.getTranslation()))
                .collect(Collectors.toMap(w -> w.getCluster().getId()));
    }
}
