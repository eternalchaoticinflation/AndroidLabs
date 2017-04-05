package com.example.nothi.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity {
    TextView themessage;
    TextView theid;
    private Button mdfragB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        //we have to create before we assign to things
        themessage=(TextView)this.findViewById(R.id.messagedetails);
        theid=(TextView)this.findViewById(R.id.idequals);


        //Intent listIn=getIntent(); THis kind of works
        //you can also use a bundle
        Bundle bundlepasser=this.getIntent().getExtras();
        //but what is getIntent???
        getIntent().toString();//that would be great


            String chatmessagePassed=bundlepasser.getString("chatmessage");
        String chatmessageidPassed=bundlepasser.getString("chatmessageid");

        //we got it, now we set it.
        themessage.setText(chatmessagePassed);
        theid.setText(chatmessageidPassed);

        mdfragB=(Button)this.findViewById(R.id.messagedeleteB);
        //inflate tells view what View IT IS. //what does the false means????
        mdfragB.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick (View v){
                        buttonClicked(v);
                    }
                }
        );


    }

    public void buttonClicked(View view){

        Intent resultIntent = new Intent(  );

        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // User clicked OK button

    }
}
