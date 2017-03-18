package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class LessonListPage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.lesson_icon);
        isVisible(R.id.lesson_topic);
        isVisible(R.id.lesson_progress);
        isVisible(R.id.lesson_list_view);
    }

    public void openItem(int index) {
        clickListItem(R.id.lesson_list_view, index);
    }
}
