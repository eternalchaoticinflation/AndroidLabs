package com.example.nothi.androidlabs;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nothi on 2017-03-03.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME="chatlines.db";
    static int VERSION_NUM=2;
    public static String TABLE_CHAT="tablechat";
    public static String KEY_ID="_id";
    public static String KEY_MESSAGE="chatcol";
    public ChatDatabaseHelper(Context ctx) {
              super(ctx, DATABASE_NAME, null, VERSION_NUM);

    }
    @Override
    public void onCreate(SQLiteDatabase db){

        Log.i("ChatDatabaseHelper", "Calling onCreateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        String query="CREATE TABLE "+TABLE_CHAT+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_MESSAGE+
                " TEXT "+");";


        db.execSQL(query);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldver, int newver){
        String query2="DROP TABLE IF EXISTS " + TABLE_CHAT;
        db.execSQL(query2);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldver + "newVersion=" + newver);
        onCreate(db);

    }

}
