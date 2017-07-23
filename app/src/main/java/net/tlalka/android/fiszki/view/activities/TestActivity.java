package net.tlalka.android.fiszki.view.activities;

import java.util.List;
import javax.inject.Inject;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.TestController;
import net.tlalka.android.fiszki.domain.utils.ValidUtils;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.view.fragments.LanguageDialogFragment;
import net.tlalka.android.fiszki.view.navigations.Navigator;

public class TestActivity extends BasePageActivity implements LanguageDialogFragment.DialogListener {

    @BindView(R.id.test_topic)
    protected TextView testTopic;

    @BindView(R.id.test_progress)
    protected TextView testProgress;

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
    protected Navigator navigator;

    @Inject
    protected LessonDto lessonDto;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.test_activity);
        super.getActivityComponent().inject(this);

        this.runActivity();
    }

    private void runActivity() {
        int lessonIndex = this.lessonDto.getLessonIndex();
        String lessonName = this.lessonDto.getLessonName();

        this.testTopic.setText(getString(R.string.test_topic, lessonIndex, lessonName));
        this.generateView();
    }

    private void generateView() {
        if (this.testController.hasNextWord()) {
            int index = this.testController.getWordIndex();
            int total = this.testController.getTestSize();

            this.testProgress.setText(getString(R.string.test_progress, index + 1, total));
            this.testWordShow.setText(this.testController.getNextWord());
            this.generateAnswers();
        } else {
            this.showTestSummary();
        }
    }

    private void generateAnswers() {
        List<String> answers = testController.getAnswers();

        if (ValidUtils.isNotEmpty(answers)) {
            this.testAnswer1.setText(answers.get(0));
            this.testAnswer2.setText(answers.get(1));
            this.testAnswer3.setText(answers.get(2));
            this.testAnswer4.setText(answers.get(3));
        } else {
            List<LanguageType> languages = testController.getLanguages();
            LanguageDialogFragment dialog = LanguageDialogFragment.getInstance(languages);
            dialog.show(getFragmentManager(), LanguageDialogFragment.class.getName());
        }
    }

    private void showTestSummary() {
        this.testController.updateBestScore();
        this.testController.updateLessonDto(lessonDto);
        this.navigator.openTestScoreActivity(this, lessonDto);
        this.navigator.finish(this);
    }

    @Override
    public void onLanguageSelected(LanguageType languageType) {
        this.testController.setTranslation(languageType);
        this.generateAnswers();
    }

    @OnClick(R.id.test_answer_1)
    public void onAnswer1Click(View view) {
        validAnswer(testAnswer1, 0);
    }

    @OnClick(R.id.test_answer_2)
    public void onAnswer2Click(View view) {
        validAnswer(testAnswer2, 1);
    }

    @OnClick(R.id.test_answer_3)
    public void onAnswer3Click(View view) {
        validAnswer(testAnswer3, 2);
    }

    @OnClick(R.id.test_answer_4)
    public void onAnswer4Click(View view) {
        validAnswer(testAnswer4, 3);
    }

    private void validAnswer(Button button, int index) {
        if (testController.validAnswer(index)) {
            button.setBackgroundResource(R.drawable.patch_green);
            delayedNextView();
        } else {
            button.setBackgroundResource(R.drawable.patch_red);
        }
    }

    private void delayedNextView() {
        new Handler().postDelayed(() -> {
            this.testAnswer1.setBackgroundResource(R.drawable.patch_cyan);
            this.testAnswer2.setBackgroundResource(R.drawable.patch_cyan);
            this.testAnswer3.setBackgroundResource(R.drawable.patch_cyan);
            this.testAnswer4.setBackgroundResource(R.drawable.patch_cyan);
            this.generateView();
        }, 500);
    }
}
