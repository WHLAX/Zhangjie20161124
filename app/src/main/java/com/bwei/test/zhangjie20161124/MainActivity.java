package com.bwei.test.zhangjie20161124;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
/**
 * autour: 张杰
 * date: 2016/11/24 9:23
 * update: 2016/11/24
 * effect: 引导页类
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler(){
            public void handleMessage(android.os.Message msg) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            };
        }.sendEmptyMessageDelayed(0, 10000);

    }

}
