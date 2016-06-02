package com.xyxd.fisher.Http;

import com.xyxd.fisher.model.ApplicationUser;
import com.xyxd.fisher.model.Event;
import com.xyxd.fisher.model.EventStatu;
import com.xyxd.fisher.model.Live;
import com.xyxd.fisher.model.Order;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lostw on 2016/4/5.
 */
public interface UserClient {
    @GET("/api/Account/UserDetail")
    Call<ApplicationUser> getUserDetail();

    @GET("/api/Orders")
    Call<List<Order>> getOrders(@Query("timePara") int time,@Query("statuPara") int statu);

    @GET("/api/Account/Lives")
    Call<List<Live>> getMyLives();

    @GET("/api/Account/Shops")
    Call<List<Event>> getMyShopEvents();

    @GET("/api/Events/EventStatu/{id}")
    Call<EventStatu> getEventStatu(@Path("id") int id);

    @POST("/api/Orders/CreateOrder/{eventId}")
    Call<Order> postCreateOrder(@Path("eventId") int id);

    @GET("/api/Orders/{id}")
    Call<Order> getOrder(@Path("id") int id);



    @POST("/api/Shops/Follow/{id}")
    Call<Void> followShop(@Path("id") int id);

    @POST("/api/Shops/Unfollow/{id}")
    Call<Void> unfollowShop(@Path("id") int id);

    @POST("/api/Live/Follow/{id}")
    Call<Void> followLive(@Path("id") int id);

    @POST("/api/Live/Unfollow/{id}")
    Call<Void> unfollowLive(@Path("id") int id);

    @POST("/api/Payments/Request")
    Call<Object> getPaymentCharge(@Query("orderId") int id);

    @POST("/api/Account/ChangeUsername")
    Call<Void> changeUserName(@Query("username") String username);


    @Headers("encrypt:multipart/form-data")
    @POST("/api/Account/ChangeAvatar")
    Call<Void> changeAvatar(@Body RequestBody file);

}
