package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class LessonPage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.lesson_icon);
        isVisible(R.id.lesson_topic);
        isVisible(R.id.lesson_progress);
        isVisible(R.id.lesson_show_word);
        isVisible(R.id.lesson_check_word);
        isVisible(R.id.button_incorrect);
        isVisible(R.id.button_correct);
    }

    public void clickCorrect() {
        clickItem(R.id.button_correct, R.string.button_correct);
    }

    public void clickIncorrect() {
        clickItem(R.id.button_incorrect, R.string.button_incorrect);
    }

    public void clickTranslation() {
        clickItem(R.id.lesson_check_word);
    }

    public void checkProgress(String progress) {
        isText(R.id.lesson_progress, progress);
    }

    public void closeActivity() {
        clickItem(R.id.item_back);
    }
}
