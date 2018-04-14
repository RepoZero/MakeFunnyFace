package com.funnyface.make.facefunny;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funnyface.make.bmpview.bmpStickerView;
import com.funnyface.make.util.Global;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class ImageEditorActivity extends AppCompatActivity {

    static Bitmap fbitmap;

    ImageButton Done;
    ImageButton dadhi;
    RelativeLayout draglout;
    ImageButton ear;
    ImageButton eye;
    ImageView frame;
    ImageButton goggles;
    //    ImageButton head;
//    ImageButton hair;
//    ImageButton cigar;
//    ImageButton mask;
//    ImageButton horror;
    ImageButton emoji;
    ImageButton heart;
    ImageButton text;
    ImageButton Gmask;
    int index;
    ImageButton lips;
    ImageButton nose;
    ImageButton tie;

    private bmpStickerView ccmCurrentView;
    private ArrayList<View> ccmViews;
    private RelativeLayout ccmContentRootView;
    private TextView tv_appName;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_editor);

        setContents();
    }

    private void setContents() {
        ccmContentRootView = (RelativeLayout) findViewById(R.id.ccmContentRootView);
        ccmViews = new ArrayList<>();

        mAdView = (AdView) findViewById(R.id.adView);
     //   String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    //    String deviceId = md5(android_id).toUpperCase();
//        Global.printLog("device id===", "====================" + deviceId);
//        mAdView.loadAd(new AdRequest.Builder().addTestDevice(deviceId).build());
           mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });


        tv_appName = ((TextView) findViewById(R.id.tv_appName));
        frame = ((ImageView) findViewById(R.id.frame));
        lips = ((ImageButton) findViewById(R.id.Glips));
        draglout = ((RelativeLayout) findViewById(R.id.draglayout));
        eye = ((ImageButton) findViewById(R.id.geye));
//        head = ((ImageButton) findViewById(R.id.Ghead));
        dadhi = ((ImageButton) findViewById(R.id.Gdadhi));
        goggles = ((ImageButton) findViewById(R.id.Ggoggles));
        nose = ((ImageButton) findViewById(R.id.Gnose));
        ear = ((ImageButton) findViewById(R.id.Gear));
        tie = ((ImageButton) findViewById(R.id.Gtie));
//        hair = ((ImageButton) findViewById(R.id.Ghair));
//        cigar = ((ImageButton) findViewById(R.id.Gcigar));
//        mask = ((ImageButton) findViewById(R.id.Gmask));
//        horror = ((ImageButton) findViewById(R.id.Ghorror));
        emoji = ((ImageButton) findViewById(R.id.Gemoji));
        heart = ((ImageButton) findViewById(R.id.Gheart));
        text = ((ImageButton) findViewById(R.id.Gtext));
        Gmask = ((ImageButton) findViewById(R.id.Gmask));

        Done = ((ImageButton) findViewById(R.id.done));

        emoji.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent9 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent9.putExtra("index", 13);
                startActivityForResult(localIntent9, 1);

            }
        });
        heart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent9 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent9.putExtra("index", 14);
                startActivityForResult(localIntent9, 1);

            }
        });
        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent9 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent9.putExtra("index", 15);
                startActivityForResult(localIntent9, 1);

            }
        });
        Gmask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent12 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent12.putExtra("index", 16);
                startActivityForResult(localIntent12, 1);
            }
        });
        lips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent9 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent9.putExtra("index", 1);
                startActivityForResult(localIntent9, 1);

            }
        });
        eye.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent8 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent8.putExtra("index", 2);
                startActivityForResult(localIntent8, 1);
            }
        });
//        head.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                Intent localIntent7 = new Intent(getApplicationContext(), Gadgets.class);
//                localIntent7.putExtra("index", 3);
//                startActivityForResult(localIntent7, 1);
//            }
//        });
        dadhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent6 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent6.putExtra("index", 4);
                startActivityForResult(localIntent6, 1);
            }
        });
        goggles.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent5 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent5.putExtra("index", 5);
                startActivityForResult(localIntent5, 1);
            }
        });
        nose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent4 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent4.putExtra("index", 6);
                startActivityForResult(localIntent4, 1);
            }
        });
        ear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent3 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent3.putExtra("index", 7);
                startActivityForResult(localIntent3, 1);
            }
        });
        tie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                Intent localIntent2 = new Intent(getApplicationContext(), Gadgets.class);
                localIntent2.putExtra("index", 8);
                startActivityForResult(localIntent2, 1);
            }
        });
//        hair.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                Intent localIntent10 = new Intent(getApplicationContext(), Gadgets.class);
//                localIntent10.putExtra("index", 9);
//                startActivityForResult(localIntent10, 1);
//            }
//        });
//        cigar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                Intent localIntent11 = new Intent(getApplicationContext(), Gadgets.class);
//                localIntent11.putExtra("index", 10);
//                startActivityForResult(localIntent11, 1);
//            }
//        });
//        mask.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                Intent localIntent12 = new Intent(getApplicationContext(), Gadgets.class);
//                localIntent12.putExtra("index", 11);
//                startActivityForResult(localIntent12, 1);
//            }
//        });

//        horror.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                Intent localIntent13 = new Intent(getApplicationContext(), Gadgets.class);
//                localIntent13.putExtra("index", 12);
//                startActivityForResult(localIntent13, 1);
//            }
//        });
        Done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                try {
                    ccmCurrentView.setInEdit(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                tv_appName.setVisibility(View.VISIBLE);
                Intent localIntent1 = new Intent(getApplicationContext(), Finalactivity.class);
                draglout.setDrawingCacheEnabled(true);
                draglout.buildDrawingCache(true);
                fbitmap = Bitmap.createBitmap(draglout.getDrawingCache());

                storeImage(fbitmap);
                draglout.setDrawingCacheEnabled(false);
                startActivity(localIntent1);
            }
        });

        @SuppressWarnings("deprecation")
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(HomeActivity.thumbnail);
        frame.setImageDrawable(localBitmapDrawable);
    }

    public static File pictureFile;
    public static Uri shareImageURI;
    public static String shareImageFilePath = "";

    private void storeImage(Bitmap image) {
        pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.e("===",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();

            Toast.makeText(getApplicationContext(), "Image Saved Successfully.", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Log.e("===", "File not found: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Image not saved", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("===", "Error accessing file: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Image not saved", Toast.LENGTH_SHORT).show();
        }

        boolean flag = pictureFile.exists();
        Uri uri = null;
        if (flag) {
            uri = Uri.fromFile(pictureFile);
            shareImageURI = uri;
        }
        shareImageFilePath = getRealPathFromURI(uri);
        Log.e("===", "shareImageURI " + shareImageURI);
    }

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + getApplicationContext().getPackageName()
//                + "/Files" + Global.AppFolder);

        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/" + Global.AppFolder);

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!root.exists()) {
            if (!root.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(root.getAbsolutePath(), mImageName);
        return mediaFile;
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            String s = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.close();
            return s;
        }
    }

    public void gatedata(Intent paramIntent) {
        addStickerView((Integer) paramIntent.getExtras().get("wall_id"));
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        if ((paramInt1 == 1) && (paramInt2 == -1)) {
            index = paramIntent.getIntExtra("index1", 0);
            if (index == 1)
                gatedata(paramIntent);
            if (index == 2)
                gatedata(paramIntent);
            if (index == 3)
                gatedata(paramIntent);
            if (index == 4)
                gatedata(paramIntent);
            if (index == 5)
                gatedata(paramIntent);
            if (index == 6)
                gatedata(paramIntent);
            if (index == 7)
                gatedata(paramIntent);
            if (index == 8)
                gatedata(paramIntent);
            if (index == 9)
                gatedata(paramIntent);
            if (index == 10)
                gatedata(paramIntent);
            if (index == 11)
                gatedata(paramIntent);
            if (index == 12)
                gatedata(paramIntent);
            if (index == 13)
                gatedata(paramIntent);
            if (index == 14)
                gatedata(paramIntent);
            if (index == 15)
                gatedata(paramIntent);
            if (index == 16)
                gatedata(paramIntent);
        }
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    public void onBackPressed() {
        finish();
    }

    private void addStickerView(int id) {
        final bmpStickerView bmpstickerView = new bmpStickerView(this);
        bmpstickerView.setImageResource(id);

        bmpstickerView.setOperationListener(new bmpStickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                ccmViews.remove(bmpstickerView);
                ccmContentRootView.removeView(bmpstickerView);
            }

            @Override
            public void onEdit(bmpStickerView ccstickerView) {
                ccmCurrentView.setInEdit(false);
                ccmCurrentView = ccstickerView;
                ccmCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(bmpStickerView ccstickerView) {
                int position = ccmViews.indexOf(ccstickerView);
                if (position == ccmViews.size() - 1) {
                    return;
                }
                bmpStickerView stickerTemp = (bmpStickerView) ccmViews.remove(position);
                ccmViews.add(ccmViews.size(), stickerTemp);
            }

        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ccmContentRootView.addView(bmpstickerView, lp);
        ccmViews.add(bmpstickerView);
        setCurrentEdit(bmpstickerView);
    }

    private void setCurrentEdit(bmpStickerView ccstickerView) {
        if (ccmCurrentView != null) {
            ccmCurrentView.setInEdit(false);
        }

        ccmCurrentView = ccstickerView;
        ccstickerView.setInEdit(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_appName.setVisibility(View.GONE);
    }
}
