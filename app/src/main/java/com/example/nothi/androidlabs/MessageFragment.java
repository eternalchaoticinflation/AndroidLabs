package com.example.nothi.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nothi on 2017-04-03.
 */

public class MessageFragment extends Fragment {
    private TextView firstline;
    private TextView secondline;
    private Button  topFragB;
    //instead of creating custom
    //private MfragmentCustomListener fragmentDirector;
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup containerfromVG, @Nullable Bundle savedInstanceState) {
        View customfragview=inflater.inflate(R.layout.activity_message_details, containerfromVG, false);//inflate means this is what is used for this fragement and for what view

        firstline=(TextView)customfragview.findViewById(R.id.messagedetails);//use our View
        secondline=(TextView)customfragview.findViewById(R.id.idequals);
        topFragB=(Button)customfragview.findViewById(R.id.messagedeleteB);
        //inflate tells view what View IT IS. //what does the false means????


        topFragB.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick (View v){
                        buttonClicked(v);
                    }
                }
        );

        //the arguments it takes are

        ///////////View view=inflater.inflate( R.layout.top_ection_fragment/layout, containerfromVG//ViewGroup, false);//which meanss???
        //now return a view
        return customfragview;

    }
    public void setMessage(String m){
        firstline.setText(m);

    }
    public void setId(String id){
        secondline.setText(id);

    }


    public interface MfragmentCustomListener    {
        public void deletemessageandfrag(String top, String bottom);//interface force to make, meth

    }

    public void buttonClicked(View view){
        //topfragmentDirector concretefiy
        //firstline is foundbyid and secondline is found byid
        //and then the onclicke listener is created

        //MainActivity has been CASTED to  topfragmentDirector!!!
        //topfragmentDirector.createTextonpic(firstline.getText().toString(), secondline.getText().toString());
        //eventually it will be override
        //TopSectionfragmentCustomListener topfragmentDirector an Interface can just exist and
        //and implement this method, like how a superclass exists,
        // i;m sure the class that polymorphs into TopSectionfragmentCustomListener has to be concrete.
        //MainAcitivy is concrete, and polymorphs into TopSectionfragmentCustomListener!!!!!
       // fragmentDirector.deletemessageandfrag(firstline.getText().toString(), secondline.getText().toString());

        ((ChatWindow)getActivity()).destroyFragment();

    }

}
