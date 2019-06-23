package com.example.asus.moodlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyInfoManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public MyInfoManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.infoTB;
    }

    public void add(MyInfoItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nickname", item.getNickname());
        values.put("sex", item.getSex());
        values.put("qianming", item.getQianming());
        values.put("xinzuo", item.getXinzuo());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(MyInfoItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nickname", item.getNickname());
        values.put("sex", item.getSex());
        values.put("qianming", item.getQianming());
        values.put("xinzuo", item.getXinzuo());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }
    public MyInfoItem findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        MyInfoItem rateItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            rateItem = new MyInfoItem();
            rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            rateItem.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
            rateItem.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            rateItem.setQianming(cursor.getString(cursor.getColumnIndex("qianming")));
            rateItem.setXinzuo(cursor.getString(cursor.getColumnIndex("xinzuo")));
            cursor.close();
        }
        db.close();
        return rateItem;
    }

}
