package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R

class TestListPage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.test_icon)
        isVisible(R.id.test_topic)
        isVisible(R.id.test_progress)
        isVisible(R.id.test_list_view)
    }

    fun openItem(index: Int) {
        clickListItem(R.id.test_list_view, index)
    }
}
