package com.example.asus.moodlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LogshowActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logshow);
        ListView listView=findViewById(R.id.log_list);
        listView.setAdapter(listItemAdapter);
    }
    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        DBManager dbManager = new DBManager(LogshowActivity.this);
        for(LogItem logItem : dbManager.listAll()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle",logItem.getLogtheme()); // 标题文字
            map.put("ItemDetail",logItem.getTime()); // 详情描述
            listItems.add(map);
        }


        // 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.log_show, // ListItem的XML布局实现
                new String[] { "ItemTitle", "ItemDetail" },
                new int[] { R.id.logTheme, R.id.logDate }
        );
    }

}
