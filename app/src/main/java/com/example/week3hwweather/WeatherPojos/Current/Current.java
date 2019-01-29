
package com.example.week3hwweather.WeatherPojos.Current;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current implements Parcelable
{

    @SerializedName("results")
    @Expose
    private CurrentResults results;
    public final static Creator<Current> CREATOR = new Creator<Current>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        public Current[] newArray(int size) {
            return (new Current[size]);
        }

    }
    ;

    protected Current(Parcel in) {
        this.results = ((CurrentResults) in.readValue((CurrentResults.class.getClassLoader())));
    }

    public Current() {
    }

    public CurrentResults getResults() {
        return results;
    }

    public void setResults(CurrentResults results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(results);
    }

    public int describeContents() {
        return  0;
    }

}
