package com.funnyface.make.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/* You can write such method somewhere in utility class and call it NetworkChangeReceiver like below */
public class NetworkChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (isConnected(context)) {
            context.startService(new Intent(context, ServiceRunner.class));
            Log.e("Broadcast","Start Service");


        }
    }



    private boolean isConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        boolean ifConnected = false;
        for (int i = 0; i < info.length; i++) {
            NetworkInfo connection = info[i];
            if (connection.getState().equals(NetworkInfo.State.CONNECTED)) {
                ifConnected = true;
            }
        }
        return ifConnected;
    }

}
