package net.tlalka.android.fiszki.models.dto;

import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.types.LevelType;
import org.parceler.Parcel;

@Parcel
public class LessonDto {

    long lessonId;
    String lessonName;
    LevelType lessonLevel;

    public LessonDto() {
    }

    public LessonDto(Lesson lesson) {
        this.lessonId = lesson.getId();
        this.lessonName = lesson.getName();
        this.lessonLevel = lesson.getLevelType();
    }

    public long getLessonId() {
        return lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public LevelType getLessonLevel() {
        return lessonLevel;
    }
}
