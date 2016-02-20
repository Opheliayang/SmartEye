package com.parklee.smarteye;
/**
 * 处理界面
 * 1、无ActionBar
 * 2、显示图片
 * 3、调节图片参数到4、剪切、选转、删除等
 * 4、调节图片白平衡、饱和度、亮度
 * 5、确定、进入MainActivity
 *
 */
import android.app.Activity;
import android.os.Bundle;

public class HandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }
}
