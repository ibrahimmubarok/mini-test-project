package com.ibeybeh.moviedbwithfavoritemovie.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeybeh.moviedbwithfavoritemovie.MoviesViewModel;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.db.FavoriteMoviesHelper;
import com.ibeybeh.moviedbwithfavoritemovie.entity.FavoriteMoviesItem;
import com.ibeybeh.moviedbwithfavoritemovie.model.detailmodel.DetailModel;

import java.util.ArrayList;

import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.CONTENT_URI;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.DESCRIPTION;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.GENRE;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.ID;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.IMG_URL;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.POPULARITY;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.POSTER_URL;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.RATING;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.RELEASE_DATE;
import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.TITLE;

public class DetailMoviesActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = DetailMoviesActivity.class.getSimpleName();

    private TextView tvPopularity, tvTitle, tvDescription, tvReleaseDate, tvGenre;
    private ImageView imgPhoto, imgPoster;
    private TextView mReleaseDate, mPopularity, mGenre;
    private CardView cardView;
    private ProgressBar progressBar;
    private ImageButton btnFavMovies;

    private FavoriteMoviesItem favMovies;
    private FavoriteMoviesHelper favMoviesHelper;

    private ArrayList<FavoriteMoviesItem> favMoviesList = new ArrayList<>();
    private ArrayList<DetailModel> detailList = new ArrayList<>();

    private String position;
    private String id, vote;
    private String genre = "";
    private boolean checked;
    private boolean favIcon = false;

    public static String KEY_ID_MOVIES = "keyidmovies";
    public static String KEY_RATING_MOVIES = "keyratingmovies";

    public static final String EXTRA_FAVORITE_MOVIES = "extra_favorite_movies";
    public static final String EXTRA_ID_MOVIES = "extra_position_movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        MoviesViewModel moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getLiveDataDetail().observe(this, getDetailMovies);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(KEY_ID_MOVIES);
        vote = bundle.getString(KEY_RATING_MOVIES);

        tvPopularity = findViewById(R.id.tv_popularity_movies_detail);
        tvTitle = findViewById(R.id.tv_title_movies_detail);
        tvDescription = findViewById(R.id.tv_desc_movies_detail);
        tvReleaseDate = findViewById(R.id.tv_release_date_movies_detail);
        tvGenre = findViewById(R.id.tv_genre_movies_detail);
        imgPhoto = findViewById(R.id.img_photo_movies_detail);
        imgPoster = findViewById(R.id.img_photo_movies_poster_detail);
        progressBar = findViewById(R.id.progress_bar_detail_movies);
        mReleaseDate = findViewById(R.id.textView_release_date_movies);
        mPopularity = findViewById(R.id.textView_popularity_movies);
        mGenre = findViewById(R.id.textView_genre_movies);
        cardView = findViewById(R.id.cardView_movies);
        btnFavMovies = findViewById(R.id.btn_fav_detail_movies);
        btnFavMovies.setOnClickListener(this);

        checked = false;

        favMoviesHelper = new FavoriteMoviesHelper(this);
        favMoviesHelper.open();

        if (new FavoriteMoviesHelper(this).isMovieFavorite(id)){
            btnFavMovies.setImageResource(R.drawable.ic_favorite_black_24dp);
            favIcon = true;
        }

        favMovies = getIntent().getParcelableExtra(EXTRA_FAVORITE_MOVIES);
        if (favMovies != null){
            position = getIntent().getStringExtra("");
            btnFavMovies.setImageResource(R.drawable.ic_favorite_black_24dp);
            favIcon = true;
            checked = true;

            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(favMovies.getTitle());
            }

            tvTitle.setText(favMovies.getTitle());
            tvDescription.setText(favMovies.getDesc());
            tvPopularity.setText(favMovies.getPopularity());
            tvReleaseDate.setText(favMovies.getReleaseDate());
            tvGenre.setText(favMovies.getGenre());

            Glide.with(this)
                    .load(favMovies.getImgUrl())
                    .apply(new RequestOptions().override(380, 420))
                    .into(imgPhoto);

            Glide.with(this)
                    .load(favMovies.getPosterUrl())
                    .apply(new RequestOptions())
                    .into(imgPoster);

            Log.d(TAG, "Ada Data");
        }else{
            favMovies = new FavoriteMoviesItem();
            checked = false;

            moviesViewModel.asyncDetailMovies(id);
            showLoading(true);
        }

        Uri uri = getIntent().getData();

        if (uri != null){
            Cursor cursor = getContentResolver().query(uri,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null){
                if (cursor.moveToFirst()) favMovies = new FavoriteMoviesItem(cursor);
                cursor.close();
            }
        }
    }

    private Observer<ArrayList<DetailModel>> getDetailMovies = new Observer<ArrayList<DetailModel>>() {
        @Override
        public void onChanged(ArrayList<DetailModel> detailModels) {
            if (detailModels != null){

                if (getSupportActionBar() != null){
                    getSupportActionBar().setTitle(detailModels.get(0).getTitle());
                }

                tvPopularity.setText(detailModels.get(0).getPopularity());
                tvTitle.setText(detailModels.get(0).getTitle());
                tvDescription.setText(detailModels.get(0).getOverview());
                tvReleaseDate.setText(detailModels.get(0).getReleaseDate());

                for (int i=0; i<detailModels.get(0).getGenre().size(); i++){
                    genre = genre + detailModels.get(0).getGenre().get(i).getName() + ". ";
                }

                tvGenre.setText(genre);

                Glide.with(getApplicationContext())
                        .load(detailModels.get(0).getPosterPath())
                        .apply(new RequestOptions().override(380,420))
                        .into(imgPhoto);

                Glide.with(getApplicationContext())
                        .load(detailModels.get(0).getCollectionDetail().getBackdropPath())
                        .apply(new RequestOptions().override(380,420))
                        .into(imgPoster);

                detailList.addAll(detailModels);

                showLoading(false);
            }else{
                Toast.makeText(DetailMoviesActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void showLoading(boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
            tvPopularity.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            tvDescription.setVisibility(View.GONE);
            tvReleaseDate.setVisibility(View.GONE);
            tvGenre.setVisibility(View.GONE);
            imgPhoto.setVisibility(View.GONE);
            imgPoster.setVisibility(View.GONE);
            mReleaseDate.setVisibility(View.GONE);
            mPopularity.setVisibility(View.GONE);
            mGenre.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            btnFavMovies.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            tvPopularity.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            tvDescription.setVisibility(View.VISIBLE);
            tvReleaseDate.setVisibility(View.VISIBLE);
            tvGenre.setVisibility(View.VISIBLE);
            imgPhoto.setVisibility(View.VISIBLE);
            imgPoster.setVisibility(View.VISIBLE);
            mReleaseDate.setVisibility(View.VISIBLE);
            mPopularity.setVisibility(View.VISIBLE);
            mGenre.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            btnFavMovies.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_fav_detail_movies){

            boolean isEmpty = false;

            Intent intent = new Intent();
            intent.putExtra(EXTRA_FAVORITE_MOVIES, favMovies);
            intent.putExtra(EXTRA_ID_MOVIES, position);

            if (!isEmpty){

                if (!favIcon){
                    btnFavMovies.setImageResource(R.drawable.ic_favorite_black_24dp);
                    favIcon = true;

                    FavoriteMoviesItem fmi = new FavoriteMoviesItem();

                    fmi.setId(detailList.get(0).getId());
                    fmi.setTitle(detailList.get(0).getTitle());
                    fmi.setDesc(detailList.get(0).getOverview());
                    fmi.setPopularity(detailList.get(0).getPopularity());
                    fmi.setReleaseDate(detailList.get(0).getReleaseDate());
                    fmi.setImgUrl(detailList.get(0).getPosterPath());
                    fmi.setPosterUrl(detailList.get(0).getCollectionDetail().getBackdropPath());
                    fmi.setGenre(genre);
                    fmi.setRating(vote);

                    favMoviesList.add(fmi);

                    ContentValues values = new ContentValues();
                    values.put(ID, favMoviesList.get(0).getId());
                    values.put(TITLE, favMoviesList.get(0).getTitle());
                    values.put(DESCRIPTION, favMoviesList.get(0).getDesc());
                    values.put(POPULARITY, favMoviesList.get(0).getPopularity());
                    values.put(RELEASE_DATE, favMoviesList.get(0).getReleaseDate());
                    values.put(IMG_URL, favMoviesList.get(0).getImgUrl());
                    values.put(POSTER_URL, favMoviesList.get(0).getPosterUrl());
                    values.put(RATING, favMoviesList.get(0).getRating());
                    values.put(GENRE, favMoviesList.get(0).getGenre());

                    getContentResolver().insert(CONTENT_URI, values);

                    Toast.makeText(DetailMoviesActivity.this, "Berhasil Ditambahkan Ke Favorit", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Berhasil Ditambahkan Ke Favorit");
                }else{
                    showAlertDialog();
                    favIcon = false;
                }
            }
        }
    }

    private void showAlertDialog(){
        String dialogTitle = "Hapus Favorite";
        String dialogMessage = "Apakah Anda Yakin Ingin Menghapus Film Dari Favorite?";

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_ID_MOVIES, position);
                        if (checked) {
                            getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + favMovies.getId()),
                                    null,
                                    null);
                        }else{
                            getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + detailList.get(0).getId()),
                                    null,
                                    null);
                        }
                        btnFavMovies.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        Toast.makeText(DetailMoviesActivity.this, "Berhasil Dihapus Dari Favorite", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Berhasil Dihapus Dari Favorit");
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}