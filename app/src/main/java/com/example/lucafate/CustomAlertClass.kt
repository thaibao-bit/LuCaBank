package com.example.lucafate

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.custom_alert.*

class CustomAlertClass(context: Context, title: String ="This is the title", message: String ="But where's the message?") : Dialog(context) {
    init {
        setCancelable(false)
    }
    val message: String = message
    val title: String = title
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_alert)
        alertBody.text = message
        alertTitle.text = title
        btn_yes.setOnClickListener { this.hide() }

    }
}