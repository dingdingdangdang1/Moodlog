package com.example.asus.moodlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "moodlog.db";
    public static final String TB_NAME = "mylogs";
    public static final String SoupETB = "soupe";
    public static final String SoupGTB = "soupgrowth";
    public static final String SoupLTB = "souplife";
    public static final String infoTB = "info";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,LOGS TEXT,TIME TEXT,LOGTHEME TEXT)");
        db.execSQL("CREATE TABLE "+SoupETB+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,soupcontent TEXT,rec TEXT)");
        db.execSQL("CREATE TABLE "+SoupGTB+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,soupcontent TEXT,rec TEXT)");
        db.execSQL("CREATE TABLE "+SoupLTB+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,soupcontent TEXT,rec TEXT)");
        db.execSQL("CREATE TABLE "+infoTB+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,nickname TEXT,sex TEXT,qianming TEXT,xinzuo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

}
