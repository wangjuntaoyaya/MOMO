package com.example.webviewmoudle.activityss;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.webviewmoudle.ChaofanBanJiang;
import com.example.webviewmoudle.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

@Route(path="/activityss/WeViewActivity1")
public class WeViewActivity1 extends AppCompatActivity {


       @Autowired(name="url")
       public String name;
//    @BindView(R.id.webview)
    WebView webview;
//    @BindView(R.id.toolbar)
    Toolbar toolbar;

  @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getStutentt(ChaofanBanJiang chaofanBanJiang){
        Log.e("juntao","chaofanBanJiang"+chaofanBanJiang.getName());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
        Uri uri=getIntent().getData();
      Log.e("juntao","uurl"+name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        ButterKnife.bind(this);
        webview=this.findViewById(R.id.webview);
        toolbar=  this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setInitialScale(0);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
       if(name!=null){
           webview.loadUrl(name);
       }

        webview.setWebChromeClient(new WebChromeClient() {

            //可以设置seekbar 进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);


            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
                super.onReceivedTitle(view, title);
            }

        });
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }
        });


    }
}
