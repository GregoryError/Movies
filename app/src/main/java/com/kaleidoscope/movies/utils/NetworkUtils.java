package com.kaleidoscope.movies.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.kaleidoscope.movies.JSONEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class NetworkUtils {
    private static final String API_KEY = "e7522639-089f-4044-a22a-ef5a2d5000da";
    private static final String BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_250_BEST_FILMS&page=1";

    public static final String OPTION_TOP250 = "top?type=TOP_250_BEST_FILMS&page=1";



    public static JSONObject getJSONFromNetwork(String options) {
        JSONObject result = null;
        try {
            URL url = new URL(BASE_URL);
            result = new JSONLoadTask().execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {
        JSONObject result = null;

        OkHttpClient httpClient = null;
        Response response = null;

        Gson gson = new Gson();
        ResponseBody responseBody = null;
        JSONEntity jsonEntity = null;


        @Override
        protected JSONObject doInBackground(URL... urls) {
            if (urls == null || urls.length == 0) {
                return result;
            }

            httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urls[0])
                    .addHeader("accept", "application/json")
                    .addHeader("X-API-KEY", API_KEY)
                    .build();
            try {
                response = httpClient.newCall(request).execute();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
             //   Log.i("RESULT", response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                result = new JSONObject(response.body().string());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}
















