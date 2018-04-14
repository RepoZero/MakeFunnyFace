package com.funnyface.make.push;

import android.app.Application;
import android.content.Intent;

/**
 * Created by lincoln on 3/9/18.
 */

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ServiceRunner.class));
    }

}
