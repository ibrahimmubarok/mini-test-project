package com.ibeybeh.moviedbwithfavoritemovie.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ibeybeh.moviedbwithfavoritemovie.LoadFavoriteCallback;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.adapter.FavoriteMoviesAdapter;
import com.ibeybeh.moviedbwithfavoritemovie.entity.FavoriteMoviesItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.CONTENT_URI;
import static com.ibeybeh.moviedbwithfavoritemovie.helper.MappingHelper.mapCursorToArrayListMovies;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements LoadFavoriteCallback {

    private ProgressBar progressBar;

    private FavoriteMoviesAdapter favMoviesAdapter;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvFavorite = view.findViewById(R.id.rv_fav_movies);
        progressBar = view.findViewById(R.id.progress_bar_fav_movies);

        favMoviesAdapter = new FavoriteMoviesAdapter(getContext());

        rvFavorite.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        rvFavorite.setAdapter(favMoviesAdapter);
        rvFavorite.setHasFixedSize(true);

        if (savedInstanceState == null){
            new LoadFavMoviesAsync(getContext(), this).execute();
        }else{
            ArrayList<FavoriteMoviesItem> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null){
                favMoviesAdapter.setListFavMovies(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(EXTRA_STATE, favMoviesAdapter.getListFavMovies());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void preExecute() {
        showLoading(true);
    }

    @Override
    public void postExecute(Cursor favMovies) {
        showLoading(false);

        ArrayList<FavoriteMoviesItem> listFavMovies = mapCursorToArrayListMovies(favMovies);
        if (listFavMovies.size() > 0){
            favMoviesAdapter.setListFavMovies(listFavMovies);
        }else{
            favMoviesAdapter.setListFavMovies(new ArrayList<FavoriteMoviesItem>());
            Toast.makeText(getContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
    }

    private static class LoadFavMoviesAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadFavMoviesAsync(Context context, LoadFavoriteCallback callback){
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    private void showLoading(boolean statement){
        if (statement){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}