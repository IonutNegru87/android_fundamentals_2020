package com.example.af2020.week8;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    
    String PAGE_QUERY = "page";
    
    @GET("/3/movie/top_rated")
    Call<MovieResult> getTopRatedMovies(@Query(PAGE_QUERY) int page);
    
    @GET("/3/movie/upcoming")
    Call<MovieResult> getUpcomingMovies(@Query(PAGE_QUERY) int page);

    @GET("/3/movie/now_playing")
    Call<MovieResult> getNowPlayingMovies(@Query(PAGE_QUERY) int page);
}
