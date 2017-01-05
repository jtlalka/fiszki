package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;

public class TestStatsActivity extends BasePageActivity {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.test.stats.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.test.stats.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.test.stats.desc";

    public static final String TOTAL_COUNT = "net.tlalka.android.fiszki.test.stats.count";
    public static final String TOTAL_GOOD = "net.tlalka.android.fiszki.test.stats.good";
    public static final String TOTAL_BAD = "net.tlalka.android.fiszki.test.stats.bad";

    private TextView textViewTopic;
    private Button buttonCount;
    private Button buttonGood;
    private Button buttonBad;

    private long lessonId;
    private String lessonName;
    private String lessonDesc;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.test_stats_activity);

        this.initElements();
        this.initBundle();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
        this.buttonCount = (Button) findViewById(R.id.button_count);
        this.buttonGood = (Button) findViewById(R.id.button_good);
        this.buttonBad = (Button) findViewById(R.id.button_bad);
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (ValidUtils.isNotNull(argsBundle)) {
            this.lessonId = argsBundle.getLong(LESSON_ID);
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);
            int totalCount = argsBundle.getInt(TOTAL_COUNT, 0);
            int totalGood = argsBundle.getInt(TOTAL_GOOD, 0);
            int totalBad = argsBundle.getInt(TOTAL_BAD, 0);

            this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
            this.buttonCount.setText(localFormat("%s: %d", this.buttonCount.getText(), totalCount));
            this.buttonGood.setText(localFormat("%s\n%d", this.buttonGood.getText(), totalGood));
            this.buttonBad.setText(localFormat("%s\n%d", this.buttonBad.getText(), totalBad));
        }
    }

    public void onRepeatClick(View view) {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putLong(TestActivity.LESSON_ID, this.lessonId);
        bundleToSend.putString(TestActivity.LESSON_NAME, this.lessonName);
        bundleToSend.putString(TestActivity.LESSON_DESC, this.lessonDesc);

        super.startActivity(LessonActivity.class, bundleToSend);
        super.finish();
    }

    public void onTestsClick(View view) {
        super.startActivity(TestActivity.class);
        super.finish();
    }
}
