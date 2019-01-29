package com.example.week3hwweather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week3hwweather.Event.WeatherForecast;
import com.example.week3hwweather.WeatherPojos.Current.CurrentResults;
import com.example.week3hwweather.WeatherPojos.Forecast.Forecast;
import com.example.week3hwweather.WeatherPojos.Forecast.ForecastResults;
import com.example.week3hwweather.WeatherPojos.Forecast.List;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalForecast extends Fragment {

    public static final String TAG = "FRANK: ";
    ArrayList<List> forecastArrayList;
    RvAdapter rvAdapter;
    RecyclerView recyclerView;
    Gson gson;
    String jsonResponse;
    ForecastResults results;



    public LocalForecast() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_forecast, container, false);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WeatherForecast weatherForecast){

        gson = new Gson();
        jsonResponse =  weatherForecast.getForecastEvent();
        results = gson.fromJson(jsonResponse, ForecastResults.class);
        forecastArrayList = new ArrayList<>(results.getList());
        rvAdapter = new RvAdapter(forecastArrayList);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvLocalForecast);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
