package com.ddd.demo.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddd.demo.aidl.IUserDataService;
import com.ddd.demo.aidl.User;

public class MainActivity extends Activity {
    private IUserDataService mIUserDataService;//实质是UserDataService的代理

    private Button mButton;
    private EditText mEditText;

    private ServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.ddd.demo.aidl", "com.ddd.demo.aidl.IPhoneBookService"));
////        startService(intent);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        bindService();

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.submit_btn);
        mEditText = (EditText) findViewById(R.id.ed);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bind service(IPC)
                getData(v);
            }
        });
    }

    private void bindService() {
        mConnection  = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("ddd", "----------onServiceConnected----------");
                mIUserDataService = IUserDataService.Stub.asInterface(service);//代理
                Log.e("ddd", "mIUserDataService >> " + mIUserDataService);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mIUserDataService = null;
                Log.e("ddd", "----------onServiceDisconnected----------");
            }
        };

        if (null == mIUserDataService) {
            Log.e("ddd", "---------start & bind service-------------");
            Intent intent = new Intent();
            intent.setAction("com.ddd.demo.aidl.IPhoneBookService");
            intent.setPackage("com.ddd.demo.aidl");
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        }
    }

    private void getData(View v) {
        User user = null;
        long id = Long.parseLong(mEditText.getText().toString().trim());
        Log.e("ddd", "id = " + id);
        Log.e("ddd", "mIUserDataService = " + mIUserDataService);
        try {
            user = mIUserDataService.lookUpUser(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        if (null != user) {
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
