package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class MainPage extends AbstractPage {

    public static void valid() {
        isVisible(R.id.main_list_view);
    }

    public static void openLessons() {
        clickListItem(R.id.main_list_view, 0);
    }

    public static void openTests() {
        clickListItem(R.id.main_list_view, 1);
    }

    public static void openWords() {
        clickListItem(R.id.main_list_view, 2);
    }

    public static void openHelp() {
        clickListItem(R.id.main_list_view, 3);
    }
}
