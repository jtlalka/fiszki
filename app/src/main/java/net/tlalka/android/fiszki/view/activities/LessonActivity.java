package net.tlalka.android.fiszki.view.activities;

import java.util.List;
import javax.inject.Inject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.LessonController;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.view.navigations.Navigator;

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
    protected LessonController lessonController;

    @Inject
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.lesson_activity);
        super.getActivityComponent().inject(this);

        this.runActivity();
    }

    private void runActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.lessonTopic.setText(getString(R.string.lesson_topic, lessonIndex, lessonName));
        this.generateView();
    }

    private void generateView() {
        if (this.lessonController.hasNextWord()) {
            this.lessonProgress.setText(lessonController.getLessonStatus());
            this.lessonShowWord.setText(lessonController.getNextWord());
            this.lessonCheckWord.setText(getText(R.string.lesson_check_word));
        } else {
            this.showLessonSummary();
        }
    }

    private void showLessonSummary() {
        this.lessonController.updateLessonDto(lessonDto);
        this.lessonController.updateLessonProgress();
        this.navigator.openLessonScoreActivity(this, lessonDto);
        this.navigator.finish(this);
    }

    @OnClick(R.id.lesson_check_word)
    public void onCheckWordClick(View view) {
        Word word = this.lessonController.getTranslateWord();

        if (ValidUtils.isNotNull(word)) {
            this.lessonCheckWord.setText(word.getValue());
        } else {
            List<LanguageType> languages = lessonController.getLanguages();
            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(languages);
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.lessonController.setTranslation(languageType);
        this.onCheckWordClick(lessonCheckWord);
    }

    @OnClick(R.id.button_correct)
    public void onCorrectClick(View view) {
        this.lessonController.correctAnswer();
        this.generateView();
    }

    @OnClick(R.id.button_incorrect)
    public void onIncorrectClick(View view) {
        this.lessonController.incorrectAnswer();
        this.generateView();
    }
}
