package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R

class TestPage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.test_icon)
        isVisible(R.id.test_topic)
        isVisible(R.id.test_progress)

        isVisible(R.id.test_show_word)
        isVisible(R.id.test_answer_1)
        isVisible(R.id.test_answer_2)
        isVisible(R.id.test_answer_3)
        isVisible(R.id.test_answer_4)
    }

    fun getWord(): String {
        return getText(R.id.test_show_word)
    }

    fun getAnswer1(): String {
        return getText(R.id.test_answer_1)
    }

    fun getAnswer2(): String {
        return getText(R.id.test_answer_2)
    }

    fun getAnswer3(): String {
        return getText(R.id.test_answer_3)
    }

    fun getAnswer4(): String {
        return getText(R.id.test_answer_4)
    }

    fun clickAnswer1() {
        clickItem(R.id.test_answer_1)
        isUpdated(R.id.test_answer_1)
    }

    fun clickAnswer2() {
        clickItem(R.id.test_answer_2)
        isUpdated(R.id.test_answer_2)
    }

    fun clickAnswer3() {
        clickItem(R.id.test_answer_3)
        isUpdated(R.id.test_answer_3)
    }

    fun clickAnswer4() {
        clickItem(R.id.test_answer_4)
        isUpdated(R.id.test_answer_4)
    }

    fun matchProgress(progress: Int) {
        matchText(R.id.test_progress, progress.toString() + "\\ .*")
    }

    fun closeActivity() {
        clickItem(R.id.item_back)
    }
}
