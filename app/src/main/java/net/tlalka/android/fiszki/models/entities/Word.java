package net.tlalka.android.fiszki.models.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.OwnerType;

@DatabaseTable(tableName = "words", daoClass = WordDao.class)
public class Word {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String value;

    @DatabaseField(canBeNull = false)
    private long clusterId;

    @DatabaseField(canBeNull = false)
    private long lessonId;

    @DatabaseField(canBeNull = false)
    private LanguageType languageType;

    @DatabaseField(canBeNull = false)
    private OwnerType ownerType;

    @DatabaseField
    private long progress;

    public Word() {
    }

    public Word(String value, long clusterId, long lessonId, LanguageType languageType) {
        this.value = value;
        this.clusterId = clusterId;
        this.lessonId = lessonId;
        this.languageType = languageType;
        this.ownerType = OwnerType.SYSTEM;
        this.progress = 0;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getClusterId() {
        return clusterId;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
