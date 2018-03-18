package com.example.rio.week3hw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        Film film = films.get(position);
        final String title = film.getTitle();
        String overView = film.getOverview();
        String posterPath = film.HostImage + film.getPosterPath();
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
        holder.imPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext,title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return films == null ? 0: films.size();
    }
;
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private ImageView imPoster;
        private ImageView imPlay;
        private TextView textTitle;
        private TextView textOverview;
        private LinearLayout line;
        public DataViewHolder(View itemView) {
            super(itemView);
            line = (LinearLayout) itemView.findViewById(R.id.line);
            imPoster = (ImageView) itemView.findViewById(R.id.imgPoster);
            imPlay = (ImageView) itemView.findViewById(R.id.imgPlay);
            textTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            textOverview = (TextView) itemView.findViewById(R.id.txtOverview);
        }
    }
}
