package net.tlalka.android.fiszki.view.activities

import javax.inject.Inject

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

import butterknife.BindView
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.view.adapters.LessonsAdapter
import net.tlalka.android.fiszki.view.navigations.Navigator

class LessonListActivity : BasePageActivity(), AdapterView.OnItemClickListener {

    @BindView(R.id.lesson_list_view)
    lateinit var listView: ListView

    @Inject
    lateinit var listController: ListController

    @Inject
    lateinit var navigator: Navigator

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.lesson_list_activity)
        super.activityComponent.inject(this)

        initLessonsList()
    }

    private fun initLessonsList() {
        listView.adapter = LessonsAdapter(this, listController.lessonList)
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val lesson = parent.getItemAtPosition(position) as Lesson

        navigator.openLessonActivity(this, LessonDto(lesson, position + 1))
        navigator.finish(this)
    }
}
