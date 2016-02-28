package com.parklee.smarteye;
/**
 * OCR界面
 * 1、一个ActionBar
 * 2、显示图片
 * 3、从下向上弹出识别结果
 * 4、有一个菜单、可以复制、txt、word
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OCRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
    }
}
