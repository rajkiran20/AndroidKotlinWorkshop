package com.thoughtworks.countdowntimer

import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.IBinder

class CountdownService : Service(), CompletionCallback {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        SimpleCountdownTimer(intent?.getLongExtra("TIME", 10L)!!, 10, this).start()
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onFinish() {
        val intent = Intent(this, CompletionActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}