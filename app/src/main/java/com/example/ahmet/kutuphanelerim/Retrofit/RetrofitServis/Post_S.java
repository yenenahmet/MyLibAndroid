package com.example.ahmet.kutuphanelerim.Retrofit.RetrofitServis;

import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitModel.LoginModel;
import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitModel.StandartPostCB;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ahmet on 23.08.2017.
 */

public interface Post_S {
    @POST("/api/Satinalma/login")
    Call<StandartPostCB> LoginServis(@Body LoginModel loginModel );


}
