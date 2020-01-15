package com.thoughtworks.countdowntimer

import android.os.CountDownTimer
import android.util.Log

class SimpleCountdownTimer(
    val time: Long,
    val interval: Long,
    val completionCallback: CompletionCallback
) : CountDownTimer(time, 10) {
    override fun onFinish() {
        completionCallback.onFinish()
    }

    override fun onTick(millisUntilFinished: Long) {
        Log.i("TAG", "Millis remaining $millisUntilFinished")
    }
}