package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.navigations.Navigator;

import javax.inject.Inject;

public class LessonStatsActivity extends BasePageActivity {

    @BindView(R.id.text_view_topic)
    protected TextView textViewTopic;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_stats_activity);
        super.getActivityComponent().inject(this);

        this.initView();
    }

    private void initView() {
        this.textViewTopic.setText(localFormat("%s - %s", lessonDto.getLessonName(), lessonDto.getLessonLevel()));
    }

    @OnClick(R.id.button_repeat)
    public void onRepeatClick(View view) {
        this.navigator.openLessonActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.button_lessons)
    public void onLessonsClick(View view) {
        this.navigator.openLessonListActivity(this);
        this.navigator.finish(this);
    }
}
