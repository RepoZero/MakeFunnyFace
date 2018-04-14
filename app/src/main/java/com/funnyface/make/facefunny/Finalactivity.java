package com.funnyface.make.facefunny;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funnyface.make.util.Global;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.funnyface.make.facefunny.ImageEditorActivity.pictureFile;
import static com.funnyface.make.facefunny.ImageEditorActivity.shareImageFilePath;
import static com.funnyface.make.facefunny.ImageEditorActivity.shareImageURI;
import static com.funnyface.make.util.Global.snackErrorMessage;


public class Finalactivity extends Activity implements View.OnClickListener {
    //    ImageButton back;
    Bitmap bmp;
    boolean btn1 = true;
    //    RelativeLayout btnlayout;
    int count;
    String fileName;
    RelativeLayout fulllayout;
    ImageView img;
    File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Global.AppFolder);
//    ImageButton save;
//    ImageButton share;

    TextView tv_facebook, tv_instagram, tv_whatsapp, tv_share, tv_feedback, tv_rate;
    LinearLayout lnr_shareBox;
    //    Uri shareImageURI;
//    File pictureFile;
//    String shareImageFilePath = "";
    String shareText = "";

    private NativeExpressAdView adView;

    public void onBackPressed() {
        finish();
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case 0:
            default:
            case 1:
        }
    }

    protected void onCreate(Bundle paramBundle) {
        requestWindowFeature(1);
        super.onCreate(paramBundle);
        setContentView(R.layout.done);

        final InterstitialAd mInterstitial = new InterstitialAd(Finalactivity.this);
        mInterstitial.setAdUnitId(getResources().getString(
                R.string.interestritial_ad_unit_id));
//        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String deviceId = md5(android_id).toUpperCase();
//        mInterstitial.loadAd(new AdRequest.Builder().addTestDevice(deviceId).build());
        mInterstitial.loadAd(new AdRequest.Builder().build());
        if (mInterstitial != null) {
            mInterstitial.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (mInterstitial.isLoaded()) {
                        mInterstitial.show();
                    }
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    //      mInterstitial.loadAd(new AdRequest.Builder().build());
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    //       mInterstitial.loadAd(new AdRequest.Builder().build());
                }
            });
        }

        shareText = "Hi,\n I found this really cool app on android market " + getString(R.string.funny_face_maker) + ". https://play.google.com/store/apps/details?id=" + getPackageName();

        root.mkdir();

//        btnlayout = ((RelativeLayout) findViewById(R.id.btnlayout));
//        fulllayout = ((RelativeLayout) findViewById(R.id.laout));
//        save = ((ImageButton) findViewById(R.id.bsave));
//
//        share = ((ImageButton) findViewById(R.id.bshare));
        adView = (NativeExpressAdView) findViewById(R.id.adView);
//        Global.printLog("device id===", "====================" + deviceId);
//        adView.loadAd(new AdRequest.Builder().addTestDevice(deviceId).build());
        adView.loadAd(new AdRequest.Builder().build());
        adView.setVisibility(View.VISIBLE);

        tv_share = ((TextView) findViewById(R.id.tv_share));
        tv_facebook = ((TextView) findViewById(R.id.tv_facebook));
        tv_instagram = ((TextView) findViewById(R.id.tv_instagram));
        tv_whatsapp = ((TextView) findViewById(R.id.tv_whatsapp));
        tv_share = ((TextView) findViewById(R.id.tv_share));

        tv_feedback = ((TextView) findViewById(R.id.tv_feedback));
        tv_rate = ((TextView) findViewById(R.id.tv_rate));

        lnr_shareBox = ((LinearLayout) findViewById(R.id.lnr_shareBox));

//        back = ((ImageButton) findViewById(R.id.backbtn));
        img = ((ImageView) findViewById(R.id.fimage));
        bmp = ImageEditorActivity.fbitmap;
        img.setImageBitmap(bmp);

        tv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareViaFacebook();
            }
        });

        tv_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareViaInstagram();
            }
        });

        tv_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareViaWhatsApp();
            }
        });

        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        lnr_shareBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent("android.intent.action.SEND");
                localIntent.setType("text/plain");
                localIntent.putExtra("android.intent.extra.SUBJECT", "See This Cool Android App");
                localIntent.putExtra("android.intent.extra.TEXT", "Hi,\n I found this really cool app on android market " + getString(R.string.funny_face_maker) + ". https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(localIntent, "Share Via"));





            }
        });
        tv_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateApp();
            }
        });

        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void shareViaFacebook() {
        boolean installed = appInstalledOrNot("com.facebook.katana");
        if (installed) {
            label0:
            {
                if (!(new File(shareImageURI.getPath())).exists()) {
                    shareImageURI = Uri.parse((new StringBuilder("file:"))
                            .append(pictureFile).toString());
                }
                share("com.facebook.katana", shareImageURI);
            }
        } else {
            snackErrorMessage(Finalactivity.this, getString(R.string.fb_not_installed));
        }
    }

    private void shareViaInstagram() {
        boolean installed = appInstalledOrNot("com.instagram.android");
        if (installed) {
            label0:
            {
                if (!(new File(shareImageURI.getPath())).exists()) {
                    shareImageURI = Uri.parse((new StringBuilder("file:"))
                            .append(pictureFile).toString());
                }
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, shareImageURI);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                shareIntent.setPackage("com.instagram.android");
                startActivity(shareIntent);
            }
        } else {
            snackErrorMessage(Finalactivity.this, getString(R.string.instagram_not_installed));
        }
    }

    private void shareViaWhatsApp() {
        boolean installed = appInstalledOrNot("com.whatsapp");
        if (installed) {
            label0:
            {
                if (!(new File(shareImageURI.getPath())).exists()) {
                    shareImageURI = Uri.parse((new StringBuilder("file:"))
                            .append(pictureFile).toString());
                }
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, shareImageURI);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi,\n I found this really cool app on android market " + getString(R.string.funny_face_maker) + ". https://play.google.com/store/apps/details?id=" + getPackageName());
                shareIntent.setPackage("com.whatsapp");
                startActivity(shareIntent);
            }
        } else {
            snackErrorMessage(Finalactivity.this, getString(R.string.whatsapp_not_installed));
        }
    }

    private void share(String s, Uri shareuri2) {
        ArrayList arraylist;
        label0:
        {
            try {
                arraylist = new ArrayList();
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/jpeg");
                List list = getPackageManager()
                        .queryIntentActivities(intent, 0);
                if (!list.isEmpty()) {
                    Iterator iterator = list.iterator();
                    do {
                        if (!iterator.hasNext()) {
                            break;
                        }
                        ResolveInfo resolveinfo = (ResolveInfo) iterator.next();
                        Intent intent2 = new Intent(
                                android.content.Intent.ACTION_SEND);
                        intent2.setType("image/html");

                        final String appPackageName = getPackageName(); // getPackageName()
                        String shareBody = "https://play.google.com/store/apps/details?id="
                                + appPackageName.toString();
                        if (resolveinfo.activityInfo.packageName.toLowerCase()
                                .contains(s)
                                || resolveinfo.activityInfo.name.toLowerCase()
                                .contains(s)) {
                            intent2.putExtra(
                                    android.content.Intent.EXTRA_SUBJECT,
                                    "Share Photo");
                            intent2.putExtra(android.content.Intent.EXTRA_TEXT,
                                    shareBody);
                            intent2.putExtra(
                                    android.content.Intent.EXTRA_STREAM,
                                    shareuri2);
                            intent2.setPackage(resolveinfo.activityInfo.packageName);
                            arraylist.add(intent2);
                        }
                    } while (true);
                    break label0;
                }
            } catch (Exception exception) {
                Log.v("VM",
                        (new StringBuilder())
                                .append("Exception while sending image on")
                                .append(s).append(" ")
                                .append(exception.getMessage()).toString());
            }
            return;
        }
        Intent intent1 = Intent.createChooser((Intent) arraylist.remove(0),
                "Select app to share");
        intent1.putExtra("android.intent.extra.INITIAL_INTENTS",
                (Parcelable[]) arraylist.toArray(new Parcelable[0]));
        startActivity(intent1);
        return;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void share() {
        Bitmap bitmap =getBitmapFromView(img);
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private void rateApp() {
        final String appName = getPackageName();// your application
        // package name i.e play
        // store application url
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse("market://details?id=" + appName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + appName)));
        }
    }

}
