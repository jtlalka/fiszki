package net.tlalka.android.fiszki.view.activities

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import net.tlalka.android.fiszki.domain.controllers.ListController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class WordsActivityTest : AbstractActivityTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Inject
    lateinit var listController: ListController

    @Before
    fun setup() {
        getEspressoComponent(activityRule.activity).inject(this)
    }

    @Test
    fun name() {
        // given
        mainPage.openWords()

        // when
        listController.lessonList.forEach { lesson ->
            run {
                wordsPage.openItem(lesson.name)
                wordsPage.openItem(lesson.name)
            }
        }
    }
}
