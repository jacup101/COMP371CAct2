package com.example.classactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {

    private TextView cityText;
    private TextView description;
    private TextView highText;
    private TextView lowText;
    private TextView feelText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityText = findViewById(R.id.cityText);
        description = findViewById(R.id.description);
        highText = findViewById(R.id.highNum);
        lowText = findViewById(R.id.lowNum);
        feelText = findViewById(R.id.feelNum);

        Intent intent = getIntent();
        String jsonStr = intent.getStringExtra(String.valueOf(R.string.json_key));

        try {
            JSONObject json = new JSONObject(jsonStr);
            JSONObject sys = json.getJSONObject("sys");
            JSONArray weather = json.getJSONArray("weather");
            JSONObject main = json.getJSONObject("main");
            String name = json.getString("name") + ", " + sys.getString("country");
            String desc = weather.getJSONObject(0).getString("description");
            int[] temps = new int[]{(int) main.getDouble("temp_max"),(int) main.getDouble("temp_min"),(int) main.getDouble("feels_like") };
            cityText.setText(name);
            description.setText(desc);
            highText.setText(temps[0]+"F");
            lowText.setText(temps[1]+"F");
            feelText.setText(temps[2]+"F");
        } catch (JSONException e) {
            Log.e("json_error","error");
            e.printStackTrace();
        }
    }
}
