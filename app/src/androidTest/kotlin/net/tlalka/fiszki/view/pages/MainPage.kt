package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R

class MainPage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.main_list_view)
        isImageDescription(R.string.app_name)
    }

    fun openLessons() {
        clickListItem(R.id.main_list_view, 0)
    }

    fun openTests() {
        clickListItem(R.id.main_list_view, 1)
    }

    fun openWords() {
        clickListItem(R.id.main_list_view, 2)
    }

    fun openHelp() {
        clickListItem(R.id.main_list_view, 3)
    }
}
