package com.example.login;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class ErrorsUtils {
    public static ApiError parseError(Response<?> response){
        Converter<ResponseBody, ApiError> converter=RetrofitClient.retrofit.responseBodyConverter(ApiError.class,new Annotation[0]);
ApiError error;
try {
    assert response.errorBody() != null;
    error = converter.convert(response.errorBody());


} catch (IOException e)
{
    return new ApiError();
}
return error;
    }
}
