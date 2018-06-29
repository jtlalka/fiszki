package net.tlalka.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LanguageType

class LessonDao constructor(connectionSource: ConnectionSource)
    : AbstractDao<Lesson, Long>(connectionSource, Lesson::class.java) {

    fun getLessonBy(id: Long): Lesson {
        return super.queryForId(id)
    }

    fun getLessonsBy(languageType: LanguageType): List<Lesson> {
        return super.queryBuilder()
                .orderBy("ownerType", true)
                .orderBy("levelType", true)
                .where()
                .eq("languageType", languageType)
                .query()
    }
}
