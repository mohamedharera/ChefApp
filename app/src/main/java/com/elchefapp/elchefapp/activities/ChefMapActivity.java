package com.elchefapp.elchefapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.Services.RetrofitInstanceChef;
import com.elchefapp.elchefapp.models.models_chef.chefProfile.ClassChefProfileResponse;
import com.elchefapp.elchefapp.models.models_chef.login_chef.ClassChefLoginData;
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
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChefMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    String MapApiToken = "1555512087Y9Vw8PVwWhCYC97YsQDv9Z80rHO7aQyGr3lHG6NRkKUREoVNhsbt62ai7s5V";
    Geocoder geocoder;
    List<Address> addresses;
    double latitude,longitude;
    boolean check = false;

    ClassChefLoginData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
        } else {
            showGPSDisabledAlertToUser();
        }


        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }



    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(latLng -> {

            mMap.clear();

            mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_marker)));
            latitude = latLng.latitude;
            longitude = latLng.longitude;

            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                Log.i("adddd", address + " " + city + " " + state + " " + country);
                String local = addresses.get(0).getAddressLine(0) + addresses.get(0).getLocality() +
                        addresses.get(0).getAdminArea() + addresses.get(0).getCountryName();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("adddd", e.toString());


            }
//            sendCrrnetloaction(latitude, longitude);

        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {

            Location userCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (userCurrentLocation != null) {
                MarkerOptions currentUserLocation = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_marker));
                mMap.setMyLocationEnabled(true);

                LatLng currentUserLatLang = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());
                latitude = currentUserLatLang.latitude;
                longitude = currentUserLatLang.longitude;

                double lat = latitude;
                double lng = longitude;
                callapi(lat,lng);
                geocoder = new Geocoder(this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();// Only if available else return NULL
                    Log.i("adddd", address + " " + city + " " + state + " " + country);
                    String loc = addresses.get(0).getAddressLine(0) + addresses.get(0).getLocality() +
                            addresses.get(0).getAdminArea() + addresses.get(0).getCountryName();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("adddd", e.toString());


                }


                currentUserLocation.position(currentUserLatLang);
                if (check == false) {
//                    sendCrrnetloaction(currentUserLatLang.latitude, currentUserLatLang.longitude);

                }
                mMap.addMarker(currentUserLocation);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, 16));


            }
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void callapi(double lat,double lng) {
        Call<ClassChefProfileResponse> addplace_moldeCall = RetrofitInstanceChef.getDataService().sendLocation(MapApiToken,lat,lng);
        addplace_moldeCall.enqueue(new Callback<ClassChefProfileResponse>() {
            @Override
            public void onResponse(Call<ClassChefProfileResponse> call, Response<ClassChefProfileResponse> response) {
                ClassChefProfileResponse response1 = response.body();
                if (response.isSuccessful()) {
                    Log.i("add_location", "Success");
                    Toast.makeText(ChefMapActivity.this, "" + response1.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("add_location", "Not Success " + new Gson().toJson(response));
                }
            }

            @Override
            public void onFailure(Call<ClassChefProfileResponse> call, Throwable t) {
                Log.i("add_location", "Failure " + new Gson().toJson(t));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onConnected(null);
        } else {
            Toast.makeText(ChefMapActivity.this, "No Permitions Granted", Toast.LENGTH_SHORT).show();
        }
    }
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}
