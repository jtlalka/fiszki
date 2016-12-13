package net.tlalka.android.fiszki.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.models.entities.Lesson;

public class LessonsListener implements AdapterView.OnItemClickListener {

    private Activity activity;

    public LessonsListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Lesson lesson = (Lesson) parent.getItemAtPosition(position);

        Intent intent = new Intent(activity, LessonActivity.class);
        intent.putExtra(LessonActivity.LESSON_ID, lesson.getId());
        intent.putExtra(LessonActivity.LESSON_NAME, lesson.getName());
        intent.putExtra(LessonActivity.LESSON_DESC, lesson.getLevelType().name());

        activity.startActivity(intent);
        activity.finish();
    }
}
