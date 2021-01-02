package com.ibeybeh.moviedbwithfavoritemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibeybeh.moviedbwithfavoritemovie.R;
import com.ibeybeh.moviedbwithfavoritemovie.activity.DetailMoviesActivity;
import com.ibeybeh.moviedbwithfavoritemovie.customclick.CustomOnItemClickListener;
import com.ibeybeh.moviedbwithfavoritemovie.entity.FavoriteMoviesItem;

import java.util.ArrayList;

import static com.ibeybeh.moviedbwithfavoritemovie.db.DatabaseContract.FavoriteMoviesColumns.CONTENT_URI;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavMoviesViewHolder> {

    private ArrayList<FavoriteMoviesItem> listFavMovies = new ArrayList<>();
    private Context context;

    public FavoriteMoviesAdapter(Context context) {
        this.context = context;
    }

    public void setListFavMovies(ArrayList<FavoriteMoviesItem> listMovies){
        this.listFavMovies.clear();
        this.listFavMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    public ArrayList<FavoriteMoviesItem> getListFavMovies(){
        return listFavMovies;
    }

    @NonNull
    @Override
    public FavMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_movies, parent, false);
        return new FavMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMoviesViewHolder holder, int position) {
        holder.tvTitle.setText(getListFavMovies().get(position).getTitle());
        holder.tvRating.setText(getListFavMovies().get(position).getRating());
        Glide.with(context)
                .load(getListFavMovies().get(position).getImgUrl())
                .apply(new RequestOptions().override(380, 420))
                .into(holder.imgPhoto);
        holder.cvFav.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailMoviesActivity.class);

                //content://com.ibeybeh.moviedbwithfavoritemovie/favorite_movies/id
                Uri uri = Uri.parse(CONTENT_URI + "/" + getListFavMovies().get(position).getId());
                intent.setData(uri);
                intent.putExtra(DetailMoviesActivity.EXTRA_ID_MOVIES, getListFavMovies().get(position).getId());
                intent.putExtra(DetailMoviesActivity.EXTRA_FAVORITE_MOVIES, getListFavMovies().get(position));
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListFavMovies().size();
    }

    public class FavMoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvRating;
        ImageView imgPhoto;
        CardView cvFav;
        public FavMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_fav_title_movies);
            tvRating = itemView.findViewById(R.id.tv_fav_rating_movies);
            imgPhoto = itemView.findViewById(R.id.img_fav_movies);
            cvFav = itemView.findViewById(R.id.card_view_fav_movies);
        }
    }
}
