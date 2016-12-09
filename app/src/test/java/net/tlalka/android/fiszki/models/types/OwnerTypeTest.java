package net.tlalka.android.fiszki.models.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OwnerTypeTest {

    @Test
    public void testCheckOwnerValues() {

        // then
        assertEquals("DB and DTO relations", OwnerType.USER, OwnerType.valueOf("USER"));
        assertEquals("DB and DTO relations", OwnerType.SYSTEM, OwnerType.valueOf("SYSTEM"));
    }

    @Test
    public void testCheckOwnerIndexes() {

        // then
        assertEquals("DB index relation", 0, OwnerType.USER.ordinal());
        assertEquals("DB index relation", 1, OwnerType.SYSTEM.ordinal());
    }
}
