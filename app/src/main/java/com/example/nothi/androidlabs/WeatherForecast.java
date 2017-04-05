package com.example.nothi.androidlabs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.example.nothi.androidlabs.Loginlab2Activity.ACTIVITY_NAME;

public class WeatherForecast extends AppCompatActivity {
    Button rouiBt;
    Button pttBt;
    Button siBt;
    Button saBt;
    ProgressBar progressBr;
    TextView currenttempView;
    TextView mintempView;
    TextView maxtempView;
    String urlW="http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

    //we need an Image View to set our thing

    ImageView outsidePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME,"ACTIVITY_NAME onCreate() works"); //activity name works here
        setContentView(R.layout.activity_weather_forecast);

        currenttempView = (TextView) findViewById(R.id.currenttemptext);
        mintempView = (TextView) findViewById(R.id.mintemptext);
        maxtempView = (TextView) findViewById(R.id.maxtemptext);
        progressBr=(ProgressBar) findViewById(R.id.progressBar);
        outsidePic = (ImageView) findViewById(R.id.imageView2);//we need to update
        //a Bitmap into it


        //let'rock


        ForecastQuery letsgo = new ForecastQuery();

        String url = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

        letsgo.execute(url);


    }//onCreate
    class ForecastQuery extends AsyncTask<String, Integer, String> {
        String minTempS="0";
        String maxTempS="0";
        String currentTempS="0";
        String parselatch="";//should we use String builder?


        String iconTaglatch="";
        Bitmap weatherPic;


        @Override
        protected void onPreExecute() {//on UI
            //could be a progress Bar, to indicate works is loading or being done
            //SHOW the progressbar
            progressBr.setVisibility(View.VISIBLE);
            currenttempView.setText("Starting AsyncTask in PreExecute...");
        }


        @Override
        protected void onPostExecute(String result) {//on UI
            //both pre and run have access to view in activity.
            //but actually progressbar is hidden on postExecute.



                currenttempView.setText(currenttempView.getText()+currentTempS+" C*");
                mintempView.setText( mintempView.getText()+minTempS+" C*");
                maxtempView.setText(maxtempView.getText()+maxTempS+" C*");


                //weatherPic.setImageBitmap(icon); need to get bitmap somwhow.
                outsidePic.setImageBitmap(weatherPic);// Bitmap weatherPic;

            progressBr.setVisibility(View.INVISIBLE);


            // Button rouiBt;
           // Button pttBt;
            //Button siBt;
           // Button saBt;
            //ProgressBar progressBr;
            //TextView currenttempView;
           // TextView mintempView;
           // TextView maxtempView;


        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            progressBr.setVisibility(View.VISIBLE);//is now Vis

            //researt to 0
            progressBr.setProgress(value[0]);

            Log.i(ACTIVITY_NAME, "We have updated");

        }


        @Override
        protected String doInBackground(String... params) {

            //can have publish progress
            //onPRogressUpdate()
            //if you USE .publishProgress()===> onProgressUpdate() runs.
            //this updates progressBar
            ////////////////////////////////
            //Post Execute runs when background complete.
            //called using execute();
            try {
                String urlString="http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 );
                conn.setConnectTimeout(15000 );
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();


                InputStream stream = conn.getInputStream();
                XmlPullParser parser = Xml.newPullParser();


                parser.setInput(stream, null);//need to make sure to catch exception
                //now parser reads in a bunch of text
                //text read in is parsed into string of text
                //can match with other string to check

                //TAGS temperature, humidity, pressure, wind speed



                //now read some file
                while (parser.next() != XmlPullParser.END_DOCUMENT) {//while true doesn't work

                    //skip if not start, no weird values
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;// it reads weird values if not start tag
                    }

                    // String minTempS="0";
                    //String maxTempS="0";
                    //String currentTempS="0";
                    //String parselatch="";//should we use String builder?
                    //Bitmap weatherPic;


                    ///<temperature value="-2" min="-2" max="-2" unit="metric"/>
                    //<humidity value="46" unit="%"/>
                    //<pressure value="1027" unit="hPa"/>

                    if (parser.getName().equals("temperature") ) {

                        ///<temperature value="-2" min="-2" max="-2" unit="metric"/>
                        currentTempS = parser.getAttributeValue(null, "value");
                        publishProgress(25);
                        minTempS = parser.getAttributeValue(null, "min");
                        publishProgress(50);
                        maxTempS = parser.getAttributeValue(null, "max");
                        publishProgress(75);
                    }

                    //<weather number="804" value="overcast clouds" icon="04n"/>
                    if (parser.getName().equals("weather")) {

                        iconTaglatch = parser.getAttributeValue(null, "icon");

                        //<weather number="804" value="overcast clouds" icon="04n"/>


                        String imageF = iconTaglatch+".png";
                        if(fileExistance(imageF))   {
                            //then it exists therefore no download
                            FileInputStream fis = null;
                            try {
                                //NOTE
                                //fis=new FileInputStream(imageF)//is wrong
                                //you need weird PATH
                                fis = new FileInputStream(getBaseContext().getFileStreamPath(imageF));   }



                            catch (FileNotFoundException e) {    e.printStackTrace();  }

                            //Bitmap weatherPic;
                            //decodes already exist file.
                            weatherPic = BitmapFactory.decodeStream(fis);
                            Log.i( ACTIVITY_NAME,"Hey already have image. in: "+ getBaseContext().getFileStreamPath(imageF));


                        }

                        else{
                        //Downloading if doesn't eexist
                        //Bitmap weatherPic;
                            iconTaglatch = parser.getAttributeValue(null, "icon");
                            URL iconUrl = new URL("http://openweathermap.org/img/w/" + iconTaglatch + ".png");



                        ////from the gotten Imagae

                        //Bitmap image  = HTTPUtils.getImage(ImageURL));
                        weatherPic = getImage(iconUrl);

                        //     outsidePic.setImageBitmap(weatherPic);// Bitmap weatherPic;
                        //You must build a Bitmap object, and then save it to the local storage.
                        // Once you have downloaded the image, call publishProgress() with 100
                        // as the parameter to show that the progress is completed.
                        // Save the Bitmap object to the local application storage with the following
                        // code:

                        //
                        FileOutputStream outputStream = openFileOutput(iconTaglatch + ".png",
                                Context.MODE_PRIVATE);


                            weatherPic.compress(Bitmap.CompressFormat.PNG, 80, outputStream);


                            outputStream.flush();
                            outputStream.close();

                        //publishProgress() with 100 ?????????
                            Log.i( ACTIVITY_NAME,"else doens't exist getting a picture and saving bitmap");
                            //I used lab2's ACTIVITY_NAME
                        }}

                        publishProgress(100); //publishProgress() with 100
                    }
                }



            catch  (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e){    e.printStackTrace();
            }
            {}

            //SystemClock.sleep(5000);
            return null; //return passed as a parameter onPostEx can return different numbers


            //bottomTView.setText("Updated via AsyncTask, this is PostExecute");
        }
    }//TaskweatherAsy

    public boolean fileExistance(String fName) {
        //Now, add code to check if your cloudy, sunny, raining images
        // are already present in the local storage directory:

        Log.i(ACTIVITY_NAME, "In fileExistance");

        Log.i(ACTIVITY_NAME, getBaseContext().getFileStreamPath(fName).toString());

       // cloudy, sunny, raining images
        File file = getBaseContext().getFileStreamPath(fName);//existing sun/rain image
        return file.exists();
    }

    public static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {//why 200?
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) { e.printStackTrace();//in get image
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }}

}//App WFC


