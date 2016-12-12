package net.tlalka.android.fiszki.models.db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.tlalka.android.fiszki.models.entities.Cluster;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;

import java.sql.SQLException;

public class DbUpdater {

    private final DbHelper dbHelper;

    protected DbUpdater(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    protected void execute(ConnectionSource connectionSource) {
        try {
            TableUtils.dropTable(connectionSource, Word.class, true);
            TableUtils.dropTable(connectionSource, Cluster.class, true);
            TableUtils.dropTable(connectionSource, Lesson.class, true);

            new DbCreator(dbHelper).execute(connectionSource);

        } catch (SQLException ex) {
            throw new RuntimeException("Can't update databases", ex);
        }
    }
}
