package net.tlalka.fiszki.model.entities

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import net.tlalka.fiszki.model.dao.LessonDao
import net.tlalka.fiszki.model.types.LanguageType
import net.tlalka.fiszki.model.types.LevelType
import net.tlalka.fiszki.model.types.OwnerType

@DatabaseTable(tableName = "lessons", daoClass = LessonDao::class)
class Lesson {

    @DatabaseField(generatedId = true)
    var id: Long = 0

    @DatabaseField(canBeNull = false)
    var name: String = ""

    @DatabaseField(canBeNull = false, dataType = DataType.ENUM_INTEGER)
    var levelType: LevelType = LevelType.BEGINNER

    @DatabaseField(canBeNull = false, index = true)
    var languageType: LanguageType = LanguageType.PL

    @DatabaseField(canBeNull = false, dataType = DataType.ENUM_INTEGER)
    var ownerType: OwnerType = OwnerType.SYSTEM

    @DatabaseField
    var progress: Int = 0

    @DatabaseField
    var score: Int = 0

    constructor() {
        // Constructor required for ORMLite library.
    }

    constructor(name: String, levelType: LevelType, languageType: LanguageType) {
        this.name = name
        this.levelType = levelType
        this.languageType = languageType
        this.ownerType = OwnerType.SYSTEM
    }
}
