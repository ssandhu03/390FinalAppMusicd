package com.example.jotsandhu.mobilefinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class HomeActivity extends AppCompatActivity implements Serializable {
    Intent myintent;
    private RelativeLayout Mainlayout;
    private EditText ETXT;
    Concerts myConcerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        //Get intent and pointers to the widgets that will be needed and used
        Intent myintent = getIntent();
        myConcerts = (Concerts) myintent.getSerializableExtra("concerts");

        Mainlayout = (RelativeLayout) findViewById(R.id.hLayout);
        //ETXT = (EditText) findViewById(R.id.editTextSettings);
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //registerChangeListener();

    }
    public void AddConcert(View view){
        finish();
    }
    public void FindConcerts(View view){
        Intent intent = new Intent(this, Show_Concerts.class);
        intent.putExtra("concerts",myConcerts);
        startActivity(intent);

    }
    public void Settings(View view){

    }
    //Register the listener for the change to to the edit Tex
    /*
    private void registerChangeListener() {
        ETXT.setOnFocusChangeListener(NameListener);


    }
    private View.OnFocusChangeListener NameListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            saveName(ETXT.getText().toString());
        };


    };


    //Next three functions are event handlers for the radiobuttons to set the background color
    //and to save the color.

    */
}