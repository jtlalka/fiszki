package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.TestController;
import net.tlalka.android.fiszki.domain.services.StorageService;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;
import java.util.List;

public class TestActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.test_topic)
    protected TextView testTopic;

    @BindView(R.id.test_show_word)
    protected Button testWordShow;

    @BindView(R.id.test_answer_1)
    protected Button testAnswer1;

    @BindView(R.id.test_answer_2)
    protected Button testAnswer2;

    @BindView(R.id.test_answer_3)
    protected Button testAnswer3;

    @BindView(R.id.test_answer_4)
    protected Button testAnswer4;

    @Inject
    protected TestController testController;

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
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.testTopic.setText(getString(R.string.test_activity_topic, lessonIndex, lessonName));
        this.generateView();
    }

    private void generateView() {
        if (this.testController.hasNextWord()) {
            this.testWordShow.setText(this.testController.getNextWord());
            this.generateAnswers();
        } else {
            this.showTestSummary();
        }
    }

    public void generateAnswers() {
        List<Word> answers = this.testController.getAnswers(this.translation);

        if (ValidUtils.isNotEmpty(answers)) {
            this.testAnswer1.setText(answers.get(0).getValue());
            this.testAnswer2.setText(answers.get(1).getValue());
            this.testAnswer3.setText(answers.get(2).getValue());
            this.testAnswer4.setText(answers.get(3).getValue());
        } else {
            List<LanguageType> languages = this.testController.getLanguages();
            languages.remove(this.language);
            languages.remove(this.translation);

            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(languages);
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    private void showTestSummary() {
        this.testController.updateTestScore();
        this.navigator.openTestStatActivity(this, this.lessonDto);
        this.navigator.finish(this);
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.translation = languageType;
        this.generateAnswers();
    }

    @OnClick(R.id.test_answer_1)
    public void onAnswer1Click(View view) {
        this.validAnswer(0);
    }

    @OnClick(R.id.test_answer_2)
    public void onAnswer2Click(View view) {
        this.validAnswer(1);
    }

    @OnClick(R.id.test_answer_3)
    public void onAnswer3Click(View view) {
        this.validAnswer(2);
    }

    @OnClick(R.id.test_answer_4)
    public void onAnswer4Click(View view) {
        this.validAnswer(3);
    }

    private void validAnswer(int id) {
        super.alert("Nat implemented yet.");

        if (this.testController.validAnswer(id)) {
            this.testController.correctAnswer();
        } else {
            this.testController.incorrectAnswer();
        }
    }
}
