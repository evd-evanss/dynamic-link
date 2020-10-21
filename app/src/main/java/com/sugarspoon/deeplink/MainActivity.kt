package com.sugarspoon.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        Log.d("LINK-DYNAMIC" , getLink().uri.toString())
    }

    private fun setupListener() {
        paymentBt.setOnClickListener {
            val dynamicLinkUri = getLink().uri
            startActivity(Intent(Intent.ACTION_VIEW).setData(dynamicLinkUri))
        }
    }

    private fun getLink(): DynamicLink {
        return Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse(URL)
            domainUriPrefix = DOMAIN_PREFIX
            androidParameters("br.com.minharevendadigital") {
                minimumVersion = 1011001
                fallbackUrl = Uri.parse("https:www.google.com.br")
            }
        }
    }

    companion object {
        const val URL = "https://minharevendadigital.page.link/shop/?barcode=848400000003631001622020009201009300744000611235"
        const val DOMAIN_PREFIX = "https://minharevendadigital.page.link/"
    }

}