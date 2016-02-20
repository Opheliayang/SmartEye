package com.parklee.smarteye;
/**
 * 拍照结果界面
 * 1、无ActionBar
 * 2、显示图片
 * 3、不要这张，返回拍照界面
 * 4、图片剪切（磁性）
 * 5、底部栏，旋转按钮、要不要磁性按钮
 * 6、确定按钮，跳转到HandlerActivity
 *
 * 不要这张已实现
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ResultActivity extends Activity {

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

}
