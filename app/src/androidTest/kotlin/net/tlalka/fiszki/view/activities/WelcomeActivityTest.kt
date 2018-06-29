package net.tlalka.fiszki.view.activities

import android.support.test.runner.AndroidJUnit4

import net.tlalka.fiszki.model.helpers.StorageHelper
import net.tlalka.fiszki.model.types.StorageType
import net.tlalka.fiszki.test.ActivityLazyRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest : AbstractActivityTest() {

    @get:Rule
    val activityRule = ActivityLazyRule(MainActivity::class.java)

    @Before
    fun setup() {
        StorageHelper(AbstractActivityTest.getContext()).setBoolean(StorageType.WELCOME_VIEW, true)

        activityRule.launchActivity()
    }

    @Test
    fun clickOnWelcomeMessage() {

        // given
        welcomePage.valid()

        // when
        welcomePage.clickMessage()

        // then
        mainPage.valid()
    }

    @Test
    fun clickOnActivityLayout() {

        // given
        welcomePage.valid()

        // when
        welcomePage.clickLayout()

        // then
        mainPage.valid()
    }
}
