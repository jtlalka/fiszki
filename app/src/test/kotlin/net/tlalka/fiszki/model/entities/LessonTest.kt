package net.tlalka.fiszki.model.entities

import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LanguageType
import net.tlalka.fiszki.model.types.LevelType
import net.tlalka.fiszki.model.types.OwnerType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class LessonTest {

    @Test
    fun testCheekDefaultConstructor() {

        // when
        val lesson = Lesson()

        // then
        assertNotNull(lesson)
        assertEquals(0, lesson.id)
        assertEquals(0, lesson.progress.toLong())
        assertEquals(0, lesson.score.toLong())
    }

    @Test
    fun testCheekCustomConstructor() {

        // given
        val name = "Colors"
        val levelType = LevelType.ADVANCED
        val languageType = LanguageType.EN

        // when
        val lesson = Lesson(name, levelType, languageType)

        // then
        assertNotNull(lesson)
        assertEquals(name, lesson.name)
        assertEquals(levelType, lesson.levelType)
        assertEquals(languageType, lesson.languageType)
        assertEquals(OwnerType.SYSTEM, lesson.ownerType)
    }
}
