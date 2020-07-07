package com.devapp.demoapplication.Interface;

import com.devapp.demoapplication.model.Datum;
import com.devapp.demoapplication.model.GettingData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content_Type:application/json")
    @GET("employees")
    Call<GettingData>getList();


}
