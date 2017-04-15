package com.example.nothi.androidlabs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener{
    FloatingActionButton fab;

    String iconthreemess;
    EditText assistantmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        assistantmessage= (EditText)findViewById(R.id.asticon_edittext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "<no escape\"&gt;\">", Snackbar.LENGTH_LONG)
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


                //This works
                //Snackbar.make(findViewById(R.id.toolbar), "<no escape\"&gt;\">", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //This works too
                Snackbar.make(findViewById(R.id.action_folder), "selected FOLDER, icon3 message is:"+iconthreemess + "< \"&gt;\">", Snackbar.LENGTH_LONG).setAction("Action", null).show();




                break;
            case R.id.action_save:
                Log.d("Toolbar", "sction_save: selected");
                Snackbar.make(findViewById(R.id.action_folder), "you selected SAVE, using findViewById(R.id.action_folder)", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                LayoutInflater dinflater = getLayoutInflater();

                builder.setMessage(R.string.saveiconmessage) //Add a dialog message to strings.xml
                        .setTitle(R.string.dialog_title)


                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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



                break;
            case R.id.action_assistant:

                Snackbar.make(findViewById(R.id.action_assistant), "You selected Assistant, just feed dummy view?", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                AlertDialog.Builder builder3 = new AlertDialog.Builder(TestToolbar.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                LayoutInflater dinflater3 = getLayoutInflater();

                final View vinflator=dinflater3.inflate(R.layout.assistant_view, null);
                //that gets and inflates someview.
                assistantmessage=(EditText)vinflator.findViewById(R.id.asticon_edittext);

                builder3.setMessage(R.string.ast_message) //Add a dialog message to strings.xml
                        .setTitle(R.string.ast_title)
                        .setView(dinflater3.inflate(R.layout.assistant_view, null))

                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                iconthreemess=assistantmessage.getText().toString();


                                onResume(); // User clicked OK button
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                onResume();
                            }
                        })
                        .show();


                break;
            case R.id.action_toast:
                Toast.makeText(getApplicationContext(), "Version 1.0 Wei Cui", Toast.LENGTH_LONG).show();

                break;
            case R.id.action_redbutton:

                finish();
                break;



        }

    return false;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //iconthreemess=assistantmessage.getText().toString();

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
