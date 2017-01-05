package net.tlalka.android.fiszki.model.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StorageTypeTest {

    @Test
    public void testCheckOwnerValues() {

        // then
        assertEquals("DB and DTO relations", StorageType.LANGUAGE, StorageType.valueOf("LANGUAGE"));
        assertEquals("DB and DTO relations", StorageType.TRANSLATION, StorageType.valueOf("TRANSLATION"));
        assertEquals("DB and DTO relations", StorageType.WELCOME_VIEW, StorageType.valueOf("WELCOME_VIEW"));
    }

    @Test
    public void testCheckOwnerIndexes() {

        // then
        assertEquals("DB index relation", 0, StorageType.LANGUAGE.ordinal());
        assertEquals("DB index relation", 1, StorageType.TRANSLATION.ordinal());
        assertEquals("DB index relation", 2, StorageType.WELCOME_VIEW.ordinal());
    }
}
