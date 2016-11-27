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
import net.tlalka.android.fiszki.models.entity.LessonEntity;
import net.tlalka.android.fiszki.models.entity.WordEntity;
import net.tlalka.android.fwork.FworkInit;

import java.sql.SQLException;
import java.util.List;

public class LessonActivity extends AbstractActivity {

    public static final String LESSON_NAME = "net.tlalka.android.fiszki.lesson.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.lesson.desc";

    private LessonDao lessonDao;
    private WordDao wordDao;

    private List<WordEntity> wordList;
    private WordEntity wordEntity;
    private LessonEntity lessonEntity;

    private TextView textViewTopic;
    private Button buttonWordShow;
    private Button buttonWordCheck;
    private Button buttonGood;
    private Button buttonBad;

    private String lessonName;
    private String lessonDesc;
    private int wordCount;
    private int wordNumber;
    private int wordGood;
    private int wordBad;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_view);

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

        if (FworkInit.Valid.isNotNull(argsBundle)) {
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);

            this.textViewTopic.setText(localFormat("%s - %s", lessonName ,lessonDesc));
        }
    }

    private void initDataBase() {
        try {
            this.lessonDao = DatabaseManager.getHelper(this).getLessonDao();
            this.wordDao = DatabaseManager.getHelper(this).getWordDao();

            this.lessonEntity = this.lessonDao.getLessonByLessonName(lessonName);
            this.wordList = this.wordDao.getWordsByLessonName(lessonName);

            for (WordEntity wordEntity : wordList) {
                Log.d(this.getLocalClassName(), wordEntity.getWordPL() + " " + wordEntity.getProgress());
            }

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    private void runActivity() {
        this.lessonEntity.setProgress(this.lessonEntity.getProgress() + 1);

        this.wordCount = this.wordList.size();
        this.wordNumber = 0;
        this.wordGood = 0;
        this.wordBad = 0;

        this.generateView(this.wordNumber);
    }

    private void generateView(int number) {
        this.wordEntity = this.wordList.get(number);

        this.buttonWordShow.setText(this.wordEntity.getWordEN());
        this.buttonWordCheck.setText(getText(R.string.activity_lesson_show));
    }

    public void showWord() {
        this.buttonWordCheck.setText(this.wordEntity.getWordPL());
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
        this.wordEntity.setProgress(this.wordEntity.getProgress() + 1);
        this.wordDao.save(this.wordEntity);
        this.wordGood++;
    }

    public void progressDown() {
        this.wordEntity.setProgress(this.wordEntity.getProgress() - 1);
        this.wordDao.save(this.wordEntity);
        this.wordBad++;
    }

    public void showOverview() {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putString(OverviewActivity.LESSON_NAME, this.lessonName);
        bundleToSend.putString(OverviewActivity.LESSON_DESC, this.lessonDesc);
        bundleToSend.putInt(OverviewActivity.TOTAL_COUNT, this.wordNumber);
        bundleToSend.putInt(OverviewActivity.TOTAL_GOOD, this.wordGood);
        bundleToSend.putInt(OverviewActivity.TOTAL_BAD, this.wordBad);

        super.startActivity(OverviewActivity.class, bundleToSend);
        super.finish();
    }
}
