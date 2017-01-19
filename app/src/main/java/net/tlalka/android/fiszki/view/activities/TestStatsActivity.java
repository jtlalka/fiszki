package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;

public class TestStatsActivity extends BasePageActivity {

    @BindView(R.id.test_topic)
    protected TextView testTopic;

    @BindView(R.id.test_total)
    protected Button testTotal;

    @BindView(R.id.test_score)
    protected TextView testScore;

    @BindView(R.id.test_incorrect)
    protected TextView testIncorrect;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.test_stats_activity);
        super.getActivityComponent().inject(this);

        this.runActivity();
    }

    private void runActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.testTopic.setText(getString(R.string.test_activity_topic, lessonIndex, lessonName));
        this.testTotal.setText(getString(R.string.test_stats_activity_total, lessonDto.getCorrectScore()));
        this.testScore.setText(getString(R.string.test_stats_activity_score_vale, lessonDto.getGeneralScore()));
        this.testIncorrect.setText(getString(R.string.test_stats_activity_incorrect, lessonDto.getIncorrectScore()));
    }

    @OnClick(R.id.test_stats_repeat)
    public void onRepeatClick(View view) {
        this.navigator.openTestActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.test_stats_tests)
    public void onTestsClick(View view) {
        this.navigator.openTestListActivity(this);
        this.navigator.finish(this);
    }
}
