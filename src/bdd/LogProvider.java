package bdd;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by LEFFY-PC on 04/01/2015.
 */
public class LogProvider extends ContentProvider {

    private LogDatabase mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int SYSTEM_CP = 100;
    private static final int SYSTEM_CP_BASEID = 101;

    private static final int SYSTEM_BD = 200;
    private static final int SYSTEM_BD_BASEID = 201;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = LogContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, LogContract.PATH_SYSTEM_CP, SYSTEM_CP);
        matcher.addURI(authority, LogContract.PATH_SYSTEM_CP + "/" +LogContract.SystemCp.PATH_BASEID + "/#", SYSTEM_CP_BASEID);

        matcher.addURI(authority, LogContract.PATH_SYSTEM_BD, SYSTEM_BD);
        matcher.addURI(authority, LogContract.PATH_SYSTEM_BD + "/" + LogContract.SystemBd.PATH_BASEID + "/#", SYSTEM_BD_BASEID);

        return matcher;
    }

    @Override
    public boolean onCreate()
    {
        mOpenHelper = new LogDatabase(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SYSTEM_CP:
            case SYSTEM_CP_BASEID:
                return LogContract.SystemCp.CONTENT_ITEM_TYPE;
            case SYSTEM_BD:
            case SYSTEM_BD_BASEID:
                return LogContract.SystemBd.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (ConfigApp.DEBUG) {
            android.util.Log.i("", "insert" + uri.toString());
        }

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SYSTEM_CP: {
                long _id = db.insertOrThrow(
                        LogDatabase.Tables.SYSTEM_CONTENT_PROVIDER, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return LogContract.SystemCp.buildBaseIdUri(_id);
            }

            case SYSTEM_BD: {
                long _id = db.insertOrThrow(
                        LogDatabase.Tables.SYSTEM_BROADCAST_PROVIDER, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return LogContract.SystemCp.buildBaseIdUri(_id);
            }

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (ConfigApp.DEBUG) {
            android.util.Log.i("", "delete  " + uri.toString());
        }

        int count = 0;
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        final LogBuilder builder = buildSimpleSelection(uri);
        count = builder.where(selection, selectionArgs).delete(db);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (ConfigApp.DEBUG) {
            android.util.Log.i("", "query " + uri.toString());
        }

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        LogBuilder builder = null;
        Cursor c = null;
        builder = buildSimpleSelection(uri);
        builder.where(selection, selectionArgs);
        c = builder.query(db, projection, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    private LogBuilder buildSimpleSelection(Uri uri) {
        final LogBuilder builder = new LogBuilder();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SYSTEM_CP:
                builder.table(LogDatabase.Tables.SYSTEM_CONTENT_PROVIDER);
                break;
            case SYSTEM_CP_BASEID:
                builder.table(LogDatabase.Tables.SYSTEM_CONTENT_PROVIDER).where(
                        BaseColumns._ID + "=?",
                        LogContract.SystemCp.getBaseIdUri(uri));
                break;

            case SYSTEM_BD:
                builder.table(LogDatabase.Tables.SYSTEM_BROADCAST_PROVIDER);
                break;
            case SYSTEM_BD_BASEID:
                builder.table(LogDatabase.Tables.SYSTEM_BROADCAST_PROVIDER).where(
                        BaseColumns._ID + "=?",
                        LogContract.SystemCp.getBaseIdUri(uri));
                break;

        }
        return builder;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (ConfigApp.DEBUG) {
            android.util.Log.i("", "update " + uri.toString());
        }
        int count = 0;
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final LogBuilder builder = buildSimpleSelection(uri);
        count = builder.where(selection, selectionArgs).update(db, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}
