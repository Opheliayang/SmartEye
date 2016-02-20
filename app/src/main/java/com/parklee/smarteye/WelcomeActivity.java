package com.parklee.smarteye;
/**
 * 欢迎界面
 * 1、无ActionBar
 * 2、随机显示作者的话或欢迎动画
 * 3、自动跳转到MainActivity
 */
import android.app.Activity;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
