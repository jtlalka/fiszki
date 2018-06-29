package net.tlalka.android.fiszki.domain.services

import android.util.Log
import net.tlalka.android.fiszki.core.annotations.SessionScope
import net.tlalka.android.fiszki.model.dao.LessonDao
import net.tlalka.android.fiszki.model.db.DbHelper
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.types.LanguageType
import java.sql.SQLException
import javax.inject.Inject

@SessionScope
class LessonService @Inject constructor(dbHelper: DbHelper) {

    private var lessonDao: LessonDao? = null

    init {
        try {
            this.lessonDao = dbHelper.getLessonDao()
        } catch (ex: SQLException) {
            throw RuntimeException("Cannot obtain lesson DAO", ex)
        }
    }

    fun getLesson(id: Long): Lesson {
        return try {
            this.lessonDao!!.getLessonBy(id)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain lesson by ID", ex)
            Lesson()
        }
    }

    fun getLessons(languageType: LanguageType): List<Lesson> {
        return try {
            this.lessonDao!!.getLessonsBy(languageType)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain lesson list", ex)
            emptyList()
        }
    }

    fun increaseProgress(lesson: Lesson) {
        lesson.progress = lesson.progress + 1
        lessonDao!!.update(lesson)
    }

    fun updateScore(lesson: Lesson, score: Int) {
        lesson.score = score
        lessonDao!!.update(lesson)
    }
}
