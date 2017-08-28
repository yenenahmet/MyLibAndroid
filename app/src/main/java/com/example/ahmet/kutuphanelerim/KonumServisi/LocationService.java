package com.example.ahmet.kutuphanelerim.KonumServisi;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Ahmet on 02.08.2017.
 */

public class LocationService extends Service {
    private   Location locationGPS;
    private   Location locationNETWROK;
    private LocationManager locationManager;
    private LocationManager locationManagerNetwork;
    private long zaman = 1000 * 6;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GPSLog();
        NetworkLog();
    }

    private void GPSLog() {
        if (ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, zaman, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationGPS = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                if (ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, zaman, 0, this);
            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

    }
    private void NetworkLog() {
        if (ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
         locationManagerNetwork = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, zaman, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationNETWROK = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                if (ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, zaman, 0, this);
            }

            @Override
            public void onProviderDisabled(String provider) {
                try{  Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }catch (Exception ex){ }
            }
        });

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return Service.START_STICKY;
    }
    private  Location getLocation(){
        Location location = null;
        if(locationGPS != null){
            location = locationGPS;
        }else{
            if(locationNETWROK != null){
                location = locationNETWROK;
            }
        }
     return location;
    }

    public   double getLatitude(){
        Location location = getLocation();
        if(location != null){
            return location.getLatitude();
        }
        return 0;
    }
    public   double getLongitude(){
        Location location = getLocation();
        if(location != null){
            return location.getLongitude();
        }
        return 0;
    }

    public  void StopService(){
        stopService(new Intent(this,LocationService.class));
        locationManager.removeUpdates((LocationListener)this);
        locationManagerNetwork.removeUpdates((LocationListener)this);
    }


}
