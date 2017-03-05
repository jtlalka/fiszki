package net.tlalka.android.fiszki.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AppFiszkiAndroidTest {

    @Test
    public void checkMainPackageName() throws Exception {

        // when
        Context appContext = InstrumentationRegistry.getTargetContext();

        // then
        assertEquals("net.tlalka.android.fiszki", appContext.getPackageName());
    }

    @Test
    public void checkApplicationInfo() throws Exception {

        // when
        ApplicationInfo applicationInfo = InstrumentationRegistry.getTargetContext().getApplicationInfo();

        // then
        assertEquals("net.tlalka.android.fiszki.core.AppFiszki", applicationInfo.className);
    }
}
