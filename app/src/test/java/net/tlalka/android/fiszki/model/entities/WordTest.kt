package net.tlalka.android.fiszki.model.entities

import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.model.types.OwnerType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class WordTest {

    @Test
    fun testCheekDefaultConstructor() {

        // when
        val word = Word()

        // then
        assertNotNull(word)
        assertEquals(0, word.id)
        assertEquals(0, word.progress)
    }

    @Test
    fun testCheekCustomConstructor() {

        // given
        val value = "red"
        val cluster = Cluster()
        val lesson = Lesson()
        val languageType = LanguageType.EN

        // when
        val word = Word(value, cluster, lesson, languageType)

        // then
        assertNotNull(word)
        assertEquals(value, word.value)
        assertEquals(cluster, word.cluster)
        assertEquals(languageType, word.languageType)
        assertEquals(OwnerType.SYSTEM, word.ownerType)
    }
}
