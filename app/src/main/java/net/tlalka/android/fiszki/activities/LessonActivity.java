package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;
import net.tlalka.android.fiszki.navigations.Navigator;
import net.tlalka.android.fiszki.utils.ValidUtils;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LessonActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.text_view_topic)
    protected TextView textViewTopic;

    @BindView(R.id.show_word_button)
    protected Button buttonWordShow;

    @BindView(R.id.check_word_button)
    protected Button buttonWordCheck;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

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
        super.getActivityComponent().inject(this);

        this.initStorage();
        this.initBundle();
        this.initDataBase();

        this.runActivity();
    }

    private void initStorage() {
        this.translation = super.getStorageHelper().getEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    private void initBundle() {
        this.lessonId = lessonDto.getLessonId();
        this.lessonName = lessonDto.getLessonName();
        this.lessonDesc = lessonDto.getLessonLevel().name();
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
        navigator.openLessonStatActivity(this, lessonDto);
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
