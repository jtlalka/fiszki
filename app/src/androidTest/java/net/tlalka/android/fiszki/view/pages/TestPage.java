package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class TestPage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.test_icon);
        isVisible(R.id.test_topic);
        isVisible(R.id.test_progress);

        isVisible(R.id.test_show_word);
        isVisible(R.id.test_answer_1);
        isVisible(R.id.test_answer_2);
        isVisible(R.id.test_answer_3);
        isVisible(R.id.test_answer_4);
    }

    public void checkWord(String word) {
        isText(R.id.test_show_word, word);
    }

    public void checkProgress(String progress) {
        isText(R.id.test_progress, progress);
    }

    public void closeActivity() {
        clickItem(R.id.item_back);
    }
}
