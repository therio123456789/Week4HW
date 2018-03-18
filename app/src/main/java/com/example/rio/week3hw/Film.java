package com.example.rio.week3hw;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Rio on 3/15/2018.
 */


public class Film{
    public final static String HostImage = "http://image.tmdb.org/t/p/w500";
    private String title;
    private String overview;
    private Boolean isVideo;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private Float voteAverage;
    public Film(String title, String overview, Boolean isVideo, String posterPath, String backdropPath,
                String releaseDate, Float voteAverage) {
        super();
        this.title = title;
        this.overview = overview;
        this.isVideo = isVideo;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Boolean getIsVideo() {
        return isVideo;
    }
    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Float getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }
    public static ArrayList<Film> getListFilmFromJson(String jsonString) throws JsonParseException {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonMaterial = jsonParser.parse(jsonString).getAsJsonObject();
        JsonArray jsonFilmArray = jsonMaterial.get("results").getAsJsonArray();
        ArrayList<Film> films = new ArrayList<>();
        if(!jsonFilmArray.isJsonArray())
            return null;
        else {
            for(int i = 0; i < jsonFilmArray.size(); i++ ) {
                JsonObject jsonObject = jsonFilmArray.get(i).getAsJsonObject();
                films.add(new Film(jsonObject.get("title").getAsString(), jsonObject.get("overview").getAsString(), jsonObject.get("video").getAsBoolean()
                        , jsonObject.get("poster_path").getAsString(), jsonObject.get("backdrop_path").getAsString(), jsonObject.get("release_date").getAsString(), jsonObject.get("vote_average").getAsFloat()));
            }
            return films;
        }

    }
}