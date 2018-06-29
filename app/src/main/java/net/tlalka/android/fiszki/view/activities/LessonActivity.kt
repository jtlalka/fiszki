package net.tlalka.android.fiszki.view.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.LessonController
import net.tlalka.android.fiszki.domain.utils.ValidUtils
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.model.types.LanguageType
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment
import net.tlalka.android.fiszki.view.navigations.Navigator
import javax.inject.Inject

class LessonActivity : BasePageActivity(), LanguageDialogFragment.DialogListener {

    @BindView(R.id.lesson_topic)
    lateinit var lessonTopic: TextView

    @BindView(R.id.lesson_progress)
    lateinit var lessonProgress: TextView

    @BindView(R.id.lesson_show_word)
    lateinit var lessonShowWord: Button

    @BindView(R.id.lesson_check_word)
    lateinit var lessonCheckWord: Button

    @Inject
    lateinit var lessonController: LessonController

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var lessonDto: LessonDto

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.lesson_activity)
        super.activityComponent.inject(this)

        runActivity()
    }

    private fun runActivity() {
        lessonTopic.text = getString(R.string.lesson_topic, lessonDto.lessonIndex, lessonDto.lessonName)
        generateView()
    }

    private fun generateView() {
        if (lessonController.hasNextWord()) {
            lessonProgress.text = lessonController.getLessonStatus()
            lessonShowWord.text = lessonController.getNextWord()
            lessonCheckWord.text = getText(R.string.lesson_check_word)
        } else {
            showLessonSummary()
        }
    }

    private fun showLessonSummary() {
        lessonController.updateLessonDto(lessonDto)
        lessonController.updateLessonProgress()
        navigator.openLessonScoreActivity(this, lessonDto)
        navigator.finish(this)
    }

    @OnClick(R.id.lesson_check_word)
    fun onCheckWordClick(@Suppress("UNUSED_PARAMETER") view: View?) {
        val word = this.lessonController.getTranslateWord()

        if (ValidUtils.isNotNull(word)) {
            lessonCheckWord.text = word.value
        } else {
            LanguageDialogFragment
                    .getInstance(lessonController.getLanguages())
                    .show(fragmentManager, LanguageDialogFragment::class.java.name)
        }
    }

    override fun onLanguageSelected(languageType: LanguageType) {
        lessonController.setTranslation(languageType)
        onCheckWordClick(lessonCheckWord)
    }

    @OnClick(R.id.button_correct)
    fun onCorrectClick(view: View) {
        lessonController.correctAnswer()
        generateView()
    }

    @OnClick(R.id.button_incorrect)
    fun onIncorrectClick(view: View) {
        lessonController.incorrectAnswer()
        generateView()
    }
}
