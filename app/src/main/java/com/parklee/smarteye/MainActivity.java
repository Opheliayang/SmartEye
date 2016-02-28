package com.parklee.smarteye;
/**
 * 主界面
 * 1、OK 一个ActionBar，，搜索+右上角一个菜单（重命名、删除、教程、合并、关于我们等）
 * 2、文档的图片和名字显示，已用ListView，最好能显示缩略图，左边缩略图，右边文件名
 * 3、OK 两个按钮，拍照和导入，FloatingActionButton
 * 4、点击拍照，进入CameraActivity
 * 5、点击导入，进行导入
 * 6、点击文档，进入ExplorerActivity
 * 7、搜索
 * 8、右上角一个菜单（重命名、删除、教程、合并、关于我们等）里面的功能，长摁也可唤出
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String nameForNewDir;
    private ListView listview;
    public int MID;
    public String dirName;
    public String sdCard;
    private String TAG = "info";
    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdCard = getSDPath();
        if (sdCard == null) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("无法读取内存卡")
                    .setPositiveButton("确定", null)
                    .show();
        } else {
            init(sdCard);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Map<String, Object> list = listItems.get(position);
                    dirName = (String) list.get("fileName");
                    Intent intent = new Intent(MainActivity.this, ExplorerActivity.class);
                    intent.putExtra("dirName", dirName);
                    startActivity(intent);
                }
            });
            //为每个listview的item设置长按相应菜单
            listview.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v,
                                                ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(0, 0, 0, "删除");
                    menu.add(0, 1, 0, "编辑");
                    final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    View view = info.targetView;
                    TextView nameText = (TextView) view.findViewById(R.id.file_name);
                    dirName = (String) nameText.getText();
                }
            });
        }
    }

    //作为长按后弹出来的菜单选择后做出的响应
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                deleteDir(sdCard + "/SmartEye/" + dirName);
                break;
            case 1:
                changeDirName(sdCard + "/SmartEye/" + dirName);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    //公用name_input.xml作为输入新名字的框
    public void changeDirName(String dirPath) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.filename_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView)
                .setTitle("请输入更改后文件夹名字")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name = (EditText) dialogView.findViewById(R.id.et_filename);
                        nameForNewDir = name.getText().toString();
                        File desFileDir = new File(sdCard + "/SmartEye/" + nameForNewDir);
                        if (!desFileDir.exists()) {
                            File srcDirFile = new File(sdCard + "/SmartEye/" + dirName);
                            srcDirFile.renameTo(desFileDir);
                            init(sdCard);
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("提示")
                                    .setMessage("该名字文件夹已存在")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                    }
                }).show();
    }

    //删除指定目录的文件夹及下面的文件
    public void deleteDir(String dirPath) {
        File dirFile = new File(dirPath);
        File[] downFile = dirFile.listFiles();
        for (int i = 0; i < downFile.length; i++) {
            downFile[i].delete();
        }
        dirFile.delete();
        init(sdCard);
    }

    //实现了从sdcard/SmartEye下面读取文件夹，将其以listview的item形式显示出来
    public void init(String sdCard) {
        File picData = new File(sdCard + "/SmartEye");
        if (!picData.exists())
            picData.mkdir();
        String[] fileName = picData.list();
        String test = "";
        for (int i = 0; i < fileName.length; i++) {
            test += fileName[i] + ";";
        }
        listview = (ListView) findViewById(R.id.list_view);
        for (int i = 0; i < fileName.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("fileName", fileName[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.main_list_item,
                new String[]{"fileName"},
                new int[]{R.id.file_name});
        listview.setAdapter(simpleAdapter);
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString();
    }

    public void StartCamera(View view) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.filename_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView)
                .setTitle("请输入新建文件夹名字")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name = (EditText) dialogView.findViewById(R.id.et_filename);
                        nameForNewDir = name.getText().toString();
                        File newDirFile = new File(sdCard + "/SmartEye/" + nameForNewDir);
                        if (newDirFile.exists()) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("提示")
                                    .setMessage("该名字文件夹已存在")
                                    .setPositiveButton("确定", null)
                                    .show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                            intent.putExtra("nameForDir", nameForNewDir);
                            startActivity(intent);
                        }
                    }
                }).show();
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearch() {

    }


}
