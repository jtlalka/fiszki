package net.tlalka.fiszki.model.types

import net.tlalka.fiszki.model.types.StorageType
import org.junit.Assert.assertEquals
import org.junit.Test

class StorageTypeTest {

    @Test
    fun testCheckOwnerValues() {

        // then
        assertEquals("DB and DTO relations", StorageType.LANGUAGE, StorageType.valueOf("LANGUAGE"))
        assertEquals("DB and DTO relations", StorageType.TRANSLATION, StorageType.valueOf("TRANSLATION"))
        assertEquals("DB and DTO relations", StorageType.WELCOME_VIEW, StorageType.valueOf("WELCOME_VIEW"))
    }

    @Test
    fun testCheckOwnerIndexes() {

        // then
        assertEquals("DB index relation", 0, StorageType.LANGUAGE.ordinal.toLong())
        assertEquals("DB index relation", 1, StorageType.TRANSLATION.ordinal.toLong())
        assertEquals("DB index relation", 2, StorageType.WELCOME_VIEW.ordinal.toLong())
    }
}
