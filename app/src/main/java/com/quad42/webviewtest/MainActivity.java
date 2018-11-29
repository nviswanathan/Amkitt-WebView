package com.quad42.webviewtest;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;
    WebView webView;
    ArrayList<String> urls;
    int count = 0;
    private RelativeLayout.LayoutParams layoutParams;

    public void createNewView(){
        if(webView != null && webView.getParent() != null){
            layout.removeView(webView);
            webView.destroy();
        }
        String url = getUrl();
        layoutParams = new RelativeLayout.LayoutParams(layout.getLayoutParams());
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
//        WebView.setWebContentsDebuggingEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        layout.addView(webView, layoutParams);
        webView.loadUrl(url);
        webView.setVisibility(View.VISIBLE);
        count++;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createNewView();
            }
        }, 1000 * 30);
    }

    private String getUrl() {
        if(count >= urls.size() -1){
            count = 0;
        }
        return urls.get(count);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urls = new ArrayList<String>(){
            {
                add("https://www.thanthitv.com/");
                add("https://www.polimernews.com/live-tv");
                add("https://www.youtube.com/watch?v=lzemW2QMeOc");
                add("https://www.bbc.com/news/world");
                add("https://timesofindia.indiatimes.com/world");
                add("https://news.sky.com/world");
                add("https://www.independent.ie/world-news/");
                add("https://www.news.com.au/world");
                add("https://www.ndtv.com/world-news");
                add("https://indianexpress.com/section/world/");
            }
        };
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);
        createNewView();
    }
}
