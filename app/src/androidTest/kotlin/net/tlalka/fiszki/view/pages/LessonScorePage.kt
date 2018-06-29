package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R

class LessonScorePage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.lesson_icon)
        isVisible(R.id.lesson_topic)
        isVisible(R.id.lesson_score_total)
        isVisible(R.id.lesson_score_incorrect)
        isVisible(R.id.lesson_score_repeat, R.string.lesson_score_repeat)
        isVisible(R.id.lesson_score_lessons, R.string.lesson_score_lessons)
    }

    fun clickRepeat() {
        clickItem(R.id.lesson_score_repeat, R.string.lesson_score_repeat)
    }

    fun clickLessons() {
        clickItem(R.id.lesson_score_lessons, R.string.lesson_score_lessons)
    }
}
