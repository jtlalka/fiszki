package net.tlalka.fiszki.view.navigations

import android.app.Activity
import android.content.Context
import android.content.Intent
import net.tlalka.fiszki.model.dto.LessonDto
import net.tlalka.fiszki.model.dto.WelcomeDto
import net.tlalka.fiszki.view.activities.*
import org.parceler.Parcels
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun openWelcomeActivity(context: Context, welcomeDto: WelcomeDto) {
        val intent = Intent(context, WelcomeActivity::class.java)
        intent.putExtra(WelcomeDto::class.java.name, Parcels.wrap(welcomeDto))
        context.startActivity(intent)
    }

    fun openLessonActivity(context: Context, lessonDto: LessonDto) {
        val intent = Intent(context, LessonActivity::class.java)
        intent.putExtra(LessonDto::class.java.name, Parcels.wrap(lessonDto))
        context.startActivity(intent)
    }

    fun openLessonListActivity(context: Context) {
        context.startActivity(Intent(context, LessonListActivity::class.java))
    }

    fun openLessonScoreActivity(context: Context, lessonDto: LessonDto) {
        val intent = Intent(context, LessonScoreActivity::class.java)
        intent.putExtra(LessonDto::class.java.name, Parcels.wrap(lessonDto))
        context.startActivity(intent)
    }

    fun openTestActivity(context: Context, lessonDto: LessonDto) {
        val intent = Intent(context, TestActivity::class.java)
        intent.putExtra(LessonDto::class.java.name, Parcels.wrap(lessonDto))
        context.startActivity(intent)
    }

    fun openTestListActivity(context: Context) {
        context.startActivity(Intent(context, TestListActivity::class.java))
    }

    fun openTestScoreActivity(context: Context, lessonDto: LessonDto) {
        val intent = Intent(context, TestScoreActivity::class.java)
        intent.putExtra(LessonDto::class.java.name, Parcels.wrap(lessonDto))
        context.startActivity(intent)
    }

    fun openWordsActivity(context: Context, lessonDto: LessonDto) {
        val intent = Intent(context, WordsActivity::class.java)
        intent.putExtra(LessonDto::class.java.name, Parcels.wrap(lessonDto))
        context.startActivity(intent)
    }

    fun finish(activity: Activity) {
        activity.finish()
    }
}
