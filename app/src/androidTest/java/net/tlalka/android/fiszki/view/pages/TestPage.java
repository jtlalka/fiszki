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

    public String getWord() {
        return getText(R.id.test_show_word);
    }

    public String getAnswer1() {
        return getText(R.id.test_answer_1);
    }

    public String getAnswer2() {
        return getText(R.id.test_answer_2);
    }

    public String getAnswer3() {
        return getText(R.id.test_answer_3);
    }

    public String getAnswer4() {
        return getText(R.id.test_answer_4);
    }

    public void clickAnswer1() {
        clickItem(R.id.test_answer_1);
        isUpdated(R.id.test_answer_1);
    }

    public void clickAnswer2() {
        clickItem(R.id.test_answer_2);
        isUpdated(R.id.test_answer_2);
    }

    public void clickAnswer3() {
        clickItem(R.id.test_answer_3);
        isUpdated(R.id.test_answer_3);
    }

    public void clickAnswer4() {
        clickItem(R.id.test_answer_4);
        isUpdated(R.id.test_answer_4);
    }

    public void matchProgress(int progress) {
        matchText(R.id.test_progress, progress + "\\ .*");
    }

    public void closeActivity() {
        clickItem(R.id.item_back);
    }
}
