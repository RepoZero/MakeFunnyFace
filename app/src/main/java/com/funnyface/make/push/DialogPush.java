package com.funnyface.make.push;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.funnyface.make.facefunny.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DialogPush extends AppCompatActivity {



    @BindView(R.id.imgClose)protected ImageView imgClose;
    @BindView(R.id.imgLogo)protected ImageView imgLogo;
    @BindView(R.id.imgBanner)protected ImageView imgBanner;

    @BindView(R.id.txtTitle)protected TextView txtTitle;
    @BindView(R.id.txtDesc)protected TextView txtDesc;

    @BindView(R.id.btnDown)protected TextView btnDown;

    private String logo= null;
    private String banner= null;
    private String title= null;
    private String desc= null;
    private String link= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowParams();
        setContentView(R.layout.activity_dialog_push);
        ButterKnife.bind(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            logo = extras.getString("logo");
            banner = extras.getString("banner");
            title = extras.getString("title");
            desc = extras.getString("desc");
            link = extras.getString("link");

        }






        Picasso.with(this).load(logo).into(imgLogo);
        Picasso.with(this).load(banner).into(imgBanner);

        txtTitle.setText(title);
        txtDesc.setText(desc);


        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);

                finish();
            }
        });




    }

    @OnClick(R.id.imgClose)
    void submitButton(View view) {
        if (view.getId() == R.id.imgClose) {

            finish();
        }
    }


    public void setWindowParams() {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.dimAmount = 0;
        wlp.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        getWindow().setAttributes(wlp);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams params = this.getWindow().getAttributes();





    }
}
