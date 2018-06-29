package net.tlalka.fiszki.view.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import net.tlalka.fiszki.R
import net.tlalka.fiszki.model.dto.LessonDto
import net.tlalka.fiszki.view.navigations.Navigator
import javax.inject.Inject

class LessonScoreActivity : BasePageActivity() {

    @BindView(R.id.lesson_topic)
    lateinit var lessonTopic: TextView

    @BindView(R.id.lesson_score_total)
    lateinit var lessonTotal: TextView

    @BindView(R.id.lesson_score_incorrect)
    lateinit var lessonIncorrect: TextView

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var lessonDto: LessonDto

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.lesson_score_activity)
        super.activityComponent.inject(this)

        initActivity()
    }

    private fun initActivity() {
        lessonTopic.text = getString(R.string.lesson_topic, lessonDto.lessonIndex, lessonDto.lessonName)
        lessonTotal.text = getString(R.string.test_score_total, lessonDto.generalScore)
        lessonIncorrect.text = getString(R.string.test_score_incorrect, lessonDto.incorrectScore)
    }

    @OnClick(R.id.lesson_score_repeat)
    fun onRepeatClick(view: View) {
        this.navigator.openLessonActivity(this, lessonDto)
        this.navigator.finish(this)
    }

    @OnClick(R.id.lesson_score_lessons)
    fun onLessonsClick(view: View) {
        this.navigator.openLessonListActivity(this)
        this.navigator.finish(this)
    }
}
