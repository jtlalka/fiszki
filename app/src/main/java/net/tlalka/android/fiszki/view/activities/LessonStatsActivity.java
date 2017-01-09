package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;

public class LessonStatsActivity extends BasePageActivity {

    @BindView(R.id.lesson_topic)
    protected TextView lessonTopic;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_stats_activity);
        super.getActivityComponent().inject(this);

        this.initActivity();
    }

    private void initActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.lessonTopic.setText(getString(R.string.lesson_activity_topic, lessonIndex, lessonName));
    }

    @OnClick(R.id.lessons_stats_repeat)
    public void onRepeatClick(View view) {
        this.navigator.openLessonActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.lessons_stats_lessons)
    public void onLessonsClick(View view) {
        this.navigator.openLessonListActivity(this);
        this.navigator.finish(this);
    }
}
