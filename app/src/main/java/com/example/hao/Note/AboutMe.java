package com.example.hao.Note;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import moe.feng.alipay.zerosdk.AlipayZeroSdk;

/**
 * Created by hao on 2016-08-21.
 */
public class AboutMe extends AppCompatActivity {

    private LinearLayout github;
    private LinearLayout Alipay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);

        github = (LinearLayout) findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebsite(getString(R.string.GitHub));
            }
        });

        Alipay = (LinearLayout) findViewById(R.id.Alipay);
        Alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean b = AlipayZeroSdk.hasInstalledAlipayClient(getApplicationContext());
                if (b) {
                    AlipayZeroSdk.startAlipayClient(AboutMe.this, getString(R.string.Alipay));
                }
            }
        });

    }

    public void openWebsite(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorAccent));
        builder.build().launchUrl(this, Uri.parse(url));
    }
}
