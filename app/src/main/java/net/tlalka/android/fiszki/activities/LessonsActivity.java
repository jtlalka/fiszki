package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.widget.ListView;
import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LessonsAdapter;
import net.tlalka.android.fiszki.listeners.LessonsListener;
import net.tlalka.android.fiszki.services.LessonListService;

import javax.inject.Inject;

public class LessonsActivity extends BasePageActivity {

    @BindView(R.id.list_view_lessons)
    protected ListView listView;

    @Inject
    protected LessonListService lessonListService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lessons_activity);
        super.getActivityComponent().inject(this);

        this.initLessonsList();
    }

    private void initLessonsList() {
        listView.setAdapter(new LessonsAdapter(this, lessonListService.getLessons()));
        listView.setOnItemClickListener(new LessonsListener(this));
    }
}
