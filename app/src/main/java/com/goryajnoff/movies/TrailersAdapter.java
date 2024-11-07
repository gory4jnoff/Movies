package com.goryajnoff.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private OnClickTrailers onClickTrailers;

    public void setOnClickTrailers(OnClickTrailers onClickTrailers) {
        this.onClickTrailers = onClickTrailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_item,
                parent,
                false
        );
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.textViewTrailer.setText(trailer.getName());
        holder.textViewTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickTrailers != null) {
                    onClickTrailers.clickTrailers(trailer);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTrailer;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailer = itemView.findViewById(R.id.textViewTrailer);
        }
    }

    interface OnClickTrailers {
        void clickTrailers(Trailer trailer);
    }


}
