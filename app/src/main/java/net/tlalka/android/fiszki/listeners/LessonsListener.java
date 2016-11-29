package net.tlalka.android.fiszki.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.elements.LessonElement;

public class LessonsListener implements OnClickListener {

    private Context context;
    private LessonElement lessonElement;

    public LessonsListener(Context context, LessonElement lessonElement) {
        this.context = context;
        this.lessonElement = lessonElement;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context.getApplicationContext(), LessonActivity.class);
        intent.putExtra(LessonActivity.LESSON_NAME, lessonElement.getName());
        intent.putExtra(LessonActivity.LESSON_DESC, lessonElement.getDesc());

        context.startActivity(intent);
        ((Activity) (context)).finish();
    }
}
