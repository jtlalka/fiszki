package net.tlalka.fiszki.model.dto.parcel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LevelType

@Parcelize
data class LessonDto constructor(val lessonId: Long = 0,
                                 val lessonName: String = "",
                                 val lessonLevel: LevelType = LevelType.BEGINNER,
                                 val lessonIndex: Int = 0,
                                 var generalScore: Int = 0,
                                 var correctScore: Int = 0,
                                 var incorrectScore: Int = 0) : Parcelable {

    constructor(lesson: Lesson, index: Int) : this(lesson.id, lesson.name, lesson.levelType, index)

    fun setScoreValues(generalScore: Int, correctScore: Int, incorrectScore: Int) {
        this.generalScore = generalScore
        this.correctScore = correctScore
        this.incorrectScore = incorrectScore
    }
}
