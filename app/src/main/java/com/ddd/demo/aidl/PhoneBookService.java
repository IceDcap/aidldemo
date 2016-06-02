package com.ddd.demo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author: doushuqi
 * Date: 15/8/28
 * Time: 下午4:17
 * Email: shuqi.dou@singuloid.com
 */
public class PhoneBookService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ddd", "--------PhoneBookService-----onCreate----------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ddd", "receive start command");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }
//
//    IPhoneBookService.Stub mBinder = new IPhoneBookService.Stub() {
//        @Override
//        public List lookUpPhone(String name) throws RemoteException {
//            List phoneList = new ArrayList();
//
//            return phoneList;
//        }
//    };

    //IUserDataService.Stub是一个抽象类实现接口IUserDataService，所以要在实例中实现
    //lookUpUser方法，也就是远程服务中提供服务的入口。
    IUserDataService.Stub mService = new IUserDataService.Stub() {
        @Override
        public User lookUpUser(long userId) throws RemoteException {
            User user = new User();
            user.setId(101l);
            user.setAge(23);
            user.setPhone("123 456 7890");
            user.setRegistered(true);

            User anonym = new User();
            anonym.setPhone("Not Published");
            anonym.setRegistered(false);

            return (userId == user.getId() ? user : anonym);
        }
    };

}
