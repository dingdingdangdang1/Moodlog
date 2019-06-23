package com.example.asus.moodlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddInfoActivity extends AppCompatActivity {

    private static final String TAG ="AddInfoActivity" ;
    String nicknameStr,sexStr,qianmingStr,xinzuoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

    }
    public void saveinfo(View btn){
        Log.i(TAG, "onClick:saveinfo ");
        EditText nickname = (EditText)findViewById(R.id.eT_nickname);
        EditText qianming = (EditText)findViewById(R.id.eT_qianming);
        EditText xinzuo = (EditText)findViewById(R.id.eT_xinzuo);
        nicknameStr=nickname.getText().toString();
        qianmingStr=qianming.getText().toString();
        xinzuoStr=xinzuo.getText().toString();

        RadioGroup groupSex = (RadioGroup)findViewById(R.id.RG_sex);
        final RadioButton btnfemale=findViewById(R.id.radioBfe);
        final RadioButton btnmale=findViewById(R.id.radioBm);
        groupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);

                switch(checkedId){
                    case R.id.radioBfe:
                        sexStr=btnfemale.getText().toString();
                        break;
                    case R.id.radioBm:
                        sexStr=btnmale.getText().toString();
                        break;
                    default:
                        break;
                }
            }
        });

        if(nicknameStr.length()>0 && sexStr.length()>0){
            //保存到数据库中
            int i=1;
            MyInfoItem infoItem= new MyInfoItem();
            infoItem.setId(i);
            infoItem.setNickname(nicknameStr);
            infoItem.setSex(sexStr);
            infoItem.setQianming(qianmingStr);
            infoItem.setXinzuo(xinzuoStr);
            MyInfoManager infoManager = new MyInfoManager(AddInfoActivity.this);
            infoManager.add(infoItem);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }else{
            //用户没有输入内容
            Toast.makeText(this, "请填写昵称或者性别", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
