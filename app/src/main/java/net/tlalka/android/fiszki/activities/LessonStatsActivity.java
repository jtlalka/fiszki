package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.utils.ValidUtils;

public class LessonStatsActivity extends BasePageActivity {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.lesson.stats.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.lesson.stats.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.lesson.stats.desc";

    private TextView textViewTopic;

    private long lessonId;
    private String lessonName;
    private String lessonDesc;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_stats_activity);

        this.initElements();
        this.initBundle();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (ValidUtils.isNotNull(argsBundle)) {
            this.lessonId = argsBundle.getLong(LESSON_ID);
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);

            this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        }
    }

    @XmlOnClick
    public void onRepeatClick(View view) {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putLong(LessonActivity.LESSON_ID, this.lessonId);
        bundleToSend.putString(LessonActivity.LESSON_NAME, this.lessonName);
        bundleToSend.putString(LessonActivity.LESSON_DESC, this.lessonDesc);

        super.startActivity(LessonActivity.class, bundleToSend);
        super.finish();
    }

    @XmlOnClick
    public void onLessonsClick(View view) {
        super.startActivity(LessonsActivity.class);
        super.finish();
    }
}
