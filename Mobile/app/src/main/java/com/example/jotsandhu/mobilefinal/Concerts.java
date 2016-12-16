package com.example.jotsandhu.mobilefinal;

/**
 * Created by jotsandhu on 12/13/16.
 */
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
public class Concerts implements Serializable{
    List<Concert> myConcerts;
    List<LatLng> locations;
    public Concerts(){
        myConcerts = new ArrayList<Concert>();
    }
    public void addConcert(Concert inConcert){
        myConcerts.add(inConcert);
    }
    public List<Concert> getConcertList(){
        return myConcerts;
    }
    public ArrayList<String> out(){
        ArrayList<String> outlist = new ArrayList<String>();
        String temp;
        Concert tmpCon;
        for(int i = 0; i < myConcerts.size(); i++){
            tmpCon = myConcerts.get(i);
            temp = "BAND: " + tmpCon.getBandName() + "- Genre: " + tmpCon.getgenre() + "- Time: " + tmpCon.getTime() + "-Date: " +tmpCon.getDate();
            outlist.add(temp);

        }
        return outlist;
    }

}
