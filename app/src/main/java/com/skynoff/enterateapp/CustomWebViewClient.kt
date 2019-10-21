package com.skynoff.enterateapp

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.MutableLiveData

class CustomWebViewClient : WebViewClient() {

    var isFullLoaded = MutableLiveData<Boolean>()
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        isFullLoaded.value = false
        view?.loadUrl(url!!)
        return false
    }

    override fun onPageFinished(webview: WebView?, url: String?) {
        isFullLoaded.value = true
        webview?.loadUrl(
            "javascript:(function() { " +
                    "document.getElementsByClassName('header-wrap')[0].style.display='none';" +
                    "document.getElementsByClassName('site-footer')[0].style.display='none';" +
                    "document.getElementsByClassName('widget widget_custom-twitter-feeds-widget')[0].style.display='none'; })()"
        )
    }
}