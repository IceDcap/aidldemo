package com.ddd.demo.aidl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Author: doushuqi
 * Date: 15/8/29
 * Time: 上午11:30
 * Email: shuqi.dou@singuloid.com
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, PhoneBookService.class));
    }
}
