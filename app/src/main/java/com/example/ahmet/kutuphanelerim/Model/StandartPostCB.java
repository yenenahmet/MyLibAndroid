package com.example.ahmet.kutuphanelerim.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 02.08.2017.
 */

public class StandartPostCB {
    @SerializedName("Response")
    @Expose
    private int Response;

    public int getResponse() {
        return Response;
    }

    public void setResponse(int response) {
        Response = response;
    }
}
