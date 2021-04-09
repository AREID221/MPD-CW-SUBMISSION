package org.me.gcu.reid_aidan_s1920166;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

// Reid_Aidan_S1920166

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {



    BackgroundTasks backgroundTask;
    public Button pressedButton;
    public static Context mainActivityContext;
    public static Context getContext() { return mainActivityContext; }
    ArrayList<Double> tempLatList;
    public static Double latInfo;

    ArrayList<Double> tempLongList;
    public static Double longInfo;
    ArrayList<Double> tempMagList;
    public static Double magInfo;
    ArrayList<Integer> tempDepthList;
    public static Integer depthInfo;
    ArrayList<String> locationsList;
    public static String locInfo;
    ArrayList<String> datesList;
    public static String dateInfo;
    ArrayList<Integer> depthList;
    public static Integer tempMagEntry;

    public static Double highestMagnitude;
    public static Integer deepestDepth;
    public static Integer shallowDepth;
    public static Double maxLatitude;
    public static Double minLatitude;
    public static Double maxLongitude;
    public static Double minLongitude;
    public Boolean setFromDate;
    public Boolean setToDate;
    public Boolean setDateSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityContext = this;
        AppManager.bgs_RSSFeed = (ListView)findViewById(R.id.BGS_RSS);
        AppManager.titleTags = new ArrayList<String>();
        AppManager.descriptionTags = new ArrayList<String>();

        AppManager.latitudeList = new ArrayList<Double>();
        AppManager.longitudeList = new ArrayList<Double>();
        AppManager.entryMagnitude = 0.0;
        AppManager.magnitudeList = new ArrayList<Double>();
        AppManager.entryDepth = 0;
        AppManager.depthList = new ArrayList<Integer>();
        tempLatList = new ArrayList<Double>();
        tempLongList = new ArrayList<Double>();
        tempMagList = new ArrayList<Double>();
        tempDepthList = new ArrayList<Integer>();
        AppManager.dateFromSet = new String();
        AppManager.dateToSet = new String();
        AppManager.date = new ArrayList<String>();
        AppManager.time = new ArrayList<String>();
        AppManager.longitudeList = new ArrayList<Double>();
        AppManager.latitudeList = new ArrayList<Double>();
        AppManager.magnitudeList = new ArrayList<Double>();
        AppManager.depthList = new ArrayList<Integer>();
        AppManager.mostNorthernEntry = (TextView) findViewById(R.id.mNorthernEntry);
        AppManager.mostSouthernEntry = (TextView) findViewById(R.id.mSouthernEntry);
        AppManager.mostWesterlyEntry = (TextView) findViewById(R.id.mWesterlyEntry);
        AppManager.mostEasterlyEntry = (TextView) findViewById(R.id.mEasterlyEntry);
        AppManager.largestMagnitudeEntry = (TextView) findViewById(R.id.lMagnitudeEntry);
        AppManager.deepestDepthEntry = (TextView) findViewById(R.id.dDepthEntry);
        AppManager.shallowestDepthEntry = (TextView) findViewById(R.id.sDepthEntry);
        AppManager.latValue = 0.0;
        AppManager.longValue = 0.0;
        highestMagnitude = 0.0;
        deepestDepth = 0;
        shallowDepth = 0;
        minLatitude = 0.0;
        maxLatitude = 0.0;
        minLongitude = 0.0;
        maxLongitude = 0.0;
        latInfo = 0.0;
        longInfo = 0.0;
        magInfo = 0.0;
        depthInfo = 0;
        tempMagEntry = 0;
        locInfo = new String();
        dateInfo = new String();
        setFromDate = false;
        setToDate = false;
        setDateSuccess = false;
        AppManager.locationsArrayList = new ArrayList<String>();
        locationsList = new ArrayList<String>();
        datesList = new ArrayList<String>();
        depthList = new ArrayList<Integer>();
        pressedButton = new Button(this);
        backgroundTask = new BackgroundTasks();
        AppManager.mapsIntent = new Intent(MainActivity.this, MapsActivity.class);
        AppManager.tableIntent = new Intent(MainActivity.this, TableActivity.class);
        AppManager.infoCardIntent = new Intent(MainActivity.this, InfoActivity.class);
        AppManager.bgs_RSSFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                AppManager.latValue = Double.parseDouble(AppManager.latitudeList.get(position).toString());
                AppManager.longValue = Double.parseDouble(AppManager.longitudeList.get(position).toString());
                AppManager.entryMagnitude = AppManager.magnitudeList.get(position);
                dateInfo = datesList.get(position);
                locInfo = locationsList.get(position);
                latInfo = AppManager.latValue;
                longInfo = AppManager.longValue;
                depthInfo = depthList.get(position);
                magInfo = AppManager.magnitudeList.get(position);
                tempMagEntry = AppManager.magnitudeList.get(position).intValue();

                startActivity(AppManager.tableIntent);
            }
        });
        backgroundTask.execute();


        AppManager.links = new ArrayList<String>();
        AppManager.pubDates = new ArrayList<Date>();
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        if (pressedButton.getId() == findViewById(R.id.fromDateButton).getId())
        {
            setFromDate = false;

            AppManager.dateFromSet = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
            AppManager.dateToSet = AppManager.dateFromSet;
            setFromDate = true;
            if (AppManager.dateFromSet.length() < 11)
            {
                AppManager.dateFromSet = "0" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());

            }
            pressedButton.setText(AppManager.dateFromSet);
//            Button alterToBtn = findViewById(R.id.toDateButton);
//            alterToBtn.setText(AppManager.dateToSet);
        }
        else if (pressedButton.getId() == findViewById(R.id.toDateButton).getId())
        {
            setToDate = false;
            AppManager.dateToSet = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
            setToDate = true;
            if (AppManager.dateToSet.length() < 11)
            {
                AppManager.dateToSet = "0" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());

            }
            pressedButton.setText(AppManager.dateToSet);
        }
    }

    @Override
    public void onClick(View view)
    {
        Button buttonUsed = (Button) view;

        if (buttonUsed.getId() == findViewById(R.id.fromDateButton).getId())
        {
            DialogFragment fromDialog = new CalenderClass();
            fromDialog.show(getSupportFragmentManager(), "From");
            pressedButton = buttonUsed;

        }
        if (buttonUsed.getId() == findViewById(R.id.toDateButton).getId())
        {
            DialogFragment toDialog = new CalenderClass();
            toDialog.show(getSupportFragmentManager(), "To");
            pressedButton = buttonUsed;
        }
        if (buttonUsed.getId() == findViewById(R.id.setDateButton).getId())
        {
            if (setFromDate && setToDate)
            {
                try {
                    backgroundTask.PullFromDate(AppManager.dateFromSet, AppManager.dateToSet);

                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                if (setDateSuccess)
                {
                    Collections.sort(tempLatList);
                    Collections.sort(tempLongList);
                    Collections.sort(tempMagList);
                    Collections.sort(tempDepthList);

                    highestMagnitude = tempMagList.get(tempMagList.size() - 1);
                    shallowDepth = tempDepthList.get(0);
                    deepestDepth = tempDepthList.get(tempDepthList.size() - 1);
                    minLatitude = tempLatList.get(0);
                    maxLatitude = tempLatList.get(tempLatList.size() - 1);
                    minLongitude = tempLongList.get(0);
                    maxLongitude = tempLongList.get(tempLongList.size() - 1);
                    pressedButton = buttonUsed;


                    startActivity(AppManager.infoCardIntent);
                }else
                {
                    String latestDate = datesList.get(datesList.size() - 1);

                    Toast.makeText(MainActivity.getContext(),
                            "ERROR: Invalid Date given! Please enter date on or before" + latestDate,
                            Toast.LENGTH_SHORT).show();
                }


            }

//            setFromDate = false;
//            setToDate = false;
        }
    }

    public InputStream streamInputData(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public class BackgroundTasks extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog progressDialogs = new ProgressDialog(MainActivity.getContext());
        Exception backgroundProcessException = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialogs.setMessage("Busy loading RSS feed. Please wait for sync...");
            progressDialogs.show();
        }

        public void PullFromDate(String dateFrom, String dateTo) throws ParseException
        {
            setDateSuccess = false;
            Calendar dateFmt = Calendar.getInstance();
            Date fromDate = DateFormat.getDateInstance().parse(dateFrom);
            Date toDate = DateFormat.getDateInstance().parse(dateTo);
            dateFmt.setTime(fromDate);
            tempLatList = new ArrayList<Double>();
            tempLongList= new ArrayList<Double>();
            tempMagList= new ArrayList<Double>();
            tempDepthList= new ArrayList<Integer>();

            for (int j = 0; j < AppManager.descriptionTags.size(); j++)
            {
                //setDateSuccess = false;
                String splitDes = datesList.get(j);
                String[] splitDesc = splitDes.split(",", 1);
                String[] splitDesc1 = splitDesc.clone()[0].split(" ", 4);
                String splitDesc2 = splitDesc1.clone()[2] + " " + splitDesc1.clone()[3];
                String[] splitDesc3 = splitDesc2.split(" ", 4);
                String splitDesc4 =  splitDesc3.clone()[1] + " " + splitDesc3.clone()[0] + ", " +  " " + splitDesc3.clone()[2];

                if (splitDesc4.length() > 10)
                {
                    Date getDate = DateFormat.getDateInstance().parse(splitDesc4);
                    if (getDate.getTime() >= fromDate.getTime() && getDate.getTime() <= toDate.getTime())
                    {
                        tempLatList.add(AppManager.latitudeList.get(j));
                        tempLongList.add(AppManager.longitudeList.get(j));
                        tempMagList.add(AppManager.magnitudeList.get(j));
                        tempDepthList.add(depthList.get(j));
                        setDateSuccess = true;

                    }
                }
                else
                {


                }
            }




        }


        @Override
        protected Exception doInBackground(Integer... integers) {
            try
            {
                URL feedUrl = new URL("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false);

                XmlPullParser pullParser = factory.newPullParser();
                pullParser.setInput(streamInputData(feedUrl), "UTF_8");

                boolean insideItemTag = false;

                int tagType = pullParser.getEventType();

                while(tagType != XmlPullParser.END_DOCUMENT)
                {
                    if (tagType == XmlPullParser.START_TAG)
                    {
                        if (pullParser.getName().equalsIgnoreCase("item"))
                        {
                            insideItemTag = true;
                        }
                        else if (pullParser.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItemTag)
                            {
                                AppManager.titleTags.add(pullParser.nextText());
                            }
                        }
                        else if (pullParser.getName().equalsIgnoreCase("description"))
                        {
                            if (insideItemTag)
                            {
                                AppManager.descriptionTags.add(pullParser.nextText());
                            }
                        }
                        else if (pullParser.getName().equalsIgnoreCase("link"))
                        {
                            if (insideItemTag)
                            {
                                AppManager.links.add(pullParser.nextText());
                            }
                        }
//                        else if (pullParser.getName().equalsIgnoreCase("pubDate"))
//                        {
//                            if (insideItemTag)
//                            {
//                                ApplicationClass.pubDates.add(Date.parse()pullParser.nextText());
//                            }
//                        }
                        else if (pullParser.getName().equalsIgnoreCase("geo:lat"))
                        {
                            if (insideItemTag)
                            {
                                AppManager.latitudeList.add(Double.parseDouble(pullParser.nextText()));
                            }
                        }
                        else if (pullParser.getName().equalsIgnoreCase("geo:long"))
                        {
                            if (insideItemTag)
                            {
                                AppManager.longitudeList.add(Double.parseDouble(pullParser.nextText()));
                            }
                        }

                    }
                    else if (tagType == XmlPullParser.END_TAG && pullParser.getName().equalsIgnoreCase("item"))
                    {
                        insideItemTag = false;
                    }

                    tagType = pullParser.next();
                }
            }
            catch (MalformedURLException e)
            {
                backgroundProcessException = e;
            }
            catch (XmlPullParserException e)
            {
                backgroundProcessException = e;
            }
            catch (IOException e)
            {
                backgroundProcessException = e;
            }

            return backgroundProcessException;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            for (int j = 0; j < AppManager.titleTags.size(); j++)
            {
                String currentDescTag = AppManager.descriptionTags.get(j);
                String[] locationSplit0 = currentDescTag.split(":", 6);
                String[] locationSplit1 = currentDescTag.split(":", 2);
                String[] locationSplit2 = locationSplit1.clone()[1].split(";", 0);
                String[] locationSplit3 = locationSplit0.clone()[4].split(";", 0);

                String dateSplit0 = locationSplit2.clone()[0];

                String[] eqValues = locationSplit0.clone()[5].split(":", 3);
                String magValue = eqValues.clone()[2];
                magValue.trim();

                String depthValue = eqValues.clone()[1];
                String[] depthSplit0 = depthValue.split("k", 0);
                depthSplit0.clone()[0].trim();
                String depthValue1 = depthSplit0.clone()[0];
                String sub = depthValue1.substring(1, depthValue1.length() - 1);
                Integer toInt = Integer.parseInt(sub);
                AppManager.entryDepth = toInt;
                Double toDub = Double.parseDouble(magValue);

                //toDub /= 10;

                //AppManager.entryMagnitude = toDub;
                String outputLocation = locationSplit3.clone()[0] + " M: " + toDub;
                datesList.add(dateSplit0);
                locationsList.add(locationSplit3.clone()[0]);
                depthList.add(AppManager.entryDepth);
                AppManager.magnitudeList.add(AppManager.GetMagnitude(toDub));
                AppManager.locationsArrayList.add(outputLocation);
            }

            Collections.sort(datesList);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, AppManager.locationsArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    String mag = AppManager.magnitudeList.get(position).toString();
                    String charsToString1 = new String(String.valueOf(mag.charAt(0)) + String.valueOf(mag.charAt(2)));
                    Double stringToInt1 = Double.parseDouble(charsToString1);
                    //AppManager.magnitudeList.add(AppManager.GetMagnitude(stringToInt1));
                    view.setBackgroundColor(0xffffff00 * (8 * AppManager.GetMagnitude(stringToInt1).intValue()));
                    return view;
                }
            };
            AppManager.bgs_RSSFeed.setAdapter(adapter);
            progressDialogs.dismiss();
        }
    }
}