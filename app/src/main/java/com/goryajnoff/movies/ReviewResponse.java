package com.goryajnoff.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("docs")
    private List<Review> reviewList;

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewList=" + reviewList +
                '}';
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public ReviewResponse(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
