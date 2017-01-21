package net.tlalka.android.fiszki.model.dto;

import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.types.LevelType;
import org.parceler.Parcel;

@Parcel
public class LessonDto {

    protected long lessonId;
    protected String lessonName;
    protected LevelType lessonLevel;
    protected int lessonIndex;
    protected int generalScore;
    protected int correctScore;
    protected int incorrectScore;

    public LessonDto() {
    }

    public LessonDto(Lesson lesson, int index) {
        this.lessonId = lesson.getId();
        this.lessonName = lesson.getName();
        this.lessonLevel = lesson.getLevelType();
        this.lessonIndex = index;
    }

    public void setScoreValues(int generalScore, int correctScore, int incorrectScore) {
        this.generalScore = generalScore;
        this.correctScore = correctScore;
        this.incorrectScore = incorrectScore;
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

    public int getLessonIndex() {
        return lessonIndex;
    }

    public int getGeneralScore() {
        return generalScore;
    }

    public int getCorrectScore() {
        return correctScore;
    }

    public int getIncorrectScore() {
        return incorrectScore;
    }
}
