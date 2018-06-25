package net.tlalka.android.fiszki.view.activities

import javax.inject.Inject

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import butterknife.BindView
import butterknife.OnClick
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.view.navigations.Navigator

class TestScoreActivity : BasePageActivity() {

    @BindView(R.id.test_topic)
    lateinit var testTopic: TextView

    @BindView(R.id.test_score_total)
    lateinit var testTotal: Button

    @BindView(R.id.test_score_value)
    lateinit var testScore: TextView

    @BindView(R.id.test_score_incorrect)
    lateinit var testIncorrect: TextView

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var lessonDto: LessonDto

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.test_score_activity)
        super.activityComponent.inject(this)

        runActivity()
    }

    private fun runActivity() {
        testTopic.text = getString(R.string.test_topic, lessonDto.lessonIndex, lessonDto.lessonName)
        testTotal.text = getString(R.string.test_score_total, lessonDto.correctScore)
        testScore.text = getString(R.string.test_score_value, lessonDto.generalScore)
        testIncorrect.text = getString(R.string.test_score_incorrect, lessonDto.incorrectScore)
    }

    @OnClick(R.id.test_score_repeat)
    fun onRepeatClick(view: View) {
        navigator.openTestActivity(this, this.lessonDto)
        navigator.finish(this)
    }

    @OnClick(R.id.test_score_tests)
    fun onTestsClick(view: View) {
        navigator.openTestListActivity(this)
        navigator.finish(this)
    }
}
