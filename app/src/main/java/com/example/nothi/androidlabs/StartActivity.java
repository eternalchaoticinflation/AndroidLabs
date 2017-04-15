package com.example.nothi.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    private Button listB;
    private Button weatherForcastBt;
   // private EditText emailInput;
    //private EditText passInput;
    private Button toolbarb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "in onCreate()" );
        setContentView(R.layout.activity_start);


        ///
        listB=(Button)findViewById(R.id.startListbutton);
        Button chatB=(Button)findViewById(R.id.startChatbt);
        weatherForcastBt=(Button) findViewById(R.id.weatherforcastBt);
        toolbarb=(Button)this.findViewById(R.id.toolbarbutton);


        //emailInput=(EditText) findViewById(R.id.eTemail);//edit text email
       // passInput=(EditText) findViewById(R.id.eTpassword);//edit text pass
        listB.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //need to overrid abstract.
                        //create obj intent class
                      //  String dfEmail= (String)  emailInput.getText().toString();
                      //  String dfPass= (String)   passInput.getText().toString();
                      //  Log.i("Email input is ", dfEmail );
                     //   Log.i("Passwd input is ", dfPass );
                        // Intent listIn= new Intent("com.example.nothi.androidlabs.StartActivity");
                        //^old one
                        //new one tools:context="com.example.nothi.androidlabs.ListItemsActivity"
                       // com.example.nothi.androidlabs.ListItemsActivity);
                        Intent listIn= new Intent("com.example.nothi.androidlabs.ListItemsActivity");
                        startActivityForResult(listIn, 5);
                        //Intent listIn= new Intent("com.example.nothi.androidlabs.ListItemsActivity");
                        //put activity package route
                        //now let's start the LISTITEMS ACT
                        //startActivity(listIn);//done

                    }
                }

        );


        chatB.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "User Clicked Start Chat" );
                        Intent chatIn= new Intent("com.example.nothi.androidlabs.ChatWindow");
                        startActivity(chatIn);



                    }
                }

        );
        weatherForcastBt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "WeatherForcast is loading..." );
                        Intent findweather= new Intent("com.example.nothi.androidlabs.WeatherForecast");
                        startActivity(findweather);



                    }
                }
        );
        toolbarb.setOnClickListener(//starts Act for toolbar
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i(ACTIVITY_NAME, "Testing ToolBar" );
                        Intent testtoolbar= new Intent("com.example.nothi.androidlabs.TestToolbar");
                        startActivity(testtoolbar);



                    }
                }

        );



    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "in onResume()" );
    }
    @Override
    protected void onStart(){
        Log.i(ACTIVITY_NAME, "in onStart()" );
        super.onStart();
    }
    @Override
    protected void onPause(){
        Log.i(ACTIVITY_NAME, "in onPause()" );
        super.onPause();
    }
    @Override
    protected void onStop(){
        Log.i(ACTIVITY_NAME, "in onStop()" );

        super.onStop();
    }
    @Override
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "in onDestroy()" );
        super.onDestroy();
    }
    @Override
    protected void  onActivityResult(int requestCode, int responseCode, Intent data){

        //startActivityForResult(listIn, 5); triggered by
        // will input onActivityResult(5, RESULT_OK or RESULT_CANCELLED, listIn)
        //will be inputed after we have returned to startactivity
       //will log like onResume(), or onStop();
        if (requestCode==5){
            //could start activie or flag error for now
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
            if (responseCode==RESULT_OK){
                String messagePassed=data.getStringExtra("Responsekey");//data is the Intent.
                String resultOK=" Also the valueOf RESULT_OK is: "+String.valueOf(RESULT_OK);
                Toast.makeText(this, messagePassed+resultOK, Toast.LENGTH_LONG).show();

            }

        }


    }

}
