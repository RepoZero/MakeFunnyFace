package com.funnyface.make.facefunny;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



@SuppressWarnings("unused")
public class Gadgets extends Activity {
    ImageButton dadhi;
    Integer[] dadhiIDs;
    ImageButton ear;
    Integer[] earIDs;
    ImageButton eye;
    Integer[] eyeIDs;
    ImageButton goggles;
    Integer[] gogglesIDs;
    GridView gudgets;
    //    ImageButton head;
//    Integer[] headIDs;
    ImageButton lips;
    Integer[] lipsIDs;
    ImageButton nose;
    Integer[] noseIDs;
    ImageButton tie;
    Integer[] tieIDs;
//    ImageButton hair;
//    Integer[] hairIDs;
//    ImageButton cigar;
//    Integer[] cigarIDs;
//    ImageButton mask;
//    Integer[] maskIDs;
//    ImageButton horror;
//    Integer[] horrorIDs;
    ImageButton emoji;
    Integer[] emojiIDs;
    ImageButton heart;
    Integer[] heartIDs;
    ImageButton text;
    Integer[] textIDs;
    ImageButton Gmask;
    Integer[] gMaskIDs;

    private AdView mAdView;

    public Gadgets() {
        Integer[] arrayOfInteger1 = new Integer[17];
        arrayOfInteger1[0] = R.drawable.lips1;
        arrayOfInteger1[1] = R.drawable.lips2;
        arrayOfInteger1[2] = R.drawable.lips3;
        arrayOfInteger1[3] = R.drawable.lips4;
        arrayOfInteger1[4] = R.drawable.lips5;
        arrayOfInteger1[5] = R.drawable.lips6;
        arrayOfInteger1[6] = R.drawable.lips7;
        arrayOfInteger1[7] = R.drawable.lips8;
        arrayOfInteger1[8] = R.drawable.lips9;
        arrayOfInteger1[9] = R.drawable.lips10;
        arrayOfInteger1[10] = R.drawable.lips11;
        arrayOfInteger1[11] = R.drawable.lips12;
        arrayOfInteger1[12] = R.drawable.lips13;
        arrayOfInteger1[13] = R.drawable.lips14;
        arrayOfInteger1[14] = R.drawable.lips15;
        arrayOfInteger1[15] = R.drawable.lips16;
        arrayOfInteger1[16] = R.drawable.lips17;
        lipsIDs = arrayOfInteger1;

        Integer[] arrayOfInteger2 = new Integer[23];
        arrayOfInteger2[0] = R.drawable.changer5;
        arrayOfInteger2[1] = R.drawable.changer6;
        arrayOfInteger2[2] = R.drawable.changer7;
        arrayOfInteger2[3] = R.drawable.changer8;
        arrayOfInteger2[4] = R.drawable.changer9;
        arrayOfInteger2[5] = R.drawable.changer10;
        arrayOfInteger2[6] = R.drawable.changer11;
        arrayOfInteger2[7] = R.drawable.changer12;
        arrayOfInteger2[8] = R.drawable.changer13;
        arrayOfInteger2[9] = R.drawable.changer14;
        arrayOfInteger2[10] = R.drawable.changer15;
        arrayOfInteger2[11] = R.drawable.changer16;
        arrayOfInteger2[12] = R.drawable.changer17;
        arrayOfInteger2[13] = R.drawable.changer18;
        arrayOfInteger2[14] = R.drawable.changer19;
        arrayOfInteger2[15] = R.drawable.changer20;
        arrayOfInteger2[16] = R.drawable.changer21;
        arrayOfInteger2[17] = R.drawable.changer22;
        arrayOfInteger2[18] = R.drawable.changer23;
        arrayOfInteger2[19] = R.drawable.changer24;
        arrayOfInteger2[20] = R.drawable.changer25;
        arrayOfInteger2[21] = R.drawable.changer26;
        arrayOfInteger2[22] = R.drawable.changer27;
        eyeIDs = arrayOfInteger2;

//        Integer[] arrayOfInteger3 = new Integer[21];
//        arrayOfInteger3[0] = R.drawable.cap1;
//        arrayOfInteger3[1] = R.drawable.cap2;
//        arrayOfInteger3[2] = R.drawable.cap3;
//        arrayOfInteger3[3] = R.drawable.cap4;
//        arrayOfInteger3[4] = R.drawable.cap5;
//        arrayOfInteger3[5] = R.drawable.cap6;
//        arrayOfInteger3[6] = R.drawable.cap7;
//        arrayOfInteger3[7] = R.drawable.cap8;
//        arrayOfInteger3[8] = R.drawable.cap9;
//        arrayOfInteger3[9] = R.drawable.cap10;
//        arrayOfInteger3[10] = R.drawable.cap11;
//        arrayOfInteger3[11] = R.drawable.cap12;
//        arrayOfInteger3[12] = R.drawable.cap13;
//        arrayOfInteger3[13] = R.drawable.cap14;
//        arrayOfInteger3[14] = R.drawable.cap15;
//        arrayOfInteger3[15] = R.drawable.cap16;
//        arrayOfInteger3[16] = R.drawable.cap17;
//        arrayOfInteger3[17] = R.drawable.cap18;
//        arrayOfInteger3[18] = R.drawable.cap19;
//        arrayOfInteger3[19] = R.drawable.cap20;
//        arrayOfInteger3[20] = R.drawable.cap21;
//        headIDs = arrayOfInteger3;

        Integer[] arrayOfInteger4 = new Integer[20];
        arrayOfInteger4[0] = R.drawable.mos1;
        arrayOfInteger4[1] = R.drawable.mos2;
        arrayOfInteger4[2] = R.drawable.mos3;
        arrayOfInteger4[3] = R.drawable.mos4;
        arrayOfInteger4[4] = R.drawable.mos5;
        arrayOfInteger4[5] = R.drawable.mos6;
        arrayOfInteger4[6] = R.drawable.mos7;
        arrayOfInteger4[7] = R.drawable.mos8;
        arrayOfInteger4[8] = R.drawable.mos9;
        arrayOfInteger4[9] = R.drawable.mos10;
        arrayOfInteger4[10] = R.drawable.mos11;
        arrayOfInteger4[11] = R.drawable.mos12;
        arrayOfInteger4[12] = R.drawable.mos13;
        arrayOfInteger4[13] = R.drawable.mos14;
        arrayOfInteger4[14] = R.drawable.mos15;
        arrayOfInteger4[15] = R.drawable.mos16;
        arrayOfInteger4[16] = R.drawable.mos17;
        arrayOfInteger4[17] = R.drawable.mos18;
        arrayOfInteger4[18] = R.drawable.mos19;
        arrayOfInteger4[19] = R.drawable.mos20;
        dadhiIDs = arrayOfInteger4;

        Integer[] arrayOfInteger5 = new Integer[16];
        arrayOfInteger5[0] = R.drawable.goggle1;
        arrayOfInteger5[1] = R.drawable.goggle2;
        arrayOfInteger5[2] = R.drawable.goggle3;
        arrayOfInteger5[3] = R.drawable.goggle4;
        arrayOfInteger5[4] = R.drawable.goggle5;
        arrayOfInteger5[5] = R.drawable.goggle6;
        arrayOfInteger5[6] = R.drawable.goggle7;
        arrayOfInteger5[7] = R.drawable.goggle8;
        arrayOfInteger5[8] = R.drawable.goggle13;
        arrayOfInteger5[9] = R.drawable.goggle14;
        arrayOfInteger5[10] = R.drawable.goggle15;
        arrayOfInteger5[11] = R.drawable.goggle16;
        arrayOfInteger5[12] = R.drawable.goggle17;
        arrayOfInteger5[13] = R.drawable.goggle18;
        arrayOfInteger5[14] = R.drawable.goggle19;
        arrayOfInteger5[15] = R.drawable.goggle20;
        gogglesIDs = arrayOfInteger5;

        Integer[] arrayOfInteger6 = new Integer[9];
        arrayOfInteger6[0] = R.drawable.nose1;
        arrayOfInteger6[1] = R.drawable.nose2;
        arrayOfInteger6[2] = R.drawable.nose3;
        arrayOfInteger6[3] = R.drawable.nose4;
        arrayOfInteger6[4] = R.drawable.nose5;
        arrayOfInteger6[5] = R.drawable.nose6;
        arrayOfInteger6[6] = R.drawable.nose7;
        arrayOfInteger6[7] = R.drawable.nose8;
        arrayOfInteger6[8] = R.drawable.nose9;
        noseIDs = arrayOfInteger6;

        Integer[] arrayOfInteger7 = new Integer[5];
        arrayOfInteger7[0] = R.drawable.ear1;
        arrayOfInteger7[1] = R.drawable.ear2;
        arrayOfInteger7[2] = R.drawable.ear3;
        arrayOfInteger7[3] = R.drawable.ear4;
        arrayOfInteger7[4] = R.drawable.ear5;
        earIDs = arrayOfInteger7;

        Integer[] arrayOfInteger8 = new Integer[13];
        arrayOfInteger8[0] = R.drawable.tie1;
        arrayOfInteger8[1] = R.drawable.tie2;
        arrayOfInteger8[2] = R.drawable.tie3;
        arrayOfInteger8[3] = R.drawable.tie4;
        arrayOfInteger8[4] = R.drawable.tie5;
        arrayOfInteger8[5] = R.drawable.tie6;
        arrayOfInteger8[6] = R.drawable.tie10;
        arrayOfInteger8[7] = R.drawable.tie11;
        arrayOfInteger8[8] = R.drawable.tie12;
        arrayOfInteger8[9] = R.drawable.tie14;
        arrayOfInteger8[10] = R.drawable.tie15;
        arrayOfInteger8[11] = R.drawable.tie16;
        arrayOfInteger8[12] = R.drawable.tie17;
        tieIDs = arrayOfInteger8;

//        Integer[] arrayOfInteger9 = new Integer[17];
//        arrayOfInteger9[0] = R.drawable.hair1;
//        arrayOfInteger9[1] = R.drawable.hair2;
//        arrayOfInteger9[2] = R.drawable.hair3;
//        arrayOfInteger9[3] = R.drawable.hair4;
//        arrayOfInteger9[4] = R.drawable.hair5;
//        arrayOfInteger9[5] = R.drawable.hair6;
//        arrayOfInteger9[6] = R.drawable.hair7;
//        arrayOfInteger9[7] = R.drawable.hair8;
//        arrayOfInteger9[8] = R.drawable.hair9;
//        arrayOfInteger9[9] = R.drawable.hair10;
//        arrayOfInteger9[10] = R.drawable.hair11;
//        arrayOfInteger9[11] = R.drawable.hair12;
//        arrayOfInteger9[12] = R.drawable.hair13;
//        arrayOfInteger9[13] = R.drawable.hair14;
//        arrayOfInteger9[14] = R.drawable.hair15;
//        arrayOfInteger9[15] = R.drawable.hair16;
//        arrayOfInteger9[16] = R.drawable.hair17;
//        hairIDs = arrayOfInteger9;
//
//        Integer[] arrayOfInteger10 = new Integer[10];
//        arrayOfInteger10[0] = R.drawable.mask23;
//        arrayOfInteger10[1] = R.drawable.mask14;
//        arrayOfInteger10[2] = R.drawable.mask15;
//        arrayOfInteger10[3] = R.drawable.mask16;
//        arrayOfInteger10[4] = R.drawable.mask17;
//        arrayOfInteger10[5] = R.drawable.mask18;
//        arrayOfInteger10[6] = R.drawable.mask19;
//        arrayOfInteger10[7] = R.drawable.mask20;
//        arrayOfInteger10[8] = R.drawable.mask21;
//        arrayOfInteger10[9] = R.drawable.mask22;
//        maskIDs = arrayOfInteger10;
//
//        Integer[] arrayOfInteger11 = new Integer[3];
//        arrayOfInteger11[0] = R.drawable.cigar4;
//        arrayOfInteger11[1] = R.drawable.cigar5;
//        arrayOfInteger11[2] = R.drawable.cigar6;
//        cigarIDs = arrayOfInteger11;
//
//        Integer[] arrayOfInteger12 = new Integer[14];
//        arrayOfInteger12[0] = R.drawable.horror1;
//        arrayOfInteger12[1] = R.drawable.horror2;
//        arrayOfInteger12[2] = R.drawable.horror3;
//        arrayOfInteger12[3] = R.drawable.horror4;
//        arrayOfInteger12[4] = R.drawable.horror5;
//        arrayOfInteger12[5] = R.drawable.horror6;
//        arrayOfInteger12[6] = R.drawable.horror7;
//        arrayOfInteger12[7] = R.drawable.horror8;
//        arrayOfInteger12[8] = R.drawable.horror9;
//        arrayOfInteger12[9] = R.drawable.horror10;
//        arrayOfInteger12[10] = R.drawable.horror11;
//        arrayOfInteger12[11] = R.drawable.horror12;
//        arrayOfInteger12[12] = R.drawable.horror13;
//        arrayOfInteger12[13] = R.drawable.horror14;
//        horrorIDs = arrayOfInteger12;

        Integer[] arrayOfInteger13 = new Integer[16];
        arrayOfInteger13[0] = R.drawable.emoji1;
        arrayOfInteger13[1] = R.drawable.emoji2;
        arrayOfInteger13[2] = R.drawable.emoji3;
        arrayOfInteger13[3] = R.drawable.emoji4;
        arrayOfInteger13[4] = R.drawable.emoji5;
        arrayOfInteger13[5] = R.drawable.emoji6;
        arrayOfInteger13[6] = R.drawable.emoji7;
        arrayOfInteger13[7] = R.drawable.emoji8;
        arrayOfInteger13[8] = R.drawable.emoji9;
        arrayOfInteger13[9] = R.drawable.emoji10;
        arrayOfInteger13[10] = R.drawable.emoji11;
        arrayOfInteger13[11] = R.drawable.emoji12;
        arrayOfInteger13[12] = R.drawable.emoji13;
        arrayOfInteger13[13] = R.drawable.emoji14;
        arrayOfInteger13[14] = R.drawable.emoji15;
        arrayOfInteger13[15] = R.drawable.emoji16;
        emojiIDs = arrayOfInteger13;

        Integer[] arrayOfInteger14 = new Integer[17];
        arrayOfInteger14[0] = R.drawable.heart1;
        arrayOfInteger14[1] = R.drawable.heart2;
        arrayOfInteger14[2] = R.drawable.heart3;
        arrayOfInteger14[3] = R.drawable.heart4;
        arrayOfInteger14[4] = R.drawable.heart5;
        arrayOfInteger14[5] = R.drawable.heart6;
        arrayOfInteger14[6] = R.drawable.heart7;
        arrayOfInteger14[7] = R.drawable.heart8;
        arrayOfInteger14[8] = R.drawable.heart9;
        arrayOfInteger14[9] = R.drawable.heart10;
        arrayOfInteger14[10] = R.drawable.heart11;
        arrayOfInteger14[11] = R.drawable.heart12;
        arrayOfInteger14[12] = R.drawable.heart13;
        arrayOfInteger14[13] = R.drawable.heart14;
        arrayOfInteger14[14] = R.drawable.heart15;
        arrayOfInteger14[15] = R.drawable.heart16;
        arrayOfInteger14[16] = R.drawable.heart17;
        heartIDs = arrayOfInteger14;

        Integer[] arrayOfInteger15 = new Integer[9];
        arrayOfInteger15[0] = R.drawable.text2;
        arrayOfInteger15[1] = R.drawable.text3;
        arrayOfInteger15[2] = R.drawable.text4;
        arrayOfInteger15[3] = R.drawable.text5;
        arrayOfInteger15[4] = R.drawable.text6;
        arrayOfInteger15[5] = R.drawable.text7;
        arrayOfInteger15[6] = R.drawable.text8;
        arrayOfInteger15[7] = R.drawable.text9;
        arrayOfInteger15[8] = R.drawable.text10;
        textIDs = arrayOfInteger15;

        Integer[] arrayOfInteger16 = new Integer[22];
        arrayOfInteger16[0] = R.drawable.mask1;
        arrayOfInteger16[1] = R.drawable.mask2;
        arrayOfInteger16[2] = R.drawable.mask3;
        arrayOfInteger16[3] = R.drawable.mask4;
        arrayOfInteger16[4] = R.drawable.mask5;
        arrayOfInteger16[5] = R.drawable.mask6;
        arrayOfInteger16[6] = R.drawable.mask7;
        arrayOfInteger16[7] = R.drawable.mask8;
        arrayOfInteger16[8] = R.drawable.mask9;
        arrayOfInteger16[9] = R.drawable.mask10;
        arrayOfInteger16[10] = R.drawable.mask11;
        arrayOfInteger16[11] = R.drawable.mask12;
        arrayOfInteger16[12] = R.drawable.mask13;
        arrayOfInteger16[13] = R.drawable.mask14;
        arrayOfInteger16[14] = R.drawable.mask15;
        arrayOfInteger16[15] = R.drawable.mask16;
        arrayOfInteger16[16] = R.drawable.mask17;
        arrayOfInteger16[17] = R.drawable.mask18;
        arrayOfInteger16[18] = R.drawable.mask19;
        arrayOfInteger16[19] = R.drawable.mask20;
        arrayOfInteger16[20] = R.drawable.mask21;
        arrayOfInteger16[21] = R.drawable.mask22;
        gMaskIDs = arrayOfInteger16;
    }

    private void function1() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new lipAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(@SuppressWarnings("rawtypes") AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", lipsIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 1);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

    private void function2() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new eyeAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(@SuppressWarnings("rawtypes") AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", eyeIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 2);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

//    private void function3() {
//        gudgets = ((GridView) findViewById(R.id.gridgudgets));
//        gudgets.setAdapter(new headAdapter(this));
//        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//                Intent localIntent = new Intent();
//                localIntent.putExtra("wall_id", headIDs[paramAnonymousInt]);
//                localIntent.putExtra("index1", 3);
//                setResult(-1, localIntent);
//                finish();
//            }
//        });
//    }

    private void function4() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new dadhiAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", dadhiIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 4);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

    private void function5() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new gogglesAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", gogglesIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 5);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

    private void function6() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new noseAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(@SuppressWarnings("rawtypes") AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", noseIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 6);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

    private void function7() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new earAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", earIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 7);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

    private void function8() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new tieAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", tieIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 8);
                setResult(-1, localIntent);
                finish();
            }
        });
    }

//    private void function9() {
//        gudgets = ((GridView) findViewById(R.id.gridgudgets));
//        gudgets.setAdapter(new hairAdapter(this));
//        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//                Intent localIntent = new Intent();
//                localIntent.putExtra("wall_id", hairIDs[paramAnonymousInt]);
//                localIntent.putExtra("index1", 9);
//                setResult(-1, localIntent);
//                finish();
//            }
//        });
//    }
//
//    private void function10() {
//        gudgets = ((GridView) findViewById(R.id.gridgudgets));
//        gudgets.setAdapter(new cigarAdapter(this));
//        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//                Intent localIntent = new Intent();
//                localIntent.putExtra("wall_id", cigarIDs[paramAnonymousInt]);
//                localIntent.putExtra("index1", 10);
//                setResult(-1, localIntent);
//                finish();
//            }
//        });
//    }
//
//    private void function11() {
//        gudgets = ((GridView) findViewById(R.id.gridgudgets));
//        gudgets.setAdapter(new maskAdapter(this));
//        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//                Intent localIntent = new Intent();
//                localIntent.putExtra("wall_id", maskIDs[paramAnonymousInt]);
//                localIntent.putExtra("index1", 11);
//                setResult(-1, localIntent);
//                finish();
//            }
//        });
//    }
//
//    private void function12() {
//        gudgets = ((GridView) findViewById(R.id.gridgudgets));
//        gudgets.setAdapter(new horrorAdapter(this));
//        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//                Intent localIntent = new Intent();
//                localIntent.putExtra("wall_id", horrorIDs[paramAnonymousInt]);
//                localIntent.putExtra("index1", 12);
//                setResult(-1, localIntent);
//                finish();
//            }
//        });
//
//    }

    private void function13() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new EmojiAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", emojiIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 13);
                setResult(-1, localIntent);
                finish();
            }
        });

    }

    private void function14() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new heartAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", heartIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 14);
                setResult(-1, localIntent);
                finish();
            }
        });

    }

    private void function15() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new TextAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", textIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 15);
                setResult(-1, localIntent);
                finish();
            }
        });

    }

    private void function16() {
        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        gudgets.setAdapter(new MaskAdapter(this));
        gudgets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                Intent localIntent = new Intent();
                localIntent.putExtra("wall_id", gMaskIDs[paramAnonymousInt]);
                localIntent.putExtra("index1", 16);
                setResult(-1, localIntent);
                finish();
            }
        });

    }

    protected void onCreate(Bundle paramBundle) {
        requestWindowFeature(1);
        super.onCreate(paramBundle);
        setContentView(R.layout.gadgets);

        mAdView = (AdView) findViewById(R.id.adView);
//        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String deviceId = md5(android_id).toUpperCase();
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


        gudgets = ((GridView) findViewById(R.id.gridgudgets));
        lips = ((ImageButton) findViewById(R.id.Glips));
        eye = ((ImageButton) findViewById(R.id.geye));
//        head = ((ImageButton) findViewById(R.id.Ghead));
        dadhi = ((ImageButton) findViewById(R.id.Gdadhi));
        goggles = ((ImageButton) findViewById(R.id.Ggoggles));
        nose = ((ImageButton) findViewById(R.id.Gnose));
        ear = ((ImageButton) findViewById(R.id.Gear));
        tie = ((ImageButton) findViewById(R.id.Gtie));
//        hair = ((ImageButton) findViewById(R.id.Ghair));
//        cigar = ((ImageButton) findViewById(R.id.Gcigar));
        Gmask = ((ImageButton) findViewById(R.id.Gmask));
//        horror = ((ImageButton) findViewById(R.id.Ghorror));
        emoji = ((ImageButton) findViewById(R.id.Gemoji));
        heart = ((ImageButton) findViewById(R.id.Gheart));
        text = ((ImageButton) findViewById(R.id.Gtext));

        lips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function1();
            }
        });
        eye.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function2();
            }
        });
//        head.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                function3();
//            }
//        });
        dadhi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function4();
            }
        });
        goggles.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function5();
            }
        });
        nose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function6();
            }
        });
        ear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function7();
            }
        });
        tie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function8();
            }
        });
//        hair.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                function9();
//            }
//        });
//        cigar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                function10();
//            }
//        });
        Gmask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Effects.getInstance().playSound(Effects.SOUND_1);
                function16();
            }
        });
//        horror.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Effects.getInstance().playSound(Effects.SOUND_1);
//                function12();
//            }
//        });
        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function13();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function14();
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                function15();
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        int i = getIntent().getExtras().getInt("index");
        switch (i) {
            case 1:
                function1();
                break;
            case 2:
                function2();
                break;
            case 3:
//                function3();
                break;
            case 4:
                function4();
                break;
            case 5:
                function5();
                break;
            case 6:
                function6();
                break;
            case 7:
                function7();
                break;
            case 8:
                function8();
                break;
//            case 9:
//                function9();
//                break;
//            case 10:
//                function10();
//                break;
//            case 11:
//                function11();
//                break;
//            case 12:
//                function12();
//                break;
            case 13:
                function13();
                break;
            case 14:
                function14();
                break;
            case 15:
                function15();
                break;
            case 16:
                function16();
                break;
        }
    }

    class EmojiAdapter extends BaseAdapter {
        Context context;

        public EmojiAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return emojiIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return emojiIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(emojiIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class MaskAdapter extends BaseAdapter {
        Context context;

        public MaskAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return gMaskIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return gMaskIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(gMaskIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class TextAdapter extends BaseAdapter {
        Context context;

        public TextAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return textIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return textIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(textIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class heartAdapter extends BaseAdapter {
        Context context;

        public heartAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return heartIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return heartIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(heartIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class dadhiAdapter extends BaseAdapter {
        Context context;

        public dadhiAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return dadhiIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return dadhiIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(dadhiIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

//    class hairAdapter extends BaseAdapter {
//        Context context;
//
//        public hairAdapter(Context arg2) {
//            context = arg2;
//        }
//
//        public int getCount() {
//            return hairIDs.length;
//        }
//
//        public Object getItem(int paramInt) {
//            return paramInt;
//        }
//
//        public long getItemId(int paramInt) {
//            return hairIDs[paramInt].intValue();
//        }
//
//        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
//            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            if (paramView == null) ;
//            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
//                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
//                localImageView.setImageResource(hairIDs[paramInt].intValue());
////                localImageView.setBackgroundResource(R.drawable.border);
//                return localView;
//            }
//        }
//    }
//
//    class cigarAdapter extends BaseAdapter {
//        Context context;
//
//        public cigarAdapter(Context arg2) {
//            context = arg2;
//        }
//
//        public int getCount() {
//            return cigarIDs.length;
//        }
//
//        public Object getItem(int paramInt) {
//            return paramInt;
//        }
//
//        public long getItemId(int paramInt) {
//            return cigarIDs[paramInt].intValue();
//        }
//
//        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
//            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            if (paramView == null) ;
//            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
//                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
//                localImageView.setImageResource(cigarIDs[paramInt].intValue());
////                localImageView.setBackgroundResource(R.drawable.border);
//                return localView;
//            }
//        }
//    }

    class earAdapter extends BaseAdapter {
        Context context;

        public earAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return earIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return earIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(earIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class eyeAdapter extends BaseAdapter {
        Context context;

        public eyeAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return eyeIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return eyeIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(eyeIDs[paramInt].intValue());
                //   localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class gogglesAdapter extends BaseAdapter {
        Context context;

        public gogglesAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return gogglesIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return gogglesIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(gogglesIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

//    class maskAdapter extends BaseAdapter {
//        Context context;
//
//        public maskAdapter(Context arg2) {
//            context = arg2;
//        }
//
//        public int getCount() {
//            return maskIDs.length;
//        }
//
//        public Object getItem(int paramInt) {
//            return paramInt;
//        }
//
//        public long getItemId(int paramInt) {
//            return maskIDs[paramInt].intValue();
//        }
//
//        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
//            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            if (paramView == null) ;
//            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
//                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
//                localImageView.setImageResource(maskIDs[paramInt].intValue());
////                localImageView.setBackgroundResource(R.drawable.border);
//                return localView;
//            }
//        }
//    }

//    class headAdapter extends BaseAdapter {
//        Context context;
//
//        public headAdapter(Context arg2) {
//            context = arg2;
//        }
//
//        public int getCount() {
//            return headIDs.length;
//        }
//
//        public Object getItem(int paramInt) {
//            return paramInt;
//        }
//
//        public long getItemId(int paramInt) {
//            return headIDs[paramInt].intValue();
//        }
//
//        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
//            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            if (paramView == null) ;
//            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
//                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
//                localImageView.setImageResource(headIDs[paramInt].intValue());
////                localImageView.setBackgroundResource(R.drawable.border);
//                return localView;
//            }
//        }
//    }

//    class horrorAdapter extends BaseAdapter {
//        Context context;
//
//        public horrorAdapter(Context arg2) {
//            context = arg2;
//        }
//
//        public int getCount() {
//            return horrorIDs.length;
//        }
//
//        public Object getItem(int paramInt) {
//            return paramInt;
//        }
//
//        public long getItemId(int paramInt) {
//            return horrorIDs[paramInt].intValue();
//        }
//
//        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
//            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            if (paramView == null) ;
//            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
//                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
//                localImageView.setImageResource(horrorIDs[paramInt].intValue());
////                localImageView.setBackgroundResource(R.drawable.border);
//                return localView;
//            }
//        }
//    }

    class lipAdapter extends BaseAdapter {
        Context context;

        public lipAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return lipsIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return lipsIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {

                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(Gadgets.this.lipsIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    class noseAdapter extends BaseAdapter {
        Context context;

        public noseAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return noseIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return noseIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(noseIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }

    /**
     * Part of the activity's life cycle, StartAppAd should be integrated here
     * for the home button exit ad integration.
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Part of the activity's life cycle, StartAppAd should be integrated here
     * for the back button exit ad integration.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class tieAdapter extends BaseAdapter {
        Context context;

        public tieAdapter(Context arg2) {
            context = arg2;
        }

        public int getCount() {
            return tieIDs.length;
        }

        public Object getItem(int paramInt) {
            return paramInt;
        }

        public long getItemId(int paramInt) {
            return tieIDs[paramInt].intValue();
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            LayoutInflater localLayoutInflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
            if (paramView == null) ;
            for (View localView = localLayoutInflater.inflate(R.layout.row, null); ; localView = paramView) {
                ImageView localImageView = (ImageView) localView.findViewById(R.id.imagerow);
                localImageView.setImageResource(tieIDs[paramInt].intValue());
//                localImageView.setBackgroundResource(R.drawable.border);
                return localView;
            }
        }
    }
}
