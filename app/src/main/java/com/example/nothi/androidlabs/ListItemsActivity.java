package com.example.nothi.androidlabs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    private ImageButton imageB; //imageButtonone
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int latch=0;
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "in onCreate()" );
        setContentView(R.layout.activity_list_items);
        //imageButtonone
        imageB= (ImageButton) findViewById(R.id.imageButtonone);// it's is called
        //image buttion
        imageB.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //need to overrid abstract.
                        //create obj intent class
                        //String dfEmail= (String)  emailInput.getText().toString();
                       // String dfPass= (String)   passInput.getText().toString();
                       // Log.i("Email input is ", dfEmail );
                       // Log.i("Passwd input is ", dfPass );
                        //Intent loggedIn= new Intent("com.example.nothi.androidlabs.StartActivity");
                        //put activity package route
                        //now let's start
                        dispatchTakePictureIntent();
                           // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                           // if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                          //      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                          //  }
                        //onActivityResult(1,RESULT_OK, takePictureIntent );
                        //sent to  protected void onActivityResult(int requestCode, int resultCode, Intent data)
                        //startActivity(loggedIn);//done
                    }
                }
        );
        Switch swT = (Switch) findViewById(R.id.switchToast);
        swT.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        latch=latch+1;//at first 1%2 !=0, if 1, goes in 2,
                            if (latch==3){ //set it back to 1
                                latch=1;}
                                setOnCheckedChanged();
                    }
                }
        );

        CheckBox chBox = (CheckBox) findViewById(R.id.checkBox);
        chBox.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                        // 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml
                                .setTitle(R.string.dialog_title)
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent resultIntent = new Intent(  );
                                        resultIntent.putExtra("Responsekey", "ListItemActivity Passed using StringKey: " +
                                                "My info long lab");
                                        setResult(Activity.RESULT_OK, resultIntent);
                                        finish(); // User clicked OK button
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                        onResume();
                                    }
                                })
                                .show();

                    }
                }
        );
    }

    @Override
    protected void onResume(){
        Log.i(ACTIVITY_NAME, "in onResume()" );
        super.onResume();
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
        Intent finishbackS= new Intent("com.example.nothi.androidlabs.StartActivity");
        startActivity(finishbackS);//done
        super.onDestroy();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageB.setImageBitmap(imageBitmap);
        }

    }

    // Intent loggedIn= new Intent("com.example.nothi.androidlabs.StartActivity");

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    private void setOnCheckedChanged(){


        if ((latch%2)==1) {

            CharSequence text = "Switch is On";

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this , text, duration);
            toast.show();
        }
        else {
            CharSequence text = "Switch is Off!!!"; // "Switch is Off"
            int duration = Toast.LENGTH_LONG; //= Toast.LENGTH_LONG if Off

            Toast toast = Toast.makeText(this, text, duration); //this is the ListActivity
            toast.show();
        }
       //


    }



}
