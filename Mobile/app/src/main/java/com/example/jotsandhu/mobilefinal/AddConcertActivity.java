package com.example.jotsandhu.mobilefinal;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.Toast;
import java.sql.Time;
import java.util.Calendar;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date.*;
import java.util.List;

public class AddConcertActivity extends AppCompatActivity {
    private LinearLayout concertLayout;
    private RelativeLayout Mainlayout;
    private EditText bName;
    private EditText gET;
    private EditText ETXT;
    private EditText cET;
    private EditText sET;

    private Calendar date;
    private TimePicker Tp;
    private int year, month, day,hour,minutes;
    private DBHelper myDB;
    private double latitude;
    private double longitude;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concert_layout);

        //Get intent and pointers to the widgets that will be needed and used
        intent = getIntent();
        concertLayout = (LinearLayout) findViewById(R.id.concerts);
        Mainlayout = (RelativeLayout) findViewById(R.id.map);
        bName = (EditText) findViewById(R.id.bandName);
        gET = (EditText) findViewById(R.id.genre);
        sET = (EditText) findViewById(R.id.specialInfo);
        //Dp = (DatePicker) findViewById(R.id.datePicker1);
        Tp = (TimePicker) findViewById(R.id.timePicker1);
        //ETXT = (EditText) findViewById(R.id.editTextSettings);
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        latitude = intent.getFloatExtra("latitude",0);
        longitude = intent.getFloatExtra("longitude",0);
        //registerChangeListener();
        date = Calendar.getInstance();
        year = date.get(Calendar.YEAR);
        myDB = new DBHelper(getApplicationContext());

        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);
        hour = Tp.getCurrentHour();
        minutes = Tp.getCurrentMinute();
        //showDate(year, month+1, day);
    }
    public void Submit(View view){
        String time = String.valueOf(hour) + ":" + String.valueOf(minutes);
        String date = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
        if(myDB.insertContact(bName.getText().toString(),gET.getText().toString(),date,time,sET.getText().toString(),latitude,longitude)) {
            //bName.setText(myDB.getOneRow());
        }
        intent.putExtra("bandName",bName.getText().toString());
        intent.putExtra("genre",gET.getText().toString());
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        intent.putExtra("special",sET.getText().toString());
        intent.putExtra("long",longitude);
        intent.putExtra("lat",latitude);
        Concerts myConcerts = myDB.getAllConcerts();
        List<Concert> tempList = myConcerts.getConcertList();
        bName.setText(tempList.get(0).getBandName());
        setResult(RESULT_OK, intent);
        super.finish();

    }
    public void onResume(){
        super.onResume();
    }
    public void toHome(View view){
        finish();
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Thanks for Submitting the Date",
                Toast.LENGTH_SHORT);
                //.show();
    }
    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        showDialog(666);
        Toast.makeText(getApplicationContext(), "Thanks for Submitting the Time",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        if (id == 666){
            return new TimePickerDialog(this,myTimeListener,hour,minutes,true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                    year = arg1;
                    month = arg2+1;
                    day = arg3;
                }
            };
    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    hour = hourOfDay;
                    minutes = minute;
                    showTime(hourOfDay, minute);
        }
    };
    private void showDate(int year, int month, int day) {
        int a = year;
        //bName.setText(new StringBuilder().append(day).append("/")
                //.append(month).append("/").append(year));
    }
    private void showTime(int hour, int minute){
        int b = hour;
        //bName.setText(new StringBuilder().append(hour).append(":").append(minute));
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