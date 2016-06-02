package com.xyxd.fisher.Http;

import com.xyxd.fisher.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lostw on 2016/3/31.
 */
public interface IClient {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/api/Information")
    Call<List<Information>> getInformation(@Query("typeId") int typeId,@Query("page") int page,@Query("PageSize") int pageSize );

    @GET("/api/Events")
    Call<List<Event>> getEvents(@Query("page") int page,@Query("PageSize") int pageSize );

    @GET("/api/Events?date={date}")
    Call<List<Event>> getEvents(@Path("date") String date);

    @GET("/api/InformationTypes")
    Call<List<InformationType>> getInformationTypes();

    @GET("/api/Celebrities")
    Call<List<Celebrity>> getCelebrities(@Query("page") int page,@Query("PageSize") int pageSize );

    @GET("/api/Live/LocalLive")
    Call<List<Live>> getLive(@Query("type") int type, @Query("page") int page,@Query("PageSize") int pageSize );

    @GET("/api/LiveTypes")
    Call<List<LiveType>> getLiveTypes();

    @GET("/api/Ads/Home")
    Call<List<Ad>> getHomeAds();

    @GET("/api/Ads/FameHall")
    Call<List<Ad>> getFameAds();

    @GET("/api/Ads/Events")
    Call<List<Ad>> getEventsAds();

    @GET("/api/Ads/Live")
    Call<List<Ad>> getLiveAds();

    @GET("/api/Celebrities/{celeid}/Information")
    Call<List<Information>> getCelebrityInformation(@Path("celeid") int id);

    @GET("/api/Celebrities/{celeid}/Videos")
    Call<List<Video>> getCelebrityVideos(@Path("celeid") int id);

}