package net.tlalka.android.fiszki.model.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.model.dao.LessonDao;
import net.tlalka.android.fiszki.model.types.LanguageType;
import net.tlalka.android.fiszki.model.types.LevelType;
import net.tlalka.android.fiszki.model.types.OwnerType;

@DatabaseTable(tableName = "lessons", daoClass = LessonDao.class)
public class Lesson {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.ENUM_INTEGER)
    private LevelType levelType;

    @DatabaseField(canBeNull = false, index = true)
    private LanguageType languageType;

    @DatabaseField(canBeNull = false, dataType = DataType.ENUM_INTEGER)
    private OwnerType ownerType;

    @DatabaseField
    private int progress;

    @DatabaseField
    private int score;

    /**
     * Constructor required for ORMLite library.
     */
    public Lesson() {
    }

    public Lesson(String name, LevelType levelType, LanguageType languageType) {
        this.name = name;
        this.levelType = levelType;
        this.languageType = languageType;
        this.ownerType = OwnerType.SYSTEM;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public OwnerType getOwnerType() {
        return ownerType;
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
