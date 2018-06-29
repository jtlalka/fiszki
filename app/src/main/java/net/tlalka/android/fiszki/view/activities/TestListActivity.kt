package net.tlalka.android.fiszki.view.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import butterknife.BindView
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.model.dto.LessonDto
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.view.adapters.TestsAdapter
import net.tlalka.android.fiszki.view.navigations.Navigator
import javax.inject.Inject

class TestListActivity : BasePageActivity(), AdapterView.OnItemClickListener {

    @BindView(R.id.test_list_view)
    lateinit var listView: ListView

    @Inject
    lateinit var listController: ListController

    @Inject
    lateinit var navigator: Navigator

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.test_list_activity)
        super.activityComponent.inject(this)

        initLessonsList()
    }

    private fun initLessonsList() {
        listView.adapter = TestsAdapter(this, listController.getLessonList())
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val lesson = parent.getItemAtPosition(position) as Lesson

        navigator.openTestActivity(this, LessonDto(lesson, position + 1))
        navigator.finish(this)
    }
}
