package com.example.asus.moodlog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtHome,rbtFunc,rbtMyinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragment_home);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragment_func);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragment_myinfo);

        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();
        rbtHome = (RadioButton)findViewById(R.id.radioHome);
        rbtFunc = (RadioButton)findViewById(R.id.radioFunc);
        rbtMyinfo = (RadioButton)findViewById(R.id.radioMyinfo);
        rbtHome.setBackgroundResource(R.drawable.shape2);

        radioGroup = (RadioGroup)findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);
                fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
                rbtHome.setBackgroundResource(R.drawable.shape1);
                rbtFunc.setBackgroundResource(R.drawable.shape1);
                rbtMyinfo.setBackgroundResource(R.drawable.shape1);
                switch(checkedId){
                    case R.id.radioHome:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtHome.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radioFunc:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtFunc.setBackgroundResource(R.drawable.shape2);
                        break;
                    case R.id.radioMyinfo:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtMyinfo.setBackgroundResource(R.drawable.shape2);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_home){
            //点击后的事件处理
            //测试数据库的添加功能
            DBManager manager=new DBManager(this);
            manager.add(new LogItem("加油哦","2018-02-22","满足"));
            Log.i(TAG,"写入数据库完毕");
            //查询所有数据
            List<LogItem> testlist=manager.listAll();
            for(LogItem i: testlist){
                Log.i(TAG,"取出数据主题："+i.getLogtheme()+"取出数据日期："+i.getTime());
            }
        }else{

        }

        return super.onOptionsItemSelected(item);
    }


}
