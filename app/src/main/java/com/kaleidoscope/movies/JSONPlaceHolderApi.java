package com.kaleidoscope.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @Headers({
            "Accept: application/json",
            "X-API-KEY: e7522639-089f-4044-a22a-ef5a2d5000da",
    })


    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);
}
