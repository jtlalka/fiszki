package net.tlalka.android.fiszki.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.models.entities.Lesson;

public class LessonsListener implements OnClickListener {

    private Context context;
    private Lesson lesson;

    public LessonsListener(Context context, Lesson lesson) {
        this.context = context;
        this.lesson = lesson;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context.getApplicationContext(), LessonActivity.class);
        intent.putExtra(LessonActivity.LESSON_ID, lesson.getId());
        intent.putExtra(LessonActivity.LESSON_NAME, lesson.getName());
        intent.putExtra(LessonActivity.LESSON_DESC, lesson.getLevelType().name());

        context.startActivity(intent);
        ((Activity) (context)).finish();
    }
}
