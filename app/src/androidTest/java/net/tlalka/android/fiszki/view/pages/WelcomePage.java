package net.tlalka.android.fiszki.view.pages;

import net.tlalka.android.fiszki.R;

public class WelcomePage extends AbstractPage {

    @Override
    public void valid() {
        isVisible(R.id.welcome_layout);
        isVisible(R.id.welcome_message);
    }

    public void clickLayout() {
        clickItem(R.id.welcome_layout);
    }

    public void clickMessage() {
        clickItem(R.id.welcome_message, R.string.welcome_message);
    }
}
