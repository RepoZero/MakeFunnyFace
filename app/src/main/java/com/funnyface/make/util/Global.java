package com.funnyface.make.util;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created on 08-Mar-17 by Bhoomika Patel .
 */

public class Global {
    public static String AppFolder = "FunnyFaceMaker";

    public static void snackErrorMessage(Activity c, String str) {
        Snackbar snackbar = Snackbar.make(c.findViewById(android.R.id.content), str, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.RED);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public static void printLog(String key, String value) {
        Log.e("==========key:==", "============value==" + value);
    }
}
