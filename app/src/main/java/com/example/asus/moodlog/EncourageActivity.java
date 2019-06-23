package com.example.asus.moodlog;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EncourageActivity extends AppCompatActivity implements  Runnable{
    private static final String TAG ="EncourageActivity" ;
    private ArrayList<HashMap<String, String>> soupList; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器
    ListView listView;
    private int msgWhat = 3;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encourage);
        TextView noda2=findViewById(R.id.nodata2);
        listView=findViewById(R.id.soup_list);
        listView.setEmptyView(noda2);
        Thread t = new Thread(this); // 创建新线程
        t.start(); // 开启线程
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == msgWhat){
                    soupList = (ArrayList<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(EncourageActivity.this, soupList, // listItems数据源
                            R.layout.souplistf, // ListItem的XML布局实现
                            new String[] { "ItemTitle", "ItemDetail" },
                            new int[] { R.id.soup_detail, R.id.soup_rsc });
                    listView.setAdapter(listItemAdapter);
                    Log.i("handler","reset list...");
                }
                super.handleMessage(msg);
            }

        };



    }
    @Override
    public void run() {
        Log.i("thread","run.....");
        boolean marker = false;
        ArrayList<HashMap<String,String>> relist=new ArrayList<HashMap<String,String>>();

        try {
            Document doc = Jsoup.connect("https://www.juzimi.com/tags/%E5%8A%B1%E5%BF%97").get();
            Elements soup = doc.select("div.views-field-phpcode");
            for (Element element : soup) {//遍历数组
                HashMap<String, String> map = new HashMap<String, String>();


                String tdStr =element.select("a.xlistju").text();//查找element下的a标签鸡汤的内容
                String author=element.select("a.views-field-field-oriwriter-value").text();
                String book=element.select("a.active").text();
                //查找出处的内容
                String pStr ="——"+author+"《"+book+"》";
                map.put("ItemTitle", tdStr);
                map.put("ItemDetail", pStr);//将出处组合起来放入map中
                relist.add(map);
                Log.i("鸡汤",tdStr + "出处" + pStr);

            }
            marker = true;
        } catch (MalformedURLException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("www", e.toString());
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage();
        msg.what = msgWhat;
        if(marker){
            msg.arg1 = 1;//标记操作成功与否
        }else{
            msg.arg1 = 0;
        }

        msg.obj = relist;
        handler.sendMessage(msg);

        Log.i("thread","sendMessage.....");
    }


}
