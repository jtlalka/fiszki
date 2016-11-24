package net.tlalka.android.fiszki.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import net.tlalka.android.fiszki.LessonSumActivity;

public class LessonSumListener implements OnClickListener {

    public final static int ACTION_GOTO_LESSON = 1;
    public final static int ACTION_GOTO_LESSONS_LIST = 2;

    private LessonSumActivity context;
    private int action;

    public LessonSumListener(LessonSumActivity context, int action) {
        this.context = context;
        this.action = action;
    }

    @Override
    public void onClick(View view) {
        switch (action) {
            case ACTION_GOTO_LESSON:
                this.actionGotoLesson();
                break;

            case ACTION_GOTO_LESSONS_LIST:
                this.actionGotoLessonsList();
                break;

            default:
                Toast.makeText(this.context, "Not implement", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void actionGotoLesson() {
        this.context.gotoLesson();
    }

    private void actionGotoLessonsList() {
        this.context.gotoLessonsList();
    }
}
