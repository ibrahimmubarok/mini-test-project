package com.ibeybeh.moviedbwithfavoritemovie.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeybeh.moviedbwithfavoritemovie.MoviesViewModel;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity;
import com.ibeybeh.moviedbwithfavoritemovie.adapter.ComingSoonAdapter;
import com.ibeybeh.moviedbwithfavoritemovie.adapter.PopularMoviesAdapter;
import com.ibeybeh.moviedbwithfavoritemovie.customclick.ItemClickSupport;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesItem;

import java.util.ArrayList;

import static com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity.KEY_ID_MOVIES;
import static com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity.KEY_RATING_MOVIES;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView tvPopular, tvComingSoon;

    private ArrayList<MoviesItem> popularMoviesList = new ArrayList<>();
    private ArrayList<MoviesItem> comingSoonList = new ArrayList<>();
    private PopularMoviesAdapter popularMoviesAdapter;
    private ComingSoonAdapter comingSoonAdapter;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MoviesViewModel moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getLiveDataPopular().observe(this, getPopularMovies);
        moviesViewModel.getLiveDataComingSoon().observe(this, getComingSoonMovies);

        progressBar = view.findViewById(R.id.progress_bar_movies);
        tvPopular = view.findViewById(R.id.tv_popular_movies);
        tvComingSoon = view.findViewById(R.id.tv_coming_soon);

        RecyclerView rvPopularMovies = view.findViewById(R.id.rv_popular_movies);
        RecyclerView rvComingSoon = view.findViewById(R.id.rv_coming_soon);

        popularMoviesAdapter = new PopularMoviesAdapter(this.getContext(), popularMoviesList);
        popularMoviesAdapter.notifyDataSetChanged();

        comingSoonAdapter = new ComingSoonAdapter(this.getContext(), comingSoonList);
        comingSoonAdapter.notifyDataSetChanged();

        rvPopularMovies.setHasFixedSize(true);
        rvPopularMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPopularMovies.setAdapter(popularMoviesAdapter);

        rvComingSoon.setHasFixedSize(true);
        rvComingSoon.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvComingSoon.setAdapter(comingSoonAdapter);

        showLoading(true);
        moviesViewModel.asyncPopularMovies();
        moviesViewModel.asyncComingSoon();

        onClickPopularMovies(rvPopularMovies);
        onClickkComingSoon(rvComingSoon);
    }

    private void onClickPopularMovies(RecyclerView recyclerView) {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getContext(), popularMoviesList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent detailIntent = new Intent(getContext(), DetailMoviesActivity.class);
                detailIntent.putExtra(KEY_ID_MOVIES, popularMoviesList.get(position).getId());
                detailIntent.putExtra(KEY_RATING_MOVIES, popularMoviesList.get(position).getVoteAverage());
                startActivity(detailIntent);
            }
        });
    }

    private void onClickkComingSoon(RecyclerView recyclerView) {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getContext(), comingSoonList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent detailIntent = new Intent(getContext(), DetailMoviesActivity.class);
                detailIntent.putExtra(KEY_ID_MOVIES, comingSoonList.get(position).getId());
                detailIntent.putExtra(KEY_RATING_MOVIES, comingSoonList.get(position).getVoteAverage());
                startActivity(detailIntent);
            }
        });
    }

    private Observer<ArrayList<MoviesItem>> getPopularMovies = new Observer<ArrayList<MoviesItem>>() {
        @Override
        public void onChanged(ArrayList<MoviesItem> moviesItems) {
            if (moviesItems != null){
                popularMoviesAdapter.setData(moviesItems);
                showLoading(false);
            }else{
                Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Observer<ArrayList<MoviesItem>> getComingSoonMovies = new Observer<ArrayList<MoviesItem>>() {
        @Override
        public void onChanged(ArrayList<MoviesItem> moviesItems) {
            if (moviesItems != null){
                comingSoonAdapter.setData(moviesItems);
                showLoading(false);
            }else{
                Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void showLoading(boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
            tvPopular.setVisibility(View.GONE);
            tvComingSoon.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            tvPopular.setVisibility(View.VISIBLE);
            tvComingSoon.setVisibility(View.VISIBLE);
        }
    }
}
