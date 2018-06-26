package net.tlalka.android.fiszki.view.pages

import net.tlalka.android.fiszki.R

class WelcomePage : AbstractPage() {

    override fun valid() {
        isVisible(R.id.welcome_layout)
        isVisible(R.id.welcome_message)
        isImageDescription(R.string.null_value)
    }

    fun clickLayout() {
        clickItem(R.id.welcome_layout)
    }

    fun clickMessage() {
        clickItem(R.id.welcome_message, R.string.welcome_message)
    }
}
