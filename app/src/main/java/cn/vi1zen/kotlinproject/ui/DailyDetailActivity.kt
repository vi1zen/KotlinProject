package cn.vi1zen.kotlinproject.ui

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import cn.vi1zen.kotlinproject.R
import cn.vi1zen.kotlinproject.constant.Constant

/**
 * 文件名称：DailyDetailActivity
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/16 13:58
 */


class DailyDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.title = intent.getStringExtra(Constant.TITLE)
        val fabBack = findViewById<FloatingActionButton>(R.id.fab_back)
        fabBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        val webView = findViewById<WebView>(R.id.webView)
        webViewSetting(webView)
        val url = intent.getStringExtra(Constant.URL)
        webView.loadUrl(url)
    }

    private fun webViewSetting(webView: WebView) {
        webView.webViewClient = MyWebViewClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.url.toString())
            }
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}
