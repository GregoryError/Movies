package com.kaleidoscope.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kaleidoscope.movies.utils.NetworkUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.OPTION_TOP250);
        if (jsonObject == null)
            Log.i("My result", "ОШИБКА");
        else
            Log.i("My result", jsonObject.toString());

    }
}