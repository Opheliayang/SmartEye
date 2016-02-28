package com.parklee.smarteye;
/**
 * 从文档浏览界面点击图片时，查看图片
 * 1、编辑，进入HandlerActivity
 * 2、ocr识别，进入DrawAreaActivity
 * 3、有actionbar，回退，页面顺序
 * 4、可以手势放大缩小
 * 5、重拍，新的activity，以原来图片名命名
 * 6、删除，对话框提示是否要删除
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ExplorerOnePicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_one_pic);
    }
}
