package com.example.rio.week4hw;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = CFragment.class.getSimpleName();
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_LINK = "ARG_LINK";
    private CFragmentListener listener;
    private int mPage;
    private String link = "http://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private RecyclerView recyclerView;
    RecyclerDataAdapter recyclerDataAdapter;
    public ArrayList<Film> films;
    private boolean isHaveContent = false;
    public CFragment() {
        // Required empty public constructor
    }
    public interface CFragmentListener {
        void onButtonClick();
    }

    // TODO: Rename and change types and number of parameters
    public static CFragment newInstance(int page,String link) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_LINK,link);
        CFragment fragment = new CFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mPage = args.getInt(ARG_PAGE);
        link = args.getString(ARG_LINK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        //Create RycyclerView and evenlistener

        recyclerView = view.findViewById(R.id.recycler_view);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(link).build();
             client.newCall(request).enqueue(new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()) {
                        String jsonResponse = response.body().string();
                        films = Film.getListFilmFromJson(jsonResponse);
                        Log.d("Res:", jsonResponse);
                        isHaveContent = true;
                    }
                 }
             });
        while (!isHaveContent);
        recyclerDataAdapter = new RecyclerDataAdapter(getActivity(),films);
        recyclerView.setAdapter(recyclerDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG,"End onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        //catch exception
        if(getActivity() instanceof  CFragmentListener) {
            listener = (CFragmentListener) getActivity();
        }else {
            throw new ClassCastException(getActivity().getPackageName() +"is not cast CFragmentListener" );
        }
    }
}
