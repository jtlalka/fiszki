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
import java.util.Locale;

public class TestStatsActivity extends BasePageActivity {

    @BindView(R.id.test_topic)
    protected TextView testTopic;

    @BindView(R.id.test_total)
    protected Button testTotal;

    @BindView(R.id.button_correct)
    protected Button testCorrect;

    @BindView(R.id.button_incorrect)
    protected Button testIncorrect;

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
        String lessonName = lessonDto.getLessonName();
        String lessonDesc = lessonDto.getLessonLevel().name().toLowerCase(Locale.getDefault());

        this.testTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.testTotal.setText(localFormat("%s: %d", this.testTotal.getText(), lessonDto.getLessonId()));
        this.testCorrect.setText(localFormat("%s\n%d", this.testCorrect.getText(), lessonDto.getLessonId()));
        this.testIncorrect.setText(localFormat("%s\n%d", this.testIncorrect.getText(), lessonDto.getLessonId()));
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
