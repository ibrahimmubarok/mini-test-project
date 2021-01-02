package com.ibeybeh.moviedbwithfavoritemovie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbfavoritemovies";

    private static final int DATABASE_VERSION = 8;

    private static final String SQL_CREATE_TABLE_FAV_MOVIES = String.format("CREATE TABLE %s"
            + "(%s STRING PRIMARY KEY,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL,"
            + "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORITE_MOVIES,
            DatabaseContract.FavoriteMoviesColumns.ID,
            DatabaseContract.FavoriteMoviesColumns.TITLE,
            DatabaseContract.FavoriteMoviesColumns.DESCRIPTION,
            DatabaseContract.FavoriteMoviesColumns.IMG_URL,
            DatabaseContract.FavoriteMoviesColumns.POSTER_URL,
            DatabaseContract.FavoriteMoviesColumns.RELEASE_DATE,
            DatabaseContract.FavoriteMoviesColumns.POPULARITY,
            DatabaseContract.FavoriteMoviesColumns.RATING,
            DatabaseContract.FavoriteMoviesColumns.GENRE
    );

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAV_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_MOVIES);
        onCreate(sqLiteDatabase);
    }
}