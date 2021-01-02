package com.ibeybeh.moviedbwithfavoritemovie.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.fragment.FavoriteFragment;
import com.ibeybeh.moviedbwithfavoritemovie.fragment.MoviesFragment;
import com.ibeybeh.moviedbwithfavoritemovie.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private String fragmentName = "";
    private final String FRAGMENT_TAG = "fragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("MovieDB");
        }

        if (savedInstanceState != null){
            fragmentName = savedInstanceState.getString(FRAGMENT_TAG);
        }else {
            loadFragment(new MoviesFragment());
        }

        bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.movies :
                        fragmentName = "movies";
                        loadFragment(new MoviesFragment());
                        return true;

                    case R.id.search :
                        fragmentName = "search";
                        loadFragment(new SearchFragment());
                        return true;

                    case R.id.fav_menu :
                        fragmentName = "favorite";
                        loadFragment(new FavoriteFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(FRAGMENT_TAG, fragmentName);
        super.onSaveInstanceState(outState);
    }

    private void loadFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_container, fragment);
        fragmentTransaction.commit();
    }

}
