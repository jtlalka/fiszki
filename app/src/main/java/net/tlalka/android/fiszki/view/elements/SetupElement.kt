package net.tlalka.android.fiszki.view.elements

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.view.activities.AbstractActivity
import net.tlalka.android.fiszki.view.activities.SettingsActivity

enum class SetupElement(private val resourceId: Int) {

    SETTINGS(R.id.item_settings) {
        override fun action(activity: AbstractActivity) {
            activity.startActivity(SettingsActivity::class.java)
        }
    },
    ABOUT(R.id.item_about) {
        override fun action(activity: AbstractActivity) {
            activity.alert("Not implemented yet.")
        }
    },
    BACK(R.id.item_back) {
        override fun action(activity: AbstractActivity) {
            activity.finish()
        }
    };

    abstract fun action(activity: AbstractActivity)

    companion object {
        fun triggerAction(activity: AbstractActivity, resourceId: Int): Boolean {
            for (item in values()) {
                if (item.resourceId == resourceId) {
                    item.action(activity)
                    return true
                }
            }
            return false
        }
    }
}
