package com.example.nothi.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.nothi.androidlabs.R.string.Enter_email;
import static com.example.nothi.androidlabs.R.string.enter_pass;

public class Loginlab2Activity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "Loginlab2Activity";
    private Button loginB;
    private EditText emailInput;
    private EditText passInput;
    SharedPreferences sharedInfo;
    //first para, name of preference's file
    SharedPreferences.Editor editallInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "in onCreate()" );
        setContentView(R.layout.activity_loginlab2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         sharedInfo= getSharedPreferences("userInfoKey1", Context.MODE_PRIVATE);//only want this app to access info
        //first para, name of preference's file
         editallInfo= sharedInfo.edit();//edit into Info all the info

        loginB=(Button)findViewById(R.id.bLogin);


        emailInput=(EditText) findViewById(R.id.eTemail);//edit text email

        emailInput.setText(sharedInfo.getString("Emailkey", "default" ));
        passInput=(EditText) findViewById(R.id.eTpassword);//edit text pass
        loginB.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //need to overrid abstract.
                        //create obj intent class
                        saveInfo(v);
                        displayInfo(v);

                        String dfEmail= (String)  emailInput.getText().toString();
                        String dfPass= (String)   passInput.getText().toString();
                        Log.i("Email input is ", dfEmail );
                        Log.i("Passwd input is ", dfPass );

                        Intent loggedIn= new Intent("com.example.nothi.androidlabs.StartActivity");
                        //put activity package route
                        //now let's start
                        startActivity(loggedIn);//done



                    }
                                  }

        );




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);//I think
        //that's our button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
    public void saveInfo(View view){
        //takes view to get infoz

        // EditText emailInput=(EditText) findViewById(R.id.eTemail);//edit text email
        //EditText passInput=(EditText) findViewById(R.id.eTpassword);//edit text pass
        //
        editallInfo.putString("Emailkey", emailInput.getText().toString() );//from eTmail id. then put to string
        editallInfo.putString("Passwordkey", passInput.getText().toString() );//from eTmail id. then put to string
        editallInfo.commit();//this edits all info
        Toast.makeText(this, "user input successful!", Toast.LENGTH_LONG).show();

    }

    public void displayInfo(View view){
        SharedPreferences sharedInfo= getSharedPreferences("userInfoKey1", Context.MODE_PRIVATE);
        //userIKone accesses sharedInfo
       // SharedPreferences.Editor allInfo= sharedInfo.edit();//edit into Info all the info
        // EditText emailInput=(EditText) findViewById(R.id.eTemail);//edit text email
        //EditText passInput=(EditText) findViewById(R.id.eTpassword);//edit text pass
        //
        String dfEmail= (String) this.getResources().getText(R.string.Enter_email);
        String dfPass= (String) this.getResources().getText(R.string.enter_pass);
        /////access email and pass word by id.
        ///
        String dispEmail = sharedInfo.getString("Emailkey", dfEmail);//from eTmail id. then put to string
        String dispPasswd = sharedInfo.getString("Passwordkey", dfPass);//from eTmail id. then put to string
        //retrives shared info
        Toast.makeText(this, dispEmail, Toast.LENGTH_LONG).show();
        Toast.makeText(this, dispPasswd, Toast.LENGTH_LONG).show();




    }


}
