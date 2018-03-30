package com.example.rio.week4hw;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


/**
 * Created by Rio on 3/15/2018.
 */

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder> {

    private Context mcontext;
    private List<Film> films;
    public RecyclerDataAdapter(Context context, ArrayList<Film> films) {
        this.mcontext = context;
        this.films = films;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemView = layoutInflater.inflate(R.layout.item_film,parent,false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        final Film film = films.get(position);
        String title = film.getTitle();
        String overView = film.getOverview();
        String posterPath = film.getPosterPath();
        Boolean isPlay = film.getIsVideo();
        holder.textTitle.setText(title);
        holder.textOverview.setText(overView);
        Picasso.with(mcontext).load(posterPath).into(holder.imPoster);
        holder.imPlay.setImageResource(android.R.drawable.ic_media_play);
        if(isPlay) {
            holder.imPlay.setVisibility(View.VISIBLE);
        } else {
            holder.imPlay.setVisibility(View.INVISIBLE);
        }
//        holder.imPoster.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mcontext,title, Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,FilmDetailActivity.class);
                intent.putExtra("Title",film.getTitle());
                intent.putExtra("Overview",film.getOverview());
                intent.putExtra("ReleaseDate",film.getReleaseDate());
                intent.putExtra("BackdropPath",film.getBackdropPath());
                intent.putExtra("VoteAverage",film.getVoteAverage());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return films == null ? 0: films.size();
    }
;
    public class DataViewHolder extends RecyclerView.ViewHolder{
        private ImageView imPoster;
        private ImageView imPlay;
        private TextView textTitle;
        private TextView textOverview;
        private LinearLayout item;
        public DataViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.line);
            imPoster = (ImageView) itemView.findViewById(R.id.imgPoster);
            imPlay = (ImageView) itemView.findViewById(R.id.imgPlay);
            textTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            textOverview = (TextView) itemView.findViewById(R.id.txtOverview);
        }
    }

}
