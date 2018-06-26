package net.tlalka.android.fiszki.view.activities

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : AbstractActivityTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun openLessonsActivity() {

        // given
        mainPage.valid()

        // when
        mainPage.openLessons()

        // then
        lessonListPage.valid()
    }

    @Test
    fun openTestsActivity() {

        // given
        mainPage.valid()

        // when
        mainPage.openTests()

        // then
        testListPage.valid()
    }
}
