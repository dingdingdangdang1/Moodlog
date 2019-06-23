package com.example.asus.moodlog;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateInfoActivity extends AppCompatActivity {
    private static final String TAG ="UpdateInfoActivity" ;
    String nicknameStr,sexStr,qianmingStr,xinzuoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        EditText nickname = (EditText)findViewById(R.id.eT_nickname2);
        EditText qianming = (EditText)findViewById(R.id.eT_qianming2);
        EditText xinzuo = (EditText)findViewById(R.id.eT_xinzuo2);
        RadioGroup groupSex = (RadioGroup)findViewById(R.id.RG_sex2);
        final RadioButton btnfemale=findViewById(R.id.radioBfe2);
        final RadioButton btnmale=findViewById(R.id.radioBm2);

        SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Activity.MODE_PRIVATE);
        nicknameStr = sharedPreferences.getString("nickname","");
        sexStr = sharedPreferences.getString("sex","");
        qianmingStr = sharedPreferences.getString("qianming","");
        xinzuoStr = sharedPreferences.getString("xinzuo","");


        Log.i(TAG, "onCreate: sp nicknameStr=" + nicknameStr);
        Log.i(TAG, "onCreate: sp sexStr=" + sexStr);
        Log.i(TAG, "onCreate: sp qianmingStr=" + qianmingStr);
        Log.i(TAG, "onCreate: sp xinzuoStr=" + xinzuoStr);

        nickname.setText(nicknameStr);
        qianming.setText(qianmingStr);
        xinzuo.setText(xinzuoStr);
        if(sexStr.equals("女")){
            btnfemale.setChecked(true);
        }else {
            btnmale.setChecked(true);
        }

    }

    public void updateinfo(View btn){
        Log.i(TAG, "onClick:updateinfo ");
        EditText nickname = (EditText)findViewById(R.id.eT_nickname2);
        EditText qianming = (EditText)findViewById(R.id.eT_qianming2);
        EditText xinzuo = (EditText)findViewById(R.id.eT_xinzuo2);
        nicknameStr=nickname.getText().toString();
        qianmingStr=qianming.getText().toString();
        xinzuoStr=xinzuo.getText().toString();


        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RG_sex2);
        RadioButton btnfemale=findViewById(R.id.radioBfe2);
        RadioButton btnmale=findViewById(R.id.radioBm2);
        for(int i = 0 ;i < radioGroup.getChildCount();i++){
            RadioButton rb = (RadioButton)radioGroup.getChildAt(i);
            if(rb.isChecked()){
                sexStr=rb.getText().toString();
                break;
            }
        }

        Log.i(TAG, "updateinfo:  nicknameStr=" + nicknameStr);
        Log.i(TAG, "updateinfo: sexStr=" + sexStr);
        Log.i(TAG, "updateinfo:  qianmingStr=" + qianmingStr);
        Log.i(TAG, "updateinfo: xinzuoStr=" + xinzuoStr);

        if(nicknameStr.length()>0 && sexStr.length()>0){
            //更新SP中

            SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nickname",nicknameStr);
            editor.putString("sex",sexStr);
            editor.putString("qianming",qianmingStr);
            editor.putString("xinzuo",xinzuoStr);
            editor.commit();

            Log.i(TAG, "onActivityResult: 数据已保存到sharedPreferences");
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
        }else{
            //用户没有输入内容
            Toast.makeText(this, "更新失败：请填写昵称或者性别", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}
