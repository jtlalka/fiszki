package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.listeners.LessonListener;
import net.tlalka.android.fiszki.models.DatabaseManager;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.utils.ValidUtils;

import java.sql.SQLException;
import java.util.List;

public class LessonActivity extends BasePageActivity {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.lesson.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.lesson.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.lesson.desc";

    private LessonDao lessonDao;
    private WordDao wordDao;

    private List<Word> wordList;
    private Word word;
    private Lesson lesson;

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
        super.setContentView(R.layout.lesson_activity);

        this.initElements();
        this.initListeners();
        this.initBundle();
        this.initDataBase();

        this.runActivity();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
        this.buttonWordShow = (Button) findViewById(R.id.button_word_show);
        this.buttonWordCheck = (Button) findViewById(R.id.button_word_check);
        this.buttonGood = (Button) findViewById(R.id.button_good);
        this.buttonBad = (Button) findViewById(R.id.button_bad);
    }

    private void initListeners() {
        this.buttonWordCheck.setOnClickListener(new LessonListener(this, LessonListener.ACTION_CHECK));
        this.buttonGood.setOnClickListener(new LessonListener(this, LessonListener.ACTION_GOOD));
        this.buttonBad.setOnClickListener(new LessonListener(this, LessonListener.ACTION_BAD));
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
            this.lessonDao = DatabaseManager.getHelper(this).getLessonDao();
            this.wordDao = DatabaseManager.getHelper(this).getWordDao();

            this.lesson = this.lessonDao.getLessonBy(lessonId);
            this.wordList = this.wordDao.getWordsBy(lesson);

            Log.d(this.getLocalClassName(), lesson.getName() + " " + lesson.getProgress() + " / " + lesson.getScore());
            for (Word word : wordList) {
                Log.d(this.getLocalClassName(), word.getValue() + " " + word.getProgress());
            }

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    private void runActivity() {
        this.wordCount = this.wordList.size();
        this.wordNumber = 0;
        this.wordGood = 0;
        this.wordBad = 0;

        this.generateView(this.wordNumber);
    }

    private void generateView(int number) {
        this.word = this.wordList.get(number);

        this.buttonWordShow.setText(this.word.getValue());
        this.buttonWordCheck.setText(getText(R.string.activity_lesson_show));
    }

    public void showWord() {
        try {
            Word translation = this.wordDao.getWordBy(word.getCluster(), LanguageType.PL);
            this.buttonWordCheck.setText(translation.getValue());

        } catch (SQLException ignore) {
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
        this.wordDao.save(this.word);
        this.wordGood++;
    }

    public void progressDown() {
        this.word.setProgress(this.word.getProgress() - 1);
        this.wordDao.save(this.word);
        this.wordBad++;
    }

    public void showOverview() {
        this.updateLessonProgress();

        super.startActivity(OverviewActivity.class, this.getOverviewBundles());
        super.finish();
    }

    private void updateLessonProgress() {
        this.lesson.setProgress(this.lesson.getProgress() + 1);
        this.lessonDao.save(this.lesson);
    }

    private Bundle getOverviewBundles() {
        Bundle bundle = new Bundle();
        bundle.putLong(OverviewActivity.LESSON_ID, this.lessonId);
        bundle.putString(OverviewActivity.LESSON_NAME, this.lessonName);
        bundle.putString(OverviewActivity.LESSON_DESC, this.lessonDesc);
        bundle.putInt(OverviewActivity.TOTAL_COUNT, this.wordNumber);
        bundle.putInt(OverviewActivity.TOTAL_GOOD, this.wordGood);
        bundle.putInt(OverviewActivity.TOTAL_BAD, this.wordBad);
        return bundle;
    }
}
