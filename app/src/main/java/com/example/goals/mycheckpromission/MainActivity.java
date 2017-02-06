package com.example.goals.mycheckpromission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toast(View view) {
        // 检查当前权限的授权值
        int permission1Check = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);
        int permission2Check = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        Toast.makeText(this, "清单文件中已经申请的权限值:" + permission1Check + ",权限2：" + permission2Check + ",系统允许权限值：" + PackageManager.PERMISSION_GRANTED +
                ",系统不允许权限值：" + PackageManager.PERMISSION_DENIED, Toast.LENGTH_SHORT).show();

    }

    public void toast1(View view) {
        // 如果权限被拒
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            boolean flag = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALENDAR);
            Log.i("是否显示权限说明框", "toast1: " + flag);
            //是否彻底禁止权限的提示
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CALENDAR)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_CODE);
            } else {
                //显示申请对话框
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_CODE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        }


        // 权限是否被拒
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //是否彻底禁止权限的提示
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_CODE);
            } else {
                //是否需要显示权限申请，如果权限没被允许：显示申请对话框
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        }
    }

    /**
     * 权限申请的结果返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE:
                /**
                 * 权限1，申请状态处理
                 */
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "申请权限个数：" + grantResults.length + ",READ_CONTACTS被允许", Toast.LENGTH_SHORT).show();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "申请权限个数：" + grantResults.length + ",READ_CONTACTS被拒绝", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

                /**
                 * 权限2，申请状态处理
                 */
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "申请权限个数：" + grantResults.length + ",WRITE_CALENDAR被允许", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "申请权限个数：" + grantResults.length + ",WRITE_CALENDAR被拒绝", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
