package net.tlalka.fiszki.model.dto

import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.types.LevelType
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class LessonDto {

    var lessonId: Long = 0
    var lessonName: String = ""
    var lessonLevel: LevelType = LevelType.BEGINNER
    var lessonIndex: Int = 0
    var generalScore: Int = 0
    var correctScore: Int = 0
    var incorrectScore: Int = 0

    @ParcelConstructor
    constructor()

    constructor(lesson: Lesson, index: Int) {
        this.lessonId = lesson.id
        this.lessonName = lesson.name
        this.lessonLevel = lesson.levelType
        this.lessonIndex = index
    }

    fun setScoreValues(generalScore: Int, correctScore: Int, incorrectScore: Int) {
        this.generalScore = generalScore
        this.correctScore = correctScore
        this.incorrectScore = incorrectScore
    }
}
