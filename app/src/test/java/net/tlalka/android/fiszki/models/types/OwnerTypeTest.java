package net.tlalka.android.fiszki.models.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OwnerTypeTest {

    @Test
    public void testCheckLanguagesList() {

        // then
        assertEquals(OwnerType.SYSTEM, OwnerType.valueOf("SYSTEM"));
        assertEquals(OwnerType.USER, OwnerType.valueOf("USER"));
    }
}
