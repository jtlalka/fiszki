package net.tlalka.android.fiszki.domain.services

import android.util.Log
import net.tlalka.android.fiszki.core.annotations.SessionScope
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import java.util.WeakHashMap
import javax.inject.Inject

@SessionScope
class CacheService @Inject constructor() {

    @Inject
    lateinit var lessonService: LessonService

    @Inject
    lateinit var wordService: WordService

    private val lessons = WeakHashMap<Long, Lesson>()
    private val words = WeakHashMap<Long, List<Word>>()

    fun getLesson(lessonId: Long): Lesson {
        if (lessons[lessonId] == null) {
            lessons[lessonId] = lessonService.getLesson(lessonId)
            Log.i(CacheService::class.java.simpleName, "Cache for lessons: $lessonId")
        }
        return lessons[lessonId]!!
    }

    fun getWords(lesson: Lesson): List<Word> {
        if (words[lesson.id] == null) {
            words[lesson.id] = wordService.getWords(lesson)
            Log.i(CacheService::class.java.simpleName, "Cache for words")
        }
        return this.words[lesson.id]!!
    }
}
