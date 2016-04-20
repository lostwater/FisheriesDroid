package com.xyxd.fisher.model;

import com.xyxd.fisher.Http.Client;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by lostw on 2016/4/7.
 */
public class ServerErrorUtils {
    public static ServerError parseError(Response<?> response) {
        Converter<ResponseBody, ServerError> converter =
                Client.retrofit.responseBodyConverter(ServerError.class, new Annotation[0]);
        ServerError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ServerError();
        }

        return error;
    }
}
