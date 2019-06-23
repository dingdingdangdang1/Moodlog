package com.example.asus.moodlog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
        TextView nickname=view.findViewById(R.id.tv_nickname);
        TextView sex=view.findViewById(R.id.tv_sex);
        TextView qianming=view.findViewById(R.id.tv_qinaming);
        TextView xnizuo=view.findViewById(R.id.tv_xinzuo);

        int i=1;
        MyInfoManager infoManager = new MyInfoManager(getActivity());
        myInfoItem= new MyInfoItem();
        infoItem = infoManager.findById(i);
        if(infoItem!=null){
            nickname.setText("昵称："+infoItem.getNickname());
            sex.setText("性别："+infoItem.getSex());
            qianming.setText("签名："+infoItem.getQianming());
            xnizuo.setText("星座："+infoItem.getXinzuo());
        }else{
            nickname.setText("昵称：");
            sex.setText("性别：");
            qianming.setText("签名：");
            xnizuo.setText("星座：");
        }
        Button btnadd=(Button)view.findViewById(R.id.btn_addinfo);
        Button btnupdate=(Button)view.findViewById(R.id.btn_udinfo);
        Button btnyao=(Button)view.findViewById(R.id.btn_yao);
        Button btngrade=(Button)view.findViewById(R.id.btn_grade);
        btnadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                if(infoItem.getNickname()==""){
                    Intent intent = new Intent(getActivity(),AddInfoActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "你已经有信息了，不能多次添加", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在这里使用getActivity
                Intent intent = new Intent(getActivity(),UpdateInfoActivity.class);
                startActivity(intent);
            }
        });
        btngrade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage("功能尚在完善中").setPositiveButton("确认",null).setNegativeButton("否",null);
                builder.create().show();
            }
        });
        return view;
    }

}
