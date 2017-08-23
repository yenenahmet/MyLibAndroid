package com.example.ahmet.kutuphanelerim.Static;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ahmet on 23.08.2017.
 */

public class Statics {
    public static boolean isTelNoDigit(String NO){
        boolean durum = true;
        for(int i=0 ; i<11;i++){
            if(!Character.isDigit(NO.charAt(i))){
                durum= false;
            }
        }
        return durum;
    }
    public static boolean isGPSEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    public static void gpsSettingsIntent(Context context){
        Toast(context,"Konum özelliği aktif etmeniz gerekiyor !");
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static ProgressDialog ProgressRun (Context context, String veri){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(veri);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public static void HideKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public static void ThisCloseNewActivty(Context context ,Class classs){
        Intent intent = new Intent(context, classs);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
    public static String toEditTrim(EditText veri){ // Edittexti Ayarla
        return veri.getText().toString().trim();
    }
    public static void Toast(Context context,String veri){
        Toast.makeText(context,veri,Toast.LENGTH_SHORT).show();
    }

    public static String getPrefString(Context context,String veri){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(veri,"");
    }
    public static SharedPreferences.Editor getEditor(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }
}
