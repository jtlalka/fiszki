package net.tlalka.android.fiszki.model.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelTypeTest {

    @Test
    public void testCheckLevelValues() {

        // then
        assertEquals("DB and DTO relations", LevelType.BEGINNER, LevelType.valueOf("BEGINNER"));
        assertEquals("DB and DTO relations", LevelType.ELEMENTARY, LevelType.valueOf("ELEMENTARY"));
        assertEquals("DB and DTO relations", LevelType.INTERMEDIATE, LevelType.valueOf("INTERMEDIATE"));
        assertEquals("DB and DTO relations", LevelType.ADVANCED, LevelType.valueOf("ADVANCED"));
        assertEquals("DB and DTO relations", LevelType.PROFICIENT, LevelType.valueOf("PROFICIENT"));
    }

    @Test
    public void testCheckLevelIndexes() {

        // then
        assertEquals("DB index relation", 0, LevelType.BEGINNER.ordinal());
        assertEquals("DB index relation", 1, LevelType.ELEMENTARY.ordinal());
        assertEquals("DB index relation", 2, LevelType.INTERMEDIATE.ordinal());
        assertEquals("DB index relation", 3, LevelType.ADVANCED.ordinal());
        assertEquals("DB index relation", 4, LevelType.PROFICIENT.ordinal());
    }
}
