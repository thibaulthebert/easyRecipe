package com.example.zahut.easyrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://moncompte.carrefour.fr/iam/XUI/#login/&realm=%2FCarrefourConnect&goto=http%3A%2F%2Fmoncompte.carrefour.fr%2Fiam%2Foauth2%2FCarrefourConnect%2Fauthorize%3Fresponse_type%3Dcode%26client_id%3Dcarrefour_onecarrefour_web%26scope%3Dopenid%2520iam%2520register-aHR0cHM6Ly93d3cuY2FycmVmb3VyLmZyL21vbi1jb21wdGUvaW5zY3JpcHRpb24%253D%26redirect_uri%3Dhttps%253A%252F%252Fwww.carrefour.fr%252Flogin%252Fcheck");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
