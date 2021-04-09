package org.me.gcu.reid_aidan_s1920166;

import android.app.Application;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Reid_Aidan_S1920166

public class AppManager extends Application
{
    public static ListView bgs_RSSFeed;
    public static Intent mapsIntent;
    public static Intent tableIntent;
    public static Intent infoCardIntent;
    public static ArrayList<String> date;
    public static ArrayList<String> time;
    public static Button toMapsButton;
    public static ArrayList<String> titleTags;
    public static ArrayList<String> descriptionTags;
    public static ArrayList<String> links;
    public static ArrayList<Date> pubDates;
    public static Double latValue;
    public static Double longValue;
    public static ArrayList<Double> latitudeList;
    public static ArrayList<Double> longitudeList;
    public static Double entryMagnitude;
    public static List<Double> magnitudeList;
    public static Integer entryDepth;
    public static ArrayList<Integer> depthList;
    public static String dateFromSet;
    public static String dateToSet;
    public static TextView mostNorthernEntry;
    public static TextView mostSouthernEntry;
    public static TextView mostWesterlyEntry;
    public static TextView mostEasterlyEntry;
    public static TextView largestMagnitudeEntry;
    public static TextView deepestDepthEntry;
    public static TextView shallowestDepthEntry;
    public static ArrayList<String> locationsArrayList;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static Double GetMagnitude(double in)
    {
        return entryMagnitude = in;
    }
}
