package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.services.LessonListService;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.view.adapters.LessonsAdapter;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;

public class LessonListActivity extends BasePageActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.lesson_list_view)
    protected ListView listView;

    @Inject
    protected LessonListService lessonListService;

    @Inject
    protected Navigator navigator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lesson_list_activity);
        super.getActivityComponent().inject(this);

        this.initLessonsList();
    }

    private void initLessonsList() {
        this.listView.setAdapter(new LessonsAdapter(this, lessonListService.getLessons()));
        this.listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Lesson lesson = (Lesson) parent.getItemAtPosition(position);

        this.navigator.openLessonActivity(this, new LessonDto(lesson, position + 1));
        this.navigator.finish(this);
    }
}
