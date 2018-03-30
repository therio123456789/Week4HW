package com.example.rio.week4hw;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmDetailActivity extends AppCompatActivity {
    private ImageView dropBackgroundImage;
    private TextView titleTxtView;
    private RatingBar ratingBar;
    private TextView releaseDateTxtView;
    private TextView overViewTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        Intent intent = getIntent();
        connetView();
        if(intent != null) {
            String dropBackgroundPath = intent.getStringExtra("BackdropPath");
            Picasso.with(this).load(dropBackgroundPath).into(dropBackgroundImage);

            String title = intent.getStringExtra("Title");
            titleTxtView.setText(title);

            float voteAverage = intent.getFloatExtra("VoteAverage",0);
            Log.d("Rio:", String.valueOf(voteAverage));
            ratingBar.setRating(voteAverage/2);

            String releaseDate = intent.getStringExtra("ReleaseDate");
            releaseDateTxtView.setText("Release Date: "+releaseDate);

            String overview = intent.getStringExtra("Overview");
            overViewTxtView.setText(overview);

        }
    }

    private void connetView() {
        dropBackgroundImage = findViewById(R.id.dropBackground);
        titleTxtView = findViewById(R.id.txtTitle);
        ratingBar = findViewById(R.id.ratingBar);
        releaseDateTxtView = findViewById(R.id.txtReleaseDate);
        overViewTxtView = findViewById(R.id.txtOverview);
    }
}
