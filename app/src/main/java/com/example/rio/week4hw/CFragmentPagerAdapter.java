package com.example.rio.week4hw;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CFragmentPagerAdapter extends FragmentPagerAdapter{
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Now Playing", "Top Rate"};
    private String link[] = new String[] {"https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc1\n" +
            "8f5cb106bfe4cc1f83ad8ed","https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f\n" +
            "5cb106bfe4cc1f83ad8ed"};
    private Context mcontext;
    public CFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mcontext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CFragment.newInstance(position+1,link[position]);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
