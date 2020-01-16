package com.thoughtworks.countdowntimer

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_completion.*

class CompletionActivity : AppCompatActivity(), Callback {
     val CAMERA_REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)

        IncrementAsyncTask(2, this, this).execute()
    }

    override fun invoke(i: Int) {
        tv_timer.text = "Waited for $i"
    }

    fun showButton() {
        btn_start_camera.visibility = View.VISIBLE
        btn_start_camera.setOnClickListener { requestCamera() }
    }

    private fun requestCamera() {
        val permissionResult = ContextCompat.checkSelfPermission(this, CAMERA)
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(CAMERA), CAMERA_REQUEST_CODE)
        } else {
            startCamera()
        }
    }

    private fun startCamera() {
        startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            Toast.makeText(this, "Please grant necessary permissions", Toast.LENGTH_SHORT).show()
        }
    }
}
