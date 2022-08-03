package com.example.mentalcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mentalcare.db";
    private static final String DB_TABLE_NAME = "User_Table";

    private static final String userId = "USER_ID";
    private static final String userName = "USER_NAME";
    private static final String userPassword = "USER_PASSWORD";
    private static final String userEmail = "USER_EMAIL";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "(" + userId +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + userName + " TEXT, " + userEmail + " TEXT, " + userPassword + " TEXT)";

    private static final String DB_TABLE_AP = "Appointment_Table";
    private static final String apId = "AP_ID";
    private static final String apName = "AP_NAME";
    private static final String apDesc = "AP_DESC";
    private static final String apDate = "AP_DATE";
    private static final String apTime = "AP_TIME";
    private static final String loggedId = "LOGGED_ID";
    private static final String CREATE_TABLE_AP = "CREATE TABLE " + DB_TABLE_AP + "(" + apId +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + apName + " TEXT, " + apDesc + " TEXT, " + apDate + " TEXT, " + apTime + " TEXT, "
            + loggedId + " TEXT)";

    private static final String DB_TABLE_MED = "Medicine_Table";
    private static final String medId = "_id";
    private static final String medPrice = "MED_PRICE";
    private static final String medName = "MED_NAME";

    private static final String CREATE_TABLE_MED = "CREATE TABLE " + DB_TABLE_MED + " (" + medId + " INTEGER PRIMARY KEY, " + medName +
            " TEXT, " + medPrice + " INTEGER)";

    public DatabaseHelper(Context con){
        super(con, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_AP);
        sqLiteDatabase.execSQL(CREATE_TABLE_MED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_AP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_MED);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String email, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(userId, id);
        cv.put(userName, name);
        cv.put(userEmail, email);
        cv.put(userPassword, password);

        long res = database.insert(DB_TABLE_NAME, null, cv);

        return res != -1;
    }

    public boolean insertDataAp(String name, String desc, String date, String time, int id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(userId, id);
        cv.put(apName, name);
        cv.put(apDesc, desc);
        cv.put(apDate, date);
        cv.put(apTime, time);
        cv.put(loggedId, id);

        long res = database.insert(DB_TABLE_AP, null, cv);

        return res != -1;
    }

    public boolean insertDataMed(int id, String name, int price){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(medId, id);
        cv.put(medName, name);
        cv.put(medPrice, price);

        long res = database.insert(DB_TABLE_MED, null, cv);

        return res != -1;
    }

    public Cursor viewData(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_NAME;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public Cursor viewDataAp(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_AP;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public Cursor viewDataMed(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_MED;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public Cursor viewDataApUser(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_AP + " WHERE " + loggedId + " = " + id;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public void deleteData(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_NAME + " WHERE " + userId + " = " + id);
        database.close();
    }

    public void deleteDataMed(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_MED + " WHERE " + medId + " = " + id);
        database.close();
    }

    public void deleteAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_MED);
        database.close();
    }
}