package net.tlalka.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LanguageType
import java.sql.SQLException

class LessonDao @Throws(SQLException::class)
constructor(connectionSource: ConnectionSource) : AbstractDao<Lesson, Long>(connectionSource, Lesson::class.java) {

    @Throws(SQLException::class)
    fun getLessonBy(id: Long): Lesson {
        return super.queryForId(id)
    }

    @Throws(SQLException::class)
    fun getLessonsBy(languageType: LanguageType): List<Lesson> {
        return super.queryBuilder()
                .orderBy("ownerType", true)
                .orderBy("levelType", true)
                .where()
                .eq("languageType", languageType)
                .query()
    }
}
