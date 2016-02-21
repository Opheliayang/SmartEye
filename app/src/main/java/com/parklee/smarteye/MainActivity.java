package com.parklee.smarteye;
/**
 * 主界面
 * 1、一个ActionBar，右上角一个菜单（重命名、删除、关于我们等）
 * 2、文档的图片和名字显示，初定GridLayout 不确定
 * 3、两个按钮，拍照和导入
 * 4、点击拍照，进入CameraActivity
 * 5、点击导入，进行导入
 * 6、点击文档，进入文档浏览界面
 *
 * 拍照已实现
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartCamera(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearch() {

    }

    private void openSettings(){

    }

}
