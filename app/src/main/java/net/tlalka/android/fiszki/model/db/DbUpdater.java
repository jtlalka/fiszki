package net.tlalka.android.fiszki.model.db;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.tlalka.android.fiszki.model.entities.Cluster;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.helpers.AssetsHelper;

import java.sql.SQLException;

public class DbUpdater {

    private final DbHelper dbHelper;

    private final AssetsHelper assetsHelper;

    protected DbUpdater(DbHelper dbHelper, AssetsHelper assetsHelper) {
        this.dbHelper = dbHelper;
        this.assetsHelper = assetsHelper;
    }

    protected void execute(ConnectionSource connectionSource) {
        try {
            TableUtils.dropTable(connectionSource, Word.class, true);
            TableUtils.dropTable(connectionSource, Cluster.class, true);
            TableUtils.dropTable(connectionSource, Lesson.class, true);

            new DbCreator(dbHelper, assetsHelper).execute(connectionSource);

        } catch (SQLException ex) {
            throw new RuntimeException("Can't update databases", ex);
        }
    }
}
