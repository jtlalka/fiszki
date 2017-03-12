package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class MainPage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.main_list_view);
        isImageDescription(R.string.app_name);
    }

    public void openLessons() {
        clickListItem(R.id.main_list_view, 0);
    }

    public void openTests() {
        clickListItem(R.id.main_list_view, 1);
    }

    public void openWords() {
        clickListItem(R.id.main_list_view, 2);
    }

    public void openHelp() {
        clickListItem(R.id.main_list_view, 3);
    }
}
