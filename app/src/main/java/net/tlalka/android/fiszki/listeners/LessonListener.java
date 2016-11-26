package net.tlalka.android.fiszki.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fiszki.activities.LessonActivity;

public class LessonListener implements OnClickListener {

    public final static int ACTION_CHECK = 0;
    public final static int ACTION_GOOD = 1;
    public final static int ACTION_BAD = 2;

    private LessonActivity activity;
    private int action;

    public LessonListener(LessonActivity activity, int action) {
        this.activity = activity;
        this.action = action;
    }

    @Override
    public void onClick(View view) {
        switch (action) {
            case ACTION_CHECK:
                this.actionCheck();
                break;

            case ACTION_GOOD:
                this.actionGood();
                break;

            case ACTION_BAD:
                this.actionBad();
                break;
        }
    }

    private void actionGood() {
        this.activity.progressUp();
        this.activity.nextWord();
    }

    private void actionBad() {
        this.activity.progressDown();
        this.activity.nextWord();
    }

    private void actionCheck() {
        this.activity.showWord();
    }
}
