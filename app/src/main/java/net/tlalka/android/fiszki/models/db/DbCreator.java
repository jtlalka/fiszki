package net.tlalka.android.fiszki.models.db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.tlalka.android.fiszki.models.dto.json.LessonDto;
import net.tlalka.android.fiszki.models.dto.json.WordDto;
import net.tlalka.android.fiszki.models.entities.Cluster;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.OwnerType;
import net.tlalka.android.fiszki.services.AssetsHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbCreator {

    private static final String FILE_PATH = "lessons";

    private final DbHelper dbHelper;

    protected DbCreator(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    protected void execute(ConnectionSource connectionSource) {
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
        final AssetsHelper helper = new AssetsHelper();
        try {
            for (String fileName : helper.getJsonList(FILE_PATH)) {
                insertLessonsData(helper.getJson(fileName, LessonDto.class));
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
        dbHelper.getLessonDao().create(lessonMap.values());
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
            dbHelper.getWordDao().create(words);
        }
    }

    private Cluster insertClusterData() throws SQLException {
        Cluster cluster = new Cluster(OwnerType.SYSTEM);
        dbHelper.getClusterDao().create(cluster);
        return cluster;
    }
}
