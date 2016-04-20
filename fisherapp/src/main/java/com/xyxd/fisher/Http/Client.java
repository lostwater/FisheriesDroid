package com.xyxd.fisher.Http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xyxd.fisher.Http.*;
import com.xyxd.fisher.model.ApplicationUser;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by lostw on 2016/3/31.
 */
public class Client {
    public static final String SERVERBASEURL = "http://123.56.207.193/";
    public static final String APIBASEURL = "http://123.56.207.193/";
    public static AccessToken accessToken = null;
    public static ApplicationUser user = null;
    static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    public static Retrofit retrofit;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }


    public static <S> S createService(Class<S> serviceClass, AccessToken token) {
        if (token != null) {
            final AccessToken _token = token;
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization",
                                    _token.getTokenType() + " " + _token.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(APIBASEURL)
                    .addConverterFactory(GsonConverterFactory.create(gson));


    public static String toUri(String path)
    {
        if(path == null)
            return "";
        return SERVERBASEURL  + path.replace('~','/').replace('\\','/');
    }

    public static ILogin LoginService()
    {
        return createService(ILogin.class);
    }

    public static IClient instance()
    {
       return createService(IClient.class);
    }

    public static UserClient userClient(){
        return createService(UserClient.class,accessToken);
    }


    public static IClient instance(AccessToken token)
    {
        return createService(IClient.class,token);
    }

    public static void TryLogin(String phone, String password) {
        Call<AccessToken> call =  Client.LoginService().getAccessToken(phone, password, "password");
        call.enqueue(new retrofit2.Callback<AccessToken>() {
            @Override
            public void onResponse(retrofit2.Response<AccessToken> response) {
                if(response.isSuccess())
                {
                    Client.accessToken = response.body();
                    TryGetUserDetail();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public static void TryGetUserDetail() {
        Call<ApplicationUser> call =  Client.userClient().getUserDetail();
        call.enqueue(new retrofit2.Callback<ApplicationUser>() {
            @Override
            public void onResponse(retrofit2.Response<ApplicationUser> response) {
                if(response.isSuccess())
                {
                    Client.user = response.body();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}



