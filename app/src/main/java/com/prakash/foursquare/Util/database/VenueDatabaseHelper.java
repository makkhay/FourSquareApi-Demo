package com.prakash.foursquare.Util.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLite Database to save a venue
 */
public class VenueDatabaseHelper extends SQLiteOpenHelper  {

    private static final String TAG = VenueDatabaseHelper.class.getName();

    private static final String DB_NAME = "venues.sqlite";
    private static final int VERSION = 1;

    public static final String TABLE_VENUE = "venue";      //name of the table
    public static final String COLUMN_ID = "_id";         //primary key of the table
    public static final String COLUMN_VENUE_ID = "venue_id";
    public static final String COLUMN_VENUE_NAME = "venue_name";



    //Create the db
    public VenueDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        Log.d(TAG, "com.prakash.foursquare.Util.database instance created.");
    }


    //create the venue table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_VENUE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VENUE_ID + " TEXT, " +
                COLUMN_VENUE_NAME + " TEXT" +
                ");";
        db.execSQL(createQuery);
        Log.d(TAG, TABLE_VENUE + " table created.");
    }


    // Upgrade the table, delete the older version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "Upgrading com.prakash.foursquare.Util.database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUE);
        onCreate(db);
    }

}
