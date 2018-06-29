package net.tlalka.fiszki.utils

import net.tlalka.fiszki.domain.utils.ValidUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidUtilsTest {

    @Test
    fun testCheckIfObjectIsNull() {
        assertTrue(ValidUtils.isNull(null))
    }

    @Test
    fun testCheckIfObjectIsNotNull() {
        assertTrue(ValidUtils.isNotNull("OK"))
    }

    @Test
    fun testCheckIfArgumentIsTrue() {
        assertTrue(ValidUtils.isTrue(true))
    }

    @Test
    fun testCheckIfArgumentIsFalse() {
        assertTrue(ValidUtils.isFalse(false))
    }

    @Test
    fun testCheckIfNullArgumentIsFalse() {
        assertFalse(ValidUtils.isTrue(null))
    }

    @Test
    fun testCheckIfStringIsEmpty() {
        assertTrue(ValidUtils.isEmpty(""))
    }

    @Test
    fun testCheckIfListIsEmpty() {
        assertTrue(ValidUtils.isEmpty(emptyList<Any>()))
    }

    @Test
    fun testCheckIfMapIsEmpty() {
        assertTrue(ValidUtils.isEmpty(emptyMap<Any, Any>()))
    }

    @Test
    fun testCheckIfStringIsNotEmpty() {
        assertTrue(ValidUtils.isNotEmpty("NOT EMPTY"))
    }

    @Test
    fun testCheckIfListIsNotEmpty() {
        assertTrue(ValidUtils.isNotEmpty(listOf("OK")))
    }

    @Test
    fun testCheckIfMapIsNotEmpty() {
        assertTrue(ValidUtils.isNotEmpty(mapOf(Pair("KEY", "OK"))))
    }

    @Test
    fun testCheckIfNullStringIsEmpty() {
        assertFalse(ValidUtils.isEmpty(getNullValue(String::class.java)))
    }

    @Test
    fun testCheckIfNullListIsEmpty() {
        assertFalse(ValidUtils.isEmpty(getNullValue(List::class.java)))
    }

    @Test
    fun testCheckIfNullMapIsEmpty() {
        assertFalse(ValidUtils.isEmpty(getNullValue(Map::class.java)))
    }

    private fun <E> getNullValue(clazz: Class<E>): E? {
        return null
    }
}
