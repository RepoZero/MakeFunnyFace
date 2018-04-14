package com.funnyface.make.facefunny;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.funnyface.make.cropimage.CropImage;
import com.funnyface.make.util.Global;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



/*
 * Created by Bhoomika Patel on 25-Nov-16.
 */

public class ListAppFolderFilesActivity extends AppCompatActivity {

    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();
    private TextView tv_noNotesMsg;
    private LayoutInflater linf;


    private Uri dataUri;
    private String filePath;
    private static final int PICKFILE_RESULT_CODE = 1;

    private boolean isBackPressed = false;
    private File mFileTemp;
    public static String TEMP_PHOTO_FILE_NAME = "temp.jpg";
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;

    private NativeExpressAdView adView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        setContents();
    }

    private void setContents() {
        tv_noNotesMsg = (TextView) findViewById(R.id.tv_noNotesMsg);
        linf = LayoutInflater.from(this);

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
        tv_toolbar.setText(getString(R.string.my_creation));

        //getting SDcard root path
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.AppFolder);


//        for (int i = 0; i < fileList.size(); i++) {
//            rowFileList(fileList.get(i));
//        }
    }

    private void setMyCollection() {
        getfile(root);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

        if (fileList.size() == 0) {
            tv_noNotesMsg.setVisibility(View.VISIBLE);
        } else {
            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(this, fileList));

            tv_noNotesMsg.setVisibility(View.GONE);
        }
    }

    public ArrayList<File> getfile(File dir) {
        fileList.clear();

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    fileList.add(listFile[i]);
                    getfile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".jpeg")) {
                        fileList.add(listFile[i]);
                    }
                }
            }
        }

//        Global.printLog("getfile", "=========" + fileList.size());
        return fileList;
    }

    private void rowFileList(final File file) {
        final View v = linf.inflate(R.layout.row_file_details, null);
        try {

            ImageView iv_image = (ImageView) v.findViewById(R.id.iv_image);
            TextView tv_fileName = (TextView) v.findViewById(R.id.tv_fileName);
            tv_fileName.setText(file.getName() + "\n" + new Date(file.lastModified()));
            String mfilePath = file.getAbsolutePath();
            File mFile = new File(mfilePath);
            dataUri = Uri.fromFile(mFile);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dataUri);
            iv_image.setImageBitmap(bitmap);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filePath = file.getAbsolutePath();
                    File file = new File(filePath);
                    dataUri = Uri.fromFile(file);
                    Log.e("==================", "clicked====" + dataUri);

                    try {
                        InputStream inputStream = getContentResolver().openInputStream(dataUri);
                        FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                        copyStream(inputStream, fileOutputStream);
                        fileOutputStream.close();
                        inputStream.close();
                        startCropImage();
                    } catch (Exception e) {
                        Log.e("", "Error while creating temp file", e);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        lnr_files.addView(v);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<File> fileList;

        // Constructor
        public ImageAdapter(Context c, ArrayList<File> fileList) {
            mContext = c;
            this.fileList = fileList;
        }

        public int getCount() {
            return fileList.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View v = linf.inflate(R.layout.row_file_details, null);

            ImageView iv_image = (ImageView) v.findViewById(R.id.iv_image);
            TextView tv_fileName = (TextView) v.findViewById(R.id.tv_fileName);
            LinearLayout lnr_main = (LinearLayout) findViewById(R.id.lnr_main);

            final String mfilePath = fileList.get(position).getAbsolutePath();
            final File mFile = new File(mfilePath);
            dataUri = Uri.fromFile(mFile);
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), dataUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            Glide.with(mContext)
                    .load(new File(dataUri.getPath()))
                    .asBitmap()
                    .centerCrop()
                    .into(iv_image);
//            iv_image.setImageBitmap(bitmap);
            SimpleDateFormat sdf_time = new SimpleDateFormat("MMM dd hh:mm a");
            long date = Long.parseLong(String.valueOf(fileList.get(position).lastModified()));
            final Date graph_date = new Date(date);
            final String formated_date = sdf_time.format(graph_date);

            tv_fileName.setText(formated_date + "");

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewCollectionFileActivity.mFile = new File(fileList.get(position).getAbsolutePath());
                    startActivity(new Intent(ListAppFolderFilesActivity.this, ViewCollectionFileActivity.class));
                }
            });

            return v;
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

    private void startCropImage() {
        Intent intent = new Intent(ListAppFolderFilesActivity.this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.CIRCLE_CROP, true);
        intent.putExtra("return-data", false);
        intent.putExtra(CropImage.ASPECT_X, 1);
        intent.putExtra(CropImage.ASPECT_Y, 1);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }


    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
//            Global.printLog("getFilePath", "=====uri.getAuthority=====" + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

//                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, uri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, uri, selection, selectionArgs);
            }
        }
//        Global.printLog("getFilePath", "=====uri.getScheme=====" + uri.getScheme());

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
//        Global.printLog("isExternalStorageDocument", "=====uri.getAuthority=====" + uri.getAuthority());
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
//        Global.printLog("isDownloadsDocument", "=====uri.getAuthority=====" + uri.getAuthority());

        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
//        Global.printLog("isMediaDocument", "=====uri.getAuthority=====" + uri.getAuthority());

        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        //localUri = paramIntent.getData();

        switch (requestCode) {
            case REQUEST_CODE_CROP_IMAGE:
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    return;
                }
                HomeActivity.thumbnail = BitmapFactory.decodeFile(mFileTemp.getPath());
                startActivity(new Intent(this, ImageEditorActivity.class));

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMyCollection();
    }
}
