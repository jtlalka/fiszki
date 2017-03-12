package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class LessonScorePage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.lesson_icon);
        isVisible(R.id.lesson_topic);
        isVisible(R.id.lesson_score_total);
        isVisible(R.id.lesson_score_incorrect);
        isVisible(R.id.lesson_score_repeat, R.string.lesson_score_repeat);
        isVisible(R.id.lesson_score_lessons, R.string.lesson_score_lessons);
    }

    public void clickLessons() {
        clickListItem(R.id.lesson_score_lessons, R.string.lesson_score_lessons);
    }

    public void clickRepeat() {
        clickListItem(R.id.lesson_score_repeat, R.string.lesson_score_repeat);
    }
}
