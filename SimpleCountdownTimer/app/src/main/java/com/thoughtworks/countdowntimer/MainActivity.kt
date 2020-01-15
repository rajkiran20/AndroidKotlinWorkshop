package com.thoughtworks.countdowntimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startCountdown(view: View) {
        val timeInSeconds = et_time.text.toString().toLong()
        val intent = Intent(this, CountdownService::class.java)
        intent.putExtra("TIME", TimeUnit.SECONDS.toMillis(timeInSeconds))
        startService(intent)
    }

}