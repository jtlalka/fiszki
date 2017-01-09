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

    @BindView(R.id.text_view_topic)
    protected TextView textViewTopic;

    @BindView(R.id.button_count)
    protected Button buttonCount;

    @BindView(R.id.button_good)
    protected Button buttonGood;

    @BindView(R.id.button_bad)
    protected Button buttonBad;

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

        this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.buttonCount.setText(localFormat("%s: %d", this.buttonCount.getText(), lessonDto.getLessonId()));
        this.buttonGood.setText(localFormat("%s\n%d", this.buttonGood.getText(), lessonDto.getLessonId()));
        this.buttonBad.setText(localFormat("%s\n%d", this.buttonBad.getText(), lessonDto.getLessonId()));
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
