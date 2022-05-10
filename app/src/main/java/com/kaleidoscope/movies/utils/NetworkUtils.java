package com.kaleidoscope.movies.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    private static final String API_KEY = "e7522639-089f-4044-a22a-ef5a2d5000da";
    private static final String BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/";

    public static final String OPTION_TOP250 = "top?type=TOP_250_BEST_FILMS&page=1";


    public static JSONObject getJSONFromNetwork(String options) {
        JSONObject result = null;

        try {
            URL url = new URL(BASE_URL + options);
            result = new JSONLoadTask().execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {
        JSONObject result = null;
        StringBuilder builder = null;
        HttpsURLConnection connection = null;
        OutputStreamWriter request = null;

        @Override
        protected JSONObject doInBackground(URL... urls) {
            if (urls == null || urls.length == 0) {
                return result;
            }

            try {
                connection = (HttpsURLConnection) urls[0].openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("X-API-KEY", API_KEY);
                connection.setDoOutput(true);
     

                request = new OutputStreamWriter(connection.getOutputStream());


                InputStreamReader inputStreamReader = null;

                int status = connection.getResponseCode();
                if(status < 400) {
                    inputStreamReader = new InputStreamReader(connection.getInputStream());

                } else{
                    inputStreamReader = new InputStreamReader(connection.getErrorStream());
                }



               // InputStream inputStream = connection.getInputStream();
               // InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                result = new JSONObject(builder.toString());

            }catch (Exception e) {
                Log.i("EXC", "JSONLoadTask");
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }
    }


}
