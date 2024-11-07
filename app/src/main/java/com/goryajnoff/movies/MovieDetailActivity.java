package com.goryajnoff.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";
    private MovieDetailViewModel viewModel;
    private RecyclerView recyclerViewTrailers;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerViewReviews;
    private ImageView imageViewPosterDetail;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private TrailersAdapter trailersAdapter;
    private ImageView imageViewStar;
    private static final String TAG = "MovieDetailActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
        recyclerViewReviews.setAdapter(reviewAdapter);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTrailers.setAdapter(trailersAdapter);
        recyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        textViewTitle.setText(movie.getName());
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPosterDetail);
        textViewDescription.setText(movie.getDescription());
        textViewYear.setText(String.valueOf(movie.getYear()));
        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
            }
        });
        trailersAdapter.setOnClickTrailers(new TrailersAdapter.OnClickTrailers() {
            @Override
            public void clickTrailers(Trailer trailer) {//неявный интент
                Intent intent = new Intent(Intent.ACTION_VIEW);//открывает адрес в браузере
                intent.setData(Uri.parse(trailer.getUrl()));//передаем адрес
                startActivity(intent);
            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviewList(reviews);
            }
        });
        viewModel.loadReviews(movie.getId());

        Drawable starOff = ContextCompat.getDrawable(MovieDetailActivity.this,
                android.R.drawable.star_big_off);
        Drawable starOnn = ContextCompat.getDrawable(MovieDetailActivity.this,
                android.R.drawable.star_big_on);

        viewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb == null) {
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.insertMovie(movie);
                        }
                    });
                } else {
                    imageViewStar.setImageDrawable(starOnn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.deleteMovie(movie.getId());
                        }
                    });
                }
            }
        });
    }

    private void initViews() {
        imageViewPosterDetail = findViewById(R.id.imageViewPosterDetail);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        reviewAdapter = new ReviewAdapter();
        trailersAdapter = new TrailersAdapter();
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}