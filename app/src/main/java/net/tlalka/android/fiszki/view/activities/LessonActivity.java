package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.services.LessonService;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;
import java.util.List;

public class LessonActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.lesson_topic)
    protected TextView lessonTopic;

    @BindView(R.id.lesson_progress)
    protected TextView lessonProgress;

    @BindView(R.id.lesson_show_word)
    protected Button lessonShowWord;

    @BindView(R.id.lesson_check_word)
    protected Button lessonCheckWord;

    @Inject
    protected LessonService lessonService;

    @Inject
    protected StorageService storageService;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    private LanguageType language;
    private LanguageType translation;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_activity);
        super.getActivityComponent().inject(this);

        this.initStorage();
        this.runActivity();
    }

    private void initStorage() {
        this.language = this.storageService.getLanguage();
        this.translation = this.storageService.getTranslation();
    }

    private void runActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.lessonTopic.setText(getString(R.string.lesson_activity_topic, lessonIndex, lessonName));
        this.generateView();
    }

    private void generateView() {
        if (this.lessonService.hasNextWord()) {
            this.lessonProgress.setText(this.lessonService.getLessonStatus());
            this.lessonShowWord.setText(this.lessonService.getNextWord());
            this.lessonCheckWord.setText(getText(R.string.lesson_activity_check_word));
        } else {
            this.showLessonSummary();
        }
    }

    private void showLessonSummary() {
        this.lessonService.updateLessonProgress();
        this.navigator.openLessonStatActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.lesson_check_word)
    public void onCheckWordClick(View view) {
        Word word = this.lessonService.getTranslation(this.translation);

        if (ValidUtils.isNotNull(word)) {
            this.lessonCheckWord.setText(word.getValue());
        } else {
            List<LanguageType> languages = this.lessonService.getLanguages();
            languages.remove(this.language);
            languages.remove(this.translation);

            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(languages);
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.translation = languageType;
        this.onCheckWordClick(this.lessonCheckWord);
    }

    @OnClick(R.id.button_correct)
    public void onCorrectClick(View view) {
        this.lessonService.correctAnswer();
        this.generateView();
    }

    @OnClick(R.id.button_incorrect)
    public void onIncorrectClick(View view) {
        this.lessonService.incorrectAnswer();
        this.generateView();
    }
}
