package net.tlalka.android.fiszki.listeners;

import net.tlalka.android.fiszki.LessonActivity;
import net.tlalka.android.fiszki.LessonsListActivity;
import net.tlalka.android.fiszki.elements.LessonElement;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LessonsListListener implements OnClickListener {

	private Context context;
	private LessonElement lessonElement;

	public LessonsListListener(Context context, LessonElement lessonElement) {
		this.context = context;
		this.lessonElement = lessonElement;
	}

	@Override
	public void onClick(View view) {
		Bundle bundleToSend = new Bundle();
		bundleToSend.putString(LessonActivity.LESSON_NAME, lessonElement.getName());
		bundleToSend.putString(LessonActivity.LESSON_DESC, lessonElement.getDesc());

	    Intent intent = new Intent(context.getApplicationContext(), LessonActivity.class);
	    intent.putExtras(bundleToSend);

	    context.startActivity(intent);
	    ((LessonsListActivity) (context)).finish();
	}
}
