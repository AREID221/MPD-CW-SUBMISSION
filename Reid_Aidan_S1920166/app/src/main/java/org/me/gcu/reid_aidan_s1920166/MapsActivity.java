package org.me.gcu.reid_aidan_s1920166;

import androidx.fragment.app.FragmentActivity;

import android.app.Application;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Reid_Aidan_S1920166

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        LatLng coords = new LatLng(AppManager.latValue, AppManager.longValue);
//        mMap.addMarker(new MarkerOptions().position(coords).title(MainActivity.locInfo));
//        mMap.addMarker(new MarkerOptions().position(coords).title(MainActivity.locInfo));

        MarkerOptions customM = new MarkerOptions().position(coords).title(MainActivity.locInfo).icon(BitmapDescriptorFactory.defaultMarker(6 / AppManager.entryMagnitude.floatValue()));
        //customM.icon(BitmapDescriptorFactory.defaultMarker(MainActivity.tempMagEntry));
        mMap.addMarker(customM);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(coords));
    }
}