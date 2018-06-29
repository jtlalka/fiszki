package net.tlalka.android.fiszki.view.activities

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.annimon.stream.IntStream
import com.annimon.stream.Stream
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.domain.services.WordService
import net.tlalka.android.fiszki.model.entities.Word
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class TestActivityTest : AbstractActivityTest() {

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
    fun simulateFullTest() {

        // given
        val words = getWords(0)

        mainPage.openTests()
        testListPage.openItem(0)

        // when
        IntStream.range(0, words.size).forEach { i ->
            testPage.matchProgress(i + 1)

            val wordValue = testPage.getWord()
            val transValue = getTranslation(words, wordValue)

            when (transValue) {
                testPage.getAnswer1() -> testPage.clickAnswer1()
                testPage.getAnswer2() -> testPage.clickAnswer2()
                testPage.getAnswer3() -> testPage.clickAnswer3()
                testPage.getAnswer4() -> testPage.clickAnswer4()
            }
        }

        // then (score)
        testScorePage.valid()
        testScorePage.clickTests()

        // then (tests)
        testListPage.valid()
    }

    @Test
    fun simulateInvalidAnswer() {

        // given
        mainPage.openTests()
        testListPage.openItem(0)
        testPage.matchProgress(1)

        // when
        val wordValue = testPage.getWord()
        val transValue = getTranslation(getWords(0), wordValue)

        when {
            testPage.getAnswer1() != transValue -> testPage.clickAnswer1()
            testPage.getAnswer2() != transValue -> testPage.clickAnswer2()
            testPage.getAnswer3() != transValue -> testPage.clickAnswer3()
            testPage.getAnswer4() != transValue -> testPage.clickAnswer4()
        }

        // then (test)
        testPage.valid()
        testPage.matchProgress(1)
        testPage.closeActivity()

        // then (main)
        mainPage.valid()
    }

    private fun getWords(index: Int): List<Word> {
        return wordService.getWords(listController.getLessonList()[index])
    }

    private fun getTranslation(words: List<Word>, wordValue: String): String {
        return wordService.getTranslation(getWord(words, wordValue), AbstractActivityTest.testTranslation)!!.value
    }

    private fun getWord(words: List<Word>, wordValue: String): Word {
        return Stream
                .of(words)
                .filter { w -> w.value == wordValue }
                .single()
    }
}
