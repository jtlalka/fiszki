package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;
import net.tlalka.android.fiszki.utils.ValidUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LessonActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    public static final String LESSON_ID = "net.tlalka.android.fiszki.lesson.id";
    public static final String LESSON_NAME = "net.tlalka.android.fiszki.lesson.name";
    public static final String LESSON_DESC = "net.tlalka.android.fiszki.lesson.desc";

    @BindView(R.id.text_view_topic)
    protected TextView textViewTopic;

    @BindView(R.id.show_word_button)
    protected Button buttonWordShow;

    @BindView(R.id.check_word_button)
    protected Button buttonWordCheck;

    private LessonDao lessonDao;
    private WordDao wordDao;

    private List<Word> words;
    private Lesson lesson;
    private Word word;
    private LanguageType translation;

    private long lessonId;
    private String lessonName;
    private String lessonDesc;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_activity);
        ButterKnife.bind(this);

        this.initStorage();
        this.initBundle();
        this.initDataBase();

        this.runActivity();
    }

    private void initStorage() {
        this.translation = super.getStorageHelper().getEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    private void initBundle() {
        Bundle argsBundle = super.getIntent().getExtras();

        if (ValidUtils.isNotNull(argsBundle)) {
            this.lessonId = argsBundle.getLong(LESSON_ID);
            this.lessonName = argsBundle.getString(LESSON_NAME);
            this.lessonDesc = argsBundle.getString(LESSON_DESC);
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
        this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.generateView();
    }

    private void generateView() {
        if (words.isEmpty()) {
            this.showLessonSummary();
        } else {
            this.word = this.generateWord();
            this.buttonWordShow.setText(this.word.getValue());
            this.buttonWordCheck.setText(getText(R.string.lesson_activity_check_word));
        }
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

    @OnClick(R.id.check_word_button)
    public void onCheckWordClick(View view) {
        try {
            this.setTextForCheckButton(this.wordDao.getWordBy(word.getCluster(), this.translation));
        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
        }
    }

    private void setTextForCheckButton(Word word) {
        if (ValidUtils.isNotNull(word)) {
            this.buttonWordCheck.setText(word.getValue());
        } else {
            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(getLanguages());
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    private List<LanguageType> getLanguages() {
        try {
            return Stream.of(this.wordDao.getWordsBy(this.word.getCluster()))
                    .filter(word -> this.translation != word.getLanguageType())
                    .map(Word::getLanguageType)
                    .collect(Collectors.toList());

        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.translation = languageType;
    }

    @OnClick(R.id.button_good)
    public void onCorrectClick(View view) {
        this.word.setProgress(this.word.getProgress() + 1);
        this.wordDao.update(this.word);
        this.words.remove(this.word);
        this.generateView();
    }

    @OnClick(R.id.button_bad)
    public void onIncorrectClick(View view) {
        this.word.setProgress(this.word.getProgress() - 1);
        this.wordDao.update(this.word);
        this.generateView();
    }
}
