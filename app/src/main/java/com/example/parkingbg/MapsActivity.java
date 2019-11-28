package com.example.parkingbg;

import androidx.annotation.NonNull;
import android.widget.SearchView;
//import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SearchView searchView;


    private final LatLng mDefaultLocation = new LatLng(43.642567, -79.387054);
    //default zoom to value 15
    private final static int DEFAULT_ZOOM = 15;

    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private static final String TAG = "MapsActivity";

    //used for accessing device location
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                geoLocate();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.addMarker(new MarkerOptions().position(mDefaultLocation).title("CN Tower, Toronto")).showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));

        //get the location permission
        this.getLocationPermission();

        //turn on my location layer on the map
        this.updateLocationUI();

        //start getting current location of the device and show it on map
        this.getDeviceLocation();


    }

    private void getLocationPermission(){
        //check if for the current app permission for accessing fine location is granted
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocationPermissionGranted = true;
        }else{
            //request for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void updateLocationUI(){
        //update the location on map
        if (mMap == null){
            return;
        }

        //enable the map features
        try{
            if (mLocationPermissionGranted){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }else{
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        }catch (SecurityException e){
            Log.e(TAG + " Exception : ", e.getMessage());
        }
    }

    private void getDeviceLocation(){
        //get current location of the device

        try{
            if(mLocationPermissionGranted){
                Task<Location> locationResult =
                        mFusedLocationProviderClient.getLastLocation();

                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        //check if fetching the last location was successful or not
                        if (task.isSuccessful()){
                            //means we got the last location and now we set it on our map and
                            // then move the camera accordingly

                            mLastKnownLocation = task.getResult();

                            LatLng currentLocation = new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude());

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions()
                                    .position(currentLocation)
                                    .title("You are here!"))
                                    .showInfoWindow();

                            Log.e(TAG, "Lat : " + currentLocation.latitude +
                                    " Lng: " + currentLocation.longitude);
                        }else{
                            //task is unsuccessful in fetching the last location
                            // so now we display the default location to user
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions()
                                    .position(mDefaultLocation)
                                    .title("You are here!"))
                                    .showInfoWindow();

                            Log.e(TAG, "Lat : " + mDefaultLocation.latitude +
                                    " Lng: " + mDefaultLocation.longitude);

                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                            mMap.getUiSettings().setZoomControlsEnabled(true);
                        }
                    }
                });
            }
        }catch(SecurityException e){
            Log.e(TAG + "Exception", e.getMessage());
        }
    }


    private void geoLocate(){
        String searchString = searchView.getQuery().toString();
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> addressList = new ArrayList<>();
        try{
            addressList = geocoder.getFromLocationName(searchString, 1);
        }catch (Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }

        Log.e(TAG, addressList.toString());

        if (addressList.size()>0){
            Address foundAddress = addressList.get(0);
            foundAddress.getCountryName();
            foundAddress.getSubAdminArea();
            foundAddress.getThoroughfare();

            LatLng foundLocation = new LatLng(foundAddress.getLatitude(), foundAddress.getLongitude());

            mMap.addMarker(new MarkerOptions().position(foundLocation)).showInfoWindow();
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foundLocation, 15));
        }
    }


}
