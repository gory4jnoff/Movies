package com.goryajnoff.movies;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.author.setText(review.getAuthor());
        holder.review.setText(review.getReview());
        String type1 = "Позитивный";
        String type2 = "Нейтральный";
        String type3 = "Негативный";
        int colorId=0;
        if(review.getType().equals(type1)){
            colorId = android.R.color.holo_green_light;
        }else if(review.getType().equals(type2)){
            colorId = android.R.color.holo_orange_light;
        }else if(review.getType().equals(type3)){
            colorId = android.R.color.holo_red_light;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(),colorId);
        holder.cardView.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.textViewAuthor);
            review = itemView.findViewById(R.id.textViewReview);
            cardView = itemView.findViewById(R.id.cardView);
        }

        private TextView author;
        private TextView review;
        private CardView cardView;
    }
}
