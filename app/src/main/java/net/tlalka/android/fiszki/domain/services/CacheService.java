package net.tlalka.android.fiszki.domain.services;

import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;

import javax.inject.Inject;
import java.util.List;

@SessionScope
public class CacheService {

    @Inject
    protected LessonService lessonService;

    @Inject
    protected WordService wordService;

    private Lesson lesson;
    private List<Word> words;

    @Inject
    public CacheService() {
    }

    public Lesson getLesson(long lessonId) {
        if (this.lesson == null || this.lesson.getId() != lessonId) {
            this.lesson = lessonService.getLesson(lessonId);
        }
        return this.lesson;
    }

    public List<Word> getWords(Lesson lesson) {
        if (this.words == null || this.words.isEmpty()) {
            this.words = wordService.getWords(lesson);
        }
        return this.words;
    }
}
