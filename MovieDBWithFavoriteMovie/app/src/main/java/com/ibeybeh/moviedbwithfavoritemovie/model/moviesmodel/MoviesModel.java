package com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesModel {
    @SerializedName("results")
    private ArrayList<MoviesItem> dataMovies;

    public MoviesModel(){
    }

    public ArrayList<MoviesItem> getDataMovies() {
        return dataMovies;
    }

    public void setDataMovies(ArrayList<MoviesItem> dataMovies) {
        this.dataMovies = dataMovies;
    }
}
