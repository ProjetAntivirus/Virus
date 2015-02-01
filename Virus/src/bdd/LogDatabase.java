package bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by LEFFY-PC on 02/01/2015.
 */
public class LogDatabase extends SQLiteOpenHelper {

    private static final String TAG = LogDatabase.class.getSimpleName();
    private static final boolean DEBUG = true;

    private static final String DB_NAME = "log.db";
    private static final int DB_VERSION = 1;

    public interface Tables {
        public static final String SYSTEM_CONTENT_PROVIDER = "cp";
        public static final String SYSTEM_BROADCAST_PROVIDER = "bd";

    }

    public LogDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (ConfigApp.DEBUG && DEBUG) {
            android.util.Log.d(TAG, "LogDatabase");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        if (ConfigApp.DEBUG && DEBUG) {
            android.util.Log.d(TAG, "onCreate");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE").append(Tables.SYSTEM_CONTENT_PROVIDER).append(" ( ");
        sb.append(BaseColumns._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(LogContract.systemcpColumns.IDENTIFIER).append(" TEXT, ");
        sb.append(LogContract.systemcpColumns.TIME).append(" TEXT, ");
        sb.append(LogContract.systemcpColumns.DESCRIPTION).append(" TEXT)");

        db.execSQL(sb.toString());
        sb.setLength(0);

        sb.append("CREATE TABLE").append(Tables.SYSTEM_BROADCAST_PROVIDER).append(" ( ");
        sb.append(BaseColumns._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(LogContract.systembdColumns.ACTION).append(" TEXT, ");
        sb.append(LogContract.systembdColumns.TIME).append(" TEXT, ");
        sb.append(LogContract.systembdColumns.DESCRIPTION).append(" TEXT)");

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(ConfigApp.DEBUG && DEBUG)
        {
            android.util.Log.d(TAG, "onUpgrade");
        }
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SYSTEM_CONTENT_PROVIDER);
        onCreate(db);
    }
}
