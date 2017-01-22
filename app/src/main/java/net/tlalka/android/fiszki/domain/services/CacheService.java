package net.tlalka.android.fiszki.domain.services;

import android.util.Log;
import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScope
public class CacheService {

    @Inject
    protected LessonService lessonService;

    @Inject
    protected WordService wordService;

    private Map<Long, WeakReference<Lesson>> lessons = new HashMap<>();
    private Map<Long, WeakReference<List<Word>>> words = new HashMap<>();

    @Inject
    public CacheService() {
    }

    public Lesson getLesson(long lessonId) {
        if (isNull(lessons.get(lessonId))) {
            this.lessons.put(lessonId, new WeakReference<>(lessonService.getLesson(lessonId)));
            Log.i(CacheService.class.getSimpleName(), "Cache for lessons: " + lessonId);
        }
        return this.lessons.get(lessonId).get();
    }

    public List<Word> getWords(Lesson lesson) {
        if (isNull(words.get(lesson.getId()))) {
            this.words.put(lesson.getId(), new WeakReference<>(wordService.getWords(lesson)));
            Log.i(CacheService.class.getSimpleName(), "Cache for words");
        }
        return this.words.get(lesson.getId()).get();
    }

    private boolean isNull(WeakReference reference) {
        return reference == null || reference.get() == null;
    }
}
