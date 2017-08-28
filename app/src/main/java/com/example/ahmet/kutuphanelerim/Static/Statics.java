package com.example.ahmet.kutuphanelerim.Static;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by Ahmet on 23.08.2017.
 */

public class Statics {
    public static boolean isTelNoDigit(String NO){
        for(int i=0 ; i<11;i++){
            if(!Character.isDigit(NO.charAt(i))){
                return false;
            }
        }
        return true;
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
    public static void NewActivity(Context context,Class classs){
        Intent intent = new Intent(context,classs);
        context.startActivity(intent);
    }
    public static boolean isEditText(EditText editText){ // Edittexti Ayarla
        return editText.getText().toString().trim().isEmpty();
    }
    public static void Toast(Context context,String veri){
        Toast.makeText(context,veri,Toast.LENGTH_SHORT).show();
    }

    public static String getPrefString(Context context,String veri){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(veri,"");
    }
    public static int getPrefInt(Context context,String veri){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(veri,0);
    }
    public static SharedPreferences.Editor getEditor(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    public static String BitmapConvertString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public static void ScreenBarClear(Activity context){
        View decorView = context.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

}
