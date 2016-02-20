package com.parklee.smarteye;
/**
 * 点击文档，进入的浏览界面
 * 1、一个ActionBar，文档的名字
 * 2、右上角菜单，编辑、pdf设置、重命名、删除、添加等
 * 3、显示一张张图片，Gridlayout？
 * 4、拍照CameraActivity、导入按钮
 * 5、导出pdf
 * 6、点击图片，进入ExplorerOnePicActivity
 */
import android.app.Activity;
import android.os.Bundle;

public class ExplorerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);
    }
}
