package net.tlalka.android.fiszki.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.tlalka.android.fiszki.models.dao.ClusterDao;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.dto.WordDto;
import net.tlalka.android.fiszki.models.entities.Cluster;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.OwnerType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "fiszki.db";
    public static final int DATABASE_VERSION = 10;

    private final AssetManager assetManager;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.assetManager = context.getAssets();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Lesson.class);
            TableUtils.createTable(connectionSource, Cluster.class);
            TableUtils.createTable(connectionSource, Word.class);

            this.insertApplicationData();
            this.insertConfigurationData();

        } catch (SQLException ex) {
            throw new RuntimeException("Can't create database", ex);
        }
    }

    private void insertApplicationData() throws SQLException {
        // not implemented yet.
    }

    private void insertConfigurationData() throws SQLException {
        final Gson gson = new Gson();
        final String assetPath = "lessons";

        try {
            for (String file : assetManager.list(assetPath)) {
                InputStream stream = assetManager.open(assetPath + File.separator + file);
                Reader reader = new InputStreamReader(stream, "UTF-8");

                insertLessonsData(gson.fromJson(reader, LessonDto.class));
            }
        } catch (IOException ex) {
            throw new RuntimeException("Reading initial json data error.", ex);
        }
    }

    private void insertLessonsData(LessonDto lessonDto) throws SQLException {
        Map<LanguageType, Lesson> lessonMap = new HashMap<>();
        Map<LanguageType, String> lessonNames = lessonDto.getName();

        for (LanguageType languageType : lessonNames.keySet()) {
            String name = lessonNames.get(languageType);
            Lesson lesson = new Lesson(name, lessonDto.getLevel(), languageType);

            lessonMap.put(languageType, lesson);
        }
        this.getLessonDao().create(lessonMap.values());
        insertWordsData(lessonDto.getWords(), lessonMap);
    }

    private void insertWordsData(List<WordDto> wordsDto, Map<LanguageType, Lesson> lessonMap) throws SQLException {
        for (WordDto wordDto : wordsDto) {
            List<Word> words = new ArrayList<>();
            Cluster cluster = insertClusterData();

            for (LanguageType languageType : wordDto.getLanguages()) {
                String value = wordDto.get(languageType);
                Lesson lesson = lessonMap.get(languageType);
                words.add(new Word(value, cluster, lesson, languageType));
            }
            this.getWordDao().create(words);
        }
    }

    private Cluster insertClusterData() throws SQLException {
        Cluster cluster = new Cluster(OwnerType.SYSTEM);
        this.getClusterDao().create(cluster);
        return cluster;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Lesson.class, true);
            TableUtils.dropTable(connectionSource, Cluster.class, true);
            TableUtils.dropTable(connectionSource, Word.class, true);

            this.onCreate(db, connectionSource);

        } catch (SQLException ex) {
            throw new RuntimeException("Can't drop databases", ex);
        }
    }

    public LessonDao getLessonDao() throws SQLException {
        return super.getDao(Lesson.class);
    }

    public ClusterDao getClusterDao() throws SQLException {
        return super.getDao(Cluster.class);
    }

    public WordDao getWordDao() throws SQLException {
        return super.getDao(Word.class);
    }
}
