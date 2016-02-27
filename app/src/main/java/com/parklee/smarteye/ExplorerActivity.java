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

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ExplorerActivity extends Activity {

    private String sdCard;
    private String appName = "SmartEye";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);
        sdCard = getSDPath();
        Intent intent = getIntent();
        String dirName = intent.getStringExtra("dirName");
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
        File dirFile = new File(sdCard + "/" + appName + "/" + dirName);
        String[] fileName = dirFile.list();
        for (int i = 0; i < fileName.length; i++) {
            Bitmap pic = BitmapFactory.decodeFile(sdCard + "/" + appName + "/" + dirName + "/" + fileName[i]);
            ImageView imageView = new ImageView(this);
            imageView.setPadding(10, 10, 10, 10);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 400));
            imageView.setImageBitmap(pic);
            layout.addView(imageView);
        }
//	File dirFile=new File()
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString();
    }
}
