package net.tlalka.android.fiszki.utils;

import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidUtilsTest {

    @Test
    public void testCheckIfObjectIsNull() {

        // then
        assertTrue(ValidUtils.isNull(null));
    }

    @Test
    public void testCheckIfObjectIsNotNull() {

        // then
        assertTrue(ValidUtils.isNotNull("OK"));
    }

    @Test
    public void testCheckIfArgumentIsTrue() {

        // then
        assertTrue(ValidUtils.isTrue(2 + 2 == 4));
    }

    @Test
    public void testCheckIfArgumentIsFalse() {

        // then
        assertTrue(ValidUtils.isFalse(2 + 2 == 5));
    }

    @Test
    public void testCheckIfStringIsEmpty() {

        // then
        assertTrue(ValidUtils.isEmpty(""));
    }

    @Test
    public void testCheckIfListIsEmpty() {

        // then
        assertTrue(ValidUtils.isEmpty(Collections.emptyList()));
    }

    @Test
    public void testCheckIfMapIsEmpty() {

        // then
        assertTrue(ValidUtils.isEmpty(Collections.emptyMap()));
    }

    @Test
    public void testCheckIfStringIsNotEmpty() {

        // then
        assertTrue(ValidUtils.isNotEmpty("NOT EMPTY"));
    }

    @Test
    public void testCheckIfListIsNotEmpty() {

        // then
        assertTrue(ValidUtils.isNotEmpty(Collections.singletonList("OK")));
    }

    @Test
    public void testCheckIfMapIsNotEmpty() {

        // then
        assertTrue(ValidUtils.isNotEmpty(Collections.singletonMap("KEY", "OK")));
    }

    @Test
    public void testCheckIfNullStringIsEmpty() {

        // then
        assertFalse(ValidUtils.isEmpty(getNullValue(String.class)));
    }

    @Test
    public void testCheckIfNullListIsEmpty() {

        // then
        assertFalse(ValidUtils.isEmpty(getNullValue(List.class)));
    }

    @Test
    public void testCheckIfNullMapIsEmpty() {

        // then
        assertFalse(ValidUtils.isEmpty(getNullValue(Map.class)));
    }

    private <E> E getNullValue(Class<E> clazz) {
        return null;
    }
}
