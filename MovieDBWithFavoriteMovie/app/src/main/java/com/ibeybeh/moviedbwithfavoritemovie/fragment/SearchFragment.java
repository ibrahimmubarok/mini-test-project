package com.ibeybeh.moviedbwithfavoritemovie.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ibeybeh.moviedbwithfavoritemovie.MoviesViewModel;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity;
import com.ibeybeh.moviedbwithfavoritemovie.adapter.SearchMoviesAdapter;
import com.ibeybeh.moviedbwithfavoritemovie.customclick.ItemClickSupport;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesItem;

import java.util.ArrayList;

import static com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity.KEY_ID_MOVIES;
import static com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity.KEY_RATING_MOVIES;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextView tvResult;
    private EditText edtSearch;
    private Button btnSearch;

    private MoviesViewModel moviesViewModel;

    private SearchMoviesAdapter searchMoviesAdapter;
    private ArrayList<MoviesItem> searchMoviesList = new ArrayList<>();
    private String query ;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getLiveDataSearch().observe(this, getSearchMovies);

        progressBar = view.findViewById(R.id.progress_bar_search_movies);
        tvResult = view.findViewById(R.id.tv_hasil_pencarian);
        edtSearch = view.findViewById(R.id.edt_search_movies);
        btnSearch = view.findViewById(R.id.btn_search);

        RecyclerView rvSearchMovies = view.findViewById(R.id.rv_search_movies);

        searchMoviesAdapter = new SearchMoviesAdapter(getContext(), searchMoviesList);
        searchMoviesAdapter.notifyDataSetChanged();

        rvSearchMovies.setHasFixedSize(true);
        rvSearchMovies.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        rvSearchMovies.setAdapter(searchMoviesAdapter);

        btnSearch.setOnClickListener(this);
        onClickSearchMovies(rvSearchMovies);
    }

    private Observer<ArrayList<MoviesItem>> getSearchMovies = new Observer<ArrayList<MoviesItem>>() {
        @Override
        public void onChanged(ArrayList<MoviesItem> moviesItems) {
            if (moviesItems != null){
                searchMoviesAdapter.setData(moviesItems);
                showLoading(false);
            }
        }
    };

    private void showLoading(boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
            tvResult.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            tvResult.setVisibility(View.VISIBLE);
        }
    }

    private void onClickSearchMovies(RecyclerView recyclerView) {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getContext(), searchMoviesList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent detailIntent = new Intent(getContext(), DetailMoviesActivity.class);
                detailIntent.putExtra(KEY_ID_MOVIES, searchMoviesList.get(position).getId());
                detailIntent.putExtra(KEY_RATING_MOVIES, searchMoviesList.get(position).getVoteAverage());
                startActivity(detailIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_search){
            tvResult.setText("Hasil Pencarian : "+edtSearch.getText().toString());

            moviesViewModel.asyncSearchMovies(edtSearch.getText().toString());
            showLoading(true);
        }
    }
}
