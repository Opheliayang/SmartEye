package com.parklee.smarteye;
/**
 * 点击文档，进入的浏览界面
 * 1、一个ActionBar，文档的名字, 回退按钮，导出pdf，拍照
 * 2、OK 右上角菜单，排序、重命名、选择、删除、导入等
 * 3、显示一张张图片，GridView，图片下有顺序
 * 4、拍照，跳转CameraActivity（注意存在哪里）
 * 5、点击图片，进入ExplorerOnePicActivity
 * 6、排序，对文档进行拖动排序
 * 7、重命名，对文档进行重命名操作
 * 8、选择，批量选择图片进行移动到另一个文档中和删除功能
 * 9、删除，删除此文档，有对话框提醒
 * 10、导入，进行导入
 * 11、导出pdf
 * 12、回退按钮和动画
 */

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ExplorerActivity extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.explorer_actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_camera:
                startActivity(new Intent(ExplorerActivity.this, CameraActivity.class));
                return true;
            case R.id.action_topdf:
                Reverttopdf();
                return true;
            case R.id.action_gallery:
                opengallery();
                return true;
            case R.id.action_rename:
                Reverttopdf();
                return true;
            case R.id.action_sort:
                Reverttopdf();
                return true;
            case R.id.action_choose:
                Reverttopdf();
                return true;
            case R.id.action_delete:
                Reverttopdf();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void opengallery() {
    }

    private void Reverttopdf() {

    }
}
