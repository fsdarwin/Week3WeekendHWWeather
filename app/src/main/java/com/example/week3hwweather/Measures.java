package com.example.week3hwweather;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class Measures {
    public Measures() {
    }
    static DecimalFormat decimalFormat = new DecimalFormat("###.##");
    static DecimalFormat decimalDegF = new DecimalFormat("###");
    static DecimalFormat decimalDegC = new DecimalFormat("###.#");

    public static String convertToDegF (String tempString){

        Double tempK = new Double(tempString);
        Double tempF = (tempK - 273.15) * 9.0/5.0 + 32.0;
        return decimalDegF.format(tempF);
    }
    public static String convertToDegC (String tempString){
        Double tempK = new Double(tempString);
        Double tempC = (tempK - 273.15);
        return decimalDegC.format(tempC);
    }
    public static String make2PlaceDecimal(Float humidity){
        String string = decimalFormat.format(humidity);
        return string;
    }
}
