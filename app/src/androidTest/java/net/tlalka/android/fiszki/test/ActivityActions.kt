package net.tlalka.android.fiszki.test

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.util.TreeIterables
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

object ActivityActions {

    fun readTest(reference: AtomicReference<String>): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController, view: View) {
                reference.set((view as TextView).text.toString())
            }
        }
    }

    fun waitForUpdate(timeout: Long): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "wait for text new value"
            }

            override fun perform(uiController: UiController, view: View) {
                val value1 = getTextById(view)
                val time = 50L

                for (i in timeout downTo 0 step time) {
                    uiController.loopMainThreadForAtLeast(time)
                    val value2 = getTextById(view)

                    if (!isCompare(value1, value2)) {
                        return
                    }
                }
            }

            private fun getTextById(view: View): String {
                return (view as TextView).text.toString()
            }

            private fun isCompare(value1: String?, value2: String?): Boolean {
                return value1 == null && value2 == null || value1 != null && value1 == value2
            }
        }
    }

    fun waitForView(resId: Int, timeout: Long): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for resource id: $resId"
            }

            override fun perform(uiController: UiController, view: View) {
                val time = 50L

                for (i in timeout downTo 0 step time) {
                    uiController.loopMainThreadForAtLeast(time)

                    if (isView(view, resId)) {
                        return
                    }
                }
            }

            private fun isView(view: View, resId: Int): Boolean {
                for (item in TreeIterables.breadthFirstViewTraversal(view)) {
                    if (withId(resId).matches(item)) {
                        return true
                    }
                }
                return false
            }
        }
    }
}
