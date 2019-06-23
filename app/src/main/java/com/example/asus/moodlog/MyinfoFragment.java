package com.example.asus.moodlog;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyinfoFragment extends Fragment {

    private static final String TAG ="MyinfoFragment";
    public MyinfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_myinfo, container, false);
        //获取四个文本视图
        TextView nickname=view.findViewById(R.id.tv_nickname);
        TextView sex=view.findViewById(R.id.tv_sex);
        TextView qianming=view.findViewById(R.id.tv_qinaming);
        TextView xnizuo=view.findViewById(R.id.tv_xinzuo);
        //引入SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myinfo", Activity.MODE_PRIVATE);
        //申明写入信息的字符串名
        String nicknameStr,sexStr,qianmingStr,xinzuoStr;

        nicknameStr = sharedPreferences.getString("nickname","");
        sexStr = sharedPreferences.getString("sex","");
        qianmingStr = sharedPreferences.getString("qianming","");
        xinzuoStr = sharedPreferences.getString("xinzuo","");


        Log.i(TAG, "onCreate: sp nicknameStr=" + nicknameStr);
        Log.i(TAG, "onCreate: sp sexStr=" + sexStr);
        Log.i(TAG, "onCreate: sp qianmingStr=" + qianmingStr);
        Log.i(TAG, "onCreate: sp xinzuoStr=" + xinzuoStr);


        nickname.setText("昵称："+nicknameStr);
        sex.setText("性别："+sexStr);
        qianming.setText("签名："+qianmingStr);
        xnizuo.setText("星座："+xinzuoStr);


        Button btnupdate=(Button)view.findViewById(R.id.btn_udinfo);
        Button btnyao=(Button)view.findViewById(R.id.btn_yao);
        Button btngrade=(Button)view.findViewById(R.id.btn_grade);

        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),UpdateInfoActivity.class);
                startActivity(intent);
            }
        });
        btnyao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),YaoMoodActivity.class);
                startActivity(intent);
            }
        });
        btngrade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage("功能尚在完善中").setPositiveButton("确认",null).setNegativeButton("取消",null);
                builder.create().show();
            }
        });
        return view;
    }

}
