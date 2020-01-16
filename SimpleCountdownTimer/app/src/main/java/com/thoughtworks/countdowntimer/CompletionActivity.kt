package com.thoughtworks.countdowntimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_completion.*

class CompletionActivity : AppCompatActivity(), Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)

        IncrementAsyncTask(5, this, this).execute()
    }

    override fun invoke(i: Int) {
        tv_timer.text = "Waited for $i"
    }
}
