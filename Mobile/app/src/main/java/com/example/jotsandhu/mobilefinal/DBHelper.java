package com.example.jotsandhu.mobilefinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String CONCERTS_TABLE_NAME = "concerts";
    public static final String CONCERTS_COLUMN_ID = "id";
    public static final String CONCERTS_COLUMN_BNAME = "bandName";
    public static final String CONCERTS_COLUMN_GENRE = "genre";
    public static final String CONCERTS_COLUMN_DATE = "date";
    public static final String CONCERTS_COLUMN_TIME = "time";
    public static final String CONCERTS_COLUMN_SPECIAL= "specialInstructions";
    public static final String CONCERTS_COLUMN_LATITUDE= "latitude";
    public static final String CONCERTS_COLUMN_LONGITUDE= "longitude";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table concerts " +
                        "(id integer primary key, bandName text,genre text,date text, time text,specialInstructions text, latitude real, longitude real)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (String bandName, String genre, String date, String time,String specInstr,Double latitude, Double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("bandName", bandName);
            contentValues.put("genre", genre);
            contentValues.put("date", date);
            contentValues.put("time", time);
            contentValues.put("specialInstructions", specInstr);
            contentValues.put("longitude", longitude);
            contentValues.put("latitude", latitude);
            db.insert("concerts", null, contentValues);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public String getOneRow(){
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor res = mydb.rawQuery("select * from concerts",null);

        if(res.moveToFirst()) {
            return String.valueOf(res.getCount());
        }
        return "false";
        //if(res != null){
            //return res.getString(1);
        //}
        //return "false";
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from concerts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONCERTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Concerts getAllConcerts() {
        Concerts outConcerts = new Concerts();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from concerts", null );


        if (res.moveToFirst()) {
            do {
                Concert temp = new Concert();
                temp.setBandName(res.getString(1));
                temp.setAddress(res.getString(2));
                temp.setDate(res.getString(3));
                temp.setTime(res.getString(4));
                temp.setSpecialInfo(res.getString(5));

                temp.setLat(res.getDouble(6));
                temp.setLong(res.getDouble(7));

                // Adding Translate to list
                outConcerts.addConcert(temp);
            } while (res.moveToNext());
        }
        return outConcerts;
    }
}
