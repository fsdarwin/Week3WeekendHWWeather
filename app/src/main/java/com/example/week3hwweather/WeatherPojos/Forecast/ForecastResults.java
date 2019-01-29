
package com.example.week3hwweather.WeatherPojos.Forecast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastResults implements Parcelable
{

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Float message;
    @SerializedName("cnt")
    @Expose
    private Float cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.week3hwweather.WeatherPojos.Forecast.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;
    public final static Creator<ForecastResults> CREATOR = new Creator<ForecastResults>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ForecastResults createFromParcel(Parcel in) {
            return new ForecastResults(in);
        }

        public ForecastResults[] newArray(int size) {
            return (new ForecastResults[size]);
        }

    }
    ;

    protected ForecastResults(Parcel in) {
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Float) in.readValue((Float.class.getClassLoader())));
        this.cnt = ((Float) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.list, (com.example.week3hwweather.WeatherPojos.Forecast.List.class.getClassLoader()));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    public ForecastResults() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Float getMessage() {
        return message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public Float getCnt() {
        return cnt;
    }

    public void setCnt(Float cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.example.week3hwweather.WeatherPojos.Forecast.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.week3hwweather.WeatherPojos.Forecast.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(city);
    }

    public int describeContents() {
        return  0;
    }

}
