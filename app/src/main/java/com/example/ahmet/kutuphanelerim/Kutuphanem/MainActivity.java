package com.example.ahmet.kutuphanelerim.Kutuphanem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmet.kutuphanelerim.KonumServisi.LocationService;
import com.example.ahmet.kutuphanelerim.R;
import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitGeneric.RetrofitPost;
import com.example.ahmet.kutuphanelerim.Static.Statics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         // Her Hangi Bvir Login Button Servise Login :)
        RetrofitPost.Login("EdittextKullaniAdi","EdittextSifre",getApplicationContext());
    }
    public static void Login (Context context,String KullaniciAdi,int User){
        SharedPreferences.Editor editor = Statics.getEditor(context);
        editor.putString("KullaniciAdi",KullaniciAdi);
        editor.putInt("UserID",User);
        editor.commit();
        Statics.ThisCloseNewActivty(context,MainActivity.class);
    }


    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(getApplicationContext(), LocationService.class));
    }
}
