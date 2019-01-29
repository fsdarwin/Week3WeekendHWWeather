package com.example.week3hwweather.OkHttp;

import android.util.Log;
import com.example.week3hwweather.Event.WeatherCurrent;
import com.example.week3hwweather.Event.WeatherForecast;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpHelper {

    public static final String TAG = "FRANK: ";

    public OkHttpHelper() {
    }

    public static void asyncOkHttpCall(String url, final String type) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;
            WeatherCurrent sendCurrent;
            WeatherForecast sendForecast;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (type == "current") {
                    jsonResponse = response.body().string();
                    sendCurrent = new WeatherCurrent(jsonResponse);
                    Log.d(TAG, "onResponse: " + jsonResponse);
                    EventBus.getDefault().post(sendCurrent);
                } else {
                    jsonResponse = response.body().string();
                    sendForecast = new WeatherForecast(jsonResponse);
                    Log.d(TAG, "onResponse: " + jsonResponse);
                    EventBus.getDefault().post(sendForecast);
                }
            }
        });
    }
}
