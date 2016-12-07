package net.tlalka.android.fiszki.models.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelTypeTest {

    @Test
    public void testCheckLevelNames() {

        // then
        assertEquals("0", LevelType.BEGINNER.getName());
        assertEquals("A1", LevelType.ELEMENTARY.getName());
        assertEquals("B1", LevelType.INTERMEDIATE.getName());
        assertEquals("C1", LevelType.ADVANCED.getName());
        assertEquals("C2", LevelType.PROFICIENT.getName());
    }

    @Test
    public void testCheckLevelValues() {

        // then
        assertEquals(0, LevelType.BEGINNER.getValue());
        assertEquals(100, LevelType.ELEMENTARY.getValue());
        assertEquals(300, LevelType.INTERMEDIATE.getValue());
        assertEquals(500, LevelType.ADVANCED.getValue());
        assertEquals(600, LevelType.PROFICIENT.getValue());
    }
}
