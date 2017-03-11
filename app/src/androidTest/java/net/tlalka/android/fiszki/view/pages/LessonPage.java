package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class LessonPage extends AbstractPage {

    public static void valid() {
        isVisible(R.id.lesson_icon);
        isVisible(R.id.lesson_topic);
        isVisible(R.id.lesson_progress);
        isVisible(R.id.lesson_show_word);
        isVisible(R.id.lesson_check_word);
        isVisible(R.id.button_incorrect);
        isVisible(R.id.button_correct);
    }

    public static void clickCorrect() {
        clickItem(R.id.button_correct, R.string.button_correct);
    }

    public static void clickIncorrect() {
        clickItem(R.id.button_incorrect, R.string.button_incorrect);
    }
}
