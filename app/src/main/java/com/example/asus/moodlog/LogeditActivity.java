package com.example.asus.moodlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogeditActivity extends AppCompatActivity {
    private final String TAG = "LogeditActivity";
    String writeDate2;
    EditText logadd2,logtheme2;
    Button save2;
    TextView dateText2;
    int shu_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logedit);
        writeDate2 = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Log.i(TAG,"writeDate:" + writeDate2);
        save2 = (Button)findViewById(R.id.btnsave2);
        dateText2=(TextView)findViewById(R.id.datetext2);
        dateText2.setText(writeDate2);
        //列表点击后的参数传递
        String title = getIntent().getStringExtra("title");
        String detail = getIntent().getStringExtra("detail");
        shu_id=Integer.parseInt(getIntent().getStringExtra("IdStr"));

        Log.i(TAG, "onCreate: title = " + title);
        Log.i(TAG, "onCreate: detail=" + detail);

        ((EditText)findViewById(R.id.theme_edt2)).setText(title);
        ((EditText)findViewById(R.id.add_log2)).setText(detail);
    }
    public void savenew(View btn){
        Log.i(TAG, "onClick: ");
        logadd2 = (EditText)findViewById(R.id.add_log2);
        logtheme2 = (EditText)findViewById(R.id.theme_edt2);
        String logstr = logadd2.getText().toString();
        String logstr2 = logtheme2.getText().toString();
        Log.i(TAG, "onUpdate: title = " + logstr2);
        Log.i(TAG, "onUpdate: detail=" + logstr);
        if(logstr2.length()>0){
            //更新数据库
            LogItem logItem= new LogItem();
            logItem.setLogs(logstr);
            logItem.setLogtheme(logstr2);
            logItem.setTime(writeDate2);
            logItem.setId(shu_id);
            DBManager dbManager = new DBManager(LogeditActivity.this);
            dbManager.update(logItem);
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
        }else{
            //用户没有输入内容
            Toast.makeText(this, "你的主题为空哦", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
