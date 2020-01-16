package com.thoughtworks.countdowntimer

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast

class IncrementAsyncTask(
    private val incrementCount: Int,
    private val callback: Callback,
    val context: Context
) : AsyncTask<Void, Int, String>() {
    var initialCount = 0

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        callback.invoke(values[0]!!)
    }

    override fun doInBackground(vararg params: Void?): String {
        for (i in initialCount..incrementCount) {
            Thread.sleep(1000)
            initialCount++
            publishProgress(i)
        }
        return "Finished waiting"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        (context as CompletionActivity).showButton()
    }
}