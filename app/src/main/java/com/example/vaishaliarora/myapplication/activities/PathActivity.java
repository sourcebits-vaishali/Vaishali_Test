package com.example.vaishaliarora.myapplication.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.helper.HttpConnection;
import com.example.vaishaliarora.myapplication.helper.PathJSONParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class PathActivity extends FragmentActivity implements OnMapReadyCallback, Listener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private static final LatLng INDIA = new LatLng(20.5937, 78.9629);

    ArrayList<LatLng> markerPoints;
    public static final float DEFAULT_ZOOM_LEVEL = 3.0f;

    ArrayList<LatLng> points = null;
    PolylineOptions lineOptions = null;

    private final String TAG = "PathGoogleMapActivity";
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_activity);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.path_map);
        markerPoints = new ArrayList<>();
//        fm.getMapAsync(this);

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //Moving to a sample location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.5937, 78.9629), DEFAULT_ZOOM_LEVEL));

        // Setting onclick event listener for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    mMap.clear();
                }

                // Adding new point to the ArrayList
                markerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /**
                 * For the start location, Marker color is GREEN,
                 * for the end location, Marker color is MAGENTA.
                 */
                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                }

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = HttpConnection.getDirectionsUrl(origin, dest);
                    GetDirections getDirections = new GetDirections(PathActivity.this);
                    getDirections.startGettingDirections(url);
                }

                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);
            }
        });

    }


    //The task for getting directions ends up here...
    @Override
    public void onSuccessfullRouteFetch(final List<List<HashMap<String, String>>> result) {

        //if it takes a long time, we will do it in a seperate thread...
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Traversing through all the routes
                for (List<HashMap<String, String>> path : result) {
                    points = new ArrayList<>();
                    lineOptions = new PolylineOptions();

                    // Get all the points for this route
                    for (HashMap<String, String> point : path) {
                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);
                        points.add(position);
                    }
                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(12);
                    lineOptions.color(Color.RED);
                }
                //Do all UI operations on the UI thread only...
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Drawing polyline in the Google Map for the this route
                        mMap.addPolyline(lineOptions);
                    }
                });

            }
        }).start();

    }

    @Override
    public void onFail() {
        Log.i(TAG, "Failed to get directions from Google...");
    }

   /* @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Toast.makeText(this ,"Current location\n LAT  == "+location.getLatitude()+",LNG == "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        Geocoder geoCoder = new Geocoder(this , Locale.getDefault());
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
            if(address.size()>1){
                Toast.makeText(PathActivity.this , "LOcation name == == "+address.get(0).getLocality(), Toast.LENGTH_LONG).show();
            }
        }catch (IOException exception){
            Log.d(TAG , exception.toString());
        }
    }*/

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            double lat = mLastLocation.getLatitude();
            double lng = mLastLocation.getLongitude();
            Toast.makeText(this ,"Current location\n LAT  == "+mLastLocation.getLatitude()+",LNG == "+mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();
            Log.d("VAISHALI##","Current location\n" +
                    " LAT  == " +mLastLocation.getLatitude() +",LNG == "+mLastLocation.getLongitude());
            Geocoder geoCoder = new Geocoder(this , Locale.getDefault());
            try {
                List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
                if(address.size()>1){
                    Toast.makeText(PathActivity.this , "LOcation name == == "+address.get(0).getLocality(), Toast.LENGTH_LONG).show();
                    Log.d("VAISHALI##","LOCATOIN NAME == "+address.get(0).getLocality());
                }
            }catch (IOException exception){
                Log.d(TAG , exception.toString());
            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(PathActivity.this , "Connection suspended", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(PathActivity.this , "Connection failed", Toast.LENGTH_LONG).show();
    }



    public static class GetDirections {

        private Listener listener;
        private final String TAG = GetDirections.class.getSimpleName();

        public GetDirections(Listener listener) {
            this.listener = listener;
        }

        public void startGettingDirections(String downloadUrl) {
            new DirectionsTask().execute(downloadUrl);
        }

        class DirectionsTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... url) {

                String data = null;
                try {
                    data = HttpConnection.getDataFromUrl(url[0]);
                } catch (Exception e) {
                    Log.d("Background Task", e.toString());
                }
                return data;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                ParserTask parserTask = new ParserTask();
                parserTask.execute(result);
            }
        }

        /**
         * A class to parse the Google Places in JSON format
         */
        private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

            @Override
            protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try {
                    jObject = new JSONObject(jsonData[0]);
                    PathJSONParser parser = new PathJSONParser();
                    routes = parser.parse(jObject);
                } catch (JSONException e) {
                    listener.onFail();
                    Log.d(TAG, e.toString());
                }
                return routes;
            }

            @Override
            protected void onPostExecute(List<List<HashMap<String, String>>> result) {
                super.onPostExecute(result);
                if (result != null && result.size() > 0)
                    listener.onSuccessfullRouteFetch(result);
                else
                    listener.onFail();
            }
        }

    }
}
