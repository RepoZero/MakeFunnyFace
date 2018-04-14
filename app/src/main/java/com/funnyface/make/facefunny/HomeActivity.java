package com.funnyface.make.facefunny;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.funnyface.make.cropimage.CropImage;
import com.funnyface.make.push.ServiceRunner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;


public class HomeActivity extends ActivityManagePermission implements View.OnClickListener {

    private NativeExpressAdView adView;
    private LinearLayout lnrCamera;
    private LinearLayout lnrGallary;
    private LinearLayout lnrCollection;
    private LinearLayout lnrRateUs;
    private LinearLayout lnrMore;
    private LinearLayout lnrShare;

    public static final int CAMERA_PIC_REQUEST = 100;
    public static final int SELECT_PHOTO = 200;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;

    public static Bitmap thumbnail;
    Uri selectedImageUri;
    private File mFileTemp;
    public static String TEMP_PHOTO_FILE_NAME = "temp.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        askCompactPermissions(new String[]{PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE}, new PermissionResult() {
            @Override
            public void permissionGranted() {
                setContents();
            }

            @Override
            public void permissionDenied() {
                setContents();
            }

            @Override
            public void permissionForeverDenied() {
                openSettingsApp(HomeActivity.this);
            }
        });
    }

    private void setContents() {
        adView = (NativeExpressAdView) findViewById(R.id.adView);
//        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String deviceId = md5(android_id).toUpperCase();
//        Global.printLog("device id===", "====================" + deviceId);
//        adView.loadAd(new AdRequest.Builder().addTestDevice(deviceId).build());
       adView.loadAd(new AdRequest.Builder().build());
        adView.setVisibility(View.VISIBLE);

        lnrCamera = (LinearLayout) findViewById(R.id.lnr_camera);
        lnrGallary = (LinearLayout) findViewById(R.id.lnr_gallary);
        lnrCollection = (LinearLayout) findViewById(R.id.lnr_collection);
        lnrRateUs = (LinearLayout) findViewById(R.id.lnr_rateUs);
        lnrMore = (LinearLayout) findViewById(R.id.lnr_more);
        lnrShare = (LinearLayout) findViewById(R.id.lnr_share);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        lnrCamera.setOnClickListener(this);
        lnrGallary.setOnClickListener(this);
        lnrCollection.setOnClickListener(this);
        lnrRateUs.setOnClickListener(this);
        lnrMore.setOnClickListener(this);
        lnrShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnr_camera:
                openCamera();
                break;

            case R.id.lnr_gallary:
                openGallary();
                break;

            case R.id.lnr_collection:
//                File file = new File(Environment.getExternalStorageDirectory(),
//                        Global.AppFolder);
//
//                //       Log.e("path", file.toString());
//
//                if (!file.exists())
//                    file.mkdir();
//
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setDataAndType(Uri.fromFile(file), "*/*");
//                startActivity(intent);

                startActivity(new Intent(HomeActivity.this, ListAppFolderFilesActivity.class));

                break;

            case R.id.lnr_rateUs:
                rateApp();
                break;

            case R.id.lnr_more:
                moreApps();
                break;

            case R.id.lnr_share:
                shareApp();
                break;

        }
    }

    private void rateApp() {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(Uri.parse("bazaar://details?id=" + PACKAGE_NAME));
        intent.setPackage("com.farsitel.bazaar");
        startActivity(intent);
    }

    private void moreApps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + "892991082211"));
        intent.setPackage("com.farsitel.bazaar");
        startActivity(intent);
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("bazaar://details?id=" + PACKAGE_NAME));
        intent.setPackage("com.farsitel.bazaar");
        startActivity(intent);
    }


    private void openGallary() {
        askCompactPermissions(new String[]{PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE}, new PermissionResult() {
            @Override
            public void permissionGranted() {
                startGallary();
            }

            @Override
            public void permissionDenied() {
                startGallary();
            }

            @Override
            public void permissionForeverDenied() {
                openSettingsApp(HomeActivity.this);
            }
        });
    }

    private void startGallary() {
//        Intent localIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        localIntent.setType("image/*");
//        localIntent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(localIntent, SELECT_PHOTO);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    private void openCamera() {
        askCompactPermissions(new String[]{PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE}, new PermissionResult() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void permissionGranted() {
                startCamera();
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void permissionDenied() {
                startCamera();
            }

            @Override
            public void permissionForeverDenied() {
                openSettingsApp(HomeActivity.this);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void startCamera() {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            builder.detectFileUriExposure();

            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);


            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        } catch (ActivityNotFoundException e) {
            Log.e("", "cannot take picture", e);
        }

    }

    private void startCropImage() {
        Intent intent = new Intent(HomeActivity.this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.CIRCLE_CROP, true);
        intent.putExtra("return-data", false);
        intent.putExtra(CropImage.ASPECT_X, 1);
        intent.putExtra(CropImage.ASPECT_Y, 1);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        //localUri = paramIntent.getData();

        switch (requestCode) {
            case CAMERA_PIC_REQUEST:
//                thumbnail = (Bitmap) data.getExtras().get("data");
//
                startCropImage();
//                startActivity(new Intent(this, ImageEditorActivity.class));
                break;

            case SELECT_PHOTO:
//                try {
//                    selectedImageUri = data.getData();
//                    thumbnail = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
//
//                    startActivity(new Intent(this, ImageEditorActivity.class));
//                    super.onActivityResult(requestCode, resultCode, data);
//                } catch (FileNotFoundException localFileNotFoundException) {
//                    localFileNotFoundException.printStackTrace();
//                }
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                    startCropImage();
                } catch (Exception e) {
                    Log.e("", "Error while creating temp file", e);
                }
                break;

            case REQUEST_CODE_CROP_IMAGE:
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    return;
                }
                thumbnail = BitmapFactory.decodeFile(mFileTemp.getPath());
                startActivity(new Intent(this, ImageEditorActivity.class));

                break;
        }
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

}
