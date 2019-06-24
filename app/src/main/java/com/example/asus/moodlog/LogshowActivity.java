package com.example.asus.moodlog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LogshowActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    private static final String TAG ="LogshowActivity" ;
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logshow);
        TextView noda=findViewById(R.id.nodata);
        listView=findViewById(R.id.log_list);
        initListView();
        listView.setAdapter(listItemAdapter);
        listView.setEmptyView(noda);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        DBManager dbManager = new DBManager(LogshowActivity.this);
        for(LogItem logItem : dbManager.listAll()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle",logItem.getLogtheme()); // 标题
            map.put("ItemContent",logItem.getLogs()); // 详情描述
            map.put("ItemTime",logItem.getTime());//时间
            map.put("ItemId", Integer.toString(logItem.getId()));//id-数据表中的
            listItems.add(map);
        }


        // 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.log_show, // ListItem的XML布局实现
                new String[] { "ItemTitle", "ItemTime" },
                new int[] { R.id.logTheme, R.id.logDate }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newlog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.newlog){
            //点击后的事件处理，可填入打开新建日志页面的代码
            Intent intent = new Intent(this,LogaddActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: parent=" + parent);
        Log.i(TAG, "onItemClick: view=" + view);
        Log.i(TAG, "onItemClick: position=" + position);
        Log.i(TAG, "onItemClick: id=" + id);

        //从ListView中获取选中数据
        HashMap<String,String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemContent");
        String IdStr = map.get("ItemId");
       // String timeStr = map.get("ItemTime");
        Log.i(TAG, "onItemClick: titleStr=" + titleStr);
        Log.i(TAG, "onItemClick: detailStr=" + detailStr);
        Log.i(TAG, "onItemClick: IdStr=" + IdStr);
       // Log.i(TAG, "onItemClick: timeStr=" + timeStr);
        //打开新的页面，传入参数
        Intent editTrac = new Intent(this,LogeditActivity.class);
        editTrac.putExtra("title",titleStr);
        editTrac.putExtra("detail",detailStr);
        editTrac.putExtra("IdStr",IdStr);
        listItemAdapter.notifyDataSetChanged();
      //  editTrac.putExtra("time",timeStr);
        startActivity(editTrac);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按列表项position=" + position);
        //构造对话框进行确认操作,删除
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: 对话框事件处理");
                //listItems.remove(position);
                HashMap<String,String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String IdStr = map.get("ItemId");
                DBManager dbManager = new DBManager(LogshowActivity.this);//从数据库中删除
                dbManager.delete(Integer.parseInt(IdStr));
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();
        Log.i(TAG, "onItemLongClick: size=" + listItems.size());


        return true;
    }
}
