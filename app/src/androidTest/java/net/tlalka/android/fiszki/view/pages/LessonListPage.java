package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class LessonListPage extends AbstractPage {

    public static void valid() {
        isVisible(R.id.lesson_icon);
        isVisible(R.id.lesson_topic);
        isVisible(R.id.lesson_progress);
        isVisible(R.id.lesson_list_view);
    }

    public static void openLesson(int index) {
        clickListItem(R.id.lesson_list_view, index);
    }
}
