package com.kaleidoscope.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kaleidoscope.movies.utils.NetworkUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewCenter);


        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(1)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();

                        textView.append(post.getId() + "\n");
                        textView.append(post.getUserId() + "\n");
                        textView.append(post.getTitle() + "\n");
                        textView.append(post.getBody() + "\n");
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });


//
//        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.OPTION_TOP250);
//
//        if (jsonObject != null) {
//            Log.i("OUT", jsonObject.toString());
//        }


    }
}