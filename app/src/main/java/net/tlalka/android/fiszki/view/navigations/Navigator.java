package net.tlalka.android.fiszki.view.navigations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import net.tlalka.android.fiszki.model.dto.LessonDto;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.view.activities.LessonActivity;
import net.tlalka.android.fiszki.view.activities.LessonListActivity;
import net.tlalka.android.fiszki.view.activities.LessonScoreActivity;
import net.tlalka.android.fiszki.view.activities.TestActivity;
import net.tlalka.android.fiszki.view.activities.TestListActivity;
import net.tlalka.android.fiszki.view.activities.TestScoreActivity;
import net.tlalka.android.fiszki.view.activities.WelcomeActivity;
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
        context.startActivity(new Intent(context, LessonListActivity.class));
    }

    public void openLessonScoreActivity(Context context, LessonDto lessonDto) {
        Parcelable parcelable = Parcels.wrap(lessonDto);

        Intent intent = new Intent(context, LessonScoreActivity.class);
        intent.putExtra(LessonDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void openTestActivity(Context context, LessonDto lessonDto) {
        Parcelable parcelable = Parcels.wrap(lessonDto);

        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(LessonDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void openTestListActivity(Context context) {
        context.startActivity(new Intent(context, TestListActivity.class));
    }

    public void openTestScoreActivity(Context context, LessonDto lessonDto) {
        Parcelable parcelable = Parcels.wrap(lessonDto);

        Intent intent = new Intent(context, TestScoreActivity.class);
        intent.putExtra(LessonDto.class.getName(), parcelable);
        context.startActivity(intent);
    }

    public void finish(Activity activity) {
        activity.finish();
    }
}
