package net.tlalka.android.fiszki.models.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageTypeTest {

    @Test
    public void testCheckLanguageValues() {

        // then
        assertEquals("DB and DTO relations", LanguageType.EN, LanguageType.valueOf("EN"));
        assertEquals("DB and DTO relations", LanguageType.FR, LanguageType.valueOf("FR"));
        assertEquals("DB and DTO relations", LanguageType.DE, LanguageType.valueOf("DE"));
        assertEquals("DB and DTO relations", LanguageType.PL, LanguageType.valueOf("PL"));
    }

    @Test
    public void testCheckLanguageIndexes() {

        // then
        assertEquals("DB index relation", 0, LanguageType.EN.ordinal());
        assertEquals("DB index relation", 1, LanguageType.FR.ordinal());
        assertEquals("DB index relation", 2, LanguageType.DE.ordinal());
        assertEquals("DB index relation", 3, LanguageType.PL.ordinal());
    }
}
