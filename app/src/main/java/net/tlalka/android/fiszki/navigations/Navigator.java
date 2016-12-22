package net.tlalka.android.fiszki.navigations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import net.tlalka.android.fiszki.activities.LessonActivity;
import net.tlalka.android.fiszki.activities.LessonStatsActivity;
import net.tlalka.android.fiszki.activities.WelcomeActivity;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.dto.WelcomeDto;
import org.parceler.Parcels;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void openWelcomeActivity(Context context, WelcomeDto welcomeDto) {
        Parcelable parcelable = Parcels.wrap(welcomeDto);

        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra(WelcomeDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void openLessonActivity(Context context, LessonDto lessonDto) {
        Parcelable parcelable = Parcels.wrap(lessonDto);

        Intent intent = new Intent(context, LessonActivity.class);
        intent.putExtra(LessonDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void openLessonListActivity(Context context) {
        context.startActivity(new Intent(context, LessonActivity.class));
    }

    public void openLessonStatActivity(Context context, LessonDto lessonDto) {
        Parcelable parcelable = Parcels.wrap(lessonDto);

        Intent intent = new Intent(context, LessonStatsActivity.class);
        intent.putExtra(LessonDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void finish(Activity activity) {
        activity.finish();
    }
}
