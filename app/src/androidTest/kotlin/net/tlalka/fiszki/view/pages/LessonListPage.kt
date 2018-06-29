package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R

class LessonListPage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.lesson_icon)
        isVisible(R.id.lesson_topic)
        isVisible(R.id.lesson_progress)
        isVisible(R.id.lesson_list_view)
    }

    fun openItem(index: Int) {
        clickListItem(R.id.lesson_list_view, index)
    }
}
