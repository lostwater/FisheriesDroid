package com.xyxd.fisher.Http;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lostw on 2016/4/4.
 */
public interface ILogin

{
    @FormUrlEncoded
    @POST("/token")
    Call<AccessToken> getAccessToken(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType);
    @FormUrlEncoded
    @POST("/api/Account/Register")
    Call<Object> register(
            @Field("phoneNumber") String phone,
            @Field("password") String password,
            @Field("confirmPassword") String confirmPassword,
            @Field("verifyCode") String code);
    @FormUrlEncoded
    @POST("/api/Account/ResetPassword")
    Call<Object> resetPassword(
            @Field("phoneNumber") String phone,
            @Field("password") String password,
            @Field("confirmPassword") String confirmPassword,
            @Field("verifyCode") String code);
}
