package com.example.asus.moodlog;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class YaoMoodActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private static String str[]={"笑一笑十年少","好气！","让悲伤逆流成河","思考是进步的重要途径"};
    private static int pics[]={R.mipmap.smile,R.mipmap.angry,R.mipmap.ku,R.mipmap.thinking};

    private TextView textView;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_mood);
        textView=findViewById(R.id.tv_yao);
        img=findViewById(R.id.imageView);

        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

    }
    protected void onResume(){
        super.onResume();
        if(sensorManager!=null){
            //第一个是listener，第二个是传感器类型，第三是参数值获取传感器信息的频率
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    protected void onStop(){
        super.onStop();
        if(sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values=event.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];
            int medumValue=10;
            if(Math.abs(x)>medumValue||Math.abs(y)>medumValue||Math.abs(z)>medumValue){
                vibrator.vibrate(200);
                Message msg=new Message();
                msg.what=10;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    Log.i("YaoMoodActivity","摇晃被检测到，执行操作");
                    java.util.Random r=new java.util.Random();
                    int num=Math.abs(r.nextInt())%3;
                    textView.setText(str[num]);
                    img.setImageResource(pics[num]);
                    break;
            }

        }
    };

}
