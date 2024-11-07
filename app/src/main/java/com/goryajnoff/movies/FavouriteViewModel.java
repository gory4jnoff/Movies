package com.goryajnoff.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private MovieDao movieDao;
    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();

    }


    public LiveData<List<Movie>> getAllFavouriteMovies(){
        return movieDao.getAllFavouriteMovies();
    }
}
