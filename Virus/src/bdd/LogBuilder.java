package bdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LEFFY-PC on 02/01/2015.
 */
public class LogBuilder {

    private String mTable = null;
    private Map<String, String> mProjectionMap = new HashMap<String, String>();
    private StringBuilder mSelection = new StringBuilder();
    private ArrayList<String> mSelectionArgs = new ArrayList<String>();

    public LogBuilder reset()
    {
        mTable = null;
        mSelection.setLength(0);
        mSelectionArgs.clear();
        return this;
    }
    public LogBuilder where(String selection, String... selectionArgs)
    {
        if(TextUtils.isEmpty(selection))
        {
            if(selectionArgs != null && selectionArgs.length > 0)
            {
                throw new IllegalArgumentException("Mauvaise sélection des arguments.");
            }
            return this;
        }
        if(mSelection.length() > 0)
        {
            mSelection.append(" AND ");
        }

        mSelection.append("(").append(selection).append(")");
        if(selectionArgs != null)
        {
            for(String arg : selectionArgs)
            {
                mSelectionArgs.add(arg);
            }
        }
        return this;
    }

    public LogBuilder table(String table)
    {
        mTable = table;
        return this;
    }

    private void assertTable()
    {
        if(mTable == null)
        {
            throw new IllegalStateException("Table not specified");
        }
    }

    public LogBuilder mapToTable(String column, String table)
    {
        mProjectionMap.put(column, table + "." + column);
        return this;
    }

    public LogBuilder map(String fromColumn, String toClause)
    {
        mProjectionMap.put(fromColumn, toClause + " AS " + fromColumn);
        return this;
    }

    public String getSelection()
    {
        return mSelection.toString();
    }

    public String[] getSelectionArgs()
    {
        return mSelectionArgs.toArray(new String[mSelectionArgs.size()]);
    }

    private void mapColumns(String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            final String target = mProjectionMap.get(columns[i]);
            if (target != null) {
                columns[i] = target;
            }
        }
    }

    @Override
    public String toString() {
        return "LogBuilder[table=" + mTable + ", selection=" + getSelection()
                + ", selectionArgs=" + Arrays.toString(getSelectionArgs())
                + "]";
    }


    public Cursor query(SQLiteDatabase db, String[] columns, String orderBy) {
        return query(db, columns, null, null, orderBy, null);
    }

    public Cursor query(SQLiteDatabase db, String[] columns, String groupBy,
                        String having, String orderBy, String limit) {
        assertTable();
        if (columns != null) {
            mapColumns(columns);
        }

        return db.query(mTable, columns, getSelection(), getSelectionArgs(),
                groupBy, having, orderBy, limit);
    }

    public int update(SQLiteDatabase db, ContentValues values) {
        assertTable();
        return db.update(mTable, values, getSelection(), getSelectionArgs());
    }

    public int delete(SQLiteDatabase db) {
        assertTable();

        return db.delete(mTable, getSelection(), getSelectionArgs());
    }
}

