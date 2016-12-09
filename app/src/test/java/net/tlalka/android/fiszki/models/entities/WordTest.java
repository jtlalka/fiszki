package net.tlalka.android.fiszki.models.entities;

import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.OwnerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WordTest {

    @Test
    public void testCheekDefaultConstructor() {

        // when
        Word word = new Word();

        // then
        assertNotNull(word);
        assertEquals(0, word.getId());
        assertEquals(0, word.getProgress());
    }

    @Test
    public void testCheekCustomConstructor() {

        // given
        String value = "red";
        Cluster cluster = new Cluster();
        Lesson lesson = new Lesson();
        LanguageType languageType = LanguageType.EN;

        // when
        Word word = new Word(value, cluster, lesson, languageType);

        // then
        assertNotNull(word);
        assertEquals(value, word.getValue());
        assertEquals(cluster, word.getCluster());
        assertEquals(languageType, word.getLanguageType());
        assertEquals(OwnerType.SYSTEM, word.getOwnerType());
    }
}
