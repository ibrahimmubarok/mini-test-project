package com.ibeybeh.moviedbwithfavoritemovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.model.moviesmodel.MoviesItem;

import java.util.ArrayList;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder> {

    private Context context;
    private ArrayList<MoviesItem> mData;

    public void setData(ArrayList<MoviesItem> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public ArrayList<MoviesItem> getmData() {
        return mData;
    }

    public ComingSoonAdapter(Context context, ArrayList<MoviesItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ComingSoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coming_soon, parent, false);
        return new ComingSoonViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonViewHolder holder, int position) {
        holder.tvTitleMovies.setText(getmData().get(position).getTitle());
        holder.tvRatingMovies.setText(getmData().get(position).getVoteAverage());

        Glide.with(context)
                .load(getmData().get(position).getPosterPath())
                .apply(new RequestOptions().override(380,420))
                .into(holder.imgPhotoMovies);
    }

    @Override
    public int getItemCount() {
        return getmData().size();
    }

    public class ComingSoonViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhotoMovies;
        TextView tvTitleMovies, tvRatingMovies;

        public ComingSoonViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotoMovies = itemView.findViewById(R.id.img_coming_soon);
            tvTitleMovies = itemView.findViewById(R.id.tv_title_coming_soon);
            tvRatingMovies = itemView.findViewById(R.id.tv_rating_coming_soon);
        }
    }
}
