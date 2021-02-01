package com.example.classactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static final String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String url2 =  "&appid=44e12b853ec02c62501a0970f3ec9642&units=imperial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.cityInput);
        button = findViewById(R.id.buttonGo);
    }

    public void searchWeather(View v) {
        //Implement JSON

        if(editText.getText().length()!=0) {
            String text = String.valueOf(editText.getText());
            text.replace(" ","+");
            client.get(url1 + text + url2, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("client_response", new String(responseBody));
                    Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                    intent.putExtra(String.valueOf(R.string.json_key),new String(responseBody));
                    startActivity(intent);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("api_request", new String(responseBody));
                    Intent intentF = new Intent(MainActivity.this,FailActivity.class);
                    startActivity(intentF);
                }
            });
        }

    }
}