package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.services.LessonListService;
import net.tlalka.android.fiszki.view.adapters.LessonsAdapter;
import net.tlalka.android.fiszki.view.listeners.LessonsListener;

import javax.inject.Inject;

public class LessonListActivity extends BasePageActivity {

    @BindView(R.id.lesson_list_view)
    protected ListView listView;

    @Inject
    protected LessonListService lessonListService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lesson_list_activity);
        super.getActivityComponent().inject(this);

        this.initLessonsList();
    }

    private void initLessonsList() {
        listView.setAdapter(new LessonsAdapter(this, lessonListService.getLessons()));
        listView.setOnItemClickListener(new LessonsListener(this));
    }
}
