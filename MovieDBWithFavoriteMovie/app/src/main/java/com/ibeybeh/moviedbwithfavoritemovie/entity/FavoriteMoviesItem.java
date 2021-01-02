package com.ibeybeh.moviedbwithfavoritemovie.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract;

import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.getColumnString;


public class FavoriteMoviesItem implements Parcelable {
    private String id;
    private String title;
    private String desc;
    private String imgUrl;
    private String posterUrl;
    private String releaseDate;
    private String popularity;
    private String rating;
    private String genre;

    public FavoriteMoviesItem(){
    }

    public FavoriteMoviesItem(String id, String title, String desc, String imgUrl, String posterUrl, String releaseDate, String popularity, String rating, String genre) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imgUrl = imgUrl;
        this.posterUrl = posterUrl;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.rating = rating;
        this.genre = genre;
    }

    public FavoriteMoviesItem(Cursor cursor){
        this.id = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.TITLE);
        this.desc = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.DESCRIPTION);
        this.imgUrl = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.IMG_URL);
        this.posterUrl = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.POSTER_URL);
        this.releaseDate = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.RELEASE_DATE);
        this.popularity = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.POPULARITY);
        this.rating = getColumnString(cursor, DatabaseContract.FavoriteMoviesColumns.RATING);
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    protected FavoriteMoviesItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        desc = in.readString();
        imgUrl = in.readString();
        posterUrl = in.readString();
        releaseDate = in.readString();
        popularity = in.readString();
        rating = in.readString();
        genre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(imgUrl);
        dest.writeString(posterUrl);
        dest.writeString(releaseDate);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(genre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteMoviesItem> CREATOR = new Creator<FavoriteMoviesItem>() {
        @Override
        public FavoriteMoviesItem createFromParcel(Parcel in) {
            return new FavoriteMoviesItem(in);
        }

        @Override
        public FavoriteMoviesItem[] newArray(int size) {
            return new FavoriteMoviesItem[size];
        }
    };
}