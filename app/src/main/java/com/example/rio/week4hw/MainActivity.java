package com.example.rio.week4hw;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CFragment.CFragmentListener {
    public ArrayList<Film> films;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        films = Film.getListFilmFromJson(MyApp.getMessage());
//        RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(this,films);
//        recyclerView.setAdapter(recyclerDataAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onButtonClick() {
        Log.d("MSG","ButtonClick");
    }
}