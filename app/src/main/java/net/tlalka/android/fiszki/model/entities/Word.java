package net.tlalka.android.fiszki.model.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.model.dao.WordDao;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.OwnerType;

@DatabaseTable(tableName = "words", daoClass = WordDao.class)
public class Word {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String value;

    @DatabaseField(canBeNull = false, foreign = true)
    private Cluster cluster;

    @DatabaseField(canBeNull = false, foreign = true)
    private Lesson lesson;

    @DatabaseField(canBeNull = false, index = true)
    private LanguageType languageType;

    @DatabaseField(canBeNull = false)
    private OwnerType ownerType;

    @DatabaseField
    private long progress;

    /**
     * Constructor required for ORMLite library.
     */
    public Word() {
    }

    public Word(String value, Cluster cluster, Lesson lesson, LanguageType languageType) {
        this.value = value;
        this.cluster = cluster;
        this.lesson = lesson;
        this.languageType = languageType;
        this.ownerType = OwnerType.SYSTEM;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
