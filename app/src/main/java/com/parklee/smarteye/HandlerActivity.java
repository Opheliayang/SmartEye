package com.parklee.smarteye;
/**
 * 处理界面
 * 1、有ActionBar
 * 2、显示图片
 * 3、旋转、剪切、参数、完成按钮
 * 4、参数调节图片对比度、锐化、灰度、黑白、亮度(如何实现）
 * 5、选转90度
 * 6、剪切（边沿检测能否实现）
 * 7、OK 完成、进入ExplorerOnePicActivity
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }

    public void HandlerDone(View view) {
        startActivity(new Intent(HandlerActivity.this, ExplorerOnePicActivity.class));
        HandlerActivity.this.finish();
    }

}
