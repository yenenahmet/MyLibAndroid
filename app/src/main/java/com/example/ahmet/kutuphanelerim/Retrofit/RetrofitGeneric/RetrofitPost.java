package com.example.ahmet.kutuphanelerim.Retrofit.RetrofitGeneric;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.ahmet.kutuphanelerim.Kutuphanem.MainActivity;
import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitModel.LoginModel;
import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitServis.Post_S;
import com.example.ahmet.kutuphanelerim.Static.Statics;

/**
 * Created by Ahmet on 02.08.2017.
 */

public class RetrofitPost  {

        public static void Login(final String KullaniciAdi, final String Sifre, final Context context){
            final ProgressDialog progressDialog = Statics.ProgressRun(context,"Giriş Yapılıyor...");
            LoginModel model = new LoginModel(KullaniciAdi,Sifre);
            RetrofitS.getCallPushPost(RetrofitS.createServis(Post_S.class).LoginServis(model),progressDialog,context,false); // Başka İşlemler Yapıldığında Başarılı Yazılmak
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {                                                                  // istenirse Otomatik YAzılsın
                    if(RetrofitS.returnCall !=0){
                            // Sonuç Olumlu Döndükten Sonra Yapılması Gereken İşlemler
                        MainActivity.Login(context,KullaniciAdi,RetrofitS.returnCall);
                    }
                }
            });
        }
}
