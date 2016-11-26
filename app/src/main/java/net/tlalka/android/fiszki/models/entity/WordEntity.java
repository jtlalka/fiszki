package net.tlalka.android.fiszki.models.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.models.dao.WordDao;

@DatabaseTable(tableName = "words", daoClass = WordDao.class)
public class WordEntity {

    public static final long PROGRESS_BASE = 0;

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String lessonName;

    @DatabaseField
    private String wordEN;

    @DatabaseField
    private String wordPL;

    @DatabaseField
    private long progress;

    public WordEntity() {
    }

    public WordEntity(String lessonName, String wordEN, String wordPL, long progress) {
        this.lessonName = lessonName;
        this.wordEN = wordEN;
        this.wordPL = wordPL;
        this.progress = progress;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getWordEN() {
        return wordEN;
    }

    public void setWordEN(String wordEN) {
        this.wordEN = wordEN;
    }

    public String getWordPL() {
        return wordPL;
    }

    public void setWordPL(String wordPL) {
        this.wordPL = wordPL;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
