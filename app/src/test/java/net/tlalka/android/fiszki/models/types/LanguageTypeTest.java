package net.tlalka.android.fiszki.models.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageTypeTest {

    @Test
    public void testCheckLanguagesList() {

        // then
        assertEquals(LanguageType.EN, LanguageType.valueOf("EN"));
        assertEquals(LanguageType.FR, LanguageType.valueOf("FR"));
        assertEquals(LanguageType.DE, LanguageType.valueOf("DE"));
        assertEquals(LanguageType.PL, LanguageType.valueOf("PL"));
    }
}
