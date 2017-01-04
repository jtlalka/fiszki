package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;
import net.tlalka.android.fiszki.navigations.Navigator;
import net.tlalka.android.fiszki.services.LessonService;
import net.tlalka.android.fiszki.services.StorageHelper;
import net.tlalka.android.fiszki.utils.ValidUtils;

import javax.inject.Inject;
import java.util.List;

public class LessonActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.text_view_topic)
    protected TextView textViewTopic;

    @BindView(R.id.show_word_button)
    protected Button buttonWordShow;

    @BindView(R.id.check_word_button)
    protected Button buttonWordCheck;

    @Inject
    protected LessonService lessonService;

    @Inject
    protected StorageHelper storageHelper;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

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
        this.translation = this.storageHelper.getEnum(StorageType.TRANSLATION, LanguageType.PL);
    }

    private void runActivity() {
        String lessonName = this.lessonDto.getLessonName();
        String lessonDesc = this.lessonDto.getLessonLevel().name().toLowerCase();

        this.textViewTopic.setText(localFormat("%s - %s", lessonName, lessonDesc));
        this.generateView();
    }

    private void generateView() {
        if (this.lessonService.hasNextWord()) {
            this.buttonWordShow.setText(this.lessonService.getNextWord());
            this.buttonWordCheck.setText(getText(R.string.lesson_activity_check_word));
        } else {
            this.showLessonSummary();
        }
    }

    private void showLessonSummary() {
        this.lessonService.updateLessonProgress();
        this.navigator.openLessonStatActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.check_word_button)
    public void onCheckWordClick(View view) {
        Word word = this.lessonService.getTranslation(this.translation);

        if (ValidUtils.isNotNull(word)) {
            this.buttonWordCheck.setText(word.getValue());
        } else {
            List<LanguageType> languages = this.lessonService.getLanguages();
            languages.remove(this.translation);

            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(languages);
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.translation = languageType;
        this.onCheckWordClick(this.buttonWordCheck);
    }

    @OnClick(R.id.button_good)
    public void onCorrectClick(View view) {
        this.lessonService.correctAnswer();
        this.generateView();
    }

    @OnClick(R.id.button_bad)
    public void onIncorrectClick(View view) {
        this.lessonService.incorrectAnswer();
        this.generateView();
    }
}
