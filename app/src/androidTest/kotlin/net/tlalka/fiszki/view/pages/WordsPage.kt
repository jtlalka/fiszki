package net.tlalka.fiszki.view.pages

import net.tlalka.fiszki.R



class WordsPage: AbstractPage() {

    override fun valid() {
        isVisible(R.id.words_icon)
        isVisible(R.id.words_topic)
        isVisible(R.id.word_list_view)
    }

    fun openItem(name: String) {
        clickItem(R.id.word_list_lesson, name)
    }
}
