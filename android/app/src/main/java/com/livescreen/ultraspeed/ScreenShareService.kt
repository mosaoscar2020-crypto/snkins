package com.livescreen.ultraspeed

import android.app.*
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.livekit.android.LiveKit
import io.livekit.android.room.Room
import io.livekit.android.room.participant.LocalParticipant
import io.livekit.android.room.participant.ScreenCaptureParams
import kotlinx.coroutines.*

/*
 * LiveKit version: 2.27.0.
 *
 * The token endpoint is deliberately external. Do NOT put LiveKit API secret
 * in the APK. Replace TOKEN_ENDPOINT with your own HTTPS token service.
 */
class ScreenShareService : Service() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var room: Room? = null

    private val TOKEN_ENDPOINT = "https://live-1corft.sandbox.livekit.io/token?role=host"

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(
            44,
            NotificationCompat.Builder(this, "live")
                .setSmallIcon(android.R.drawable.presence_video_online)
                .setContentTitle("LiveScreen")
                .setContentText("البث يعمل — 60 FPS — بدون صوت")
                .setOngoing(true).build()
        )

        val resultData = if (Build.VERSION.SDK_INT >= 33)
            intent?.getParcelableExtra("projectionData", Intent::class.java)
        else @Suppress("DEPRECATION") intent?.getParcelableExtra("projectionData")
        if (resultData == null) return START_NOT_STICKY

        scope.launch {
            try {
                val token = java.net.URL(TOKEN_ENDPOINT).readText().trim()
                LiveKit.init(applicationContext)
                val r = LiveKit.create(applicationContext)
                room = r
                // Connect to the SFU.
                r.connect("wss://live-z32agtvo.livekit.cloud", token)

                // Publish Android MediaProjection screen. No microphone is enabled.
                val params = ScreenCaptureParams(
                    mediaProjectionPermissionResultData = resultData
                )
                r.localParticipant.setScreenShareEnabled(true, params)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        room?.disconnect()
        room = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            getSystemService(NotificationManager::class.java).createNotificationChannel(
                NotificationChannel("live", "LiveScreen", NotificationManager.IMPORTANCE_LOW)
            )
        }
    }
}