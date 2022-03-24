package com.example.isobus

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import java.net.InetAddress
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val webView = findViewById<WebView>(R.id.web)
    val search  = findViewById<SearchView>(R.id.search)
    /*val back = findViewById<ImageView>(R.id.img_back)
    val forward = findViewById<ImageView>(R.id.img_forward)*/

    search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {

            Thread {
                val url = try {
                    InetAddress.getByName(query)
                    "https://$query"
                } catch (e: UnknownHostException) {
                    "https://www.google.com/search?query=$query"
                }

                // aqui ja tem a URL nova pronta
                runOnUiThread {
                    webView.loadUrl(url)
                }
            }.start()
            return false
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://isobus.fertisystem.com.br/")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                /* back.isEnabled = webView.canGoBack() */
                /*forward.isEnabled = webView.canGoForward() */
                search.setQuery(url, false)

            /*back.imageTintList = if (back.isEnabled)
                ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.teal_200))
            else
                ColorStateList.valueOf(Color.GRAY)
            forward.imageTintList = if (forward.isEnabled)
                ColorStateList.valueOf(ContextCompat.getColor(applicationContext, R.color.teal_200))
            else
                ColorStateList.valueOf(Color.GRAY)*/

            super.onPageFinished(view, url)
        }
    }
    /*back.setOnClickListener {
        webView.goBack()
    }
    forward.setOnClickListener {
        webView.goForward()
        }*/
    }
}