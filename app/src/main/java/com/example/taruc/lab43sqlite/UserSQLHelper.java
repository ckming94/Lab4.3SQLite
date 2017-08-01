package com.example.taruc.lab43sqlite;

/**
 * Created by TAR UC on 7/28/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.taruc.lab43sqlite.UserContract.User;

public class UserSQLHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + "(" +
                    User.COLUMN_PHONE + " TEXT," +
                    User.COLUMN_NAME + " TEXT," +
                    User.COLUMN_EMAIL + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;
    private String[] allColumn = {
            User.COLUMN_PHONE,
            User.COLUMN_NAME,
            User.COLUMN_EMAIL
    };

    public UserSQLHelper(Context context) { //context link activity to db
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    //Add a new record
    public void insertUser(UserRecord userRecord){
        //Prepare record
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_PHONE, userRecord.getPhone());
        values.put(User.COLUMN_NAME, userRecord.getName());
        values.put(User.COLUMN_EMAIL, userRecord.getEmail());

        //Insert a row
        //Create an instance of database
        //getWritableDatabase to perform insert, delete and update
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(User.TABLE_NAME, null, values); // null is to allocate specific location

        //Close database connection
        database.close();
    }

    //Get all records
    public List<UserRecord> getAllUsers(){
        List<UserRecord> records = new ArrayList<UserRecord>();

        //Create an instance of database
        //getReadableDatabase to retrieve/read records
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(User.TABLE_NAME, allColumn , null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){ //while not EOF
            UserRecord userRecord = new UserRecord();
            userRecord.setPhone(cursor.getString(0));
            userRecord.setName(cursor.getString(1));
            userRecord.setEmail(cursor.getString(2));
            records.add(userRecord); //insert a record to list
            cursor.moveToNext(); //move to the next record
        }

        return records;
    }

}

