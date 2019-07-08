package com.bytedance.camera.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_picture).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TakePictureActivity.class));
        });

        findViewById(R.id.btn_camera).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RecordVideoActivity.class));
        });

        findViewById(R.id.btn_custom).setOnClickListener(v -> {
            //todo 在这里申请相机、麦克风、存储的权限

            if (!checkPermissionAllGranted(mPermissionsArrays)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(mPermissionsArrays, REQUEST_PERMISSION);
                }
            } else {Toast.makeText(MainActivity.this, "已经获取所有所需权限", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, CustomCameraActivity.class));
            }
        });
    }

    private String[] mPermissionsArrays = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
    private  final static int REQUEST_PERMISSION = 123;
    private boolean checkPermissionAllGranted(String[] mPermissionsArrays)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : mPermissionsArrays) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }
}
