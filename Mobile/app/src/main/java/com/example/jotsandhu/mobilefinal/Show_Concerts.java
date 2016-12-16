package com.example.jotsandhu.mobilefinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class Show_Concerts extends AppCompatActivity implements Serializable {
    private ArrayAdapter<String> adapter;
    private RelativeLayout Mainlayout;
    private EditText ETXT;
    private ListView Lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_concerts);

        //Get intent and pointers to the widgets that will be needed and used
        Intent intent = getIntent();

        Mainlayout = (RelativeLayout) findViewById(R.id.hLayout);
        Lv = (ListView) findViewById(R.id.listView);
        //ETXT = (EditText) findViewById(R.id.editTextSettings);
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //registerChangeListener();
        ArrayList<String> myArray = new ArrayList<>();
        Concerts myConcerts = (Concerts) intent.getSerializableExtra("concerts");
        myArray = myConcerts.out();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, myArray);

        // Here, you set the data in your ListView
        Lv.setAdapter(adapter);
    }
    public void end(View view){
        finish();
    }

}
