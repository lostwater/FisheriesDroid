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
public class LoginErrorUtils {
    public static LoginError parseError(Response<?> response) {
        Converter<ResponseBody, LoginError> converter =
                Client.retrofit.responseBodyConverter(LoginError.class, new Annotation[0]);
        LoginError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new LoginError();
        }

        return error;
    }
}
