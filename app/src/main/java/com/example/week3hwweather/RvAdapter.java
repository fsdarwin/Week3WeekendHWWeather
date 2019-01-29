package com.example.week3hwweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week3hwweather.WeatherPojos.Forecast.ForecastResults;
import com.example.week3hwweather.WeatherPojos.Forecast.List;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<List> forecastArrayList;
    public static final String TAG = "FRANK: ";

    public RvAdapter(ArrayList<List> forecastArrayList) {
        this.forecastArrayList = forecastArrayList;
    }

    //ViewHolder viewHolder;
    //int position;

    @NonNull
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder viewHolder, int position) {

        List forecast = forecastArrayList.get(position);
        if (forecast != null) {
            //GET TEMPERATURE AND CONVERT, FORMAT ACCORDING TO PREFERENCES
            String temp = forecast.getMain().getTemp();
            if (new Double(temp) >= 288.7) {
                viewHolder.tvTemp.setTextColor(0xFFFF8888);
            }else{
                viewHolder.tvTemp.setTextColor(0xFF8888FF);
            }
            if (LocalWeather.measure == "metric") {
                temp = Measures.convertToDegC(temp);
            } else {
                temp = Measures.convertToDegF(temp);
            }
            String dateTime = forecast.getDtTxt();
            //MAKE HUMIDITY INTO @ DECIMAL PLACE NUMBER AND PUT PERCENT SIGN.
            Log.d(TAG, "onBindViewHolder: Humidity:" + forecast.getMain().getHumidity());
            String local = "Humidity: " + Measures.make2PlaceDecimal(forecast.getMain().getHumidity()) + "%";

            viewHolder.tvDateTime.setText(dateTime);
            viewHolder.tvLocation.setText(local);
            viewHolder.tvTemp.setText(temp);
            Log.d(TAG, "onBindViewHolder: " + temp);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvDateTime;
        TextView tvLocation;
        TextView tvTemp;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvTemp = itemView.findViewById(R.id.tvTemp);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public int getItemCount() {
        return forecastArrayList != null ? forecastArrayList.size() : 0;
    }
}
