package com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

public class GenresModel {
    @SerializedName("name")
    private String name;

    public GenresModel(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
