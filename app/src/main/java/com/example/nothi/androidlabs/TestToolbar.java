package com.example.nothi.androidlabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu m){

        //Kind of like
        // View customfragview=inflater.inflate(R.layout.activity_message_details, (ViewGroup)containerfromVG, false);
        this.getMenuInflater().inflate(R.menu.toolbar_menu, m );
        //this is my toolbar

        return true;



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mioption){
        //some menu item is clicked or selected
        //assign it to any id
        int menuoptionid= mioption.getItemId();

        switch( menuoptionid ){
            case R.id.action_folder:
                Log.d("Toolbar", "action_folder: selected");
                Toast.makeText(getApplicationContext(), "action_folder: selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_save:
                Log.d("Toolbar", "sction_save: selected");
                Toast.makeText(getApplicationContext(), "sction_save: selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_assistant:
                Toast.makeText(getApplicationContext(), "action_assistant: selected", Toast.LENGTH_LONG).show();
                Log.d("Toolbar", "action_assistant: selected");
                break;



        }

    return false;
    }

}
