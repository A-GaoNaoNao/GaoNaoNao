package com.example.day03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class Main3Activity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv_title;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }

    private void initView() {
        iv = findViewById(R.id.iv_san);
        tv_title = findViewById(R.id.tv_san);
        //webview = findViewById(R.id.web);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String img = intent.getStringExtra("ima");
        String web = intent.getStringExtra("web");
        Glide.with(this).load(img).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv);
        tv_title.setText(title);
        /*webview.loadUrl(web);
        webview.setWebViewClient(new WebViewClient());*/
    }
}
