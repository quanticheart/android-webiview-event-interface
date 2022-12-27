package com.quanticheart.webiviewevent

import android.app.AlertDialog
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.interface_web)

        // opening the html file in webview
        webView.loadUrl("file:///android_asset/index.html")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.addJavascriptInterface(this, "Dialog")
    }

    @JavascriptInterface
    fun showMsg(fname: String, pswd: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Confirmation").setMessage(
            """
            UserName:	$fname
            Password:	$pswd
            """.trimIndent()
        )
            .setPositiveButton("Ok") { _, i ->
                Toast.makeText(applicationContext, " Data Saved Locally", Toast.LENGTH_SHORT).show()
                // You can use shared preference or db here to store The Data
            }
        builder.create().show()
    }
}