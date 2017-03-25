package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class TestScorePage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.test_icon);
        isVisible(R.id.test_topic);
        isVisible(R.id.test_score_total);
        isVisible(R.id.test_score_value);
        isVisible(R.id.test_score_incorrect);
        isVisible(R.id.test_score_repeat, R.string.test_score_repeat);
        isVisible(R.id.test_score_tests, R.string.test_score_tests);
    }

    public void clickRepeat() {
        clickItem(R.id.test_score_repeat, R.string.test_score_repeat);
    }

    public void clickTests() {
        clickItem(R.id.test_score_tests, R.string.test_score_tests);
    }
}
