package com.example.asus.moodlog;

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
        MyInfoManager infoManager = new MyInfoManager(UpdateInfoActivity.this);
        infoManager.update();
        EditText nickname = (EditText)findViewById(R.id.eT_nickname2);
        EditText qianming = (EditText)findViewById(R.id.eT_qianming2);
        EditText xinzuo = (EditText)findViewById(R.id.eT_xinzuo2);

    }
    public void updateinfo(View btn){
        Log.i(TAG, "onClick:updateinfo ");
        EditText nickname = (EditText)findViewById(R.id.eT_nickname2);
        EditText qianming = (EditText)findViewById(R.id.eT_qianming2);
        EditText xinzuo = (EditText)findViewById(R.id.eT_xinzuo2);
        nicknameStr=nickname.getText().toString();
        qianmingStr=qianming.getText().toString();
        xinzuoStr=xinzuo.getText().toString();

        RadioGroup groupSex = (RadioGroup)findViewById(R.id.RG_sex2);
        final RadioButton btnfemale=findViewById(R.id.radioBfe2);
        final RadioButton btnmale=findViewById(R.id.radioBm2);
        groupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);

                switch(checkedId){
                    case R.id.radioBfe2:
                        sexStr=btnfemale.getText().toString();
                        break;
                    case R.id.radioBm2:
                        sexStr=btnmale.getText().toString();
                        break;
                    default:
                        break;
                }
            }
        });

        if(nicknameStr.length()>0 && sexStr.length()>0){
            //更新数据库中
            MyInfoItem infoItem= new MyInfoItem();
            infoItem.setNickname(nicknameStr);
            infoItem.setSex(sexStr);
            infoItem.setQianming(qianmingStr);
            infoItem.setXinzuo(xinzuoStr);
            MyInfoManager infoManager = new MyInfoManager(UpdateInfoActivity.this);
            infoManager.update(infoItem);
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
        }else{
            //用户没有输入内容
            Toast.makeText(this, "请填写昵称或者性别", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
