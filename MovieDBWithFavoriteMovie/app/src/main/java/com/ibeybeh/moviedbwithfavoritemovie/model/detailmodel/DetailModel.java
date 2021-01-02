package com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailModel {
    @SerializedName("belongs_to_collection")
    private CollectionDetail collectionDetail;
    @SerializedName("genres")
    private ArrayList<GenresModel> genre;
    @SerializedName("id")
    private String id;
    @SerializedName("original_title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;

    public DetailModel() {
    }

    public CollectionDetail getCollectionDetail() {
        return collectionDetail;
    }

    public void setCollectionDetail(CollectionDetail collectionDetail) {
        this.collectionDetail = collectionDetail;
    }

    public ArrayList<GenresModel> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<GenresModel> genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
