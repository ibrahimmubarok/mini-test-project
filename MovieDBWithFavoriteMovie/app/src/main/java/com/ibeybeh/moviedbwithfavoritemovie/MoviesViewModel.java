package com.ibeybeh.moviedbwithfavoritemovie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel.CollectionDetail;
import com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel.DetailModel;
import com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel.GenresModel;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesItem;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesModel;
import com.ibeybeh.moviedbwithfavoritemovie.rest.client.ApiClient;
import com.ibeybeh.moviedbwithfavoritemovie.rest.service.GetMoviesInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {
    private final String TAG = MoviesViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<MoviesItem>> mutableLiveDataPopular = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MoviesItem>> mutableLiveDataComingSoon = new MutableLiveData<>();
    private MutableLiveData<ArrayList<DetailModel>> mutableLiveDataDetail = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MoviesItem>> mutableLiveDataSearch = new MutableLiveData<>();

    public void asyncPopularMovies(){
         GetMoviesInterface getMoviesInterface = ApiClient.getClient().create(GetMoviesInterface.class);

        final ArrayList<MoviesItem> listPopularMovies = new ArrayList<>();

        Call<MoviesModel> call = getMoviesInterface.getMovies();
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()){
                    if ((response.body().getDataMovies() != null)){
                        ArrayList<MoviesItem> moviesItems = response.body().getDataMovies();

                        for (MoviesItem mi : moviesItems){
                            MoviesItem moviesItem = new MoviesItem();

                            moviesItem.setId(mi.getId());
                            if (mi.getBackdropPath() != null) {
                                moviesItem.setBackdropPath("https://image.tmdb.org/t/p/original" + mi.getBackdropPath());
                            }else{
                                moviesItem.setBackdropPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setOverview(mi.getOverview());
                            moviesItem.setPopularity(mi.getPopularity());
                            if (mi.getPosterPath() != null) {
                                moviesItem.setPosterPath("https://image.tmdb.org/t/p/original" + mi.getPosterPath());
                            }else{
                                moviesItem.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setReleaseDate(mi.getReleaseDate());
                            moviesItem.setTitle(mi.getTitle());
                            moviesItem.setVoteAverage(mi.getVoteAverage());

                            listPopularMovies.add(moviesItem);
                        }
                        mutableLiveDataPopular.postValue(listPopularMovies);
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public void asyncComingSoon(){
        GetMoviesInterface getMoviesInterface = ApiClient.getClient().create(GetMoviesInterface.class);

        final ArrayList<MoviesItem> listComingSoonMovies = new ArrayList<>();

        Call<MoviesModel> call = getMoviesInterface.getComingSoon();
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()){
                    if ((response.body().getDataMovies() != null)){
                        ArrayList<MoviesItem> moviesItems = response.body().getDataMovies();

                        for (MoviesItem mi : moviesItems){
                            MoviesItem moviesItem = new MoviesItem();

                            moviesItem.setId(mi.getId());
                            if (mi.getBackdropPath() != null) {
                                moviesItem.setBackdropPath("https://image.tmdb.org/t/p/original" + mi.getBackdropPath());
                            }else{
                                moviesItem.setBackdropPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setOverview(mi.getOverview());
                            moviesItem.setPopularity(mi.getPopularity());
                            if (mi.getPosterPath() != null) {
                                moviesItem.setPosterPath("https://image.tmdb.org/t/p/original" + mi.getPosterPath());
                            }else{
                                moviesItem.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setReleaseDate(mi.getReleaseDate());
                            moviesItem.setTitle(mi.getTitle());
                            moviesItem.setVoteAverage(mi.getVoteAverage());

                            listComingSoonMovies.add(moviesItem);
                        }
                        mutableLiveDataComingSoon.postValue(listComingSoonMovies);
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public void asyncDetailMovies(String id){
        GetMoviesInterface getMoviesInterface = ApiClient.getClient().create(GetMoviesInterface.class);

        final ArrayList<DetailModel> listDetail= new ArrayList<>();

        Call<DetailModel> call = getMoviesInterface.getDetailMovies(id);
        call.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                if (response.isSuccessful()){
                    ArrayList<GenresModel> genreItems = response.body().getGenre();

                    ArrayList<GenresModel> listGenre = new ArrayList<>();

                    CollectionDetail cd = new CollectionDetail();

                    if (response.body().getCollectionDetail() != null) {
                        if (response.body().getCollectionDetail().getPosterPath() != null) {
                            cd.setPosterPath("https://image.tmdb.org/t/p/original" + response.body().getCollectionDetail().getPosterPath());
                        } else {
                            cd.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                        }

                        if (response.body().getCollectionDetail().getBackdropPath() != null){
                            cd.setBackdropPath("https://image.tmdb.org/t/p/original"+response.body().getCollectionDetail().getBackdropPath());
                        }else{
                            cd.setBackdropPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                        }
                    }else{
                        cd.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                        cd.setBackdropPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                    }


                    DetailModel detailModel = new DetailModel();

                    detailModel.setId(response.body().getId());
                    detailModel.setCollectionDetail(cd);
                    detailModel.setOverview(response.body().getOverview());
                    detailModel.setPopularity(response.body().getPopularity());
                    if (response.body().getPosterPath() != null) {
                        detailModel.setPosterPath("https://image.tmdb.org/t/p/original"+response.body().getPosterPath());
                    }else{
                        detailModel.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                    }
                    detailModel.setReleaseDate(response.body().getReleaseDate());
                    detailModel.setTitle(response.body().getTitle());

                    for (GenresModel gm : genreItems){
                        GenresModel genresModel = new GenresModel();

                        genresModel.setName(gm.getName());

                        listGenre.add(genresModel);
                    }

                    detailModel.setGenre(listGenre);

                    listDetail.add(detailModel);

                    mutableLiveDataDetail.postValue(listDetail);

                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public void asyncSearchMovies(String query){
        GetMoviesInterface getMoviesInterface = ApiClient.getClient().create(GetMoviesInterface.class);

        final ArrayList<MoviesItem> listSearchMovies = new ArrayList<>();

        Call<MoviesModel> call = getMoviesInterface.getSearchMovies(query);
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()){
                    if ((response.body().getDataMovies() != null)){
                        ArrayList<MoviesItem> moviesItems = response.body().getDataMovies();

                        for (MoviesItem mi : moviesItems){
                            MoviesItem moviesItem = new MoviesItem();

                            moviesItem.setId(mi.getId());
                            if (mi.getBackdropPath() != null) {
                                moviesItem.setBackdropPath("https://image.tmdb.org/t/p/original" + mi.getBackdropPath());
                            }else{
                                moviesItem.setBackdropPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setOverview(mi.getOverview());
                            moviesItem.setPopularity(mi.getPopularity());
                            if (mi.getPosterPath() != null) {
                                moviesItem.setPosterPath("https://image.tmdb.org/t/p/original" + mi.getPosterPath());
                            }else{
                                moviesItem.setPosterPath("https://upload.wikimedia.org/wikipedia/commons/6/6f/GAMBAR_TIDAK_TERSEDIA.png");
                            }
                            moviesItem.setReleaseDate(mi.getReleaseDate());
                            moviesItem.setTitle(mi.getTitle());
                            moviesItem.setVoteAverage(mi.getVoteAverage());

                            listSearchMovies.add(moviesItem);
                        }
                        mutableLiveDataSearch.postValue(listSearchMovies);
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public LiveData<ArrayList<MoviesItem>> getLiveDataPopular(){
        return mutableLiveDataPopular;
    }

    public LiveData<ArrayList<MoviesItem>> getLiveDataComingSoon(){
        return mutableLiveDataComingSoon;
    }

    public LiveData<ArrayList<DetailModel>> getLiveDataDetail(){
        return mutableLiveDataDetail;
    }

    public LiveData<ArrayList<MoviesItem>> getLiveDataSearch(){
        return mutableLiveDataSearch;
    }
}
