package com.example.vaishaliarora.myapplication.model;

/**
 * Created by vaishaliarora on 16/05/16.
 */
import com.google.android.gms.maps.model.LatLng;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

}