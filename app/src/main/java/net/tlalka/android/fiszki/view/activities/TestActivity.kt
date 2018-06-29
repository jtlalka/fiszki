package net.tlalka.android.fiszki.view.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.TestController
import net.tlalka.android.fiszki.domain.utils.ValidUtils
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment
import net.tlalka.android.fiszki.view.navigations.Navigator
import javax.inject.Inject

class TestActivity : BasePageActivity(), LanguageDialogFragment.DialogListener {

    @BindView(R.id.test_topic)
    lateinit var testTopic: TextView

    @BindView(R.id.test_progress)
    lateinit var testProgress: TextView

    @BindView(R.id.test_show_word)
    lateinit var testWordShow: Button

    @BindView(R.id.test_answer_1)
    lateinit var testAnswer1: Button

    @BindView(R.id.test_answer_2)
    lateinit var testAnswer2: Button

    @BindView(R.id.test_answer_3)
    lateinit var testAnswer3: Button

    @BindView(R.id.test_answer_4)
    lateinit var testAnswer4: Button

    @Inject
    lateinit var testController: TestController

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var lessonDto: LessonDto

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.test_activity)
        super.activityComponent.inject(this)

        runActivity()
    }

    private fun runActivity() {
        testTopic.text = getString(R.string.test_topic, lessonDto.lessonIndex, lessonDto.lessonName)
        generateView()
    }

    private fun generateView() {
        if (testController.hasNextWord()) {
            val index = testController.wordIndex
            val total = testController.getTestSize()

            testProgress.text = getString(R.string.test_progress, index + 1, total)
            testWordShow.text = testController.getNextWord()
            generateAnswers()
        } else {
            showTestSummary()
        }
    }

    private fun generateAnswers() {
        val answers = testController.getThisAnswers()

        if (ValidUtils.isNotEmpty(answers)) {
            testAnswer1.text = answers[0]
            testAnswer2.text = answers[1]
            testAnswer3.text = answers[2]
            testAnswer4.text = answers[3]
        } else {
            LanguageDialogFragment
                    .getInstance(testController.getLanguages())
                    .show(fragmentManager, LanguageDialogFragment::class.java.name)
        }
    }

    private fun showTestSummary() {
        testController.updateBestScore()
        testController.updateLessonDto(lessonDto)
        navigator.openTestScoreActivity(this, lessonDto)
        navigator.finish(this)
    }

    override fun onLanguageSelected(languageType: LanguageType) {
        testController.setTranslation(languageType)
        generateAnswers()
    }

    @OnClick(R.id.test_answer_1)
    fun onAnswer1Click(view: View) {
        validAnswer(testAnswer1, 0)
    }

    @OnClick(R.id.test_answer_2)
    fun onAnswer2Click(view: View) {
        validAnswer(testAnswer2, 1)
    }

    @OnClick(R.id.test_answer_3)
    fun onAnswer3Click(view: View) {
        validAnswer(testAnswer3, 2)
    }

    @OnClick(R.id.test_answer_4)
    fun onAnswer4Click(view: View) {
        validAnswer(testAnswer4, 3)
    }

    private fun validAnswer(button: Button?, index: Int) {
        if (testController.validAnswer(index)) {
            button?.setBackgroundResource(R.drawable.patch_green)
            delayedNextView()
        } else {
            button?.setBackgroundResource(R.drawable.patch_red)
        }
    }

    private fun delayedNextView() {
        Handler().postDelayed({
            testAnswer1.setBackgroundResource(R.drawable.patch_cyan)
            testAnswer2.setBackgroundResource(R.drawable.patch_cyan)
            testAnswer3.setBackgroundResource(R.drawable.patch_cyan)
            testAnswer4.setBackgroundResource(R.drawable.patch_cyan)
            generateView()
        }, 500)
    }
}
