package org.me.gcu.reid_aidan_s1920166;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Reid_Aidan_S1920166

public class TableActivity extends FragmentActivity
{
    private GoogleMap mMapTable;

    TextView dateTimeTitle;
    TextView dateTimeEntry;
    TextView locationTitle;
    TextView locationEntry;
    TextView latTitle;
    TextView latEntry;
    TextView longTitle;
    TextView longEntry;
    TextView depthTitle;
    TextView depthEntry;
    TextView magnitudeTitle;
    TextView magnitudeEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        //AppManager.infoTable = (TableLayout) findViewById(R.id.infoTable);
        AppManager.toMapsButton = (Button) findViewById(R.id.googleMapsButton);
        dateTimeEntry = (TextView)  findViewById(R.id.dateTimeEntry);
        locationEntry = (TextView)  findViewById(R.id.locationEntry);
        latEntry = (TextView)  findViewById(R.id.latitudeEntry);
        longEntry = (TextView)  findViewById(R.id.longitudeEntry);
        depthEntry = (TextView)  findViewById(R.id.depthEntry);
        magnitudeEntry = (TextView)  findViewById(R.id.magnitudeEntry);

        dateTimeEntry.setText(MainActivity.dateInfo);
        locationEntry.setText(MainActivity.locInfo);
        latEntry.setText(MainActivity.latInfo.toString());
        longEntry.setText(MainActivity.longInfo.toString());
        magnitudeEntry.setText(MainActivity.magInfo.toString());
        depthEntry.setText(MainActivity.depthInfo.toString());


        AppManager.toMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AppManager.mapsIntent);
            }
        });
    }
}
