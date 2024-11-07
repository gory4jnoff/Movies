package com.goryajnoff.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class favourite_movies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private FavouriteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        recyclerView = findViewById(R.id.recyclerViewFavourite);
        moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
        viewModel.getAllFavouriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        moviesAdapter.setOnMovieListener(new MoviesAdapter.OnMovieListener() {
            @Override
            public void onMovie(Movie movie) {
                launchNewScreen(movie);
            }
        });


    }

    private void launchNewScreen(Movie movie){
       Intent intent =  MovieDetailActivity.newIntent(favourite_movies.this,movie);
        startActivity(intent);
    }
    public static Intent newIntent(Context context){
        return new Intent(context,favourite_movies.class);
    }


}