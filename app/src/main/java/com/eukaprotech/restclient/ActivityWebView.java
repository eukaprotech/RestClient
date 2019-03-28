package com.eukaprotech.restclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.eukaprotech.customdialog.CustomDialog;
import com.eukaprotech.customdialog.CustomDialogHandler;

/**
 * Created by APPS KIT on 15/02/2017.
 */
public class ActivityWebView extends AppCompatActivity {
    Context context;
    Activity activity;
    WebView webView;
    String html_content = "";
    String action;
    Toolbar toolbar;
    String title="Web View";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        context=ActivityWebView.this;
        activity=ActivityWebView.this;
        try{
            Intent intent=getIntent();
            Bundle extras=intent.getExtras();
            action=intent.getAction();
            if(action.equals("browse")) {
                if (extras != null) {
                    html_content = extras.getString("html_content");
                    //title = extras.getString("title");
                }
            }
        }catch(Exception ex){}
        initToolbar(title);
        webView=(WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", html_content, "text/html", "UTF-8", "");
        webView.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (! isFinishing()) {
                    try {
                        if (mProgress == null) {
                            mProgress = new ProgressDialog(context);
                            mProgress.show();
                        }
                        mProgress.setMessage("Loading..." );
                        //mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
                        if (progress == 100) {
                            mProgress.dismiss();
                            mProgress = null;
                        }
                    }catch (Exception ex){}
                }
            }
        });

    }
    private void initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode ==KeyEvent.KEYCODE_BACK)) {
            CustomDialog dialog = new CustomDialog(context, activity, "Exit Web View", "Yes", "No");
            dialog.show(new CustomDialogHandler() {
                @Override
                public void onAccept() {
                    activity.finish();
                }

                @Override
                public void onCancel() {

                }
            });
        }
        return false;
    }
}
