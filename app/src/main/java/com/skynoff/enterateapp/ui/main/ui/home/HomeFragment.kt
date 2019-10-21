package com.skynoff.enterateapp.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skynoff.enterateapp.CustomWebViewClient
import com.skynoff.enterateapp.MAIN_WEB
import com.skynoff.enterateapp.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(this, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeWebView = view.findViewById<WebView>(R.id.home_webView)
        val client = CustomWebViewClient()

        homeWebView.webViewClient = client
        val webSettings = homeWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.displayZoomControls = true
        homeWebView.settings.setSupportMultipleWindows(true)
        homeWebView.loadUrl(MAIN_WEB)
    }
}