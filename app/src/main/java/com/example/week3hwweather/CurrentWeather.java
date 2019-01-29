package com.example.week3hwweather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeather extends Fragment {

    public static final String TAG = "FRANK: ";
    static TextView tvZip;
    static TextView tvTemp;


    public CurrentWeather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvZip = view.findViewById(R.id.tvZip);
        tvTemp = view.findViewById(R.id.tvTemp);

    }

    public static void setDisplay(String local, String temp) {
        tvZip.setText(local);
        tvTemp.setText(temp);
    }


}
