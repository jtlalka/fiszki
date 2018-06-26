package net.tlalka.android.fiszki.view.pages

import net.tlalka.android.fiszki.R

class LessonPage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.lesson_icon)
        isVisible(R.id.lesson_topic)
        isVisible(R.id.lesson_progress)
        isVisible(R.id.lesson_show_word)
        isVisible(R.id.lesson_check_word)
        isVisible(R.id.button_incorrect)
        isVisible(R.id.button_correct)
    }

    fun clickTranslation() {
        clickItem(R.id.lesson_check_word)
    }

    fun clickCorrect() {
        clickItem(R.id.button_correct, R.string.button_correct)
    }

    fun clickIncorrect() {
        clickItem(R.id.button_incorrect, R.string.button_incorrect)
    }

    fun matchProgress(progress: Int) {
        matchText(R.id.lesson_progress, ".*$progress")
    }

    fun closeActivity() {
        clickItem(R.id.item_back)
    }
}
