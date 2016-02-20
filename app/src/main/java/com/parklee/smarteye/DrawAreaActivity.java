package com.parklee.smarteye;
/**
 * 分区界面
 * 1、无ActionBar
 * 2、显示图片
 * 3、点击分区按钮，动画出现3个按钮，文字，图片，表格
 * 4、确定按钮，进入OCRActivity
 */
import android.app.Activity;
import android.os.Bundle;

public class DrawAreaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_area);
    }
}
