package com.parklee.smarteye;
/**
 * 拍照结果界面
 * 1、OK 有ActionBar，放弃、确认按钮
 * 2、OK 显示图片
 * 3、OK 不要这张，返回CameraActivity，删除图片
 * 4、OK 确定按钮，跳转到HandlerActivity
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ResultActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String path = getIntent().getStringExtra("picPath");
        ImageView imageView = (ImageView) findViewById(R.id.pic);
        try {
            FileInputStream fis = new FileInputStream(path);
            Bitmap cameraBitmap = BitmapFactory.decodeStream(fis);
            //需要的大小
            int w = getWindowManager().getDefaultDisplay().getWidth();
            int h = getWindowManager().getDefaultDisplay().getHeight();
            Matrix matrix = new Matrix();
            float wx = ((float) w) / cameraBitmap.getHeight();
            float hy = ((float) h) / cameraBitmap.getWidth();
            matrix.postScale(wx, hy);
            cameraBitmap = Bitmap.createBitmap(cameraBitmap, 0, 0, cameraBitmap.getWidth(),
                    cameraBitmap.getHeight(), matrix, true);
            matrix.reset();
            matrix.setRotate(90);
            cameraBitmap = Bitmap.createBitmap(cameraBitmap, 0, 0, cameraBitmap.getWidth(),
                    cameraBitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(cameraBitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void not_this(View view) {
        String path = getIntent().getStringExtra("picPath");
        File file = new File(path);
        file.delete();
        startActivity(new Intent(ResultActivity.this, CameraActivity.class));
        ResultActivity.this.finish();
    }

    public void yes_this(View view) {
        startActivity(new Intent(ResultActivity.this, HandlerActivity.class));
        ResultActivity.this.finish();
    }
}
