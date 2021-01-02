package com.ibeybeh.moviedbwithfavoritemovie.helper;

import android.database.Cursor;

import com.ibeybeh.moviedbwithfavoritemovie.entity.FavoriteMoviesItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.DESCRIPTION;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.GENRE;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.ID;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.IMG_URL;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.POPULARITY;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.POSTER_URL;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.RATING;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.RELEASE_DATE;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.TITLE;

public class MappingHelper {

    public static ArrayList<FavoriteMoviesItem> mapCursorToArrayListMovies(Cursor favMoviesCursor){
        ArrayList<FavoriteMoviesItem> favMoviesList = new ArrayList<>();

        while (favMoviesCursor.moveToNext()){
            String id = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(ID));
            String title = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(TITLE));
            String desc = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String imgUrl = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(IMG_URL));
            String posterUrl = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(POSTER_URL));
            String popularity = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(POPULARITY));
            String releaseDate = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(RELEASE_DATE));
            String rating = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(RATING));
            String genre = favMoviesCursor.getString(favMoviesCursor.getColumnIndexOrThrow(GENRE));
            favMoviesList.add(new FavoriteMoviesItem(id, title, desc, imgUrl, posterUrl, popularity, releaseDate, rating, genre));
        }
        return favMoviesList;
    }
}
