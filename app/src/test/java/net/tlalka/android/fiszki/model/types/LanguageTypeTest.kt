package net.tlalka.android.fiszki.model.types

import org.junit.Assert.assertEquals
import org.junit.Test

class LanguageTypeTest {

    @Test
    fun testCheckLanguageValues() {

        // then
        assertEquals("DB and DTO relations", LanguageType.EN, LanguageType.valueOf("EN"))
        assertEquals("DB and DTO relations", LanguageType.FR, LanguageType.valueOf("FR"))
        assertEquals("DB and DTO relations", LanguageType.DE, LanguageType.valueOf("DE"))
        assertEquals("DB and DTO relations", LanguageType.PL, LanguageType.valueOf("PL"))
    }

    @Test
    fun testCheckLanguageIndexes() {

        // then
        assertEquals("DB index relation", 0, LanguageType.EN.ordinal.toLong())
        assertEquals("DB index relation", 1, LanguageType.FR.ordinal.toLong())
        assertEquals("DB index relation", 2, LanguageType.DE.ordinal.toLong())
        assertEquals("DB index relation", 3, LanguageType.PL.ordinal.toLong())
    }
}
