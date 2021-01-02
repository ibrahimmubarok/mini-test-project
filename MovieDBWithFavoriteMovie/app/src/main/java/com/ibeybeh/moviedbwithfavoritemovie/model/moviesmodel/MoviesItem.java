package com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MoviesItem implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("overview")
    private String overview;

    public MoviesItem(){
    }

    protected MoviesItem(Parcel in) {
        id = in.readString();
        voteAverage = in.readString();
        popularity = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
    }

    public static final Creator<MoviesItem> CREATOR = new Creator<MoviesItem>() {
        @Override
        public MoviesItem createFromParcel(Parcel in) {
            return new MoviesItem(in);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(voteAverage);
        parcel.writeString(popularity);
        parcel.writeString(title);
        parcel.writeString(backdropPath);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
    }
}
