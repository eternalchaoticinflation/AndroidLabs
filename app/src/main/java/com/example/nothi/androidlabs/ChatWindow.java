package com.example.nothi.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Arrays;

import static com.example.nothi.androidlabs.R.id.sendButton;

public class ChatWindow extends AppCompatActivity {
    ListView pieListView;
    ArrayList<String> pies = new ArrayList<String>(
            Arrays.asList(""));
    EditText inputText;

    private Button sendB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        //assigns our array of String

        sendB = (Button) findViewById(R.id.sendButton);
        pieListView = (ListView) findViewById(R.id.chatList);
        inputText = (EditText) findViewById(R.id.editText4);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        pieListView.setAdapter(messageAdapter);

        Resources resources = getResources();

        sendB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inMess = (String) inputText.getText().toString();
                        pies.add(inMess);
                        messageAdapter.notifyDataSetChanged();
                        inputText.setText("");
                    }
                }

        );
    }

    class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return pies.size();
        }

        public String getItem(int position) {
            return pies.get(position);
        }

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

    }
}
