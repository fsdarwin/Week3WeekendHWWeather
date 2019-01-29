package com.example.week3hwweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    //VARIABLES=======================
    public static final String TAG = "FRANK: ";
    String toggeleMeasures;

    //XML OBJECTS=====================
    ImageView imgLogo;
    ToggleButton tbMeasures;
    EditText etZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggeleMeasures = "U.S. Standard";

        //INSTANTIATE XML OBJECTS===================================================================
        imgLogo = findViewById(R.id.imgLogo);
        Glide.with(this)
                .load("http://clipartmag.com/images/umbrella-clipart-9.png")
                .into(imgLogo);
        tbMeasures = findViewById(R.id.tbMeasures);
        tbMeasures.setText(toggeleMeasures);
        etZip = findViewById(R.id.etZip);

        tbMeasures.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                if (isChecked) {
                    tbMeasures.setChecked(true);
                } else {
                    tbMeasures.setChecked(false);
                }
            }
        });

    }

    public void onClick(View view) {
        String zip = etZip.getText().toString();
        if (zip.length() == 5
                && isInteger(zip)
                && Integer.parseInt(zip) > 500
                && Integer.parseInt(zip) < 99951) {
            Intent intent = new Intent(this, LocalWeather.class);
            intent.putExtra("zip", zip);
            if(tbMeasures.isChecked()){
                intent.putExtra("measure","usStandard");
            }else {
                intent.putExtra("measure", "metric");
            }
            startActivity(intent);
        } else {
            etZip.setHint("Please enter a 5 digit zip code.");
        }

    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            Log.d(TAG, "isInteger: " + ex);
        }

        return isValidInteger;
    }
}
