package com.example.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String Base_URL="https://apis.martcart.pk/";
    private static RetrofitClient retrofitClient;
    public static Retrofit retrofit;
    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public  static  synchronized RetrofitClient getInstance(){
        if(retrofitClient==null)
        {
          retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
    public Api getApi(){
        return  retrofit.create(Api.class);
    }
}
