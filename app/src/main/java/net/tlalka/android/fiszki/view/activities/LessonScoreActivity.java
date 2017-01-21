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

public class LessonScoreActivity extends BasePageActivity {

    @BindView(R.id.lesson_topic)
    protected TextView lessonTopic;

    @BindView(R.id.lesson_score_total)
    protected TextView lessonTotal;

    @BindView(R.id.lesson_score_incorrect)
    protected TextView lessonIncorrect;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_score_activity);
        super.getActivityComponent().inject(this);

        this.initActivity();
    }

    private void initActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.lessonTopic.setText(getString(R.string.lesson_topic, lessonIndex, lessonName));
        this.lessonTotal.setText(getString(R.string.test_score_total, lessonDto.getGeneralScore()));
        this.lessonIncorrect.setText(getString(R.string.test_score_incorrect, lessonDto.getIncorrectScore()));
    }

    @OnClick(R.id.lessons_score_repeat)
    public void onRepeatClick(View view) {
        this.navigator.openLessonActivity(this, lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.lessons_score_lessons)
    public void onLessonsClick(View view) {
        this.navigator.openLessonListActivity(this);
        this.navigator.finish(this);
    }
}
