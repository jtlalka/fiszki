package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.listeners.TestListener;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.utils.ValidUtils;

import java.sql.SQLException;
import java.util.List;

public class TestActivity extends BasePageActivity {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.test.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.test.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.test.desc";

    private LessonDao lessonDao;
    private WordDao wordDao;

    private List<Word> words;
    private Lesson lesson;
    private Word word;

    private TextView textViewTopic;
    private Button buttonWordShow;
    private Button buttonWordCheck;
    private Button buttonGood;
    private Button buttonBad;

    private long lessonId;
    private String lessonName;
    private String lessonDesc;
    private int wordCount;
    private int wordNumber;
    private int wordGood;
    private int wordBad;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.test_activity);

        this.initElements();
        this.initListeners();
        this.initBundle();
        this.initDataBase();

        this.runActivity();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
        this.buttonWordShow = (Button) findViewById(R.id.show_word_button);
        this.buttonWordCheck = (Button) findViewById(R.id.check_word_button);
        this.buttonGood = (Button) findViewById(R.id.button_good);
        this.buttonBad = (Button) findViewById(R.id.button_bad);
    }

    private void initListeners() {
        this.buttonWordCheck.setOnClickListener(new TestListener(this, TestListener.ACTION_CHECK));
        this.buttonGood.setOnClickListener(new TestListener(this, TestListener.ACTION_GOOD));
        this.buttonBad.setOnClickListener(new TestListener(this, TestListener.ACTION_BAD));
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

    private void initDataBase() {
        try {
            this.lessonDao = super.getDbHelper().getLessonDao();
            this.wordDao = super.getDbHelper().getWordDao();

            this.lesson = this.lessonDao.getLessonBy(lessonId);
            this.words = this.wordDao.getWordsBy(lesson);

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    private void runActivity() {
        this.wordCount = this.words.size();
        this.wordNumber = 0;
        this.wordGood = 0;
        this.wordBad = 0;

        this.generateView(this.wordNumber);
    }

    private void generateView(int number) {
        this.word = this.words.get(number);

        this.buttonWordShow.setText(this.word.getValue());
        this.buttonWordCheck.setText(getText(R.string.lesson_activity_check_word));
    }

    public void showWord() {
        try {
            Word translation = this.wordDao.getWordBy(word.getCluster(), LanguageType.PL);
            this.buttonWordCheck.setText(translation.getValue());

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    public void nextWord() {
        this.wordNumber++;

        if (this.hasNextWord()) {
            this.generateView(this.wordNumber);
        } else {
            this.showOverview();
        }
    }

    public boolean hasNextWord() {
        return this.wordNumber < this.wordCount;
    }

    public void progressUp() {
        this.word.setProgress(this.word.getProgress() + 1);
        this.wordDao.update(this.word);
        this.wordGood++;
    }

    public void progressDown() {
        this.word.setProgress(this.word.getProgress() - 1);
        this.wordDao.update(this.word);
        this.wordBad++;
    }

    public void showOverview() {
        this.updateLessonProgress();

        super.startActivity(TestStatsActivity.class, this.getOverviewBundles());
        super.finish();
    }

    private void updateLessonProgress() {
        this.lesson.setProgress(this.lesson.getProgress() + 1);
        this.lessonDao.update(this.lesson);
    }

    private Bundle getOverviewBundles() {
        Bundle bundle = new Bundle();
        bundle.putLong(TestStatsActivity.LESSON_ID, this.lessonId);
        bundle.putString(TestStatsActivity.LESSON_NAME, this.lessonName);
        bundle.putString(TestStatsActivity.LESSON_DESC, this.lessonDesc);
        bundle.putInt(TestStatsActivity.TOTAL_COUNT, this.wordNumber);
        bundle.putInt(TestStatsActivity.TOTAL_GOOD, this.wordGood);
        bundle.putInt(TestStatsActivity.TOTAL_BAD, this.wordBad);
        return bundle;
    }
}
