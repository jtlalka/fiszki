package net.tlalka.android.fiszki.model.entities

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable
import net.tlalka.android.fiszki.model.dao.ClusterDao
import net.tlalka.android.fiszki.model.types.OwnerType

@DatabaseTable(tableName = "clusters", daoClass = ClusterDao::class)
class Cluster {

    @DatabaseField(generatedId = true)
    var id: Long = 0

    @DatabaseField(canBeNull = false)
    var ownerType: OwnerType = OwnerType.SYSTEM

    @ForeignCollectionField(eager = true)
    var words: ForeignCollection<Word>? = null

    constructor() {
        // Constructor required for ORMLite library.
    }

    constructor(ownerType: OwnerType) {
        this.ownerType = ownerType
    }
}
