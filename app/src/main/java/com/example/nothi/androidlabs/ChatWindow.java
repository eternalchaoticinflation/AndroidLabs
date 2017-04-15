package com.example.nothi.androidlabs;

import android.app.Activity;
import android.app.Fragment;//android.support.v4.app, is in support lib, helps with API less than 11
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.nothi.androidlabs.Loginlab2Activity.ACTIVITY_NAME;

public class ChatWindow extends AppCompatActivity {
    ListView pieListView;
    ArrayList<String> pies = new ArrayList< >();
    EditText inputText;
    public SQLiteDatabase cDB;
    boolean haveInput=false;
    public int tracker=0;

    private Button sendB;
    private boolean xml600checker=true;
    private android.app.FragmentManager fmanager;//this interacts with fragment
    //NOT import android.support.v4.app.FragmentManager; RATHER the android
    private FragmentTransaction transaction;
    private int positionlatch=0;
    private boolean fragmentdead=true;

    private ChatAdapter messageAdapter;
    private MessageFragment mdetails;
    private int deleteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        fmanager=this.getFragmentManager();//maybe try static?
        //this.findViewById(R.id.chatwindowframe).isActivated();
        messageAdapter=new ChatAdapter(this);
        if(findViewById(R.id.chatwindowframe)==null){
            xml600checker=false;
            Log.i(ACTIVITY_NAME, "the 600xml is NOT Load and xml600checker is: " +  xml600checker);

            Toast.makeText(getApplicationContext(), "findViewById(R.id.chatwindowframe)==null", Toast.LENGTH_LONG).show();

        }
        else{//is TABLET
             mdetails= new MessageFragment();



            //let's begin transaction
            if(fragmentdead==true){//fragment is dead make new frag
            transaction=fmanager.beginTransaction();
            transaction.add(R.id.chatwindowframe, mdetails, "messagedetailsfrag");
            //commit
            transaction.commit();
            fragmentdead=false;
            }

            Log.i(ACTIVITY_NAME, "this.findViewById(R.id.chatwindowframe) is: " +  this.findViewById(R.id.chatwindowframe));
            //at least it's not null.
            Log.i(ACTIVITY_NAME, "LEGIT xml600checker is: " +  xml600checker);
        }


        final ChatDatabaseHelper dbToChat = new ChatDatabaseHelper(this);
        //gets querys to help us       //now we can query     //cDB.rawQuery();
        //no  you need      //pies.add(cDB.rawQuery());   //it comes in a cusor

        sendB = (Button) findViewById(R.id.sendButton);
        pieListView = (ListView) findViewById(R.id.chatList);
        inputText = (EditText) findViewById(R.id.editText4);


        pieListView.setAdapter(messageAdapter);
        cDB = dbToChat.getWritableDatabase();

        Resources resources = getResources();

        //Cursor cursor = cDB.rawQuery("SELECT chatcol FROM tablechat where chatcol='uuyu' ", null);
        //Cursor cursor = cDB.rawQuery("SELECT _id, chatcol FROM tablechat where chatcol='rrr' ", null);
        Cursor cursor = cDB.rawQuery("SELECT _id, chatcol FROM tablechat ", null);

        //for loop.`
        /*
        pies.add(cursor.getString(cursor.getColumnIndex("chatcol")));
        //assigns our array of String
        */
        while (!cursor.isAfterLast()) {
                if (cursor.moveToFirst()) {
                    Log.i(ACTIVITY_NAME, "Cursor’s  total count is = " + cursor.getCount());
                    //Cursor’s  total count is = 4
                    do {
                        Log.i(ACTIVITY_NAME, "SQL MESSAGE:" +
                                cursor.getString(cursor.getColumnIndex(dbToChat.KEY_MESSAGE)
                                        //it expects a table [_id, chatcol], which is [0, 1]

                                ));
                        //Toast.makeText(getApplicationContext(), "rrr is RRRRR "+cursor.getString(cursor.getColumnIndex(dbToChat.KEY_ID)), Toast.LENGTH_LONG).show();
                        //SQL MESSAGE:rddhjjjj and SQL MESSAGE:dfhhhClick to Chat, SQL MESSAGE:ddffgghh
                        pies.add( cursor.getString(cursor.getColumnIndex(dbToChat.KEY_MESSAGE)));
                        Log.i(ACTIVITY_NAME, "Cursor’s table's column count = " + cursor.getColumnCount());

                    } while (cursor.moveToNext());
                }
            }
        Log.i(ACTIVITY_NAME, "Cursor’s  column count = " + cursor.getColumnCount());
            for (int i = 0; i < cursor.getColumnCount(); i++) {

                Log.i(ACTIVITY_NAME, "Column name is = " + cursor.getColumnName(i));
            }





            sendB.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            haveInput = true;
                            String inMess = (String) inputText.getText().toString();
                            pies.add(inMess);
                            ContentValues newvalues = new ContentValues();// you need a contentValues
                            newvalues.put(dbToChat.KEY_MESSAGE, inMess); //then put it in column name(key), then message(value);
                            cDB.insertWithOnConflict(dbToChat.TABLE_CHAT, null, newvalues, SQLiteDatabase.CONFLICT_IGNORE);
                            messageAdapter.notifyDataSetChanged();
                            inputText.setText("");

                            if (cDB.isOpen()) {
                                Log.i(ACTIVITY_NAME, "the database is open");
                            } else {
                                Log.i(ACTIVITY_NAME, "NOT EVEN OPEN, NOT EVEN OPEN, NOT EVEN OPEN");
                            }

                        }
                    }
            );


        pieListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id){

                //status.setText(printerValueMap.get(printers.get(position)));
                //positiontracker=printers.indexOf(printers.get(position));
                //printers =new ArrayList<>( Arrays.asList("C110","C112","C138","C148"));
                String toastdisplay= Integer.toString( (int)messageAdapter.getItemID(position));
                deleteid=(int)messageAdapter.getItemID(position);
                positionlatch=position;//used for intent
                Toast.makeText(getApplicationContext(), "pies.get(position) is: "+pies.get(position)+" item ID = " +toastdisplay, Toast.LENGTH_LONG).show();
                if(findViewById(R.id.chatwindowframe)==null){

                Intent showdetails= new Intent("com.example.nothi.androidlabs.MessageDetails");


                    showdetails.putExtra("chatmessage", pies.get(position));

                //beter version of showdetails.putExtra("chatmessageid", messageAdapter.getItemID(position));
                showdetails.putExtra("chatmessageid", toastdisplay);
                    startActivityForResult(showdetails, 15);

                     }
                else{



                    if (fragmentdead==true){

                        //fragment is dead make frag before
                        transaction=fmanager.beginTransaction();
                        transaction.add(R.id.chatwindowframe, mdetails, "messagedetailsfrag");
                        //commit
                        transaction.commit();
                        fragmentdead=false;

                    }
                   // MessageFragment newmdetails= new MessageFragment();
                    mdetails=(MessageFragment)getFragmentManager().findFragmentById(R.id.chatwindowframe);
                    //support for the v4.app stuff.
                    mdetails.setMessage(pies.get(position));
                    mdetails.setId(toastdisplay);


                }


            }

        });




    }



    public Cursor queryChatDBid(String message){
        //this gets the id of the message passed

        return cDB.rawQuery("SELECT _id FROM tablechat WHERE _id = ?", new String[]{ message});

    }

    @Override
    protected void onDestroy(){
        cDB.close();
        super.onDestroy();

    }


    @Override
    protected void  onActivityResult(int requestCode, int responseCode, Intent data){

        //startActivityForResult(listIn, 5); triggered by
        // will input onActivityResult(5, RESULT_OK or RESULT_CANCELLED, listIn)
        //will be inputed after we have returned to startactivity
        //will log like onResume(), or onStop();
        if (requestCode==15){
            //could start activie or flag error for now
            Log.i(ACTIVITY_NAME, "BACKED From DELETE request 15");
            if (responseCode==RESULT_OK){
                // in delete button
                //       Intent resultIntent = new Intent(  );
                //     setResult(Activity.RESULT_OK, resultIntent);
                //  finish(); // User clicked OK button


                String toastdummy=Integer.valueOf(positionlatch).toString();
                Toast.makeText(this, "ListView position is: "+toastdummy, Toast.LENGTH_LONG).show();

                cDB.delete("tablechat","_id=? and chatcol=?",new String[]{Integer.toString(deleteid), pies.get(positionlatch) });
                //^above command is like
                //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
                //delete from tablename where id='1' and name ='jack'
                //DO NOT USE cDB.rawQuery("DELETE FROM tablechat WHERE chatcol = ?", new String[]{ pies.get(positionlatch)});
                pies.remove(positionlatch);
                messageAdapter.notifyDataSetChanged();


            }
        }
    }//onActivityResult

    public void destroyFragment(){
        transaction=fmanager.beginTransaction();
        pies.remove(positionlatch);
        messageAdapter.notifyDataSetChanged();
        MessageFragment des=(MessageFragment)fmanager.findFragmentByTag("messagedetailsfrag");
        if(des!=null){
            transaction.remove(des);
            transaction.commit();
            fragmentdead=true;//fragment is dead
        }
    }



    class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return pies.size();
        }
        @Override
        public String getItem(int position) {
            // this fills the chatAdapter with strings as long as it is below getCount
            //if the string is "GGGGG" it will display "GGGGG" until getCount is reached
            return pies.get(position);
        }//alreadydone

        public long getItemID(int position) {
            //pies.get(position) is a STRING and it is the message on display that is clicked AND it is
            //the message in the database. cDB is the database.
            //change query for custom query
            Cursor cursorgetId=cDB.rawQuery("SELECT _id FROM tablechat WHERE chatcol = ?", new String[]{ pies.get(position)});
            int theid= 0;
            //while is good to not get -1 null point exception
            while (!cursorgetId.isAfterLast()) {
                if (cursorgetId.moveToFirst()) {
                    Log.i(ACTIVITY_NAME, "CursorIdIDDDDD’s  total count is = " + cursorgetId.getCount());
                    //Cursor’s  total count is = 4
                    do {
                        theid=Integer.valueOf(cursorgetId.getString(cursorgetId.getColumnIndex("_id")));
                        Log.i(ACTIVITY_NAME, "in getItemID SQL COLUMN ID is:" +
                                cursorgetId.getString(cursorgetId.getColumnIndex("_id")
                                        //it expects a table [_id, chatcol], which is [0, 1]

                                ));
                        //Toast.makeText(getApplicationContext(), "rrr is RRRRR "+cursor.getString(cursor.getColumnIndex(dbToChat.KEY_ID)), Toast.LENGTH_LONG).show();
                        //SQL MESSAGE:rddhjjjj and SQL MESSAGE:dfhhhClick to Chat, SQL MESSAGE:ddffgghh
                        //String td=cursorgetId.getString(cursorgetId.getColumnIndex("_id"));
                        Log.i(ACTIVITY_NAME, " getItemID Cursor’s table's column count = " + cursorgetId.getColumnCount());
                       // Toast.makeText(getApplicationContext(), td, Toast.LENGTH_LONG).show();

                    } while (cursorgetId.moveToNext());
                }
            }



            //cursorgetId.getString(cursorgetId.getColumnIndex("chatcol"));
            return theid;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;


        }

    }///ChatAdapter




}//ChatWindow
