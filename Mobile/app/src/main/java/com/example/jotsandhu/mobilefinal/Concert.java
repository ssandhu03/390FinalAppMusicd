package com.example.jotsandhu.mobilefinal;

import android.graphics.Paint;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jotsandhu on 12/13/16.
 */

public class Concert implements Serializable {
    String bandName;
    String genre;

    String Date;
    String Time;
    String specialInfo;
    Double longitude;
    Double latitude;
    public Concert(){
        bandName = "";
        genre = "";
        Date = "";
        Time = "";
        specialInfo = "";
        longitude = 0.0;
        latitude = 0.0;
    }
    public Concert(String name, String gen, String inDate, String inTime, String inInfo){
        bandName = name;
        genre = gen;
        Date = inDate;
        specialInfo = inInfo;
        Time = inTime;

    }
    public void setBandName(String inName){
        bandName = inName;
    }
    public void setAddress(String inAdd){
        genre = inAdd;
    }

    public void setDate(String inDate){
        Date = inDate;
    }
    public void setSpecialInfo(String inInfo){
        specialInfo  = inInfo;
    }
    public void setTime(String inTime){Time = inTime;
    }
    public void setLong(double longtd){longitude = longtd;}
    public void setLat(double Lat){latitude = Lat;}
    public String getBandName(){
        return bandName;
    }
    public String getgenre(){
        return genre;
    }

    public String getDate(){
        return Date;
    }
    public String getTime(){return Time;}
    public Double getLongitude(){return longitude;}
    public Double getLatitude(){return latitude;}
    public String getSpecialInfo(){
        return specialInfo;
    }
}
