package net.tlalka.fiszki.model.entities

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import net.tlalka.fiszki.model.dao.WordDao
import net.tlalka.fiszki.model.types.LanguageType
import net.tlalka.fiszki.model.types.OwnerType

@DatabaseTable(tableName = "words", daoClass = WordDao::class)
class Word {

    @DatabaseField(generatedId = true)
    var id: Long = 0

    @DatabaseField(canBeNull = false)
    var value: String = ""

    @DatabaseField(canBeNull = false, foreign = true)
    var cluster: Cluster = Cluster()

    @DatabaseField(canBeNull = false, foreign = true)
    var lesson: Lesson = Lesson()

    @DatabaseField(canBeNull = false, index = true)
    var languageType: LanguageType = LanguageType.PL

    @DatabaseField(canBeNull = false)
    var ownerType: OwnerType = OwnerType.SYSTEM

    @DatabaseField
    var progress: Long = 0

    constructor() {
        // Constructor required for ORMLite library.
    }

    constructor(value: String, cluster: Cluster, lesson: Lesson, languageType: LanguageType) {
        this.value = value
        this.cluster = cluster
        this.lesson = lesson
        this.languageType = languageType
        this.ownerType = OwnerType.SYSTEM
    }
}
