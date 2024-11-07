package com.goryajnoff.movies;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {
    @SerializedName("videos")
    private TrailersList trailersList;

    public TrailersList getTrailersList() {
        return trailersList;
    }

    public TrailerResponse(TrailersList trailersList) {
        this.trailersList = trailersList;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailersList=" + trailersList +
                '}';
    }
}
