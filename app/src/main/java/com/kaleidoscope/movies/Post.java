package com.kaleidoscope.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

public class Post {
    @SerializedName("pagesCount")
    @Expose
    private int pagesCount;

    @SerializedName("films")
    @Expose
    private JSONArray films;

    public int getPagesCount() {
        return pagesCount;
    }

    public JSONArray getFilms() {
        return films;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setFilms(JSONArray films) {
        this.films = films;
    }

}