package com.example.asus.moodlog;


    import java.util.ArrayList;
    import java.util.List;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;

    public class DBManager {

        private DBHelper dbHelper;
        private String TBNAME;

        public DBManager(Context context) {
            dbHelper = new DBHelper(context);
            TBNAME = DBHelper.TB_NAME;
        }

        public void add(LogItem item){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("logs", item.getLogs());
            values.put("time", item.getTime());
            values.put("logtheme", item.getLogtheme());
            db.insert(TBNAME, null, values);
            db.close();
        }


        public void deleteAll(){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(TBNAME,null,null);
            db.close();
        }

        public void delete(int id){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
            db.close();
        }

        public void update(LogItem item){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("logs", item.getLogs());
            values.put("time", item.getTime());
            values.put("logtheme", item.getLogtheme());
            db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
            db.close();
        }

        public List<LogItem> listAll(){
            List<LogItem> logList = null;
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
            if(cursor!=null){
                logList = new ArrayList<LogItem>();
                while(cursor.moveToNext()){
                    LogItem item = new LogItem();
                    item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                    item.setLogs(cursor.getString(cursor.getColumnIndex("LOGS")));
                    item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
                    item.setTime(cursor.getString(cursor.getColumnIndex("LOGSTHEME")));

                    logList.add(item);
                }
                cursor.close();
            }
            db.close();
            return logList;

        }

        public LogItem findById(int id){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
            LogItem rateItem = null;
            if(cursor!=null && cursor.moveToFirst()){
                rateItem = new LogItem();
                rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                rateItem.setLogs(cursor.getString(cursor.getColumnIndex("LOGS")));
                rateItem.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
                rateItem.setTime(cursor.getString(cursor.getColumnIndex("LOGSTHEME")));
                cursor.close();
            }
            db.close();
            return rateItem;
        }
    }

