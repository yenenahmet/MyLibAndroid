package com.example.ahmet.kutuphanelerim.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 02.08.2017.
 */

public class LoginModel {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Sifre")
    @Expose
    private String Sifre;

    public LoginModel(String email,String sifre){
        this.email = email;
        this.Sifre = sifre;
    }
}
