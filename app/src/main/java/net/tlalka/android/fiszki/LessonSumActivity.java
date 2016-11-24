package net.tlalka.android.fiszki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.listeners.LessonSumListener;
import net.tlalka.android.fwork.FworkActivity;
import net.tlalka.android.fwork.FworkInit;

public class LessonSumActivity extends FworkActivity {

    public static final String LESSON_NAME = "LessonElement.name";
    public static final String LESSON_DESC = "LessonElement.desc";
    public static final String TOTAL_COUNT = "LessonSumActivity.count";
    public static final String TOTAL_GOOD = "LessonSumActivity.good";
    public static final String TOTAL_BAD = "LessonSumActivity.bad";

    private TextView textViewTopic;
    private Button buttonSum;
    private Button buttonGood;
    private Button buttonBad;
    private Button buttonRepeat;
    private Button buttonLessons;

    private String lessonName;
    private String lessonDesc;
    private int totalCount;
    private int totalGood;
    private int totalBad;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, R.layout.lesson_sum_view);

        this.initElements();
        this.initListeners();
        this.initBundle();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.textViewTopic);
        this.buttonSum = (Button) findViewById(R.id.buttonSum);
        this.buttonGood = (Button) findViewById(R.id.buttonGood);
        this.buttonBad = (Button) findViewById(R.id.buttonBad);
        this.buttonRepeat = (Button) findViewById(R.id.buttonRepeat);
        this.buttonLessons = (Button) findViewById(R.id.buttonLessons);
    }

    private void initListeners() {
        this.buttonRepeat.setOnClickListener(new LessonSumListener(this, LessonSumListener.ACTION_GOTO_LESSON));
        this.buttonLessons.setOnClickListener(new LessonSumListener(this, LessonSumListener.ACTION_GOTO_LESSONS_LIST));
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (FworkInit.Valid.isNotNull(argsBundle)) {
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);
            this.totalCount = argsBundle.getInt(TOTAL_COUNT, 0);
            this.totalGood = argsBundle.getInt(TOTAL_GOOD, 0);
            this.totalBad = argsBundle.getInt(TOTAL_BAD, 0);

            this.textViewTopic.setText(lessonName + " - " + lessonDesc);
            this.buttonSum.setText(this.buttonSum.getText() + ": " + this.totalCount);
            this.buttonGood.setText(this.buttonGood.getText() + "\n" + this.totalGood);
            this.buttonBad.setText(this.buttonBad.getText() + "\n" + this.totalBad);
        }
    }

    public void gotoLesson() {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putString(LessonSumActivity.LESSON_NAME, this.lessonName);
        bundleToSend.putString(LessonSumActivity.LESSON_DESC, this.lessonDesc);

        super.startActivity(LessonActivity.class, bundleToSend);
        super.finish();
    }

    public void gotoLessonsList() {
        super.startActivity(LessonsListActivity.class, new Bundle());
        super.finish();
    }
}
