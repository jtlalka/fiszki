package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.utils.ValidUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class LessonActivity extends BasePageActivity {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.lesson.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.lesson.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.lesson.desc";

    private LessonDao lessonDao;
    private WordDao wordDao;

    private List<Word> words;
    private Lesson lesson;
    private Word word;

    private TextView textViewTopic;
    private Button buttonWordShow;
    private Button buttonWordCheck;

    private long lessonId;
    private String lessonName;
    private String lessonDesc;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_activity);

        this.initElements();
        this.initBundle();
        this.initDataBase();

        this.runActivity();
    }

    private void initElements() {
        this.textViewTopic = (TextView) findViewById(R.id.text_view_topic);
        this.buttonWordShow = (Button) findViewById(R.id.button_word_show);
        this.buttonWordCheck = (Button) findViewById(R.id.button_word_check);
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (ValidUtils.isNotNull(argsBundle)) {
            this.lessonId = argsBundle.getLong(LESSON_ID, 0);
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);
        }
    }

    private void initDataBase() {
        try {
            this.lessonDao = super.dbHelper.getLessonDao();
            this.wordDao = super.dbHelper.getWordDao();

            this.lesson = this.lessonDao.getLessonBy(lessonId);
            this.words = this.wordDao.getWordsBy(lesson);

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    private void runActivity() {
        this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.generateView();
    }

    private void generateView() {
        if (words.isEmpty()) {
            this.showLessonSummary();
            return;
        }
        this.word = this.generateWord();
        this.buttonWordShow.setText(this.word.getValue());
        this.buttonWordCheck.setText(getText(R.string.activity_lesson_show));
    }

    private Word generateWord() {
        Word newWord = words.get(new Random().nextInt(words.size()));
        return (words.size() > 1 && newWord == word) ? generateWord() : newWord;
    }

    private void showLessonSummary() {
        this.updateLessonProgress();
        this.startLessonEndActivity();
    }

    private void updateLessonProgress() {
        this.lesson.setProgress(this.lesson.getProgress() + 1);
        this.lessonDao.update(this.lesson);
    }

    private void startLessonEndActivity() {
        Bundle bundle = new Bundle();
        bundle.putLong(LessonStatsActivity.LESSON_ID, this.lessonId);
        bundle.putString(LessonStatsActivity.LESSON_NAME, this.lessonName);
        bundle.putString(LessonStatsActivity.LESSON_DESC, this.lessonDesc);

        super.startActivity(LessonStatsActivity.class, bundle);
        super.finish();
    }

    @XmlOnClick
    public void onCheckWordClick(View view) {
        try {
            Word translation = this.wordDao.getWordBy(word.getCluster(), LanguageType.PL);
            this.buttonWordCheck.setText(translation.getValue());

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    @XmlOnClick
    public void onCorrectClick(View view) {
        this.word.setProgress(this.word.getProgress() + 1);
        this.wordDao.update(this.word);
        this.words.remove(this.word);
        this.generateView();
    }

    @XmlOnClick
    public void onIncorrectClick(View view) {
        this.word.setProgress(this.word.getProgress() - 1);
        this.wordDao.update(this.word);
        this.generateView();
    }
}
