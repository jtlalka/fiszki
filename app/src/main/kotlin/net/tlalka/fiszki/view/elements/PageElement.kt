package net.tlalka.fiszki.view.elements

import android.app.Activity
import net.tlalka.fiszki.R
import net.tlalka.fiszki.view.activities.HelpActivity
import net.tlalka.fiszki.view.activities.LessonListActivity
import net.tlalka.fiszki.view.activities.TestListActivity
import net.tlalka.fiszki.view.activities.WordsActivity
import kotlin.reflect.KClass

enum class PageElement(val resourceId: Int, private val activityClass: KClass<out Activity>) {

    LESSONS(R.string.nav_lessons, LessonListActivity::class),
    TESTS(R.string.nav_tests, TestListActivity::class),
    WORDS(R.string.nav_words, WordsActivity::class),
    HELP(R.string.nav_help, HelpActivity::class);

    fun getActivityClass(): Class<out Activity> {
        return activityClass.java
    }
}
