package com.example.vaishaliarora.myapplication.activities;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.vaishaliarora.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener,GoogleMap.OnMapLongClickListener,GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private int mapType;
    private boolean isIndoor = false;
    private boolean isDrawCircleOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map);

        (findViewById(R.id.hybrid)).setOnClickListener(this);
        (findViewById(R.id.satellite)).setOnClickListener(this);
        (findViewById(R.id.terrain)).setOnClickListener(this);
        (findViewById(R.id.indoor)).setOnClickListener(this);
        (findViewById(R.id.circle)).setOnClickListener(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()          //Map Fragment works in API 18 or above but to work with lower end devices u have to use support map fragment instead of Map fragment
                                                                                //and also add the supported API.
                .findFragmentById(R.id.map);
        mapType = GoogleMap.MAP_TYPE_NORMAL;
        mapFragment.getMapAsync(this);
    }
    private LatLng sydney = new LatLng(-34, 151);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        MarkerOptions marker = new MarkerOptions();
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(marker.position(sydney).title("Marker in Sydney"));

        mMap.setMapType(mapType);

        if(isDrawCircleOn){
            drawCircle();
            return;
        }


        if(isIndoor) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(-33.86997, 151.2089), 18));
        } else {
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//            move camera to a particular position
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    sydney).zoom(12).build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);




    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.hybrid:
                mapType = GoogleMap.MAP_TYPE_HYBRID;
                isIndoor = false;
                isDrawCircleOn = false;
                mapFragment.getMapAsync(this);
                break;
            case R.id.satellite:
                mapType = GoogleMap.MAP_TYPE_SATELLITE;
                isIndoor = false;
                isDrawCircleOn = false;
                mapFragment.getMapAsync(this);
                break;
            case R.id.terrain:
                mapType = GoogleMap.MAP_TYPE_TERRAIN;
                isIndoor = false;
                isDrawCircleOn = false;
                mapFragment.getMapAsync(this);
                break;
            case R.id.indoor:
                isIndoor = true;
                isDrawCircleOn = false;
                mapFragment.getMapAsync(this);
                break;

            case R.id.circle:
                isDrawCircleOn = true;
                isIndoor = false;
                mapFragment.getMapAsync(this);
                break;
        }
    }

    private void drawCircle() {
        mMap.addCircle(new CircleOptions()
                .center(sydney)
                .radius(1000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onMapLongClick(LatLng point) {
        CircleOptions circleOptions = new CircleOptions()
                .center(point)   //set center
                .radius(1000)   //set radius in meters
                .fillColor(Color.RED)
                .strokeColor(Color.BLACK)
                .strokeWidth(5);

        mMap.addCircle(circleOptions);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        GroundOverlayOptions option = new GroundOverlayOptions();
        option.position(latLng , 30, 40);

        option.image(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources() , R.mipmap.ic_launcher)));
        mMap.addGroundOverlay(option);
    }
}
