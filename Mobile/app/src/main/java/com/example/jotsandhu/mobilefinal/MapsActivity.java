package com.example.jotsandhu.mobilefinal;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener,Serializable {
    static final int request = 1;
    static final int request2 = 2;
    private GoogleMap mMap;
    private DBHelper myDBHelper;
    Concerts myConcerts;
    List<Concert> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager myFragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) myFragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myDBHelper = new DBHelper(this);

        //mMap = mapFragment.get
        //registerChangeListener();
        myConcerts = new Concerts();


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void onResume(){
        super.onResume();
        myConcerts = myDBHelper.getAllConcerts();
        tempList = myConcerts.getConcertList();


        if(!myConcerts.equals(null)){
            if(!tempList.equals(null )){
                if(tempList.size() == 0){
                    LatLng pos = new LatLng(-40,160);
                    mMap.addMarker(new MarkerOptions().position(pos).title("ADFAD"));
                }
                /*
                else{
                    //Concert temporary = tempList.get(tempList.size()-1);
                    LatLng otherpos = new LatLng(-34,160);
                    mMap.addMarker(new MarkerOptions().position(otherpos).title("AAAAAA"));
                }
                /*
                /*
                else{
                    //Concert temporary = tempList.get(0);
                    LatLng pos = new LatLng(-34,160);
                    mMap.addMarker(new MarkerOptions().position(pos).title("ADFAD"));
                }
                */
                    /*
                Concert temp = tempList.get(0);
                LatLng pos = new LatLng(temp.getLongitude(),temp.getLatitude());
                mMap.addMarker(new MarkerOptions().position(pos).title("ADFAD"));
                */
                /*
                for(Concert curCon: tempList){
                    LatLng pos = new LatLng(curCon.getLatitude(),curCon.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(pos).title("none"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
                    */

                }
            }




        }









    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ithaca = new LatLng(42.444, -76.5019);
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(ithaca, 15);

        mMap.addMarker(new MarkerOptions().position(ithaca).title("Marker in Ithaca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ithaca));
        mMap.animateCamera(cameraPosition);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }


    public void onMapClick(LatLng arg0){
        Intent intent = new Intent(getApplicationContext(), AddConcertActivity.class);
        intent.putExtra("longitude", arg0.longitude);
        intent.putExtra("latitude",arg0.latitude);
        startActivityForResult(intent,request);

    }
    public void onMapLongClick(LatLng point){
        Intent intent = new Intent(getApplicationContext(), AddConcertActivity.class);

        intent.putExtra("longitude", point.longitude);
        intent.putExtra("latitude",point.latitude);
        startActivityForResult(intent,request);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == request) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                LatLng temp = new LatLng(data.getDoubleExtra("latitude",0),data.getDoubleExtra("longitude",0) );
                mMap.addMarker(new MarkerOptions().position(temp).title(data.getStringExtra("bandName") + " performing at " + data.getStringExtra("time") +" on " + data.getStringExtra("date") + " **Special instructions: " ));
                Concert mine = new Concert(data.getStringExtra("bandName"),data.getStringExtra("genre"),data.getStringExtra("date"),data.getStringExtra("time"),data.getStringExtra("special"));
                mine.setLat(data.getDoubleExtra("latitude",0));
                mine.setLong(data.getDoubleExtra("longitude",0));
                myConcerts.addConcert(mine);
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("concerts",myConcerts);
                startActivityForResult(intent,request2);

            }
            else{
                LatLng temp = new LatLng(data.getDoubleExtra("latitude",0),data.getDoubleExtra("longitude",0) );
                mMap.addMarker(new MarkerOptions().position(temp).title("Marker Somewhere"));
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("concerts",myConcerts);
                startActivityForResult(intent,request2);
            }
        }
        if (requestCode == request2){
            if(resultCode == RESULT_OK){
                LatLng temp = new LatLng(data.getDoubleExtra("latitude",0),data.getDoubleExtra("longitude",0) );
                mMap.addMarker(new MarkerOptions().position(temp).title("Marker Somewhere"));
            }

        }
        //Intent intent = new Intent(this, HomeActivity.class);
        //startActivity(intent);
    }

    /*
    GoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

        @Override
        public void onMapClick(LatLng arg0) {
            // TODO Auto-generated method stub
            Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
        }
    });
    @Override
    private GoogleMap.OnMapClickListener mListener = new GoogleMap.OnMapClickListener(){
        @Override
        public void onMapClick(LatLng arg0) {
            // TODO Auto-generated method stub
            //Log.d("arg0", arg0.latitude + "-" + arg0.longitude);
            Intent intent = new Intent(getApplicationContext(), AddConcertActivity.class);
            startActivity(intent);
        }
    };
    */

}
