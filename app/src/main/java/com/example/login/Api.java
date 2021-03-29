package com.example.login;

import com.example.login.ModelResponse.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface Api {
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/v1/user/login")
    Call<LoginResponse> Login(
            @Body HashMap<String,String> fields
    );
}

