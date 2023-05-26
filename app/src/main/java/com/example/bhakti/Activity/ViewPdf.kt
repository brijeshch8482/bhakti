package com.example.bhakti.Activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityViewPdfBinding
import com.github.barteksc.pdfviewer.PDFView
import java.net.URLEncoder


class ViewPdf : AppCompatActivity() {

    private lateinit var contentBinding: ActivityViewPdfBinding
    private lateinit var pdfView: PDFView

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_pdf)

        contentBinding.pdfView.settings.javaScriptEnabled = true

        val textName = intent.getStringExtra("textName")
        val purl = intent.getStringExtra("purl")

        val pd= ProgressDialog(this)
        pd.setTitle(textName)
        pd.setMessage("Opening.......!!!")

        contentBinding.pdfView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pd.show()

            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                pd.dismiss()
            }

        }
        contentBinding.pdfView.settings.javaScriptEnabled = true
        val pdfSetting = contentBinding.pdfView.settings
        pdfSetting.loadWithOverviewMode =true
        pdfSetting.builtInZoomControls = true
        pdfSetting.setSupportZoom(true)
        pdfSetting.setSaveFormData(true)
        pdfSetting.useWideViewPort = true
        pdfSetting.displayZoomControls = true





        var url =""
        try {
            url= URLEncoder.encode(purl, "UTF-8")
        }catch (e: Exception){

        }

        contentBinding.pdfView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")


    }





}