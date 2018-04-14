package com.funnyface.make.facefunny;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.io.File;

public class ViewCollectionFileActivity extends AppCompatActivity {

    public static File mFile;

    private NativeExpressAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);
        setContents();
    }

    private void setContents() {
        adView = (NativeExpressAdView) findViewById(R.id.adView);
//        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String deviceId = md5(android_id).toUpperCase();
//        Global.printLog("device id===", "====================" + deviceId);
//        adView.loadAd(new AdRequest.Builder().addTestDevice(deviceId).build());
        adView.loadAd(new AdRequest.Builder().build());
        adView.setVisibility(View.VISIBLE);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("");
        tv_toolbar.setText(getString(R.string.delete_creation));

        ImageView iv_delete = (ImageView) findViewById(R.id.iv_delete);
        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        ImageView iv_image = (ImageView) findViewById(R.id.iv_image);
        Glide.with(this)
                .load(new File(Uri.fromFile(mFile).getPath()))
                .asBitmap()
                .centerCrop()
                .into(iv_image);

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFile.exists())
                    mFile.delete();
                finish();
            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent("android.intent.action.SEND");
                localIntent.setType("image/*");
                localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(mFile));
                localIntent.putExtra("android.intent.extra.SUBJECT", "See This Cool Android App");
                localIntent.putExtra("android.intent.extra.TEXT", "Hi,\n I found this really cool app on android market " + getString(R.string.funny_face_maker) + ". https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(localIntent, "Share Via"));
            }
        });
    }
}
