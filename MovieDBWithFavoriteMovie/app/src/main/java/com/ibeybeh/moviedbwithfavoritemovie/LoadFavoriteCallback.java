package com.ibeybeh.moviedbwithfavoritemovie;

import android.database.Cursor;

public interface LoadFavoriteCallback {
    void preExecute();
    void postExecute(Cursor cursor);
}
