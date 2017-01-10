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
import java.util.Locale;

public class TestActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.test_topic)
    protected TextView testTopic;

    @BindView(R.id.test_show_word)
    protected Button testWordShow;

    @BindView(R.id.test_check_word)
    protected Button testWordCheck;

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
        super.setContentView(R.layout.test_activity);
        super.getActivityComponent().inject(this);

        this.initStorage();
        this.runActivity();
    }

    private void initStorage() {
        this.language = this.storageService.getLanguage();
        this.translation = this.storageService.getTranslation();
    }

    private void runActivity() {
        String lessonName = this.lessonDto.getLessonName();
        String lessonDesc = this.lessonDto.getLessonLevel().name().toLowerCase(Locale.getDefault());

        this.testTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.generateView();
    }

    private void generateView() {
        if (this.lessonService.hasNextWord()) {
            this.testWordShow.setText(this.lessonService.getNextWord());
            this.testWordCheck.setText(getText(R.string.lesson_activity_check_word));
        } else {
            this.showTestSummary();
        }
    }

    private void showTestSummary() {
        this.lessonService.updateLessonProgress();
        this.navigator.openTestStatActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.test_check_word)
    public void onCheckWordClick(View view) {
        Word word = this.lessonService.getTranslation(this.translation);

        if (ValidUtils.isNotNull(word)) {
            this.testWordCheck.setText(word.getValue());
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
        this.onCheckWordClick(this.testWordCheck);
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
