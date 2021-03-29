package com.example.login;

import retrofit2.Response;

public class ApiError {
    private int timestamp;
    private int status;
    private String message="Unkown error";
    public int getTimestamp(){
        return timestamp;
    }
    public int getStatus()
    {
        return status;
    }
    public String getMessage()
    {

        return message;
    }



}
