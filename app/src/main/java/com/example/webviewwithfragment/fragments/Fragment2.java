package com.example.webviewwithfragment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webviewwithfragment.R;


public class Fragment2 extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(webView ==null) {
            webView = view.findViewById(R.id.webView);
            webView.getSettings().setAppCacheEnabled(true);


           /* webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setAppCacheMaxSize(5 * 1024 * 1024);
            webView.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);*/
        }
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://www.google.com");
      /*  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10) ;*/

    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}