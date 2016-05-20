package at.campus02.asy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoActivity extends AppCompatActivity {

    WebView webViewInfo = null;
    String url = "http://www.campus02.at";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        webViewInfo = (WebView) findViewById(R.id.webviewInfo);


        // Set WebView client
        // avoid new browser window for webView links
        webViewInfo.setWebViewClient(new WebViewClient()  {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});

        // fit webpage in webView
        webViewInfo.getSettings().setLoadWithOverviewMode(true);
        webViewInfo.getSettings().setUseWideViewPort(true);
        // load URL
        webViewInfo.loadUrl(url);


    }
}
