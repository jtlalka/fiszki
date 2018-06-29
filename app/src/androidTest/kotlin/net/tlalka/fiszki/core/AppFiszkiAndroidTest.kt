package net.tlalka.fiszki.core

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppFiszkiAndroidTest {

    @Test
    fun checkMainPackageName() {

        // when
        val appContext = InstrumentationRegistry.getTargetContext()

        // then
        assertEquals("net.tlalka.fiszki", appContext.packageName)
    }

    @Test
    fun checkApplicationInfo() {

        // when
        val applicationInfo = InstrumentationRegistry.getTargetContext().applicationInfo

        // then
        assertEquals("net.tlalka.fiszki", applicationInfo.packageName)
        assertEquals("net.tlalka.fiszki.core.AppFiszki", applicationInfo.className)
    }
}
