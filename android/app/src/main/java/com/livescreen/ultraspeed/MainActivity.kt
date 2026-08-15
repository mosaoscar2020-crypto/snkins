package com.livescreen.ultraspeed

import android.app.Activity
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private val REQUEST_CAPTURE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val status = TextView(this).apply {
            text = "LiveScreen Ultra Speed\n60 FPS • بدون صوت • Stream واحد عبر SFU"
            textSize = 18f
            setPadding(30, 60, 30, 30)
        }
        val button = Button(this).apply { text = "🚀 بدء البث" }
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(status)
            addView(button)
        }
        setContentView(layout)

        button.setOnClickListener {
            val mgr = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            startActivityForResult(mgr.createScreenCaptureIntent(), REQUEST_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK && data != null) {
            val i = Intent(this, ScreenShareService::class.java)
                .putExtra("resultCode", resultCode)
                .putExtra("projectionData", data)
            startForegroundService(i)
        }
    }
}