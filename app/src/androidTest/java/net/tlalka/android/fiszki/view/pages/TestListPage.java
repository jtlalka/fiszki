package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class TestListPage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.test_icon);
        isVisible(R.id.test_topic);
        isVisible(R.id.test_progress);
        isVisible(R.id.test_list_view);
    }

    public void openItem(int index) {
        clickListItem(R.id.test_list_view, index);
    }
}
