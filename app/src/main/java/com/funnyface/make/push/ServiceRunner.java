package com.funnyface.make.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import co.ronash.pushe.Pushe;

/**
 * Created by lincoln on 2/25/18.
 */

public class ServiceRunner extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public void onStart(Intent intent, int startId) {


        super.onStart(intent, startId);

        Log.e("Service","Run");


        OneSignalData one = new OneSignalData(this);
        one.enableData();


        Pushe.initialize(this,true);



    }

}
