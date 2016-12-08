package net.tlalka.android.fiszki.models.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.models.dao.ClusterDao;
import net.tlalka.android.fiszki.models.types.OwnerType;

@DatabaseTable(tableName = "clusters", daoClass = ClusterDao.class)
public class Cluster {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private OwnerType ownerType;

    public Cluster() {
    }

    public Cluster(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public long getId() {
        return this.id;
    }

    public OwnerType getOwnerType() {
        return this.ownerType;
    }
}
