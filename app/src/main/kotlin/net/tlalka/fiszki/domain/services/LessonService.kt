package net.tlalka.fiszki.domain.services

import android.util.Log
import net.tlalka.fiszki.core.annotations.SessionScope
import net.tlalka.fiszki.model.dao.LessonDao
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LanguageType
import java.sql.SQLException
import javax.inject.Inject

@SessionScope
class LessonService @Inject constructor(private val lessonDao: LessonDao) {

    fun getLesson(id: Long): Lesson {
        return optional({ lessonDao.getLessonBy(id) }, Lesson())
    }

    fun getLessons(languageType: LanguageType): List<Lesson> {
        return optional({ lessonDao.getLessonsBy(languageType) }, emptyList())
    }

    fun increaseProgress(lesson: Lesson) {
        lesson.progress = lesson.progress + 1
        lessonDao.update(lesson)
    }

    fun updateScore(lesson: Lesson, score: Int) {
        lesson.score = score
        lessonDao.update(lesson)
    }

    private fun <E> optional(func: () -> E, default: E): E {
        return try {
            func()
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain data from repository.", ex)
            default
        }
    }
}
