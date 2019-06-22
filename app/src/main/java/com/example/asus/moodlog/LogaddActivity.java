package com.example.asus.moodlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogaddActivity extends AppCompatActivity {
    private final String TAG = "LogaddActivity";
    String writeDate;
    EditText logadd;
    Button save;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logadd);
        writeDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Log.i(TAG,"writeDate:" + writeDate);
        save = (Button)findViewById(R.id.btnsave);
        dateText=(TextView)findViewById(R.id.datetext);
        dateText.setText(writeDate);
    }
    public void save(View btn){
        Log.i(TAG, "onClick: ");
        logadd = (EditText)findViewById(R.id.add_log);
        String logstr = logadd.getText().toString();
        if(logstr.length()>0){
           //保存到数据库中
            LogItem logItem= new LogItem();
            logItem.setLogs(logstr);
            logItem.setTime(writeDate);
            DBManager dbManager = new DBManager(LogaddActivity.this);
            dbManager.add(logItem);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }else{
            //用户没有输入内容
            Toast.makeText(this, "你还没有记录心情哦", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
