package com.example.rio.week3hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Film> films;  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        films = Film.getListFilmFromJson(MyApp.getMessage());
        RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(this,films);
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}