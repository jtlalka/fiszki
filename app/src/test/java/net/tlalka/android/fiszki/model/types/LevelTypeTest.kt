package net.tlalka.android.fiszki.model.types

import org.junit.Assert.assertEquals
import org.junit.Test

class LevelTypeTest {

    @Test
    fun testCheckLevelValues() {

        // then
        assertEquals("DB and DTO relations", LevelType.BEGINNER, LevelType.valueOf("BEGINNER"))
        assertEquals("DB and DTO relations", LevelType.ELEMENTARY, LevelType.valueOf("ELEMENTARY"))
        assertEquals("DB and DTO relations", LevelType.INTERMEDIATE, LevelType.valueOf("INTERMEDIATE"))
        assertEquals("DB and DTO relations", LevelType.ADVANCED, LevelType.valueOf("ADVANCED"))
        assertEquals("DB and DTO relations", LevelType.PROFICIENT, LevelType.valueOf("PROFICIENT"))
    }

    @Test
    fun testCheckLevelIndexes() {

        // then
        assertEquals("DB index relation", 0, LevelType.BEGINNER.ordinal.toLong())
        assertEquals("DB index relation", 1, LevelType.ELEMENTARY.ordinal.toLong())
        assertEquals("DB index relation", 2, LevelType.INTERMEDIATE.ordinal.toLong())
        assertEquals("DB index relation", 3, LevelType.ADVANCED.ordinal.toLong())
        assertEquals("DB index relation", 4, LevelType.PROFICIENT.ordinal.toLong())
    }
}
