package net.tlalka.android.fiszki.models.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.LevelType;
import net.tlalka.android.fiszki.models.types.OwnerType;

@DatabaseTable(tableName = "lessons", daoClass = LessonDao.class)
public class Lesson {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private LevelType levelType;

    @DatabaseField(canBeNull = false)
    private LanguageType languageType;

    @DatabaseField(canBeNull = false)
    private OwnerType ownerType;

    @DatabaseField
    private int progress;

    @DatabaseField
    private int score;

    public Lesson() {
    }

    public Lesson(String name, LevelType levelType, LanguageType languageType) {
        this.name = name;
        this.levelType = levelType;
        this.languageType = languageType;
        this.ownerType = OwnerType.SYSTEM;
        this.progress = 0;
        this.score = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
