package com.skynoff.enterateapp

import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url!!)
        return true
    }
}