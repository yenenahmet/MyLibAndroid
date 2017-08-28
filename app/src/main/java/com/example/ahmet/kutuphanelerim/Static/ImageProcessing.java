package com.example.ahmet.kutuphanelerim.Static;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.ahmet.kutuphanelerim.Retrofit.RetrofitGeneric.RetrofitS;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.http.Url;

/**
 * Created by Ahmet on 28.08.2017.
 */

public class ImageProcessing {


    public static void ImgSave(Context context, String url){
        String dirName = Environment.getExternalStorageDirectory().toString() + "/MyApp/images";
        final File storageDir = new File(dirName);
        // Create directory
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        String MY_IMAGE_URL = RetrofitS.URL_IMAGES+url;

        final ImageView profile = new ImageView(context);
        final String img_path = storageDir.getAbsolutePath() + "/" + url;

        Picasso.with(context).load(MY_IMAGE_URL.toString()).into(profile, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("onsucces =","yeap");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = ((BitmapDrawable)profile.getDrawable()).getBitmap();
                        File file = new File(img_path);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("Error File",e.toString());

                        }
                    }
                },100);
            }
            @Override
            public void onError( ) {
                Log.e("ERROR","Error Image");
            }
        });
    }

    public static Bitmap ResmiAl(String ResimAdi){
        String dirName = Environment.getExternalStorageDirectory().toString() + "/MyApp/images";
        final File storageDir = new File(dirName);
        final String img_path = storageDir.getAbsolutePath();
        Bitmap b = null;
        try {
            File f=new File(img_path,ResimAdi);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            Log.e("Hata =",e.toString());
        }
        return b;
    }
}
