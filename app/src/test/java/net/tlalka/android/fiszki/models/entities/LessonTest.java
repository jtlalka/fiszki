package net.tlalka.android.fiszki.models.entities;

import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.LevelType;
import net.tlalka.android.fiszki.models.types.OwnerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LessonTest {

    @Test
    public void testCheekDefaultConstructor() {

        // when
        Lesson lesson = new Lesson();

        // then
        assertNotNull(lesson);
        assertEquals(0, lesson.getId());
        assertEquals(0, lesson.getProgress());
        assertEquals(0, lesson.getScore());
    }

    @Test
    public void testCheekCustomConstructor() {

        // given
        String name = "Colors";
        LevelType levelType = LevelType.ADVANCED;
        LanguageType languageType = LanguageType.EN;

        // when
        Lesson lesson = new Lesson(name, levelType, languageType);

        // then
        assertNotNull(lesson);
        assertEquals(name, lesson.getName());
        assertEquals(levelType, lesson.getLevelType());
        assertEquals(languageType, lesson.getLanguageType());
        assertEquals(OwnerType.SYSTEM, lesson.getOwnerType());
    }
}
