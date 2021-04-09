package org.me.gcu.reid_aidan_s1920166;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Reid_Aidan_S1920166

public class InfoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //mag.setText("HI");
        AppManager.mostNorthernEntry = (TextView) findViewById(R.id.mNorthernEntry);
        AppManager.mostSouthernEntry = (TextView) findViewById(R.id.mSouthernEntry);
        AppManager.mostEasterlyEntry = (TextView) findViewById(R.id.mEasterlyEntry);
        AppManager.mostWesterlyEntry = (TextView) findViewById(R.id.mWesterlyEntry);
        AppManager.largestMagnitudeEntry = (TextView) findViewById(R.id.lMagnitudeEntry);
        AppManager.deepestDepthEntry = (TextView) findViewById(R.id.dDepthEntry);
        AppManager.shallowestDepthEntry = (TextView) findViewById(R.id.sDepthEntry);

        AppManager.mostNorthernEntry.setText((MainActivity.maxLongitude.toString()));
        AppManager.mostSouthernEntry.setText((MainActivity.minLongitude.toString()));
        AppManager.mostEasterlyEntry.setText((MainActivity.minLatitude.toString()));
        AppManager.mostWesterlyEntry.setText((MainActivity.maxLatitude.toString()));
        AppManager.largestMagnitudeEntry.setText(MainActivity.highestMagnitude.toString());
        AppManager.deepestDepthEntry.setText((MainActivity.deepestDepth.toString()));
        AppManager.shallowestDepthEntry.setText((MainActivity.shallowDepth.toString()));
    }
}
