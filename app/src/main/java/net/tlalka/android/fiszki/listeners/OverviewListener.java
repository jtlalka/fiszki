package net.tlalka.android.fiszki.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fiszki.activities.OverviewActivity;

public class OverviewListener implements OnClickListener {

    public final static int ACTION_GOTO_LESSON = 0x000001;
    public final static int ACTION_GOTO_LESSON_LIST = 0x000002;

    private OverviewActivity activity;
    private int action;

    public OverviewListener(OverviewActivity activity, int action) {
        this.activity = activity;
        this.action = action;
    }

    @Override
    public void onClick(View view) {
        switch (action) {
            case ACTION_GOTO_LESSON:
                this.actionGotoLesson();
                break;

            case ACTION_GOTO_LESSON_LIST:
                this.actionGotoLessonsList();
                break;
        }
    }

    private void actionGotoLesson() {
        this.activity.gotoLesson();
    }

    private void actionGotoLessonsList() {
        this.activity.gotoLessonList();
    }
}
