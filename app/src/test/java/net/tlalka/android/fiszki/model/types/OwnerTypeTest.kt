package net.tlalka.android.fiszki.model.types

import org.junit.Assert.assertEquals
import org.junit.Test

class OwnerTypeTest {

    @Test
    fun testCheckOwnerValues() {

        // then
        assertEquals("DB and DTO relations", OwnerType.USER, OwnerType.valueOf("USER"))
        assertEquals("DB and DTO relations", OwnerType.SYSTEM, OwnerType.valueOf("SYSTEM"))
    }

    @Test
    fun testCheckOwnerIndexes() {

        // then
        assertEquals("DB index relation", 0, OwnerType.USER.ordinal.toLong())
        assertEquals("DB index relation", 1, OwnerType.SYSTEM.ordinal.toLong())
    }
}
