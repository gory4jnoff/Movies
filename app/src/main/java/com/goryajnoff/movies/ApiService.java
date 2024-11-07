package com.goryajnoff.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.4/movie?rating.kp=7-10&sortField=votes.kp&sortType=-1&token=238G1V0-RJM43YZ-NE0ZGAQ-TJFRBPW&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);
    @GET("v1.4/movie/{id}?token=238G1V0-RJM43YZ-NE0ZGAQ-TJFRBPW")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);
    @GET("/v1.4/review?limit=10&selectFields=&token=238G1V0-RJM43YZ-NE0ZGAQ-TJFRBPW")
    Single<ReviewResponse> loadReview(@Query("movieId") int movieId);


}
