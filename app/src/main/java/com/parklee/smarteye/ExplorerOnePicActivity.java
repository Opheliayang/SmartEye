package com.parklee.smarteye;
/**
 * 从文档浏览界面点击图片时，查看图片
 * 1、编辑，进入HandlerActivity
 * 2、ocr识别，进入DrawAreaActivity
 */
import android.app.Activity;
import android.os.Bundle;

public class ExplorerOnePicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_one_pic);
    }
}
