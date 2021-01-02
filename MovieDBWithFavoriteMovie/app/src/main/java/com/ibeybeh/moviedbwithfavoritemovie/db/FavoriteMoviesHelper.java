package com.ibeybeh.moviedbwithfavoritemovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.ID;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.TABLE_FAVORITE_MOVIES;

public class FavoriteMoviesHelper {
    private static final String DATABASE_TABLE_MOVIES = TABLE_FAVORITE_MOVIES;
    private static DatabaseHelper databaseHelper;
    private static FavoriteMoviesHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteMoviesHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteMoviesHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteMoviesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryByIdProviders(String id){
        return database.query(DATABASE_TABLE_MOVIES,
                null,
                ID + " = ?",
                new String[]{id},
                null,
                null,
                null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE_MOVIES,
                null,
                null,
                null,
                null,
                null,
                ID + " ASC");
    }

    public boolean isMovieFavorite(String id){

        open();

        String query = "SELECT * FROM "+ TABLE_FAVORITE_MOVIES+" WHERE id like '"+id+"'";
        boolean isMovieFavorite;

        Cursor row  = database.rawQuery(query, null);
        if (row.moveToFirst() && row.getCount() > 0){
            isMovieFavorite = true;
        } else {
            isMovieFavorite = false;
        }

        close();

        return isMovieFavorite;
    }

    public long insertProviders(ContentValues values){
        return database.insert(DATABASE_TABLE_MOVIES, null, values);
    }

    public int deleteProviders(String id){
        return database.delete(DATABASE_TABLE_MOVIES, ID + " = ?", new String[]{id});
    }
}