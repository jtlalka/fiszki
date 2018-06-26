package net.tlalka.android.fiszki.view.activities

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.annimon.stream.IntStream
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.domain.services.WordService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class LessonAndroidTest : AbstractAndroidTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Inject
    lateinit var listController: ListController

    @Inject
    lateinit var wordService: WordService

    @Before
    fun setup() {
        getEspressoComponent(activityRule.activity).inject(this)
    }

    @Test
    fun simulateFullLesson() {

        // given
        mainPage.openLessons()
        lessonListPage.openItem(0)

        // when
        IntStream.range(0, getWordSize()).forEach { i ->
            lessonPage.clickTranslation()
            lessonPage.clickCorrect()
        }

        // then (score)
        lessonScorePage.valid()
        lessonScorePage.clickLessons()

        // then (lesson)
        lessonListPage.valid()
    }

    @Test
    fun simulateInvalidAnswer() {

        // given
        mainPage.openLessons()
        lessonListPage.openItem(0)

        // when
        lessonPage.clickIncorrect()
        lessonPage.clickIncorrect()

        // then (lesson)
        lessonPage.valid()
        lessonPage.matchProgress(getWordSize())
        lessonPage.closeActivity()

        // then (main)
        mainPage.valid()
    }

    private fun getWordSize(): Int {
        return wordService.getWords(listController.lessonList[0]).size
    }
}
