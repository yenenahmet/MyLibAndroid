package com.example.ahmet.kutuphanelerim.Retrofit.RetrofitGeneric;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitModel.StandartPostCB;
import com.example.ahmet.kutuphanelerim.Static.Statics;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmet on 02.08.2017.
 */

public final class RetrofitS  {
    private static final String URL ="http://localhost:55529";
    public static final String URL_IMAGES = "http://192.168.43.201:84/Resim/";
    public static int returnCall =0;

    private  static OkHttpClient isClient(final Context context){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", Statics.getPrefString(context,"Token"))
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        return client;
    }

    public static <S> S createServisToken(Class<S> sClass,Context context){
        Retrofit retrofit = new Retrofit.Builder()
                 .client(isClient(context))
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(sClass);
    }

    public static <S> S createServis(Class<S> sClass){

        Retrofit retrofit = new Retrofit.Builder()
             //   .client(client) // Token olması Durumunda  Burda Direk Eklenebilir Veya // inteface @Headers Olarakta Eklebilir Fakat Burda Daha Az Kod kalabalığı Yaparak Eklenebilir
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(sClass);
    }


    public static  void getCallPushPost(Call<StandartPostCB> call , final ProgressDialog progressDialog, final Context context,final  Boolean SuccesToast) { // 0 ve 1 dönüşler için
        call.enqueue(new Callback<StandartPostCB>() {
            @Override
            public void onResponse(Call<StandartPostCB> call, Response<StandartPostCB> response) {
                returnCall = 0;
                    if(response.isSuccessful()){
                        if(response.body().getResponse() !=0){
                            if(SuccesToast){
                                Statics.Toast(context,"İşlem Başarıyla Tamamlandı");
                            }
                            returnCall = response.body().getResponse();
                        }else {
                            Statics.Toast(context,"Hata Oluştu !!");
                        }
                    }else{
                        Statics.Toast(context,"Hata Oluştu !");
                    }
                    progressDialog.cancel();
            }
            @Override
            public void onFailure(Call<StandartPostCB> call, Throwable t)
            {
                returnCall = 0;
                progressDialog.cancel();
                Statics.Toast(context,"İnternet Bağlantınızda Sorun Var !");
            }

        });
    }




}
