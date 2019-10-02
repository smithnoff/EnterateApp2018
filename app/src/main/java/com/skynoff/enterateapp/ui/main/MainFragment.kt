package com.skynoff.enterateapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.google.firebase.messaging.FirebaseMessaging
import com.skynoff.enterateapp.CustomWebViewClient
import com.skynoff.enterateapp.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = view.findViewById<WebView>(R.id.main_webview)
        webView.webViewClient = CustomWebViewClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.displayZoomControls = true
        webView.loadUrl("https://enterate24.com/")
        webView.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                val webPage = v as WebView
                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> {
                        if (webPage.canGoBack()) {
                            webPage.goBack()
                            return@setOnKeyListener true
                        }
                    }
                }
            }
            return@setOnKeyListener false
        }
    }
}

