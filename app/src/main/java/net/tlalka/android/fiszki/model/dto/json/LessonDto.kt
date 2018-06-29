package net.tlalka.android.fiszki.model.dto.json

import com.google.gson.annotations.SerializedName
import net.tlalka.android.fiszki.model.types.LevelType

class LessonDto {

    @SerializedName("name")
    var name: WordDto = WordDto()

    @SerializedName("level")
    var level: LevelType = LevelType.BEGINNER

    @SerializedName("words")
    var words: List<WordDto> = ArrayList()
}
