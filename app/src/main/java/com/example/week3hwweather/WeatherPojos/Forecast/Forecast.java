
package com.example.week3hwweather.WeatherPojos.Forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast implements Parcelable
{

    @SerializedName("results")
    @Expose
    private ForecastResults results;
    public final static Creator<Forecast> CREATOR = new Creator<Forecast>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        public Forecast[] newArray(int size) {
            return (new Forecast[size]);
        }

    }
    ;

    protected Forecast(Parcel in) {
        this.results = ((ForecastResults) in.readValue((ForecastResults.class.getClassLoader())));
    }

    public Forecast() {
    }

    public ForecastResults getResults() {
        return results;
    }

    public void setResults(ForecastResults results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(results);
    }

    public int describeContents() {
        return  0;
    }

}
