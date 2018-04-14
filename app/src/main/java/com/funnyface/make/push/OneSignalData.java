package com.funnyface.make.push;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.funnyface.make.facefunny.R;
import com.onesignal.OSNotification;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by lincoln on 2/24/18.
 */

public class OneSignalData extends com.onesignal.OneSignal {

    private Context context;

    private  String type = null;
    private String link = null;

    private String logo= null;
    private String banner= null;
    private String title= null;
    private String desc= null;

    public OneSignalData(Context context){
        this.context = context;
    }






    public void enableData(){

        com.onesignal.OneSignal.startInit(context)
                .autoPromptLocation(false) // default call promptLocation later
                .setNotificationReceivedHandler(new Handler())
                .inFocusDisplaying(OSInFocusDisplayOption.None)
                .init();

    }



    private class Handler  implements NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {

            Log.e("Ruuuuuuuuuuuuun","Ruuuuuuuuuuuuun");


                JSONObject data = notification.payload.additionalData;

                Log.e("data", data.toString());



                try {
                    type = data.getString("type");
                    link = data.getString("link");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(type.equals("notification")){

                    try {

                        title = data.getString("titr");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    createNotification(title,link);




                }else if(type.equals("notification_dialog")) {

                    try {

                        logo = data.getString("icon");
                        banner = data.getString("banner");
                        title = data.getString("titr");
                        desc = data.getString("matn");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    ArrayList<String> urlImage = new ArrayList<String>();

                    urlImage.add(logo);
                    urlImage.add(banner);

                    bigStyleNotification bsf = new bigStyleNotification(context,title,desc,link,urlImage) ;

                    bsf.execute();




                }else if (type.equals("popup")) {

                    ArrayList<String> packages = new ArrayList<String>();
                    packages.add("org.telegram.messenger");
                    packages.add("com.hanista.mobogram");
                    packages.add("org.thunderdog.challegram");
                    packages.add("org.telegram.plus");
                    packages.add("org.thunderdog.challegram");
                    packages.add("others");


                    for (int i = 0; i < packages.size(); i++) {
                        if (!packages.get(i).equals("others")) {

                            if (isAppInstalled(context, packages.get(i))) {

                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                intent.setPackage(packages.get(i));
                                context.startActivity(intent);
                                break;

                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(link));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }

                    }

                } else if (type.equals("download")) {


                    DownloadNewVersion downloadNewVersion = new DownloadNewVersion();

                    String Tag = null;

                    downloadNewVersion.execute(Tag);

                } else if (type.equals("dialog")) {


                    try {

                        logo = data.getString("icon");
                        banner = data.getString("banner");
                        title = data.getString("titr");
                        desc = data.getString("matn");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Intent i = new Intent(context, DialogPush.class);


                    i.putExtra("logo", logo);
                    i.putExtra("banner", banner);
                    i.putExtra("title", title);
                    i.putExtra("desc", desc);
                    i.putExtra("link", link);

                    context.startActivity(i);




            }



        }

          boolean isAppInstalled(Context context, String packageName) {
            try {
                context.getPackageManager().getApplicationInfo(packageName, 0);
                return true;
            }
            catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }











    }


    private void createNotification(String text, String link){

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(text);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // pending implicit intent to view url
        Intent resultIntent = new Intent(Intent.ACTION_VIEW);
        resultIntent.setData(Uri.parse(link));

        PendingIntent pending = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pending);

        // using the same tag and Id causes the new notification to replace an existing one
        mNotificationManager.notify(String.valueOf(System.currentTimeMillis()), 1, notificationBuilder.build());
    }


    class DownloadNewVersion extends AsyncTask<String,Integer,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);

        }
        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);



            if(result){

//                Toast.makeText(context,"Update Done",
//                        Toast.LENGTH_SHORT).show();

            }else{

//                Toast.makeText(context,"Error: Try Again",
//                        Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            Boolean flag = false;

            try {


                URL url = new URL(link);

                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();


                String PATH = Environment.getExternalStorageDirectory()+"/Download/";
                File file = new File(PATH);
                file.mkdirs();

                File outputFile = new File(file,link.substring(link.lastIndexOf(".")));

                if(outputFile.exists()){
                    outputFile.delete();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);
                InputStream is = c.getInputStream();

                int total_size = 1431692;//size of apk

                byte[] buffer = new byte[1024];
                int len1 = 0;
                int per = 0;
                int downloaded=0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    downloaded +=len1;
                    per = (int) (downloaded * 100 / total_size);
                    publishProgress(per);
                }
                fos.close();
                is.close();

                OpenNewVersion(PATH);

                flag = true;
            } catch (Exception e) {
                Log.e("TAG", "Update Error: " + e.getMessage());
                flag = false;
            }
            return flag;

        }

    }

    void OpenNewVersion(String location) {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(location + "app-debug.apk")),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


    @SuppressLint("StaticFieldLeak")
    public class bigStyleNotification extends AsyncTask<ArrayList<String>, Void, ArrayList<Bitmap>> {

        private Context mContext;
        private String mtitle, message, urlPass;
        ArrayList<String> urlImage ;


        bigStyleNotification(Context context, String mtitle, String message, String link, ArrayList<String> urlImage) {
            super();
            this.mContext = context;
            this.mtitle = mtitle;
            this.message = message;
            this.urlPass = link;
            this.urlImage = urlImage;
        }



        @Override
        protected ArrayList<Bitmap> doInBackground(ArrayList<String>[] arrayLists) {
            InputStream in;
            try {

                ArrayList<Bitmap> myBitmaps = new ArrayList<Bitmap>();

                for(int i=0; i<urlImage.size();i++) {
                    URL url = new URL(this.urlImage.get(i));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    in = connection.getInputStream();

                    myBitmaps.add(BitmapFactory.decodeStream(in));
                }

                return myBitmaps;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);








            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlPass));
            PendingIntent intent = PendingIntent.getActivity(mContext, 0, i,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(intent);

            builder.setTicker(title);

            builder.setSmallIcon(R.mipmap.ic_launcher);


            builder.setAutoCancel(true);

            Notification notification = builder.build();


            RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.small_notif);
            contentView.setTextViewText(R.id.txtTitle_s,title);
            contentView.setImageViewBitmap(R.id.imglogo_s,bitmaps.get(0));

            notification.contentView = contentView;

            if (Build.VERSION.SDK_INT >= 16) {
                RemoteViews bigview = new RemoteViews(mContext.getPackageName(), R.layout.big_notif);
                bigview.setTextViewText(R.id.txtTitle,mtitle);
                bigview.setTextViewText(R.id.txtDesc,message);
                bigview.setImageViewBitmap(R.id.imgLogo,bitmaps.get(0));
                bigview.setImageViewBitmap(R.id.imgBanner,bitmaps.get(1));
                notification.bigContentView = bigview;

            }

            NotificationManager nm = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
            nm.notify(0, notification);







            Log.e("ok","ok");

        }
    }




}















