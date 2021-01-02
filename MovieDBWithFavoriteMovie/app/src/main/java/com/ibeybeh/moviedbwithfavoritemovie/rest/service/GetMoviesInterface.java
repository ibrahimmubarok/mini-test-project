package com.ibeybeh.moviedbwithfavoritemovie.rest.service;

import com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel.DetailModel;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.ibeybeh.moviedbwithfavoritemovie.rest.client.ApiClient.API_KEY;

public interface GetMoviesInterface {
    @GET("3/discover/movie?api_key="+API_KEY+"&language=enUS&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    Call<MoviesModel> getMovies(
    );

    @GET("3/discover/movie?api_key="+API_KEY+"&language=enUS&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&year=2022")
    Call<MoviesModel> getComingSoon(
    );

    @GET("3/search/movie?api_key="+API_KEY+"&language=enUS&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    Call<MoviesModel> getSearchMovies(
            @Query("query") String query
    );

    @GET("3/movie/{movie_id}?api_key=7e6dc9e445f1edd16330cb045b7ba4c0&language=en-US")
    Call<DetailModel> getDetailMovies(
          @Path("movie_id") String id
    );
}
