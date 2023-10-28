package com.example.poketracker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

public class PokeTrackerDBProvider extends ContentProvider {

    public static final String DB_NAME = "PokeTrackerDB";
    public static final String TABLE_NAME = "Pokemon";
    public static final String COLUMN1_NAME = "Number";
    public static final String COLUMN2_NAME = "Name";
    public static final String COLUMN3_NAME = "Species";
    public static final String COLUMN4_NAME = "Gender";
    public static final String COLUMN5_NAME = "Height";
    public static final String COLUMN6_NAME = "Weight";
    public static final String COLUMN7_NAME = "Level";
    public static final String COLUMN8_NAME = "HP";
    public static final String COLUMN9_NAME = "ATK";
    public static final String COLUMN10_NAME = "DEF";
    public static final String AUTHORITY = "com.poketracker.dbprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DB_NAME);
    private static final String CREATE_DB_QUERY = "CREATE TABLE " + TABLE_NAME +
            "( _ID INTEGER PRIMARY KEY," +
            COLUMN1_NAME + " TEXT," +
            COLUMN2_NAME + " TEXT," +
            COLUMN3_NAME + " TEXT," +
            COLUMN4_NAME + " TEXT," +
            COLUMN5_NAME + " TEXT," +
            COLUMN6_NAME + " TEXT," +
            COLUMN7_NAME + " TEXT," +
            COLUMN8_NAME + " TEXT," +
            COLUMN9_NAME + " TEXT," +
            COLUMN10_NAME + " TEXT)"
            ;

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {

        public MainDatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
        }
    }
    private MainDatabaseHelper SQLHelper;

    public PokeTrackerDBProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = SQLHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        SQLHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return SQLHelper.getWritableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
        null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return SQLHelper.getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }
}