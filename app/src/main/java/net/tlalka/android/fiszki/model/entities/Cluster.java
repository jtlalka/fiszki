package net.tlalka.android.fiszki.model.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.model.dao.ClusterDao;
import net.tlalka.android.fiszki.model.types.OwnerType;

@DatabaseTable(tableName = "clusters", daoClass = ClusterDao.class)
public class Cluster {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private OwnerType ownerType;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Word> words;

    /**
     * Constructor required for ORMLite library.
     */
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

    public ForeignCollection<Word> getWords() {
        return words;
    }
}
