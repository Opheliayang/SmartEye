package com.parklee.smarteye;
/**
 * 拍照界面
 * 1、无ActionBar
 * 2、单张、多张
 * 3、拍照
 * 4、单张模式下，点击拍照进入ResultActivity
 * 5、多张模式下，继续拍照
 * 拍照已实现
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends Activity implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;
    private String nameForDir;
    private String TAG = "info";
    private int moreNum = 0;
    private RadioGroup group;
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                group = (RadioGroup) findViewById(R.id.choose_model);
                int choosed = group.getCheckedRadioButtonId();
                if (choosed == R.id.one) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    String filename = format.format(date) + ".jpg";
                    File fileFolder = new
                            File(Environment.getExternalStorageDirectory() + "/SmartEye/" + nameForDir);
                    if (!fileFolder.exists()) {
                        fileFolder.mkdir();
                    }
                    File jpgFile = new File(fileFolder, filename);
                    FileOutputStream fos = new FileOutputStream(jpgFile);
                    fos.write(data);
                    fos.close();

                    Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
                    intent.putExtra("picPath", jpgFile.getAbsolutePath());
                    startActivity(intent);
                    CameraActivity.this.finish();
                } else if (choosed == R.id.more) {
                    if (moreNum == 0) {
                        RelativeLayout insertLayout = (RelativeLayout) findViewById(R.id.camera_layout);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
                        layoutParams.topMargin = 8;
                        layoutParams.leftMargin = 8;
                        layoutParams.rightMargin = 8;
                        layoutParams.bottomMargin = 8;
                        layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.btn_capture);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                        Button text = new Button(CameraActivity.this);
                        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        text.setText("完成");
                        text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View arg0) {
                                startActivity(new Intent(CameraActivity.this, MainActivity.class));
                            }
                        });

                        insertLayout.addView(text, layoutParams);
                    }
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    String filename = format.format(date) + ".jpg";
                    File fileFolder = new
                            File(Environment.getExternalStorageDirectory() + "/SmartEye/" + nameForDir);
                    if (!fileFolder.exists()) {
                        fileFolder.mkdir();
                    }
                    File jpgFile = new File(fileFolder, filename);
                    FileOutputStream fos = new FileOutputStream(jpgFile);
                    fos.write(data);
                    fos.close();
                    moreNum++;
                    mCamera.startPreview();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent i = getIntent();
        nameForDir = i.getStringExtra("nameForDir");
        mPreview = (SurfaceView) findViewById(R.id.preview);
        mHolder = mPreview.getHolder();
        mHolder.setKeepScreenOn(true);
        mHolder.addCallback(this);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(null);
            }
        });
        Button capture = (Button) findViewById(R.id.btn_capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mCamera.takePicture(null, null, mPictureCallback);
            }
        });
    }

    public void Capture(View view) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setJpegQuality(80);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {

            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null) {
            mCamera = getCamera();
            if (mHolder != null) {
                setStartPreview(mCamera, mHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 获取Camera对象
     *
     * @return
     */
    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    /**
     * 开始预览相机内容
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            //将系统预览角度调整为竖屏
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }
}
