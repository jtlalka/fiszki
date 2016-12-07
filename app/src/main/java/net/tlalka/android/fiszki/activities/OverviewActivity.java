package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.listeners.OverviewListener;
import net.tlalka.android.fiszki.utils.ValidUtils;

public class OverviewActivity extends BasePageActivity {

    public static final String LESSON_NAME = "net.tlalka.android.fiszki.overview.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.overview.desc";
    public static final String TOTAL_COUNT = "net.tlalka.android.fiszki.overview.count";
    public static final String TOTAL_GOOD = "net.tlalka.android.fiszki.overview.good";
    public static final String TOTAL_BAD = "net.tlalka.android.fiszki.overview.bad";

    private TextView textViewTopic;
    private Button buttonCount;
    private Button buttonGood;
    private Button buttonBad;
    private Button buttonRepeat;
    private Button buttonLessons;

    private String lessonName;
    private String lessonDesc;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.overview_activity);

        this.initElements();
        this.initListeners();
        this.initBundle();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
        this.buttonCount = (Button) findViewById(R.id.button_count);
        this.buttonGood = (Button) findViewById(R.id.button_good);
        this.buttonBad = (Button) findViewById(R.id.button_bad);
        this.buttonRepeat = (Button) findViewById(R.id.button_repeat);
        this.buttonLessons = (Button) findViewById(R.id.button_lessons);
    }

    private void initListeners() {
        this.buttonRepeat.setOnClickListener(new OverviewListener(this, OverviewListener.ACTION_GOTO_LESSON));
        this.buttonLessons.setOnClickListener(new OverviewListener(this, OverviewListener.ACTION_GOTO_LESSON_LIST));
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (ValidUtils.isNotNull(argsBundle)) {
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

    public void gotoLesson() {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putString(LessonActivity.LESSON_NAME, this.lessonName);
        bundleToSend.putString(LessonActivity.LESSON_DESC, this.lessonDesc);

        super.startActivity(LessonActivity.class, bundleToSend);
        super.finish();
    }

    public void gotoLessonList() {
        super.startActivity(LessonsActivity.class, new Bundle());
        super.finish();
    }
}
