package net.tlalka.android.fiszki.models.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.tlalka.android.fiszki.models.dao.LessonDao;

@DatabaseTable(tableName = "lessons", daoClass = LessonDao.class)
public class LessonEntity {

    public static final int PROGRESS_BASE = 0;
    public static final int STATUS_APP_DEFINED = 0;
    public static final int STATUS_USER_DEFINED = 1;

    @DatabaseField(generatedId = true)
    private long lessonId;

    @DatabaseField
    private String name;

    @DatabaseField
    private String desc;

    @DatabaseField
    private int progress;

    @DatabaseField
    private int status;

    public LessonEntity() {
    }

    public LessonEntity(String name) {
        this.name = name;
        this.desc = "";
        this.progress = PROGRESS_BASE;
        this.status = STATUS_APP_DEFINED;
    }

    public LessonEntity(String name, String desc, int progress, int status) {
        this.name = name;
        this.desc = desc;
        this.progress = progress;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
