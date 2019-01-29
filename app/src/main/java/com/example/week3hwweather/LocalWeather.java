package com.example.week3hwweather;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.week3hwweather.Event.WeatherCurrent;
import com.example.week3hwweather.OkHttp.OkHttpHelper;
import com.example.week3hwweather.WeatherPojos.Current.CurrentResults;
import com.example.week3hwweather.WeatherPojos.Forecast.List;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.week3hwweather.OkHttp.OkHttpConstants.WEATHER_CURRENT;
import static com.example.week3hwweather.OkHttp.OkHttpConstants.WEATHER_CURRENT2;
import static com.example.week3hwweather.OkHttp.OkHttpConstants.WEATHER_FORECAST;
import static com.example.week3hwweather.OkHttp.OkHttpConstants.WEATHER_FORECAST2;

public class LocalWeather extends AppCompatActivity {
    //CLASS VARIABLES=============================================
    Intent receivedIntent;
    String zip;
    public static String measure;
    String jsonResponse;
    Gson gson;
    CurrentWeather currentFrag;
    LocalForecast forecastFrag;


    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_weather);

        currentFrag = new CurrentWeather();
        forecastFrag = new LocalForecast();

        receivedIntent = getIntent();
        zip = receivedIntent.getStringExtra("zip");
        measure = receivedIntent.getStringExtra("measure");


        OkHttpHelper.asyncOkHttpCall(WEATHER_CURRENT + zip + WEATHER_CURRENT2, "current");
        OkHttpHelper.asyncOkHttpCall(WEATHER_FORECAST + zip + WEATHER_FORECAST2, "forecast");

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flTop, currentFrag).commit();
        fm.beginTransaction().replace(R.id.flBottom, forecastFrag).commit();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WeatherCurrent weatherCurrent) {
        String temp;

        gson = new Gson();
        jsonResponse = weatherCurrent.getCurrentEvent();
        CurrentResults results = gson.fromJson(jsonResponse, CurrentResults.class);
        if (measure == "metric") {
            temp = Measures.convertToDegC(results.getMain().getTemp().toString());
        } else {
            temp = Measures.convertToDegF(results.getMain().getTemp().toString());
        }
        CurrentWeather.setDisplay(results.getName(), temp);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
